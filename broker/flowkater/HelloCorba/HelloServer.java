import HelloApp. *;
import org.omg.CosNaming. *;
import org.omg.CosNaming.NamingContextPackage. *;
import org.omg.CORBA. *;
import org.omg.PortableServer. *;
import org.omg.PortableServer.POA;
import java.util.Properties;

/*
	HelloImple 서번트
*/
class HelloImpl extends HelloPOA{
	private ORB orb;

	public void setORB(ORB orb_val){
		orb = orb_val;
	}

	// sayHello() method 구현
	public String sayHello(){
		return "\nHello World!! \n";
	}

	// shutdown() method 구현
	public void shutdown(){
		orb.shutdown(false);
	}
}


/*
	서버 클래스
*/
public class HelloServer{
	public static void main(String[] args) {
		try{
			// ORB 만들고 초기화
			ORB orb = ORB.init(args, null);

			// rootpoa 에 참조를 가져오고 POAManager 활성화
			// 「루트 POA」 는, ORB 에 의해 관리되어 ORB 초기화 인터페이스를 사용하는 어플리케이션에 「RootPOA」라고 하는 초기 객체명으로 제공
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			// POAManager POA 처리상태를 제어
			rootpoa.the_POAManager().activate();

			// servant 를 만들고 그것을 ORB 와 함께 등록
			HelloImpl helloImpl = new HelloImpl();
			helloImpl.setORB(orb);

			// servant 로 부터 참조 객체 가져오기
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(helloImpl);
			Hello href = HelloHelper.narrow(ref);

			// root 네이밍 context 가져오기
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");

			// Use NamingContextExt which is part of the Interoperable
			// Naming Service (INS) specification.
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			// bind the Object Reference in Naming 네이밍에서 객체 참조를 바인딩
			String name = "Hello";
			NameComponent path[] = ncRef.to_name(name);
			ncRef.rebind(path, href);
			System.out.println("HelloServer ready and waiting...");

			// 클라이언트로부터 호출될때까지 대기
			orb.run();
		}catch(Exception e){
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}

		System.out.println("HelloServer Exiting...");
	}
}