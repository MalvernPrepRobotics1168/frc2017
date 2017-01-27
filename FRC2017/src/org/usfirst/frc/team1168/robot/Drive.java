package org.usfirst.frc.team1168.robot;

import edu.wpi.first.wpilibj.*;

public class Drive {
	private static Talon rightB;
	private static Talon rightF;
	private static Talon leftB;
	private static Talon leftF;
	
	public static void init(int rF, int rB, int lF, int lB){
		rightB = new Talon(3);
		rightF = new Talon(2);
		leftF = new Talon(0);
		leftB = new Talon(1);
	}
	
	public static void tank(int rs, int ls){
		rightB.set(Robot.xbox.getRawAxis(rs)/4);
		rightF.set(Robot.xbox.getRawAxis(rs)/4);
		leftB.set(-Robot.xbox.getRawAxis(ls)/4);
		leftF.set(-Robot.xbox.getRawAxis(ls)/4);
	}
	
	public static void mecanum(){
		double lsY, lsX, rsX;
		lsY=Robot.xbox.getRawAxis(1);
		lsX=Robot.xbox.getRawAxis(0);
		rsX=Robot.xbox.getRawAxis(4);
		
		if(lsY+lsX-rsX > 1 || -1 > lsY+lsX-rsX){
			rightB.set(scale(-3,3,1,-1,lsY+lsX-rsX));
		} else {
			rightB.set(lsY+lsX-rsX);
		}
		
		if(lsY-lsX-rsX > 1 || -1 > lsY-lsX-rsX){
			rightF.set(scale(-3,3,1,-1,lsY-lsX-rsX));
		} else {
			rightF.set(lsY-lsX-rsX);
		}

		if(lsY+lsX+rsX > 1 || -1 >lsY+lsX+rsX){
			leftF.set(scale(-3,3,1,-1,lsY+lsX+rsX));
		} else {
			leftF.set(lsY+lsX+rsX);
		}
		
		if(lsY-lsX+rsX > 1 || -1 > lsY-lsX+rsX){
			leftB.set(scale(-3,3,1,-1,lsY-lsX+rsX));
		} else {
			leftB.set(lsY-lsX+rsX);	
		}
	}
	
	private static double scale(double min, double max, double b, double a, double x){
		return (((b-a)*(x-min))/(max-min))+a;
	}
}
