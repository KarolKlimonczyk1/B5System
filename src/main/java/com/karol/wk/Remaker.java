package com.karol.wk;

import java.util.ArrayList;
import java.util.List;

import com.karol.model.Reservation;


public class Remaker {

	public Remaker() {
	}
	
	public List<String> getTime(List<Reservation> reservation)
	{
		List<String> time=new ArrayList<String>();
		String tempStart;
		String tempFinish;
		for(Reservation i: reservation)
		{
			tempStart=i.getDateStart().substring(11);
			tempStart=tempStart.substring(0, tempStart.length()-2);
			
			
			tempFinish=i.getDateFinish().substring(11);
			tempFinish=tempFinish.substring(0, tempFinish.length()-2);
			
			time.add( tempStart+"-"+tempFinish);
		}
		
		return time;
		
	}
	
	public List<String> getTimeForUpdate(String date, String time){
		List<String> list=new ArrayList<String>();
		
		String tempStart;
		String tempFinish;
		
		tempStart=time.substring(0,5);
		tempStart=tempStart+":00";
		
		tempFinish=time.substring(6,11);
		tempFinish=tempFinish+":00";
		
		
		tempStart=date+" "+tempStart;
		
		tempFinish=date+" "+tempFinish;
		
		
		list.add(tempStart);
		list.add(tempFinish);
		
		return list;
		
		
	}
}
