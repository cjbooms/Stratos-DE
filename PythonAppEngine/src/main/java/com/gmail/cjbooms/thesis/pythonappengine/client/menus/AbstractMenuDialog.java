package com.gmail.cjbooms.thesis.pythonappengine.client.menus;

import com.gmail.cjbooms.thesis.pythonappengine.client.menus.git.GITCommands;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.*;

import static com.gmail.cjbooms.thesis.pythonappengine.shared.ConfigConstants.PROJECT_ROOT;

/**
 * User: Conor Gallagher
 * Date: 23/10/11
 * Time: 23:04
 */
public abstract class AbstractMenuDialog extends Composite {
   
    protected DialogBox dialog;
    protected GITCommands gitCommands;
    protected String remoteLoginName;
    protected String remoteLoginPassword;
    protected String gitURLEntered;
    protected String saveToLocation;
    protected String title;



    protected AbstractMenuDialog() {
        gitCommands = new GITCommands();
        saveToLocation = "";
        gitURLEntered = "";
    }

    /**
     * Create the Command Object to open this Menu dialog
     * Used for assigning to the appropriate Menu Entry
     *
     * @return A Command object referencing the Dialog Widget
     */
    public Command openMenuDialog(){
        return new Command() {
            @Override
            public void execute() {
                dialog = createDialog();
            }
        };
    }

    /**
     * Create the Dialog Window
     *
     * @return dialog The created Dialog Box
     */
    protected DialogBox createDialog(){

        DialogBox dialog = createStandardDialog();

        VerticalPanel panel = new VerticalPanel();
        panel.setSpacing(5);
        addCustomWidgetsToDialogPanel(panel);
        panel.add(createExecuteAndCancelButtons());

        dialog.add(panel);

        return dialog;
    }


    /**
     * Create a dialog with standard settings
     * @return
     */
    protected DialogBox createStandardDialog() {
        DialogBox dialog = new DialogBox(true);
        dialog.setAnimationEnabled(true);
        dialog.center();
        dialog.setText(title);
        dialog.setGlassEnabled(true);
        dialog.setAutoHideEnabled(true);
        return dialog;
    }


    /**
     * Add two widgets to a horizontal panel
     * @param leftWidget
     * @param rightWidget
     * @return the horizontal panel containing both widgets
     */
    protected HorizontalPanel createHorizontalHolder(Widget leftWidget, Widget rightWidget){
        HorizontalPanel hpanel = new HorizontalPanel();
        hpanel.setSpacing(10);
        hpanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        hpanel.add(leftWidget);
        hpanel.add(rightWidget);
        return hpanel;
    }

    /**
     * Create generic execute and cancel buttons housed in a horizontal panel
     * @return panel containing both buttons
     */
    protected HorizontalPanel createExecuteAndCancelButtons() {
        Button executeClone  = createExecutePushButton();
        Button closeDialog = createCloseDialogButton();
        return createHorizontalHolder(closeDialog, executeClone);
    }

    /**
     * Close Button
     * @return
     */
    protected Button createCloseDialogButton(){
        return new Button("Close", new ClickHandler(){
            @Override
            public void onClick(ClickEvent ce) {
                dialog.hide();
            }
        });
    }

    /**
     * Create login name entry widget
     * @return vertical panel housing login name entry
     */
    protected VerticalPanel createLoginNameInput() {
        VerticalPanel loginPanel = new VerticalPanel();
        loginPanel.add(new Label("Login User Name:"));
        TextBox loginTextBox = new TextBox();
        loginTextBox.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                remoteLoginName = event.getValue();
            }
        });
        loginPanel.add(loginTextBox);
        return loginPanel;
    }

    /**
     * Create login password entry widget
     * @return vertical panel housing password entry
     */
    protected VerticalPanel createLoginPasswordInput() {
        VerticalPanel passwordPanel = new VerticalPanel();
        passwordPanel.add(new Label("Login Password:"));
        PasswordTextBox passwordTextBox = new PasswordTextBox();
        passwordTextBox.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                remoteLoginPassword = event.getValue();
            }
        });
        passwordPanel.add(passwordTextBox);
        return passwordPanel;
    }

    /**
     * Add Custom Widgets to a vertical Panel.
     * Used to customize Dialog widget
     * @param panel, the panel to add widgets to
     */
    protected abstract void addCustomWidgetsToDialogPanel(VerticalPanel panel);


    /**
     * Execute Button
     * @return
     */
    protected abstract Button createExecutePushButton();




}
