package pl.pkuzia.services;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Przemysław Kuzia on 07.01.2018.
 */
public class FileService {

    public String[] fileNames() {
        return new String[]{"/Users/Przemo/Praca/Projekty/lego-recognizer-java/1.png",
                "/Users/Przemo/Praca/Projekty/lego-recognizer-java/2.jpeg",
                "/Users/Przemo/Praca/Projekty/lego-recognizer-java/3.jpeg",
                "/Users/Przemo/Praca/Projekty/lego-recognizer-java/4.jpeg"};
    }

    public List<File> loadFile() throws IOException {

        List<File> files = new ArrayList<>();
        for (String fileName: fileNames()) {
            File file = new File(fileName);
            if (!file.exists()) {
                System.out.println("File doesn't exist.");
                System.exit(1);
            }
            files.add(file);
        }
        return files;
    }

    public void saveFile(List<Mat> images) {
        for (int i = 0; i < images.size(); i++) {
            File out = Paths.get("/Users/Przemo/Praca/Projekty/lego-recognizer-java/").resolve("result" + i + ".png").toFile();
            Imgcodecs.imwrite(out.getAbsolutePath(), images.get(i));
        }
    }
}
