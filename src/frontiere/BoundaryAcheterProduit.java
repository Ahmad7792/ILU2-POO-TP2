package frontiere;

import java.util.Scanner;

import controleur.ControlAcheterProduit;
import personnages.Gaulois;
import personnages.Personnage;

public class BoundaryAcheterProduit {
	private Scanner scan = new Scanner(System.in);
	private ControlAcheterProduit controlAcheterProduit;

	public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
		this.controlAcheterProduit = controlAcheterProduit;
	}

	public void acheterProduit(String nomAcheteur) {
			String produit = Clavier.entrerChaine("Quel produit souhaitez vous acheter?");
			Personnage[] vendeurs = controlAcheterProduit.trouverVendeurs(produit);
			if (vendeurs == null) System.out.println("D�sol�, personne ne vend ce produit au march�");
			else {
				System.out.println("Chez quel commerçant(e) voulez vous acheter des "+produit+" ?");
				for(int i=0;i!=vendeurs.length;i++) {
					System.out.println(i+1 + " : " + vendeurs[i].getNom());
				}
				int indiceVendeurChoix;
				do {
					indiceVendeurChoix = Clavier.entrerEntier("");
					if(indiceVendeurChoix > vendeurs.length || indiceVendeurChoix < 0) System.out.println("Vous devez entrer un nombre entre 1 et " + vendeurs.length );
				}while (indiceVendeurChoix > vendeurs.length || indiceVendeurChoix < 0);
				System.out.println(nomAcheteur + " se d�place jusqu'à l'etal de " + vendeurs[indiceVendeurChoix-1].getNom());
				int choixUtilisateur = Clavier.entrerEntier("\"Bonjour" + nomAcheteur + ", combien de "+ produit+" voulez vous acheter?");
				int quantiteAchetee = controlAcheterProduit.acheterProduit(vendeurs[indiceVendeurChoix - 1], choixUtilisateur);
				if(quantiteAchetee == choixUtilisateur) {
					System.out.println(nomAcheteur+" ach�te "+ quantiteAchetee + " " + produit +" à " +vendeurs[indiceVendeurChoix-1].getNom() + ".");
				}
				else if (quantiteAchetee == 0) {
					System.out.println(nomAcheteur +" veut acheter " + choixUtilisateur + " " + produit +", malheureusement il n’en a plus");
				}
				else if (quantiteAchetee < choixUtilisateur && quantiteAchetee != 0) {
					System.out.println(nomAcheteur + " veut acheter "+ choixUtilisateur + " " + produit + ", malheureusement "+ vendeurs[indiceVendeurChoix-1].getNom() + " n'en a plus que "+quantiteAchetee+". "+nomAcheteur+" ach�te tout le stock de " + vendeurs[indiceVendeurChoix-1].getNom());
				}
			}
	}
}
