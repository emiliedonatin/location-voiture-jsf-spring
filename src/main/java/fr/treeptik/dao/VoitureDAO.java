package fr.treeptik.dao;

import java.util.List;

import fr.treeptik.exception.DAOException;
import fr.treeptik.model.Voiture;


public interface VoitureDAO  {
	
	Voiture save (Voiture voiture) throws DAOException;
	Voiture update (Voiture voiture) throws DAOException;
	void remove (Voiture voiture) throws DAOException;
	List<Voiture> findAll() throws DAOException;
	Voiture findById(Integer id) throws DAOException;

	
	
	

}
