package game.data.character

import game.data.StatAffixBase
import game.data.StatCommulator
import game.data.inventroy.Inventory

import java.util.ArrayList

/**
 * Created on 26.04.2016.
 */
class CharacterData internal constructor()
{
    var jobsList = ArrayList<Job>()
    var characterBaseStats = StatAffixBase()

    var playerCurrentStats = StatCommulator()

    var inventory = Inventory()

    var name: String = "";

    var currentExp = 0
    var nextLevelExp = 0

    var level = 0

    init {
        initialize()
    }

    private fun initialize()
    {
        characterBaseStats.strength = (Math.random() * 10).toInt()
        characterBaseStats.vitality = (Math.random() * 10).toInt()
        characterBaseStats.intelligence = (Math.random() * 10).toInt()
        characterBaseStats.dexterity = (Math.random() * 10).toInt()
        characterBaseStats.spirit = (Math.random() * 10).toInt()

        playerCurrentStats.addStatSource(characterBaseStats)
    }

    public val currentJob: Job
        get() = jobsList[jobsList.size - 1]

    public fun addJob(job: Job) {
        jobsList.add(job)
        playerCurrentStats.addStatSource(job)
    }

}
