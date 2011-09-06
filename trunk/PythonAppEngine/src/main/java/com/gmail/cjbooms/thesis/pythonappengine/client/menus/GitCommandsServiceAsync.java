package com.gmail.cjbooms.thesis.pythonappengine.client.menus;

import com.gmail.cjbooms.thesis.pythonappengine.client.filebrowser.FileWrapper;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.io.IOException;

/**
 * @author firas
 */
public interface GitCommandsServiceAsync {

    void createRepository(String filePath, AsyncCallback<Boolean> async) throws IOException;

    void cloneRepositoryOverHTTP(String filePath, String gitHttpURL, AsyncCallback<Boolean> async);

    void cloneRepositoryOverHTTPS(String filePath, String gitHttpsURL, AsyncCallback<Boolean> async);
}
