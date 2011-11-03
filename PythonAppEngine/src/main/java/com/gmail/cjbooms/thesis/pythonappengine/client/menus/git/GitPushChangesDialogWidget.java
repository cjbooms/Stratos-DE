package com.gmail.cjbooms.thesis.pythonappengine.client.menus.git;

import com.gmail.cjbooms.thesis.pythonappengine.client.editor.SelectionHelper;
import com.gmail.cjbooms.thesis.pythonappengine.client.menus.AbstractMenuDialog;
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
public class GitPushChangesDialogWidget extends AbstractMenuDialog {

    private SuggestBox gitURLSuggestBox;

    /**
     * Default Constructor
     */
    public GitPushChangesDialogWidget(){
        super();
        title = "Push Changes To Remote Repository";

    }

    /**
     * Add Custom Widgets to a vertical Panel.
     * Used to customize Dialog widget
     * @param panel, the panel to add widgets to
     */
    @Override
    protected void addCustomWidgetsToDialogPanel(VerticalPanel panel) {
        panel.add(createGitURLSuggestionBox());
        panel.add(createLoginNameInput());
        panel.add(createLoginPasswordInput());
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

    @Override
    protected Button createCloseDialogButton(){
        return new Button("close", new ClickHandler(){
            @Override
            public void onClick(ClickEvent ce) {
                dialog.hide();
            }
        });
    }

    @Override
    protected Button createExecutePushButton(){
        return new Button("Push Changes", new ClickHandler(){
            @Override
            public void onClick(ClickEvent ce) {
                String projectDirectory = SelectionHelper.getCurrentProjectDirectory();
                gitCommands.pushToRemoteRepository(projectDirectory, gitURLEntered, remoteLoginName, remoteLoginPassword);
                dialog.hide();
            }
        });
    }





}
