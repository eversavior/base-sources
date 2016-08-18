package game.data.virtualDataBase


class DataLoader
{
    private String directoryPath
    private DataParser parser
    private VirtualDataBase dataBase

    DataLoader(String directoryPath, DataParser parser, VirtualDataBase dataBase)
    {
        this.dataBase = dataBase
        this.parser = parser
        this.directoryPath = directoryPath
    }

    public void execute()
    {
        def directory = new File("resources/" + directoryPath);

        def filesList = directory.listFiles();

        for(int i = 0; i < filesList.size(); i++)
        {
            def currentFile = filesList[i];
            def fileName = currentFile.name;

            fileName = fileName.substring(0, currentFile.name.lastIndexOf("."))

            def fileContent = currentFile.getText()

            def data = parseContent(fileContent);

            dataBase.put(directoryPath, fileName, data);
        }
    }

    def parseContent(String data) {
        return parser.parse(data);
    }
}
