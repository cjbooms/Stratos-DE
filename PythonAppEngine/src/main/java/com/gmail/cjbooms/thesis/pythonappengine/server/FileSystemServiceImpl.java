/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.cjbooms.thesis.pythonappengine.server;

import com.gmail.cjbooms.thesis.pythonappengine.client.filebrowser.FileSystemService;
import com.gmail.cjbooms.thesis.pythonappengine.client.filebrowser.FileWrapper;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;


/**
 *
 */
public class FileSystemServiceImpl extends RemoteServiceServlet implements FileSystemService {

    private static final long serialVersionUID = 1L;
    private static SimpleDateFormat dateFormater;

    static{
        dateFormater = new SimpleDateFormat("MMM d, yy");
    }

    private static String dateFormat(long dateLong){
        return dateFormater.format(new Date(dateLong));
    }

    @Override
    public FileWrapper[] getDirectoryContents(FileWrapper file) {
        File fsFile = new File(file.getPath());
        if (fsFile.isDirectory()) {
            return this.buildFilesList(fsFile.listFiles());
        }
        return null;
    }

    @Override
    public FileWrapper[] getDirectoryContents(String file) {
        return this.getDirectoryContents(new FileWrapper(file));
    }


    @Override
    public String getFileContents(String file) {
        try {
            return Utilities.fileToString(file);
        } catch (IOException e) {
            //e.printStackTrace();  //TODO
        }
        return null;
    }


    private FileWrapper[] buildFilesList(File[] files) {

        FileWrapper[] result = new FileWrapper[files.length];
        for (int i = 0; i < files.length; i++) {
            result[i] = new FileWrapper(files[i].getAbsolutePath(), files[i].getName(), dateFormat( files[i].lastModified()));
            if (files[i].isDirectory()) {
                result[i].setIsDirectory();
            }
        }
        return result;
    }

    @Override
    public void deleteFile(String absoluteName) {
        System.out.println("deleting : "+absoluteName);
        new File(absoluteName).delete();
    }


    @Override
    public void saveFile(String absoluteName, String fileContents)  throws Exception {
        FileUtils.fileWrite(absoluteName, fileContents);
    }

}
