package http.actions;

import core.AppStarter;
import core.net.http.HttpRequestData;
import core.net.http.html.HtmlInfoWriter;
import threading.SessionManager;

import java.io.UnsupportedEncodingException;
import java.util.Timer;
import java.util.TimerTask;

public class ActionRestart extends HtmlInfoWriter
{
    private Timer timer;
    private SessionManager sessionManager;

    public ActionRestart(SessionManager sessionManager) {
        this.sessionManager = sessionManager;

    }

    @Override
    public void writeInfo(HttpRequestData httpRequestData) {
        htmlInfoPrinter.close();

        if(!httpRequestData.urlVariables.containsKey("delay") || !httpRequestData.urlVariables.containsKey("message"))
            return;

        long delay = Long.parseLong(httpRequestData.urlVariables.get("delay")) * 1000l;
        String message = null;

        try {
            message = java.net.URLDecoder.decode(httpRequestData.urlVariables.get("message"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //SendChatMessageCommand chatMessageCommand = (SendChatMessageCommand) sessionManager.getSession(SessionManager.LOBBY_SESSION).getCommandsManager().getCommand(SendChatMessageCommand.class);

        //ChatMessage chatMessage = new ChatMessage(-1, SessionManager.LOBBY_SESSION, message);
        //chatMessageCommand.broadcastForAll(chatMessage, sessionManager.getSession(SessionManager.LOBBY_SESSION));



        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                AppStarter.restart();
            }
        }, delay);

    }
}
