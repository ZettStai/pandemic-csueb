package controller;

import java.util.Map;
import model.Location;
import model.Disease;

public class TreatCommand implements Command
{
	private Color color;
	private int cubeAmount;
	private boolean eradicated;

	public TreatCommand(String CurrentLocation, String DiseaseName)
	{
		if (CurrentLocation == LocationName)
		{
			DiseaseName.setEradicated(true);
			return true;
		}
		return DiseaseName.getEradicated();
	}
	
	public Color getColor()
	{
		return color;
	}
	public void setColor(Color color)
	{
		this.color = color;
	}
	public int getCubeAmount;
	{
		return cubeAmount;
	}
	public void setCubeAmount(int cubeAmount)
	{
		this.cubeAmount = cubeAmount;
	}
	public boolean isEradicated
	{
		return eradicated;
	}
	public void setEradicated(boolean eradicated)
	{
		this.eradicated = eradicated;
	}
}
