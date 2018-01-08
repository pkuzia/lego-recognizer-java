package pl.pkuzia;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import pl.pkuzia.services.FileService;
import pl.pkuzia.services.RecognizerService;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        FileService fileService = new FileService();
        RecognizerService recognizerService = new RecognizerService();

        File image = fileService.loadFile(args);
        Mat resultImage = recognizerService.recognizeLogotype(image);

        fileService.saveFile(resultImage, image.getAbsolutePath());
    }
}
