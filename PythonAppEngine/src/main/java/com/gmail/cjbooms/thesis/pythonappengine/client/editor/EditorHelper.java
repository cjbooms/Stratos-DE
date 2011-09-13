package com.gmail.cjbooms.thesis.pythonappengine.client.editor;

/**
 * Created by IntelliJ IDEA.
 * User: conor
 * Date: 13/09/11
 * Time: 23:50
 * To change this template use File | Settings | File Templates.
 */
public class EditorHelper {



    private static String fileContents;
    private static String filePath;
    private static String editorID;
    private static SourceCodeEditor sourceCodeEditor;


    /**
     * Private Constructor to enforce non-instanciation of static class
     */
    private EditorHelper() {
    }


    public static SourceCodeEditor getSourceCodeEditor() {
        return sourceCodeEditor;
    }

    public static void setSourceCodeEditor(SourceCodeEditor sourceCodeEditor) {
        EditorHelper.sourceCodeEditor = sourceCodeEditor;
    }

    public static String getFileContents() {
        fileContents = sourceCodeEditor.getFileContents();
        return fileContents;
    }

    public static void setFileContents(String fileContents) {
        EditorHelper.fileContents = fileContents;
    }

    public static String getFilePath() {
        filePath = sourceCodeEditor.getFilePath();
        return filePath;
    }

    public static void setFilePath(String filePath) {
        EditorHelper.filePath = filePath;

    }

    public static String getEditorID() {
        return editorID;
    }

    public static void setEditorID(String editorID) {
        EditorHelper.editorID = editorID;
    }
}
