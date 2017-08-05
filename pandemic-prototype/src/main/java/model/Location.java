package model;

import java.awt.Color;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import controller.GameController;

public class Location {
	private String name;
	private Set<Location> adjacentLocations;
	private boolean hasResearchCenter=false;
	private Color color = Color.BLUE;
	private int[] position= {0,0};

	private Map<Disease, Integer> infection;
	/**Olsen -- 
	 * created private data structures to store infections.
	 * created methods to control the previously added structures
	 *
	 */
	
	public Location(String name) {
		this.name=name;
		adjacentLocations = new HashSet<Location>();
		infection = new HashMap<Disease, Integer>();
		for (Disease disease : Disease.getDiseases()) {
			infection.put(disease, 0);
		}
	}
	
	public Location(String name, Set<Location> locations) {
		this.name=name;
		this.adjacentLocations=locations;
	}

	public String getName() {
		return name;
	}infectionDeck action = null;
	public Set<Location> getAdjacentLocations() {
		return adjacentLocations;
	}
	
	public void addAdjacentLocation(Location location) {
		adjacentLocations.add(location);
		location.adjacentLocations.add(this);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean hasResearchCenter() {
		return hasResearchCenter;
	}

	public void setResearchCenter(boolean hasResearchCenter) {
		this.hasResearchCenter = hasResearchCenter;
	}
	
	public void setPosition(int x, int y){
		position[0]=x;
		position[1]=y;
}

	
	public int[] getPosition() {
		return position;

	}
	
	
	
	static Set<Location> alreadyInfected = new HashSet<Location>();
	
	public void infect(Disease disease) {
		if( infection.get(disease) != null ) {
			if(infection.get(disease) < 3) {
				Integer i=infection.get(disease);
				infection.put(disease, new Integer(i.intValue()+1));
			} else if(infection.get(disease) == 3) {
				//Spread the disease
				alreadyInfected.add(this);
				GameController.notifyOutbreak(this);
				spreadDisease(disease);
				alreadyInfected.clear();
			}
		}
	}
	
	public void infectWhileSpreading(Disease disease) {
		if( infection.get(disease) != null ) {
			alreadyInfected.add(this);
			if(infection.get(disease) < 3) {
				Integer i=infection.get(disease);
				infection.put(disease, new Integer(i.intValue()+1));
			} else if(infection.get(disease) == 3) {
				GameController.notifyOutbreak(this);
				spreadDisease(disease);
			}
		}
	}
	
	public void infectEpidemic(Disease disease) {
		if( infection.get(disease) != null ) {
			alreadyInfected.add(this);
		}
		infection.put(disease, 3);
		GameController.notifyOutbreak(this);
		spreadDisease(disease);
	}
	
	/**
	 * Spread disease to adjacent locations
	 * @param disease
	 */
	private void spreadDisease(Disease disease) {
		System.out.println("Spreading from "+this.name);
		//for each adjacent location :
		for( Location currentLoc : getAdjacentLocations())
		{
			// check if location is in alreadyInfected set
			if (alreadyInfected.contains(currentLoc)) {
				if(infection.get(disease) < 3) {
					Integer i=infection.get(disease);
					infection.put(disease, new Integer(i.intValue()+1));
				} 
			}
			// if not, call infectWhileSpreading
			else {
				currentLoc.infectWhileSpreading(disease);
			}
		}
	}

	public boolean treat(Disease disease) {
		if( infection.get(disease) != null ) {
			if(infection.get(disease) > 0) {
				if(disease.isCured()) {
					infection.put(disease, new Integer(0));
				} else {
					Integer i=infection.get(disease);
					infection.put(disease, new Integer(i.intValue()-1));					
				}				
				return true;
			}
		}
		return false;
	}
	
	@Deprecated
	public void infect(infectionCard drawn){
		Disease disease = drawn.getDisease();
		Integer cube = 0;
		if (this.infection.isEmpty()){
			this.infection.put(disease, 1);
		}
		else {
			
			if (this.infection.containsKey(disease)){
			  cube = this.infection.get(disease);
			  if (cube < 3){
				  cube++;
				  this.infection.put(disease, cube);
			  }
				  
			  else{//call epidemic
			  }
			  
			}
		}
		
			
		
	}


public Map<Disease, Integer> getInfections() {
	return infection;
} 
}
