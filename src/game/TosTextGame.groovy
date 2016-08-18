package game

import game.data.PlayerData
import game.data.PlayerDataBase
import game.data.character.JobFactory
import game.data.virtualDataBase.VirtualDataBase
import game.fight.FightService
import game.start.PlayerStartService

class TosTextGame
{
    public PlayerDataBase playerDataBase = new PlayerDataBase();
    private PlayerStartService startService;

    private ArrayList<GamePeriodicService> gamePeriodicServicesList = new ArrayList<>();

    private FightService fightService;
    private VirtualDataBase dataBase

    TosTextGame(VirtualDataBase dataBase)
    {
        this.dataBase = dataBase

        initialize();
    }

    private void gameCycleTick()
    {
        gamePeriodicServicesList.each {
            it ->
                it.tick();
        }
    }

    void initialize()
    {
        startService = new PlayerStartService(new JobFactory(dataBase));
        fightService = new FightService();

        createNewPlayer("Dummy1", "flashy", "cleric");
        createNewPlayer("Dummy2", "gettly", "cleric");
        createNewPlayer("Dummy3", "blorny", "cleric");

        fightService.doFight(playerDataBase.getByName("flashy"), playerDataBase.getByName("gettly"));
    }

    public PlayerData createNewPlayer(String accountName, String nick, String className)
    {
        PlayerData newPlayer = startService.createPlayer(accountName, nick, className);

        playerDataBase.add(newPlayer);

        return newPlayer;
    }

    boolean fightRandom(String accountName)
    {
        return fightService.doFight(playerDataBase.getByAccountName(accountName), playerDataBase.getRandomBut(name));
    }

    boolean fightWithCharacter(String accountName, String target)
    {
       return fightService.doFight(playerDataBase.getByAccountName(accountName), playerDataBase.getByName(target));
    }

    boolean fightWithAccount(String accountName, String targetAccountName)
    {
        return fightService.doFight(playerDataBase.getByAccountName(accountName), playerDataBase.getByAccountName(targetAccountName));
    }
}
