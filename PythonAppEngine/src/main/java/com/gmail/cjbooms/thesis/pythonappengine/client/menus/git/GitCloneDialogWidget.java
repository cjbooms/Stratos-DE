package com.gmail.cjbooms.thesis.pythonappengine.client.menus.git;

import com.gmail.cjbooms.thesis.pythonappengine.shared.ConfigConstants;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.*;

/**
 * User: conor
 * Date: 19/09/11
 * Time: 21:43
 */
public class GitCloneDialogWidget extends Composite implements ChangeHandler, ValueChangeHandler, SelectionHandler{

    private DialogBox cloneDialog;
    private String title = "Clone Repository";
    private TextBox folderNameBox;
    private SuggestBox gitURLSuggestBox;

    private String gitURLEntered = "";
    private String saveToLocation = "";
    private static final String rootLocation = ConfigConstants.PROJECT_ROOT;
    private GITCommands gitCommands;

    /**
     * Default Constructor
     */
    public GitCloneDialogWidget(){

    }

    /**
     * Create the GIT Clone Dialog Window
     *
     * @return dialog The created Dialog Box
     */
    private DialogBox createCloneDialog(){
        gitCommands = new GITCommands();

        //TODO Add input stuff to vertical panel
        DialogBox dialog = new DialogBox(true);
        dialog.setAnimationEnabled(true);
        dialog.center();
        dialog.setText(title);
        dialog.setGlassEnabled(true);

        VerticalPanel panel = new VerticalPanel();
        panel.setSpacing(5);
        //panel.add(createHtmlContent());
        panel.add(createGitURLSuggestionBox());
        panel.add(createFolderNameTextBox());
        panel.add(createExecuteAndCancelButtons());

        dialog.add(panel);
        dialog.setAutoHideEnabled(true);
        return dialog;
    }

    private HorizontalPanel createExecuteAndCancelButtons() {
        Button executeClone  = createExecuteCloneButton();
        Button closeDialog = createCloseDialogButton();
        return createHorizontalHolder(closeDialog, executeClone);

    }

    private HorizontalPanel createHorizontalHolder(Widget leftWidget, Widget rightWidget){
        HorizontalPanel hpanel = new HorizontalPanel();
        hpanel.setSpacing(10);
        hpanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        hpanel.add(leftWidget);
        hpanel.add(rightWidget);
        return hpanel;
    }

    private HorizontalPanel createFolderNameTextBox() {
        folderNameBox = new TextBox();
        Label folderNameBoxLabel = new Label("Enter Destination Folder");
        folderNameBox.addChangeHandler(this);
        folderNameBox.setText("NEWFOLDER");
        saveToLocation = (rootLocation + folderNameBox.getText());
        return createHorizontalHolder(folderNameBox, folderNameBoxLabel);
    }

    /**
     * Create a Suggestion Box for GIT URLS
     * TODO - Populate the suggestion oracle dynamically with previously used GIT repos
     *
     * @return
     */
    private HorizontalPanel createGitURLSuggestionBox(){
        MultiWordSuggestOracle gitURLOracle = new MultiWordSuggestOracle();
        gitURLOracle.add("http://github.com/schacon/grack.git");

        gitURLSuggestBox = new SuggestBox(gitURLOracle);
        Label gitURLSuggestBoxLabel = new Label("Enter The complete GIT URL");
        gitURLSuggestBox.addValueChangeHandler(this);
        gitURLSuggestBox.addSelectionHandler(this);

        return createHorizontalHolder(gitURLSuggestBox,gitURLSuggestBoxLabel);
    }


    private Button createCloseDialogButton(){
        return new Button("close", new ClickHandler(){
            @Override
            public void onClick(ClickEvent ce) {
                cloneDialog.hide();
            }
        });
    }

    private Button createExecuteCloneButton(){
        return new Button("Clone Repository", new ClickHandler(){
            @Override
            public void onClick(ClickEvent ce) {
                //TODO - Implement Clone
                gitCommands.cloneGITRepositoryOverHttp(saveToLocation,gitURLEntered);
                cloneDialog.hide();
            }
        });
    }

    //TODO - not used anymore, use or delete
    private static HTML createHtmlContent(){
        StringBuilder sb = new StringBuilder();
        sb.append("<p>");
        sb.append("This is the GIT Clone Dialog, Replace with input text Boxes :");
        return new HTML(sb.toString());
    }

    /**
     * Create the Command Object to open this dialog
     * Used for assigning to the appropriate Menu Entry
     *
     * @return Open Clone Dialog Command
     */
    public Command openDialogForGITCloneCommand(){
        return new Command() {
            @Override
            public void execute() {
                cloneDialog = createCloneDialog();
            }
        };
    }

    /**
     * Used to detect changes in the Folder Location Box
     * @param event
     */
    @Override
    public void onChange(ChangeEvent event) {
        saveToLocation = (rootLocation + folderNameBox.getText());
    }

    /**
     * Used to detect changes in the GIT URL Suggestion Box
     * @param valueChangeEvent
     */
    @Override
    public void onValueChange(ValueChangeEvent valueChangeEvent) {
        gitURLEntered = gitURLSuggestBox.getText();

    }

    @Override
    public void onSelection(SelectionEvent selectionEvent) {
        gitURLEntered = gitURLSuggestBox.getText();
    }
}
