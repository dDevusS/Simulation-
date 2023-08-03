package resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pathfinder {
	
	public static Coordinate findPath(Coordinate goal, Coordinate creature, WorldMapNew world) {
		List<Coordinate> avaibleCells=new ArrayList<>();
		List<Integer> listOfCellsPrice=new ArrayList<>();
		
		for (int y=-1; y<2; y++) {
			for (int x=-1; x<2; x++) {
				if (world.isEmpty(creature.shiftCell(x, y))) {
					if (y==-1&&x==-1||y==-1&&x==2||y==2&&x==-1||y==2&&x==-1) {
					listOfCellsPrice.add(14+calculateSteps(goal, creature));
					avaibleCells.add(creature);
					}
					else {
						listOfCellsPrice.add(10+calculateSteps(goal, creature));
						avaibleCells.add(creature);
					}
				}
			}
		}
		
		int index=0;
		int indexCell=0;
		
		for (Integer price: listOfCellsPrice) {
			while (index<listOfCellsPrice.size()-1&&listOfCellsPrice.size()!=1) {
				if (listOfCellsPrice.get(index)>listOfCellsPrice.get(index+1)) {
					indexCell=index;
				}
				else {indexCell=index+1;}
			}
		}
		
		return avaibleCells.get(indexCell);
	}
	
	public static Coordinate closedEmptyRandomCell(Coordinate creature, WorldMapNew world) {
		Random random=new Random();
		for (int counter=9; counter>0; counter--) {
			int randomX=random.nextInt(-1, 2);
			int randomY=random.nextInt(-1, 2);
			if (world.isEmpty(creature.shiftCell(randomX, randomY))) {
				return creature.shiftCell(randomX, randomY);
			}
		}
		return null;
	}
	
	private static int calculateSteps(Coordinate goal, Coordinate creature) {
		int countSteps=(Math.abs(goal.x-creature.x)+Math.abs(goal.y-creature.y))*10;
		return countSteps;
	}
}
