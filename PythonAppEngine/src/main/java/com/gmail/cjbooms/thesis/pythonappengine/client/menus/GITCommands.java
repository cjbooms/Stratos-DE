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
            }

            @Override
            public void onSuccess(Boolean result) {
                 //TODO set Dialog Message using boolean result
            }

        };
        gitCommandsSvc.cloneRepositoryOverHTTP(filePath, url, callback);

    }




}