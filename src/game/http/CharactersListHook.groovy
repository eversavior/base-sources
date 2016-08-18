package game.http

import core.net.http.HttpRequestData
import core.net.http.html.HtmlInfoWriter
import game.TosTextGame
import game.data.PlayerData

class CharactersListHook extends HtmlInfoWriter
{
    private TosTextGame tosTextGame

    CharactersListHook(TosTextGame tosTextGame)
    {
        this.tosTextGame = tosTextGame
    }

    @Override
    void writeInfo(HttpRequestData httpRequestData)
    {
       def charactersCount = 0;

        charactersCount = tosTextGame.playerDataBase.playersCount();

        htmlInfoPrinter.print("Heroes in the world: " + charactersCount);
    }
}
