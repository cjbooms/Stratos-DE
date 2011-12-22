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

    private boolean displayHidden = false;

    static{
        dateFormater = new SimpleDateFormat("MMM d, yy");
    }

    private static String dateFormat(long dateLong){
        return dateFormater.format(new Date(dateLong));
    }

    @Override
    public FileWrapper[] getDirectoryContents(FileWrapper file) throws IOException {
        File fsFile = new File(file.getPath());
        if (fsFile.isDirectory()) {
            return this.buildFilesList(fsFile.listFiles());
        }
        return null;
    }

    @Override
    public FileWrapper[] getDirectoryContents(String file) throws IOException {
        return this.getDirectoryContents(new FileWrapper(file));
    }


    @Override
    public String getFileContents(String file) throws IOException {
        return Utilities.fileToString(file);
    }


    /**
     * Create a list of all File details using File Wrapper
     * Determine if File is to be added based on displayHidden
     * @param files List of java file objects
     * @return wrappedFiles The list of wrapped files
     */
    private FileWrapper[] buildFilesList(File[] files) {

        FileWrapper[] wrappedFiles = new FileWrapper[files.length];
        int i = 0;
        for (File currentFile :files) {
            String path = currentFile.getAbsolutePath();
            String fileName = currentFile.getName();
            String modified = dateFormat( currentFile.lastModified());
            if(!(fileName.startsWith(".") && !displayHidden)) {
                wrappedFiles[i] = new FileWrapper(path, fileName, modified);
                if (files[i].isDirectory()) {
                    wrappedFiles[i].setIsDirectory();
                }
                i++;
            }
        }
        return wrappedFiles;
    }


    @Override
    public void deleteFile(String absoluteName) {
        System.out.println("deleting : "+absoluteName);
        Boolean result = new File(absoluteName).delete();
        if(!result){
            throw new RuntimeException("Error Deleting File");
        }
    }


    @Override
    public void saveFile(String absoluteName, String fileContents)  throws Exception {
        FileUtils.fileWrite(absoluteName, fileContents);
    }

    /**
     * Should hidden files be displayed?
     * @return
     */
    public boolean isDisplayHidden() {
        return displayHidden;
    }

    /**
     * Set whether to display hidden files and folders.
     * @param displayHidden
     */
    public void setDisplayHidden(boolean displayHidden) {
        this.displayHidden = displayHidden;
    }
}
