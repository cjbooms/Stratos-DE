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

package com.gmail.cjbooms.thesis.pythonappengine.client.menus;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;

/**
 * User: conor
 * Date: 19/09/11
 * Time: 21:43
 */
public class OperationResultDialog extends Composite {

    private DialogBox statusDialog;
    private String title = "Operation Result";


    /**
     * Default Constructor
     */
    public OperationResultDialog(String message){
        statusDialog = createGitStatusDialog(message);
        statusDialog.show();
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
                statusDialog.hide();
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