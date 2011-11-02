package com.gmail.cjbooms.thesis.pythonappengine.client.editor;

import com.gmail.cjbooms.thesis.pythonappengine.client.filebrowser.FileSystemService;
import com.gmail.cjbooms.thesis.pythonappengine.client.filebrowser.FileSystemServiceAsync;
import com.gmail.cjbooms.thesis.pythonappengine.client.menus.OperationResultDialog;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * A TextArea prepared to edit source code in a variety of formats.
 * 
 *
 */

public class SourceCodeEditor extends Composite
{
	private final String id;
	private final String syntax;

	private final TextArea editAreaContent;
	private final VerticalPanel main = new VerticalPanel();
    private static FileSystemServiceAsync fileSystemSvc = GWT.create(FileSystemService.class);

	private boolean initialized = false;

    private String fileContents = "";

    private String filePath = "";


	/**
	 * Constructs a SourceCodeEditor to edit code written in the specified syntax
	 * 
	 * @param syntax the syntax the editor highlights
	 */

	public SourceCodeEditor(String syntax)
	{
		this.syntax          = syntax;
		this.id              = DOM.createUniqueId();
		this.editAreaContent = new TextArea();

		editAreaContent.getElement().setId(id);

		main.add(editAreaContent);
		initWidget(main);

		main.setCellWidth  (editAreaContent, "100%");
		main.setCellHeight (editAreaContent, "100%");

		editAreaContent.setHeight ("100%");
		editAreaContent.setWidth  ("100%");
        SelectionHelper.setSourceCodeEditor(this);
        SelectionHelper.setEditorID(id);
	}


	/**
	 * This method is called immediately after a widget becomes attached to the
	 * browser's document.
	 */

	@Override
	protected void onLoad()
	{
		super.onLoad();
		convertToEditArea(id, syntax);
		initialized = true;
		setText(id, fileContents);
        exportStaticSaveMethod();
	}

	@Override
	protected void onUnload()
		{initialized = false; super.onUnload();}


	/** Gets the content shown in the editor
     *
     * @return the content of the editor
     */
    public String getFileContents() {
        if(initialized)
            return getText(id);
        return fileContents;
    }

	/** Store content and display in editor if initialised
     *
     *  @param value the fileContents to be shown in the editor
     */
    public void setFileContents(String value) {
        fileContents = value;
        if(initialized)
            setText(id, value);

    }

	/** Sets the editor width.    @param width the object's new width, in CSS units (e.g. "10px", "1em") */
	/*SETTER*/ @Override public void setWidth(String width) {super.setWidth(width); main.setWidth(width);}

	/** Sets the editor height.   @param height the object's new height, in CSS units (e.g. "10px", "1em") */
	/*SETTER*/ @Override public void setHeight(String height) {super.setHeight(height); main.setHeight(height);}


	/* Encapsulated native code */

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
            toolbar: "new_document, save, search, go_to_line, |, undo, redo, |, select_font, |, change_smooth_selection, highlight, reset_highlight, |, help",
            save_callback: "save"
		});
	}-*/;


    public static void save()  {
        String fileContents = SelectionHelper.getFileContents();
        String filePath = SelectionHelper.getFilePath();
        AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {

            @Override
            public void onFailure(Throwable thrwbl) {
                new OperationResultDialog("Error Saving. Cached Locally");
            }
            @Override
            public void onSuccess(Boolean result) {
                 new OperationResultDialog("Saved to File System" );
            }
        };
//        try {
            fileSystemSvc.saveFile(filePath, fileContents, callback);

/*        } catch (Exception e) {
            GWT.log("Error Saving File" + e.getStackTrace());
            Window.alert("Error Saving. Cached Locally\n" );
        }*/
    }


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
		/*-{$wnd.editAreaLoader.setValue(id, value)}-*/;


	/**
	 * Gets the fileContents shown by the EditArea identified by the specified id.
	 * 
	 * @param id    the id of the EditArea
	 * 
	 * @return the fileContents the EditArea is showing
	 */
	private static native String getText(String id)
		/*-{return $wnd.editAreaLoader.getValue(id);}-*/;


    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}