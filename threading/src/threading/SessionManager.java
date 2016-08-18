package threading;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SessionManager
{
	public static ExecutorService executor = Executors.newCachedThreadPool();

	private HashMap<Integer, Session> sessionsPool = new HashMap<Integer, Session>();

	public void removeSession(Session session)
    {
        sessionsPool.remove(session.id);
        session.stopSession();
        session.interrupt();
    }

	public void addSession(Session session)
	{
		sessionsPool.put(session.id, session);

		executor.execute(session);
	}

    public HashMap<Integer, Session> getSessionsList() {
        return sessionsPool;
    }

	public Session getSession(int sessionId)
	{
		return sessionsPool.get(sessionId);
	}
}
