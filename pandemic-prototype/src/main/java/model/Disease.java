package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Disease {
	
	//test
	/**
	 * Static field with all the existing diseases
	 */
	static private Set<Disease> diseases = new HashSet<Disease>();
	
	private String name;
	private Color color;
	private int cubes;
	private boolean cured=false;
	
	/**
	 * Create a disease
	 * @param color color of the disease
	 * The new disease is then add to the static set of disease
	 */
	public Disease(Color color) {
		this.color=color;
		diseases.add(this);
	}
	
	/**
	 * Get the set of all existing diseases
	 * @return set of all diseases
	 */
	public static Set<Disease> getDiseases() {
		return diseases;
	}
	
	/**
	 * Get a disease by its color
	 * @param color color of the disease
	 * @return one disease that matches the color, or null if any
	 */
	public static Disease getDiseaseByColor(Color color) {
		for (Disease d : diseases) {
			if(d.getColor().equals(color)) return d;
		}
		return null;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public int getCubes() {
		return cubes;
	}
	public void setCubes(int cubes) {
		this.cubes = cubes;
	}
	public boolean isCured() {
		return cured;
	}
	public void setCured(boolean cured) {
		this.cured = cured;
	}
	
	public String toString() {
		if(color==Color.RED) {
			return "red";
		} else if (color==Color.BLUE){
			return "blue";
		} else if (color==Color.YELLOW) {
			return "yellow";
		} else if (color==Color.GREEN) {
			return "green";
		} else return color.toString();
	}
	
	
	
}

