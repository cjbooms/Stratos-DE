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

package com.gmail.cjbooms.thesis.pythonappengine.client.filebrowser;

import com.gmail.cjbooms.thesis.pythonappengine.shared.MyResources;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;

/**
 * User: Conor Gallagher
 * Date: 10/12/11
 * Time: 01:18
 */
public class TreeItemWidget extends Composite {

    MyResources resources;
    HorizontalPanel treeItemPanel;
    /**
     * Create a new Tree Widget that has a file icon and caption
     * @param fileType
     * @param caption
     */
    public TreeItemWidget(FileType fileType, String caption) {
        resources = GWT.create(MyResources.class);
        treeItemPanel = new HorizontalPanel();
        treeItemPanel.setSpacing(2);

        Image icon = getIconForFileType(fileType);
        treeItemPanel.add(icon);
        treeItemPanel.add(new HTML(caption));
        initWidget(treeItemPanel);
    }

    /**
     * Determine the image type to display based on FileType
     * @param fileType
     * @return
     */
    private Image getIconForFileType(FileType fileType) {
        switch(fileType) {
            case DIR :
                return new Image(resources.folder());
            case IMG:
                return new Image(resources.imageFile());
            case DOC:
                return new Image(resources.documentFile());
            case MM:
                return new Image(resources.videoFile());
            case PYTHON:
                return new Image(resources.pythonFile());
            case RUBY:
                return new Image(resources.rubyFile());
            case TABULAR:
                return new Image(resources.tableFile());
            case SHEET:
                return new Image(resources.spreadsheetFile());
            default :
                return new Image(resources.blankFile());
        }
    }
}
