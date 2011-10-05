/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.cjbooms.thesis.pythonappengine.client.filebrowser;

import com.gmail.cjbooms.thesis.pythonappengine.client.editor.SourceCodeEditor;
import com.gmail.cjbooms.thesis.pythonappengine.shared.ConfigConstants;
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

/**
 * Builds the server side representation of the file tree hierarchy
 */
public class FileSystemTreeWidget extends Composite {

    private Tree tree;
    private SourceCodeEditor editor;
    private String treeTitle;
    private HTML footer;
    private FileSystemServiceAsync fileSystemSvc = GWT.create(FileSystemService.class);
    private static final String rootFolder = ConfigConstants.projectRoot;

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
                GWT.log("Tree Item opened");
                TreeItem targetItem = (TreeItem) oe.getTarget();
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
                //TODO instead of finding path, find the contents if a document
                String path = findPath(selectedItem);
                getFileContentsAsString(path);
                footer.setHTML("Current path : "+path);
            }


        };
    }


    /**
     * Use the RPC to get the file contents form the path in String form
     * Set
     * @param path
     */
    private void getFileContentsAsString(String path) {
        if (this.fileSystemSvc == null) {
            fileSystemSvc = GWT.create(FileSystemService.class);
        }
        editor.setFilePath(path);
        AsyncCallback<String> callback = new AsyncCallback<String>() {

            @Override
            public void onFailure(Throwable thrwbl) {
                //Something wrong. to be handled.
            }
            @Override
            public void onSuccess(String contents) {
               editor.setFileContents(contents);

            }
        };
        fileSystemSvc.getFileContents(path, callback);
    }

    private String findPath(TreeItem item) {
        TreeItem parent = item.getParentItem();
        //TODO - Dynamically set folder root at user login
        if (parent == null) {
            return rootFolder;
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
        fileSystemSvc.getDirectoryContents(path, callback);
    }


    /**
     * Handle Tree Expansion. Add new branch nodes if directory, otherwise add as leaf nodes
     * @param father
     * @param files
     */
    private void expandTreeItem(TreeItem father, FileWrapper[] files) {
        father.removeItems();
        for (FileWrapper file : files) {
            // Add new branch node
            if (file.getKind() == FileType.DIR) {
                TreeItem newItem = new TreeItem(file.getName());
                father.addItem(newItem);
                newItem.addItem("Loading...");
            }
            // Add Leaf
            else{
                father.addItem(file.getName());
            }
        }
    }
}
