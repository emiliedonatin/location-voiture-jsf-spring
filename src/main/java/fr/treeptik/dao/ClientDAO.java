package fr.treeptik.dao;

import java.util.List;

import fr.treeptik.exception.DAOException;
import fr.treeptik.model.Client;

public interface ClientDAO {
	
	Client save (Client client) throws DAOException;
	Client update (Client client) throws DAOException;
	void remove (Client client) throws DAOException;
	List<Client> findAll() throws DAOException;
	Client findById(Integer id) throws DAOException;
}
