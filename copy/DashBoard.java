package org.usfirst.frc.team3952.robot;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashBoard {
	private Map<String,String> stringLog; // All Error Logs
	private Map<String,Boolean> booleanLog;
	private Map<String, Double> numberLog;
	
	private ImageProcess i;
	
	SmartDashboard b; // Main Dashboard
	public DashBoard(){
    	stringLog = new TreeMap<String,String>();
    	booleanLog = new TreeMap<String,Boolean>();
    	numberLog = new TreeMap<String,Double>();
    	b = new SmartDashboard();
    	i=new ImageProcess();
	}
	
	public void updateDashboard() {
    	Iterator<String>  stringLogIterator = stringLog.keySet().iterator();
    	Iterator<String> booleanLogIterator = booleanLog.keySet().iterator();
    	Iterator<String> integerLogIterator = numberLog.keySet().iterator();
    	while(stringLogIterator.hasNext()){
    		String key = stringLogIterator.next();
    		String value = stringLog.get(key);
    		b.putString(key, value);
    	}
        while(booleanLogIterator.hasNext()){
    	   String key = booleanLogIterator.next();
    	   boolean value = booleanLog.get(key);
    	   b.putBoolean(key, value);
       }   	
        while(integerLogIterator.hasNext()){
     	   String key = integerLogIterator.next();
     	   double value = numberLog.get(key);
     	   b.putNumber(key, value);
        }   	
    }
	public void addStringLog(String key, String value){
		stringLog.put(key, value);
	}
	public void addBooleanLog(String key, boolean value){
		booleanLog.put(key, value);
	}
	public void addNumberLog(String key, double value){
		numberLog.put(key,value);
	}
}
