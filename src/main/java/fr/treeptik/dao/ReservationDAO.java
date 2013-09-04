package fr.treeptik.dao;

import java.util.List;

import fr.treeptik.exception.DAOException;
import fr.treeptik.model.Reservation;

public interface ReservationDAO {
	
	Reservation save (Reservation reservation) throws DAOException;
	Reservation update (Reservation reservation) throws DAOException;
	void remove (Reservation reservation) throws DAOException;
	List<Reservation> findAll() throws DAOException;
	Reservation findById(Integer id) throws DAOException;

}
