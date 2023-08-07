package resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import creatures.Creatures;

public class Pathfinder {
	
	public static Coordinate findPath(Coordinate goal, Coordinate creature, Simulation world) {
		List<Coordinate> avaibleCells=new ArrayList<>();
		List<Integer> listOfCellsPrice=new ArrayList<>();
		
		if(getClosedEmptyRandomCell(creature, world)==null) {
			return null;
		}
		
		for (int y=-1; y<2; y++) {
			for (int x=-1; x<2; x++) {
				if (world.isEmpty(creature.shiftCell(x, y))) {
					if (y==-1&&x==-1||y==-1&&x==1||y==1&&x==-1||y==1&&x==-1||y==1&&x==1) {
					listOfCellsPrice.add(14+calculateSteps(goal, creature.shiftCell(x, y)));
					avaibleCells.add(creature.shiftCell(x, y));
					}
					else {
						listOfCellsPrice.add(10+calculateSteps(goal, creature.shiftCell(x, y)));
						avaibleCells.add(creature.shiftCell(x, y));
					}
				}
			}
		}
		
		int index=0;
		int indexCell=0;
		
		if(!listOfCellsPrice.isEmpty()&&!avaibleCells.isEmpty()) {
			int lowestCell=listOfCellsPrice.get(index);
			for (Integer price: listOfCellsPrice) {
				while (index<listOfCellsPrice.size()-1&&listOfCellsPrice.size()!=1) {
					if (lowestCell>listOfCellsPrice.get(index+1)) {
						indexCell=index+1;
						lowestCell=listOfCellsPrice.get(index+1);
					}
					index++;
				}
			}
			return avaibleCells.get(indexCell);
		}
		return null;
	}
	
	public static Coordinate getClosedEmptyRandomCell(Coordinate creature, Simulation world) {
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
	
	public static boolean isClosedCell(Coordinate goalCell, Creatures creature, Simulation world) {
		boolean isClosedCell;
		if(Math.abs(goalCell.x-creature.getCoordinate().x)<=1&&Math.abs(goalCell.y-creature.getCoordinate().y)<=1) {
			return isClosedCell=true;
		}
		else {
			return isClosedCell=false;
		}
	}
	
	public static int calculateSteps(Coordinate goal, Coordinate closedEmptyCell) {
		int countSteps=(Math.abs(goal.x-closedEmptyCell.x)+Math.abs(goal.y-closedEmptyCell.y))*10;
		return countSteps;
	}
}
