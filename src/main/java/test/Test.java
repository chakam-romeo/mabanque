package test;

import org.gestion.app.Metier.IBanqueMetier;
import org.gestion.app.entities.Client;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml"});
		IBanqueMetier metier = (IBanqueMetier) context.getBean("metier");
		metier.addClient(new Client("romeo","Douala"));
		metier.addClient(new Client("chakam","center"));
		metier.addClient(new Client("elvira","Yaoundé"));
		metier.addClient(new Client("jeff","Bafang"));
	}

}
