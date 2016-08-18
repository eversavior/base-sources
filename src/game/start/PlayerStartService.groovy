package game.start

import game.data.character.CharacterData
import game.data.character.Job
import game.data.character.JobFactory;
import game.data.PlayerData;

public class PlayerStartService
{
    private JobFactory jobFactory

    PlayerStartService(JobFactory jobFactory)
    {
        this.jobFactory = jobFactory
    }

    public PlayerData createPlayer(String accountName, String nick, String className)
    {
        Job currentJob = jobFactory.getJobByName(className)

        if(currentJob == null)
            return null;

        PlayerData playerData = new PlayerData();

        playerData.accountName = accountName;

        playerData.characterData = new CharacterData();
        playerData.characterData.name = nick;
        playerData.characterData.addJob(currentJob);



        return playerData;
    }
}
