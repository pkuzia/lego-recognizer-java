package pl.pkuzia.services;

import java.util.Scanner;

/**
 * Created by Przemysław Kuzia on 22.01.2018.
 */
public class UserInterfaceService {

    public RecognizerService.RecognitionType menu() {
        System.out.println("-----+ Lego logotype recognizer +-----\n");
        System.out.println("Select option: \n");
        System.out.println("1. Full recognition");
        System.out.println("2. Image segmentation\n");

        while(true) {
            Scanner s = new Scanner(System.in);
            int optionNumber = s.nextInt();
            switch (optionNumber) {
                case 1:
                    return RecognizerService.RecognitionType.fullRecognition;
                case 2:
                    return RecognizerService.RecognitionType.segmentation;
                default:
                    System.out.println("Error, select option again.");
            }
        }
    }
}
