package ua.com.alevel.thirdlevel;

import ua.com.alevel.TaskRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameOfLife implements TaskRunner {

    int[][] origin;
    int[][] future;
    int m, n;

    @Override
    public void run() {
        while (true) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter the size of your array");
                System.out.println("Enter the number of rows(M):");
                m = Integer.parseInt(bufferedReader.readLine());
                System.out.println("Enter the number of columns(N):");
                n = Integer.parseInt(bufferedReader.readLine());
                init();
                inputArrayFromConsoleAndCatchException(bufferedReader);
                printGeneration(origin);
                nextGeneration();
                printGeneration(future);
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void inputArrayFromConsoleAndCatchException(BufferedReader reader) {
        while (true) {
            try {
                inputArrayFromConsole(reader);
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void inputArrayFromConsole(BufferedReader reader) throws IOException {
        System.out.println("Input your array by rows");
        for (int i = 0; i < m; i++) {
            System.out.println(i + 1 + " row:");
            String s = reader.readLine();
            String[] strings = s.split(" ");
            if (strings.length != n) {
                throw new RuntimeException("You must input " + n + " values in each row");
            }
            int j = 0;
            for (String s1 : strings) {
                int number = Integer.parseInt(s1);
                if (!(number == 0 || number == 1)) {
                    throw new RuntimeException("Your elements must be 0 or 1");
                }
                origin[i][j++] = number;
            }
        }
    }

    private void init() {
        origin = new int[m][n];
        future = new int[m][n];
    }

    private int findNumberAliveNeighbours(int indexI, int indexJ) {
        int aliveNeighbours = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                aliveNeighbours += origin[indexI + i][indexJ + j];
            }
        }
        return aliveNeighbours;
    }

    private void setElementDieOrAlive(int indexI, int indexJ, int aliveNeighbours) {
        if ((origin[indexI][indexJ] == 1) && (aliveNeighbours < 2))
            future[indexI][indexJ] = 0;
        else if ((origin[indexI][indexJ] == 1) && (aliveNeighbours > 3))
            future[indexI][indexJ] = 0;
        else if ((origin[indexI][indexJ] == 0) && (aliveNeighbours == 3))
            future[indexI][indexJ] = 1;
        else
            future[indexI][indexJ] = origin[indexI][indexJ];
    }

    private void printGeneration(int[][] generation) {
        System.out.println("Generation:");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (generation[i][j] == 0)
                    System.out.print(".");
                else
                    System.out.print("*");
            }
            System.out.println();
        }
    }

    private void nextGeneration() {
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                int aliveNeighbours = findNumberAliveNeighbours(i, j);
                aliveNeighbours -= origin[i][j];
                setElementDieOrAlive(i, j, aliveNeighbours);
            }
        }
    }
}
