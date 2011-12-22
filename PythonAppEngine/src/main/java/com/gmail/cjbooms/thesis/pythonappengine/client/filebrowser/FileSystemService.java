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
    FileWrapper[] getDirectoryContents(FileWrapper file) throws IOException;
    FileWrapper[] getDirectoryContents(String file) throws IOException;
    String getFileContents(String file) throws IOException;
    void deleteFile(String absoluteName);
    void saveFile(String absoluteName, String fileContents) throws Exception;
}
