package fr.algo.com.object;

import java.util.ArrayList;

public class Utilisateur {

	private String nom;
	private String prenom;
	private int nombre_film;
	
	private ArrayList<Film> liste_films;

	public Utilisateur(String nom, String prenom, int nombre_film, ArrayList<Film> liste_films) {
		
		this.nom = nom;
		this.prenom = prenom;
		this.nombre_film = nombre_film;
		this.liste_films = liste_films;
		
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getNombre_film() {
		return nombre_film;
	}

	public void setNombre_film(int nombre_film) {
		this.nombre_film = nombre_film;
	}

	public ArrayList<Film> getListe_films() {
		return liste_films;
	}

	public void setListe_films(ArrayList<Film> liste_films) {
		this.liste_films = liste_films;
	}
	
	public void loadData() {
		
		// Récupérer informations de l'utilisateur sur la bdd
		
	}
	
	public void saveData(){
		
		// Sauvegarder les données dans la base de données
		
	}
	
	
}
