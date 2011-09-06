/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gmail.cjbooms.thesis.pythonappengine.server.git;

import com.gmail.cjbooms.thesis.pythonappengine.client.menus.GitCommandsService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.RepositoryBuilder;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author firas
 */
public class GitCommandsServiceImpl extends RemoteServiceServlet implements GitCommandsService{


    /** Create a blank GIT repository on the local file system
     *
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
    public boolean cloneRepositoryOverHTTP(String filePath, String gitHttpURL) {
        File directory = new File(filePath);
        CloneCommand command = Git.cloneRepository();
        command.setDirectory(directory);
        command.setURI(gitHttpURL);
        Git git2 = command.call();
        Repository repository = git2.getRepository();
        //Map<String, Ref> tags = repository.getTags();
        //for (String key : tags.keySet()) {
        //    System.out.println(key+":"+tags.get(key).toString());
        //}

        return true;


    }


    /** Clone a repository from a HTTPS location
     * URL should be of the form:
     * "https://cjbooms@github.com/cjbooms/helloworld.git"
     *
     * TODO: Refactor so user name and password informatiuon can be passed as separate parameters - Testing Needed
     *
     * @param filePath
     * @param gitHttpsURL
     */
    public boolean cloneRepositoryOverHTTPS(String filePath, String gitHttpsURL) {
        File directory = new File(filePath);
        CloneCommand command = Git.cloneRepository();
        command.setDirectory(directory);
        command.setURI(gitHttpsURL);
        Git git2 = command.call();
        Repository repository = git2.getRepository();
/*            Map<String, Ref> tags = repository.getTags();
            for (String key : tags.keySet()) {
                System.out.println(key+":"+tags.get(key).toString());
            }*/

        return true;
    }
}