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
		int choixUtilisateur = Clavier.entrerEntier("1 - je veux acheter un produit\n2 - je veux avoir une vue d'ensemble du marche\n3 - quitter l'application");
		switch (choixUtilisateur) {
		case 1:
			String produit = Clavier.entrerChaine("Quel produit souhaitez vous acheter?");
			Personnage[] vendeurs = controlAcheterProduit.trouverVendeurs(produit);
			if (vendeurs.length == 0) System.out.println("“Désolé, personne ne vend ce produit au marché");
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
				System.out.println(nomAcheteur + " se déplace jusqu'à l'etal de " + vendeurs[indiceVendeurChoix-1].getNom());
				choixUtilisateur = Clavier.entrerEntier("\"Bonjour" + nomAcheteur + ", combien de "+ produit+" voulez vous acheter?");
				int quantiteRestante = controlAcheterProduit.acheterProduit(nomAcheteur, vendeurs[indiceVendeurChoix - 1], choixUtilisateur);
				if(quantiteRestante > 0 || choixUtilisateur==quantiteRestante) {
					System.out.println(nomAcheteur+" achète "+ quantiteRestante + " " + produit +" à " +vendeurs[indiceVendeurChoix-1].getNom() + ".");
				}
				else if (quantiteRestante == 0) {
					System.out.println(nomAcheteur +" veut acheter " + choixUtilisateur + " " + produit +", malheureusement il n’en a plus");
				}
				else {
					System.out.println(nomAcheteur + " veut acheter "+ choixUtilisateur + " " + produit + ", malheureusement "+ vendeurs[indiceVendeurChoix-1] + " n'en a plus que "+quantiteRestante+". "+nomAcheteur+" achète tout le stock de " + vendeurs[indiceVendeurChoix-1]);
				}
			}
			break;
		case 2:
			String[] data = controlAcheterProduit.donnerInfosMarche();
			if (data.length == 0) System.out.println("Le marché est vide, revenez plus tard.");
			else {
				System.out.println(nomAcheteur + ", vous trouverez au marché :");
				for(int i=0;i!=data.length;i+=3) {
					System.out.println(data[i] +" qui vend " + data[i+1] +" " +data[i+2]+".");
				}
			}
			break;
		default:
			break;
		}
	}
}
