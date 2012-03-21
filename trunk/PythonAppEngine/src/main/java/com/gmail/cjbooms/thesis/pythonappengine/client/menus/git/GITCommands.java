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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gmail.cjbooms.thesis.pythonappengine.client.menus.git;

import com.gmail.cjbooms.thesis.pythonappengine.client.menus.OperationResultDialog;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;


/**
 *
 */
public class GITCommands{


    private GitCommandsServiceAsync gitCommandsSvc = GWT.create(GitCommandsService.class);

    /**
     * Default Constructor.
     */
    public GITCommands(){

    }


    /**
     * Execute the RPC GIT CLone over HTTP call
     *
     * @param filePath
     * @param url
     */
    public void cloneGITRepositoryOverHttp(String filePath, String url) {
        if (this.gitCommandsSvc == null) {
            gitCommandsSvc = GWT.create(GitCommandsService.class);
        }
        AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {

            @Override
            public void onFailure(Throwable thrwbl) {
                new OperationResultDialog("Clone Failure");
            }

            @Override
            public void onSuccess(Boolean result) {
                new OperationResultDialog("Clone Success");

            }

        };
        gitCommandsSvc.cloneRepositoryOverHTTP(filePath, url, callback);

    }


    /**
     * Execute the RPC GIT Initialize New Repo call
     *
     * @param filePath
     */
    public void initializeNewRepository(String filePath) {
        if (this.gitCommandsSvc == null) {
            gitCommandsSvc = GWT.create(GitCommandsService.class);
        }
        AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {

            @Override
            public void onFailure(Throwable thrwbl) {
                new OperationResultDialog("Create New Project Failure");
            }

            @Override
            public void onSuccess(Boolean result) {
                new OperationResultDialog("Create New Project Success");

            }

        };
        gitCommandsSvc.initializeNewRepository(filePath, callback);

    }

    /**
     * Execute the RPC to commit changes to local repository
     *
     * @param filePath
     */
    public void commitChangesToLocalRepository(String filePath,String logMessage,String committerName,String committerEmail) {
        if (this.gitCommandsSvc == null) {
            gitCommandsSvc = GWT.create(GitCommandsService.class);
        }
        AsyncCallback<Void> callback = new AsyncCallback<Void>() {

            @Override
            public void onFailure(Throwable thrwbl) {
                new OperationResultDialog("Commit Changes Failure");
            }

            @Override
            public void onSuccess(Void result) {
                 new OperationResultDialog("Commit Success");
            }

        };
        gitCommandsSvc.commitChangesToLocalRepository(filePath, logMessage, committerName, committerEmail, callback);

    }

    /**
     * Execute the RPC to commit changes to local repository
     *
     * @param filePath
     */
    public void pushToRemoteRepository(String filePath,String remoteRepo,String userName,String userPassword) {
        if (this.gitCommandsSvc == null) {
            gitCommandsSvc = GWT.create(GitCommandsService.class);
        }
        AsyncCallback<Void> callback = new AsyncCallback<Void>() {

            @Override
            public void onFailure(Throwable thrwbl) {
                new OperationResultDialog("Push Changes Failure");
            }

            @Override
            public void onSuccess(Void result) {
                new OperationResultDialog("Push Success");

            }

        };
        gitCommandsSvc.pushLocalCommitsToRemoteRepository(filePath, remoteRepo, userName, userPassword, callback);

    }

    /**
     * Execute the RPC to add file to local repository
     *
     * @param pathToRepository Repository Path
     * @param fileName name of file to add to repo
     */
    public void addFileToRepository(String pathToRepository, String fileName) {
        if (this.gitCommandsSvc == null) {
            gitCommandsSvc = GWT.create(GitCommandsService.class);
        }
        AsyncCallback<Void> callback = new AsyncCallback<Void>() {

            @Override
            public void onFailure(Throwable thrwbl) {
                new OperationResultDialog("Failed to Add File to Repository");
            }

            @Override
            public void onSuccess(Void result) {
                new OperationResultDialog("File Added to Repository Successfully");

            }

        };
        gitCommandsSvc.addFileToRepository(pathToRepository, fileName, callback);

    }

}