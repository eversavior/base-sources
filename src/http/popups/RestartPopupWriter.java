package http.popups;

import core.net.http.HttpRequestData;
import core.net.http.html.HtmlInfoWriter;
import http.actions.ActionHooks;

public class RestartPopupWriter extends HtmlInfoWriter
{

    public RestartPopupWriter()
    {

    }

    @Override
    public void writeInfo(HttpRequestData httpRequestData)
    {
        htmlInfoPrinter.form(ActionHooks.RESTART);

        htmlInfoPrinter.print("Restart after");
        htmlInfoPrinter.textInput("delay", 15, "10", "[0-9]{1,10}");
        htmlInfoPrinter.print(" in seconds");

        htmlInfoPrinter.br();

        htmlInfoPrinter.print("Restart notify");
        htmlInfoPrinter.textInput("message", 25, "Server will restart in 10 sec", "");

        htmlInfoPrinter.br();
        htmlInfoPrinter.br();

        htmlInfoPrinter.input("submit", "submit");

        htmlInfoPrinter.formEnd();
    }
}
