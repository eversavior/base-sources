package http

import core.net.http.HttpRequestData
import core.net.http.html.*
import javafx.scene.image.Image
import org.w3c.dom.Document
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import sun.misc.BASE64Encoder
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.net.URL
import java.util.*
import java.util.regex.Pattern
import javax.imageio.ImageIO
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory

/**
 * Created on 14.04.2016.
 */
class Opus: HtmlInfoWriter()
{

    private var info = PlainTextOutput();
    private var _init:Boolean = false;
    private var infoPrinter = HtmlInfoPrinter();

    override fun writeInfo(httpRequestData: HttpRequestData)
    {
        this.infoPrinter.setOutput(info)

        if(!_init)
            initialize();


        
        /*if(!httpRequestData.urlVariables.containsKey("name"))
        {
            infoPrinter.print("URL is not defined");
            return;
        }

        var itemName:String = httpRequestData.urlVariables.get("name")!!;*/


    }

    private fun initialize()
    {
        info.clear();
        var opusBaseRaw:List<String> = File("resources/itemlist.txt").readLines();
        var opusBase:HashMap<String, String> = HashMap<String, String>();

        for (readLine in opusBaseRaw)
        {
            var lineParts = readLine.split("\t");

            var classID:String = lineParts[0];
            var itemName:String = lineParts[14];

            opusBase.put(itemName, classID);
        }

       // var opusFormulasRaw:String = File("resources/opus.txt").readText(Charsets.UTF_8);



        var dbFactory:DocumentBuilderFactory = DocumentBuilderFactory.newInstance();
        var dBuilder:DocumentBuilder = dbFactory.newDocumentBuilder();
        var doc:Document = dBuilder.parse(File("resources/opus.txt"));

        doc.getDocumentElement().normalize();

        var node:Node? = doc.documentElement.firstChild;

        var index = 0;
        while(node != null)
        {
            if(node.attributes != null && node.attributes.length > 0)
            {
                var name: String? = node.attributes.getNamedItem("Name").nodeValue.toString();
                var targetName: String = node.attributes.getNamedItem("TargetItem").nodeValue.toString();
                //var craftTime: Int = Integer.parseInt(node.attributes.getNamedItem("NeedSec").nodeValue.toString());
                var classID: String?

                var formula = getFormula(node);

                if(!opusBase.containsKey(targetName) && targetName.contains("_"))
                {
                    var targetNameParts = targetName.split("_");

                    try
                    {
                        var possiblyClassId = Integer.parseInt(targetNameParts[1]);
                    }
                    catch(e:Error)
                    {

                    }

                    classID = targetNameParts[1];
                }
                else
                {
                    classID  = opusBase[targetName];
                }

                name = getItemName(classID.toString(), targetName);

                htmlInfoPrinter.a("#" + name, index.toString() + ". " + name);
                htmlInfoPrinter.br();
                index++;

                infoPrinter.print("<a id=\"" + name + "\" name=\"" + name + "\"></a>")
                infoPrinter.br();
                infoPrinter.a("http://www.tosbase.com/database/items/" + classID, name);
                infoPrinter.br();
                infoPrinter.print("classID: " + classID);

                var image:BufferedImage  = BufferedImage(9 * 12 + 1, 9 * 12 + 1, BufferedImage.TYPE_INT_RGB)

                var tableTemplate = File("resources/tabletemplate").readText();

                for (opusItemInfo in formula)
                {
                    drawBlock(opusItemInfo.col, opusItemInfo.row, image)

                    name = getItemName(opusBase[opusItemInfo.name], opusItemInfo.name);

                    name = "<a href=\"http://www.tosbase.com/database/items/" + opusBase[opusItemInfo.name] + "\">" + name + "</a></br>";

                    tableTemplate = tableTemplate.replace("@" + opusItemInfo.row + "" + opusItemInfo.col, name);
                };

                //tableTemplate = tableTemplate.replace(Regex("(<td>@+\\w.*</td>)"), "");
                tableTemplate = tableTemplate.replace(Regex("(@+\\w.*)"), " - ");

                drawGrid(12, 12, image);

                infoPrinter.br();

                infoPrinter.print("<img src=\"data:image/png;base64," + encodeToString(image, "png")+ "\"/>");
                infoPrinter.br();

                infoPrinter.print("Rec: ")
                infoPrinter.br();
                infoPrinter.print(tableTemplate);
                infoPrinter.br();
            }

            node = node.nextSibling;
        }

        htmlInfoPrinter.br();
        htmlInfoPrinter.br();
        htmlInfoPrinter.br();
        htmlInfoPrinter.br();


        htmlInfoPrinter.print(info.getContent());
    }

    private  var cache: HashMap<String?, String> = hashMapOf();

    private fun getItemName(itemID:String?, itemName:String):String?
    {
        if(cache.containsKey(itemID))
            return cache[itemID];

        var itemURL:String = "http://www.tosbase.com/database/items/" + itemID;

        var pageData = URL(itemURL).readText();

        var titlePattern = Pattern.compile("(<title>)(\\w.*?)( - Item Database - Tree of Savior Fan Base</title>)");
        var m = titlePattern.matcher(pageData);

        var name = itemName;

        if(m.find())
        {
            name = m.group(2)
        }

        cache[itemID] = name;

        return name;
    }

    private fun drawGrid(w: Int, h: Int, img:BufferedImage)
    {

        for(i in 0..9)
        {
            for(j in 0..9 * 12)
            {
               img.setRGB(j, i * h, 0x0000FF);
            }
        }

        for(i in 0..9)
        {
            for(j in 0..9 * 12)
            {
                img.setRGB(i * w, j, 0x0000FF);
            }
        }
    }

    private fun getFormula(parentNode: Node): ArrayList<OpusItemInfo>
    {
        var out:ArrayList<OpusItemInfo> = arrayListOf();

        var node:Node? = parentNode.firstChild;

        while(node != null)
        {
            //Name="misc_0010" Row="0" Col="0"/>
            if(node.attributes != null && node.attributes.length > 0)
            {
                var name:String = node.attributes.getNamedItem("Name").nodeValue.toString();
                var row:Int = Integer.parseInt(node.attributes.getNamedItem("Row").nodeValue.toString());
                var col:Int = Integer.parseInt(node.attributes.getNamedItem("Col").nodeValue.toString());

                var data:OpusItemInfo = OpusItemInfo();
                data.name = name;
                data.row = row;
                data.col = col;

                out.add(data);
            }

            node = node.nextSibling;
        }

        return out;
    }

    fun drawBlock(x:Int, y:Int, img:BufferedImage)
    {
        var _x = x * 12;
        var _y = y * 12;

        for(i in _x.._x + 12)
        {
            for(j in _y.._y + 12)
            {
                img.setRGB(i, j, 0xFF0000);
            }
        }



    }

    public fun encodeToString(image:BufferedImage, type:String):String
    {
        var imageString = "";
        var bos = ByteArrayOutputStream();

        try
        {
            ImageIO.write(image, type, bos);
            var imageBytes  = bos.toByteArray();

            var encoder = BASE64Encoder();
            imageString = encoder.encode(imageBytes);

            bos.close();
        }
        catch (e: IOException)
        {
            e.printStackTrace();
        }

        return imageString;
    }
}