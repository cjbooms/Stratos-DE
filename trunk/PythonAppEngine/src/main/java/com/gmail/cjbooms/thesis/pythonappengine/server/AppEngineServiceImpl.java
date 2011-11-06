package com.gmail.cjbooms.thesis.pythonappengine.server;

import com.gmail.cjbooms.thesis.pythonappengine.client.PythonAppEngine;
import com.gmail.cjbooms.thesis.pythonappengine.client.appengine.AppEngineService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.io.*;

/**
 * User: Conor Gallagher
 * Date: 02/11/11
 * Time: 22:42
 */
public class AppEngineServiceImpl extends RemoteServiceServlet implements AppEngineService {

    private static final String PYTHON = "/usr/bin/python2.5";
    private static final String DEPLOY = "/home/webapp/google_appengine/appcfg.py";
    private static final String EMAIL = "--email=";
    private static final String PASSIN = "--passin";
    private static final String UPDATE = "update";




    @Override
    public String deployToAppEngine(String email, String password, String projectPath) {
        try
        {
            String eol = System.getProperty("line.separator");
            String login = EMAIL + email;
            String output = "";
            ProcessBuilder builder = new ProcessBuilder(PYTHON, DEPLOY, login, PASSIN, UPDATE, projectPath);
            builder.redirectErrorStream(true);

            // start process and get output via BufferedReader
            Process process = builder.start();
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            out.write(password);
            out.newLine();
            out.flush();
            out.close();

            StringBuffer buf = new StringBuffer();
            String line;
            while ((line = input.readLine()) != null)
            {
                buf.append(line);
            }
            output = buf.toString();

            process.waitFor();
            input.close();
            return output;
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
            //TODO Create a custom serializable checked exception and forward to client
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
            //TODO Create a custom serializable checked exception and forward to client
        }
    }
}
