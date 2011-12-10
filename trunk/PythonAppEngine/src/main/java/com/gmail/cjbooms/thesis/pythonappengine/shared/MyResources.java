package com.gmail.cjbooms.thesis.pythonappengine.shared;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * User: Conor Gallagher
 * Date: 14/11/11
 * Time: 00:30
 */
public interface MyResources extends ClientBundle  {


    @Source("Folder.png")
    public ImageResource folder();

    @Source("File.png")
    public ImageResource blankFile();

    @Source("Python.png")
    public ImageResource pythonFile();

    @Source("Document.png")
    public ImageResource documentFile();

    @Source("Picture.png")
    public ImageResource imageFile();

    @Source("Presentation.png")
    public ImageResource videoFile();

    @Source("Table.png")
    public ImageResource tableFile();

    @Source("Resources.png")
    public ImageResource resourcesFile();

    @Source("Spreadsheet.png")
    public ImageResource spreadsheetFile();

    @Source("Ruby.png")
    public ImageResource rubyFile();

    @Source("Copy.png")
    public ImageResource copyIcon();

    @Source("OpenDocument.png")
    public ImageResource openIcon();

    @Source("Refresh.png")
    public ImageResource refreshIcon();

    @Source("Save.png")
    public ImageResource saveIcon();

    @Source("UploadDocument.png")
    public ImageResource uploadIcon();


    @Source("Separator.png")
    public ImageResource buttonSeparator();

}
