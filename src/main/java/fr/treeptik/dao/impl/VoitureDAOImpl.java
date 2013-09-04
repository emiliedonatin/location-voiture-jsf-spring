package fr.treeptik.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import fr.treeptik.dao.VoitureDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.model.Voiture;

@Repository
public class VoitureDAOImpl implements VoitureDAO , Serializable{

	private static final long serialVersionUID = 1L;
	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	public Voiture save(Voiture voiture) throws DAOException {
		entityManager.persist(voiture);

		return voiture;
	}

	@Override
	public void remove(Voiture voiture) throws DAOException {
		try {
			Query query = entityManager.createQuery("Delete from Voiture v where v.id= :id");
			query.setParameter("id", voiture.getId());
			query.executeUpdate();
		} catch (PersistenceException e) {
			throw new DAOException(e.getMessage(), e);
		}

	}

	@Override
	public List<Voiture> findAll() throws DAOException {

		List<Voiture> voitures = null;
		try {
			TypedQuery<Voiture> query = entityManager.createQuery("Select v From Voiture v", Voiture.class);
			voitures = query.getResultList();

		} catch (PersistenceException e) {
			throw new DAOException(e.getMessage(), e);
		}

		return voitures;

	}

	@Override
	public Voiture update(Voiture voiture) throws DAOException {
		Voiture v = null;
		try {
			v = entityManager.merge(voiture);

		} catch (PersistenceException e) {
			throw new DAOException(e.getMessage(), e);
		}
		return v;
	}

	@Override
	public Voiture findById(Integer id) throws DAOException {
		Voiture v = null;
		try {
			v = entityManager.find(Voiture.class, id);

		} catch (PersistenceException e) {
			throw new DAOException(e.getMessage(), e);
		}
		return v;
	}

}
