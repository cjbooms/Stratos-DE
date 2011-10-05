package com.gmail.cjbooms.thesis.pythonappengine.client.menus;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.*;

/**
 * User: conor
 * Date: 19/09/11
 * Time: 21:43
 */
public class GitOperationResultDialog extends Composite {

    private DialogBox gitStatusDialog;
    private String title = "GIT Operation Status";


    /**
     * Default Constructor
     */
    public GitOperationResultDialog(String message){
        gitStatusDialog = createGitStatusDialog(message);
        gitStatusDialog.show();
    }

    /**
     * Create the GIT Clone Dialog Window
     *
     * @return dialog The created Dialog Box
     */
    private DialogBox createGitStatusDialog(String message){
        DialogBox dialog = new DialogBox(true);
        dialog.setAnimationEnabled(true);
        dialog.center();
        dialog.setText(title);
        dialog.setGlassEnabled(true);

        VerticalPanel panel = new VerticalPanel();
        panel.add(createHtmlContent(message));
        panel.add(createCloseDialogButton());

        dialog.add(panel);
        dialog.setAutoHideEnabled(true);
        return dialog;
    }



    private Button createCloseDialogButton(){
        return new Button("OK", new ClickHandler(){
            @Override
            public void onClick(ClickEvent ce) {
                gitStatusDialog.hide();
            }
        });
    }



    private HTML createHtmlContent(String message){
        StringBuilder sb = new StringBuilder();
        sb.append("<p>");
        sb.append(message);
        return new HTML(sb.toString());
    }



}