package org.usfirst.frc.team3952.robot;

public class MultiTask extends Task {

	private Task t1, t2;
	
	public MultiTask(Task t1, Task t2){
		this.t1 = t1;
		this.t2 = t2;
		
	}
	
	@Override
	public boolean run() {
		boolean b1 = true;
		boolean b2 = true;
		
		if(t1 != null){
			b1 = t1.run();
		}
		if(t2 != null){
			b2 = t2.run();
		}
		
		return b1 && b2;
	}

	@Override
	public void cancel() {
		t1.cancel();
		t2.cancel();
	}

	@Override
	public String toString() {
		return "MultiTask: " + t1 + ", " + t2;
	}

}
