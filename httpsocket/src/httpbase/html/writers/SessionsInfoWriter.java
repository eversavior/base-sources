package httpbase.html.writers;

import core.net.http.HttpRequestData;
import core.net.http.html.HtmlInfoWriter;
import threading.Session;
import threading.SessionManager;

import java.util.HashMap;
import java.util.Map;

public class SessionsInfoWriter extends HtmlInfoWriter
{
    private SessionManager sessionManager;

    public SessionsInfoWriter(SessionManager sessionManager)
    {
        this.sessionManager = sessionManager;
    }

    @Override
    public void writeInfo(HttpRequestData httpRequestData)
    {
        htmlInfoPrinter.b("active sessions");
        htmlInfoPrinter.br();

        HashMap<Integer, Session> sessionsList = sessionManager.getSessionsList();

        boolean printBr = false;

        for(Map.Entry<Integer, Session> entry : sessionsList.entrySet())
        {
            Session session = entry.getValue();

            if(printBr)
                htmlInfoPrinter.br();
            else
                printBr = true;

            //htmlInfoPrinter.print("Client id=<a href='info.player?id=" + playerConnection.connectionId + "'>" + playerConnection.connectionId + "</a> login="+playerConnection.login);

            htmlInfoPrinter.print("<a href='info.session?id=" + session.id + "'>Session - " + session.getName() + "</a>");
            htmlInfoPrinter.details("info", "type=" + session.getClass() + "<br/>" + "id=" + session.id + "<br/>" + "isExit=" + session.getIsExit() + "<br/>" + "sleepTime=" + session.getSleepTime() + "<br/>");
        }
    }
}
