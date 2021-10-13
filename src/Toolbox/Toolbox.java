package Toolbox;

import java.awt.Point;
import java.util.Random;

public class Toolbox {
	
	/*
	private void calculateAngle(Point target) {
		  // calculate the angle from the center of the image
		  float deltaY = target.y - (imageLocation.y + bi.getHeight() / 2);
		  float deltaX = target.x - (imageLocation.x + bi.getWidth() / 2);
		  angle = (float) Math.atan2(deltaY, deltaX);
		  if (angle < 0) {
		     angle += (Math.PI * 2);
		  }
	}
	
	angle = Math.toRadians(Math.toDegrees(angle) + 180.0);
	*/

	public static String generateRandomString(int len) {
		String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%&";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(chars.charAt(rnd.nextInt(chars.length())));
		return sb.toString();
	}
	
	public static float getAngle2(Point start, Point end) {
	    float angle = (float) Math.toDegrees(Math.atan2(start.y - end.y, start.x - end.x));
	    
	    float yD = start.y - end.y;
	    float xD = start.x - end.x;
	    
	    float rad = yD/xD;
	    
	    angle = (float)Math.toDegrees(rad);
	    
/*
	    if(angle < 0){
	        angle += 360;
	    }
^*/
	    return angle;
	}
	
	/**
	 * Calculates the angle from centerPt to targetPt in degrees.
	 * The return should range from [0,360), rotating CLOCKWISE, 
	 * 0 and 360 degrees represents NORTH,
	 * 90 degrees represents EAST, etc...
	 *
	 * Assumes all points are in the same coordinate space.  If they are not, 
	 * you will need to call SwingUtilities.convertPointToScreen or equivalent 
	 * on all arguments before passing them  to this function.
	 *
	 * @param centerPt   Point we are rotating around.
	 * @param targetPt   Point we want to calcuate the angle to.  
	 * @return angle in degrees.  This is the angle from centerPt to targetPt.
	 */
	public static double getAngle(Position centerPt, Position targetPt)
	{
	    // calculate the angle theta from the deltaY and deltaX values
	    // (atan2 returns radians values from [-PI,PI])
	    // 0 currently points EAST.  
	    // NOTE: By preserving Y and X param order to atan2,  we are expecting 
	    // a CLOCKWISE angle direction.  
	    double theta = Math.atan2(targetPt.y - centerPt.y, targetPt.x - centerPt.x);

	    // rotate the theta angle clockwise by 90 degrees 
	    // (this makes 0 point NORTH)
	    // NOTE: adding to an angle rotates it clockwise.  
	    // subtracting would rotate it counter-clockwise
	    theta += Math.PI/2.0;

	    // convert from radians to degrees
	    // this will give you an angle from [0->270],[-180,0]
	    double angle = Math.toDegrees(theta);

	    // convert to positive range [0-360)
	    // since we want to prevent negative angles, adjust them now.
	    // we can assume that atan2 will not return a negative value
	    // greater than one partial rotation
	    if (angle < 0) {
	        angle += 360;
	    }

	    return angle;
	}
	
} 
