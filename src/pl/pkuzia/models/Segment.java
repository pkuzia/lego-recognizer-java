package pl.pkuzia.models;

import java.util.Set;

/**
 * Created by Przemysław Kuzia on 12.01.2018.
 */

public class Segment {

    private Set<Pixel> pixels;

    public Segment(Set<Pixel> pixels) {
        this.pixels = pixels;
    }

    public Set<Pixel> getPixels() {
        return pixels;
    }

    public long size() {
        return pixels.size();
    }

    boolean contains(Pixel pixel) {
        return pixels.contains(pixel);
    }

    int[] boundingBox() {
        double x1 = Double.POSITIVE_INFINITY, y1 = Double.POSITIVE_INFINITY,
                x2 = Double.NEGATIVE_INFINITY, y2 = Double.NEGATIVE_INFINITY;
        for (Pixel pixel : pixels) {
            x1 = Math.min(x1, pixel.getCol());
            y1 = Math.min(y1, pixel.getRow());
            x2 = Math.max(x2, pixel.getCol());
            y2 = Math.max(y2, pixel.getRow());
        }

        return new int[]{(int) x1, (int) y1, (int) x2,(int)  y2};
    }
}
