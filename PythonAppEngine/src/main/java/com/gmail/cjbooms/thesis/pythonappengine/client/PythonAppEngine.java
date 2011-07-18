package com.gmail.cjbooms.thesis.pythonappengine.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.dom.client.Style.Unit;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class PythonAppEngine implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
		+ "attempting to contact the server. Please check your network "
		+ "connection and try again.";

	/**


	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Button sendButton = new Button("Send");
		final TextBox nameField = new TextBox();
		nameField.setText("CONOR User");
		final Label errorLabel = new Label();

		// Create the main panel to hold everything
		DockLayoutPanel rootPanel = new DockLayoutPanel(Unit.EM);

		// Panel to house a logo if desired	(Not Necessary)	
		FlowPanel logoPanel = new FlowPanel();
		DOM.setElementAttribute(logoPanel.getElement(), "id", "logo_panel");
		rootPanel.addNorth(logoPanel, 6);
		
		// Panel to house the Main Menu	
		FlowPanel menuBarPanel = new FlowPanel();
		DOM.setElementAttribute(menuBarPanel.getElement(), "id", "main_menu_panel");
		rootPanel.addNorth(menuBarPanel, 2);

		// Panel to house a footer if desired
		HTMLPanel footer = new HTMLPanel("New HTML");
		DOM.setElementAttribute(footer.getElement(), "id", "footer_panel");
		rootPanel.addSouth(footer, 2);

		
		
		//Build the Main Work Area Panel and Add to Root
		SplitLayoutPanel mainWorkAreaPanel = new SplitLayoutPanel();
		rootPanel.add(mainWorkAreaPanel);
		
		//Create a Tree Scroll For Project Structure and Add to Main Work Panel
		ScrollPanel treeScrollPanel = new ScrollPanel();
		mainWorkAreaPanel.addWest(treeScrollPanel, 155.0);
		
		
		//Add Text Editor Panel
		DockLayoutPanel textEditorPanel = new DockLayoutPanel(Unit.EM);
		mainWorkAreaPanel.add(textEditorPanel);

		// Add Scroll Panel for Text Editor
		ScrollPanel contentScrollPanel = new ScrollPanel();
		textEditorPanel.addWest(contentScrollPanel, 100.0);

		//Add Panel Holder For Edit Area
		VerticalPanel editAreaMainPanel = new VerticalPanel();
		contentScrollPanel.setWidget(editAreaMainPanel);


        VerticalPanel editAreaContentPanel = new VerticalPanel();


        // Creation of the sample SourceCodeEditor. In the constructor we say wich syntax we want to edit.
		final SourceCodeEditor editor = new SourceCodeEditor("python");

	    // Seting an initial content for the editor.
		editor.setText("import unittest\n" +
                "from ChessBoard import *\n" +
                "\n" +
                "class GetLastMoveTest(unittest.TestCase):\n" +
                "\n" +
                "    def testGetLastMove(self):\n" +
                "        \"\"\"\n" +
                "        Test the behaviour of getLastMove for a valid move.\n" +
                "        The expected result should be returned.\n" +
                "        \"\"\"\n" +
                "        chessboard = ChessBoard()\n" +
                "        chessboard.resetBoard()\n" +
                "        expectedResult = ((4, 6), (4, 5))\n" +
                "        chessboard.addMove(expectedResult[0],expectedResult[1])\n" +
                "        result = chessboard.getLastMove()\n" +
                "        self.assertEqual(expectedResult[0],result[0])\n" +
                "        self.assertEqual(expectedResult[1],result[1])\n" +
                "        \n" +
                "    def testGetLastMove_noMovesMade(self):\n" +
                "        \"\"\"\n" +
                "        Test the behaviour of getLastMove when no moves have been made.\n" +
                "        None should be returned.\n" +
                "        \"\"\"\n" +
                "        chessboard = ChessBoard()\n" +
                "        chessboard.resetBoard()\n" +
                "        result = chessboard.getLastMove()\n" +
                "        self.assertEqual(None,result)\n" +
                "        ");


		// Creation of a button that will show the content of the editor
		Button b = new Button("Show content", new ClickHandler()
			{public void onClick(ClickEvent event) {
                Window.alert("The editor content is:\n\n" + editor.getText());}});

		//BEGIN: Creation of the screen layout
		editAreaContentPanel.add(new HTML("<br /><b>SourceCode Showcase</b><br /><br />"));
		editAreaContentPanel.add(editor);
		editAreaContentPanel.add(b);

		editAreaContentPanel.setCellHorizontalAlignment(b, HasHorizontalAlignment.ALIGN_RIGHT);

        editAreaMainPanel.add(editAreaContentPanel);
        editAreaMainPanel.setCellHorizontalAlignment(editAreaContentPanel, HasHorizontalAlignment.ALIGN_CENTER);


		editAreaMainPanel.setWidth("100%");
		editAreaContentPanel.setWidth("90%");
		editor.setWidth("100%");
		editor.setHeight("300px");

	    RootLayoutPanel.get().add(rootPanel);
		//END: Creation of the screen layout


	}
}
