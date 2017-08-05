package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;

import controller.GameController;

import model.Disease;
import model.Location;
import model.Player;
import model.PlayerCard;
import model.WorldMap;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.AbstractListModel;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private WorldView worldView;
	private JPanel panelButtons;
	private JButton btnTreat;
	private JButton btnPass;
	private JPanel panelPlayer;
	private JLabel lblPlayername;
	private JLabel lblLocation;
	private JLabel lblActionsLeft;
	private JList listPlayerCards;
	private DefaultListModel listmodel;
	private JPanel panelPlayerText;
	private JPanel panelPlayerCards;
	private JPanel panelDiseasesCured;
	private JButton btnFlyTo;
	private JLabel lblCards;
	private JButton btnBuildRC;
	private JButton btnShareKnowledge;
	private JButton btnCure;
	private JButton btnShuttleFlight;
	private JButton btnCharterFlight;
	private JButton btnRestartScenario;
	private JPanel panelPlayerCardDeck;
	private JPanel panelSetPlayerRole;
	private JLabel lblPlayerCardDeck;
	private JLabel lblCardsLeft;
	private JButton btnHelp;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainFrame frame = new MainFrame(new WorldMap());
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public MainFrame(WorldMap worldMap) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(new BorderLayout(0, 0));
		worldView = new WorldView(worldMap);
		worldView.setBackground(Color.DARK_GRAY);
		panel.add(worldView);
		
		panelButtons = new JPanel();
		contentPane.add(panelButtons, BorderLayout.EAST);
		panelButtons.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnMoveTo = new JButton("movement1 : FERRY  / DRIVE");
		panelButtons.add(btnMoveTo);
		btnMoveTo.addActionListener(new MoveToActionListener());
		
		btnFlyTo = new JButton("movement2 : DIRECT FLIGHT");
		btnFlyTo.addActionListener(new FlyToActionListener());
		panelButtons.add(btnFlyTo);
		
		btnCharterFlight = new JButton("movement3 : CHARTER FLIGHT");
		btnCharterFlight.addActionListener(new CharterActionListener());
		panelButtons.add(btnCharterFlight);
		
		btnShuttleFlight = new JButton("movement4 : SHUTTLE FLIGHT");
		btnShuttleFlight.addActionListener(new ShuttleActionListener());
		panelButtons.add(btnShuttleFlight);
		
		btnTreat = new JButton("Treat");
		btnTreat.addActionListener(new TreatActionListener());
		panelButtons.add(btnTreat);
		
		btnBuildRC = new JButton("Build RC");
		btnBuildRC.addActionListener(new BuildRCActionListener());
		panelButtons.add(btnBuildRC);
		
		btnShareKnowledge = new JButton("Share knowledge");
		btnShareKnowledge.addActionListener(new ShareKnowledgeListener());
		panelButtons.add(btnShareKnowledge);
		
		btnRestartScenario = new JButton("RESET GAME WITH SCENARIO");
		btnRestartScenario.addActionListener(new RestartListener());
		panelButtons.add(btnRestartScenario);
		
		btnCure = new JButton("Cure");
		btnCure.addActionListener(new CureActionListener());
		panelButtons.add(btnCure);
		
		btnPass = new JButton("++++++++PASS YOUR TURN++++++++");
		btnPass.addActionListener(new PassActionListener());
		panelButtons.add(btnPass);
		
		btnHelp = new JButton("Help");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HelpFrame help = new HelpFrame();
				help.setVisible(true);
			}
		});
		panelButtons.add(btnHelp);
		
		panelPlayer = new JPanel();
		contentPane.add(panelPlayer, BorderLayout.SOUTH);
		panelPlayer.setLayout(new BorderLayout(0, 0));
		
		panelPlayerCards = new JPanel();
		panelPlayer.add(panelPlayerCards, BorderLayout.CENTER);
		
		listmodel = new DefaultListModel();
		
		lblCards = new JLabel("Cards: ");
		panelPlayerCards.add(lblCards);
		listPlayerCards = new JList(listmodel);
		listPlayerCards.setVisibleRowCount(7);
		panelPlayerCards.add(listPlayerCards);
		
		panelPlayerText = new JPanel();
		panelPlayer.add(panelPlayerText, BorderLayout.NORTH);
		
		lblPlayername = new JLabel("PlayerName");
		panelPlayerText.add(lblPlayername);
		
		lblLocation = new JLabel("Location");
		panelPlayerText.add(lblLocation);
		
		lblActionsLeft = new JLabel("Actions left");
		panelPlayerText.add(lblActionsLeft);
		
		panelPlayerCardDeck = new JPanel();
		panelPlayerCardDeck.setBackground(Color.LIGHT_GRAY);
		panelPlayer.add(panelPlayerCardDeck, BorderLayout.WEST);
		panelPlayerCardDeck.setLayout(new BorderLayout(0, 0));
		
		panelDiseasesCured = new JPanel();
		panelDiseasesCured.setBackground(Color.LIGHT_GRAY);
		panelPlayer.add(panelDiseasesCured, BorderLayout.EAST);
		panelDiseasesCured.setLayout(new BorderLayout(0, 0));
		
		lblPlayerCardDeck = new JLabel("Player card deck:");
		panelPlayerCardDeck.add(lblPlayerCardDeck, BorderLayout.NORTH);
		
		lblCardsLeft = new JLabel("cards left");
		panelPlayerCardDeck.add(lblCardsLeft);
		
		panelSetPlayerRole = new JPanel();
		panelSetPlayerRole.setBackground(Color.LIGHT_GRAY);
		panelPlayer.add(panelSetPlayerRole, BorderLayout.EAST);
		panelSetPlayerRole.setLayout(new BorderLayout(0,0));
		
		//update world view
		updateWorld();
		
	}
	
	public WorldView getWorldView()  {
		return worldView;
	}
	
	public Object clickCanvas(Integer x, Integer y) {
    	return worldView.clickCanvas(x, y);
    }
	
	/**
	 * update World view
	 */
	public void updateWorld() {
		worldView.updateWorld();
		updatePlayerPanel();
	}
	
	/**
	 * update Player view
	 */
	public void updatePlayers() {
		worldView.updatePlayers();
		updatePlayerPanel();
		
	}
	
	/**
	 * Update current player panel
	 */
	private void updatePlayerPanel() {
		Player current = GameController.getCurrentPlayer();
		lblPlayername.setText(current.getName());
		lblLocation.setText(current.getLocation().getName());
		lblActionsLeft.setText("Actions left:"+
				Integer.valueOf(GameController.getActionsLeft()).toString());
		listmodel.clear();
		for (PlayerCard card : current.getPlayerCards()) {
			listmodel.addElement(card);
		}
		lblCardsLeft.setText(String.valueOf(GameController.getPlayerCardsLeft())
				+" player cards left");
	}
	
	public void notifyDrawnCard(Player player,PlayerCard card) {
		JOptionPane.showMessageDialog(null, player.getName()+" draws card "+card.toString(), 
				"Drawn card",JOptionPane.INFORMATION_MESSAGE);		
	}
	
	public void notifyEpidemic(Location location) {
		JOptionPane.showMessageDialog(null, "Epidemic in "+location.getName(), 
				"Epidemic",JOptionPane.WARNING_MESSAGE);		
	}
	
	public void notifyOutbreak(Location location) {
		JOptionPane.showMessageDialog(null, "Outbreak in "+location.getName(), 
				"Outbreak",JOptionPane.WARNING_MESSAGE);
	}
	/**
	 * Action listener for move to button
	 */
	private class MoveToActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			ClickableObject obj = worldView.getClickedObject();
			if(obj != null) {
				if(obj.getClass() == LocationView.class) {
					boolean ret = GameController.moveCurrentPlayerToLocation((Location)obj.modelTarget);
					if(!ret) {
						JOptionPane.showMessageDialog(null, "You can't move there", 
								"Invalid move",JOptionPane.ERROR_MESSAGE);
					}
					else {
						updatePlayers();
						/*JOptionPane.showMessageDialog(null, "Drive/Ferry action successful", 
								"Action Complete",JOptionPane.OK_CANCEL_OPTION);*/
					}
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "You have to select a destination city before performing a movement", 
						"Select destination city first",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**
	 * Action listener for pass button
	 * @author jbghoul
	 *
	 */
	private class PassActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			GameController.pass();
			updatePlayers();
		}
	}
	
	
	/**
	 * Action listener for Treat button
	 * @author jbghoul
	 *
	 */
	private class TreatActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Location location = GameController.getCurrentPlayer().getLocation();
			List<Disease> diseases = new ArrayList<Disease>(location.getInfections().keySet());
			List<Disease> effDiseases = new ArrayList<Disease>();
			List<String> options = new ArrayList<String>();
			for (Disease disease : diseases) {
				Integer count = location.getInfections().get(disease);
				if(location.getInfections().get(disease) >0 ) {
					options.add(disease.toString()+" ("+count+")");
					effDiseases.add(disease);
				}
			}
			int n = JOptionPane.showOptionDialog(null,
				    "Which disease do you want to treat ? ",
				    "Treat current location",
				    JOptionPane.YES_NO_CANCEL_OPTION,
				    JOptionPane.QUESTION_MESSAGE,
				    null,
				    options.toArray(),
				    null);
			if(n!=-1) {
				if( ! GameController.treatCurrentLocation(effDiseases.get(n)) ) {
					JOptionPane.showMessageDialog(null, "You can't treat this", 
							"Invalid treat",JOptionPane.ERROR_MESSAGE);
				} else {
					updateWorld();
				}
			}
			
		}
	}
	
	/**
	 * Action listener for Cure button
	 * @author Shashank
	 *
	 */
	private class CureActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Location location = GameController.getCurrentPlayer().getLocation();
			Object[] selected = listPlayerCards.getSelectedValues();
			if(selected == null) {
				JOptionPane.showMessageDialog(null, "Select TWO cards of same color to cure disease", 
						"Cure Disease",JOptionPane.INFORMATION_MESSAGE);
			}
			if(selected.length == 0) {
				JOptionPane.showMessageDialog(null, "Select TWO cards of same color to cure disease", 
						"Cure Disease",JOptionPane.INFORMATION_MESSAGE);
			}
			else if(selected.length == 1){
				JOptionPane.showMessageDialog(null, "You need to select atlease two cards(NOTE:press ctrl to select two cards)", 
						"Cure Disease",JOptionPane.OK_OPTION);
			}
			else if(selected.length == 2){
				PlayerCard c1 = (PlayerCard) selected[0];
				PlayerCard c2 = (PlayerCard) selected[1];
				if(GameController.cure(c1,c2)){
					updatePlayers();
					updateWorld();
					JOptionPane.showMessageDialog(null, "CONGRATULATIONS! Disease Cured", 
							"Cure Disease",JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog(null, "Sorry can't cure the disease with the cards you selected", 
							"Cure Disease",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}
	
	/**
	 * Action listener for Fly To button
	 * @author jbghoul
	 *
	 */
	private class FlyToActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ClickableObject obj = worldView.getClickedObject();
			if(obj!=null) {
				if(obj.getClass() == LocationView.class) {
					Object[] selected = listPlayerCards.getSelectedValues();
					if(selected == null) {
						JOptionPane.showMessageDialog(null, "Select ONE card to Fly", 
								"DIRECT FLIGHT",JOptionPane.INFORMATION_MESSAGE);
					} else if (selected.length == 1) {
						PlayerCard card = (PlayerCard) selected[0];
						Location location = (Location)obj.modelTarget;
						if(GameController.flyCurrentPlayerTo(location, card)) {
							updatePlayers();
							JOptionPane.showMessageDialog(null, "You have taken a direct flight", 
									"DIRECT FLIGHT",JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "You can't this direct flight with the selected card", 
									"DIRECT FLIGHT",JOptionPane.INFORMATION_MESSAGE);
						}
						
					} else {
						JOptionPane.showMessageDialog(null, "Select a card before taking flight", 
								"DIRECT FLIGHT",JOptionPane.INFORMATION_MESSAGE);
					}					
				}
				
			}
			else{
				JOptionPane.showMessageDialog(null, "Please Select destination city before clicking direct flight", 
						"DIRECT FLIGHT",JOptionPane.ERROR_MESSAGE);
			}
			
			
		}
	}
	
	private class CharterActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ClickableObject obj = worldView.getClickedObject();
			if(obj!=null) {
				if(obj.getClass() == LocationView.class) {
					Object[] selected = listPlayerCards.getSelectedValues();
					if(selected == null) {
						JOptionPane.showMessageDialog(null, "Select ONE card to Fly", 
								"CHARTER FLIGHT",JOptionPane.INFORMATION_MESSAGE);
					} else if (selected.length == 1) {
						PlayerCard card = (PlayerCard) selected[0];
						Location location = (Location)obj.modelTarget;
						if(GameController.charterPlayerTo(location, card)) {
							updatePlayers();
							JOptionPane.showMessageDialog(null, "You have taken a charter flight", 
									"CHARTER FLIGHT",JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "You can't this charter flight with the selected card", 
									"CHARTER FLIGHT",JOptionPane.INFORMATION_MESSAGE);
						}
						
					} else {
						JOptionPane.showMessageDialog(null, "Select a card before taking flight", 
								"CHARTER FLIGHT",JOptionPane.INFORMATION_MESSAGE);
					}					
				}
				
			}
			else{
				JOptionPane.showMessageDialog(null, "Select destination city before taking flight", 
						"CHARTER FLIGHT",JOptionPane.ERROR_MESSAGE);
			}
			
			
		}
	}
	
	private class BuildRCActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
					  
						if(GameController.buildRC()) {
							updateWorld();
							
							JOptionPane.showMessageDialog(null, "Research Center built", 
									"Research Center built",JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "You can't build a Research Center as you dont have the correct City card", 
									"Unable to build Research Center",JOptionPane.INFORMATION_MESSAGE);
						}
											
		}
	}
	
	private class ShuttleActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ClickableObject obj = worldView.getClickedObject();
			if(obj!=null) {
				if(obj.getClass() == LocationView.class) {
					Location location = (Location)obj.modelTarget;
					if(GameController.shuttle(location)){
						updatePlayers();
						JOptionPane.showMessageDialog(null, "Shuttle between Research Centers complete", 
								"Shuttle Flight",JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null, "Shuttle Flight not possible", 
								"Shuttle Flight",JOptionPane.INFORMATION_MESSAGE);
					}
				}					
				}
			else{
				JOptionPane.showMessageDialog(null, "Select destination city before taking flight", 
						"CHARTER FLIGHT",JOptionPane.ERROR_MESSAGE);
			}
				
			}
	}
	
	private class ShareKnowledgeListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int result = GameController.shareKnowledge();
			
			           if(result == 0){
			        	   updateWorld();
							JOptionPane.showMessageDialog(null, "Cannot share knowledge,Players not at same City", 
									"Can't share knowledge",JOptionPane.INFORMATION_MESSAGE);
			           }
			           else if(result== 1){
			        	   updateWorld();
							
							JOptionPane.showMessageDialog(null, "You have GIVEN your card to the other Player", 
									"Card GIVEN",JOptionPane.INFORMATION_MESSAGE);
			           }
			           else if(result == 2){
			        	   updateWorld();
							
							JOptionPane.showMessageDialog(null, "You have TAKEN your card from the other Player", 
									"Card TAKEN",JOptionPane.INFORMATION_MESSAGE);
			           }
			           else if(result == 3){
			        	   updateWorld();
							
							JOptionPane.showMessageDialog(null, "Neither player has the City card,knowledge transfer not possible", 
									"No City Card",JOptionPane.INFORMATION_MESSAGE);
			           }
		}
	}
	

	private class RestartListener implements ActionListener {
	
	public void actionPerformed(ActionEvent e) {
		GameController.restart();
		updatePlayers();
		JOptionPane.showMessageDialog(null, "Player locations have been reset to Paris and Tokyo", 
				"Reset Game Scenario",JOptionPane.INFORMATION_MESSAGE);
		
	}
	}

	

}

