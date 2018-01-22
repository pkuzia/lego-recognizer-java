package pl.pkuzia.models;

import java.awt.*;

public class BoundingBox {
    private final ImagePixel image;
    private final Segment segment;

    public BoundingBox(ImagePixel image, Segment segment) {
        this.image = image;
        this.segment = segment;
    }

    public void draw() {
        int[] constraints = segment.boundingBox();
        int xMin = constraints[0], yMin = constraints[1],
            xMax = constraints[2], yMax = constraints[3];

        image.getPixels().forEach(px -> {
            if ((between(px.getCol(), xMin - 1, xMin + 1)
                    || between(px.getCol(), xMax - 1, xMax + 1))
                    && between(px.getRow(), yMin, yMax)) {
                px.colorPixel(GreenPixel.values());
            } else if ((between(px.getRow(), yMin - 1, yMin + 1)
                    || between(px.getRow(), yMax - 1, yMax + 1))
                    && between(px.getCol(), xMin, xMax)) {
                px.colorPixel(GreenPixel.values());
            }
        });
    }

    private boolean between(double value, double min, double max) {
        return min <= value && value <= max;
    }
}
