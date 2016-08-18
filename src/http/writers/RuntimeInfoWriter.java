package http.writers;

import core.ApplicationData;
import core.net.http.HttpRequestData;
import core.net.http.html.HtmlInfoWriter;

/**
 * Created by Asfel on 22.02.2015.
 */
public class RuntimeInfoWriter extends HtmlInfoWriter
{

    private Runtime runtime;
    private ApplicationData applicationData;

    public RuntimeInfoWriter(ApplicationData applicationData) {
        this.applicationData = applicationData;
        this.runtime = Runtime.getRuntime();
    }

    @Override
    public void writeInfo(HttpRequestData httpRequestData)
    {
        int mb = 1024 * 1024;

        htmlInfoPrinter.b("Runtime info");
        htmlInfoPrinter.br();
        htmlInfoPrinter.print("Available Processors: " + runtime.availableProcessors());
        htmlInfoPrinter.br();
        htmlInfoPrinter.print("Used Memory: " + (runtime.totalMemory() - runtime.freeMemory()) / mb);
        htmlInfoPrinter.br();
        htmlInfoPrinter.print("Free Memory: " + runtime.freeMemory() / mb);
        htmlInfoPrinter.br();
        htmlInfoPrinter.print("Total Memory:" + runtime.totalMemory() / mb);
        htmlInfoPrinter.br();
        htmlInfoPrinter.print("Max Memory:" + runtime.maxMemory() / mb);
        htmlInfoPrinter.br();

        htmlInfoPrinter.print("Up time: " + (System.currentTimeMillis() - applicationData.startDate) / 1000 / 60 + " minutes");

        htmlInfoPrinter.br();
    }
}
