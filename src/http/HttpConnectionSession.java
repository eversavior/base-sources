package http;



import core.ApplicationData;
import core.GlobalSettings;
import core.net.http.HttpServer;
import core.net.http.html.HtmlInfoPrinter;
import core.net.http.html.HttpInfoOutput;
import game.TosTextGame;
import lombok.extern.java.Log;
import threading.Session;
import threading.SessionManager;

@Log
public class HttpConnectionSession extends Session
{
    private static final int HTTP_HANDLER_SESSION = -100;
    private HttpServer httpServer;
    private SessionManager sessionManager;

    private HttpInfoOutput httpInfoOutput;
    private HtmlInfoPrinter htmlInfoPrinter;

    private ApplicationData applicationData;

    private TosTextGame tosTextGame;

    public HttpConnectionSession(ApplicationData applicationData, TosTextGame tosTextGame)
    {
        super("HttpConnectionSession", HTTP_HANDLER_SESSION);

        this.applicationData = applicationData;
        this.tosTextGame = tosTextGame;

        httpServer = new HttpServer(GlobalSettings.webPort);


        htmlInfoPrinter = new HtmlInfoPrinter();
        HttpDataListener httpDataListener = new HttpDataListener(htmlInfoPrinter, sessionManager, applicationData);
        httpDataListener.makeGamePages(tosTextGame);

        httpServer.setListener(httpDataListener);

        httpInfoOutput = new HttpInfoOutput();
        httpInfoOutput.setHttpServer(httpServer);
        htmlInfoPrinter.setOutput(httpInfoOutput);
    }

    @Override
    public void afterRun() {
        super.afterRun();

        httpServer.listen();
    }
}
