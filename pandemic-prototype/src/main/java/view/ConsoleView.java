package view;

import java.io.OutputStream;

import model.Location;
import model.Player;
import model.WorldMap;

public class ConsoleView {
	
	public void printCurrentInfection(WorldMap w){
		
		System.out.println("Infection Status: ");
		//System.out.println(w.getInfections());
	}
	
	public void printPlayerPostion(Player p) {
		String ret = new String();
		ret+=p.getName()+" at "+p.getLocation().getName();
		System.out.println(ret);
	}
	
	public void printAdjacentLocations(Location loc) {
		String ret = new String();
		for (Location l : loc.getAdjacentLocations()) {
			ret+=l.getName()+", ";
		}
		System.out.print("Adjacent locations : ");
		System.out.println(ret);
		
	}

}
