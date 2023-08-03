import java.util.*;

import resources.*;

public class Main {

	public static void main(String[] args) {
		
		Thread world=new Thread(new ActionWorld(30, 30));
		world.start();
	}
}
