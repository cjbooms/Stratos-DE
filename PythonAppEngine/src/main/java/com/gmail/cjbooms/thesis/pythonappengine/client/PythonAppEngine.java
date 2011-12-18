package com.gmail.cjbooms.thesis.pythonappengine.client;

import com.gmail.cjbooms.thesis.pythonappengine.client.editor.SourceCodeEditor;
import com.gmail.cjbooms.thesis.pythonappengine.client.filebrowser.FileSystemTreeWidget;
import com.gmail.cjbooms.thesis.pythonappengine.client.menus.MainMenuWidget;
import com.gmail.cjbooms.thesis.pythonappengine.client.menus.Toolbar;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.dom.client.Style.Unit;

import java.awt.*;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class PythonAppEngine implements EntryPoint {

    final SourceCodeEditor editor = new SourceCodeEditor("python");

    // Create the main panel to hold everything
	DockLayoutPanel rootPanel = new DockLayoutPanel(Unit.EM);

    //Create a Tree Scroll For Project Structure and Add to Main Work Panel
	ScrollPanel treeScrollPanel = new ScrollPanel();

    private static final String TITLE = "StratosDE - The Cloud Based Development Environment";

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {


		

		VerticalPanel menuAndToolBarPanel = new VerticalPanel();
        menuAndToolBarPanel.setWidth("100%");

		// Panel to house the Main Menu
		FlowPanel menuBarPanel = new FlowPanel();
        DOM.setElementAttribute(menuBarPanel.getElement(), "id", "main_menu_panel");
        menuBarPanel.add(new MainMenuWidget());
        menuBarPanel.setHeight("1");

        HTMLPanel titleBar = new HTMLPanel(TITLE);
        DOM.setElementAttribute(titleBar.getElement(), "id", "pa-header");


        menuAndToolBarPanel.add(titleBar);
		menuAndToolBarPanel.add(menuBarPanel);
        menuAndToolBarPanel.add(new Toolbar());


        rootPanel.addNorth(menuAndToolBarPanel, 7);


		// Panel to house a footer containing File Context
		HTML footer = new HTML();
		DOM.setElementAttribute(footer.getElement(), "id", "pa-footer");
		rootPanel.addSouth(footer, 2);

		
		//Build the Main Work Area Panel and Add to Root
		SplitLayoutPanel mainWorkAreaPanel = new SplitLayoutPanel();
		rootPanel.add(mainWorkAreaPanel);



        FileSystemTreeWidget fileSystemTreeWidget = new FileSystemTreeWidget("Available Projects", editor, footer);
        treeScrollPanel.add(fileSystemTreeWidget);
		mainWorkAreaPanel.addWest(treeScrollPanel, 200);
		

        //contentScrollPanel.setHeight("100%");

		editor.setWidth("100%");
        editor.setHeight("100%");

        //contentScrollPanel.setWidget(editor);
        mainWorkAreaPanel.add(editor);
        mainWorkAreaPanel.setHeight("100%");



	    RootLayoutPanel.get().add(rootPanel);


}
}
