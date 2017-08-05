package view;

import java.awt.Color;
import java.awt.Graphics2D;

import model.Player;

/**
 * Player view class
 * Represents the player model class
 * @author jbghoul
 *
 */
public class PlayerView extends ClickableObject {

    private Player player;
    private static Integer size = 10;
    
    public PlayerView(Player player){
    	super(player.getLocation(), size, player);
    	this.player = player;
    }

    public void draw(Graphics2D graph) {
    	super.draw(graph);
    	int[]pos=location.getPosition();
    	graph.setColor(player.getColor());
    	graph.fillOval(pos[0] - size/2, pos[1] - size/2, size, size);
    	graph.drawString(player.getName(), pos[0] - size*3, pos[1] + size*2);
    }

	public Player getPlayer() {
		return player;
	}
    
    

}