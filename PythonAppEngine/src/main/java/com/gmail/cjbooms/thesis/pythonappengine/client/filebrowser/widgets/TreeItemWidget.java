package com.gmail.cjbooms.thesis.pythonappengine.client.filebrowser.widgets;

import com.gmail.cjbooms.thesis.pythonappengine.client.filebrowser.FileType;
import com.gmail.cjbooms.thesis.pythonappengine.shared.MyResources;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.*;

import static com.gmail.cjbooms.thesis.pythonappengine.client.filebrowser.FileType.*;

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
            default :
                return new Image(resources.documentFile());
        }
    }
}
