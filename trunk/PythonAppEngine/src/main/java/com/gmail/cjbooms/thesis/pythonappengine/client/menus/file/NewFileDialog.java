package com.gmail.cjbooms.thesis.pythonappengine.client.menus.file;

import com.gmail.cjbooms.thesis.pythonappengine.client.editor.SelectionHelper;
import com.gmail.cjbooms.thesis.pythonappengine.client.filebrowser.FileCommands;
import com.gmail.cjbooms.thesis.pythonappengine.client.menus.AbstractMenuDialog;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;


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
