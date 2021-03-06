package com.vamosaprogramar.CeibaEstacionamiento.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vamosaprogramar.CeibaEstacionamiento.GeneralConstants;
import com.vamosaprogramar.CeibaEstacionamiento.entity.ParkingTicket;

@Repository
public class ParkingTicketRepositoryImpl implements ParkingTicketRepository {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<ParkingTicket> getParkingTickets() {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query theQuery = session.createQuery("from ParkingTicket");

			List<ParkingTicket> parkingTickets = theQuery.list();

			return parkingTickets;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return null;
	}

	@Override
	public Integer countNumberConcurrentVehicles(String vehicleType) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query theQuery = session.createQuery(
					"Select count(*) from ParkingTicket where status = :status and vehicleType = :vehicleType");

			theQuery.setParameter("vehicleType", vehicleType);
			
			theQuery.setParameter("status", GeneralConstants.TICKET_REGISTERED);

			System.out.println("Consulta # Carros: "+theQuery.uniqueResult());
			
			Integer numberConcurrentVehicles = ((Long) theQuery.uniqueResult()).intValue();
			
			System.out.println("Consulta # Carros(numberConcurrentVehicles): "+numberConcurrentVehicles);
			
			return numberConcurrentVehicles;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return 0;
	}

	@Override
	public void addParkingTicket(ParkingTicket parkingTicket) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(parkingTicket);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public ParkingTicket getParkingTicket(int id) {
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			ParkingTicket parkingTicket = session.get(ParkingTicket.class, id);
			
			return parkingTicket;
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return null;
	}

	@Override
	public void toCheckOut(ParkingTicket parkingTicket) {
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			session.update(parkingTicket);
			
			session.getTransaction().commit();
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
	}

	@Override
	public List<ParkingTicket> getParkingTicketsByStatus(String status) {

		Session session = null;

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query theQuery = session.createQuery("from ParkingTicket where status= :status");
			
			theQuery.setParameter("status", status);

			List<ParkingTicket> parkingTickets = theQuery.list();

			return parkingTickets;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return null;
	}

}
