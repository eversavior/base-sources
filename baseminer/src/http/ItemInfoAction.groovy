package http

import core.net.http.HttpRequestData
import core.net.http.html.HtmlInfoWriter

import java.util.regex.Pattern

/**
 * Created on 11.04.2016.
 */
class ItemInfoAction extends HtmlInfoWriter
{
    def itemIDCache = new HashMap<String, String>();
    def itemDataCache = new HashMap<String, String>();
    def baseItemPageURL = "http://www.tosbase.com/database/items/"

    ItemInfoAction()
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

        def itemID;

        if(itemIDCache.containsKey(itemName))
        {
            itemID = itemIDCache.get(itemName);
        }
        else
        {
            def baseURL = "http://www.tosbase.com/database/items/?item_name=@name&order=name&grade=All&stars=All&search="

            def pageData = new URL(baseURL.replace("@name", itemName)).getText();

            Pattern MY_PATTERN = Pattern.compile("(database/items/(\\d.*?)/)");
            def m = MY_PATTERN.matcher(pageData);

            if (m.find()) {
                itemID = m.group(2);
                itemIDCache.put(itemName, itemID)

                fetchItemData(itemID);
                return;
            } else {
                itemInfoPage = "Nothing found"
                htmlInfoPrinter.print(itemInfoPage);
                return;
            }
        }

        fetchItemData(itemID);
    }

    Pattern itemDescription = Pattern.compile("(<pre class=\"item-desc\">)(\\w.*?)(</pre>)");
    Pattern damage = Pattern.compile("(<tr><th>Attack</th><td>(\\d.*?)</td></tr>)");

    void fetchItemData(String itemID)
    {
        def pageData;
        if(itemDataCache.containsKey(itemID))
        {
            pageData = itemDataCache.get(itemID);
        }
        else
        {

            pageData = new URL("https://tos.neet.tv/items/" + itemID).getText();
            //htmlInfoPrinter.print(pageData)
        }

        def matcher = itemDescription.matcher(pageData);

        if(matcher.find())
        {
            def description = matcher.group(2);

            if(description.length() > 0)
            {
                htmlInfoPrinter.print("Description: " + description.substring(1));

            }
        }

        matcher = damage.matcher(pageData);

        if(matcher.find())
        {
            def damage = matcher.group(2);
            htmlInfoPrinter.print("Damage: " + damage);
        }
    }
}
