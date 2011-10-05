/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gmail.cjbooms.thesis.pythonappengine.client.menus;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 *
 */
public class HelpDialogWidget extends Composite{

    private DialogBox helpDialog;
    private String title = "Help";
    
    public HelpDialogWidget(){
        VerticalPanel panel = new VerticalPanel();
        helpDialog=createDialog();
        panel.add(createOpenButton());
        initWidget(panel);
    }

    private DialogBox createDialog(){
        VerticalPanel panel = new VerticalPanel();
        DialogBox dialog = new DialogBox(true);
        dialog.setAnimationEnabled(true);
        dialog.center();
        dialog.setText(title);
        dialog.setGlassEnabled(true);
        panel.add(createHtmlContent());
        panel.add(createCloseButton());
        dialog.add(panel);

        //dialog.setAutoHideEnabled(false);
        return dialog;
    }

    private Button createOpenButton(){
        return new Button(title, new ClickHandler(){
            @Override
            public void onClick(ClickEvent ce) {
                helpDialog.show();
            }
        });
    }

    private Button createCloseButton(){
        return new Button("close", new ClickHandler(){
            @Override
            public void onClick(ClickEvent ce) {
                helpDialog.hide();
            }
        });
    }
    
    private static HTML createHtmlContent(){
        StringBuilder sb = new StringBuilder();
        sb.append("<p>");
        sb.append("This is a simple Online File Manager, it has been built on top of Google Web Toolkit."
                + "It has some basic functionalties :");
        sb.append("</p>");
        sb.append("<ul>");
        sb.append("<li>").append("Tree structure view of the file system.").append("</li>");
        sb.append("<li>").append("Deleting files and directories.").append("</li>");
        sb.append("<li>").append("Creating new Directories.").append("</li>");
        sb.append("</ul>");
        return new HTML(sb.toString());
    }

    public Command createOpenCommand(){
        return new Command() {
            @Override
            public void execute() {
                helpDialog.show();
            }
        };
    }
}