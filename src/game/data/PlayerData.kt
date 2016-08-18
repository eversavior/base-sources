package game.data

import game.data.character.CharacterData

class PlayerData
{
    var id:Int = -1;
    var accountName:String = "null";
    lateinit var pvpData:PVPData;
    lateinit var characterData:CharacterData;
}