package org.usfirst.frc.team3952.robot;

import edu.wpi.first.wpilibj.*;

public class Ladder {
	private Talon ladder, coiler;
	
	public Ladder(Talon ladder, Talon coiler) {
		this.ladder = ladder;
		this.coiler = coiler;
	}
	
	public boolean extendLadder() {			// TODO
		boolean ladderIsExtendedAllTheWay = true;		// calculate this probably with ultrasonic stuff(temporary variable)
		boolean clawIsExtendedAllTheWay = true;			// same thing
		if(ladderIsExtendedAllTheWay) {
			ladder.set(0);
			coiler.set(0);
		} else {
			if(clawIsExtendedAllTheWay) {
				ladder.set(1);				// more power & set to a reasonable value
			} else {
				ladder.set(0.5);			// less power & set to a reasonable value
			}
			coiler.set(0.5);
		}
		return ladderIsExtendedAllTheWay;
	}
	
	public boolean retractLadder() {
		boolean ladderIsRetractedAllTheWay = true;		// calculate this probably with ultrasonic stuff(temporary variable)
		if(ladderIsRetractedAllTheWay) {
			ladder.set(0);
			coiler.set(0);
		} else {
			ladder.set(-0.5);				// set to a reasonable value
			coiler.set(-0.5);				// set to a reasonable value
		}
		return ladderIsRetractedAllTheWay;
	}
	
	public void openClaw() {
		coiler.set(-1);				// set to a reasonable value
	}
	
	public void closeClaw() {
		// TODO
	}
}