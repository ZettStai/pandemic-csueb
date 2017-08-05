package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.lang.annotation.Inherited;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import model.Disease;
import model.Location;

/**
 * Location view class
 * Represents the Location model class
 * @author jbghoul
 *
 */
public class LocationView extends ClickableObject{

	/**
	 * the Location from model package
	 */
	private Location location;
	/**
	 * Size of the location view
	 */
	private static Integer size = 25;

	/**
	 * Constructor
	 * @param location
	 */
    public LocationView(Location location){
    	super(location, size, location);
    	this.location = location;
    }
    
    public void draw(Graphics2D graph) {
    	super.draw(graph);
    	int[]pos=location.getPosition();
    	graph.setColor(location.getColor().brighter());
    	if(location.hasResearchCenter()) {
    		graph.fillRect(pos[0]-size/2, pos[1]-size/2, size, size);
    	} else {
    		graph.fillOval(pos[0] - size/2, pos[1] - size/2, size, size);
    	}
    	graph.setColor(Color.BLACK);
    	graph.drawString(location.getName(), pos[0]+size/2, pos[1]-size/2);
    	drawLocalDiseases(graph);
    }

	public void drawLinks(Graphics2D graph) {
		// TODO Auto-generated method stub
		for (Location loc : location.getAdjacentLocations()) {
			this.drawLinkTo(graph,loc);
		}
	}

	private void drawLinkTo(Graphics2D graph, Location loc) {
		// TODO Auto-generated method stub
		int[] pos1=this.location.getPosition();
		int[] pos2=loc.getPosition();
		graph.setColor(Color.WHITE);
		graph.drawLine(pos1[0], pos1[1], pos2[0], pos2[1]);
	}
	
	
	/**
	 * Draw colored squares for one local disease
	 * @param graph
	 * @param d the disease
	 * @param lefttop the (x,y) point, at the top left
	 * 0----> (x axe)
	 * | D |
	 * |---|
	 * | D |
	 * |---|
	 * | D |
	 * |---|
	 * v
	 * (y axe)
	 */
	private void drawLocalDisease(Graphics2D graph, Disease d, int[] lefttop) {
		Integer num = this.location.getInfections().get(d);
		int squaresize=Math.round(0.31f*size/2);
		for(int i=0; i<num.intValue(); i++) {
			graph.setColor(d.getColor());
			graph.fillRect(lefttop[0], lefttop[1], squaresize, squaresize);
			graph.setColor(Color.BLACK);
			graph.drawRect(lefttop[0], lefttop[1], squaresize, squaresize);
			lefttop[1]+=squaresize;
		}
	}
	
	private void drawLocalDiseases(Graphics2D graph) {
		Set<Disease> sa = location.getInfections().keySet();
		ArrayList<Disease> da = new ArrayList<Disease>(sa);
		assert(da.size()==4);
		int pos[] = location.getPosition();
		int small = Math.round(0.31f*size/2);
		int large = Math.round(0.93f*size/2);
		drawLocalDisease(graph, da.get(0), new int[]{pos[0],pos[1]});
		drawLocalDisease(graph, da.get(1), new int[]{pos[0]-small,pos[1]});
		drawLocalDisease(graph, da.get(2), new int[]{pos[0],pos[1]-large});
		drawLocalDisease(graph, da.get(3), new int[]{pos[0]-small,pos[1]-large});
	}
}