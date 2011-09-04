package com.gmail.cjbooms.thesis.pythonappengine.client.filebrowser;


import java.io.Serializable;
/**
 * Stores all relevant information about a file
 * Generally just stores file name and detail,
 * Converted to store contents of certain file types as a String (eg Python files etc)
 *
 */


public class FileWrapper implements Serializable {

    private String name;
    private String path;
    private FileType kind;
    private String modifiedAt;
    private String contents;


    public FileWrapper(String path, String name, String modifiedAt) {
        this(path, name);
        this.modifiedAt=modifiedAt;
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

   public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    /**
     * Accessed on the server side where directory can be determined from File object
     */
    public void setIsDirectory() {
        this.kind = FileType.DIR;
    }

    private static String extractFileExtention(String file) {
        int dot = file.lastIndexOf('.');
        return file.substring(dot + 1).toLowerCase();
    }

    /**
     * Set some basic File Types
     *
     * @param ext
     * @return
     */
    private FileType getFileType(String ext) {
        if(ext.equals("doc") || ext.equals("pdf") || ext.equals("ppt") || ext.equals("html"))
            return FileType.DOC;
        if(ext.equals("avi") || ext.equals("wnv") || ext.equals("mpeg") || ext.equals("mov") || ext.equals("mp3"))
            return FileType.MM;
        if(ext.equals("gif") || ext.equals("jpg") || ext.equals("png") || ext.equals("tif") || ext.equals("psd"))
            return FileType.IMG;
        if(ext.equals("py"))
            return FileType.PYTHON;
        if(ext.equals("rb"))
            return FileType.RUBY;
        return FileType.OTHER;

    }
}
