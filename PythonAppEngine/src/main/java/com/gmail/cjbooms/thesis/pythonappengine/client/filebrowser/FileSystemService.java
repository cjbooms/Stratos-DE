/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gmail.cjbooms.thesis.pythonappengine.client.filebrowser;

import com.google.gwt.user.client.rpc.RemoteService;

/**
 *
 * @author firas
 */
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("fs")
public interface FileSystemService extends RemoteService{
    FileWrapper[] getContents(FileWrapper file);
    FileWrapper[] getContents(String file);
    Boolean deleteFile(String absoluteName);
}
