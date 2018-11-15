package com.joyBox.shefaa.converters;

import android.util.Base64;

import java.io.File;
import java.io.FileInputStream;

public class FileConverter {

    public static String file2Base24(File filePath) {
        String encodedBase64 = "";
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(filePath);
            byte[] bytes = new byte[(int) filePath.length()];
            fileInputStreamReader.read(bytes);
            encodedBase64 = new String(Base64.encode(bytes, Base64.DEFAULT));
        } catch (Exception ex) {

        }
        return encodedBase64;
    }

}
