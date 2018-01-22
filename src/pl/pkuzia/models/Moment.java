package pl.pkuzia.models;

import java.util.Set;

import static java.lang.Math.pow;

public class Moment {
    private Set<Pixel> image;

    public Moment(Set<Pixel> image) {
        this.image = image;
    }

    private double m(int p, int q) {
        double val = image.stream()
                .reduce(0d, (acc, px) -> px.isBlack() ? acc + pow(px.getCol(), p) * pow(px.getRow(), q) : acc,
                        (v1, v2) -> v1 + v2);
        return val;
    }

    private double M(int p, int q) {
        final double xCenter = xc();
        final double yCenter = yc();
        return image.stream()
                .reduce(0d, (acc, px) -> px.isBlack() ?
                                acc + pow(px.getCol() - xCenter, p) * pow(px.getRow() - yCenter, q) : acc,
                        (v1, v2) -> v1 + v2);
    }

    private double xc() {
        return m(1, 0) / m(0, 0);
    }

    private double yc() {
        return m(0, 1) / m(0, 0);
    }


    private double M1() {
        return (M(2, 0) + M(0, 2)) / pow(m(0, 0), 2);
    }

    private double M2() {
        return (pow(M(2, 0) - M(0, 2), 2) + 4 * pow(M(1, 1), 2)) / pow(m(0, 0), 4);
    }

    private double M3() {
        return (pow(M(3, 0) - 3 * M(1, 2), 2) + pow(M(2, 1) - M(0, 3), 2)) / pow(m(0, 0), 5);
    }

    private double M4() {
        return (pow(M(3, 0) + M(1, 2), 2) + pow(M(2, 1) + M(0, 3), 2)) / pow(m(0, 0), 5);
    }

    private double M6() {
        return ((M(2, 0) - M(0, 2)) * (pow(M(3, 0) + M(1, 2), 2) - pow(M(2, 1) + M(0, 3), 2))
                + 4 * M(1, 1) * (M(3, 0) + M(1, 2)) * (M(2, 1) + M(0, 3)))
                / pow(m(0, 0), 7);
    }

    private double M7() {
        return (M(2, 0) * M(0, 2) - pow(M(1, 1), 2)) / pow(m(0, 0), 4);
    }

    private double M8() {
        return (M(3, 0) * M(1, 2) + M(2, 1) * M(0, 3) - pow(M(1, 2), 2) - pow(M(2, 1), 2)) / pow(m(0, 0), 5);
    }

    double area() {
        return M(0, 0);
    }

    private boolean between(double value, double min, double max) {
        return min <= value && value <= max;
    }

    private int legoScore() {
        int score = 0;

        if (between(M1(),0.2, 0.4)) {
            score++;
        }

        if (between(M2(),7.85e-4, 14.85e-4)) {
            score++;
        }

        if (between(M3(),2e-7, 5.7e-5)) {
            score++;
        }

        if (between(M4(),1e-7, 2.7e-5)) {
            score++;
        }

        if (between(M6(),1e-9, 4e-7)) {
            score++;
        }

        if (between(M6(),-4e-7,1e-9 )) {
            score++;
        }

        if (between(M7(),2e-3, 4.2e-2)) {
            score++;
        }

        if (between(M8(),4e-8, 2.8e-6)) {
            score++;
        }

        if (between(M8(),-2.8e-6, 4e-8)) {
            score++;
        }
        return score;
    }

    public boolean legoMark() {
        return legoScore() >= 5;
    }
}
