package httpbase.html.writers.templates;


import core.net.http.html.HtmlInfoPrinter;
import core.net.http.html.PlainTextOutput;

/**
 * Created by Asfel on 07.04.2015.
 */
public class DynamicHTMLTemplate extends HtmlInfoPrinter
{

    private PlainTextOutput plainTextOutput;

    public DynamicHTMLTemplate()
    {
        plainTextOutput = new PlainTextOutput();
        setOutput(plainTextOutput);
    }

    public DynamicHTMLTemplate clear()
    {
        plainTextOutput.clear();
        return this;
    }

    public String getTemplate()
    {
        return plainTextOutput.getContent();
    }
}
