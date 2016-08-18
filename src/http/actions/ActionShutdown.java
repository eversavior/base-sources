package http.actions;


import core.net.http.HttpRequestData;
import core.net.http.html.HtmlInfoWriter;

import java.util.Timer;
import java.util.TimerTask;

public class ActionShutdown extends HtmlInfoWriter
{
    private Timer timer = new Timer();

    public ActionShutdown() {

    }

    @Override
    public void writeInfo(HttpRequestData httpRequestData) {
        htmlInfoPrinter.close();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, 10);

    }
}
