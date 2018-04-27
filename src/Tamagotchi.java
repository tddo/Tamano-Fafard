
public class Tamagotchi {
	private String nom;
	private int age, ageSagesse, energie,  actif;	// 0: atteint l'âge de sagesse
													// 1: meurt
													// 2: encore vivant
	
	private final String[] tabEtats = {	"C'est gagné pour moi!", 
										"Je suis mort...", 
										"Je suis heureux"};

	public Tamagotchi(String nom){
		this.nom = nom;
		this.age = 0;
		this.ageSagesse = DOTD.generer(11, true) + 20;
		this.energie = DOTD.generer(5, true) + 5;
		this.actif = 2;
	}

	public String getNom() {
		return nom;
	}

	public int getAge() {
		return age;
	}

	public int getAgeSagesse() {
		return ageSagesse;
	}

	public int getEnergie() {
		return energie;
	}
	public int getActif() {
		return actif;
	}
	
	public void vieillir(){
		if(getActif() == 2) {
			age++;
			energie -= DOTD.generer(2, false);
		}
	}
	 
	public void manger(){
		if(getActif() == 2) 
			energie += DOTD.generer(5, true) + 2;
	}

	public String messageEtat(){
		String strRetourne = null;
		if(estSage())		strRetourne = tabEtats[0];
		else if(estMort()) 	strRetourne = tabEtats[1];
		else 				strRetourne = tabEtats[2];
		return strRetourne;
	}
	
	public boolean estMort(){
		boolean bool = false;
		if(energie <= 0 && !estSage()){
			actif = 1;
			bool = true;
		}
		return bool;	
	}
	
	public boolean estSage(){
		boolean bool = false;
		if(age >= ageSagesse){
			actif = 0;
			bool = true;
		}
		return bool;
	}
}
