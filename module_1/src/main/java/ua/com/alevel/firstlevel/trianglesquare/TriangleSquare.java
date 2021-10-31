package ua.com.alevel.firstlevel.trianglesquare;

import ua.com.alevel.TaskRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TriangleSquare implements TaskRunner {

    @Override
    public void run() {
        while (true) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            try {
                System.out.println("Input A point");
                Point a = inputPoint(bufferedReader);
                System.out.println("Input B point");
                Point b = inputPoint(bufferedReader);
                System.out.println("Input C point");
                Point c = inputPoint(bufferedReader);
                double square = countSquare(a, b, c);
                System.out.println("square = " + square);
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Point inputPoint(BufferedReader bufferedReader) throws IOException {
        Point point = new Point();
        System.out.println("Enter x coordinate:");
        double x = Double.parseDouble(bufferedReader.readLine());
        System.out.println("Enter y coordinate:");
        double y = Double.parseDouble(bufferedReader.readLine());
        point.setX(x);
        point.setY(y);
        return point;
    }

    private double countSquare(Point a, Point b, Point c) {
        double abSide = Math.sqrt(Math.pow((b.getX() - a.getX()), 2) + Math.pow((b.getY() - a.getY()), 2));
        double acSide = Math.sqrt(Math.pow((c.getX() - a.getX()), 2) + Math.pow((c.getY() - a.getY()), 2));
        double bcSide = Math.sqrt(Math.pow((c.getX() - b.getX()), 2) + Math.pow((c.getY() - b.getY()), 2));
        double semiPerimeter = (abSide + acSide + bcSide) / 2;
        return Math.sqrt(semiPerimeter * (semiPerimeter - abSide) * (semiPerimeter - acSide) * (semiPerimeter - bcSide));
    }
}

