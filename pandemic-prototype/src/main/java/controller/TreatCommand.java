package controller;

import java.awt.Color;
import java.util.Map;

import model.Disease;
import model.Location;

public class TreatCommand
{
	private boolean eradicated = false;
	private Location location; // currentPlayer.getLocation();
	private Disease disease;
	
	public TreatCommand(Location currentLoc, Disease diseaseName)
	{
		location=currentLoc;
		disease=diseaseName;
	}
	
	public boolean execute(Location currentLoc, Disease diseaseName)
	{//TODO work on it
		if(currentLoc == location)
		{
			setEradicated(true);
		}
		return eradicated;
	}

	public boolean isEradicated()
	{
		return eradicated;
	}
	
	public void setEradicated(boolean eradicated)
	{
		this.eradicated = eradicated;
	}

}
