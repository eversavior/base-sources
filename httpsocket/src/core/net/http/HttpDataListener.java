package core.net.http;

/**
 * Created by Asfel on 21.02.2015.
 */
public interface HttpDataListener {

    public void handleHttpRequest(HttpServer httpServer, HttpRequestData httpRequestData);
}
