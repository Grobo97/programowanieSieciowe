package lab1;

public class test {

	public static void main(String[] args) {
		KeyProtocol kP = new KeyProtocol();
		String r1 = kP.encodeMessage("key_in-set {tajneHaslo : 123456789}");
		System.out.println(r1);
		String r2 = kP.encodeMessage("key_in-set {tajneHaslo : 987654321}");
		System.out.println(r2);
		String r3 = kP.encodeMessage("key_in-get {123456789}");
		System.out.println(r3);
		String r4 = kP.encodeMessage("key_in-get {123456711}");
		System.out.println(r4);
		String r5 = kP.encodeMessage("key-in-get {123456711}");
		System.out.println(r5);
		String r6 = kP.encodeMessage("key_in-set {tajnehaslo : 978654321}");
		System.out.println(r6);
		String r7 = kP.encodeMessage("key_in-set {tajneHaslo : 987654321}");
		System.out.println(r7);
	}

}
