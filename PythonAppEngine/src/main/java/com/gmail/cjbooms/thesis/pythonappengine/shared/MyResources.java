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

package com.gmail.cjbooms.thesis.pythonappengine.shared;

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
