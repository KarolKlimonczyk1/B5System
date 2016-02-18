package com.karol.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.karol.model.Reservation;

@Repository
public class ReservationDAOImpl implements ReservationDAO {

	
	private static final Logger logger = LoggerFactory
			.getLogger(ReservationDAOImpl.class);

	@Inject
	private SessionFactory sessionFactory;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Reservation> getListByDateAndRoom(String date, String room) {
		
		StringBuilder dateStart=new StringBuilder(date);
		dateStart.append(" 00:00:00");
		
		StringBuilder dateFinish=new StringBuilder(date);
		dateFinish.append(" 23:59:59");

		
		
		Session session=sessionFactory.getCurrentSession();
		Query query = (Query) session.createQuery("select R from Reservation R where R.dateStart>=:start and "
				+ "R.dateFinish<=:finish and R.room=:temproom  ");
		
		
		query.setParameter("start", dateStart.toString());
		query.setParameter("finish", dateFinish.toString());
		query.setParameter("temproom", room);
		
		List<Reservation> list = ((Query) query).list();
		
		
	
		return list;
	}
	


	@Override
	public boolean addReservation(String room, int indexNumber, String dateStart, String dateFinish) {
		
		List<Reservation> list;
		Query query;
		
		  Session session = this.sessionFactory.getCurrentSession();
		  
		  boolean isEmpty=true;
		  
		  // mniejszy start, mniejszy koniec
		  
		   query = (Query) session.createQuery("select R from Reservation R where R.dateStart<=:start and "
					+ "R.dateFinish<=:finish and R.dateFinish>=:start and R.room=:temproom  ");
			
			query.setParameter("start", dateStart.toString());
			query.setParameter("finish", dateFinish.toString());
			query.setParameter("temproom", room);
			
			list = ((Query) query).list();
			if(!list.isEmpty())
				isEmpty=false;
		  
	     if(isEmpty)
	     {
	    	 // mniejszy start, wiêkszy koniec // zawieranie
	    	 query = (Query) session.createQuery("select R from Reservation R where R.dateStart<=:start and "
						+ "R.dateFinish>=:finish and R.room=:temproom  ");
				
				query.setParameter("start", dateStart.toString());
				query.setParameter("finish", dateFinish.toString());
				query.setParameter("temproom", room);
				
				list = ((Query) query).list();
				
				if(!list.isEmpty())
					isEmpty=false;
				
				// wiekszy start, wiekszy stop
				if(isEmpty)
			     {
			    	 // mniejszy start, wiêkszy koniec // zawieranie
			    	 query = (Query) session.createQuery("select R from Reservation R where R.dateStart>=:start and "
								+ "R.dateStart<=:finish and R.dateFinish>=:finish and R.room=:temproom  ");
						
						query.setParameter("start", dateStart.toString());
						query.setParameter("finish", dateFinish.toString());
						query.setParameter("temproom", room);
						
						list = ((Query) query).list();
						
						if(!list.isEmpty())
							isEmpty=false;
			     }
				
				// pomiedzy 
				if(isEmpty)
			     {
			    	 // mniejszy start, wiêkszy koniec // zawieranie
			    	 query = (Query) session.createQuery("select R from Reservation R where R.dateStart>=:start and "
								+ "R.dateFinish<=:finish and R.room=:temproom  ");
						
						query.setParameter("start", dateStart.toString());
						query.setParameter("finish", dateFinish.toString());
						query.setParameter("temproom", room);
						
						list = ((Query) query).list();
						
						if(!list.isEmpty())
							isEmpty=false;
			     }
				
	     }
			
	     
	     if(isEmpty){
	     
	     Reservation reservation=new Reservation();
	     
	     reservation.setRoom(room);
	     reservation.setIndexNumber(indexNumber);
	     reservation.setDateStart(dateStart);
	     reservation.setDateFinish(dateFinish);
	     
	     session.save(reservation);
	     
	     return true;
	     }
	     
	     else return false;
	        
	}
	
	@Override
	public List<Reservation> getReservationByIndexNumberWithoutRepetition(
			int indexNumber) {
		Session session=sessionFactory.getCurrentSession();
		
		Query query = (Query) session.createQuery("select R from Reservation R where R.indexNumber=:index"
				+ "  GROUP BY R.dateStart, R.room");
		
		query.setParameter("index", indexNumber);
		
		List<Reservation> list = ((Query) query).list();
		
		
		
		return list;
	}
	


	@Override
	public void removeReservationByDateIndexRoom(String date, int indexNumber,
			String room) {
		
		Session session=sessionFactory.getCurrentSession();
		session.createSQLQuery
				("delete from reservation where room=:temproom and dateStart=:tempdate and indexnumber=:tempindex ")
				.addEntity(Reservation.class)
				.setParameter("temproom", room)
				.setParameter("tempdate", date)
			    .setParameter("tempindex", indexNumber).executeUpdate(); 
		
	}

}
