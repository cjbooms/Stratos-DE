package com.gmail.cjbooms.thesis.pythonappengine.client.menus.git;

import com.gmail.cjbooms.thesis.pythonappengine.client.editor.SelectionHelper;
import com.gmail.cjbooms.thesis.pythonappengine.shared.ConfigConstants;
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
public class GitPushChangesDialogWidget extends Composite{

    private DialogBox pushDialog;
    private String title = "Push Changes To Remote Repository";
    private TextBox folderNameBox;
    private SuggestBox gitURLSuggestBox;
    private String remoteLoginName;
    private String remoteLoginPassword;
    private String gitURLEntered = "";
    private String saveToLocation = "";
    private static final String rootLocation = ConfigConstants.PROJECT_ROOT;
    private GITCommands gitCommands;

    /**
     * Default Constructor
     */
    public GitPushChangesDialogWidget(){

    }

    /**
     * Create the GIT Push Dialog Window
     *
     * @return dialog The created Dialog Box
     */
    private DialogBox createPushDialog(){
        gitCommands = new GITCommands();

        DialogBox dialog = new DialogBox(true);
        dialog.setAnimationEnabled(true);
        dialog.center();
        dialog.setText(title);
        dialog.setGlassEnabled(true);

        VerticalPanel panel = new VerticalPanel();
        panel.setSpacing(5);
        panel.add(createGitURLSuggestionBox());
        panel.add(createLoginNameInput());
        panel.add(createLoginPasswordInput());
        panel.add(createExecuteAndCancelButtons());

        dialog.add(panel);
        dialog.setAutoHideEnabled(true);
        return dialog;
    }

    private HorizontalPanel createExecuteAndCancelButtons() {
        Button executeClone  = createExecutePushButton();
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

    private VerticalPanel createLoginNameInput() {
        VerticalPanel committerInput = new VerticalPanel();
        committerInput.add(new Label("Login User Name:"));
        TextBox committerNameTextBox = new TextBox();
        committerNameTextBox.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                remoteLoginName = event.getValue();
            }
        });
        committerInput.add(committerNameTextBox);
        return committerInput;
    }


    private VerticalPanel createLoginPasswordInput() {
        VerticalPanel committerInput = new VerticalPanel();
        committerInput.add(new Label("Login Password:"));
        PasswordTextBox committerNameTextBox = new PasswordTextBox();
        committerNameTextBox.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                remoteLoginPassword = event.getValue();
            }
        });
        committerInput.add(committerNameTextBox);
        return committerInput;
    }

    /**
     * Create a Suggestion Box for GIT URLS
     * TODO - Populate the suggestion oracle dynamically with previously used GIT repos
     *
     * @return
     */
    private HorizontalPanel createGitURLSuggestionBox(){
        MultiWordSuggestOracle gitURLOracle = new MultiWordSuggestOracle();
        gitURLOracle.add("https://cjbooms@github.com/cjbooms/helloworld.git");

        gitURLSuggestBox = new SuggestBox(gitURLOracle);
        Label gitURLSuggestBoxLabel = new Label("Enter The complete GIT URL");
        gitURLSuggestBox.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> stringValueChangeEvent) {
                gitURLEntered = stringValueChangeEvent.getValue();
            }
        });
        gitURLSuggestBox.addSelectionHandler(new SelectionHandler<SuggestOracle.Suggestion>() {
            @Override
            public void onSelection(SelectionEvent<SuggestOracle.Suggestion> suggestionSelectionEvent) {
                gitURLEntered = suggestionSelectionEvent.getSelectedItem().getReplacementString();
            }
        });

        return createHorizontalHolder(gitURLSuggestBox,gitURLSuggestBoxLabel);
    }


    private Button createCloseDialogButton(){
        return new Button("close", new ClickHandler(){
            @Override
            public void onClick(ClickEvent ce) {
                pushDialog.hide();
            }
        });
    }

    private Button createExecutePushButton(){
        return new Button("Push Changes", new ClickHandler(){
            @Override
            public void onClick(ClickEvent ce) {
                gitCommands.pushToRemoteRepository(SelectionHelper.getCurrentProjectPath(), gitURLEntered, remoteLoginName, remoteLoginPassword);
                pushDialog.hide();
            }
        });
    }


    /**
     * Create the Command Object to open this dialog
     * Used for assigning to the appropriate Menu Entry
     *
     * @return Open Push Dialog Command
     */
    public Command openDialogForGITPushCommand(){
        return new Command() {
            @Override
            public void execute() {
                pushDialog = createPushDialog();
            }
        };
    }


}
