package fr.treeptik.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import fr.treeptik.dao.ClientDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.model.Client;

@Repository
public class ClientDAOImpl implements ClientDAO, Serializable {
	
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	public Client save(Client client) throws DAOException {
		entityManager.persist(client);
		return client;
	}

	@Override
	public Client update(Client client) throws DAOException {
		
		Client c = null;
		try {
			c = entityManager.merge(client);
		} catch (PersistenceException e) {
			throw new DAOException(e.getMessage(), e);
		}
		
		return c;
	}

	@Override
	public void remove(Client client) throws DAOException {
		
		try {
			
			Query query = entityManager.createQuery("Delete from Client c where c.id= :id");
			query.setParameter("id", client.getId());
			query.executeUpdate();
			
		} catch (PersistenceException e) {
			throw new DAOException(e.getMessage(), e);
		}
		
	}

	@Override
	public List<Client> findAll() throws DAOException {
		List<Client> clients = null;
		
		try {
			
			TypedQuery<Client> query = entityManager.createQuery("Select c from Client c", Client.class);
			clients = query.getResultList();
			
		} catch (PersistenceException e) {
			throw new DAOException(e.getMessage(), e);
		}
		return clients;
	}

	@Override
	public Client findById(Integer id) throws DAOException {
		Client c = null;
		
		try {
			
			c = entityManager.find(Client.class, id);
			
		} catch (PersistenceException e) {
			throw new DAOException(e.getMessage(), e);
		}
		return c;
	}

}
