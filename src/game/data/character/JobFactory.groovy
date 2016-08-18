package game.data.character

import game.data.virtualDataBase.VirtualDataBase

/**
 * Created on 11.04.2016.
 */
class JobFactory
{
    private HashMap<String, Job> jobsList;

    JobFactory(VirtualDataBase dataBase)
    {
        jobsList = dataBase.getTable("classes");
    }

    public Job getJobByName(String jobName)
    {
        if(jobsList.containsKey(jobName))
            return jobsList.get(jobName).clone();
        else
            return null;
    }
}
