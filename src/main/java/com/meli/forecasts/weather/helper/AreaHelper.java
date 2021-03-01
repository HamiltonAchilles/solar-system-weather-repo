package com.meli.forecasts.weather.helper;

import com.meli.forecasts.weather.dto.area.TriangleArea;
import io.swagger.models.auth.In;

import java.math.BigDecimal;
import java.math.MathContext;


public class AreaHelper {

    private static final long AREA_NEAR_ZERO = 30000;
    private static final int SCALE = 8;

    public static Integer getArea(TriangleArea triangle) {
        double xA = triangle.getCoordinateA().getX();
        double xB = triangle.getCoordinateB().getX();
        double xC = triangle.getCoordinateC().getX();

        double yA = triangle.getCoordinateA().getY();
        double yB = triangle.getCoordinateB().getY();
        double yC = triangle.getCoordinateC().getY();

        return (int)Math.round(0.5 * Math.abs(
                            multiply(xA, yB) +
                            multiply(xC, yA) +
                            multiply(xB, yC) -
                            multiply(xC, yB) -
                            multiply(xA, yC) -
                            multiply(xB, yA))
        );
    }

    public static Integer getPerimeter(TriangleArea triangleArea) {
        double xA = triangleArea.getCoordinateA().getX();
        double xB = triangleArea.getCoordinateB().getX();
        double xC = triangleArea.getCoordinateC().getX();

        double yA = triangleArea.getCoordinateA().getY();
        double yB = triangleArea.getCoordinateB().getY();
        double yC = triangleArea.getCoordinateC().getY();

        int distanceAB = sqrt(
                pow2(xB - xA) + pow2(yB - yA)
        );
        int distanceBC = sqrt(
                pow2(xC - xB) + pow2(yC - yB)
        );
        int distanceCA = sqrt(
                pow2(xA - xC) + pow2(yA - yC)
        );
        return distanceAB + distanceBC + distanceCA;
    }

    private static int sqrt(double val) {
        return new BigDecimal(val).round(MathContext.DECIMAL32).setScale(SCALE).sqrt(MathContext.DECIMAL32).intValue();
    }

    private static double pow2(double val) {
        if(Math.abs(val) < 1){
            return 0.0;
        }
        return new BigDecimal(val).round(MathContext.DECIMAL32).setScale(SCALE).pow(2).round(MathContext.DECIMAL32).doubleValue();
    }

    private static double multiply(double valA, double valB){
        return valA * valB;
    }

    public static boolean isAligned(TriangleArea triangle) {
        return getArea(triangle) < AREA_NEAR_ZERO;
    }

}
