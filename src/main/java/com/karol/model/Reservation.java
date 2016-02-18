package com.karol.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reservation")
public class Reservation {

	@Id
	@Column(name = "id_reservation")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int idReservation;

	@Column(name = "room")
	String room;

	@Column(name = "indexNumber")
	int indexNumber;

	@Column(name = "dateStart")
	String dateStart;

	@Column(name = "dateFinish")
	String dateFinish;
	
	public Reservation() {
		super();
	}

	
	
	public Reservation(int idReservation, String room, int indexNumber,
			String dateStart, String dateFinish) {
		super();
		this.idReservation = idReservation;
		this.room = room;
		this.indexNumber = indexNumber;
		this.dateStart = dateStart;
		this.dateFinish = dateFinish;
	}



	public int getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(int idReservation) {
		this.idReservation = idReservation;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public int getIndexNumber() {
		return indexNumber;
	}

	public void setIndexNumber(int indexNumber) {
		this.indexNumber = indexNumber;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateFinish() {
		return dateFinish;
	}

	public void setDateFinish(String dateFinish) {
		this.dateFinish = dateFinish;
	}

	@Override
	public String toString() {
		return "Reservation [idReservation=" + idReservation + ", room=" + room
				+ ", indexNumber=" + indexNumber + ", dateStart=" + dateStart
				+ ", dateFinish=" + dateFinish + "]";
	}

	
	
	


}
