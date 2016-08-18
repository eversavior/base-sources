package httpbase.html.writers.templates;

/**
 * Created by Asfel on 06.03.2015.
 */
public class SingleValueForm extends HTMLTemplate
{

    @Override
    protected void build()
    {
        super.build();

        htmlInfoPrinter.form("test");

        htmlInfoPrinter.print("ValueInput");
        htmlInfoPrinter.textInput("inputId", 40, "test", "");
        htmlInfoPrinter.input("submit", "submit");

        htmlInfoPrinter.formEnd();
    }
}
