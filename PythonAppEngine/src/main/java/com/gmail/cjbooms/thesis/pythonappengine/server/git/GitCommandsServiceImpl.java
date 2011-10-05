/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gmail.cjbooms.thesis.pythonappengine.server.git;

import com.gmail.cjbooms.thesis.pythonappengine.client.menus.GitCommandsService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.InitCommand;
import org.eclipse.jgit.api.errors.*;
import org.eclipse.jgit.errors.UnmergedPathException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.RepositoryBuilder;

import java.io.File;
import java.io.IOException;

/**
 * User: conor
 */
public class GitCommandsServiceImpl extends RemoteServiceServlet implements GitCommandsService{


    /** Create a blank GIT repository on the local file system
     * TODO - Remove, using init API call in initializeNewRepository() instead
     *
     * @param filePath
     * @throws IOException
     */
    public boolean createRepository(String filePath) throws IOException {
        RepositoryBuilder builder2 = new RepositoryBuilder();
                File gitDir = new File(filePath);
                Repository repository2 = builder2.setGitDir(gitDir).readEnvironment().findGitDir().build();
                repository2.create();

        return true;

    }

    /** Clone a repository from a HTTP location
     * URL should be of the form:
     * "http://github.com/schacon/grack.git"
     *
     * @param filePath
     * @param gitHttpURL
     */
    public void cloneRepositoryOverHTTP(String filePath, String gitHttpURL) throws JGitInternalException {
        File directory = new File(filePath);
        CloneCommand command = Git.cloneRepository();
        command.setDirectory(directory);
        command.setURI(gitHttpURL);
        command.call();
    }

    /**
     * Initialize a new empty GIT repository
     *
     * @param pathToNewRepository Location to build new Repo
     * @throws IOException, JGitInternalException
     */
	public void initializeNewRepository(String pathToNewRepository) throws IOException, JGitInternalException {
		File directory = new File(pathToNewRepository);
		InitCommand command = new InitCommand();
		command.setDirectory(directory);
		command.call();
	}


    /**
     * Add file to Local Repository
     *
     * @param pathToRepository Root Location Of Repository or Project
     * @param fileNameToAdd File name with extension of or directory to be committed
     * @throws IOException, JGitInternalException, NoFilepatternException
     */
	public void addFileToRepository(String pathToRepository, String fileNameToAdd)
            throws IOException, JGitInternalException {

        File repositoryDirectory = new File(pathToRepository);
		Git repository = Git.open(repositoryDirectory);
        try {
            repository.add().addFilepattern(fileNameToAdd).call();
        } catch (NoFilepatternException e) {
            throw new JGitInternalException(e.getMessage());
        }
    }

    /**
     * Commit Changes to local Repository
     *
     * @param pathToRepository Root Location Of Repository or Project
     * @param logMessage The log message for this commit
     * @param committerName The name of the committer
     * @param committerEmail The email address of the committer
     * @throws IOException, JGitInternalException, NoFilepatternException
     */
	public void commitChangesToLocalRepository(String pathToRepository, String logMessage,
            String committerName, String committerEmail) throws IOException, GitAPIException {

        File repositoryDirectory = new File(pathToRepository);
		Git repository = Git.open(repositoryDirectory);
        repository.commit().setMessage(logMessage).setCommitter(committerName,committerEmail).setAll(true).call();
	}

}