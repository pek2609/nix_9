package ua.com.alevel.firstlevel.knightmove;

import ua.com.alevel.TaskRunner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class KnightMove implements TaskRunner {

    private static final String[][] chessBoard = {
            {"-", "A", "B", "C", "D", "E", "F", "G", "H"},
            {"1", "o", "o", "o", "o", "o", "o", "o", "o"},
            {"2", "o", "o", "o", "o", "o", "o", "o", "o"},
            {"3", "o", "o", "o", "o", "o", "o", "o", "o"},
            {"4", "o", "o", "o", "o", "o", "o", "o", "o"},
            {"5", "o", "o", "o", "o", "o", "o", "o", "o"},
            {"6", "o", "o", "o", "o", "o", "o", "o", "o"},
            {"7", "o", "o", "o", "o", "o", "o", "o", "o"},
            {"8", "o", "o", "o", "o", "o", "o", "o", "o"},
    };

    public void run() {
        while (true) {
            try {
                printBoard();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter the initial position of your knight");
                String initialPositionLetter = inputLetter(bufferedReader);
                int initialPositionNumber = inputNumber(bufferedReader);
                checkLetterAndNumber(initialPositionLetter, initialPositionNumber);
                System.out.println("Enter the position where to move your knight");
                String potentialPositionLetter = inputLetter(bufferedReader);
                int potentialPositionNumber = inputNumber(bufferedReader);
                checkLetterAndNumber(potentialPositionLetter, potentialPositionNumber);
                int indexOfInitialLetter = BoardHelper.getIndexByLetter(initialPositionLetter);
                int indexOfPotentialLetter = BoardHelper.getIndexByLetter(potentialPositionLetter);
                showPositionsOnTheBoard(indexOfInitialLetter, initialPositionNumber, indexOfPotentialLetter, potentialPositionNumber);
                if (isPossibleToMove(indexOfInitialLetter, initialPositionNumber, indexOfPotentialLetter, potentialPositionNumber)) {
                    System.out.println("Knight can move from " + initialPositionLetter + initialPositionNumber + " to " + potentialPositionLetter + potentialPositionNumber);
                } else {
                    System.out.println("Knight cant move from " + initialPositionLetter + initialPositionNumber + " to " + potentialPositionLetter + potentialPositionNumber);
                }
                clearPositionsFromBoard(indexOfInitialLetter, initialPositionNumber, indexOfPotentialLetter, potentialPositionNumber);
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void printBoard() {
        for (String[] strings : chessBoard) {
            System.out.println(Arrays.toString(strings));
        }
    }

    private boolean isPossibleToMove(int indexOfInitialLetter, int initialPositionNumber, int indexOfPotentialLetter, int potentialPositionNumber) {
        int diffLetters = Math.abs(indexOfInitialLetter - indexOfPotentialLetter);
        int diffNumbers = Math.abs(initialPositionNumber - potentialPositionNumber);
        return (diffLetters == 1 && diffNumbers == 2) || (diffLetters == 2 && diffNumbers == 1);
    }

    private void showPositionsOnTheBoard(int indexOfInitialLetter, int initialPositionNumber, int indexOfPotentialLetter, int potentialPositionNumber) {
        chessBoard[initialPositionNumber][indexOfInitialLetter] = "x";
        chessBoard[potentialPositionNumber][indexOfPotentialLetter] = "?";
        printBoard();
    }

    private void clearPositionsFromBoard(int indexOfInitialLetter, int initialPositionNumber, int indexOfPotentialLetter, int potentialPositionNumber) {
        chessBoard[initialPositionNumber][indexOfInitialLetter] = "o";
        chessBoard[potentialPositionNumber][indexOfPotentialLetter] = "o";
    }

    private String inputLetter(BufferedReader reader) throws IOException {
        System.out.println("Enter the letter (A-H):");
        return reader.readLine().toUpperCase();
    }

    private int inputNumber(BufferedReader reader) throws IOException {
        System.out.println("Enter the number (1-8):");
        return Integer.parseInt(reader.readLine());
    }

    private void checkLetterAndNumber(String letter, int number) throws IOException {
        if (!(isCorrectNumber(number) && isCorrectLetter(letter))) {
            throw new IOException("Unsuitable letter " + letter + " or number " + number);
        }
    }

    private boolean isCorrectLetter(String letter) {
        return !letter.isBlank() && letter.length() == 1 && letter.compareTo("A") >= 0 && letter.compareTo("H") <= 0;
    }

    private boolean isCorrectNumber(int number) {
        return number >= 1 && number <= 8;
    }

}
