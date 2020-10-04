package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    // final static Scanner scanner = new Scanner(System.in);

    static void drawField(char[] array) {

        int i = 0;
        int j = 0;

        System.out.println("---------");
        while (i < 3) {
            System.out.print("|");
            while (j < 3) {
                System.out.print(" " + array[i * 3 + j]);
                j++;
            }
            System.out.println(" |");
            j = 0;
            i++;
        }
        System.out.println("---------");
    }

    static boolean isPossible(char[] array) {
        int i = 0;
        int j = 0;
        int onumber = 0;
        int xnumber = 0;
        for (i = 0; i < 9; i++) {
            if (array[i] == 'X') {
                xnumber++;
            } else if (array[i] == 'O') {
                onumber++;
            }
        }
        if (xnumber - onumber > 1 || onumber - xnumber > 1) {
            return false;
        }
        if (isWins(array, 'X') && isWins(array, 'O')) {
            return false;
        }
        return true;
    }

    static boolean isWins(char[] array, char ch) {

        boolean win = true;

        for (int i = 0; i < 9; i += 3) {
            if (array[i] == array[i + 1] && array[i + 1] == array[i + 2] && array[i] == ch) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (array[i] == array[i + 3] && array[i + 3] == array[i + 6] && array[i] == ch) {
                return true;
            }
        }

        if (array[0] == ch && array[0] == array[4] && array[4] == array[8]) {
            return true;
        }
        if (array[2] == ch && array[2] == array[4] && array[4] == array[6]) {
            return true;
        }
        return false;
    }

    static int nbrChars(char[] array, char symbol) {
        int count = 0;
        for (char ch : array) {
            if (ch == symbol) {
                count++;
            }
        }
        return count;
    }

    static boolean isFull(char[] array) {

        if (nbrChars(array, 'X') + nbrChars(array, 'O') == 9) {
            return true;
        }
        return false;
    }

    static boolean makeMove(char[] array) throws InputMismatchException {
        Scanner scanner = new Scanner(System.in);
        int xp;
        int yp;

        System.out.print("Enter the coordinates:");
        try {
            xp = scanner.nextInt();
            yp = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("You should enter numbers!");
            return false;
        }
        if (yp < 1 || yp > 3 || xp < 1 || xp > 3) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }
        if (array[(3 - yp) * 3 + xp - 1] == 'X' || array[(3 - yp) * 3 + xp - 1] == 'O') {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
        array[(3 - yp) * 3 + xp - 1] = nbrChars(array, 'X') - nbrChars(array, 'O') == 0 ? 'X' : 'O';
        return true;
    }

    public static void main(String[] args) {
        // write your code here
        // Scanner scanner = new Scanner(System.in);
        boolean posibility;
        ///System.out.print("Enter cells:");
        String line = "         "; //scanner.nextLine();
        char[] array = line.toCharArray();
        drawField(array);
        while (isFull(array) == false) {
            while (makeMove(array) == false) ;
            drawField(array);
            posibility = isPossible(array);
            if (posibility == false) {
                System.out.print("Impossible");
                return;
            }
            if (isWins(array, 'X')) {
                System.out.print("X wins");
                return;
            }
            if (isWins(array, 'O')) {
                System.out.print("O wins");
                return;
            }
            if (isFull(array)) {
                System.out.print("Draw");
                return;
            }
        }
    }
}
