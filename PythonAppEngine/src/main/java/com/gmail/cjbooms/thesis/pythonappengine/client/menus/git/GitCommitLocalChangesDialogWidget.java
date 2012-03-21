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

package com.gmail.cjbooms.thesis.pythonappengine.client.menus.git;

import com.gmail.cjbooms.thesis.pythonappengine.client.editor.SelectionHelper;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.*;

/**
 * User: Conor Gallagher
 * Date: 05/10/11
 * Time: 23:13
 *
 * This class is used to commit changes to the local Repository
 */
public class GitCommitLocalChangesDialogWidget extends Composite  {

    private DialogBox commitDialog;
    private String title = "Commit Changes To Local Repository";
    private String logMessage = "";
    private String committerName = "";
    private String committerEmail = "";
    private GITCommands gitCommands;

    /**
     * Default Constructor
     */
    public GitCommitLocalChangesDialogWidget() {
    }

    /**
     * Create the New Project Dialog Window
     *
     * @return dialog The created Dialog Box
     */
    private DialogBox createCommitChangesDialog(){
        gitCommands = new GITCommands();

        DialogBox dialog = new DialogBox(true);
        dialog.setAnimationEnabled(true);
        dialog.center();
        dialog.setText(title);
        dialog.setGlassEnabled(true);

        VerticalPanel panel = new VerticalPanel();
        panel.setSpacing(5);
        panel.add(createCommitterNameEntry());
        panel.add(createCommitterEmailEntry());
        panel.add(createLogMessageTextBox());
        panel.add(createExecuteAndCancelButtons());

        dialog.add(panel);
        dialog.setAutoHideEnabled(true);
        return dialog;
    }

    private VerticalPanel createCommitterNameEntry() {
        VerticalPanel committerInput = new VerticalPanel();
        committerInput.add(new Label("Committer Name:"));
        TextBox committerNameTextBox = new TextBox();
        committerNameTextBox.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                committerName = event.getValue();
            }
        });
        committerInput.add(committerNameTextBox);
        return committerInput;
    }

    private VerticalPanel createCommitterEmailEntry() {
        VerticalPanel committerInput = new VerticalPanel();
        committerInput.add(new Label("Committer Email:"));
        TextBox committerEmailTextBox = new TextBox();
        committerEmailTextBox.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                committerEmail = event.getValue();
            }
        });
        committerInput.add(committerEmailTextBox);
        return committerInput;
    }

    //TODO Refactor To Utility Class
    private HorizontalPanel createExecuteAndCancelButtons() {
        Button executeCommit  = createCommitChangesButton();
        Button closeDialog = createCloseDialogButton();
        return createHorizontalHolder(closeDialog, executeCommit);

    }

    /**
     * Create and return the Text holder for the log messsage
     * @return
     */
   private VerticalPanel createLogMessageTextBox() {
       VerticalPanel logPanel = new VerticalPanel();
       logPanel.add(new Label("Enter Log Message:"));
        TextArea logMessageBox = new TextArea();
        logMessageBox.setCharacterWidth(40);
        logMessageBox.setVisibleLines(4);
        logMessageBox.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                logMessage = event.getValue();
            }
        });
       logPanel.add(logMessageBox);
       logPanel.setSpacing(10);
       logPanel.setHeight("100px");
       return logPanel;
    }


    private Button createCloseDialogButton(){
        return new Button("close", new ClickHandler(){
            @Override
            public void onClick(ClickEvent ce) {
                commitDialog.hide();
            }
        });
    }

    private Button createCommitChangesButton(){
        return new Button("Commit Changes", new ClickHandler(){
            @Override
            public void onClick(ClickEvent ce) {
               gitCommands.commitChangesToLocalRepository(SelectionHelper.getCurrentProjectDirectory(),logMessage,committerName,committerEmail );
               commitDialog.hide();
            }
        });
    }

    /**
     * Helper Method to add two widgets to a horizontal panel
     * TODO Refactor To Utility Class
     * @param leftWidget
     * @param rightWidget
     * @return
     */
    private HorizontalPanel createHorizontalHolder(Widget leftWidget, Widget rightWidget){
        HorizontalPanel hpanel = new HorizontalPanel();
        hpanel.setSpacing(10);
        hpanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        hpanel.add(leftWidget);
        hpanel.add(rightWidget);
        return hpanel;
    }


    /**
     * Create the Command Object to open this dialog
     * Used for assigning to the appropriate Menu Entry
     *
     * @return Open Clone Dialog Command
     */
    public Command openDialogForGitCommitChangesCommand(){
        return new Command() {
            @Override
            public void execute() {
                commitDialog = createCommitChangesDialog();
            }
        };
    }
}
