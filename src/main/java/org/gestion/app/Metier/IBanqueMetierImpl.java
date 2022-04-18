package org.gestion.app.Metier;

import java.util.Date;
import java.util.List;

import org.gestion.app.dao.IBanqueDao;
import org.gestion.app.entities.Client;
import org.gestion.app.entities.Compte;
import org.gestion.app.entities.Employe;
import org.gestion.app.entities.Groupe;
import org.gestion.app.entities.Operation;
import org.gestion.app.entities.Retrait;
import org.gestion.app.entities.Versement;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class IBanqueMetierImpl implements IBanqueMetier {

	private IBanqueDao dao;
	public void setDao(IBanqueDao dao) {
		this.dao = dao;
	}

	@Override
	public Client addClient(Client c) {
		return dao.addClient(c);
	}

	@Override
	public Employe addEmploye(Employe e, Long employeSub) {
		return dao.addEmploye(e, employeSub);
	}

	@Override
	public Groupe addGroupe(Groupe g) {
		return dao.addGroupe(g);
	}

	@Override
	public void addEmployeToGroupe(Long codeEmploye, Long codeGroupe) {
		dao.addEmployeToGroupe(codeEmploye, codeGroupe);
	}

	@Override
	public Compte addCompte(Compte cp, Long codeClient, Long codeEmploye) {
		return dao.addCompte(cp, codeClient, codeEmploye);
	}

	@Override
	public void Verser(String codeCompte, double montant, Long codeEmploye) {
		dao.addOperation((new Versement(new Date(), montant)), codeCompte, codeEmploye);
		Compte cp=dao.consulterCompte(codeCompte);
		cp.setSolde(cp.getSolde() + montant);
	}

	@Override
	public void Retirer(String codeCompte, double montant, Long codeEmploye) {
		dao.addOperation(new Retrait(new Date(), montant), codeCompte, codeEmploye);
		Compte cp=dao.consulterCompte(codeCompte);
		cp.setSolde(cp.getSolde() - montant);
	}

	@Override
	public void Virement(String codeDebit, String compteDest, double montant, Long codeEmploye) {
		Retirer(codeDebit, montant, codeEmploye);
		Verser(compteDest, montant, codeEmploye);
	}

	@Override
	public Compte consulterCompte(String code) {
		return dao.consulterCompte(code);
	}

	@Override
	public List<Operation> consulterOperation(String compte) {
		return dao.consulterOperation(compte);
	}

	@Override
	public Client consulterClient(String codeClient) {
		return dao.consulterClient(codeClient);
	}

	@Override
	public List<Client> consulterClients(String client) {
		return dao.consulterClients(client);
	}

	@Override
	public List<Compte> getCompteByClient(String client) {
		return dao.getCompteByClient(client);
	}

	@Override
	public List<Compte> getComptesByEmployes(String emp) {
		return dao.getComptesByEmployes(emp);
	}

	@Override
	public List<Employe> getEmployes() {
		return dao.getEmployes();
	}

	@Override
	public List<Groupe> getGroupes() {
		return dao.getGroupes();
	}

	@Override
	public List<Employe> getEmployeByGroupe(String codeGroupe) {
		return dao.getEmployeByGroupe(codeGroupe);
	}

}
