package com.karol.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.karol.model.Person;
import com.karol.model.Reservation;

@Repository
public class ReservationDAOImpl implements ReservationDAO {

	
	private static final Logger logger = LoggerFactory
			.getLogger(PersonDAOImpl.class);

	@Inject
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getListByDate(String date) {
		Session session=sessionFactory.openSession();
		Query query = (Query) session.createQuery("select R.myTime from Reservation R where R.myDate=:tempdate  ");
		query.setParameter("tempdate", date);
		
		List<Integer> list = ((Query) query).list();
		
		return list;
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Reservation> getListByDateAndRoom(String date, String room) {
		
		StringBuilder dateStart=new StringBuilder(date);
		dateStart.append(" 00:00:00");
		
		StringBuilder dateFinish=new StringBuilder(date);
		dateFinish.append(" 23:59:59");
		
		//System.out.println(dateStart+"   "+dateFinish);
		
		// gdyby nie dzia³a³o otwórz now¹ sesjê // 
		Session session=sessionFactory.getCurrentSession();
		Query query = (Query) session.createQuery("select R from Reservation R where R.dateStart>=:start and "
				+ "R.dateFinish<=:finish and R.room=:temproom  ");
		
		
		//Query query = (Query) session.createQuery("select R from Reservation R where R.room=:temproom  ");
		
		
		query.setParameter("start", dateStart.toString());
		query.setParameter("finish", dateFinish.toString());
		query.setParameter("temproom", room);
		
		List<Reservation> list = ((Query) query).list();
		
		
	
		return list;
	}
	

	@Override
	public Reservation getById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Reservation rd = (Reservation) session.load(Reservation.class, new Integer(id));
		logger.info("Reservation loaded successfully, details=" + rd);
		return rd;
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
			 //  session.persist(reservation);
		//	************ zapisz zmiane!!!!
		  //session.save(reservation);
	       // logger.info("Reservation added successfully, Reservation Details="+ reservation);
	        
	        
		
	}

	@Override

	@SuppressWarnings("unchecked")
	public List<Integer> getIdByIndexNumber(int indexNumber) {
		Session session=sessionFactory.openSession();
		Query query = (Query) session.createQuery("select R.idReservation from Reservation R where R.indexNumber=:indexNumb  ");
		query.setParameter("indexNumb", indexNumber);
		
		List<Integer> list = ((Query) query).list();
		
		return list;
	}

	@Override
	public List<Reservation> getReservationByDate(String date) {
		Session session=sessionFactory.openSession();
		List<Reservation> list = (List<Reservation>) session.createSQLQuery("select * from Reservation R where R.date=:tempdate  ")
				.addEntity(Reservation.class)
			    .setParameter("tempdate", date).list(); 
		
		
		return list;
	}

	@Override
	public List<Reservation> getReservationByIndexNumber(int indexNumber) {
		Session session=sessionFactory.openSession();
		List<Reservation> list = (List<Reservation>) session.createSQLQuery("select * from Reservation R where R.indexnumber=:index  ")
				.addEntity(Reservation.class)
			    .setParameter("index", indexNumber).list(); 
		
		
		
		return list;
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
	public void removeReservationById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
        Reservation r = (Reservation) session.load(Reservation.class, new Integer(id));
        if(null != r){
            session.delete(r);
        }
		
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
