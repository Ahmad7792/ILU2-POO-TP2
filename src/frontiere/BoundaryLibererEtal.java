package frontiere;

import controleur.ControlLibererEtal;

public class BoundaryLibererEtal {
	private ControlLibererEtal controlLibererEtal;

	public BoundaryLibererEtal(ControlLibererEtal controlLibererEtal) {
		this.controlLibererEtal = controlLibererEtal;
	}

	public void libererEtal(String nomVendeur) {
		String[] data = controlLibererEtal.libererEtal(nomVendeur);
		if (data!= null && data[0] == "true") {
		System.out.println("Vous avez vendu "+ data[4] + " sur " + data[3] + data[2] +".\nAu revoir monsieur, passez une bonne journée.");
		}
		else {
			System.out.println("Mais vous n'êtes pas inscrit sur notre marché aujourd'hui !");
		}
	}

}
