package model;

public class Role
{
	private String name;
	private String advantage;
	
	public String getRoleName()
	{
		return name;
	}
	
	public String getRoleAdv()
	{
		return advantage;
	}
	
	public void setRoleName(String name)
	{
		this.name = name;
	}
	
	public void setRoleAdv(String advantage)
	{
		this.avantage = avantage;
	}
	
	public contingencyPlanner()
	{
		name = "Contingency Planner";
		advantage = "The Contingency Planner may, as an action, take an Event card from anywhere in the Player Discard Pile and place it on his Role card. Only 1 Event card can be on his role card at a time. It does not count against his hand limit. When the Contingency Planner plays the Event card on his role card, remove this Event card from the game (instead of discarding it).";
	}
	
	public operationsExpert()
	{
		name = "Operations Expert";
		advantage = "The Operations Expert may, as an action, either:build a research station in his current city without discarding (or using) a City card, or once per turn, move from a research station to any city by discarding any City card.";
	}
	
	public dispatcher()
	{
		name = "Dispatcher";
		advantage = "The Dispatcher may, as an action, either: move any pawn, if its owner agrees, to any city containing another pawn, or move another player’s";
	}
	
	public quarantineSpecialist()
	{
		name = "Quarantine Specialist";
		advantage = "The Quarantine Specialist prevents both outbreaks and the placement of disease cubes in the city she is in and all cities connected to that city. She does not affect cubes placed during setup.";
	}
	
	public medic()
	{
		name = "Medic";
		advantage = "The Medic removes all cubes, not 1, of the same color when doing the Treat Disease action.If a disease has been cured, he automatically removes all cubes of that color from a city, simply by entering it or being there. This does not take an action.";
	}
	
	public researcher()
	{
		name = "Researcher";
		advantage = "As an action, the Researcher may give any City card from her hand to another player in the same city as her, without this card having to match her city. The transfer must be from her hand to the other player’s hand, but it can occur on either player’s turn";
	}
	
	public scientist()
	{
		name = "Scientist";
		advantage = "The Scientist needs only 4 (not 5) City cards of the same disease color to Discover a Cure for that disease.";
	}
}