package HelloApp;

import org.omg.CORBA.ORB;


public class HelloImpl extends HelloPOA{

	private ORB orb;
	
	public void setORB(ORB orb) {
		this.orb = orb;
	}

	public String sayHello() {
		return "Hello World";
	}

	public void shutdown() {
		
		try{
			orb.shutdown(false);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
