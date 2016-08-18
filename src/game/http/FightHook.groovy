package game.http

import core.net.http.HttpRequestData
import core.net.http.html.HtmlInfoWriter
import game.TosTextGame
import game.data.PlayerData

class FightHook extends HtmlInfoWriter
{
    private TosTextGame tosTextGame

    FightHook(TosTextGame tosTextGame)
    {
        this.tosTextGame = tosTextGame
    }

    @Override
    void writeInfo(HttpRequestData httpRequestData)
    {
        if(!httpRequestData.urlVariables.containsKey("accountName"))
        {
            htmlInfoPrinter.print("accountName is not defined");
            return;
        }

        if(!httpRequestData.urlVariables.containsKey("type"))
        {
            htmlInfoPrinter.print("type is not defined");
            return;
        }

        String type = httpRequestData.urlVariables.get("type");
        String accountName = httpRequestData.urlVariables.get("accountName");

        PlayerData myPlayer = tosTextGame.playerDataBase.getByAccountName(accountName);

        if(myPlayer == null)
        {
            htmlInfoPrinter.print("player not found");
            return;
        }

        if(type == "random")
        {
            PlayerData randomPlayer = tosTextGame.playerDataBase.getRandomBut(accountName);

            boolean fightResult = tosTextGame.fightWithAccount(accountName, randomPlayer.accountName);

            result(fightResult, myPlayer.characterData.name, " to $randomPlayer.characterData.name");
        }
        else if(type == "target")
        {
            if(!httpRequestData.urlVariables.containsKey("target"))
            {
                htmlInfoPrinter.print("target is not defined");
                return;
            }

            String target = httpRequestData.urlVariables.get("target");

            boolean fightResult = tosTextGame.fightWithAccount(accountName, target);
            result(fightResult, myPlayer.characterData.name, " to $target");
        }
        else
        {
            htmlInfoPrinter.print("fight type: " + type + " not implemented yet");
        }
    }

    void result(boolean result, String name, String addInfo)
    {
        if(result)
            htmlInfoPrinter.print(name + " win" + addInfo);
        else
            htmlInfoPrinter.print(name + " lose" + addInfo);
    }
}
