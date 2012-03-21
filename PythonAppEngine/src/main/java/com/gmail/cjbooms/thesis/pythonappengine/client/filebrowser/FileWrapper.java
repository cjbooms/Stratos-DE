/*
 * Copyright (c) 2012 Conor Gallagher
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

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
        if(ext.equals("doc") || ext.equals("pdf") || ext.equals("html") || ext.equals("ppt"))
            return FileType.DOC;
        if(ext.equals("xls")  || ext.equals("xlsx"))
            return FileType.SHEET;
        if(ext.equals("avi") || ext.equals("wnv") || ext.equals("mpeg") || ext.equals("mov") || ext.equals("mp3"))
            return FileType.MM;
        if(ext.equals("gif") || ext.equals("jpg") || ext.equals("png") || ext.equals("tif") || ext.equals("psd"))
            return FileType.IMG;
        if(ext.equals("py"))
            return FileType.PYTHON;
        if(ext.equals("ru") || ext.equals("rb") || ext.equals("fcgi"))
            return FileType.RUBY;
        return FileType.OTHER;

    }
}
