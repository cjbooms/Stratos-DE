package com.gmail.cjbooms.thesis.pythonappengine.client.filebrowser;

import com.gmail.cjbooms.thesis.pythonappengine.client.editor.SelectionHelper;
import com.gmail.cjbooms.thesis.pythonappengine.client.menus.OperationResultDialog;
import com.gmail.cjbooms.thesis.pythonappengine.client.menus.git.GitCommandsService;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.TreeItem;

/**
 * User: Conor Gallagher
 * Date: 01/11/11
 * Time: 21:47
 */
public class FileCommands {

    private FileSystemServiceAsync fileSystemSvc = GWT.create(FileSystemService.class);

    /**
     * Default Constructor.
     */
    public FileCommands(){

    }

    public void saveFile(String filePath, String fileContents) {
        if (this.fileSystemSvc == null) {
            fileSystemSvc = GWT.create(FileSystemService.class);
        }
        AsyncCallback<Void> callback = new AsyncCallback<Void>() {

            @Override
            public void onFailure(Throwable thrwbl) {
                new OperationResultDialog("Error Saving File");
            }
            @Override
            public void onSuccess(Void result) {
                new OperationResultDialog("File Successfully Created");
            }
        };
         fileSystemSvc.saveFile(filePath, fileContents, callback);
    }

    public void deleteFile(String filePath) {
        if (this.fileSystemSvc == null) {
            fileSystemSvc = GWT.create(FileSystemService.class);
        }
        AsyncCallback<Void> callback = new AsyncCallback<Void>() {

            @Override
            public void onFailure(Throwable thrwbl) {
                new OperationResultDialog("Error Deleting File");
            }
            @Override
            public void onSuccess(Void result) {
                new OperationResultDialog("File Successfully Deleted");
            }
        };
         fileSystemSvc.deleteFile(filePath, callback);
    }

}
