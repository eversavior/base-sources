package core.net.http.html;

/**
 * Created by Asfel on 22.02.2015.
 */
public class InfoPrinter
{
    public void setOutput(InfoOutput output) {
        this.output = output;
    }

    private InfoOutput output;

    public void print(String s)
    {
        output.out(s);
    }

}
