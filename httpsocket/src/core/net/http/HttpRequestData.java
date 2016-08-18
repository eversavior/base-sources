package core.net.http;

import java.util.HashMap;

/**
 * Created by Asfel on 21.02.2015.
 */
public class HttpRequestData
{
    public String requestType;
    public String url;
    public String protocol;
    public String path;

    public HashMap<String, String> urlVariables;
    public HashMap<String, String> headers;
    public String rawContent;
    public long requestTime;
}
