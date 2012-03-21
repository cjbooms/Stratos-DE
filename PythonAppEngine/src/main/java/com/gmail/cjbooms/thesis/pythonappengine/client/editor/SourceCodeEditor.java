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

import com.gmail.cjbooms.thesis.pythonappengine.client.filebrowser.FileSystemService;
import com.gmail.cjbooms.thesis.pythonappengine.client.filebrowser.FileSystemServiceAsync;
import com.gmail.cjbooms.thesis.pythonappengine.client.menus.OperationResultDialog;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * The
 */
public class SourceCodeEditor extends Composite {
    private final String id;
    private final String syntax;

    private final TextArea editAreaContent;
    private final VerticalPanel editAreaHolderPanel = new VerticalPanel();
    private static FileSystemServiceAsync fileSystemSvc = GWT.create(FileSystemService.class);

    private boolean initialized = false;

    private String fileContents = "";

    private String filePath = "";


    /**
     * Constructs a SourceCodeEditor to edit code written in the specified syntax
     *
     * @param syntax the syntax the editor highlights
     */

    public SourceCodeEditor(String syntax) {
        this.syntax = syntax;
        this.id = DOM.createUniqueId();
        this.editAreaContent = new TextArea();

        editAreaContent.getElement().setId(id);
        editAreaContent.getElement().getStyle().setOverflow(Style.Overflow.HIDDEN);
        editAreaContent.getElement().getStyle().setProperty("resize", "none");
        editAreaContent.setVisibleLines(30);

        editAreaHolderPanel.add(editAreaContent);
        initWidget(editAreaHolderPanel);

        editAreaHolderPanel.setCellWidth(editAreaContent, "100%");
        editAreaHolderPanel.setCellHeight(editAreaContent, "100%");

        SelectionHelper.setSourceCodeEditor(this);
        SelectionHelper.setEditorID(id);
    }


    /**
     * This method is called immediately after a widget becomes attached to the
     * browser's document.
     */
    @Override
    protected void onLoad() {
        super.onLoad();
        convertToEditArea(id, syntax);
        initialized = true;
        setText(id, fileContents);
        exportStaticSaveMethod();

    }

    @Override
    protected void onUnload() {
        initialized = false;
        super.onUnload();
    }


    /**
     * Gets the content shown in the editor
     *
     * @return the content of the editor
     */
    public String getFileContents() {
        if (initialized) {
            return getText(id);
        }
        return fileContents;
    }

    /**
     * Store content and display in editor if initialised
     *
     * @param value the fileContents to be shown in the editor
     */
    public void setFileContents(String value) {
        fileContents = value;
        if (initialized) {
            setText(id, value);
        }
    }

    /**
     * Sets the editor width.
     *
     * @param width the object's new width, in CSS units (e.g. "10px", "1em")
     */
    @Override
    public void setWidth(String width) {
        super.setWidth(width);
        editAreaHolderPanel.setWidth(width);
        editAreaContent.setWidth(width);
    }

    /**
     * Sets the editor height.
     *
     * @param height the object's new height, in CSS units (e.g. "10px", "1em")
     */
    @Override
    public void setHeight(String height) {
        super.setHeight(height);
        editAreaHolderPanel.setHeight(height);
        editAreaContent.setHeight("100%");
    }


    public static void save() {
        String fileContents = SelectionHelper.getFileContents();
        String filePath = SelectionHelper.getFilePath();
        AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {

            @Override
            public void onFailure(Throwable thrwbl) {
                new OperationResultDialog("Error Saving. Cached Locally");
            }

            @Override
            public void onSuccess(Boolean result) {
                new OperationResultDialog("Saved to File System");
            }
        };
        fileSystemSvc.saveFile(filePath, fileContents, callback);
    }


    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /* Encapsulated Java Script native code */

    /**
     * Converts the TextArea identified by the specified id into an EditArea
     * widget with the highlighting activated for the specified syntax.
     *
     * @param id     the id of the TextArea to convert into an EditArea
     * @param syntax the syntax the widget must highlight
     */

    private native static void convertToEditArea(String id, String syntax)
    /*-{
        $wnd.editAreaLoader.init({
            id              : id,    // id of the textarea to transform
            start_highlight : true,  // if start with highlight
            allow_toggle    : false,
            word_wrap       : true,
            language        : "en",
            syntax          : syntax,
            toolbar: "save, search, go_to_line, |, undo, redo, |, select_font, |, change_smooth_selection, highlight, reset_highlight, |, help",
            save_callback: "save"
        });
    }-*/;


    /**
     * Export a Javascript variable that directs to the Java save method.
     * This allows the variable to be passed as a callback to the editor
     * for when the save button is pressed.
     */
    public static native void exportStaticSaveMethod()
        /*-{
            $wnd.save = @com.gmail.cjbooms.thesis.pythonappengine.client.editor.SourceCodeEditor::save()
        }-*/;


    /**
     * Sets the fileContents shown by the EditArea identified by the specified id.
     *
     * @param id    the id of the EditArea
     * @param value the new fileContents the EditArea must show
     */
    private static native void setText(String id, String value)
    /*-{
        $wnd.editAreaLoader.setValue(id, value)
    }-*/;


    /**
     * Gets the fileContents shown by the EditArea identified by the specified id.
     *
     * @param id the id of the EditArea
     * @return the fileContents the EditArea is showing
     */
    private static native String getText(String id)
    /*-{
        return $wnd.editAreaLoader.getValue(id);
    }-*/;



}