package http;

import core.ApplicationData;
import core.net.http.HttpDataListenerBase;
import core.net.http.html.InfoPrinter;
import game.TosTextGame;
import game.http.*;
import http.actions.ActionHooks;
import http.actions.ActionRestart;
import http.popups.PopupHooks;
import http.popups.RestartPopupWriter;
import http.writers.MainInfoWriter;
import httpbase.html.writers.InfoHooks;
import threading.SessionManager;


public class HttpDataListener extends HttpDataListenerBase
{


    private SessionManager sessionManager;
    private ApplicationData applicationData;

    public HttpDataListener(InfoPrinter currentInfoPrinter, SessionManager sessionManager, ApplicationData applicationData)
    {
        super(currentInfoPrinter);
        this.sessionManager = sessionManager;
        this.applicationData = applicationData;

        initialize();
        configurePages();
    }

    @Override
    protected void configurePages() {
        makeDefaultPages();
    }

    public void makeGamePages(TosTextGame tosTextGame)
    {
        CreateCharacterHook craeteCharacterHook = new CreateCharacterHook(tosTextGame);
        createInfoHook(craeteCharacterHook, TosGameHooks.ACTION_CREATE_CHARACTER);

        CharactersListHook charactersListHook = new CharactersListHook(tosTextGame);
        createInfoHook(charactersListHook, TosGameHooks.ACTION_LIST_CHARACTERS);

        FightHook fightHook = new FightHook(tosTextGame);
        createInfoHook(fightHook, TosGameHooks.ACTION_FIGHT);

        CharacterInfoHook characterInfoHook = new CharacterInfoHook(tosTextGame);
        createInfoHook(characterInfoHook, TosGameHooks.ACTION_INFO_CHARACTER);
    }

    private void makeDefaultPages() {
        MainInfoWriter mainInfoWriter = new MainInfoWriter(sessionManager, applicationData);
        createInfoHook(mainInfoWriter, InfoHooks.SERVER);

        ItemLinkActionKotlin tosBaseItemInfo = new ItemLinkActionKotlin();
        createInfoHook(tosBaseItemInfo, BaseMinerActions.ITEM_LINK);

        constructHook(ItemInfoAction.class, BaseMinerActions.ITEM_INFO);


        constructHook(Opus.class, BaseMinerActions.OPUS);

        RestartPopupWriter restartPopupWriter = new RestartPopupWriter();
        createInfoHook(restartPopupWriter, PopupHooks.RESTART);

        ActionRestart actionRestart = new ActionRestart(sessionManager);
        createInfoHook(actionRestart, ActionHooks.RESTART);
    }
}
