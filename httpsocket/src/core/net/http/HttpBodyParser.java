package core.net.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Asfel on 21.02.2015.
 */
public class HttpBodyParser
{

    public static final String CONTENT_LENGTH_HEADER = "Content-Length";

    public HttpRequestData parser(BufferedReader in) throws IOException {
        String requestBody = null;//read rquest body

        requestBody = in.readLine();

        if(requestBody.length() == 0)
        {

            return null;
        }


        HttpRequestData httpRequestData = new HttpRequestData();
        httpRequestData.requestTime = System.currentTimeMillis();

        String contentParts[] = requestBody.split(" ");//e.g GET /test.php HTTP/1.1
        String[] urlVars = null;

        if(contentParts.length > 0)
        {
            httpRequestData.requestType = contentParts[0];
            httpRequestData.url = contentParts[1].substring(1);
            httpRequestData.protocol = contentParts[2];


            if(httpRequestData.url.indexOf("?") != -1)
            {
                httpRequestData.path = httpRequestData.url.substring(0, httpRequestData.url.indexOf("?"));
                urlVars = httpRequestData.url.substring(httpRequestData.url.indexOf("?") + 1).split("&");
            }
            else
            {
                httpRequestData.path = httpRequestData.url;
            }

            httpRequestData.urlVariables = new HashMap<String, String>();

            if(urlVars != null)//hub.challenge
            {


                for (int i = 0; i < urlVars.length; i++) {
                    String varPair[] = urlVars[i].split("=");

                    if(varPair.length > 1)
                        httpRequestData.urlVariables.put(varPair[0], varPair[1]);
                    else
                        httpRequestData.urlVariables.put(varPair[0], "");
                }
            }

            httpRequestData.headers = new HashMap<String, String>();

            String line;
            while ((line = in.readLine()) != null)
            {
                if (line.length() != 0)
                {
                    int separatorIndex = line.indexOf(": ");

                    String variableName = line.substring(0, separatorIndex);
                    String variableValue = line.substring(separatorIndex + 2);

                    httpRequestData.headers.put(variableName, variableValue);
                }
                else
                    break;
            }

            int contentSize = 0;

            if(httpRequestData.headers.containsKey(CONTENT_LENGTH_HEADER))
                contentSize = Integer.parseInt(httpRequestData.headers.get(CONTENT_LENGTH_HEADER));

            if(contentSize > 0)
            {
                char[] contentBytes = new char[contentSize];
                in.read(contentBytes);

                httpRequestData.rawContent = String.valueOf(contentBytes);
            }


        }

        return httpRequestData;
    }
}
