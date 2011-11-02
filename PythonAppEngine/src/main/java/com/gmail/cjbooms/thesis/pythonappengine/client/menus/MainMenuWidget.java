/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.cjbooms.thesis.pythonappengine.client.menus;

import com.gmail.cjbooms.thesis.pythonappengine.client.menus.file.NewFileDialog;
import com.gmail.cjbooms.thesis.pythonappengine.client.menus.git.*;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuBar;

/**
 *
 */
public class MainMenuWidget extends Composite {

    private MenuBar menu;
    private GitCloneDialogWidget gitCloneDialogWidget;
    private GitCommitLocalChangesDialogWidget gitCommitDialogWidget;
    private GitPushChangesDialogWidget gitPushDialogWidget;
    private CreateNewProjectDialogWidget createNewProjectDialogWidget;
    private NewFileDialog newFileDialog;


    public MainMenuWidget() {
        gitCloneDialogWidget = new GitCloneDialogWidget();
        gitCommitDialogWidget = new GitCommitLocalChangesDialogWidget();
        createNewProjectDialogWidget = new CreateNewProjectDialogWidget();
        gitPushDialogWidget = new GitPushChangesDialogWidget();
        newFileDialog = new NewFileDialog();
        createMenu();
        initWidget(menu);
    }

    private void createMenu() {
        menu = new MenuBar();

        MenuBar fileMenu = createFileMenu();
        menu.addItem("File", fileMenu);

        MenuBar projectMenu = createProjectMenu();
        menu.addItem("Project", projectMenu);

        MenuBar gitMenu = createGITMenu();
        menu.addItem("GIT", gitMenu);

        MenuBar appEngineMenu = createAppEngineMenu();
        menu.addItem("AppEngine", appEngineMenu);

    }

    private MenuBar createAppEngineMenu() {
        MenuBar appEngineMenu = new MenuBar(true);
        appEngineMenu.addItem("Deploy", createBlankCommand());
        return appEngineMenu;
    }

    private MenuBar createGITMenu() {
        MenuBar gitMenu = new MenuBar(true);
        gitMenu.addItem("Clone", gitCloneDialogWidget.openDialogForGITCloneCommand());
        gitMenu.addItem("Commit", gitCommitDialogWidget.openDialogForGitCommitChangesCommand());
        gitMenu.addItem("Push", gitPushDialogWidget.openMenuDialog());
        return gitMenu;
    }

    private MenuBar createProjectMenu() {
        MenuBar projectMenu = new MenuBar(true);
        projectMenu.addItem("New Project", createNewProjectDialogWidget.openDialogForNewProjectCommand());
        projectMenu.addItem("Delete Project", createBlankCommand());
        projectMenu.addItem("Synch Project", createBlankCommand());
        return projectMenu;
    }

    private MenuBar createFileMenu() {
        MenuBar fileMenu = new MenuBar(true);
        fileMenu.addItem("New File", newFileDialog.openMenuDialog());
        fileMenu.addItem("Delete File", createBlankCommand());
        fileMenu.addItem("Upload File", createBlankCommand());
        fileMenu.addItem("Refresh", createBlankCommand());
        fileMenu.addSeparator();
        fileMenu.addItem("Exit", createBlankCommand());
        return fileMenu;
    }

    private Command createBlankCommand() {
        return new Command() {
            @Override
            public void execute() {
                new OperationResultDialog("This Command has not yet been implemented!");
            }
        };
    }

}
