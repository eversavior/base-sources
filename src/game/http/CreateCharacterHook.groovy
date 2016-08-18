package game.http

import core.net.http.HttpRequestData
import core.net.http.html.HtmlInfoWriter
import game.TosTextGame
import game.data.PlayerData

class CreateCharacterHook extends HtmlInfoWriter
{
    private TosTextGame tosTextGame

    CreateCharacterHook(TosTextGame tosTextGame)
    {
        this.tosTextGame = tosTextGame
    }

    @Override
    void writeInfo(HttpRequestData httpRequestData)
    {
        if(!httpRequestData.urlVariables.containsKey("accountName") && !httpRequestData.urlVariables.containsKey("class")
            && !httpRequestData.urlVariables.containsKey("name"))
        {
            htmlInfoPrinter.print("request isnt contains mandatory parameters");
            return;
        }


        String className = httpRequestData.urlVariables.get("class");
        String accountName = httpRequestData.urlVariables.get("accountName");
        String name = httpRequestData.urlVariables.get("name");

        def test = tosTextGame.playerDataBase.getByAccountName(accountName);
        if(tosTextGame.playerDataBase.getByAccountName(accountName) != null)
        {
            htmlInfoPrinter.print("Sorry u already have hero");
            return;
        }

        PlayerData newPlayer = tosTextGame.createNewPlayer(accountName, name, className);

        if(newPlayer == null)
        {
            htmlInfoPrinter.print("Sorry " + className + " is not implement yet");
            return;
        }

        htmlInfoPrinter.print("New hero is start journey as " + newPlayer.characterData.currentJob.jobName);
        htmlInfoPrinter.br();

        if(newPlayer.characterData.currentJob.description.length() > 0)
        {
            htmlInfoPrinter.print(newPlayer.characterData.currentJob.description);
            htmlInfoPrinter.br();
        }

        htmlInfoPrinter.print("Stats - strength:$newPlayer.characterData.playerCurrentStats.strength, vitality:$newPlayer.characterData.playerCurrentStats.vitality," +
                " intelligence:$newPlayer.characterData.playerCurrentStats.intelligence, spirit:$newPlayer.characterData.playerCurrentStats.spirit, " +
                    "dexterity:$newPlayer.characterData.playerCurrentStats.dexterity");
    }
}
