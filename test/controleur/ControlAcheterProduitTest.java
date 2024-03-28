package controleur;
import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Village;
import personnages.Personnage;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ControlAcheterProduitTest {
	private Village village;
	private Chef chef;
	private ControlAcheterProduit control;
	private ControlVerifierIdentite controlVerifierIdentite;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	
	@BeforeEach
	public void init() {
		this.village = new Village("le village", 10, 5);
		Chef chef = new Chef("chef", 3, village);
		this.village.setChef(chef);
		this.controlVerifierIdentite = new ControlVerifierIdentite(village);
		this.controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
	}
	@Test
	public void testControlAcheterProduit() {
		
		this.control = new ControlAcheterProduit(controlVerifierIdentite,controlTrouverEtalVendeur,village);
		assertNotNull(control);
	}
	
	@Test
	public void testDonnerInfosMarche() {
		this.control = new ControlAcheterProduit(controlVerifierIdentite,controlTrouverEtalVendeur,village);
		String[] controlMarche = this.control.donnerInfosMarche();
		String[] villageMarche = this.village.donnerEtatMarche();
		assertNotNull(controlMarche);
		for(int i=0;i<controlMarche.length;i++) {
			assertTrue(controlMarche[i].equals(villageMarche[i]));
		}
	}
	@Test
	public void testTrouverVendeurs() {
		Gaulois quentin = new Gaulois("Quentin",12);
		village.ajouterHabitant(quentin);
		Druide pessi = new Druide("pessi",12,1,2);
		village.ajouterHabitant(pessi);
		village.installerVendeur(quentin, "telephones", 5);
		village.installerVendeur(pessi, "telephones", 155);
		Personnage[] controlVendeur = this.control.trouverVendeurs("telephones");
		Personnage[] villageVendeur = village.rechercherVendeursProduit("telephones");
		assertNotNull(controlVendeur);
		for(int i=0;i<controlVendeur.length;i++) {
			assertTrue(controlVendeur[i].getNom().equals(villageVendeur[i].getNom()));
		}
	}
	@Test
	public void testAcheterProduit() {
		Gaulois quentin = new Gaulois("Quentin",12);
		village.ajouterHabitant(quentin);
		Druide pessi = new Druide("pessi",12,0,255);
		village.ajouterHabitant(pessi);
		village.installerVendeur(quentin, "telephones", 5);
		village.installerVendeur(pessi, "telephones", 155);
		assertTrue(control.acheterProduit(quentin, 1) == 1);
		assertTrue(control.acheterProduit(quentin, 5) == 4);
		assertTrue(control.acheterProduit(quentin, 1) == 0);
		assertTrue(control.acheterProduit(pessi, 160) == 155);
		assertTrue(control.acheterProduit(pessi, 100) == 0);
	}

}
