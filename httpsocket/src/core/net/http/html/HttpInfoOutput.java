package core.net.http.html;

import core.net.http.HttpServer;

/**
 * Created by Asfel on 22.02.2015.
 */
public class HttpInfoOutput implements InfoOutput
{
    public void setHttpServer(HttpServer httpServer) {
        this.httpServer = httpServer;
    }

    private HttpServer httpServer;

    @Override
    public void out(String s) {
        httpServer.writeOut(s);
    }
}
