package game.data.virtualDataBase

/**
 * Created on 12.04.2016.
 */
class VirtualDataBase
{
    HashMap<String, HashMap<String, Object>> dataBase = new HashMap<String, HashMap<String, Object>>();

    void put(String table, String columnId, Object data)
    {
        HashMap<String, Object> dataStorage;

        if(dataBase.containsKey(table) == false)
        {
            dataStorage = new HashMap<String, Object>();
            dataBase.put(table, dataStorage);
        }
        else
        {
            dataStorage = dataBase.get(table);
        }

        dataStorage.put(columnId, data);
    }

    def getTable(String table)
    {
        if(dataBase.containsKey(table) == false)
            return null;

        return dataBase.get(table);
    }

    def get(String table, String id)
    {
        if(dataBase.containsKey(table) == false)
            return null;

        def dataStorage = dataBase.get(table);

        if(dataStorage.containsKey(id) == false)
            return null;

        return dataStorage.get(id);
    }
}
