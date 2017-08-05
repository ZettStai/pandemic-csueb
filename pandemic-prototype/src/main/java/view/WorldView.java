package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Vector;

import javax.swing.JComponent;

import controller.GameController;

import model.Location;
import model.Player;
import model.WorldMap;

public class WorldView extends JComponent {
	private Vector<LocationView> locations;
    private Vector<PlayerView> players;
    private WorldMap world;
    private ClickableObject clickedObject=null;
    
    public WorldView(WorldMap world) {
    	this.world = world;    	
    	locations=new Vector<LocationView>();
    	players = new Vector<PlayerView>();
    }
    
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		//g.setColor(Color.RED);
		//g.fillRect(30, 30, 40, 40);
	}

	public Object clickCanvas(Integer x, Integer y) {
		// TODO Auto-generated method stub
		for (LocationView locationView : locations) {
			Object clicked=locationView.click(x, y);
			if(clicked!=null){
				selectObject(locationView);
				return clicked;
			}
		}
		return null;
	}
	
	private void selectObject(ClickableObject obj) {
		// TODO Auto-generated method stub
		if(clickedObject!=null){
			clickedObject.setSelected(false);
		}
		if(obj != null) {
			obj.setSelected(true);
			clickedObject=obj;
		} else clickedObject=null;
		repaint();		
	}
	
	public void unselect() {
		selectObject(null);
	}

	public ClickableObject getClickedObject() {
		return clickedObject;
	}

	public void update() {
		repaint();
	}
	public void updatePlayers() {
		players = new Vector<PlayerView>();
		for (Player player : GameController.getPlayers()) {
			players.add(new PlayerView(player));
		}
		repaint();
	}
	
	public void updateWorld() {
		//TODO stub
		players = new Vector<PlayerView>();
		locations = new Vector<LocationView>();
		for (Location location : world.getLocations()) {
			locations.add(new LocationView(location));
		}
		for (Player player : GameController.getPlayers()) {
			players.add(new PlayerView(player));
		}
		repaint();
	}
	
	public void draw(Graphics graph) {
		//TODO stub
		graph.drawString(this.toString(), 0, 0);
	}
	
	@Override
	public void paintComponent(Graphics g){
		//TODO stub
		// Links between locations
		for(LocationView locationView : locations) {
			locationView.drawLinks((Graphics2D) g);
		}
		// Location view
		for (LocationView locationView : locations) {
			locationView.draw((Graphics2D) g);
		}
		for (PlayerView playerView : players) {
			playerView.draw((Graphics2D) g);
		}
	}
	
}
