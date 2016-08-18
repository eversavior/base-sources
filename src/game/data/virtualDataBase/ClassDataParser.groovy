package game.data.virtualDataBase

import game.data.StatAffixBase
import game.data.character.Job

/**
 * Created on 12.04.2016.
 */
class ClassDataParser extends DataParser
{
    private StatAffixDataParser statAffixParser;

    ClassDataParser(String language) {
        super(language);

        statAffixParser = new StatAffixDataParser();
    }

    @Override
    def parse(String data)
    {
        Job job = new Job();
        statAffixParser.parseStatAffix(data, job);

        def rootNode = new XmlSlurper().parseText(data);

        job.jobId = rootNode.id.toInteger();
        job.jobName = rootNode.name.find { it.@lang == language }.toString();
        job.description = rootNode.description.find { it.@lang == language }.toString();

        return job;
    }
}
