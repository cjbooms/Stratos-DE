package com.gmail.cjbooms.thesis.pythonappengine.client.menus;

import com.gmail.cjbooms.thesis.pythonappengine.client.menus.appengine.DeployDialog;
import com.gmail.cjbooms.thesis.pythonappengine.client.menus.file.NewFileDialog;
import com.gmail.cjbooms.thesis.pythonappengine.client.menus.git.CreateNewProjectDialogWidget;
import com.gmail.cjbooms.thesis.pythonappengine.client.menus.git.GitCloneDialogWidget;
import com.gmail.cjbooms.thesis.pythonappengine.shared.MyResources;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;

import java.util.ArrayList;

/**
 * User: Conor Gallagher
 * Date: 10/12/11
 * Time: 00:45
 */
public class Toolbar extends Composite {
    MyResources resources;
    private HorizontalPanel bar;
    private ArrayList<Widget> buttons;

    /**
     * Constructs a Toolbar part.
     */
    public Toolbar() {
        resources = GWT.create(MyResources.class);
        buttons = new ArrayList<Widget>();
        bar = new HorizontalPanel();
        bar.setHeight("30px");
        bar.setWidth("100%");
        bar.setStylePrimaryName("lab-Tools-Panel");
        bar.add(buildToolBar());
        initWidget(bar);
    }

    public void setButtonState(String title, boolean down) {
        int i = getButtonIndex(title);
        if (i >= 0) {
            setButtonState(i, down);
        }
    }

    public void setButtonState(int index, boolean down) {
        Widget btn = buttons.get(index);
        if (btn instanceof ToggleButton) {
            ToggleButton tbtn = (ToggleButton) btn;
            tbtn.setDown(down);
        }
    }

    public void setButtonEnabled(String title, boolean down) {
        int i = getButtonIndex(title);
        if (i >= 0) {
            setButtonEnabled(i, down);
        }
    }

    public void setButtonEnabled(int index, boolean enabled) {
        Widget btn = buttons.get(index);
        if (btn != null) {
            if (enabled) {
                btn.removeStyleDependentName("Disabled");
            } else {
                btn.addStyleDependentName("Disabled");
            }
        }
    }

    private int getButtonIndex(String title) {
        for (int i = 0; i < buttons.size(); i++) {
            Widget w = buttons.get(i);
            if (w.getTitle().equals(title)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Builds the main toolbar.
     *
     * @return the main toolbar
     */
    private HorizontalPanel buildToolBar() {
        HorizontalPanel toolbarPanel = new HorizontalPanel();
        toolbarPanel.setVerticalAlignment(HorizontalPanel.ALIGN_MIDDLE);
        toolbarPanel.setStyleName("lab-Toolbar");
        toolbarPanel.add(buildButton(resources.blankFile(), "New Project", new CreateNewProjectDialogWidget().openDialogForNewProjectCommand()));
        toolbarPanel.add(buildButton(resources.openIcon(), "Open Project", createBlankCommand()));
        toolbarPanel.add(buildButton(resources.saveIcon(), "Save Project", createBlankCommand()));
        toolbarPanel.add(buildSeparator());
        toolbarPanel.add(buildButton(resources.refreshIcon(), "Refresh", createBlankCommand()));
        toolbarPanel.add(buildSeparator());
        toolbarPanel.add(buildButton(resources.copyIcon(), "Clone Project", (new GitCloneDialogWidget().openDialogForGITCloneCommand())));
        toolbarPanel.add(buildSeparator());
        toolbarPanel.add(buildButton(resources.uploadIcon(), "Deploy Project", (new DeployDialog().openMenuDialog())));

        return toolbarPanel;
    }

    /**
     * Builds a toolbar button.
     *
     * @param imageResource the button's icon
     * @param title         the button's tooltip text
     * @param command       the command to execute
     * @return a toolbar button
     */
    private Widget buildButton(ImageResource imageResource, String title,
                               final Command command) {

        final PushButton btn = new PushButton(new Image(imageResource));
        btn.setTitle(title);
        btn.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                btn.setFocus(false);
                btn.removeStyleName("gwt-ToggleButton-up-hovering");
                command.execute();
            }
        });
        buttons.add(btn);
        return btn;
    }

        private Command createBlankCommand() {
        return new Command() {
            @Override
            public void execute() {
                new OperationResultDialog("This Command has not yet been implemented!");
            }
        };
    }

  private Image buildSeparator() {
	Image sep = new Image(resources.buttonSeparator());
	sep.addStyleName("separator");
    return sep;
  }
}
