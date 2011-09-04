package com.gmail.cjbooms.thesis.pythonappengine.server;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: conor
 * Date: 04/09/11
 * Time: 21:54
 * To change this template use File | Settings | File Templates.
 */
public class Utilities {

    /**
     * Private Constructor to enforce non-instanciation of static class
     */
    private Utilities() {
    }


    /**
     * Convert File at passed filepath to a String
     *
     * @param filepath The file path
     * @return The content of the file as a String
     * @throws java.io.IOException
     */
    public static String fileToString(String filepath) throws IOException {
        byte[] buffer = new byte[(int) new File(filepath).length()];
        BufferedInputStream f = null;
        try {
            f = new BufferedInputStream(new FileInputStream(filepath));
            f.read(buffer);
        } finally {
            if (f != null) try { f.close(); } catch (IOException ignored) { }
        }
        return new String(buffer);
    }
}
