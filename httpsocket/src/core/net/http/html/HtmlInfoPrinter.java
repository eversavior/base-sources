package core.net.http.html;

/**
 * Created by Asfel on 22.02.2015.
 */
public class HtmlInfoPrinter extends InfoPrinter
{
    public HtmlInfoPrinter head()
    {
        print("<head>");
        return this;
    }

    public HtmlInfoPrinter headEnd()
    {
        print("</head>");
        return this;
    }

    public HtmlInfoPrinter radio(int id, boolean checked)
    {
        print("<input type=\"radio\" " + (checked? "checked=\"checked\"":"") + " name=\"" + id + "\" id=\"" + id + "\"/>");
        return this;
    }

    public HtmlInfoPrinter labeledRadio(int id, boolean checked, String label)
    {
        radio(id, checked).out("<label for=\"id\">" + label + "</label>");
        return this;
    }

    public HtmlInfoPrinter br()
    {
        print("<br/>");
        return this;
    }

    public HtmlInfoPrinter openPopup(String url, String trigger, String windowName)
    {
        print("<a href='#' onClick='javascript:\n" +
                "\t\t\t\t\t\t\t(\n" +
                "\t\t\t\t\t\t\t\tfunction()\n" +
                "\t\t\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t\t\t\tpopupWindow = window.open(\"" + url + "\", \"" + windowName.replace("'", "&amp") + "\", 700, 800);" +

                "\t\t\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t\t\t)()'\n" +
                "><button>" + trigger + "</button></a>");
        return this;
    }

    public HtmlInfoPrinter form(String action)
    {
        print("<form accept-charset='utf-8' action='" + action + "'>");
        return this;
    }
    public HtmlInfoPrinter formEnd()
    {
        print("</form>");
        return this;
    }

    public HtmlInfoPrinter postButton(String postUrl, String label)
    {
        print("<a href='#restart' onClick='javascript:(function()\n" +
                "\t\t\t{\n" +
                "\t\t\t\tvar form = document.createElement(\"form\");\n" +
                "\t\t\t\tform.setAttribute(\"method\", \"post\");\n" +
                "\t\t\t\tform.setAttribute(\"action\", \"" + postUrl + "\");\n" +

                "\t\t\t\tform.submit();\n" +
                "console.log(\"submit\");" +
                "\t\t\t})()\n" +
                "'><button>" + label + "</button></a>");
        return this;
    }

    public HtmlInfoPrinter h1(String s)
    {
        print("<h1>" + s + "</h1>");
        return this;
    }

    public HtmlInfoPrinter a(String href, String s)
    {
        print("<a href='" + href + "'>" + s + "</a>");
        return this;
    }

    public HtmlInfoPrinter input(String type, String value)
    {
        print("<input type='" + type + "' value='" + value + "'>");
        return this;
    }

    public HtmlInfoPrinter numericInput(String id, int size, String value)
    {
        print("<input name='" + id + "' size=\"" + size + "\" value=\"" + value + "\" type=\"number\">");
        return this;
    }

    public HtmlInfoPrinter textInput(String id, int size, String value, String inputPattern)
    {
        if(inputPattern.length() == 0)
            print("<input name='" + id + "' type=\"text\" size=\"" + size + "\" value=\"" + value + "\">");
        else
            print("<input name='" + id + "' type=\"text\" size=\"" + size + "\" value=\"" + value + "\" pattern=\"" + inputPattern + "\">");

        return this;
    }

    public HtmlInfoPrinter input(String id, String type, int size)
    {
        print("<input type='" + type + "' name='" + id + "' type=\"text\" size=\"" + size + "\">");
        return this;
    }

    public HtmlInfoPrinter input(String id, String type, String value)
    {
        print("<input type='" + type + "' name='" + id + "' type=\"text\" value=\"" + value + "\">");
        return this;
    }

    public HtmlInfoPrinter button(String href, String s, boolean disabled)
    {
        if(disabled)
            print("<a href='" + href + "'><button disabled='" + disabled + "'>" + s + "</button></a>");
        else
            print("<a href='" + href + "'><button>" + s + "</button></a>");

        return this;
    }

    public HtmlInfoPrinter out(String value)
    {
        print(value);

        return this;
    }

    public HtmlInfoPrinter summaryOpen()
    {
        print("<summary>");

        return this;
    }

    public HtmlInfoPrinter summaryClose()
    {
        print("</summary>");

        return this;
    }

    public HtmlInfoPrinter summary(String content)
    {
        print("<summary>" + content + "</summary>");

        return this;
    }

    public HtmlInfoPrinter detailsOpen()
    {
        print("<details>");

        return this;
    }

    public HtmlInfoPrinter detailClose()
    {
        print("</details>");

        return this;
    }

    public HtmlInfoPrinter details(String head, String content)
    {
        print("<details><summary>" + head + "</summary>" + content + "</details>");

        return this;
    }

    public HtmlInfoPrinter generatedTime(long startTime)
    {
        print("generated in - " + ((System.currentTimeMillis() - startTime) / 1000d) + " sec / " + (System.currentTimeMillis() - startTime));

        return this;
    }

    public HtmlInfoPrinter b(String s) {
        print("<b>" + s + "</b>");

        return this;
    }

    public HtmlInfoPrinter doctype() {
        print("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        return this;
    }

    public HtmlInfoPrinter html() {
        print("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\">");
        return this;
    }

    public HtmlInfoPrinter htmlEnd() {
        print("</html>");
        return this;
    }

    public HtmlInfoPrinter body() {
        print("<body>");
        return this;
    }

    public HtmlInfoPrinter bodyEnd() {
        print("</body>");
        return this;
    }

    public HtmlInfoPrinter close() {
        print("<script>window.close()</script>");
        return this;
    }
}
