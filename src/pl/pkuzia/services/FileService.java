package pl.pkuzia.services;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static org.opencv.core.CvType.CV_8UC3;

/**
 * Created by Przemysław Kuzia on 07.01.2018.
 */
public class FileService {

    public File loadFile(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Path to image not specified.");
            System.exit(1);
        }

        File image = new File(args[0]);
        if (!image.exists()) {
            System.out.println("File doesn't exist.");
            System.exit(1);
        }
        return image;
    }

    public void saveFile(Mat image, String path) {
        File out = Paths.get(path).getParent().resolve("result.png").toFile();
        Imgcodecs.imwrite(out.getAbsolutePath(), image);
    }
}
