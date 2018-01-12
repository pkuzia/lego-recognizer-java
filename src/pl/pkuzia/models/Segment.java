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
}
