/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.cjbooms.thesis.pythonappengine.client.filebrowser.widgets;

import com.gmail.cjbooms.thesis.pythonappengine.client.editor.SourceCodeEditor;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.OpenHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.gmail.cjbooms.thesis.pythonappengine.client.filebrowser.FileSystemService;
import com.gmail.cjbooms.thesis.pythonappengine.client.filebrowser.FileSystemServiceAsync;
import com.gmail.cjbooms.thesis.pythonappengine.client.filebrowser.FileWrapper;
import com.gmail.cjbooms.thesis.pythonappengine.client.filebrowser.FileType;

/**
 *
 * @author firas
 */
public class FileSystemTreeWidget extends Composite {

    private Tree tree;
   // private FileTableWidget table;
    private SourceCodeEditor editor;
    private String treeTitle;
    private HTML footer;
    private FileSystemServiceAsync fileSystemSvc = GWT.create(FileSystemService.class);


/*    public FileSystemTreeWidget(String treeTitle, FileTableWidget table, HTML footer) {
        this.table = table;
        this.treeTitle = treeTitle;
        this.footer=footer;
        tree = createFileSystemTree();
        
        initWidget(tree);
    }*/


    public FileSystemTreeWidget(String treeTitle, SourceCodeEditor editor, HTML footer) {
        this.editor = editor;
        this.treeTitle = treeTitle;
        this.footer=footer;
        tree = createFileSystemTree();

        initWidget(tree);
    }


    private Tree createFileSystemTree() {
        tree = new Tree();
        tree.setAnimationEnabled(true);
        TreeItem tItem = new TreeItem(treeTitle);
        tItem.addItem("Loading...");
        tree.addOpenHandler(getOpenHandler());
        tree.addSelectionHandler(getSelectionHandler());
        tree.addItem(tItem);
        tree.setSelectedItem(tItem, true);
        return tree;
    }

    private OpenHandler getOpenHandler() {
        return new OpenHandler() {
            @Override
            public void onOpen(OpenEvent oe) {
                GWT.log("Tree Item openned");
                TreeItem targetItem = (TreeItem) oe.getTarget();
                // stupid but would be usefull.
                if (targetItem.getChildCount() > 1) {
                    return;
                }
                String path = findPath(targetItem);
                fetchTreeItems(targetItem, path);
            }
        };
    }

    private SelectionHandler getSelectionHandler() {
        return new SelectionHandler() {
            @Override
            public void onSelection(SelectionEvent se) {
                TreeItem selectedItem = (TreeItem) se.getSelectedItem();
                String path = findPath(selectedItem);
                fetchTableItems(path);
                footer.setHTML("Current path : "+path);
            }
        };
    }

    private String findPath(TreeItem item) {
        TreeItem parent = item.getParentItem();

        if (parent == null) {
            return "/";
        } else {
            return findPath(parent) + "/" + item.getText();
        }
    }

    private void fetchTreeItems(final TreeItem father, String path) {
        if (this.fileSystemSvc == null) {
            fileSystemSvc = GWT.create(FileSystemService.class);
        }
        AsyncCallback<FileWrapper[]> callback = new AsyncCallback<FileWrapper[]>() {

            @Override
            public void onFailure(Throwable thrwbl) {
                //Something wrong. to be handled.
            }
            @Override
            public void onSuccess(FileWrapper[] results) {
                expandTreeItem(father, results);
            }
        };
        fileSystemSvc.getContents(path, callback);
    }

    private void fetchTableItems(String path) {
        if (this.fileSystemSvc == null) {
            fileSystemSvc = GWT.create(FileSystemService.class);
        }
        AsyncCallback<FileWrapper[]> callback = new AsyncCallback<FileWrapper[]>() {

            @Override
            public void onFailure(Throwable thrwbl) {
                //Something wrong. to be handled.
            }
            @Override
            public void onSuccess(FileWrapper[] results) {
                //todo Update Edit Area with Contents of Selected File
             //   table.updateTableContent(results);
            }
        };
        fileSystemSvc.getContents(path, callback);
    }

    private void expandTreeItem(TreeItem father, FileWrapper[] files) {
        father.removeItems();
        for (FileWrapper file : files) {
            if (file.getKind() == FileType.DIR) {
                TreeItem newItem = new TreeItem(file.getName());
                father.addItem(newItem);
                newItem.addItem("Loading...");
            }

        }
    }
}
