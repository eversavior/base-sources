package game

import game.data.PlayerData

/**
 * Created on 12.04.2016.
 */
class GameProgressionService extends GamePeriodicService
{
    private ArrayList<PlayerData> playersList

    private long lastTickTime = 0;

    GameProgressionService(ArrayList<PlayerData> playersList)
    {
        lastTickTime = System.currentTimeMillis();

        this.playersList = playersList
    }

    @Override
    void tick()
    {
        if(lastTickTime == 0)
            return;

        def currentTime = System.currentTimeMillis();

        playersList.each {
            it ->
                calculateTick(it, (currentTime-lastTickTime)/1000);
        }

        lastTickTime = currentTime;
    }

    void calculateTick(PlayerData player, long timeFactor)
    {
        player.characterData.currentJob.currentExp += timeFactor * 10;
        playersList.characterData.currentExp += timeFactor * 15;
    }
}
