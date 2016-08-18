package http.writers;

import core.ApplicationData;
import core.net.http.HttpRequestData;
import core.net.http.html.HtmlInfoWriter;
import core.net.http.html.InfoPrinter;
import httpbase.html.writers.SessionsInfoWriter;
import threading.SessionManager;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;

public class MainInfoWriter extends HtmlInfoWriter
{
    private RuntimeInfoWriter runtimeInfoWriter;
    private SessionsInfoWriter sessionInfoWriter;

    public MainInfoWriter(SessionManager sessionManager, ApplicationData applicationData)
    {
        runtimeInfoWriter = new RuntimeInfoWriter(applicationData);
        sessionInfoWriter = new SessionsInfoWriter(sessionManager);
    }

    @Override
    public void setPrinter(InfoPrinter infoPrinter) {
        super.setPrinter(infoPrinter);

        runtimeInfoWriter.setPrinter(infoPrinter);
        sessionInfoWriter.setPrinter(infoPrinter);
    }

    @Override
    public void writeInfo(HttpRequestData httpRequestData)
    {
        htmlInfoPrinter.h1("server info");

        //sessionInfoWriter.writeInfo(httpRequestData);
        //htmlInfoPrinter.br();
        runtimeInfoWriter.writeInfo(httpRequestData);

        htmlInfoPrinter.br();
        htmlInfoPrinter.br();

        try {

            htmlInfoPrinter.print("Build: " + new Date(classBuildTimeMillis()).toString());
            htmlInfoPrinter.br();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        //htmlInfoPrinter.print(new SingleValueForm().getTemplate());
    }

    private long classBuildTimeMillis() throws URISyntaxException, IllegalStateException, IllegalArgumentException {
        URL resource = getClass().getResource(getClass().getSimpleName() + ".class");
        if (resource == null) {
            throw new IllegalStateException("Failed to find class file for class: " +
                    getClass().getName());
        }

        if (resource.getProtocol().equals("file")) {

            return new File(resource.toURI()).lastModified();

        } else if (resource.getProtocol().equals("jar")) {

            String path = resource.getPath();
            return new File(path.substring(5, path.indexOf("!"))).lastModified();

        } else {

            throw new IllegalArgumentException("Unhandled url protocol: " +
                    resource.getProtocol() + " for class: " +
                    getClass().getName() + " resource: " + resource.toString());
        }
    }
}
