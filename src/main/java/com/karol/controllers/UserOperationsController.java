package com.karol.controllers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.karol.model.Reservation;
import com.karol.services.ReservationService;

@Controller
public class UserOperationsController {

	@Inject
	private ReservationService reservationService;
	
	@RequestMapping(value="/userpanel", method=RequestMethod.GET)
	public String userpanel( Model model){
		
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String name = auth.getName();
	      
	    Integer indexNumber=Integer.valueOf(name);
	    
		List<Reservation> list=reservationService.getReservationByIndexNumberWithoutRepetition(indexNumber);
		
		model.addAttribute("reservationList", list);
		
		
		
		return "userpanel";
		
	}
	
	
	
	@RequestMapping(value="/userpanel/remove_reservation", method=RequestMethod.POST)
	public String removeReservaionPost(@RequestParam("room") String room,
			@RequestParam("dateStart") String dateStart,
			Model model){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String name = auth.getName();
	      
	    Integer indexNumber=Integer.valueOf(name);
	    
		reservationService.removeReservationByDateIndexRoom(dateStart, indexNumber, room);
		
		model.addAttribute("removedSuccessfully", "true");
		return "welcomePage";
		
	}
}
