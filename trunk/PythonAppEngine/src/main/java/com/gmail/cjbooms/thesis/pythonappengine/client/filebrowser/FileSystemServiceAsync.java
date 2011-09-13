/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gmail.cjbooms.thesis.pythonappengine.client.filebrowser;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.io.IOException;

/**
 *
 */
public interface FileSystemServiceAsync {
    void getDirectoryContents(FileWrapper file, AsyncCallback<FileWrapper[]> callback);
    void getDirectoryContents(String file, AsyncCallback<FileWrapper[]> async);
    void deleteFile(String absoluteName, AsyncCallback<Boolean> callback);
    void getFileContents(String file, AsyncCallback<String> async);
    void saveFile(String absoluteName, String fileContents, AsyncCallback async);
}
