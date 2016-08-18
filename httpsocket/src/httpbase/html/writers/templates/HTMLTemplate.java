package httpbase.html.writers.templates;


import core.net.http.html.HtmlInfoPrinter;
import core.net.http.html.PlainTextOutput;

public class HTMLTemplate
{
    protected HtmlInfoPrinter htmlInfoPrinter;
    private PlainTextOutput plainTextOutput;

    protected boolean isBuilded = false;

    public HTMLTemplate()
    {
        initialize();
    }

    private void initialize()
    {
        plainTextOutput = new PlainTextOutput();
        htmlInfoPrinter = new HtmlInfoPrinter();

        htmlInfoPrinter.setOutput(plainTextOutput);
    }

    public String getTemplate()
    {
        if(!isBuilded)
            rebuildTempalte();

        return plainTextOutput.getContent();
    }

    public String getTemplate(boolean update)
    {
        if(update)
            rebuildTempalte();

        return getTemplate();
    }

    protected void rebuildTempalte()
    {
        plainTextOutput.clear();
        isBuilded = true;

        build();
    }

    protected void build()
    {

    }

}
