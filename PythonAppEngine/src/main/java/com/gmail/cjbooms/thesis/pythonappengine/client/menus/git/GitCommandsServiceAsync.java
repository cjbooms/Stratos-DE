package com.gmail.cjbooms.thesis.pythonappengine.client.menus.git;

import com.gmail.cjbooms.thesis.pythonappengine.client.filebrowser.FileWrapper;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.io.IOException;

/**
 */
public interface GitCommandsServiceAsync {

    void cloneRepositoryOverHTTP(String filePath, String gitHttpURL, AsyncCallback async);

    void initializeNewRepository(String filePath, AsyncCallback async);

    void addFileToRepository(String pathToRepository, String fileNameToAdd, AsyncCallback async);

    void commitChangesToLocalRepository(String pathToRepository, String logMessage,
                                        String committerName, String committerEmail, AsyncCallback<Void> async);

    void pushLocalCommitsToRemoteRepository(String pathToLocalRepository, String remoteRepoURL,
                                            String userName, String password, AsyncCallback<Void> async);
}
