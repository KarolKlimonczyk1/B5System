package com.karol.app;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.karol.model.Reservation;
import com.karol.services.ReservationService;
import com.karol.services.UserService;
import com.karol.wk.Remaker;

@Controller
public class ReservationController {

	@Inject
	private ReservationService reservationService;

	@Inject 
	private UserService userService;
	
	Remaker remaker=new Remaker();

	@RequestMapping(value = "/reservation", method = RequestMethod.POST)
	public String listReservation(@RequestParam("room") String room, @RequestParam("mydate") String mydate,
			Model model) {
	
		if(room.equals(""))
		{
			model.addAttribute("roomNotSelected", "1");
			return "hello";
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
	@RequestMapping(value = "/reservation", method = RequestMethod.GET)
	public String listReservationGET(Model model) {

		return "redirect:/";
	}

	//**************************************************************************************
	@RequestMapping(value = "/reserved", method = RequestMethod.POST)
	public String listAddReservation(@RequestParam("room") String room, 
			@RequestParam("mydate") String mydate,
			@RequestParam("indexNumber") int indexNumber,
			@RequestParam("selectedTime") String selectedTime,
			@RequestParam Map<String, String> check,Model model) {
	
	
		boolean addGood;
		List<String> list=remaker.getTimeForUpdate(mydate, selectedTime);
		
		
		addGood=reservationService.addReservation(room, indexNumber, list.get(0), list.get(1));
		
		if(addGood)
		{
		
			model.addAttribute("addReservation", "true");
			return "hello";
		}
		
		else {
			model.addAttribute("addReservation", "false");
			return "hello";
		}
	//	return "redirect:/reservation";
//		return "redirect:/reserved";

	}
	
	@RequestMapping(value="/userpanel/{indexNumber}", method=RequestMethod.GET)
	public String userpanel(@PathVariable("indexNumber") int indexNumber, Model model){
		
		List<Reservation> list=reservationService.getReservationByIndexNumberWithoutRepetition(indexNumber);
		
		model.addAttribute("reservationList", list);
		
		
		
		return "userpanel";
		
	}
	
	@RequestMapping(value="/remove_reservation/{room}/{dateStart}/{indexnumber}", method=RequestMethod.GET)
	public String removeReservaion(@PathVariable("room") String room,
			@PathVariable("dateStart") String dateStart,
			@PathVariable("indexnumber") int indexnumber,
			Model model){
		
		
		reservationService.removeReservationByDateIndexRoom(dateStart, indexnumber, room);
		
		model.addAttribute("removedSuccessfully", "true");
		return "hello";
		
	}
	
	
	@RequestMapping(value="/szablon", method=RequestMethod.GET)
	public String removeReservaion(){
			

		
		return "szablonowy";
		
	}

}
