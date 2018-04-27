import java.util.ArrayList;
import java.util.Collections;

public class JeuTamagotchi {
	private int nbTamagotchis;
	private Tamagotchi[] mesTamagotchis;	
	
	// Liste de noms des tamagotchis et de leur fichiers 'png'
	private final String[] tabNoms = {	"Charles", 
										"Flavien", 
										"Bob", 
										"Brad",
										"Valence", 
										"Pétrolia",
										"Serge 18"};
	
	private ArrayList<Integer> listOrdreAleatoire = new ArrayList<Integer>();

	public JeuTamagotchi(int nbTamagotchis){
		this.nbTamagotchis = nbTamagotchis;
		this.mesTamagotchis = new Tamagotchi[nbTamagotchis];
		
		for(int i = 0; i < tabNoms.length; i++) listOrdreAleatoire.add(i);
		
		Collections.shuffle(listOrdreAleatoire);
		
		for(int i = 0; i < mesTamagotchis.length; i++) {
			this.mesTamagotchis[i] = new Tamagotchi(tabNoms[listOrdreAleatoire.get(i)]);
//			this.mesTamagotchis[i] = new Tamagotchi(tabNoms[i]);
//			DOTD.SOPL(listOrdreAleatoire.get(i).toString());
		}
	}
	
	/*
	 *	Retour des informations d'un Tamagotchi demandé
	 *	0:Nom
	 *	1:Age
	 *	2:Age de sagesse
	 *	3:Énergie
	 *	4:Message d'état
	 *	5:Code actif
	 */
	public String getInfo(int iTamagotchi) {
		return 	mesTamagotchis[iTamagotchi].getNom()
				+","+mesTamagotchis[iTamagotchi].getAge()
				+","+mesTamagotchis[iTamagotchi].getAgeSagesse()
				+","+mesTamagotchis[iTamagotchi].getEnergie()
				+","+mesTamagotchis[iTamagotchi].messageEtat()
				+","+mesTamagotchis[iTamagotchi].getActif();
	}
	
	public void jouer(int iTamagotchi) {
		this.mesTamagotchis[iTamagotchi].manger();
		for(Tamagotchi tamagotchi: mesTamagotchis)
			tamagotchi.vieillir();
	}
	
	public boolean sontMorts() {
		for(Tamagotchi tamagotchi: mesTamagotchis)
			if(tamagotchi.estMort()) return true;
		return false;
	}
	public boolean sontSages() {
		for(Tamagotchi tamagotchi: mesTamagotchis)
			if(!tamagotchi.estSage()) return false;
		return true;
	}
}
