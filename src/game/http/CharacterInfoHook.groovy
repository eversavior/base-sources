package game.http

import core.net.http.HttpRequestData
import core.net.http.html.HtmlInfoWriter
import game.TosTextGame
import game.data.PlayerData

class CharacterInfoHook extends HtmlInfoWriter
{
    private TosTextGame tosTextGame

    CharacterInfoHook(TosTextGame tosTextGame)
    {
        this.tosTextGame = tosTextGame
    }

    @Override
    void writeInfo(HttpRequestData httpRequestData)
    {
        if(!httpRequestData.urlVariables.containsKey("type"))
        {
            htmlInfoPrinter.print("type is not defined");
            return;
        }

        if(!httpRequestData.urlVariables.containsKey("value"))
        {
            htmlInfoPrinter.print("key value is not defined");
            return;
        }

        String type = httpRequestData.urlVariables.get("type");
        String value = httpRequestData.urlVariables.get("value");

        PlayerData player;

        if(type == "account")
        {
            player = tosTextGame.playerDataBase.getByAccountName(value);
        }
        else if(type == "character")
        {
            player = tosTextGame.playerDataBase.getByName(value);
        }
        else
        {
            htmlInfoPrinter.print("type " + type + " is not supported yet");
        }

        if(player == null)
        {
            htmlInfoPrinter.print("Player or character is not found");
            return;
        }

        htmlInfoPrinter.print("Name: " + player.characterData.name)
        htmlInfoPrinter.br();

        htmlInfoPrinter.print("Level: " + player.characterData.level + 1)
        htmlInfoPrinter.br();

        htmlInfoPrinter.print("Current Exp: " + player.characterData.currentExp)
        htmlInfoPrinter.br();

        htmlInfoPrinter.print("Current job: " + player.characterData.currentJob.jobName)
        htmlInfoPrinter.br();

        htmlInfoPrinter.print("strength: " + player.characterData.playerCurrentStats.strength)
        htmlInfoPrinter.br();

        htmlInfoPrinter.print("vitality: " + player.characterData.playerCurrentStats.vitality)
        htmlInfoPrinter.br();

        htmlInfoPrinter.print("intelligence: " + player.characterData.playerCurrentStats.intelligence)
        htmlInfoPrinter.br();

        htmlInfoPrinter.print("spirit: " + player.characterData.playerCurrentStats.spirit)
        htmlInfoPrinter.br();

        htmlInfoPrinter.print("dexterity: " + player.characterData.playerCurrentStats.dexterity)
        htmlInfoPrinter.br();

        htmlInfoPrinter.print("strength: " + player.characterData.playerCurrentStats.strength)
        htmlInfoPrinter.br();
    }
}
