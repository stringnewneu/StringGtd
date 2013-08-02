package com.string.gtd.core;

public class GtdStateTag {
	public static final GtdStateTag Working = new GtdStateTag("Working");
	public static final GtdStateTag Resting = new GtdStateTag("Resting");
	public static final GtdStateTag PomoWorking = new GtdStateTag("PomoWork", Working);
	public static final GtdStateTag PomoResting = new GtdStateTag("PomoResting", Working);
	
	private String stateName = null;
	@SuppressWarnings("unused")
	private GtdStateTag parent = null;
	
	public GtdStateTag(String stateName){
		this.stateName = stateName;
	}

	public GtdStateTag(String stateName, GtdStateTag parent) {
		this.stateName = stateName;
		this.parent = parent;
	}
	
	public String toString(){
		return stateName;
	}
}
