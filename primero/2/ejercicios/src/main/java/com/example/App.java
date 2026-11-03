package com.example;

public class App {
    public static void main(String[] args) {
        // Ningun ejercicio tendra entrada por teclado a no ser que sea necesario
        // porque luego tardo mucho en testear cosas
        //
        // el que tenga dudas de como hacer Scanner sc = new Scanner(System.in)
        // que se lea la docu

        // ex1();
        // ex2();
        // ex3();
        // ex4();
        // ex5();
        // ex6();
        // ex7();
        ex8();
        // ex9();
        // ex10();
        // ex11();
        // ex12();
        // ex13();
        // ex14();
    }

    // Parell o senar.
    // Demana un nombre enter per teclat i indica si és parell o senar.
    public static void ex1() {
        int n = 16;

        if (n % 2 == 0) {
            System.out.println("Parell");
        } else {
            System.out.println("Senar");
        }
    }

    // El més gran de tres.
    // Demana tres nombres per teclat i mostra quin és el més gran.
    public static void ex2() {

        int a = 8,
                b = 2,
                c = 3;

        int max = a;

        if (max < b) {
            max = b;
        }

        if (max < c) {
            max = c;
        }

        System.out.println(max);

    }

    // Qualificació amb switch.
    // Demana una nota numèrica (0-10) i mostra la qualificació corresponent
    // (Suspès, Aprovat, Notable, Excel·lent) fent servir switch.
    public static void ex3() {

        int nota = 6;
        switch (nota) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                System.out.println("Suspès");
                break;
            case 5:
            case 6:
                System.out.println("Aprovat");
                break;
            case 7:
            case 8:
                System.out.println("Notable");
                break;
            case 9:
            case 10:
                System.out.println("Excelent");
                break;
            default:
                System.out.println("????????????????");
                break;
        }

    }

    // Comptar de l'1 al 20.
    // Mostra per pantalla els números de l'1 al 20, un per línia, fent servir un
    // for.
    public static void ex4() {

        for (int i = 1; i <= 20; i++) {
            System.out.println(i);
        }

    }

    // Suma de l'1 al N.
    // Demana un nombre N per teclat i calcula la suma de tots els enters de l'1 a
    // N,
    // fent servir un while.
    public static void ex5() {

        int N = 50,
                i = 1,
                sum = 0;

        while (i != N) {
            sum += i;
            i++;
        }

        System.out.println(sum);

    }

    // Taula de multiplicar.
    // Demana un nombre per teclat i mostra la seva taula de multiplicar de l'1 al
    // 10.
    public static void ex6() {

        int n = 8;

        for (int i = 0; i <= 10; i++) {
            System.out.printf("%d x %d = %d%n", i, n, i * n);
        }

    }

    // Nombres parells amb continue.
    // Mostra tots els nombres de l'1 al 30, però salta't (no mostris) els múltiples
    // de 3, fent servir continue.
    public static void ex7() {

        for (int i = 0; i <= 30; i++) {
            if (i % 3 != 0) {
                continue;
            }
            System.out.println(i);
        }

    }

    // Cerca amb break.
    // Donat un array d'enters ja definit al codi, demana un valor per teclat i
    // recorre l'array amb un bucle, aturant-lo amb break en trobar-lo;
    // indica si s'ha trobat o no i en quina posició.
    public static void ex8() {
        int buscar = 9;
        int[] miArray = { 1, 2, 4, 7, 8, 9, 10 };
        int where = 0;

        boolean found = false;

        for (int i = 0; i < miArray.length; i++) {
            if (miArray[i] == buscar) {
                found = true;
                where = i;
                break;
            }
        }

        if (found) {
            System.out.println("Encontrado " + buscar + " en la posicion " + where);
        } else {
            System.out.println("No se ha encontrado");
        }

    }

    // Menú repetitiu.
    // Fes un programa amb un menú de tres opcions (1. Saludar, 2. Acomiadar-se,
    // 3. Sortir) que es repeteixi (amb do-while) fins que l'usuari triï sortir.
    public static void ex9() {

    }

    // Divisió seguretat.
    // Demana dos nombres per teclat i mostra el resultat de dividir-los,
    // capturant amb try/catch l'excepció que es produiria si el divisor és zero,
    // mostrant un missatge d'error clar en aquest cas.
    public static void ex10() {

    }

    // Índex fora de rang.
    // Donat un array de 5 elements, demana a l'usuari una posició per teclat
    // i mostra l'element corresponent, capturant l'excepció si la posició
    // no existeix a l'array.
    public static void ex11() {

    }

    // Excepció pròpia: edat no vàlida.
    // Crea una excepció pròpia EdatNoValidaException. Fes un mètode que rebi
    // una edat i llanci aquesta excepció si és negativa o més gran de 120.
    // Prova el mètode amb diversos valors, capturant l'excepció quan es produeixi.
    public static void ex12() {

    }

    // Bucles niats: taula completa.
    // Fes servir dos bucles for niats per mostrar les taules de multiplicar
    // de l'1 al 5 (files) pels números de l'1 al 10 (columnes), en format de taula.
    public static void ex13() {

    }

    // Validació amb bucle i excepció.
    // Fes un programa que demani repetidament un nombre per teclat fins que
    // l'usuari introdueixi un valor vàlid (un enter entre 1 i 100),
    // capturant amb try/catch l'excepció que es produeix si l'usuari escriu
    // un text que no és un número (NumberFormatException).
    public static void ex14() {

    }
}
