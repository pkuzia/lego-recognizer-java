package pl.pkuzia.models;

import org.opencv.core.Mat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Przemysław Kuzia on 12.01.2018.
 */

public class Pixel {

    private static final int binarizationValue = 125;

    private Mat img;
    private int row;
    private int col;

    Pixel(Mat img, int row, int col) {
        this.img = img;
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public double[] value() {
        return img.get(row, col);
    }

    public void convertToGray() {
        double[] bgr = img.get(row, col);
        double grayValue = (bgr[0] + bgr[2] + bgr[2]) / 3;
        img.put(row, col, grayValue, grayValue, grayValue);
    }

    public void thresholdingPixel() {
        double[] values = img.get(row, col);
        if (values[0] > binarizationValue) {
            img.put(row, col, BlackPixel.values());
        } else {
            img.put(row, col, WhitePixel.values());
        }
    }

    public List<Pixel> neighbors() {
        return Stream.of(new Pixel(img, row + 1, col + 1),
                new Pixel(img, row + 1, col),
                new Pixel(img, row + 1, col - 1),
                new Pixel(img, row, col + 1),
                new Pixel(img, row, col - 1),
                new Pixel(img, row - 1, col + 1),
                new Pixel(img, row - 1, col),
                new Pixel(img, row - 1, col - 1))
                .filter(px -> px.col >= 0 && px.col < img.cols()
                        && px.row >= 0 && px.row < img.rows())
                .collect(Collectors.toList());
    }

    public boolean isBlack() {
        return Arrays.equals(img.get(row, col), BlackPixel.values());
    }

    public void colorPixel(double[] values) {
        img.put(row, col, values[0], values[1], values[2]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pixel pixel = (Pixel) o;

        if (row != pixel.row) return false;
        if (col != pixel.col) return false;
        return img != null ? img.equals(pixel.img) : pixel.img == null;
    }

    @Override
    public int hashCode() {
        int result = img != null ? img.hashCode() : 0;
        result = 31 * result + row;
        result = 31 * result + col;
        return result;
    }
}
