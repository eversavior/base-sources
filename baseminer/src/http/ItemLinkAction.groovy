package http

import core.net.http.HttpRequestData
import core.net.http.html.HtmlInfoWriter

import java.util.regex.Pattern

/**
 * Created on 11.04.2016.
 */
class ItemLinkAction extends HtmlInfoWriter
{
    def cache = new HashMap<String, String>();
    def baseItemPageURL = "http://www.tosbase.com/database/items/"


    ItemLinkAction()
    {

    }

    @Override
    void writeInfo(HttpRequestData httpRequestData) {

        if(!httpRequestData.urlVariables.containsKey("name"))
        {
            htmlInfoPrinter.print("URL is not defined");
            return;
        }

        def itemName = httpRequestData.urlVariables.get("name")
        def itemInfoPage;

        if(cache.containsKey(itemName))
        {
            itemInfoPage = cache.get(itemName);
        }
        else
        {
            def baseURL = "http://www.tosbase.com/database/items/?item_name=@name&order=name&grade=All&stars=All&search="

            def pageData = new URL(baseURL.replace("@name", itemName)).getText();

            Pattern MY_PATTERN = Pattern.compile("(database/items/(\\d.*?)/)");
            def m = MY_PATTERN.matcher(pageData);


            if(m.find())
            {
                def itemID = m.group(2)

                itemInfoPage = baseItemPageURL+itemID;
                cache.put(itemName, itemInfoPage)
            }
            else
                itemInfoPage = "Nothing found"
        }

        htmlInfoPrinter.print(itemInfoPage);
    }
}
