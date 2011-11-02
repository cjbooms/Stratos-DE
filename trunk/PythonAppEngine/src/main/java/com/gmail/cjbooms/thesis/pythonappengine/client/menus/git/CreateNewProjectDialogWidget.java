package com.gmail.cjbooms.thesis.pythonappengine.client.menus.git;

import com.gmail.cjbooms.thesis.pythonappengine.client.menus.git.GITCommands;
import com.gmail.cjbooms.thesis.pythonappengine.shared.ConfigConstants;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.*;

/**
 * User: Conor Gallagher
 * Date: 05/10/11
 * Time: 23:13
 *
 * This class is used to create a new Project from scratch. In doing so a
 * blank git repo will also be created locally
 * TODO -
 */
public class CreateNewProjectDialogWidget extends Composite implements ChangeHandler {

    private DialogBox cloneDialog;
    private String title = "New Project";
    private TextBox folderNameBox;

    private String saveToLocation = "";
    private static final String ROOT_LOCATION = ConfigConstants.PROJECT_ROOT;
    private GITCommands gitCommands;

    /**
     * Default Constructor
     */
    public CreateNewProjectDialogWidget() {
    }

    /**
     * Create the New Project Dialog Window
     *
     * @return dialog The created Dialog Box
     */
    private DialogBox createNewProjectDialog(){
        gitCommands = new GITCommands();

        DialogBox dialog = new DialogBox(true);
        dialog.setAnimationEnabled(true);
        dialog.center();
        dialog.setText(title);
        dialog.setGlassEnabled(true);

        VerticalPanel panel = new VerticalPanel();
        panel.setSpacing(5);
        panel.add(createFolderNameTextBox());
        panel.add(createExecuteAndCancelButtons());

        dialog.add(panel);
        dialog.setAutoHideEnabled(true);
        return dialog;
    }


    private HorizontalPanel createExecuteAndCancelButtons() {
        Button executeClone  = createNewProjectButton();
        Button closeDialog = createCloseDialogButton();
        return createHorizontalHolder(closeDialog, executeClone);

    }

   private HorizontalPanel createFolderNameTextBox() {
        folderNameBox = new TextBox();
        Label folderNameBoxLabel = new Label("Enter Project Name");
        folderNameBox.addChangeHandler(this);
        folderNameBox.setText("NEWFOLDER");
        saveToLocation = (ROOT_LOCATION + folderNameBox.getText());
        return createHorizontalHolder(folderNameBox, folderNameBoxLabel);
    }


    private Button createCloseDialogButton(){
        return new Button("close", new ClickHandler(){
            @Override
            public void onClick(ClickEvent ce) {
                cloneDialog.hide();
            }
        });
    }

    private Button createNewProjectButton(){
        return new Button("New Project", new ClickHandler(){
            @Override
            public void onClick(ClickEvent ce) {
                gitCommands.initializeNewRepository(saveToLocation);
                cloneDialog.hide();
            }
        });
    }

    private HorizontalPanel createHorizontalHolder(Widget leftWidget, Widget rightWidget){
        HorizontalPanel hpanel = new HorizontalPanel();
        hpanel.setSpacing(10);
        hpanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        hpanel.add(leftWidget);
        hpanel.add(rightWidget);
        return hpanel;
    }


    /**
     * Used to detect changes in the Folder Location Box
     * @param event
     */
    @Override
    public void onChange(ChangeEvent event) {
        saveToLocation = (ROOT_LOCATION + "/" +  folderNameBox.getText());
    }

    /**
     * Create the Command Object to open this dialog
     * Used for assigning to the appropriate Menu Entry
     *
     * @return Open Clone Dialog Command
     */
    public Command openDialogForNewProjectCommand(){
        return new Command() {
            @Override
            public void execute() {
                cloneDialog = createNewProjectDialog();
            }
        };
    }
}
