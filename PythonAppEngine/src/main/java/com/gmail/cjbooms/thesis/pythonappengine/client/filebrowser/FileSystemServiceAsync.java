/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gmail.cjbooms.thesis.pythonappengine.client.filebrowser;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author firas
 */
public interface FileSystemServiceAsync {
    void getContents(FileWrapper file, AsyncCallback<FileWrapper[]> callback);
    void getContents(String file, AsyncCallback<FileWrapper[]> callback);
    void deleteFile(String absoluteName, AsyncCallback<Boolean> callback);
}
