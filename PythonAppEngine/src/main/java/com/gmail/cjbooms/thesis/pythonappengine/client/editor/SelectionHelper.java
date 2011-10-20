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
    private static String currentProjectPath = "";

    public static String getCurrentProjectPath() {
        return currentProjectPath;
    }

    private static String fileContents;
    private static String filePath;
    private static String editorID;
    private static SourceCodeEditor sourceCodeEditor;


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
        setCurrentProjectPath(filePath);
    }

    /**
     * Set the Project Folder Path. Update only if the root folder selection changes
     * @param filePath
     */
    public static void setCurrentProjectPath(String filePath) {
        int rootLength = ROOT_LOCATION.length() + 1;
        int index = filePath.indexOf("/", rootLength);
        if(index > 0){
            SelectionHelper.currentProjectPath = filePath.substring(0,index);
        }else{
            SelectionHelper.currentProjectPath = filePath;
        }

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
