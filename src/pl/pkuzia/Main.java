package pl.pkuzia;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import pl.pkuzia.services.FileService;
import pl.pkuzia.services.RecognizerService;
import pl.pkuzia.services.UserInterfaceService;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        UserInterfaceService userInterfaceService = new UserInterfaceService();
        RecognizerService.RecognitionType type = userInterfaceService.menu();
        FileService fileService = new FileService();
        RecognizerService recognizerService = new RecognizerService();

        List<File> images = fileService.loadFile();
        List<Mat> resultImages = images.stream().map(file -> { return recognizerService.recognizeLogotype(file, type);})
                .collect(Collectors.toList());

        fileService.saveFile(resultImages);
    }
}
