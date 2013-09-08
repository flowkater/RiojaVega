package server.dispatcher;

public class ProactorThreadPool {

	private ProactorIOThread[] threadList;
	
	public ProactorThreadPool(int threadLength) {
		
		threadList = new ProactorIOThread[threadLength];
		
		for(int i = 0; i < threadLength ; i++) {
			
			threadList[i] = new ProactorIOThread(i);
			
		}
		
	}
	
	public synchronized ProactorIOThread getThread() {
		
		ProactorIOThread result = null;
		
		while(result == null) {
			
			for(int i = 0 ; i < threadList.length; i++) {
				if ( !threadList[i].isRun() ) {
					System.out.println(i + "th Thread return  " + threadList[i] + " " + threadList[i].isRun());
					result = threadList[i];
					result.setRun(true);
					break;
				}
				
			}
			
		}
		
		System.out.println(result);
		
		return result;
		
	}
	
}
