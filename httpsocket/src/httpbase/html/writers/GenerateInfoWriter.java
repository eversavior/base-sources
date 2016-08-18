package httpbase.html.writers;

import core.net.http.HttpRequestData;
import core.net.http.html.HtmlInfoWriter;

public class GenerateInfoWriter extends HtmlInfoWriter
{

    private Runtime runtime;
    private HttpRequestData httpRequestData;

    public GenerateInfoWriter(HttpRequestData httpRequestData) {

        this.httpRequestData = httpRequestData;
    }

    @Override
    public void writeInfo(HttpRequestData httpRequestData)
    {
        htmlInfoPrinter.br();
        htmlInfoPrinter.print("generated in - " + ((System.currentTimeMillis() - httpRequestData.requestTime) / 1000d) + " sec / " + (System.currentTimeMillis() - httpRequestData.requestTime));
    }
}
