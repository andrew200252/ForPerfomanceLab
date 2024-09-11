import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            BufferedReader circleReader = new BufferedReader(new FileReader(args[0]));
            String[] circle = circleReader.readLine().split(" ");
            double circleX = Double.parseDouble(circle[0]);
            double circleY = Double.parseDouble(circle[1]);
            double radius = Double.parseDouble(circleReader.readLine());
            circleReader.close();

            BufferedReader pointsReader = new BufferedReader(new FileReader(args[1]));
            String pointLine;
            while ((pointLine = pointsReader.readLine()) != null){
                String[] points = pointLine.split(" ");
                int pointX = Integer.parseInt(points[0]);
                int pointY = Integer.parseInt(points[1]);
                int position = getPointPosition(circleX, circleY, radius, pointX, pointY);
                System.out.println(position);
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static int getPointPosition(double circleX, double circleY, double radius, int pointX, int pointY){
        double dx = pointX - circleX;
        double dy = pointY - circleY;
        double distanceSquared = dx * dx + dy * dy;
        double radiusSquared = radius * radius;

        if (distanceSquared < radiusSquared) {
            return 1;
        } else if (distanceSquared == radiusSquared) {
            return 0;
        } else {
            return 2;
        }
    }
}