package frontiere;

import controleur.ControlAfficherMarche;

public class BoundaryAfficherMarche {
	private ControlAfficherMarche controlAfficherMarche;

	public BoundaryAfficherMarche(ControlAfficherMarche controlAfficherMarche) {
		this.controlAfficherMarche = controlAfficherMarche;
	}

	public void afficherMarche(String nomAcheteur) {
		String[] data = controlAfficherMarche.donnerInfosMarche();
		if (data.length == 0) System.out.println("Le marché est vide, revenez plus tard.");
		else {
			System.out.println(nomAcheteur + ", vous trouverez au marché :");
			for(int i=0;i!=data.length;i+=3) {
				System.out.println(data[i] +" qui vend " + data[i+1] +" " +data[i+2]+".");
			}
		}
	}
}
