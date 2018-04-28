/*	Thang David Do
 * 	171013
 * 
 *  Liste de fonctions pour le projet de Tamagotchi
 * */
import javax.swing.*;

public class DOTD {

	public static void afficherMsgBox(String strMessage){
		JOptionPane.showMessageDialog(null,strMessage);
	}
	
	public static String afficherCbBox(String strTitre, String strMessage, String[] tab, int index){
		return((String) JOptionPane.showInputDialog(new JFrame(), 
				strMessage, strTitre, 
				JOptionPane.QUESTION_MESSAGE, 
				null, tab, tab[index]));		
	}
	
	public static int afficherCfmBox(String strTitre, String strMessage){
		return(JOptionPane.showConfirmDialog(null, strMessage, strTitre, JOptionPane.YES_NO_OPTION));		
	}
	
	public static String afficherInputBox(String strMessage){
		return JOptionPane.showInputDialog(strMessage);
	}

	public static String[] creerTableauStr(String strListe, String strSplit){
		String[] strTab = strListe.split(strSplit);
		return strTab;		
	}

	public static int convertirValeurInt(String strMessage){
		int iRetour = 0;
		try {iRetour =  Integer.parseInt(strMessage);} 
		catch (Exception e) {}
		return iRetour;
	}
	
	
	public static String formatPluriel(int intValeur){
		return  (intValeur>1?"s":"");
	}

	public static int generer(int nb, boolean bool0){
		return (int) Math.floor(Math.random() * nb) + (bool0?0:1);
	}
}
