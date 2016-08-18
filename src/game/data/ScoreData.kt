package game.data

class ScoreData
{
    var scoreId = -1
    var scoreName = ""
    var scoreDescription: String = "";
    var scoreValue = 0

    constructor(scoreId: Int, scoreName: String, scoreValue: Int, scoreDescription: String) {
        this.scoreId = scoreId
        this.scoreName = scoreName
        this.scoreDescription = scoreDescription
        this.scoreValue = scoreValue
    }

    constructor(scoreId: Int, scoreName: String, scoreValue: Int) {
        this.scoreId = scoreId
        this.scoreName = scoreName
        this.scoreValue = scoreValue
    }

    constructor(scoreId: Int, scoreName: String) {
        this.scoreId = scoreId
        this.scoreName = scoreName
    }

    internal fun toPrintString(): String {
        return scoreName + " " + scoreValue
    }
}
