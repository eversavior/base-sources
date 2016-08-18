package game.data.character

import game.data.StatAffixBase
import groovy.transform.AutoClone

@AutoClone
class Job : StatAffixBase
{
    var description = ""
    var jobName: String = "";
    var jobId: Int = 0

    var currentExp = 0
    var nextLevelExp = 0

    var level = 0

    constructor() {
    }

    constructor(jobId: Int, jobName: String) {
        this.jobId = jobId
        this.jobName = jobName
    }

    constructor(jobId: Int, jobName: String, strength: Int, dexterity: Int, intelligence: Int, vitality: Int, spirit: Int) {
        this.jobId = jobId
        this.jobName = jobName

        this.strength = strength
        this.dexterity = dexterity
        this.intelligence = intelligence
        this.vitality = vitality
        this.spirit = spirit
    }

    @Throws(CloneNotSupportedException::class)
    fun clone(): Job {

        return clone() as Job
    }
}
