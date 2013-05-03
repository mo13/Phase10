package view;

import javax.swing.*;

import controller.Controller;

import model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.*;


public class Gui implements ActionListener, GameObserver {
	
	Controller controller;
	GameModel model;
	
	
	public Gui(GameModel model) {		
		this.model = model;
		model.registerObserver((GameObserver) this);		
	}
	
	@Override
	public void registerController(Controller controller) {
		this.controller = controller;
		
	}
    
	private PlayerArea player1Area, player2Area,player3Area, player4Area;

	private JTextField textField;
	public static JFrame frame;
	private JButton button1, button2, button3, button4, button5, button6, button7, button8;
	private JPanel contentPane, leftPanel, rightPanel, topPanel, bottomPanel, centerPanel;
	private JLabel img, chiefImg, johnsonImg, cortonaImg, arbiterImg, tempLabel;
	private JMenuBar menuBar;
	private JMenuItem startRound, showOrder, resetDrawPile,
					  player1LowestScoreStrategy, player1PreventerStrategy, player1RandomStrategy, player1RecklessStrategy,
					  player2LowestScoreStrategy, player2PreventerStrategy, player2RandomStrategy, player2RecklessStrategy,
					  player3LowestScoreStrategy, player3PreventerStrategy, player3RandomStrategy, player3RecklessStrategy,
					  player4LowestScoreStrategy, player4PreventerStrategy, player4RandomStrategy, player4RecklessStrategy,
					  setPlayerStrategy, player1, player2, player3, player4,
					  scorePlayers, phaseTracker, displayScore,  
					  draw, playPhase, hit,  checkHit, discard, finishTurn;
	private JMenu menu, round, playerOptions, scoring;
	public JMenuItem exitRound;

	public static JMenuItem exitMenu;
	
	public void createUI() {
/*
*		The actual board itself.
*		
*/
		// Frame
		frame = new JFrame();
		frame.setTitle("Phase 10");
		frame.setVisible(true);
		frame.setSize(1280,700);
		contentPane = new JPanel(new BorderLayout());
		//top
		topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		topPanel.setBackground(Color.RED);
		
		//topPanel.setPreferredSize(new Dimension(400,550));
		topPanel.setBorder(BorderFactory.createLineBorder(Color.white));
		chiefImg = new JLabel(new ImageIcon("chief.jpg"), JLabel.CENTER);

		topPanel.add(chiefImg);
		//left
		leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.Y_AXIS));
		leftPanel.setBackground(Color.RED);
		leftPanel.setBorder(BorderFactory.createLineBorder(Color.white));
		cortonaImg = new JLabel(new ImageIcon("cortona.jpg"), JLabel.CENTER);
		leftPanel.add(cortonaImg);
		//center
		centerPanel = new JPanel();
		centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		centerPanel.setBorder(BorderFactory.createLineBorder(Color.white));
		centerPanel.setBackground(Color.RED);
		//right
		rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS	));
		rightPanel.setBackground(Color.RED);
		rightPanel.setBorder(BorderFactory.createLineBorder(Color.white));
		johnsonImg = new JLabel(new ImageIcon("johnson.jpg"));
		rightPanel.add(johnsonImg);
		//bottom
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		bottomPanel.setBackground(Color.RED);
		bottomPanel.setBorder(BorderFactory.createLineBorder(Color.white));
		arbiterImg = new JLabel(new ImageIcon("arbiter.jpg"));
		bottomPanel.add(arbiterImg);
		contentPane.add(bottomPanel, BorderLayout.PAGE_END);
		contentPane.add(leftPanel,BorderLayout.LINE_START);
		contentPane.add(topPanel, BorderLayout.PAGE_START);
		contentPane.add(rightPanel, BorderLayout.EAST);
		contentPane.add(centerPanel, BorderLayout.CENTER);
		frame.setVisible(true);
		frame.setContentPane(contentPane);
	
		
/*
*		The menus.
*		
*/		
	// The Menu Bar
		menuBar = new JMenuBar();
		
		
	//Game Menu
//		menu = new JMenu("Game");
//		menuBar.add(menu);		
//		setupMenuItem = new JMenuItem("Setup Board");
//		setupMenuItem.addActionListener(this);
//		menu.add(setupMenuItem);

		
//round Menu
		round = new JMenu("Round Options");
		menuBar.add(round);
		
		showOrder = new JMenuItem("Show Order");
		showOrder.addActionListener(this);
		round.add(showOrder);
		
		startRound = new JMenuItem("Start Round");
		startRound.addActionListener(this);
		round.add(startRound);
		
		resetDrawPile = new JMenuItem("Reset Draw Pile");
		resetDrawPile.addActionListener(unimplementedMenu_Click("This will reset the draw pile. "));
		round.add(resetDrawPile);
		
		exitRound= new JMenuItem("Exit Round");
		exitRound.addActionListener(unimplementedMenu_Click("This will exit the round."));
		round.add(exitRound);
		
// Scoring Menu
		scoring = new JMenu("Scoring options ");
		menuBar.add(scoring);
		
		scorePlayers = new JMenuItem("Score Players");
		scorePlayers.addActionListener(unimplementedMenu_Click("This will score the players at the end of the round. "));
		scoring.add(scorePlayers);
		
		displayScore = new JMenuItem("Display Score");
		displayScore.addActionListener(unimplementedMenu_Click("This will display each players score.  "));
		scoring.add(displayScore);
		
		phaseTracker = new JMenuItem("Display Phases");
		phaseTracker.addActionListener(unimplementedMenu_Click("This will display each players phase they are on."));
		scoring.add(phaseTracker);
		
	//Player Options Menu
		playerOptions = new JMenu("Player Options");
		menuBar.add(playerOptions);
		
		draw = new JMenuItem("Draw");
		draw.addActionListener(unimplementedMenu_Click("This will make the player draw a card."));
		playerOptions.add(draw);
		
		hit = new JMenuItem("Hit");
		hit.addActionListener(unimplementedMenu_Click("This will make the player hit a collection someone already played."));
		playerOptions.add(hit);

		playPhase = new JMenuItem("Phase Out");
		playPhase.addActionListener(unimplementedMenu_Click("This will make the player phase out when he has a phase completed."));
		playerOptions.add(playPhase);
		
		checkHit = new JMenuItem("Check Hit");
		checkHit.addActionListener(unimplementedMenu_Click("This will make the player check other players phases to see if he can hit them. "));
		playerOptions.add(checkHit);
		
		discard = new JMenuItem("Discard");
		discard.addActionListener(unimplementedMenu_Click("This will make the player discard a card."));
		playerOptions.add(discard);
		
		finishTurn = new JMenuItem("Finish turn");
		finishTurn.addActionListener(unimplementedMenu_Click("This will make the player end his turn."));
		playerOptions.add(finishTurn);
		
		
		/*
		 * Create the players for the strategy menu
		 */
		setPlayerStrategy = new JMenu("Set Player Strategy");
		//setPlayerStrategy.addActionListener(unimplementedMenu_Click("This will set allow you to set player strategies."));
		playerOptions.add(setPlayerStrategy);
		player1 = new JMenu("Master Chief");
		//player1.addActionListener(unimplementedMenu_Click("This allows you to set player 1's strategy"));
		player1RandomStrategy = new JMenuItem("Random Strategy");
		player1RandomStrategy.addActionListener(this);
		player1PreventerStrategy = new JMenuItem("Preventer Strategy");
		player1PreventerStrategy.addActionListener(this);
		player1LowestScoreStrategy = new JMenuItem("Lowest Score Strategy");
		player1LowestScoreStrategy.addActionListener(this);
		player1RecklessStrategy = new JMenuItem("Card Counter Strategy");
		player1RecklessStrategy.addActionListener(this);
		player1.add(player1RandomStrategy);
		player1.add(player1PreventerStrategy);
		player1.add(player1LowestScoreStrategy);
		player1.add(player1RecklessStrategy);
		setPlayerStrategy.add(player1);
		player2 = new JMenu("Cortona");
		//player2.addActionListener(unimplementedMenu_Click("This allows you to set player 1's strategy"));
		player2RandomStrategy = new JMenuItem("Random Strategy");
		player2RandomStrategy.addActionListener(this);
		player2PreventerStrategy = new JMenuItem("Preventer Strategy");
		player2PreventerStrategy.addActionListener(this);
		player2LowestScoreStrategy = new JMenuItem("Lowest Score Strategy");
		player2LowestScoreStrategy.addActionListener(this);
		player2RecklessStrategy = new JMenuItem("Card Counter Strategy");
		player2RecklessStrategy.addActionListener(this);
		player2.add(player2RandomStrategy);
		player2.add(player2PreventerStrategy);
		player2.add(player2LowestScoreStrategy);
		player2.add(player2RecklessStrategy);
		setPlayerStrategy.add(player2);
		player3 = new JMenu("Johnson");
	//	player3.addActionListener(unimplementedMenu_Click("This allows you to set player 1's strategy"));
		player3RandomStrategy = new JMenuItem("Random Strategy");
		player3RandomStrategy.addActionListener(this);
		player3PreventerStrategy = new JMenuItem("Preventer Strategy");
		player3PreventerStrategy.addActionListener(this);
		player3LowestScoreStrategy = new JMenuItem("Lowest Score Strategy");
		player3LowestScoreStrategy.addActionListener(this);
		player3RecklessStrategy = new JMenuItem("Card Counter Strategy");
		player3RecklessStrategy.addActionListener(this);
		player3.add(player3RandomStrategy);
		player3.add(player3PreventerStrategy);
		player3.add(player3LowestScoreStrategy);
		player3.add(player3RecklessStrategy);
		setPlayerStrategy.add(player3);
		player4 = new JMenu("Arbiter");
		//player4.addActionListener(unimplementedMenu_Click("This allows you to set player 1's strategy"));
		player4RandomStrategy = new JMenuItem("Random Strategy");
		player4RandomStrategy.addActionListener(this);
		player4PreventerStrategy = new JMenuItem("Preventer Strategy");
		player4PreventerStrategy.addActionListener(this);
		player4LowestScoreStrategy = new JMenuItem("Lowest Score Strategy");
		player4LowestScoreStrategy.addActionListener(this);
		player4RecklessStrategy = new JMenuItem("Card Counter Strategy");
		player4RecklessStrategy.addActionListener(this);
		player4.add(player4RandomStrategy);
		player4.add(player4PreventerStrategy);
		player4.add(player4LowestScoreStrategy);
		player4.add(player4RecklessStrategy);
		setPlayerStrategy.add(player4);
	
		exitMenu = new JMenu("Exit game");
		exitMenu.addActionListener(this);
		menuBar.add(exitMenu);
	
		frame.setJMenuBar(menuBar);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

		

	
	public void updateHandSize(JPanel playerArea, JLabel handSize, JLabel img, JLabel phasedOut){
		playerArea.removeAll();
		if (playerArea == centerPanel){
			playerArea.add(handSize, playerArea.CENTER_ALIGNMENT);
		} else {
			playerArea.add(handSize);
		}
	
		
		playerArea.add(img);
		playerArea.validate();
//		else
//			tempLabel = new JLabel("Not phased out yet.", JLabel.RIGHT);	
//			playerArea.add(tempLabel, JLabel.RIGHT);
//		
//	}
	}
	Deck drawPile;
	Deck discardPile;
	
	
	public void actionPerformed(ActionEvent event){
		if (event.getSource() == showOrder){
			controller.setPlayerOrder();
			String str = controller.showPlayerOrder();
			JOptionPane.showMessageDialog(null, str);
			
			System.out.println(str);
		} else if (event.getSource() == startRound){
			controller.dealCards();
			//System.out.println(controller.playerList.get(0).getHandSize())
			
			JLabel topHand = new JLabel(controller.playerList.get(0).getHandSize(), JLabel.LEFT);
			JLabel rightHand = new JLabel(controller.playerList.get(0).getHandSize());
			JLabel bottomHand = new JLabel(controller.playerList.get(0).getHandSize(), JLabel.LEFT);
			JLabel leftHand = new JLabel(controller.playerList.get(0).getHandSize());
			JLabel topPhase = new JLabel("Not phased out yet", JLabel.RIGHT	);
			
			JOptionPane.showMessageDialog(null, "Cards dealt");
			JLabel drawPile = new JLabel("The draw Pile", JLabel.CENTER);
			drawPile.setSize(100, 100);
			drawPile.setBackground(Color.WHITE);
			drawPile.setBorder(BorderFactory.createLineBorder(Color.black));
			JLabel topDiscardPile= new JLabel(controller.getTopDiscard().toString(), JLabel.CENTER);
			topDiscardPile.setSize(100, 100);
			topDiscardPile.setBackground(Color.WHITE);
			topDiscardPile.setBorder(BorderFactory.createLineBorder(Color.black));
			updateHandSize(centerPanel, drawPile, topDiscardPile,null);
			updateHandSize(topPanel, topHand , chiefImg, null);
			updateHandSize(rightPanel, rightHand, johnsonImg, null);
			updateHandSize(bottomPanel, bottomHand, arbiterImg, null);
			updateHandSize(leftPanel, leftHand, cortonaImg, null);
			
//			player1.add(player1RandomStrategy);
//			player1.add(player1PreventerStrategy);
//			player1.add(player1LowestScoreStrategy);
//			player1.add(player1RecklessStrategy);
		} else if (event.getSource() == player1RandomStrategy ){
			System.out.println("Random Strategy");
		} else if (event.getSource() ==  player2RandomStrategy ){
			System.out.println("Random Strategy");
		} else if (event.getSource() == player3RandomStrategy){
			System.out.println("Random Strategy");
		}else if (event.getSource() == player4RandomStrategy){
			System.out.println("Random Strategy");
		}else if (event.getSource() == player1PreventerStrategy){
			System.out.println("Preventer strategy");
		} else if (event.getSource() == player2PreventerStrategy){
			System.out.println("Preventer strategy");
		} else if (event.getSource() == player3PreventerStrategy){
			System.out.println("Preventer strategy");
		} else if (event.getSource() == player4PreventerStrategy){
			System.out.println("Preventer strategy");
		} else if (event.getSource() == player1LowestScoreStrategy){
			System.out.println("Lowest Score Strategy");
		} else if (event.getSource() == player2LowestScoreStrategy){
			System.out.println("Lowest Score Strategy");
		} else if (event.getSource() == player3LowestScoreStrategy){
			System.out.println("Lowest Score Strategy");
		} else if (event.getSource() == player4LowestScoreStrategy){
			System.out.println("Lowest Score Strategy");
		} else if (event.getSource() == player1RecklessStrategy){
			System.out.println("Reckless Strategy");
		} else if (event.getSource() == player1RecklessStrategy){
			System.out.println("Reckless Strategy");
		} else if (event.getSource() == player2RecklessStrategy){
			System.out.println("Reckless Strategy");
		} else if (event.getSource() == player3RecklessStrategy){
			System.out.println("Reckless Strategy");
		} else if (event.getSource() == player4RecklessStrategy){
			System.out.println("Reckless Strategy");
		}
		else if (event.getSource() == exitMenu){
			frame.setVisible(false);
			frame.dispose();
			System.exit(0);
		}
		
	}
	
	public static ActionListener unimplementedMenu_Click(final String message) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(null, message);
				System.out.println(message);
			}
		};
	}







	

}