/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gmail.cjbooms.thesis.pythonappengine.client.menus;

import com.gmail.cjbooms.thesis.pythonappengine.client.filebrowser.FileWrapper;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.io.IOException;

/**
 *
 * @author firas
 */

@RemoteServiceRelativePath("gs")
public interface GitCommandsService extends RemoteService{
    boolean createRepository(String filePath) throws IOException;
    boolean cloneRepositoryOverHTTP(String filePath, String gitHttpURL);
    boolean cloneRepositoryOverHTTPS(String filePath, String gitHttpsURL);

}
