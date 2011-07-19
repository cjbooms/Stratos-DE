package com.gmail.cjbooms.thesis.pythonappengine.client;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * A TextArea prepared to edit source code in a variety of formats.
 * 
 * @author dunlord
 *
 */

public class SourceCodeEditor extends Composite
{
	private final String id;
	private final String syntax;

	private final TextArea editAreaContent;
	private final VerticalPanel main = new VerticalPanel();

	private boolean initialized = false;
	private String text = "";


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
		setText(id, text);
	}

	@Override
	protected void onUnload()
		{initialized = false; super.onUnload();}

	/** Gets the content shown in the editor     @return the content of the editor */
	/*GETTER*/ public String getText() {if(initialized)return getText(id); return text;}

	/** Sets the content shown in the editor     @param value the text to be shown in the editor */
	/*SETTER*/ public void setText(String value) {if(initialized)setText(id, value);else text = value;}

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
            toolbar: "new_document, save, load, |, charmap, |, search, go_to_line, |, undo, redo, |, select_font, |, change_smooth_selection, highlight, reset_highlight, |, help",
            save_callback: "save"
		});
	}-*/;

    //TODO Get Callback functionality to work - http://code.google.com/docreader/#p=google-web-toolkit-doc-1-5&s=google-web-toolkit-doc-1-5&t=DevGuideJavaFromJavaScript
    public static void save(String id, String content){
        Window.alert("The editor content is:\n\n" + content);
    }

    public static native void exportStaticSaveMethod()
    /*-{
       $wnd.save = @com.gmail.cjbooms.thesis.pythonappengine.client.SourceCodeEditor::save(Ljava/lang/String;Ljava/lang/String;)
    }-*/;


	/**
	 * Sets the text shown by the EditArea identified by the specified id.
	 * 
	 * @param id    the id of the EditArea
	 * @param value the new text the EditArea must show
	 */
	private static native void setText(String id, String value)
		/*-{$wnd.editAreaLoader.setValue(id, value)}-*/;


	/**
	 * Gets the text shown by the EditArea identified by the specified id.
	 * 
	 * @param id    the id of the EditArea
	 * 
	 * @return the text the EditArea is showing
	 */

	private static native String getText(String id)
		/*-{return $wnd.editAreaLoader.getValue(id);}-*/;
}
