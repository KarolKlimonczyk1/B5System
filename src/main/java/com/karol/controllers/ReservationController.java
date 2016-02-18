package com.karol.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.karol.model.Reservation;
import com.karol.services.ReservationService;
import com.karol.wk.Remaker;

@Controller
public class ReservationController {

	@Inject
	private ReservationService reservationService;

	
	Remaker remaker=new Remaker();

	@RequestMapping(value = "/reservation", method = RequestMethod.POST)
	public String listReservation(@RequestParam("room") String room, @RequestParam("mydate") String mydate,
			Model model) {
	
		if(room.equals(""))
		{
			model.addAttribute("roomNotSelected", "1");
			return "welcomePage";
		}
		
		List<Reservation> list = reservationService.getListByDateAndRoom(mydate, room);
		List<String> timeList;
		
		
		timeList=remaker.getTime(list);
		Collections.sort(timeList);
		
		model.addAttribute("room", room);
		model.addAttribute("timeList", timeList);
		model.addAttribute("selectedDate", mydate);

		return "reservation";
	}

	//*********************************************************************************

	@RequestMapping(value = "/reserved", method = RequestMethod.POST)
	public String listAddReservation(@RequestParam("room") String room, 
			@RequestParam("mydate") String mydate,
			@RequestParam("indexNumber") int indexNumber,
			@RequestParam("selectedTime") String selectedTime,
			@RequestParam Map<String, String> check,Model model) {
	
	
		boolean added;
		List<String> list=remaker.getTimeForUpdate(mydate, selectedTime);
		
		
		added=reservationService.addReservation(room, indexNumber, list.get(0), list.get(1));
		
		if(added)
		{
		
			model.addAttribute("addReservation", "true");
			return "welcomePage";
		}
		
		else {
			model.addAttribute("addReservation", "false");
			return "welcomePage";
		}

	}
	
	
	@RequestMapping(value="/szablon", method=RequestMethod.GET)
	public String removeReservaion(){
			
		return "szablonowy";
		
	}

}
