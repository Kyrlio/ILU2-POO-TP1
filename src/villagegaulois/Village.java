package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;

	public Village(String nom, int nbVillageoisMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	private static class Marche{
		private Etal[] etals;
		
		private Marche(int nombreEtals) {
			this.etals = new Etal[nombreEtals];
			for (int i = 0 ; i < nombreEtals ; i++) {
				this.etals[i] = new Etal();
			}
		}
		
		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			if (indiceEtal < 0 || indiceEtal >= etals.length) {
				System.out.println("L'indice d'étal est invalide !");
			} else if (etals[indiceEtal].isEtalOccupe()) {
				System.out.println("L'étal " + indiceEtal + "est déjà occupé par : " + etals[indiceEtal].getVendeur().getNom() + " !");
			} else {
				System.out.println("Le vendeur " + vendeur.getNom() + " s'installe dans l'étal " + indiceEtal);
				etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
			}
		}
		
		private int trouverEtalLibre() {
			for (int i = 0 ; i < etals.length ; i++) {
				// Si l'étal est libre
				if (!etals[i].isEtalOccupe()) {
					return i;
				}
			}
			// Aucun étal libre trouvé
			return -1;
		}
		
		private Etal[] trouverEtals(String produit) {
			int nombreEtalsTrouves = 0;
			Etal[] etalsTrouves = new Etal[nombreEtalsTrouves];
			
			for (int i = 0 ; i < etals.length ; i++) {
				if (etals[i].isEtalOccupe() && etals[i].contientProduit(produit)) {
					// étal trouvé
					nombreEtalsTrouves++;
					etalsTrouves[nombreEtalsTrouves] = etals[i];
				}
			}
			
			return etalsTrouves;
		}
		
		private Etal trouverVendeur(Gaulois gaulois) {
			for (int i = 0 ; i < etals.length ; i++) {
				if (etals[i].isEtalOccupe() && etals[i].getVendeur().equals(gaulois)) {
					return etals[i];
				}
			}
			
			return null;
		}
		
		
	}
}