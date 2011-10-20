/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gmail.cjbooms.thesis.pythonappengine.client.menus.git;

import com.gmail.cjbooms.thesis.pythonappengine.client.filebrowser.FileWrapper;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 *
 */

@RemoteServiceRelativePath("gs")
public interface GitCommandsService extends RemoteService{
    void cloneRepositoryOverHTTP(String filePath, String gitHttpURL);
    void initializeNewRepository(String filePath) throws IOException;
    void addFileToRepository(String pathToRepository, String fileNameToAdd) throws IOException;
    void commitChangesToLocalRepository(
            String pathToRepository, String logMessage, String committerName, String committerEmail) throws IOException;
    void pushLocalCommitsToRemoteRepository(String pathToLocalRepository, String remoteRepoURL,
            String userName, String password) throws IOException;
}
