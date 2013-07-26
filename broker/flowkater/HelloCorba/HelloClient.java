import HelloApp. *;
import org.omg.CosNaming. *;
import org.omg.CosNaming.NamingContextPackage. *;
import org.omg.CORBA. *;

public class HelloClient{
	static Hello helloImpl;

	public static void main(String[] args) {
		try{
			// ORB 만들고 초기화
			ORB orb = ORB.init(args, null);

			// root naming context 가져오기
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");

			// Use NamingContextExt instead of NamingContext.
			// This is part of the Interoperable naming Service.
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			// 네이밍에서 객체 참조 결합
			String name = "Hello";
			helloImpl = HelloHelper.narrow(ncRef.resolve_str(name));
			System.out.println("Obtained a handle on server object: " + helloImpl);

			// 오퍼레이션 호출
			System.out.println(helloImpl.sayHello());
			helloImpl.shutdown();
		}catch(Exception e){
			System.out.println("ERROR : " + e);
			e.printStackTrace(System.out);
		}
	}
}