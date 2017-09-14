package com.codecool.snake;

import javafx.geometry.Point2D;

public class Utils {

    /*
    Converts a direction in degrees (0...360) to x and y coordinates.
    The length of this vector is the second parameter
    */
    public static Point2D directionToVector(double directionInDegrees, double length) {
        double directionInRadians = directionInDegrees / 180 * Math.PI;
        Point2D heading = new Point2D(length * Math.sin(directionInRadians), - length * Math.cos(directionInRadians));
        return heading;
    }

    /*
    Gives direction in degrees from start and end point coordinates.
     */
    public static int coordinatesToDirection( Point2D startCoord, Point2D endCoord) {
        double direction = Math.atan((endCoord.getY()-startCoord.getY())/(endCoord.getX()-startCoord.getX()));
        direction = Math.acos(Math.sin(direction)) / Math.PI * 180;
        return (int)Math.round(direction);
    }

    /*
    Calculates a vector to a given length which points from startpoint to endpoint.
    * */
    public static Point2D headingVectorFromCoords (Point2D startCoord, Point2D endCoord, int length) {
        Point2D resultVector =  endCoord.subtract(startCoord);
        resultVector = resultVector.normalize();
        return resultVector.multiply(length);
    }
}
