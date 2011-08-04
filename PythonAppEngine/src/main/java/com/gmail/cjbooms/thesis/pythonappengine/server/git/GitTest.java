package com.gmail.cjbooms.thesis.pythonappengine.server.git;

import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.RepositoryBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class GitTest{

    public GitTest(){
        RepositoryBuilder builder1 = new RepositoryBuilder();
        try {
            Repository repository = builder1.setGitDir(new File("git@github.com:cjbooms/helloworld.git")).readEnvironment().findGitDir().build();
            Map<String, Ref> tags = repository.getTags();
       /*     String head = repository.getFullBranch();
            if (head.startsWith("refs/heads/")) {
                    // Print branch name with "refs/heads/" stripped.
                    System.out.println("Current branch is " + repository.getBranch());
            }      */
            for (String key : tags.keySet()) {
                System.out.println(key+":"+tags.get(key).toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        RepositoryBuilder builder2 = new RepositoryBuilder();
        try {
                File gitDir = new File("/home/conor/gittest/.git");
                Repository repository2 = builder2.setGitDir(gitDir).readEnvironment().findGitDir().build();
                repository2.create();

            String head = repository2.getFullBranch();
            if (head.startsWith("refs/heads/")) {
                    // Print branch name with "refs/heads/" stripped.
                    System.out.println("Current branch is " + repository2.getBranch());
            }
                // ... use the new repository ...
        } catch (IllegalStateException ise) {
                System.out.println("The repository already exists");
        } catch (IOException ioe) {
            System.out.println("Failed to create the repository!");
        }

    }
    public static void main(String args[]){
        GitTest git = new GitTest();
    }
}