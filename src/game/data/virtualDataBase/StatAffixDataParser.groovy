package game.data.virtualDataBase

import game.data.StatAffixBase

/**
 * Created on 12.04.2016.
 */
class StatAffixDataParser extends DataParser
{
    StatAffixDataParser(String language) {
        super(language)
    }

    @Override
    def parse(String data)
    {
       return parseStatAffix(data, new StatAffixBase());
    }

    def parseStatAffix(String data, StatAffixBase output)
    {
        def rootNode = new XmlSlurper().parseText(data);

        def rootIterator = rootNode.depthFirst();

        output.strength = rootIterator.find { it.name() == "strength" }.toString().toInteger();
        output.dexterity = rootIterator.find { it.name() == "dexterity" }.toString().toInteger();
        output.intelligence = rootIterator.find { it.name() == "intelligence" }.toString().toInteger();
        output.vitality = rootIterator.find { it.name() == "vitality" }.toString().toInteger();
        output.spirit = rootIterator.find { it.name() == "spirit" }.toString().toInteger();

        return output;
    }
}
