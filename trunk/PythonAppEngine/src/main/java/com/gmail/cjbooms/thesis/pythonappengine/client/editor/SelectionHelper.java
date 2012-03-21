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

package com.gmail.cjbooms.thesis.pythonappengine.client.editor;

import com.gmail.cjbooms.thesis.pythonappengine.shared.ConfigConstants;


/**
 * Created by IntelliJ IDEA.
 * User: conor
 * Date: 13/09/11
 * Time: 23:50
 * To change this template use File | Settings | File Templates.
 */
public class SelectionHelper {

    private static final String ROOT_LOCATION = ConfigConstants.PROJECT_ROOT;
    private static String fileContents;
    private static String filePath;
    private static String editorID;
    private static SourceCodeEditor sourceCodeEditor;

    private static String currentDirectory;
    private static String currentProjectDirectory;



    public static String getCurrentDirectory() {
        return currentDirectory;
    }

    public static void setCurrentDirectory(String currentDirectory) {
        SelectionHelper.currentDirectory = currentDirectory;
    }

    /**
     * Private Constructor to enforce non-instanciation of static class
     */
    private SelectionHelper() {
    }


    public static SourceCodeEditor getSourceCodeEditor() {
        return sourceCodeEditor;
    }

    public static void setSourceCodeEditor(SourceCodeEditor sourceCodeEditor) {
        SelectionHelper.sourceCodeEditor = sourceCodeEditor;
    }

    public static String getFileContents() {
        fileContents = sourceCodeEditor.getFileContents();
        return fileContents;
    }

    public static void setFileContents(String fileContents) {
        SelectionHelper.fileContents = fileContents;
    }

    public static String getFilePath() {
        filePath = sourceCodeEditor.getFilePath();
        return filePath;
    }

    public static void setFilePath(String filePath) {
        SelectionHelper.filePath = filePath;
        setCurrentProjectDirectory(filePath);
    }


    /**
     * Set the Project Folder Path. Update only if the root folder selection changes
     * @param filePath
     */
    public static void setCurrentProjectDirectory(String filePath) {
        int rootLength = ROOT_LOCATION.length() + 1;
        int index = filePath.indexOf("/", rootLength);
        if(index > 0){
            SelectionHelper.currentProjectDirectory = filePath.substring(0,index);
        }else{
            SelectionHelper.currentProjectDirectory = filePath;
        }

    }

    public static String getCurrentProjectDirectory() {
        return currentProjectDirectory;
    }


    public static String getEditorID() {
        return editorID;
    }

    public static void setEditorID(String editorID) {
        SelectionHelper.editorID = editorID;
    }

    private static int nthOccurrence(String str, char c, int n) {
        int pos = str.indexOf(c, 0);
        while (n-- > 0 && pos != -1)
            pos = str.indexOf(c, pos+1);
        return pos;
    }

}
