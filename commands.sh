#!/bin/bash

set -euo pipefail

# --------------------------------------------------
# Helpers
# --------------------------------------------------

error() {
    echo "Error: $*" >&2
    exit 1
}

get_java_major() {
    local version
    version=$("$1" -version 2>&1 | awk -F '"' '/version/ {print $2; exit}')

    [[ -n "$version" ]] || return 1

    if [[ "$version" == 1.* ]]; then
        echo "$version" | cut -d. -f2
    else
        echo "$version" | cut -d. -f1
    fi
}

find_jdk() {
    local requested="$1"
    local candidate java_bin major

    local candidates=(
        "$HOME"/.sdkman/candidates/java/*
        /usr/lib/jvm/*
        /opt/java/*
        "$JAVA_HOME"
    )

    for candidate in "${candidates[@]}"; do
        [[ -x "$candidate/bin/java" ]] || continue

        java_bin="$candidate/bin/java"
        major=$(get_java_major "$java_bin") || continue

        if [[ "$major" == "$requested" ]]; then
            readlink -f "$candidate"
            return 0
        fi
    done

    return 1
}

# --------------------------------------------------
# Commands
# --------------------------------------------------

createProject() {
    [[ $# -ge 1 ]] || error "Usage: createProject PATH [jdk VERSION]"

    local target="$1"
    local requested_jdk=""
    local java_major
    local artifact_id
    local java_path

    if [[ $# -eq 3 && "$2" == "jdk" ]]; then
        requested_jdk="$3"
    elif [[ $# -ne 1 ]]; then
        error "Usage: createProject PATH [jdk VERSION]"
    fi

    # Select JDK
    if [[ -n "$requested_jdk" ]]; then
        java_path=$(find_jdk "$requested_jdk") ||
            error "JDK $requested_jdk is not installed."

        export JAVA_HOME="$java_path"
        export PATH="$JAVA_HOME/bin:$PATH"

        echo "Using JDK $requested_jdk: $JAVA_HOME"
    else
        command -v java >/dev/null || error "Java is not installed."
        echo "Using default JDK."
    fi

    command -v mvn >/dev/null || error "Maven is not installed."

    java_major=$(get_java_major "$(command -v java)") ||
        error "Could not detect Java version."

    echo "Java version: $java_major"
    mvn -version || error "Maven could not start."

    # Project information
    target="${target%/}"
    [[ -n "$target" && "$target" != "." ]] ||
        error "Invalid project path."

    artifact_id=$(basename "$target")
    artifact_id=$(echo "$artifact_id" | sed 's/[^a-zA-Z0-9_.-]/-/g')

    [[ -n "$artifact_id" ]] || error "Invalid project name."

    mkdir -p "$target"

    [[ ! -e "$target/pom.xml" ]] ||
        error "A pom.xml already exists in $target"

    # Maven structure
    mkdir -p "$target/src/main/java/com/example"
    mkdir -p "$target/src/test/java/com/example"

    # POM
    cat >"$target/pom.xml" <<EOF
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         https://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>$artifact_id</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.release>$java_major</maven.compiler.release>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.14.1</version>
            </plugin>
        </plugins>
    </build>

</project>
EOF

    # Main class
    cat >"$target/src/main/java/com/example/App.java" <<'EOF'
package com.example;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
EOF

    echo
    echo "Project created successfully!"
    echo "Location: $target"
    echo "Artifact: $artifact_id"
    echo "JDK: $java_major"
    echo
    echo "To build:"
    echo "  cd $target && mvn package"
}

run() {
    [[ $# -eq 1 ]] || error "Usage: run PATH"

    local target="$1"

    [[ -f "$target/pom.xml" ]] ||
        error "No pom.xml found in $target"

    command -v mvn >/dev/null ||
        error "Maven is not installed."

    # Compile and run the main class
    cd "$target"

    mvn compile exec:java \
        -Dexec.mainClass="com.example.App"
}

# --------------------------------------------------
# Entry point
# --------------------------------------------------
#

case "${1:-}" in
createProject)
    shift
    createProject "$@"
    ;;
run)
    shift
    run "$@"
    ;;
*)
    echo "Usage:"
    echo "  $0 createProject PATH [jdk VERSION]"
    echo "  $0 run PATH"
    exit 1
    ;;
esac
