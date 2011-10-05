/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gmail.cjbooms.thesis.pythonappengine.client.menus;

import com.gmail.cjbooms.thesis.pythonappengine.client.filebrowser.FileWrapper;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.IOException;

/**
 *
 */

@RemoteServiceRelativePath("gs")
public interface GitCommandsService extends RemoteService{
    boolean createRepository(String filePath) throws IOException;
    void cloneRepositoryOverHTTP(String filePath, String gitHttpURL);
    void initializeNewRepository(String filePath) throws IOException;
    void addFileToRepository(String pathToRepository, String fileNameToAdd) throws IOException;
}
