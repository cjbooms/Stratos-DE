/*
 * Copyright (c) 2012 Conor Gallagher
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gmail.cjbooms.thesis.pythonappengine.server.git;

import com.gmail.cjbooms.thesis.pythonappengine.client.menus.git.GitCommandsService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.InitCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.JGitInternalException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.lib.NullProgressMonitor;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.*;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;

/**
 * User: conor
 */
public class GitCommandsServiceImpl extends RemoteServiceServlet implements GitCommandsService {


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
     * @throws IOException, GitAPIException
     */
	public void commitChangesToLocalRepository(String pathToRepository, String logMessage,
            String committerName, String committerEmail) throws IOException, JGitInternalException{

        File repositoryDirectory = new File(pathToRepository);
		Git repository = Git.open(repositoryDirectory);
        try {
            repository.commit().setMessage(logMessage).setCommitter(committerName,committerEmail).setAll(true).call();
        } catch (GitAPIException e) {
            throw new JGitInternalException(e.getMessage());
        }
    }


    /**
     * Push Changes to a Remote Repository
     *
     * @param pathToLocalRepository Root Location Of Repository or Project
     * @param remoteRepoURL The URL of the Remote Repository to push to
     * @param userName The remote login user name
     * @param password The remote login password
     * @throws IOException, GitAPIException, URISyntaxException
     */
	public void pushLocalCommitsToRemoteRepository(String pathToLocalRepository, String remoteRepoURL,
            String userName, String password) throws IOException {

        File repositoryDirectory = new File(pathToLocalRepository);
		Git localGitRepositoryRef = Git.open(repositoryDirectory);
        Repository localRepository = localGitRepositoryRef.getRepository();

        URIish remoteURI = null;
        try {
            remoteURI = new URIish(remoteRepoURL);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e.getMessage());
        }
        Transport t = Transport.open(localRepository, remoteURI);
        ((TransportHttp) t).setUseSmartHttp(true);
        RemoteRefUpdate remoteRefUpdate = new RemoteRefUpdate(
                localRepository,localRepository.getRef("master"),"refs/heads/master",true,"refs/heads/master",null);
        t.setCredentialsProvider(new UsernamePasswordCredentialsProvider(userName, password));
        t.push(NullProgressMonitor.INSTANCE, Collections.singleton(remoteRefUpdate));

    }


    /**
     * Pull changes from Remote Repository
     *
     * @param pathToLocalRepository Root Location Of Repository or Project
     * @param remoteRepoURL The URL of the Remote Repository to push to
     * @param userName The remote login user name
     * @param password The remote login password
     * @throws IOException, GitAPIException, URISyntaxException
     */
	public void pullChangesFromRemoteRepository(String pathToLocalRepository, String remoteRepoURL,
            String userName, String password) throws IOException {

        File repositoryDirectory = new File(pathToLocalRepository);
		Git localGitRepositoryRef = Git.open(repositoryDirectory);
        Repository localRepository = localGitRepositoryRef.getRepository();

        URIish remoteURI = null;
        try {
            remoteURI = new URIish(remoteRepoURL);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e.getMessage());
        }
        Transport t = Transport.open(localRepository, remoteURI);
        ((TransportHttp) t).setUseSmartHttp(true);
        t.setCredentialsProvider(new UsernamePasswordCredentialsProvider(userName, password));
        RefSpec refSpec = new RefSpec("+refs/heads/*:refs/heads/*");

        t.fetch(NullProgressMonitor.INSTANCE, Collections.singleton(refSpec));

    }
}