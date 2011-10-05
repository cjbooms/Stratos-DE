/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gmail.cjbooms.thesis.pythonappengine.client.menus;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;


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

    private String hello= "sds";

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
                //TODO Something wrong. set Dialog Message
                GitOperationResultDialog gitOperationResultDialog = new GitOperationResultDialog("Clone Failure");
            }

            @Override
            public void onSuccess(Boolean result) {
                 //TODO set Dialog Message using boolean result
                GitOperationResultDialog gitOperationResultDialog = new GitOperationResultDialog("Clone Success");

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
                //TODO Something wrong. set Dialog Message
                GitOperationResultDialog gitOperationResultDialog = new GitOperationResultDialog("Create New Project Failure");
            }

            @Override
            public void onSuccess(Boolean result) {
                 //TODO set Dialog Message using boolean result
                GitOperationResultDialog gitOperationResultDialog = new GitOperationResultDialog("Create New Project Success");

            }

        };
        gitCommandsSvc.initializeNewRepository(filePath, callback);

    }



}