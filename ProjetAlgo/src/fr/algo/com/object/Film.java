package fr.algo.com.object;

public class Film {

	private String nom;
	private String realisateur;
	
	public Film(String nom, String realisateur) {
		
		this.nom = nom;
		this.realisateur = realisateur;
		
		
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getRealisateur() {
		return realisateur;
	}

	public void setRealisateur(String realisateur) {
		this.realisateur = realisateur;
	}
	
	
}
