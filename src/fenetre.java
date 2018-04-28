import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class fenetre {
	
	private int nbTamagotchis = 0;		
	private final String[] tabNiveaux = {"Débutant", "Intermédiare", "Avancé"};
	private JeuTamagotchi monJeuTamagotchi;
	
	// Nombre de tamagotchis selon le niveau sélectionné
	private final int[] tabNbTamagotchis = {2,3,4};
	
	// Liste des informations d'un Tamagotchi demandé
	private LinkedList<String> listeInfo = new LinkedList<String>();
	
	// Variables pour la création de l'interface
	private JFrame frame;
	private JPanel[] tabPanel;
	private JLabel[] lblNom, lblIcon, 
					 lblEtiA, lblAge, 
					 lblEtiAS, lblAgeSagesse, 
					 lblEtiE, lblOp, lblEnergie,  
					 lblEtat;
	private JButton[] btnManger;
	
	private final int panelX = 300, panelY = 360;
	private String strFont = "Calibri";
	private Font fontPrincipal, fontNom, fontGras, fontEtiquette, fontOp;
	private Color couleur = Color.CYAN,
					couleurFond = Color.BLACK,
					couleurGagne = Color.GREEN,
					couleurPerdu = Color.RED;
	
	private final String[] tabEtiquettes = {"Âge:", "Âge de sagesse:", "Énergies:", "Manger"},
							tabUnites = {" an", " énergie"};

	private Border borderBTN = BorderFactory.createLineBorder(couleur, 2),
					borderPanel = BorderFactory.createLineBorder(couleurFond, 1),
					borderPanelS = BorderFactory.createLineBorder(couleur, 1);
	
	private WindowListener quitterListener;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fenetre window = new fenetre();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public fenetre() {		
		demanderNiveau();
		
		quitterListener = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				quitter();
			}
		};
		
		initialize();
	}
	
	//Gestion du niveau du jeu (nombre de Tamagotchis)	
	private void demanderNiveau() {
		Boolean boolNiveau = false;
		String strNiveau = "";
		while(!boolNiveau){
			strNiveau = DOTD.afficherCbBox("Avant de commencer...", "Veuillez choisir un niveau", tabNiveaux, 1);
			if(strNiveau != null)
				boolNiveau = true;
			else
				quitter();
			
		}
		
		for(int i = 0; i < tabNiveaux.length; i++){
			if(strNiveau.equals(tabNiveaux[i])) {
				nbTamagotchis = tabNbTamagotchis[i];
				monJeuTamagotchi = new JeuTamagotchi(nbTamagotchis);
			}		
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		int inset = 10;
		int ipady = 5;

		creerFonts();
		
		// frame
		frame = new JFrame();
		frame.setTitle("Tamano Fafard : Jeu de Tamagotchi (version Dans une galaxie près de chez vous)");
		frame.setBounds(0, 0, panelX * nbTamagotchis / (nbTamagotchis>3?2:1), panelY * (nbTamagotchis>3?2:1));		
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(new GridLayout((nbTamagotchis>3?2:1),(nbTamagotchis>3?2:nbTamagotchis),0,0));
		frame.getContentPane().setBackground(couleurFond);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(quitterListener);
		
		// Panel pour chaque Tamagotchi
		tabPanel = new JPanel[nbTamagotchis];
		lblNom = new JLabel[nbTamagotchis];
		lblIcon = new JLabel[nbTamagotchis];
		lblEtiA = new JLabel[nbTamagotchis];
		lblAge = new JLabel[nbTamagotchis];
		lblEtiAS = new JLabel[nbTamagotchis];
		lblAgeSagesse = new JLabel[nbTamagotchis];
		lblEtiE = new JLabel[nbTamagotchis];
		lblOp = new JLabel[nbTamagotchis];
		lblEnergie = new JLabel[nbTamagotchis];
		lblEtat = new JLabel[nbTamagotchis];
		btnManger = new JButton[nbTamagotchis];
		
		for(int i = 0; i < nbTamagotchis; i++){
			tabPanel[i] = new JPanel();
			tabPanel[i].setBackground(couleurFond);
			tabPanel[i].setBorder(borderPanel);
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[]{0, 0, 0};
			gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
			gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			gridBagLayout.rowWeights = new double[]{10.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			tabPanel[i].setLayout(gridBagLayout);
			frame.getContentPane().add(tabPanel[i]);

			recupererInfo(monJeuTamagotchi.getInfo(i));
			
			// Nom
			lblNom[i] = new JLabel(listeInfo.get(0).toString());
			lblNom[i].setHorizontalAlignment(SwingConstants.CENTER);
			lblNom[i].setFont(fontNom);
			lblNom[i].setForeground(couleur);
			lblNom[i].setHorizontalAlignment(SwingConstants.CENTER);
			
			GridBagConstraints gbc_lblNom = new GridBagConstraints();
			gbc_lblNom.fill = GridBagConstraints.NONE;
			gbc_lblNom.insets = new Insets(5, 0, 5, 0);
			gbc_lblNom.gridx = 0;
			gbc_lblNom.gridy = 0;
			gbc_lblNom.gridwidth = 3;
			tabPanel[i].add(lblNom[i], gbc_lblNom);
			
			// Icon
			lblIcon[i] = new JLabel();
			lblIcon[i].setHorizontalAlignment(SwingConstants.CENTER);
			lblIcon[i].setIcon(new ImageIcon(fenetre.class.getResource("/Images/"+listeInfo.get(0).toString()+".png")));
			lblIcon[i].setHorizontalAlignment(SwingConstants.CENTER);
			
			GridBagConstraints gbc_lblIcon = new GridBagConstraints();
			gbc_lblIcon.fill = GridBagConstraints.VERTICAL;
			gbc_lblIcon.insets = new Insets(0, 0, 5, 0);
			gbc_lblIcon.gridx = 0;
			gbc_lblIcon.gridy = 1;
			gbc_lblIcon.gridwidth = 3;
			tabPanel[i].add(lblIcon[i], gbc_lblIcon);
			
			// Âge et son étiquette
			lblEtiA[i] = new JLabel(tabEtiquettes[0]);
			lblEtiA[i].setFont(fontEtiquette);
			lblEtiA[i].setForeground(couleur);
			GridBagConstraints gbc_lblEtiA = new GridBagConstraints();
			gbc_lblEtiA.insets = new Insets(0, inset, 0, 0);
			gbc_lblEtiA.gridx = 0;
			gbc_lblEtiA.gridy = 2;
			gbc_lblEtiA.gridwidth = 2;
			gbc_lblEtiA.ipady = ipady;
			gbc_lblEtiA.anchor = GridBagConstraints.LINE_START;
			tabPanel[i].add(lblEtiA[i], gbc_lblEtiA);
			
			lblAge[i] = new JLabel("");
			lblAge[i].setHorizontalAlignment(SwingConstants.RIGHT);
			lblAge[i].setFont(fontPrincipal);
			lblAge[i].setForeground(couleur);
			GridBagConstraints gbc_lblAge = new GridBagConstraints();
			gbc_lblAge.insets = new Insets(0, 0, 0, inset);
			gbc_lblAge.gridx = 2;
			gbc_lblAge.gridy = 2;
			gbc_lblAge.anchor = GridBagConstraints.LINE_END;
			tabPanel[i].add(lblAge[i], gbc_lblAge);
			
			// Âge de sagesse et son étiquette
			lblEtiAS[i] = new JLabel(tabEtiquettes[1]);
			lblEtiAS[i].setFont(fontEtiquette);
			lblEtiAS[i].setForeground(couleur);
			GridBagConstraints gbc_lblEtiAS = new GridBagConstraints();
			gbc_lblEtiAS.insets = new Insets(0, inset, 0, 0);
			gbc_lblEtiAS.gridx = 0;
			gbc_lblEtiAS.gridy = 3;
			gbc_lblEtiAS.gridwidth = 2;
			gbc_lblEtiAS.ipady = ipady;
			tabPanel[i].add(lblEtiAS[i], gbc_lblEtiAS);
			
			lblAgeSagesse[i] = new JLabel(listeInfo.get(2).toString()+tabUnites[0]+"s");
			lblAgeSagesse[i].setHorizontalAlignment(SwingConstants.RIGHT);
			lblAgeSagesse[i].setFont(fontPrincipal);
			lblAgeSagesse[i].setForeground(couleur);
			GridBagConstraints gbc_lblAgeSagesse = new GridBagConstraints();
			gbc_lblAgeSagesse.insets = new Insets(0, 0, 0, inset);
			gbc_lblAgeSagesse.gridx = 2;
			gbc_lblAgeSagesse.gridy = 3;
			gbc_lblAgeSagesse.anchor = GridBagConstraints.LINE_END;
			tabPanel[i].add(lblAgeSagesse[i], gbc_lblAgeSagesse);
			
			// Énergie, l'opération et son étiquette
			lblEtiE[i] = new JLabel(tabEtiquettes[2]);
			lblEtiE[i].setFont(fontEtiquette);
			lblEtiE[i].setForeground(couleur);
			GridBagConstraints gbc_lblEtiE = new GridBagConstraints();
			gbc_lblEtiE.insets = new Insets(0, inset, 0, 0);
			gbc_lblEtiE.gridx = 0;
			gbc_lblEtiE.gridy = 4;
			gbc_lblEtiE.ipady = 10;
			gbc_lblEtiE.anchor = GridBagConstraints.LINE_START;
			tabPanel[i].add(lblEtiE[i], gbc_lblEtiE);

			lblOp[i] = new JLabel("");
			lblOp[i].setHorizontalAlignment(SwingConstants.RIGHT);
			lblOp[i].setFont(fontOp);
			GridBagConstraints gbc_lblOp = new GridBagConstraints();
			gbc_lblOp.insets = new Insets(2, 2, 2, 2);
			gbc_lblOp.gridx = 1;
			gbc_lblOp.gridy = 4;
			gbc_lblOp.anchor = GridBagConstraints.LINE_END;
			tabPanel[i].add(lblOp[i], gbc_lblOp);

			lblEnergie[i] = new JLabel();
			lblEnergie[i].setHorizontalAlignment(SwingConstants.RIGHT);
			lblEnergie[i].setFont(fontGras);
			lblEnergie[i].setForeground(couleur);
			GridBagConstraints gbc_lblEnergie = new GridBagConstraints();
			gbc_lblEnergie.insets = new Insets(0, 0, 0, inset);
			gbc_lblEnergie.gridx = 2;
			gbc_lblEnergie.gridy = 4;
			gbc_lblEnergie.anchor = GridBagConstraints.LINE_END;
			tabPanel[i].add(lblEnergie[i], gbc_lblEnergie);
			
			// État
			lblEtat[i] = new JLabel();
			lblEtat[i].setHorizontalAlignment(SwingConstants.CENTER);
			lblEtat[i].setFont(fontPrincipal);
			lblEtat[i].setForeground(couleur);
			GridBagConstraints gbc_lblEtat = new GridBagConstraints();
			gbc_lblEtat.insets = new Insets(0, 0, 0, 0);
			gbc_lblEtat.gridx = 0;
			gbc_lblEtat.gridy = 5;
			gbc_lblEtat.gridwidth = 3;
			gbc_lblEtat.ipady = ipady;
			tabPanel[i].add(lblEtat[i], gbc_lblEtat);
			
			// Bouton Manger
			btnManger[i] = new JButton(tabEtiquettes[3]);
			btnManger[i].setFont(fontGras);
			btnManger[i].setForeground(couleur);
			btnManger[i].setBackground(couleurFond);
			btnManger[i].setBorder(borderBTN);
			btnManger[i].addActionListener(new MangerListener(i));
			btnManger[i].addMouseListener(new ChangerCouleursListener(i));
			
			GridBagConstraints gbc_btnManger = new GridBagConstraints();
			gbc_btnManger.insets = new Insets(inset, 0, inset, 0);
			gbc_btnManger.gridx = 0;
			gbc_btnManger.gridy = 6;
			gbc_btnManger.ipadx = 100;
			gbc_btnManger.ipady = ipady;
			gbc_btnManger.gridwidth = 3;
			tabPanel[i].add(btnManger[i], gbc_btnManger);
		}
		mettreAJour();
	}
	
	// Gestion de l'action manger et vieillir
	private class MangerListener implements ActionListener {	
		private int tmgSelectionne;
		public MangerListener(int i) {
			tmgSelectionne = i;
		}
		public void actionPerformed(ActionEvent e) {
			monJeuTamagotchi.jouer(tmgSelectionne);
			mettreAJour();
			verifier();
		}
	}
	
	// Animation pour un tamagotchi sélectionné
	private class ChangerCouleursListener implements MouseListener{
		private int tmgSelectionne;
		
		public ChangerCouleursListener(int i) {
			tmgSelectionne = i;
			recupererInfo(monJeuTamagotchi.getInfo(i));
		}
		
		@Override
		public void mouseClicked(MouseEvent arg0) { }

		@Override
		public void mouseEntered(MouseEvent arg0) {	
			if(DOTD.convertirValeurInt(listeInfo.get(5)) == 2){
				btnManger[tmgSelectionne].setBackground(couleur);
				btnManger[tmgSelectionne].setForeground(couleurFond);
				tabPanel[tmgSelectionne].setBorder(borderPanelS);
			}
		}

		@Override
		public void mouseExited(MouseEvent arg0) { 
			if(DOTD.convertirValeurInt(listeInfo.get(5)) == 2){
				btnManger[tmgSelectionne].setBackground(couleurFond);
				btnManger[tmgSelectionne].setForeground(couleur);
				tabPanel[tmgSelectionne].setBorder(borderPanel);
			}
		}

		@Override
		public void mousePressed(MouseEvent arg0) { }

		@Override
		public void mouseReleased(MouseEvent arg0) { }
		
	}
	
	/* Récupération des informations d'un Tamagotchi dans un LinkedList	
		0:Nom
		1:Age
		2:Age de sagesse
		3:Énergie
		4:Message d'état
		5:Code actif
	*/
	public void recupererInfo(String strInfo) {		
		if(!listeInfo.isEmpty()) listeInfo.remove();
		listeInfo = new LinkedList();
		String[] tab = strInfo.split(",");
		for(String info: tab) {
			listeInfo.add(info);
		}
	}
	
	// Mise à jour des données
	public void mettreAJour() {
		int energieTempo = 0, age, energie, actif;
		String[] tabString;
		for(int i = 0; i < nbTamagotchis; i++){
			recupererInfo(monJeuTamagotchi.getInfo(i));
			
			// MAJ âge
			age = DOTD.convertirValeurInt(listeInfo.get(1).toString());
			lblAge[i].setText(age+tabUnites[0]+DOTD.formatPluriel(age));
			
			// MAJ énergie et opération (soit gagner ou perdre de l'énergie)
			energie = DOTD.convertirValeurInt(listeInfo.get(3).toString());
			tabString = DOTD.creerTableauStr(lblEnergie[i].getText(), " ");
			energieTempo = DOTD.convertirValeurInt(tabString[0]);		
			
			if(age > 0) {	
				if(energieTempo < energie) {
					lblOp[i].setText("(+"+(energie - energieTempo)+")");
					lblOp[i].setForeground(couleurGagne);
				}
				else if(energieTempo > energie) {
					lblOp[i].setText("(-"+(energieTempo - energie)+")");
					lblOp[i].setForeground(couleurPerdu);
				}
				else {
					lblOp[i].setText("(+0)");
					lblOp[i].setForeground(couleur);
				}
			}			
			lblEnergie[i].setText(energie+tabUnites[1]+DOTD.formatPluriel(energie));
			
			// MAJ état
			lblEtat[i].setText(listeInfo.get(4).toString());

			// MAJ des couleurs et du bouton 'Manger' quand un tamagotchi gagne ou meurt
			actif = DOTD.convertirValeurInt(listeInfo.get(5).toString());
			if(actif != 2) {
				btnManger[i].setEnabled(false);					
				btnManger[i].setForeground(couleurFond);
				btnManger[i].setBackground(couleurFond);				
				btnManger[i].setBorder(borderPanel);
				
				for (Component jCompo : tabPanel[i].getComponents())
					if ( jCompo instanceof JLabel )
						((JLabel) jCompo).setForeground(actif == 0?couleurGagne:couleurPerdu);
			}
		}
	}
	
	// Création des fonts
	public void creerFonts() {
		fontPrincipal = new Font(strFont, 1, 18);
		fontNom = fontPrincipal.deriveFont(Font.BOLD, 22);
		fontGras = fontPrincipal.deriveFont(Font.BOLD);
		fontEtiquette = fontPrincipal.deriveFont(Font.ITALIC | Font.PLAIN, 16);
		fontOp = fontPrincipal.deriveFont(Font.BOLD, 14);
	}
	
	// Vérification si le joueur gagne ou perdre
	public void verifier() {
		if(monJeuTamagotchi.sontSages()) {
			DOTD.afficherMsgBox("Bravo! Vous avez gagné!! Merci d'avoir joué!!");
			System.exit(0);
		}else if(monJeuTamagotchi.sontMorts()){
			DOTD.afficherMsgBox("Ayoye! Vous avez perdu... Bon ben.. merci d'avoir joué!!");
			System.exit(0);
		}
	}
	
	// Confirmation pour quitter le jeu	
	public void quitter(){
		if(DOTD.afficherCfmBox("Quitter", "Voulez-vous vraiment quitter le jeu?") == 0) 
			System.exit(0);
	}
}
