package com.projet.tsakitsaky.utils;

import java.io.FileInputStream;

public class Util {
    
    public Util()
    {

    }

    public byte[] convertImageToByte(String pathImage)
    {
        byte[] imageByte = null;
        try {
            FileInputStream fis = new FileInputStream(pathImage);

            imageByte = new byte[fis.available()];
            fis.read(imageByte);

            fis.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return imageByte;
        
    }
}
