package com.gmail.cjbooms.thesis.pythonappengine.client.appengine;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * User: Conor Gallagher
 * Date: 02/11/11
 * Time: 22:44
 */

@RemoteServiceRelativePath("ae")
public interface AppEngineService extends RemoteService {

    String deployToAppEngine(String login, String password, String projectPath);
}
