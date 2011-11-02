/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gmail.cjbooms.thesis.pythonappengine.client.filebrowser;

import com.google.gwt.user.client.rpc.RemoteService;

/**
 *
 */
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.io.IOException;

@RemoteServiceRelativePath("fs")
public interface FileSystemService extends RemoteService{
    FileWrapper[] getDirectoryContents(FileWrapper file);
    FileWrapper[] getDirectoryContents(String file);
    String getFileContents(String file);
    void deleteFile(String absoluteName);
    void saveFile(String absoluteName, String fileContents) throws Exception;
}
