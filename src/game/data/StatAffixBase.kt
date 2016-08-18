package game.data

import kotlin.reflect.jvm.internal.impl.javax.inject.Inject

/**
 * Created on 26.04.2016.
 */
open class StatAffixBase
{
    constructor()
    {}

    constructor(strength: Int, dexterity: Int, intelligence: Int, vitality: Int, spirit: Int)
    {
        this.strength = strength
        this.dexterity = dexterity
        this.intelligence = intelligence
        this.vitality = vitality
        this.spirit = spirit
    }

    public var strength = 0;
    public var dexterity = 0
    public var intelligence = 0
    public var vitality = 0
    public var spirit = 0
}
