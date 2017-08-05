package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorldMap {
//    - Location(list) : to map all the cities on the board
//    - Disease        : tracks status of a disease
//    - Outbreak      :  tracks number of outbreaks so far,simple int
//    - Infection       : tracks the infection level at present time.
//    - NumRC        : number on Research Centers on the world map currently
//	  - infections   : tracks what cities are infected and how many cubes they have. <city , <disease, cubes>>
	
	private Map<String,Location> locations;
	//private Map<String, Map<String, Integer>> infections;
	private int outbreaks=0;
	private int infectionRate=0;
	private int researchCenters=0;
	
	public WorldMap() {
		locations=new HashMap<String, Location>();
	//	infections=new HashMap<String, Map<String, Integer>>();
	}
	
	/*public void addInfections(String city, String disease, int cubes){
		Map <String, Integer> temp = new HashMap<String,Integer>();
		temp.put(disease, cubes);
	//	infections.put(city, temp);
	}
	
	/**public Collection<Map<String, Integer>> getInfections(){
		return infections.values();
	}
	**/
	public void addLocation(Location location){
		locations.put(location.getName(), location);
	}
	
	public Collection<Location> getLocations() {
		return locations.values();
	}
	
	public Location getLocationByName(String name) {
		return locations.get(name);
	}
	
	public void increaseInfectionRate() {
		infectionRate++;
	}
	public int getInfectionRate() {
		return infectionRate;
	}
	public void increaseOutbreaks() {
		outbreaks++;
	}
	public int getOutbreaks() {
		return outbreaks;
	}
	
	public void Outbreak () {
		//TODO: Stub
		// Increase outbreak tracker
		// Place a cube in each connection city
		// If there are already 3 cubes, do a chain outbreak
		// If outbreak goes fill to (8th outbreak), game over
		//- LS
	}	

}
