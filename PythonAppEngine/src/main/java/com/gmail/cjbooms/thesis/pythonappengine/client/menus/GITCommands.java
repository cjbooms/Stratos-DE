/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gmail.cjbooms.thesis.pythonappengine.client.menus;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;


/**
 *
 * @author firas
 */
public class GITCommands extends Composite{

    private DialogBox gitDialog;
    private String title = "GIT Operation Status";
    private GitCommandsServiceAsync gitCommandsSvc = GWT.create(GitCommandsService.class);


    public GITCommands(){
        VerticalPanel panel = new VerticalPanel();
        gitDialog =createDialog();
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
                gitDialog.show();
            }
        });
    }

    private Button createCloseButton(){
        return new Button("close", new ClickHandler(){
            @Override
            public void onClick(ClickEvent ce) {
                gitDialog.hide();
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
                gitDialog.show();
            }
        };
    }

    /**
     * Clone a GIT Repository and display a status message to Screen
     *
     * @return
     */
    public Command cloneGITRepositoryOverHTTP(){
        return new Command() {
            @Override
            public void execute() {
                //TODO - Ask for Input, perform operation, and set Dialog message
                cloneGITRepositoryOverHttp("PATH","REPO LOCATION");
                gitDialog.show();
            }
        };
    }

    /**
     * Execute the RPC GIT CLone over HTTP call
     *
     * @param filePath
     * @param url
     */
    private void cloneGITRepositoryOverHttp(String filePath, String url) {
        if (this.gitCommandsSvc == null) {
            gitCommandsSvc = GWT.create(GitCommandsService.class);
        }
        AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {

            @Override
            public void onFailure(Throwable thrwbl) {
                //TODO Something wrong. set Dialog Message
            }

            @Override
            public void onSuccess(Boolean result) {
                 //TODO set Dialog Message using boolean result
            }

        };
        gitCommandsSvc.cloneRepositoryOverHTTP(filePath, url, callback);

    }

    private static HTML createCloneSuccessHtmlContent() {
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

}