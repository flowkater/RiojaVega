package server.dispatcher;

import java.io.InputStream;

public class ProactorIOThread extends Thread {

	private Integer index;
	
	private InputStream is;

	private EventHandler eventHandler;

	private boolean run = false;

	
	
	public ProactorIOThread(Integer index) {
		super();
		this.index = index;
	}

	public boolean isRun() {
		return run;
	}

	public InputStream getIs() {
		return is;
	}

	public void setIs(InputStream is) {
		this.is = is;
	}

	public EventHandler getEventHandler() {
		return eventHandler;
	}

	public void setEventHandler(EventHandler eventHandler) {
		this.eventHandler = eventHandler;
	}

	public ProactorIOThread setHandler(EventHandler handler, InputStream is) {

		setEventHandler(handler);
		setIs(is);
		return this;
	}

	@Override
	public void run() {
		System.out.println(index  + " run is true");
		run = true;
		eventHandler.handleEvent(is);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		run = false;
		System.out.println(index  + " run is false");
		
		
	}

	public void setRun(boolean b) {
		run = true;
	}

}
