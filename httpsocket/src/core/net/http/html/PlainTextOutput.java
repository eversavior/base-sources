package core.net.http.html;

/**
 * Created by Asfel on 06.03.2015.
 */
public class PlainTextOutput implements InfoOutput
{
    private String content = "";

    public String getContent()
    {
        return content;
    }

    public void clear()
    {
        content = "";
    }

    @Override
    public void out(String s)
    {
        content += s + "\n";
    }
}
