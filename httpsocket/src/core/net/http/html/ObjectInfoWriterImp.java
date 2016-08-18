package core.net.http.html;

import core.net.http.HttpRequestData;

/**
 * Created by Asfel on 22.02.2015.
 */
public class ObjectInfoWriterImp implements ObjectInfoWriter
{
    protected InfoPrinter printer;

    @Override
    public void writeInfo(HttpRequestData httpRequestData) {
        printer.print("ObjectInfoWriterImp.writeInfo");
    }

    @Override
    public void setPrinter(InfoPrinter infoPrinter) {
        printer = infoPrinter;
    }
}
