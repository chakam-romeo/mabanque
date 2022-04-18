package org.gestion.app.Metier;

import java.util.List;

import org.gestion.app.entities.Client;
import org.gestion.app.entities.Compte;
import org.gestion.app.entities.Employe;
import org.gestion.app.entities.Groupe;
import org.gestion.app.entities.Operation;

public interface IBanqueMetier {
	public Client addClient(Client c);
	public Employe addEmploye(Employe e, Long employeSub);
	public Groupe addGroupe(Groupe g);
	public void addEmployeToGroupe(Long codeEmploye, Long codeGroupe);
	public Compte addCompte(Compte cp, Long codeClient, Long codeEmploye);
	public void Verser(String codeCompte, double montant, Long codeEmploye);
	public void Retirer(String codeCompte, double montant, Long codeEmploye);
	public void Virement(String codeDebit, String compteDest, double montant, Long codeEmploye);
	public Compte consulterCompte(String code);
	public List<Operation> consulterOperation(String compte); 
	public Client consulterClient(String codeClient);
	public List<Client> consulterClients(String client); 
	public List<Compte> getCompteByClient(String client); 
	public List<Compte> getComptesByEmployes(String emp); 
	public List<Employe> getEmployes();
	public List<Groupe> getGroupes();
	public List<Employe> getEmployeByGroupe(String codeGroupe);
}
