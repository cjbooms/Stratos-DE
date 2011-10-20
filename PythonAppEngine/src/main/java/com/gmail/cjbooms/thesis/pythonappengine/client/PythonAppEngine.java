package com.gmail.cjbooms.thesis.pythonappengine.client;

import com.gmail.cjbooms.thesis.pythonappengine.client.editor.SourceCodeEditor;
import com.gmail.cjbooms.thesis.pythonappengine.client.filebrowser.FileSystemTreeWidget;
import com.gmail.cjbooms.thesis.pythonappengine.client.menus.MainMenuWidget;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.dom.client.Style.Unit;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class PythonAppEngine implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
        final SourceCodeEditor editor;

		// Create the main panel to hold everything
		DockLayoutPanel rootPanel = new DockLayoutPanel(Unit.EM);

		// Panel to house a logo if desired	(Not Necessary)	
		FlowPanel logoPanel = new FlowPanel();
		DOM.setElementAttribute(logoPanel.getElement(), "id", "logo_panel");
		rootPanel.addNorth(logoPanel, 6);
		
		// Panel to house the Main Menu	
		FlowPanel menuBarPanel = new FlowPanel();
		DOM.setElementAttribute(menuBarPanel.getElement(), "id", "main_menu_panel");
        menuBarPanel.add(new MainMenuWidget());
		rootPanel.addNorth(menuBarPanel, 2);

		// Panel to house a footer if desired
		HTML footer = new HTML();
		DOM.setElementAttribute(footer.getElement(), "id", "footer");
		rootPanel.addSouth(footer, 2);

		
		//Build the Main Work Area Panel and Add to Root
		SplitLayoutPanel mainWorkAreaPanel = new SplitLayoutPanel();
		rootPanel.add(mainWorkAreaPanel);


        // TODO Move this to a File Selection Handler
        // Creation of the sample SourceCodeEditor. In the constructor we say which syntax we want to edit.
		editor = new SourceCodeEditor("python");
	    // Seting an initial content for the editor.
		editor.setFileContents("Hello World");


		//Create a Tree Scroll For Project Structure and Add to Main Work Panel
		ScrollPanel treeScrollPanel = new ScrollPanel();
        treeScrollPanel.add(new FileSystemTreeWidget("Available Projects", editor, footer));
		mainWorkAreaPanel.addWest(treeScrollPanel, 200);
		


		// Add Scroll Panel for Text Editor
		ScrollPanel contentScrollPanel = new ScrollPanel();

        contentScrollPanel.setWidget(editor);
        mainWorkAreaPanel.add(contentScrollPanel);
        mainWorkAreaPanel.setHeight("100%");

		editor.setWidth("100%");
		editor.setHeight("100%");


	    RootLayoutPanel.get().add(rootPanel);


	}
}
