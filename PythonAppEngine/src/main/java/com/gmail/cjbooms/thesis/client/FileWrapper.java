package com.gmail.cjbooms.thesis.client;

import java.io.Serializable;
/**
 *
 * @author firas
 */


public class FileWrapper implements Serializable {

    private String name;
    private String path;
    private FileType kind;
    private String modifiedAt;


    public FileWrapper(String path, String name, String modifiedAt) {
        this.name = name;
        this.path = path;
        this.modifiedAt=modifiedAt;
        this.kind = this.getFileType(extractFileExtention(name));

    }

    public FileWrapper(String path, String name) {
        this.name = name;
        this.path = path;
        this.kind = this.getFileType(extractFileExtention(name));
    }

    public FileWrapper(String path) {
        this.name = "";
        this.path = path;
        this.kind = FileType.DIR;
    }

    public FileWrapper() {
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getModified() {
        return this.modifiedAt;
    }

    public FileType getKind() {
        return kind;
    }

    public void setIsDirectory() {
        this.kind = FileType.DIR;
    }

    private static String extractFileExtention(String file) {
        int dot = file.lastIndexOf('.');
        return file.substring(dot + 1).toLowerCase();
    }

    private FileType getFileType(String ext) {
        if(ext.equals("doc") || ext.equals("pdf") || ext.equals("ppt") || ext.equals("html"))
            return FileType.DOC;
        if(ext.equals("avi") || ext.equals("wnv") || ext.equals("mpeg") || ext.equals("mov") || ext.equals("mp3"))
            return FileType.MM;
        if(ext.equals("gif") || ext.equals("jpg") || ext.equals("png") || ext.equals("tif") || ext.equals("psd"))
            return FileType.IMG;
        return FileType.OTHER;

    }
}
