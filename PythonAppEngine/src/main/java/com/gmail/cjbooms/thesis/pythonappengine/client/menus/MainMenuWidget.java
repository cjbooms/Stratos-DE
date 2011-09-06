/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.cjbooms.thesis.pythonappengine.client.menus;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuBar;

/**
 *
 * @author firas
 */
public class MainMenuWidget extends Composite {

    private MenuBar menu;
    //private HelpDialogWidget helpDialog;
    private GITCommands gitCommands;
    public MainMenuWidget() {
        //this.helpDialog = new HelpDialogWidget();
        this.gitCommands = new GITCommands();
        createMenu();
        initWidget(menu);
    }

    private void createMenu() {
        menu = new MenuBar();

        MenuBar fileMenu = new MenuBar(true);
        fileMenu.addItem("New Directory", createBlankCommand());
        fileMenu.addItem("Delete Selected", createBlankCommand());
        fileMenu.addItem("Refresh", createBlankCommand());
        fileMenu.addItem("Upload", createBlankCommand());
        fileMenu.addSeparator();
        fileMenu.addItem("Exit", createBlankCommand());
        menu.addItem("File", fileMenu);

        MenuBar projectMenu = new MenuBar(true);
        projectMenu.addItem("New Project", createBlankCommand());
        projectMenu.addItem("Delete Project", createBlankCommand());
        projectMenu.addItem("Synch Project", createBlankCommand());
        menu.addItem("Project", projectMenu);

        MenuBar gitMenu = new MenuBar(true);
        gitMenu.addItem("Checkout", createBlankCommand());
        gitMenu.addItem("Commit", createBlankCommand());
        gitMenu.addItem("Merge", createBlankCommand());
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
