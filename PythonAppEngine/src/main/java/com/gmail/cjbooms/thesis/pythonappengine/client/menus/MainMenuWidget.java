/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.cjbooms.thesis.pythonappengine.client.menus;

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
    //private HelpDialogWidget helpDialog;
    private GITCommands gitCommands;
    private GitCloneDialogWidget gitCloneDialogWidget;
    private GitCommitLocalChangesDialogWidget gitCommitDialogWidget;
    private GitPushChangesDialogWidget gitPushDialogWidget;
    private CreateNewProjectDialogWidget createNewProjectDialogWidget;



    public MainMenuWidget() {
        //this.helpDialog = new HelpDialogWidget();
        gitCommands = new GITCommands();
        gitCloneDialogWidget = new GitCloneDialogWidget();
        gitCommitDialogWidget = new GitCommitLocalChangesDialogWidget();
        createNewProjectDialogWidget = new CreateNewProjectDialogWidget();
        gitPushDialogWidget = new GitPushChangesDialogWidget();
        createMenu();
        initWidget(menu);
    }

    private void createMenu() {
        menu = new MenuBar();

        MenuBar fileMenu = new MenuBar(true);
        fileMenu.addItem("New File", createBlankCommand());
        fileMenu.addItem("Delete File", createBlankCommand());
        fileMenu.addItem("Upload File", createBlankCommand());
        fileMenu.addItem("Refresh", createBlankCommand());
        fileMenu.addSeparator();
        fileMenu.addItem("Exit", createBlankCommand());
        menu.addItem("File", fileMenu);

        MenuBar projectMenu = new MenuBar(true);
        projectMenu.addItem("New Project", createNewProjectDialogWidget.openDialogForNewProjectCommand());
        projectMenu.addItem("Delete Project", createBlankCommand());
        projectMenu.addItem("Synch Project", createBlankCommand());
        menu.addItem("Project", projectMenu);

        MenuBar gitMenu = new MenuBar(true);
        gitMenu.addItem("Clone", gitCloneDialogWidget.openDialogForGITCloneCommand());
        gitMenu.addItem("Commit", gitCommitDialogWidget.openDialogForGitCommitChangesCommand());
        gitMenu.addItem("Push", gitPushDialogWidget.openDialogForGITPushCommand());
        menu.addItem("GIT", gitMenu);

        MenuBar appEngineMenu = new MenuBar(true);
        appEngineMenu.addItem("Deploy", createBlankCommand());
        menu.addItem("AppEngine", appEngineMenu);    }

    private Command createBlankCommand() {
        return new Command() {
            @Override
            public void execute() {
                Window.alert("This command has been not implemented yet.");
            }
        };
    }

}
