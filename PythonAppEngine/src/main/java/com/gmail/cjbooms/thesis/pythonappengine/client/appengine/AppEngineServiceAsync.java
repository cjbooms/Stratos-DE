package com.gmail.cjbooms.thesis.pythonappengine.client.appengine;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

public interface AppEngineServiceAsync {

    void deployToAppEngine(String login, String password, String projectPath, AsyncCallback<String> async);

}
