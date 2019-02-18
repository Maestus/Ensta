package Model;

public class Film {
	
	protected int id;
	protected String titre;
	protected String realisateur;
	
	public Film(int id, String titre, String realisateur) {
		this.id = id;
		this.titre = titre;
		this.realisateur = realisateur;
	}

	public Film(String titre, String realisateur) {
		this.titre = titre;
		this.realisateur = realisateur;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	public String getRealisateur() {
		return realisateur;
	}
	
	public void setRealisateur(String realisateur) {
		this.realisateur = realisateur;
	}
}