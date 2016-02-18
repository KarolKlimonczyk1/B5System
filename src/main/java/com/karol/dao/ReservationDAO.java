package com.karol.dao;

import java.util.List;

import com.karol.model.Reservation;

public interface ReservationDAO {
	
	public List<Reservation> getListByDateAndRoom(String date, String room);
	public boolean addReservation(String room, int indexNumber, String dateStart, String dateFinish);
	public List<Reservation> getReservationByIndexNumberWithoutRepetition(int indexNumber);
	public void removeReservationByDateIndexRoom(String date, int indexNumber, String room);

}
