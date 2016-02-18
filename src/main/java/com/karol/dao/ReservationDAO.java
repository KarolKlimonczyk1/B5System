package com.karol.dao;

import java.util.List;

import com.karol.model.Reservation;

public interface ReservationDAO {
	
	public List<Integer> getListByDate(String date);
	public List<Reservation> getListByDateAndRoom(String date, String room);
	public Reservation getById(int id);
	public boolean addReservation(String room, int indexNumber, String dateStart, String dateFinish);
	public List<Integer> getIdByIndexNumber(int indexNumber);
	public List<Reservation> getReservationByDate(String date);
	public List<Reservation> getReservationByIndexNumber(int indexNumber);
	public List<Reservation> getReservationByIndexNumberWithoutRepetition(int indexNumber);
	public void removeReservationById(int id);
	public void removeReservationByDateIndexRoom(String date, int indexNumber, String room);

}
