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

package com.gmail.cjbooms.thesis.pythonappengine.client.menus.git;

import com.google.gwt.user.client.rpc.AsyncCallback;

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
