import core.ApplicationData
import core.TrustedURLLoader
import game.TosTextGame
import game.data.virtualDataBase.ClassDataParser
import game.data.virtualDataBase.DataLoader
import game.data.virtualDataBase.VirtualDataBase
import http.HttpConnectionSession
import threading.SessionManager
import yobit.YobitService
import yobit.YobitSignGenerator

def language = "default";

new TrustedURLLoader().init();


//new TestDatabase();
//4C29D5217856BFFFD70F8EEB2A1D2639
println(new YobitSignGenerator().generateSign("8e38ca2cd3c33351a87f8ba11c76d012", "nonce=2&method=getInfo"));
println("3f867e6270830ad2f8afc9042a58111bcf0259259cac8a73b600d6ea9b9b2f2d8d16133d5b5f962be6a1041fb5078f48df0b516b7f6f21f1300f869185b48eaa");

new YobitService()

return;

def dataBase = new VirtualDataBase();
def classLoader = new DataLoader("classes", new ClassDataParser(language), dataBase)

classLoader.execute();

TosTextGame tosTextGame = new TosTextGame(dataBase);

def sessionManager = new SessionManager();
def applicationData = new ApplicationData();

sessionManager.addSession(new HttpConnectionSession(applicationData, tosTextGame))