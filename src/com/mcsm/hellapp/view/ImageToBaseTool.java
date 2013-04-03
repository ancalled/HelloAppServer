package com.mcsm.hellapp.view;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageToBaseTool {


    public static String convert(String filename) throws IOException {

        Path path = Paths.get(filename);
        byte[] data = Files.readAllBytes(path);

        return Base64.encode(data);
    }



    public static void main(String[] args) throws IOException {
        String fname = "/Users/ancalled/Documents/dev/HelloAppServer/web/resources/icons/4.png";
        System.out.println(convert(fname));

    }
}
