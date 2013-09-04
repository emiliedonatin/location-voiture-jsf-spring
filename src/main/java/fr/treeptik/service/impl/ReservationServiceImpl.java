package fr.treeptik.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.treeptik.dao.ReservationDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Reservation;
import fr.treeptik.service.ReservationService;

@Service("reservationService")
public class ReservationServiceImpl implements ReservationService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ReservationDAO reservationDAO;

	@Override
	@Transactional
	public Reservation save(Reservation reservation) throws ServiceException {
		try {
			reservationDAO.save(reservation);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
		return reservation;
	}

	@Override
	@Transactional
	public Reservation update(Reservation reservation) throws ServiceException {
		try {
			reservation = reservationDAO.update(reservation);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
		
		return reservation;
	}

	@Override
	@Transactional
	public void remove(Reservation reservation) throws ServiceException {
		try {
			reservationDAO.remove(reservation);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
		
		
	}

	@Override
	public List<Reservation> findAll() throws ServiceException {

		List<Reservation> reservations = new ArrayList<>();
		
		try {
			reservations = reservationDAO.findAll();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
		return reservations;
	}

	@Override
	public Reservation findById(Integer id) throws ServiceException {
		
		Reservation reservation = null;
		
		try {
			reservation = reservationDAO.findById(id);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}

		return reservation;
	}

}
