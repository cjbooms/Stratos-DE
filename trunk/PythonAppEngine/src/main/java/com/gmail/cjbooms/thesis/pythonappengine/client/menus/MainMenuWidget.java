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
package com.gmail.cjbooms.thesis.pythonappengine.client.menus;

import com.gmail.cjbooms.thesis.pythonappengine.client.menus.appengine.DeployDialog;
import com.gmail.cjbooms.thesis.pythonappengine.client.menus.file.NewFileDialog;
import com.gmail.cjbooms.thesis.pythonappengine.client.menus.git.CreateNewProjectDialogWidget;
import com.gmail.cjbooms.thesis.pythonappengine.client.menus.git.GitCloneDialogWidget;
import com.gmail.cjbooms.thesis.pythonappengine.client.menus.git.GitCommitLocalChangesDialogWidget;
import com.gmail.cjbooms.thesis.pythonappengine.client.menus.git.GitPushChangesDialogWidget;
import com.google.gwt.user.client.Command;
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
    private DeployDialog deployDialog;



    public MainMenuWidget() {
        gitCloneDialogWidget = new GitCloneDialogWidget();
        gitCommitDialogWidget = new GitCommitLocalChangesDialogWidget();
        createNewProjectDialogWidget = new CreateNewProjectDialogWidget();
        gitPushDialogWidget = new GitPushChangesDialogWidget();
        newFileDialog = new NewFileDialog();
        deployDialog = new DeployDialog();
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
        appEngineMenu.addItem("Deploy", deployDialog.openMenuDialog());
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
