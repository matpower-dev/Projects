

public class Executor {
	
	static long start = System.currentTimeMillis();
	
	public static void main(String[] args) {
	@SuppressWarnings("unused")
	UserInterface userInterface =new UserInterface();
	System.out.println("Building the board took " + (System.currentTimeMillis() - start) + "ms");
	
    }
}
