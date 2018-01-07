package pl.pkuzia;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Path to image not specified.");
            System.exit(1);
        }

        File image = new File(args[0]);
        if (!image.exists()) {
            System.out.println("File doesn't exist.");
            System.exit(1);
        }
    }
}
