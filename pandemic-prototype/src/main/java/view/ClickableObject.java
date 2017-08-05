package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import model.Location;

/**
 * class for all clickable objects in the view
 * @author jbghoul
 *
 */
public class ClickableObject {
	
	/**
	 * is the clickable object selected ?
	 */
	protected Boolean selected = false;
	/**
	 * Location where the clickable object belongs
	 */
	protected Location location;
	/**
	 * Size of the graphic representation of the clickable object
	 */
	protected Integer size;
	/**
	 * Object from the model package represented by this view class
	 */
	protected Object modelTarget;
	
    public void setSelected(Boolean selected){
    	this.selected = selected;
    }
	
	/**
	 * Constructor of Clickable Object
	 * @param location Location where the object takes place
	 * @param size Size of the graphic representation
	 * @param modelTarget model object represented by this view object
	 */
	public ClickableObject(Location location, Integer size, Object modelTarget)
	{
		this.location = location;
		this.size = size;
		this.modelTarget = modelTarget;
	}

	/**
	 * Draw the clickable object 
	 * @param graph the Graphics where to draw the object
	 */
    public void draw(Graphics2D graph) {
    	//TODO : stub
    	if(selected)
    	{
    		int[] pos=location.getPosition();
    		float dash[] = { 5.0f };
    		Stroke old= graph.getStroke();
    		graph.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
    		graph.setColor(Color.BLACK);
    		graph.drawOval(pos[0] - size/2-10, pos[1] - size/2-10 , size+20, size+20);
    		graph.setStroke(old);
    	}
	}
    
    /**
     * Try to get the model target by clicking on point(x,y)
     * @param x
     * @param y
     * @return the model target object if the clickable object contains (x,y), null if not
     */
    public Object click(double x, double y) {
    	// into circle (a²+ b² < c²)
    	int[] pos = location.getPosition();
    	if((Math.pow((pos[0]-x),2)+Math.pow((pos[1]-y),2)) < Math.pow(size, 2))
    		return modelTarget;
    	else
    		return null;
    }
    
}
