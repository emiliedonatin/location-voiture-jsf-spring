package fr.treeptik.service;

import java.util.List;

import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Voiture;




public interface VoitureService {

	Voiture save (Voiture voiture) throws ServiceException;
	Voiture update (Voiture voiture) throws ServiceException;
	void remove (Voiture voiture) throws ServiceException;
	List<Voiture> findAll() throws ServiceException;
	Voiture findById (Integer id) throws ServiceException;
	
	
}