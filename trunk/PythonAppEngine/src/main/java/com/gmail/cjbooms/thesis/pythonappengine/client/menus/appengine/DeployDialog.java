package com.gmail.cjbooms.thesis.pythonappengine.client.menus.appengine;

import com.gmail.cjbooms.thesis.pythonappengine.client.appengine.AppEngineService;
import com.gmail.cjbooms.thesis.pythonappengine.client.appengine.AppEngineServiceAsync;
import com.gmail.cjbooms.thesis.pythonappengine.client.editor.SelectionHelper;
import com.gmail.cjbooms.thesis.pythonappengine.client.filebrowser.FileSystemService;
import com.gmail.cjbooms.thesis.pythonappengine.client.filebrowser.FileSystemServiceAsync;
import com.gmail.cjbooms.thesis.pythonappengine.client.menus.AbstractMenuDialog;
import com.gmail.cjbooms.thesis.pythonappengine.client.menus.OperationResultDialog;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * User: Conor Gallagher
 * Date: 02/11/11
 * Time: 23:57
 */
public class DeployDialog extends AbstractMenuDialog {


    private AppEngineServiceAsync appEngineSvc = GWT.create(AppEngineService.class);


    public DeployDialog() {
        super();
        title = "Deploy To App Engine";
    }



    /**
     * Add Custom Widgets to a vertical Panel.
     * Used to customize Dialog widget
     * @param panel, the panel to add widgets to
     */
    @Override
    protected void addCustomWidgetsToDialogPanel(VerticalPanel panel) {
        panel.add(createLoginNameInput());
        panel.add(createLoginPasswordInput());
    }

    @Override
    protected Button createExecutePushButton() {
        return new Button("Deploy", new ClickHandler(){
            @Override
            public void onClick(ClickEvent ce) {
                String projectDirectory = SelectionHelper.getCurrentProjectDirectory();
                deploy(remoteLoginName, remoteLoginPassword, projectDirectory);


               dialog.hide();
            }
        });
    }

    public void deploy(String email, String password, String projectDirectory) {
        if (this.appEngineSvc == null) {
            appEngineSvc = GWT.create(FileSystemService.class);
        }
        AsyncCallback<String> callback = new AsyncCallback<String>() {

            @Override
            public void onFailure(Throwable thrwbl) {
                new OperationResultDialog("Error Deploying File");
            }
            @Override
            public void onSuccess(String result) {
                new OperationResultDialog(result);
            }
        };
         appEngineSvc.deployToAppEngine(email, password, projectDirectory, callback);
    }
}
