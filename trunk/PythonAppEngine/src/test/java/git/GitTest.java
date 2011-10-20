package git;

import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.lib.*;
import org.eclipse.jgit.transport.*;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GitTest {

    public GitTest() {

    }

    public void testServerSideCreateRepository() {

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
            File directory = new File("/home/conor/gittest/HTTP_Test/.git");
            CloneCommand command = Git.cloneRepository();
            command.setDirectory(directory);
            command.setURI("http://github.com/schacon/grack.git");
            Git git2 = command.call();
            Repository repository = git2.getRepository();
            Map<String, Ref> tags = repository.getTags();
            for (String key : tags.keySet()) {
                System.out.println(key + ":" + tags.get(key).toString());
            }

        } catch (Exception e) {
            System.out.println("Failed to create the repository!");
        }
    }

    public void testCloneRepositoryOverHTTPS() {
        try {
            File directory = new File("/home/conor/gittest/HTTPS_Test");
            CloneCommand command = Git.cloneRepository();
            command.setDirectory(directory);
            command.setURI("https://cjbooms@github.com/cjbooms/helloworld.git");
            Git git2 = command.call();
            Repository repository = git2.getRepository();
            Map<String, Ref> tags = repository.getTags();
            for (String key : tags.keySet()) {
                System.out.println(key + ":" + tags.get(key).toString());
            }

        } catch (Exception e) {
            System.out.println("Failed to create the repository!");
        }
    }


    public void testCommitRepository() {
        try {
            File repositoryDirectory = new File("/home/conor/gittest/HTTPS_Test");
            Git repository = Git.open(repositoryDirectory);
            repository.commit().setMessage("Message Number Two").setCommitter("Conor", "cjbooms@gmail.com").setAll(true).call();

        } catch (Exception e) {
            System.out.println("Failed to create the repository!");
        }
    }

    public void testPushRepositoryOverHTTPS() {
        try {

            File directory = new File("/home/conor/gittest/HTTPS_Test");
            Git localGit = null;
            localGit = Git.open(directory);
            Repository localRepository = localGit.getRepository();

            final StoredConfig localRepoConfig = localGit.getRepository().getConfig();
            RemoteConfig remoteConfig = null;
            remoteConfig = new RemoteConfig(localRepoConfig, "github");

            URIish remoteURI = null;
            remoteURI = new URIish("https://cjbooms@github.com/cjbooms/helloworld.git");

            remoteConfig.addURI(remoteURI);
            remoteConfig.addPushRefSpec(new RefSpec("+refs/heads/*:refs/heads/*"));
            remoteConfig.update(localRepoConfig);
            localRepoConfig.save();

		Transport t = Transport.open(localGit.getRepository(), remoteURI);
		((TransportHttp) t).setUseSmartHttp(true);
            RemoteRefUpdate remoteRefUpdate = new RemoteRefUpdate(localRepository,localRepository.getRef("master"),"refs/heads/master",true,"refs/heads/master",null);
		    t.setCredentialsProvider(new UsernamePasswordCredentialsProvider("cjbooms", "XXXXX"));
            t.push(NullProgressMonitor.INSTANCE, Collections.singleton(remoteRefUpdate));


        } catch (IOException e) {
            System.out.println("IOException!");
        } catch (URISyntaxException e) {
            System.out.println("URISyntaxException!");
        }
    }

    public static void main(String args[]) {
        GitTest gitTest = new GitTest();
        //  gitTest.testCloneRepositoryOverHTTP();
        //   gitTest.testServerSideCreateRepository(); .

        //    gitTest.testCloneRepositoryOverHTTPS();
        gitTest.testCommitRepository();
        gitTest.testPushRepositoryOverHTTPS();


    }
}