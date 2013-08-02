package com.string.gtd.core;

import java.util.ArrayList;

public class GtdState {
	public ArrayList<GtdStateTag> stateTags = new ArrayList<GtdStateTag>();
	
	public String toString(){
		String result = null;
		
		for (GtdStateTag tag : stateTags) {
			if (result == null)
				result = tag.toString();
			else
				result = result + "." + tag.toString();
		}
		
		return result;
	}

	public void addStateTag(GtdStateTag stateTag) {
		stateTags.add(stateTag);
	}
}
