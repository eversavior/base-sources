package core.net.http;

import core.net.http.html.InfoPrinter;
import core.net.http.html.ObjectInfoWriter;
import lombok.extern.java.Log;

import java.util.HashMap;

@Log
public class HttpDataListenerBase implements HttpDataListener
{
    private HashMap<String, ObjectInfoWriter> writesMap = new HashMap<String, ObjectInfoWriter>();

    private InfoPrinter currentInfoPrinter;

    public HttpDataListenerBase(InfoPrinter currentInfoPrinter)
    {
        this.currentInfoPrinter = currentInfoPrinter;
        initialize();
        configurePages();
    }

    protected void initialize()
    {

    }

    protected void configurePages()
    {

    }

    protected void createInfoHook(ObjectInfoWriter infoWriter, String hook)
    {
        writesMap.put(hook, infoWriter);
        infoWriter.setPrinter(currentInfoPrinter);
    }

    protected void constructHook(Class hookWriterConstructor, String hook)
    {
        try {
            createInfoHook((ObjectInfoWriter) hookWriterConstructor.newInstance(), hook);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleHttpRequest(HttpServer httpServer, HttpRequestData httpRequestData)
    {
        if(httpRequestData == null)
        {
            log.info("http request empty ");
            return;
        }
        else
        {
            String path = httpRequestData.path;
            ObjectInfoWriter currentWriter = writesMap.get(path);

            if (currentWriter != null)
            {
                currentWriter.writeInfo(httpRequestData);
            }
            else
            {
                currentInfoPrinter.print("requested data not found");
            }
        }
    }
}
