/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.cjbooms.thesis.pythonappengine.client.filebrowser.widgets;

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
    private HelpDialogWidget helpDialog;
    public MainMenuWidget() {
        this.helpDialog = new HelpDialogWidget();
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
        MenuBar helpMenu = new MenuBar(true);
        helpMenu.addItem("Help", helpDialog.createOpenCommand());
        helpMenu.addItem("About", createBlankCommand());
        menu.addItem("Help", helpMenu);
    }

    private Command createBlankCommand() {
        return new Command() {
            @Override
            public void execute() {
                Window.alert("This command has been not implemented yet.");
            }
        };
    }

}
