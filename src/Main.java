import java.util.*;

import creatures.Cattle;
import creatures.Creatures;
import creatures.Herbivore;
import items.Grass;
import resources.*;

public class Main {

	public static void main(String[] args) { 
		Thread world=new Thread(new ActionWorld(30, 30));
		world.start();
		
	}
}
