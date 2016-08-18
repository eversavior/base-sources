package core.net.http.html;

import core.net.http.HttpRequestData;

/**
 * Created by Asfel on 22.02.2015.
 */
public interface ObjectInfoWriter
{
    public void writeInfo(HttpRequestData httpRequestData);
    public void setPrinter(InfoPrinter infoPrinter);
}
