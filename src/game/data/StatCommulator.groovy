package game.data

class StatCommulator extends StatAffixBase
{
    private ArrayList<StatAffixBase> statSourcesList = new ArrayList<>();

    public void addStatSource(StatAffixBase statAffix)
    {
        statSourcesList.push(statAffix);

        strength += statAffix.strength;
        dexterity += statAffix.dexterity;
        intelligence += statAffix.intelligence;
        vitality += statAffix.vitality;
        spirit += statAffix.spirit;
    }

    public void removeStatSource(StatAffixBase statAffix)
    {
        statSourcesList.remove(statAffix);

        strength -= statAffix.strength;
        dexterity -= statAffix.dexterity;
        intelligence -= statAffix.intelligence;
        vitality -= statAffix.vitality;
        spirit -= statAffix.spirit;
    }
}
