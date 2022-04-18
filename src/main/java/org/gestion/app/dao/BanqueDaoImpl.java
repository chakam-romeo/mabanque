package org.gestion.app.dao;

import java.util.List;

import javax.management.RuntimeErrorException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.gestion.app.entities.Client;
import org.gestion.app.entities.Compte;
import org.gestion.app.entities.Employe;
import org.gestion.app.entities.Groupe;
import org.gestion.app.entities.Operation;

public class BanqueDaoImpl implements IBanqueDao {

	@PersistenceContext
	private EntityManager em;
	@Override
	public Client addClient(Client c) {
		em.persist(c);
		return c;
	}

	@Override
	public Employe addEmploye(Employe e, Long employeSub) {
		if(employeSub != null) {
			Employe sup = em.find(Employe.class, employeSub);
			e.setEmployeSub(sup);
		}
		em.persist(e);
		return e;
	}

	@Override
	public Groupe addGroupe(Groupe g) {
		em.persist(g);
		return g;
	}

	@Override
	public void addEmployeToGroupe(Long codeEmploye, Long codeGroupe) {
		Employe e = em.find(Employe.class, codeEmploye);
		Groupe g = em.find(Groupe.class, codeGroupe);
		e.getGroupes().add(g);
		g.getEmployes().add(e);
		
	}

	@Override
	public Compte addCompte(Compte cp, Long codeClient, Long codeEmploye) {
		Employe e = em.find(Employe.class, codeEmploye);
		Client c = em.find(Client.class, codeClient);
		cp.setClient(c);
		cp.setEmploye(e);
		em.persist(cp);
		return cp;
	}

	@Override
	public Operation addOperation(Operation op, String codeCompte, Long codeEmploye) {
		Compte cp=consulterCompte(codeCompte);
		Employe emp=em.find(Employe.class, codeEmploye);
		op.setCompte(cp);
		op.setEmploye(emp);
		em.persist(op);
		return op;
	}

	@Override
	public Compte consulterCompte(String code) {
		Compte c = em.find(Compte.class, code);
		if(c==null) throw new RuntimeException("Element innexistant");
		return c;
	}

	@Override
	public List<Operation> consulterOperation(String compte) {
		Query req = em.createQuery("select o from Operation o where o.compte.codeCompte=:x");
		req.setParameter("x", compte);
		return req.getResultList();
	}

	@Override
	public Client consulterClient(String codeClient) {
		Client c = em.find(Client.class, codeClient);
		if(c==null) throw new RuntimeException("Element innexistant");
		return c;
	}

	@Override
	public List<Client> consulterClients(String client) {
		Query req = em.createQuery("select cl from Client cl where cl.codeClient LIKE :x");
		req.setParameter("x", "%" + client + "%");
		return req.getResultList();
	}

	@Override
	public List<Compte> getCompteByClient(String cmptClient) {
		Query req = em.createQuery("select c from Compte c where c.client.CODE_CLIENT=:x");
		req.setParameter("x", cmptClient);
		return req.getResultList();
	}

	@Override
	public List<Compte> getComptesByEmployes(String emp) {
		Query req = em.createQuery("select c from Compte c where c.employe.codeEmploye=:x");
		req.setParameter("x", emp);
		return req.getResultList();
	}

	@Override
	public List<Employe> getEmployes() {
		Query req = em.createQuery("select e from Employe e");
		return req.getResultList();
	}

	@Override
	public List<Groupe> getGroupes() {
		Query req = em.createQuery("select g from Groupe g");
		return req.getResultList();
	}

	@Override
	public List<Employe> getEmployeByGroupe(String codeGroupe) {
		Query req = em.createQuery("select em from Employe em where em.groupes.codeGroupe=:x");
		req.setParameter("x", codeGroupe);
		return req.getResultList();
	}

}
