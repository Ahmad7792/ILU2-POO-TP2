package controleur;

import personnages.Gaulois;
import personnages.Personnage;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ControlAcheterProduit {
	private Village village;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	private ControlVerifierIdentite controlVerifierIdentite;

	public ControlAcheterProduit(ControlVerifierIdentite controlVerifierIdentite,
			ControlTrouverEtalVendeur controlTrouverEtalVendeur,
			Village village) {
		this.village = village;
		this.controlVerifierIdentite = controlVerifierIdentite;
		this.controlTrouverEtalVendeur = controlTrouverEtalVendeur;
	}

	public String[] donnerInfosMarche() {
		return this.village.donnerEtatMarche();
	}
	
	public Personnage[] trouverVendeurs(String produit) {
		return village.rechercherVendeursProduit(produit);
	}
	public int acheterProduit(String acheteur,Personnage vendeur, int quantite) {
		Etal etal = village.rechercherEtal((Gaulois) vendeur);
		return etal.acheterProduit(quantite);
	}
}
