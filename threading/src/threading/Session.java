package threading;

import lombok.Getter;
import lombok.Setter;

public class Session extends Thread
{
	public int id;
    protected Boolean isExit = false;

    @Setter @Getter
    protected long sleepTime = 100;

    public Session(Runnable target, String name, int id, int sleepTime)
    {
        super(target, name);
        this.id = id;
        this.sleepTime = sleepTime;
    }

    public Session(Runnable target, String name, int id)
    {
        super(target, name);
        this.id = id;
    }

	public Session(String name, int id)
	{
		super(name);
		this.id = id;
	}

    @Override
    public void destroy()
    {
        if(!isExit)
            stopSession();
    }

    @Override
	public void run() {

		while (!isExit)
		{
            super.run();
            afterRun();

            try
            {
                Thread.sleep(sleepTime);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
		}
	}

    public void afterRun()
    {

    }

    public void stopSession()
    {
        isExit = true;
    }

    public Boolean getIsExit() {
        return isExit;
    }
}
