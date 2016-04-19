package GUI;

public class Main {

	static String ipAddress = "100.81.132.41";
	static int socketPort = 6000;

	public static void main(String[] args) {
		// try {
		// PCClient client = new PCClient(ipAddress, socketPort);
		// } catch (UnknownHostException e) {
		// System.out.println("Error " + e.getMessage());
		// } catch (IOException e) {
		// System.out.println("Error " + e.getMessage());
		// }
		UserInterface userInterface = new UserInterface();
	}
}