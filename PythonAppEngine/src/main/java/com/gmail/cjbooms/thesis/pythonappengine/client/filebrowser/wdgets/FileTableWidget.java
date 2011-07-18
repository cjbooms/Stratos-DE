/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.cjbooms.thesis.pythonappengine.client.filebrowser.wdgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.gmail.cjbooms.thesis.pythonappengine.client.filebrowser.FileSystemService;
import com.gmail.cjbooms.thesis.pythonappengine.client.filebrowser.FileSystemServiceAsync;
import com.gmail.cjbooms.thesis.pythonappengine.client.filebrowser.FileType;
import com.gmail.cjbooms.thesis.pythonappengine.client.filebrowser.FileWrapper;

/**
 *
 * @author firas
 */
public class FileTableWidget extends Composite {

    private FlexTable table;
    private FileSystemServiceAsync fileSystemSvc = GWT.create(FileSystemService.class);
    
    public FileTableWidget() {
        table = createFileTable();
        initWidget(table);
    }

    private FlexTable createFileTable() {
        FlexTable fileTable = new FlexTable();
        createTableHeader(fileTable);
        return fileTable;
    }

    private void createTableHeader(FlexTable table) {
        DOM.setElementAttribute(table.getElement(), "id", "files-table");
        table.setWidget(0, 0, new CheckBox());
        table.setText(0, 1, "Name");
        table.setText(0, 2, "Modified at");
        table.setText(0, 3, "Kind");
        table.setText(0, 4, "Control");
        table.getCellFormatter().addStyleName(0, 0, "files-table-small-column files-table-header-cell");
        table.getCellFormatter().addStyleName(0, 1, "files-table-column files-table-header-cell");
        table.getCellFormatter().addStyleName(0, 2, "files-table-column files-table-header-cell");
        table.getCellFormatter().addStyleName(0, 3, "files-table-column files-table-header-cell");
        table.getCellFormatter().addStyleName(0, 4, "files-table-column files-table-header-cell");
    }

    public void updateTableContent(FileWrapper[] files) {
        cleanupTableContent();
        for (FileWrapper file : files) {
            insertFileRow(file);
        }
    }

    private void insertFileRow(FileWrapper file) {
        int numRows = table.getRowCount();
        table.setWidget(numRows, 0, new CheckBox());
        table.setWidget(numRows, 1, getFileIconedName(file.getName(), file.getKind()));
        table.setText(numRows, 2, file.getModified());
        table.setText(numRows, 3, file.getKind().toString());
        FlowPanel controlPanel = new FlowPanel();
        controlPanel.add(createDeleteButton(numRows, file));
        controlPanel.add(new Button("Download"));
        table.setWidget(numRows, 4, controlPanel);
        table.getCellFormatter().addStyleName(numRows, 0, "files-table-small-column");
        if (numRows % 2 == 0) {
            table.getRowFormatter().addStyleName(numRows, "files-table-odd");
        }
    }

    private Button createDeleteButton(final int rowNumber, final FileWrapper file) {
        return new Button("Delete", new ClickHandler() {
            @Override
            public void onClick(ClickEvent ce) {
                if(!Window.confirm("Are you sure you want to delete : "+file.getName())){
                    return;
                }
                if (fileSystemSvc == null) {
                    fileSystemSvc = GWT.create(FileSystemService.class);
                }
                AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {

                    @Override
                    public void onFailure(Throwable thrwbl) {
                        //Something wrong. to be handled.
                    }

                    @Override
                    public void onSuccess(Boolean deleted) {
                        if(deleted){
                            table.removeRow(rowNumber);
                        }else{
                            Window.alert("ERROR : File is not deleted!");
                        }
                    }
                };
                fileSystemSvc.deleteFile(file.getPath(), callback);
            }
        });
    }

    private void cleanupTableContent() {
        int numRows = table.getRowCount();
        while (numRows > 1) {
            table.removeRow(numRows - 1);
            numRows--;
        }
    }

    private HTML getFileIconedName(String name, FileType type) {
        if (type == FileType.DIR) {
            return new HTML(
                    "<img src='/imgs/icon_folder.gif' />"
                    + "<span>  " + name + "</span>");
        }

        if (type == FileType.MM) {
            return new HTML(
                    "<img src='/imgs/icon_mm.gif' />"
                    + "<span>  " + name + "</span>");
        }
        if (type == FileType.IMG) {
            return new HTML(
                    "<img src='/imgs/icon_img.png' />"
                    + "<span>  " + name + "</span>");
        }
        if (type == FileType.DOC) {
            return new HTML(
                    "<img src='/imgs/icon_doc.gif' />"
                    + "<span>  " + name + "</span>");
        }
        return new HTML(
                "<img src='/imgs/icon_other.png' />"
                + "<span>  " + name + "</span>");
    }
}
