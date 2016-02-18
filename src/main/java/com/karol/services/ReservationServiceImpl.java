package com.karol.services;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.karol.dao.ReservationDAO;
import com.karol.model.Reservation;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Inject
	ReservationDAO reservationDAO;
	
	
	@Override
	@Transactional
	public List<Reservation> getListByDateAndRoom(String date, String room) {
		return reservationDAO.getListByDateAndRoom(date, room);
	}
	


	@Override
	@Transactional
	public boolean addReservation(String room, int indexNumber, String dateStart, String dateFinish) {
		return reservationDAO.addReservation(room, indexNumber, dateStart, dateFinish);
		
	}


	@Override
	@Transactional
	public List<Reservation> getReservationByIndexNumberWithoutRepetition(
			int indexNumber) {
		return reservationDAO.getReservationByIndexNumberWithoutRepetition(indexNumber);
	}


	@Override
	@Transactional
	public void removeReservationByDateIndexRoom(String date, int indexNumber,
			String room) {
		this.reservationDAO.removeReservationByDateIndexRoom(date, indexNumber, room);
		
	}

	

	

}
