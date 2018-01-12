package pl.pkuzia.services;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import pl.pkuzia.models.ImagePixel;
import pl.pkuzia.models.Pixel;
import pl.pkuzia.models.Segment;

import java.io.File;
import java.util.*;

/**
 * Created by Przemysław Kuzia on 07.01.2018.
 */
public class RecognizerService {

    public Mat recognizeLogotype(File image) {
        Mat img = Imgcodecs.imread(image.getAbsolutePath());
        ImagePixel imagePixel = new ImagePixel(img);
        convertToGray(imagePixel);
        thresholdingImage(imagePixel);
        List<Segment> segments = segmentationImage(imagePixel);
//        colorizeSegments(segments);
        return img;
    }

    private List<Segment> segmentationImage(ImagePixel img) {
        ArrayList<Segment> list = new ArrayList<>();
        img.getPixels().forEach(pixel -> {
            if (pixel.isBlack() && img.notInSegment(list, pixel)) {
                Segment segment = new Segment(blackFlood(pixel));
                if (segment.size() > 200) {
                    list.add(segment);
                }
            }
        });
        return list;
    }

    private void thresholdingImage(ImagePixel img) {
        img.getPixels().forEach(Pixel::thresholdingPixel);
    }

    private void convertToGray(ImagePixel img) {
        img.getPixels().forEach(Pixel::convertToGray);
    }

    private void colorizeSegments(List<Segment> segments) {
        Random randomGenerator = new Random();


        segments.forEach(segment -> {
            double[] segmentColor = { randomGenerator.nextInt(256), randomGenerator.nextInt(256),
                    randomGenerator.nextInt(256) };
            segment.getPixels().forEach(pix -> pix.colorPixel(segmentColor));
        });
    }

    private Set<Pixel> blackFlood(Pixel start) {

        HashSet<Pixel> segment = new HashSet<>();
        HashSet<Pixel> enqueued = new HashSet<>();
        Queue<Pixel> queue = new LinkedList<>();
        queue.add(start);

        while(!queue.isEmpty()) {
            Pixel pixel = queue.remove();
            segment.add(pixel);
            pixel.neighbors()
                    .stream()
                    .filter(Pixel::isBlack)
                    .filter(pix -> !enqueued.contains(pix))
                    .forEach(pix -> {
                        queue.add(pix);
                        enqueued.add(pix);
                    });
        }
        return segment;
    }
}
