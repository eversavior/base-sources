package core;

import lombok.extern.java.Log;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Created by Asfel on 04.03.2015.
 */
@Log
public class AppStarter
{
    public static final void restart()
    {
        final String javaBin = "java";//= System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
        String path = null;

        try {
            path = AppStarter.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        log.info("call to restart server");
        log.info("jar path " + path);

        if(path == null)
            return;

        final File currentJar = new File(path);

         /* is it a jar file? */
        if(!currentJar.getName().endsWith(".jar"))
            return;

        /* Build command: java -jar application.jar */
        final ArrayList<String> command = new ArrayList<String>();
        //command.add("nohup");
        command.add(javaBin);
        command.add("-jar");
        command.add(currentJar.getPath());
        //dbname [dbname] port [port] name [name] pass [pass] webPort [webPort]
        command.add("dbname");
        command.add(GlobalSettings.baseName);

        command.add("name");
        command.add(GlobalSettings.name);

        command.add("pass");
        command.add(GlobalSettings.password);

        command.add("webPort");
        command.add(String.valueOf(GlobalSettings.webPort));
        //command.add(">");
        //command.add(path.substring(0, path.lastIndexOf("/")) + "/log.txt");
        //command.add("&");

        final ProcessBuilder builder = new ProcessBuilder(command);
        try {
            builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.exit(0);

    }
}
