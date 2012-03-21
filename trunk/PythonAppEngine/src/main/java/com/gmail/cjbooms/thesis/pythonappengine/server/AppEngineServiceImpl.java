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

package com.gmail.cjbooms.thesis.pythonappengine.server;

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
            String login = EMAIL + email;
            String output;
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

            StringBuilder buf = new StringBuilder();
            String line;
            while ((line = input.readLine()) != null)
            {
                buf.append(line + "\n");
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
