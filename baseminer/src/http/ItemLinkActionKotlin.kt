package http

import core.net.http.HttpRequestData
import core.net.http.html.HtmlInfoWriter
import java.net.URL
import java.util.*
import java.util.regex.Pattern

/**
 * Created on 14.04.2016.
 */
class ItemLinkActionKotlin: HtmlInfoWriter()
{
    var cache: HashMap<String, String> = hashMapOf();
    var baseItemPageURL = "http://www.tosbase.com/database/items/"

    override fun writeInfo(httpRequestData: HttpRequestData)
    {
        if(!httpRequestData.urlVariables.containsKey("name"))
        {
            htmlInfoPrinter.print("URL is not defined");
            return;
        }

        var itemName:String = httpRequestData.urlVariables.get("name")!!;
        var itemInfoPage = "";

        if(cache.containsKey(itemName))
        {
            itemInfoPage = cache[itemName]!!;
        }
        else
        {
            var baseURL = "http://www.tosbase.com/database/items/?item_name=@name&order=name&grade=All&stars=All&search="

            var pageData = URL(baseURL.replace("@name", itemName)).readText();

            var MY_PATTERN = Pattern.compile("(database/items/(\\d.*?)/)");
            var m = MY_PATTERN.matcher(pageData);


            if(m.find())
            {
                var itemID = m.group(2)

                itemInfoPage = baseItemPageURL+itemID;
                cache[itemName] = itemInfoPage;
            }
            else
                itemInfoPage = "Nothing found"
        }

        htmlInfoPrinter.print(itemInfoPage);
    }
}