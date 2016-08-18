package game.data

import java.util.ArrayList
import java.util.HashMap

/**
 * Created on 11.04.2016.
 */
internal class PlayerDataBase
{
    private val playersList = ArrayList<PlayerData>()
    private val nameToPlayer = HashMap<String, PlayerData>()

    fun add(playerData: PlayerData) {
        playerData.id = playersList.size

        playersList.add(playerData)
        nameToPlayer.put(playerData.characterData.name, playerData)
    }

    fun getRandomBut(name: String): PlayerData {
        val count = playersList.size
        var playerData: PlayerData

        while (true) {
            val index = (Math.random() * count).toInt()

            playerData = playersList[index]

            if (playerData.accountName !== name)
                break
        }

        return playerData
    }

    fun getByAccountName(accountName: String): PlayerData? {
        var playerData: PlayerData? = null

        for (player in playersList) {
            if (player.accountName === accountName) {
                playerData = player
            }
        }

        return playerData;
    }

    fun getByName(name: String): PlayerData? {
        return nameToPlayer[name];
    }

    operator fun get(id: Int): PlayerData {
        return playersList[id]
    }

    fun playersCount(): Int {
        return playersList.size
    }
}
