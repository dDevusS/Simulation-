package resources;

public class WorldMapRender {

	public static void doVievWorld(WorldMapNew world) {
		
		//🐅🐆🐂🐃🐄🐖🐐🐕🐒🦍🐮🐷🐀🐇🦖🥓🥩🍗🍖🍊🍎
		
		for(int ySize=0; ySize<=world.getHeight(); ySize++) {
			for(int xSize=0; xSize<=world.getWidth(); xSize++) {
				if (world.getMap().containsKey(Coordinate.doCoordinate(xSize, ySize))) {
					System.out.print(world.getMap().get(Coordinate.doCoordinate(xSize, ySize)).getMapSimbol());
				}
				//➖⬛⬜🟩🟨🟧🟥🟩🟦🟪🟫🔘🔴🟠⚫🟤🟣🔵⚪〰️
				else {System.out.print("🟫");}
				if(xSize==world.getWidth()) {System.out.print("\n");}
			}
		}
	}
}
