package pl.pkuzia.models;

import org.opencv.core.Mat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Przemysław Kuzia on 12.01.2018.
 */

public class ImagePixel {

    public Mat img;
    public List<Pixel> pixels;

    public ImagePixel(Mat img) {
        pixels = new ArrayList<>();
        this.img = img;
        for (int row = 0; row < img.rows(); row++) {
            for (int col = 0; col < img.cols(); col++) {
                pixels.add(new Pixel(img, row, col));
            }
        }
    }

    public boolean notInSegment(List<Segment> segments, Pixel pixel) {
        return segments.stream().noneMatch(segment -> segment.contains(pixel));
    }
}
