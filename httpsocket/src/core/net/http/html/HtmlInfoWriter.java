package core.net.http.html;

import core.net.http.HttpRequestData;

/**
 * Created by Asfel on 22.02.2015.
 */
public class HtmlInfoWriter implements ObjectInfoWriter
{

    protected HtmlInfoPrinter htmlInfoPrinter;

    @Override
    public void writeInfo(HttpRequestData httpRequestData)
    {

    }

    @Override
    public void setPrinter(InfoPrinter infoPrinter)
    {
        this.htmlInfoPrinter = (HtmlInfoPrinter) infoPrinter;
    }
}
