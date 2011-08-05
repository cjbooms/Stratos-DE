package git;

import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand;
import org.eclipse.jgit.lib.*;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GitTest{

    public GitTest(){

    }

    public void testServerSideCreateRepository(){

        RepositoryBuilder builder2 = new RepositoryBuilder();
        try {
                File gitDir = new File("/home/conor/gittest/CREATE_TEST/.git");
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

	public void testCloneRepositoryOverHTTP() {
		try {
            File directory =  new File("/home/conor/gittest/HTTP_Test/.git");
            CloneCommand command = Git.cloneRepository();
            command.setDirectory(directory);
            command.setURI("http://github.com/schacon/grack.git");
            Git git2 = command.call();
            Repository repository = git2.getRepository();
            Map<String, Ref> tags = repository.getTags();
            for (String key : tags.keySet()) {
                System.out.println(key+":"+tags.get(key).toString());
            }

        } catch (Exception e) {
            System.out.println("Failed to create the repository!");
        }
	}

 	public void testCloneRepositoryOverHTTPS() {
		try {
            File directory =  new File("/home/conor/gittest/HTTPS_Test/.git");
            CloneCommand command = Git.cloneRepository();
            command.setDirectory(directory);
            command.setURI("https://cjbooms@github.com/cjbooms/helloworld.git");
            Git git2 = command.call();
            Repository repository = git2.getRepository();
            Map<String, Ref> tags = repository.getTags();
            for (String key : tags.keySet()) {
                System.out.println(key+":"+tags.get(key).toString());
            }

        } catch (Exception e) {
            System.out.println("Failed to create the repository!");
        }
	}

    public static void main(String args[]){
        GitTest gitTest = new GitTest();
        gitTest.testCloneRepositoryOverHTTP();
        gitTest.testServerSideCreateRepository();
        gitTest.testCloneRepositoryOverHTTPS();
    }
}