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

package com.gmail.cjbooms.thesis.pythonappengine.client.menus.file;

import com.gmail.cjbooms.thesis.pythonappengine.client.editor.SelectionHelper;
import com.gmail.cjbooms.thesis.pythonappengine.client.filebrowser.FileCommands;
import com.gmail.cjbooms.thesis.pythonappengine.client.menus.AbstractMenuDialog;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.*;


/**
 * User: Conor Gallagher
 * Date: 01/11/11
 * Time: 20:49
 */
public class NewFileDialog extends AbstractMenuDialog {

    FileCommands fileCommands;
    String fileName = "";
    String fileContents = "";
    boolean addToGIT = true;

    public NewFileDialog() {
        super();
        title = "Create New File";
        fileCommands = new FileCommands();

    }


    /**
     * Add Custom Widgets to a vertical Panel.
     * Used to customize Dialog widget
     * @param panel, the panel to add widgets to
     */
    @Override
    protected void addCustomWidgetsToDialogPanel(VerticalPanel panel) {
        panel.add(createFileNameInput());
        panel.add(createGitAddCheclBox());
    }


    private HorizontalPanel createGitAddCheclBox() {
        Label label = new Label("");
        CheckBox checkbox = new CheckBox("Add To GIT");
        checkbox.setValue(true);
        checkbox.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                addToGIT = ((CheckBox) event.getSource()).getValue();
            }
        });
        return createHorizontalHolder(checkbox, label);
    }

    /**
     * Create a file Name Input section
     * @return
     */
    private HorizontalPanel createFileNameInput() {
        Label label = new Label("Enter File Name");
        TextBox fileNameTextBox = new TextBox();
        fileNameTextBox.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                fileName = event.getValue();
            }
        });
        return createHorizontalHolder(fileNameTextBox, label);
    }

    @Override
    protected Button createExecutePushButton() {
        return new Button("Create File", new ClickHandler(){
            @Override
            public void onClick(ClickEvent ce) {
                String filePath = SelectionHelper.getCurrentDirectory() + "/" + fileName;
                String projectDirectory = SelectionHelper.getCurrentProjectDirectory();
                fileCommands.saveFile(filePath, fileContents);

                //If Add to Git Checkbox is ticked
                if(addToGIT){
                    gitCommands.addFileToRepository(projectDirectory, fileName);
                }
               dialog.hide();
            }
        });
    }


}
