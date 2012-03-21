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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gmail.cjbooms.thesis.pythonappengine.client.menus;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.*;

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