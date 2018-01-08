package pl.pkuzia.services;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;

/**
 * Created by Przemysław Kuzia on 07.01.2018.
 */
public class RecognizerService {

    public Mat recognizeLogotype(File image) {
        Mat img = Imgcodecs.imread(image.getAbsolutePath());
        convertToGray(img);
        binarizationImage(img);
        return img;
    }

    private void binarizationImage(Mat img) {

    }

    private void convertToGray(Mat img) {
        for (int row = 0; row < img.rows(); row++) {
            for (int col = 0; col < img.cols(); col++) {
                double[] bgr = img.get(row, col);
                double grayPix = (bgr[0] + bgr[2] + bgr[2]) / 3;
                img.put(row, col, grayPix, grayPix, grayPix);
            }
        }
    }
}
