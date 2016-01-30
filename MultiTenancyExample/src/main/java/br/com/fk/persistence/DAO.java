package br.com.fk.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionImpl;

public class DAO<E, ID> {

	@PersistenceContext
	private EntityManager em;

	private Class<E> entity;

	public DAO(Class<E> entity) {
		this.entity = entity;
	}

	public void mudarSchema(String schema) {
		SessionImpl tmpsession = (SessionImpl) em.getDelegate();
		SessionFactory sessionFactory = tmpsession.getSessionFactory();
		Session session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
	}

	public void salvar(E entity, String schema) {
		mudarSchema(schema);
		em.persist(entity);
	}

	public void editar(E entity, String schema) {
		mudarSchema(schema);
		em.merge(entity);
	}

	public void remover(E entity, String schema) {
		mudarSchema(schema);
		em.remove(entity);
	}

	public E buscarPorID(ID id, String schema) {
		mudarSchema(schema);
		E obj = (E) em.find(entity, id);
		return obj;
	}

	@SuppressWarnings("unchecked")
	public List<E> buscarTodos(String schema) {
		mudarSchema(schema);
		StringBuffer query = new StringBuffer();
		query.append("FROM ").append(entity.getSimpleName());
		return em.createQuery(query.toString()).getResultList();
	}

}
