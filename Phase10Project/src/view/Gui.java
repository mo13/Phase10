package view;

import javax.swing.*;

import strategy.Strategy.strategyType;

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
    
	

	private JTextField textField;
	public static JFrame frame;
	private JButton button1, button2, button3, button4, button5, button6, button7, button8;
	private JPanel contentPane, leftPanel, rightPanel, topPanel, bottomPanel, centerPanel;
	private JLabel img, chiefImg, johnsonImg, cortonaImg, arbiterImg, tempLabel;
	private JMenuBar menuBar;
	private JMenuItem startRound, showOrder, resetDrawPile, doAWholeRound, emptyHands, resetPlayer,
					  player1LowestScoreStrategy, player1PreventerStrategy, player1RandomStrategy, player1RecklessStrategy,
					  player2LowestScoreStrategy, player2PreventerStrategy, player2RandomStrategy, player2RecklessStrategy,
					  player3LowestScoreStrategy, player3PreventerStrategy, player3RandomStrategy, player3RecklessStrategy,
					  player4LowestScoreStrategy, player4PreventerStrategy, player4RandomStrategy, player4RecklessStrategy,
					  autoSetStrategies,
					  draw, player1Draw, player2Draw, player3Draw, player4Draw, 
					  discard, player1Discard, player2Discard, player3Discard, player4Discard,
					  phaseOut, player1PhaseOut, player2PhaseOut,player3PhaseOut, player4PhaseOut,
					  doTurn, player1DoTurn, player2DoTurn, player3DoTurn,player4DoTurn,
					  hit, player1Hit, player2Hit, player3Hit, player4Hit,
					  setPlayerStrategy, player1, player2, player3, player4,
					  scorePlayers, phaseTracker, displayScore;
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
		
		doAWholeRound = new JMenuItem("Do 10 turns");
		doAWholeRound.addActionListener(this);
		round.add(doAWholeRound);
		
		emptyHands = new JMenuItem("Empty hands.");
		emptyHands.addActionListener(this);
		round.add(emptyHands);
		
		resetPlayer = new JMenuItem("Reset players");
		resetPlayer.addActionListener(this);
		round.add(resetPlayer);
		
		startRound = new JMenuItem("Start Round");
		startRound.addActionListener(this);
		round.add(startRound);
			
		exitRound= new JMenuItem("Exit Round");
		exitRound.addActionListener(unimplementedMenu_Click("This will exit the round."));
		round.add(exitRound);
		
// Scoring Menu
		scoring = new JMenu("Scoring options ");
		menuBar.add(scoring);
		
		scorePlayers = new JMenuItem("Score Players");
		scorePlayers.addActionListener(this);
		scoring.add(scorePlayers);
		
		displayScore = new JMenuItem("Display Score");
		displayScore.addActionListener(this);
		scoring.add(displayScore);
		

		
	//Player Options Menu
		playerOptions = new JMenu("Player Options");
		menuBar.add(playerOptions);
		
		draw = new JMenu("Draw");
		draw.addActionListener(unimplementedMenu_Click("This will make the player draw a card."));
		player1Draw = new JMenuItem("Master Chief Draw");
		player1Draw.addActionListener(this);
		player2Draw = new JMenuItem("Sgt.Johnson Draw");
		player2Draw.addActionListener(this);
		player3Draw = new JMenuItem("Arbiter Draw");
		player3Draw.addActionListener(this);
		player4Draw = new JMenuItem("Cortona Draw");
		player4Draw.addActionListener(this);
		draw.add(player1Draw);
		draw.add(player2Draw);
		draw.add(player3Draw);
		draw.add(player4Draw);
		playerOptions.add(draw);
		
		
		phaseOut = new JMenu("Phase Out");
		phaseOut.addActionListener(unimplementedMenu_Click("This will make the player phase out when he has a phase completed."));
		player1PhaseOut = new JMenuItem("Master Chief phase out");
		player1PhaseOut.addActionListener(this);
		player2PhaseOut = new JMenuItem("Sgt. Johnson phase out");
		player2PhaseOut.addActionListener(this);
		player3PhaseOut = new JMenuItem("Arbiter phase out");
		player3PhaseOut.addActionListener(this);
		player4PhaseOut = new JMenuItem("Cortona phase out");
		player4PhaseOut.addActionListener(this);
		phaseOut.add(player1PhaseOut);
		phaseOut.add(player2PhaseOut);
		phaseOut.add(player3PhaseOut);
		phaseOut.add(player4PhaseOut);
		playerOptions.add(phaseOut);

		
		hit = new JMenu("Hit");
		hit.addActionListener(unimplementedMenu_Click("This will make the player hit a collection someone already played."));
		player1Hit = new JMenuItem("Master Chief Hit");
		player1Hit.addActionListener(this);
		player2Hit = new JMenuItem("Sgt. Johnson Hit");
		player2Hit.addActionListener(this);
		player3Hit = new JMenuItem("Arbiter Hit");
		player3Hit.addActionListener(this);
		player4Hit = new JMenuItem("Cortona Hit");
		player4Hit.addActionListener(this);
		hit.add(player1Hit);
		hit.add(player2Hit);
		hit.add(player3Hit);
		hit.add(player4Hit);
		playerOptions.add(hit);
		playerOptions.add(hit);

		
		discard = new JMenu("Discard");
 		discard.addActionListener(unimplementedMenu_Click("This will make the player discard a card."));
		player1Discard = new JMenuItem("Master Chief Discard");
		player1Discard.addActionListener(this);
		player2Discard = new JMenuItem("Sgt. Johnson Discard");
		player2Discard.addActionListener(this);
		player3Discard = new JMenuItem("Arbiter Discard");
		player3Discard.addActionListener(this);
		player4Discard = new JMenuItem("Cortona Discard");
		player4Discard.addActionListener(this);
		
		discard.add(player1Discard);
		discard.add(player2Discard);
		discard.add(player3Discard);
		discard.add(player4Discard);
		playerOptions.add(discard);
		
		doTurn = new JMenu("Do Turn");
		doTurn.addActionListener(unimplementedMenu_Click("This will do a players turn"));
		player1DoTurn = new JMenuItem("Master Chief Do Turn");
		player1DoTurn.addActionListener(this);
		player2DoTurn = new JMenuItem("Sgt. Johnson Do Turn");
		player2DoTurn.addActionListener(this);
		player3DoTurn = new JMenuItem("Arbiter Do Turn");
		player3DoTurn.addActionListener(this);
		player4DoTurn = new JMenuItem("Cortona Do Turn");
		player4DoTurn.addActionListener(this);
		
		doTurn.add(player1DoTurn);
		doTurn.add(player2DoTurn);
		doTurn.add(player3DoTurn);
		doTurn.add(player4DoTurn);
		playerOptions.add(doTurn);

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
		player1RecklessStrategy = new JMenuItem("Reckless Strategy");
		player1RecklessStrategy.addActionListener(this);
		player1.add(player1RandomStrategy);
		player1.add(player1PreventerStrategy);
		player1.add(player1LowestScoreStrategy);
		player1.add(player1RecklessStrategy);
		setPlayerStrategy.add(player1);
		player2 = new JMenu("Johnson");
		//player2.addActionListener(unimplementedMenu_Click("This allows you to set player 1's strategy"));
		player2RandomStrategy = new JMenuItem("Random Strategy");
		player2RandomStrategy.addActionListener(this);
		player2PreventerStrategy = new JMenuItem("Preventer Strategy");
		player2PreventerStrategy.addActionListener(this);
		player2LowestScoreStrategy = new JMenuItem("Lowest Score Strategy");
		player2LowestScoreStrategy.addActionListener(this);
		player2RecklessStrategy = new JMenuItem("Reckless Strategy");
		player2RecklessStrategy.addActionListener(this);
		player2.add(player2RandomStrategy);
		player2.add(player2PreventerStrategy);
		player2.add(player2LowestScoreStrategy);
		player2.add(player2RecklessStrategy);
		setPlayerStrategy.add(player2);
		player3 = new JMenu("Arbiter");
	//	player3.addActionListener(unimplementedMenu_Click("This allows you to set player 1's strategy"));
		player3RandomStrategy = new JMenuItem("Random Strategy");
		player3RandomStrategy.addActionListener(this);
		player3PreventerStrategy = new JMenuItem("Preventer Strategy");
		player3PreventerStrategy.addActionListener(this);
		player3LowestScoreStrategy = new JMenuItem("Lowest Score Strategy");
		player3LowestScoreStrategy.addActionListener(this);
		player3RecklessStrategy = new JMenuItem("Reckless Strategy");
		player3RecklessStrategy.addActionListener(this);
		player3.add(player3RandomStrategy);
		player3.add(player3PreventerStrategy);
		player3.add(player3LowestScoreStrategy);
		player3.add(player3RecklessStrategy);
		setPlayerStrategy.add(player3);
		player4 = new JMenu("Cortona");
		//player4.addActionListener(unimplementedMenu_Click("This allows you to set player 1's strategy"));
		player4RandomStrategy = new JMenuItem("Random Strategy");
		player4RandomStrategy.addActionListener(this);
		player4PreventerStrategy = new JMenuItem("Preventer Strategy");
		player4PreventerStrategy.addActionListener(this);
		player4LowestScoreStrategy = new JMenuItem("Lowest Score Strategy");
		player4LowestScoreStrategy.addActionListener(this);
		player4RecklessStrategy = new JMenuItem("Reckless Strategy");
		player4RecklessStrategy.addActionListener(this);
		player4.add(player4RandomStrategy);
		player4.add(player4PreventerStrategy);
		player4.add(player4LowestScoreStrategy);
		player4.add(player4RecklessStrategy);
		setPlayerStrategy.add(player4);
		
		autoSetStrategies = new JMenuItem("Auto set strategies");
		autoSetStrategies.addActionListener(this);
		setPlayerStrategy.add(autoSetStrategies);
	
		exitMenu = new JMenu("Exit game");
		exitMenu.addActionListener(this);
		menuBar.add(exitMenu);
	
		frame.setJMenuBar(menuBar);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

		

	
	public void updatePlayerArea(JPanel playerArea, JLabel handSize, JLabel img, JLabel phasedOutCards,  JLabel phasedOut){
		playerArea.removeAll();
		playerArea.add(handSize);
		playerArea.add(img);
		if(phasedOutCards != null){
			playerArea.add(phasedOutCards);
		}
		playerArea.add(phasedOut);
		playerArea.validate();
	}
	public void updateCenterArea(JPanel centerPanel, JLabel drawPile, JLabel discardPile){
		centerPanel.removeAll();
		centerPanel.add(drawPile);
		centerPanel.add(discardPile);
		centerPanel.validate();
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
			
			JLabel topHand = new JLabel(("Number of Cards in hand \n" +controller.playerList.get(0).getHandSize()), JLabel.LEFT);
			JLabel topPhase = new JLabel("Phase Number \n " + controller.playerList.get(0).getPhaseNumber(), JLabel.RIGHT);
			JLabel rightHand = new JLabel(("Number of Cards in hand \n" +controller.playerList.get(0).getHandSize()));
			JLabel rightPhase = new JLabel("Phase Number \n " + controller.playerList.get(1).getPhaseNumber(), JLabel.RIGHT);
			JLabel bottomHand = new JLabel("Number of Cards in hand \n" +controller.playerList.get(0).getHandSize(), JLabel.LEFT);
			JLabel bottomPhase = new JLabel("Phase Number \n " + controller.playerList.get(2).getPhaseNumber(), JLabel.RIGHT);
			JLabel leftHand = new JLabel("Number of Cards in hand \n" +controller.playerList.get(0).getHandSize());
			JLabel leftPhase = new JLabel("Phase Number \n " + controller.playerList.get(3).getPhaseNumber(), JLabel.RIGHT);
			JLabel drawPile = new JLabel("The draw Pile", JLabel.CENTER);
			drawPile.setBorder(BorderFactory.createLineBorder(Color.black));
			JLabel topDiscardPile= new JLabel(controller.getTopDiscard().toString(), JLabel.CENTER);
			topDiscardPile.setBorder(BorderFactory.createLineBorder(Color.black));
			
			
			updateCenterArea(centerPanel, drawPile, topDiscardPile);
			updatePlayerArea(topPanel, topHand , chiefImg, null, topPhase);
			updatePlayerArea(rightPanel, rightHand, johnsonImg, null, rightPhase);
			updatePlayerArea(bottomPanel, bottomHand, arbiterImg, null, bottomPhase);
			updatePlayerArea(leftPanel, leftHand, cortonaImg, null, leftPhase); 
	/*
	 * Set Player strategies
	 */
		} else if (event.getSource() == player1RandomStrategy ){
			controller.setStrategy(0, strategyType.randomPlayer);
		} else if (event.getSource() ==  player2RandomStrategy ){
			controller.setStrategy(1, strategyType.randomPlayer);
		} else if (event.getSource() == player3RandomStrategy){
			controller.setStrategy(2, strategyType.randomPlayer);
		}else if (event.getSource() == player4RandomStrategy){
			controller.setStrategy(3, strategyType.randomPlayer);
		}else if (event.getSource() == player1PreventerStrategy){
			controller.setStrategy(0, strategyType.preventer);
		} else if (event.getSource() == player2PreventerStrategy){
			controller.setStrategy(1, strategyType.preventer);
		} else if (event.getSource() == player3PreventerStrategy){
			controller.setStrategy(2, strategyType.preventer);
		} else if (event.getSource() == player4PreventerStrategy){
			controller.setStrategy(3, strategyType.preventer);
		} else if (event.getSource() == player1LowestScoreStrategy){
			controller.setStrategy(0, strategyType.lowestScore);
		} else if (event.getSource() == player2LowestScoreStrategy){
			controller.setStrategy(1, strategyType.lowestScore);
		} else if (event.getSource() == player3LowestScoreStrategy){
			controller.setStrategy(2, strategyType.lowestScore);
		} else if (event.getSource() == player4LowestScoreStrategy){
			controller.setStrategy(3, strategyType.lowestScore);
		} else if (event.getSource() == player1RecklessStrategy){
			controller.setStrategy(0, strategyType.recklessPlayer);
		} else if (event.getSource() == player2RecklessStrategy){
			controller.setStrategy(1, strategyType.recklessPlayer);
		} else if (event.getSource() == player3RecklessStrategy){
			controller.setStrategy(2, strategyType.recklessPlayer);
		} else if (event.getSource() == player4RecklessStrategy){
			controller.setStrategy(3, strategyType.recklessPlayer);
		}else if (event.getSource() == autoSetStrategies){
			controller.setStrategy(0,strategyType.randomPlayer);
			controller.setStrategy(1, strategyType.recklessPlayer);
			controller.setStrategy(2, strategyType.preventer);
			controller.setStrategy(3, strategyType.lowestScore);
		} 
		/*
		 * Draw card options
		 */
		
		else if(event.getSource() == player1Draw){
			System.out.println("Player1 draw");
			controller.drawCard(0);
			JLabel topHand = new JLabel(("Number of Cards in hand \n" +controller.playerList.get(0).getHandSize()), JLabel.LEFT);
			JLabel topPhase = new JLabel("Phase Number \n " + controller.playerList.get(0).getPhaseNumber(), JLabel.RIGHT);
			JLabel topPhasedOutStuff = new JLabel(controller.playerList.get(0).phasedOutStuffToString());
			JLabel drawPile = new JLabel("The draw Pile", JLabel.CENTER);
			drawPile.setBorder(BorderFactory.createLineBorder(Color.black));
			if(controller.getTopDiscard() == null){
				JLabel topDiscardPile = new JLabel("The discard pile is empty", JLabel.CENTER);
				topDiscardPile.setBorder(BorderFactory.createLineBorder(Color.black));
				updateCenterArea(centerPanel, drawPile, topDiscardPile);
			}else{
				JLabel topDiscardPile= new JLabel(controller.getTopDiscard().toString(), JLabel.CENTER);
				topDiscardPile.setBorder(BorderFactory.createLineBorder(Color.black));
				updateCenterArea(centerPanel, drawPile, topDiscardPile);
			}
			
			updatePlayerArea(topPanel, topHand , chiefImg, topPhasedOutStuff, topPhase);
		} else if(event.getSource() == player2Draw){
			System.out.println("Player2 draw");
			controller.drawCard(1);
			JLabel rightHand = new JLabel(("Number of Cards in hand \n" +controller.playerList.get(1).getHandSize()));
			JLabel rightPhase = new JLabel("Phase Number \n " + controller.playerList.get(1).getPhaseNumber(), JLabel.RIGHT);
			JLabel drawPile = new JLabel("The draw Pile", JLabel.CENTER);
			drawPile.setBorder(BorderFactory.createLineBorder(Color.black));
			if(controller.getTopDiscard() == null){
				JLabel topDiscardPile = new JLabel("The discard pile is empty", JLabel.CENTER);
				topDiscardPile.setBorder(BorderFactory.createLineBorder(Color.black));
				updateCenterArea(centerPanel, drawPile, topDiscardPile);
			}else{
				JLabel topDiscardPile= new JLabel(controller.getTopDiscard().toString(), JLabel.CENTER);
				topDiscardPile.setBorder(BorderFactory.createLineBorder(Color.black));
				updateCenterArea(centerPanel, drawPile, topDiscardPile);
			}
			updatePlayerArea(rightPanel, rightHand , johnsonImg, null, rightPhase);
		} else if(event.getSource() == player3Draw){
			System.out.println("Player3 draw");
			controller.drawCard(2);
			JLabel bottomHand = new JLabel("Number of Cards in hand \n" +controller.playerList.get(2).getHandSize(), JLabel.LEFT);
			JLabel bottomPhase = new JLabel("Phase Number \n " + controller.playerList.get(2).getPhaseNumber(), JLabel.RIGHT);
			JLabel drawPile = new JLabel("The draw Pile", JLabel.CENTER);
			drawPile.setBorder(BorderFactory.createLineBorder(Color.black));
			if(controller.getTopDiscard() == null){
				JLabel topDiscardPile = new JLabel("The discard pile is empty", JLabel.CENTER);
				topDiscardPile.setBorder(BorderFactory.createLineBorder(Color.black));
				updateCenterArea(centerPanel, drawPile, topDiscardPile);
			}else{
				JLabel topDiscardPile= new JLabel(controller.getTopDiscard().toString(), JLabel.CENTER);
				topDiscardPile.setBorder(BorderFactory.createLineBorder(Color.black));
				updateCenterArea(centerPanel, drawPile, topDiscardPile);
			}
			updatePlayerArea(bottomPanel, bottomHand , arbiterImg, null, bottomPhase);

			
		} else if(event.getSource() == player4Draw){
			System.out.println("Player4 draw");
			controller.drawCard(3);
			JLabel leftHand = new JLabel("Number of Cards in hand \n" +controller.playerList.get(3).getHandSize());
			JLabel leftPhase = new JLabel("Phase Number \n " + controller.playerList.get(3).getPhaseNumber(), JLabel.RIGHT);
			JLabel drawPile = new JLabel("The draw Pile", JLabel.CENTER);
			drawPile.setBorder(BorderFactory.createLineBorder(Color.black));
			if(controller.getTopDiscard() == null){
				JLabel topDiscardPile = new JLabel("The discard pile is empty", JLabel.CENTER);
				topDiscardPile.setBorder(BorderFactory.createLineBorder(Color.black));
				updateCenterArea(centerPanel, drawPile, topDiscardPile);
			}else{
				JLabel topDiscardPile= new JLabel(controller.getTopDiscard().toString(), JLabel.CENTER);
				topDiscardPile.setBorder(BorderFactory.createLineBorder(Color.black));
				updateCenterArea(centerPanel, drawPile, topDiscardPile);
			}
			updatePlayerArea(leftPanel, leftHand , cortonaImg, null, leftPhase);
		}
		/*
		 * Discard options
		 */
		
		else if (event.getSource() == player1Discard){
			System.out.println("Player 1 Discard");
			controller.discard(0);
			JLabel topHand = new JLabel(("Number of Cards in hand \n" +controller.playerList.get(0).getHandSize()), JLabel.LEFT);
			JLabel topPhase = new JLabel("Phase Number \n " + controller.playerList.get(0).getPhaseNumber(), JLabel.RIGHT);
			JLabel topPhasedOutStuff = new JLabel(controller.playerList.get(0).phasedOutStuffToString());
			JLabel drawPile = new JLabel("The draw Pile", JLabel.CENTER);
			drawPile.setBorder(BorderFactory.createLineBorder(Color.black));
			if(controller.getTopDiscard() == null){
				JLabel topDiscardPile = new JLabel("The discard pile is empty", JLabel.CENTER);
				topDiscardPile.setBorder(BorderFactory.createLineBorder(Color.black));
				updateCenterArea(centerPanel, drawPile, topDiscardPile);
			}else{
				JLabel topDiscardPile= new JLabel(controller.getTopDiscard().toString(), JLabel.CENTER);
				topDiscardPile.setBorder(BorderFactory.createLineBorder(Color.black));
				updateCenterArea(centerPanel, drawPile, topDiscardPile);
			}
			updatePlayerArea(topPanel, topHand , chiefImg, topPhasedOutStuff, topPhase);
		} else if (event.getSource() == player2Discard){
			System.out.println("Player 2 Discard");
			controller.discard(1);
			JLabel rightHand = new JLabel(("Number of Cards in hand \n" +controller.playerList.get(1).getHandSize()), JLabel.LEFT);
			JLabel rightPhase = new JLabel("Phase Number \n " + controller.playerList.get(1).getPhaseNumber(), JLabel.RIGHT);
			JLabel rightPhasedOutStuff = new JLabel(controller.playerList.get(1).phasedOutStuffToString());
			JLabel drawPile = new JLabel("The draw Pile", JLabel.CENTER);
			drawPile.setBorder(BorderFactory.createLineBorder(Color.black));
			if(controller.getTopDiscard() == null){
				JLabel topDiscardPile = new JLabel("The discard pile is empty", JLabel.CENTER);
				topDiscardPile.setBorder(BorderFactory.createLineBorder(Color.black));
				updateCenterArea(centerPanel, drawPile, topDiscardPile);
			}else{
				JLabel topDiscardPile= new JLabel(controller.getTopDiscard().toString(), JLabel.CENTER);
				topDiscardPile.setBorder(BorderFactory.createLineBorder(Color.black));
				updateCenterArea(centerPanel, drawPile, topDiscardPile);
			}
			updatePlayerArea(rightPanel, rightHand , johnsonImg, rightPhasedOutStuff, rightPhase);
		} else if (event.getSource() == player3Discard){
			System.out.println("Player 3 Discard");
			controller.discard(2);
			JLabel bottomHand = new JLabel(("Number of Cards in hand \n" +controller.playerList.get(2).getHandSize()), JLabel.LEFT);
			JLabel bottomPhase = new JLabel("Phase Number \n " + controller.playerList.get(2).getPhaseNumber(), JLabel.RIGHT);
			JLabel bottomPhasedOutStuff = new JLabel(controller.playerList.get(2).phasedOutStuffToString());
			JLabel drawPile = new JLabel("The draw Pile", JLabel.CENTER);
			drawPile.setBorder(BorderFactory.createLineBorder(Color.black));
			if(controller.getTopDiscard() == null){
				JLabel topDiscardPile = new JLabel("The discard pile is empty", JLabel.CENTER);
				topDiscardPile.setBorder(BorderFactory.createLineBorder(Color.black));
				updateCenterArea(centerPanel, drawPile, topDiscardPile);
			}else{
				JLabel topDiscardPile= new JLabel(controller.getTopDiscard().toString(), JLabel.CENTER);
				topDiscardPile.setBorder(BorderFactory.createLineBorder(Color.black));
				updateCenterArea(centerPanel, drawPile, topDiscardPile);
			}
			updatePlayerArea(bottomPanel, bottomHand , arbiterImg, bottomPhasedOutStuff, bottomPhase);
		} else if (event.getSource() == player4Discard){
			System.out.println("Player 4 Discard");
			controller.discard(3);
			JLabel leftHand = new JLabel(("Number of Cards in hand \n" +controller.playerList.get(3).getHandSize()), JLabel.LEFT);
			JLabel leftPhase = new JLabel("Phase Number \n " + controller.playerList.get(3).getPhaseNumber(), JLabel.RIGHT);
			JLabel leftPhasedOutStuff = new JLabel(controller.playerList.get(3).phasedOutStuffToString());
			JLabel drawPile = new JLabel("The draw Pile", JLabel.CENTER);
			drawPile.setBorder(BorderFactory.createLineBorder(Color.black));
			if(controller.getTopDiscard() == null){
				JLabel topDiscardPile = new JLabel("The discard pile is empty", JLabel.CENTER);
				topDiscardPile.setBorder(BorderFactory.createLineBorder(Color.black));
				updateCenterArea(centerPanel, drawPile, topDiscardPile);
			}else{
				JLabel topDiscardPile= new JLabel(controller.getTopDiscard().toString(), JLabel.CENTER);
				topDiscardPile.setBorder(BorderFactory.createLineBorder(Color.black));
				updateCenterArea(centerPanel, drawPile, topDiscardPile);
			}
			updatePlayerArea(leftPanel, leftHand , cortonaImg, leftPhasedOutStuff, leftPhase);
		} 
		
		/*
		 * Phase out options
		 */
		else if (event.getSource() == player1PhaseOut){
				controller.phaseOut(0);
				JLabel topHand = new JLabel(("Number of Cards in hand \n" +controller.playerList.get(0).getHandSize()), JLabel.LEFT);
				JLabel topPhase = new JLabel("Phase Number \n " + controller.playerList.get(0).getPhaseNumber(), JLabel.RIGHT);
				JLabel topPhasedOutStuff = new JLabel(controller.playerList.get(0).phasedOutStuffToString());
//				JOptionPane.showMessageDialog(null, "Chief phased out "+ controller.playerList.get(0).phasedOutStuffToString());
				updatePlayerArea(topPanel, topHand , chiefImg, topPhasedOutStuff, topPhase);
		} else if (event.getSource() == player2PhaseOut){
			controller.phaseOut(1);
			JLabel rightHand = new JLabel(("Number of Cards in hand \n" +controller.playerList.get(1).getHandSize()), JLabel.LEFT);
			JLabel rightPhase = new JLabel("Phase Number \n " + controller.playerList.get(1).getPhaseNumber(), JLabel.RIGHT);
			JLabel rightPhasedOutStuff = new JLabel(controller.playerList.get(1).phasedOutStuffToString());
//			JOptionPane.showMessageDialog(null, "Johnson phased out");
			updatePlayerArea(rightPanel, rightHand , johnsonImg, rightPhasedOutStuff, rightPhase);
		} else if (event.getSource() == player3PhaseOut){
			System.out.println("Arbiter phase out");
			controller.phaseOut(2);
			JLabel bottomHand = new JLabel(("Number of Cards in hand \n" +controller.playerList.get(2).getHandSize()), JLabel.LEFT);
			JLabel bottomPhase = new JLabel("Phase Number \n " + controller.playerList.get(2).getPhaseNumber(), JLabel.RIGHT);
			JLabel bottomPhasedOutStuff = new JLabel(controller.playerList.get(2).phasedOutStuffToString());
//			JOptionPane.showMessageDialog(null, "Arbiter phased out");
			updatePlayerArea(bottomPanel, bottomHand , arbiterImg, bottomPhasedOutStuff, bottomPhase);
		
		} else if (event.getSource() == player4PhaseOut){
			System.out.println("Corotona phase out");
			controller.phaseOut(3);
			JLabel leftHand = new JLabel(("Number of Cards in hand \n" +controller.playerList.get(3).getHandSize()), JLabel.LEFT);
			JLabel leftPhase = new JLabel("Phase Number \n " + controller.playerList.get(3).getPhaseNumber(), JLabel.RIGHT);
			JLabel leftPhasedOutStuff = new JLabel(controller.playerList.get(3).phasedOutStuffToString());
//			JOptionPane.showMessageDialog(null, "Cortona phased out");
			updatePlayerArea(leftPanel, leftHand , cortonaImg, leftPhasedOutStuff, leftPhase);
		} 
		
		/*
		 * Do turn options
		 */
		
		else if (event.getSource() == player1DoTurn){
			controller.doTurn(0);
			System.out.println(controller.playerList.get(0).hand);
			JLabel topHand = new JLabel(("Number of Cards in hand \n" +controller.playerList.get(0).getHandSize()), JLabel.LEFT);
			JLabel topPhase = new JLabel("Phase Number \n " + controller.playerList.get(0).getPhaseNumber(), JLabel.RIGHT);
			JLabel topPhasedOutStuff = new JLabel(controller.playerList.get(0).phasedOutStuffToString());
			JLabel drawPile = new JLabel("The draw Pile", JLabel.CENTER);
			drawPile.setBorder(BorderFactory.createLineBorder(Color.black));
			if(controller.getTopDiscard() == null){
				JLabel topDiscardPile = new JLabel("The discard pile is empty", JLabel.CENTER);
				topDiscardPile.setBorder(BorderFactory.createLineBorder(Color.black));
				updateCenterArea(centerPanel, drawPile, topDiscardPile);
			}else{
				JLabel topDiscardPile= new JLabel(controller.getTopDiscard().toString(), JLabel.CENTER);
				topDiscardPile.setBorder(BorderFactory.createLineBorder(Color.black));
				updateCenterArea(centerPanel, drawPile, topDiscardPile);
			}
			if(controller.playerList.get(0).getPhasedOut() & controller.playerList.get(0).displayPhasedOut){
				JOptionPane.showMessageDialog(null, "Chief phased out "+ controller.playerList.get(0).phasedOutStuffToString());
				controller.playerList.get(2).displayPhasedOut = false;
			}
			updatePlayerArea(topPanel, topHand , chiefImg, topPhasedOutStuff, topPhase);
			if(controller.roundDone){
				JOptionPane.showMessageDialog(null, "Chief emptied his hand the round is over");
				controller.scoreRound();
			}
		} else if (event.getSource() == player2DoTurn){
			controller.doTurn(1);
			System.out.println(controller.playerList.get(1).hand);
			JLabel rightHand = new JLabel(("Number of Cards in hand \n" +controller.playerList.get(1).getHandSize()), JLabel.LEFT);
			JLabel rightPhase = new JLabel("Phase Number \n " + controller.playerList.get(1).getPhaseNumber(), JLabel.RIGHT);
			JLabel rightPhasedOutStuff = new JLabel(controller.playerList.get(1).phasedOutStuffToString());
			JLabel drawPile = new JLabel("The draw Pile", JLabel.CENTER);
			drawPile.setBorder(BorderFactory.createLineBorder(Color.black));
			if(controller.getTopDiscard() == null){
				JLabel topDiscardPile = new JLabel("The discard pile is empty", JLabel.CENTER);
				topDiscardPile.setBorder(BorderFactory.createLineBorder(Color.black));
				updateCenterArea(centerPanel, drawPile, topDiscardPile);
			}else{
				JLabel topDiscardPile= new JLabel(controller.getTopDiscard().toString(), JLabel.CENTER);
				topDiscardPile.setBorder(BorderFactory.createLineBorder(Color.black));
				updateCenterArea(centerPanel, drawPile, topDiscardPile);
			}
			if(controller.playerList.get(1).getPhasedOut()& controller.playerList.get(1).displayPhasedOut){
				JOptionPane.showMessageDialog(null, "Josnson phased out "+ controller.playerList.get(1).phasedOutStuffToString());
				controller.playerList.get(1).displayPhasedOut = false;
			}
			updatePlayerArea(rightPanel, rightHand , johnsonImg, rightPhasedOutStuff, rightPhase);
			if(controller.roundDone){
				JOptionPane.showMessageDialog(null, "Sgt. Johnson emptied his hand the round is over");
				controller.scoreRound();
			}
		} else if (event.getSource() == player3DoTurn){
			controller.doTurn(2);
			System.out.println(controller.playerList.get(2).hand);
			JLabel bottomHand = new JLabel(("Number of Cards in hand \n" +controller.playerList.get(2).getHandSize()), JLabel.LEFT);
			JLabel bottomPhase = new JLabel("Phase Number \n " + controller.playerList.get(2).getPhaseNumber(), JLabel.RIGHT);
			JLabel bottomPhasedOutStuff = new JLabel(controller.playerList.get(2).phasedOutStuffToString());
			JLabel drawPile = new JLabel("The draw Pile", JLabel.CENTER);
			drawPile.setBorder(BorderFactory.createLineBorder(Color.black));
			if(controller.getTopDiscard() == null){
				JLabel topDiscardPile = new JLabel("The discard pile is empty", JLabel.CENTER);
				topDiscardPile.setBorder(BorderFactory.createLineBorder(Color.black));
				updateCenterArea(centerPanel, drawPile, topDiscardPile);
			}else{
				JLabel topDiscardPile= new JLabel(controller.getTopDiscard().toString(), JLabel.CENTER);
				topDiscardPile.setBorder(BorderFactory.createLineBorder(Color.black));
				updateCenterArea(centerPanel, drawPile, topDiscardPile);
			}
			if(controller.playerList.get(2).getPhasedOut() & controller.playerList.get(2).displayPhasedOut){
				JOptionPane.showMessageDialog(null, "arbiter phased out "+ controller.playerList.get(2).phasedOutStuffToString());
				controller.playerList.get(2).displayPhasedOut = false;
			}
			updatePlayerArea(bottomPanel, bottomHand , arbiterImg, bottomPhasedOutStuff, bottomPhase);
			if(controller.roundDone){
				JOptionPane.showMessageDialog(null, "The Arbiter emptied his hand the round is over");
				controller.scoreRound();
			}
		} else if (event.getSource() == player4DoTurn){
			controller.doTurn(3);
			System.out.println(controller.playerList.get(3).hand);
			JLabel leftHand = new JLabel(("Number of Cards in hand \n" +controller.playerList.get(3).getHandSize()), JLabel.LEFT);
			JLabel leftPhase = new JLabel("Phase Number \n " + controller.playerList.get(3).getPhaseNumber(), JLabel.RIGHT);
			JLabel leftPhasedOutStuff = new JLabel(controller.playerList.get(3).phasedOutStuffToString());
			JLabel drawPile = new JLabel("The draw Pile", JLabel.CENTER);
			drawPile.setBorder(BorderFactory.createLineBorder(Color.black));
			if(controller.getTopDiscard() == null){
				JLabel topDiscardPile = new JLabel("The discard pile is empty", JLabel.CENTER);
				topDiscardPile.setBorder(BorderFactory.createLineBorder(Color.black));
				updateCenterArea(centerPanel, drawPile, topDiscardPile);
			}else{
				JLabel topDiscardPile= new JLabel(controller.getTopDiscard().toString(), JLabel.CENTER);
				topDiscardPile.setBorder(BorderFactory.createLineBorder(Color.black));
				updateCenterArea(centerPanel, drawPile, topDiscardPile);
			}
			if(controller.playerList.get(3).getPhasedOut()& controller.playerList.get(3).displayPhasedOut){
				JOptionPane.showMessageDialog(null, "cortona phased out "+ controller.playerList.get(3).phasedOutStuffToString());
				controller.playerList.get(3).displayPhasedOut = false;
			}
			updatePlayerArea(leftPanel, leftHand , cortonaImg, leftPhasedOutStuff, leftPhase);
			if(controller.roundDone){
				JOptionPane.showMessageDialog(null, "Cortona emptied her hand the round is over");
				controller.scoreRound();
			}
		} 
		
		/*
		 * Hit options
		 */
		
		else if (event.getSource() == player1Hit){
			controller.hit(0);
			JLabel topHand = new JLabel(("Number of Cards in hand \n" +controller.playerList.get(0).getHandSize()), JLabel.LEFT);
			JLabel topPhase = new JLabel("Phase Number \n " + controller.playerList.get(0).getPhaseNumber(), JLabel.RIGHT);
			JLabel topPhasedOutStuff = new JLabel(controller.playerList.get(0).phasedOutStuffToString());
			JLabel drawPile = new JLabel("The draw Pile", JLabel.CENTER);
			drawPile.setBorder(BorderFactory.createLineBorder(Color.black));
			if(controller.getTopDiscard() == null){
				JLabel topDiscardPile = new JLabel("The discard pile is empty", JLabel.CENTER);
				topDiscardPile.setBorder(BorderFactory.createLineBorder(Color.black));
				updateCenterArea(centerPanel, drawPile, topDiscardPile);
			}else{
				JLabel topDiscardPile= new JLabel(controller.getTopDiscard().toString(), JLabel.CENTER);
				topDiscardPile.setBorder(BorderFactory.createLineBorder(Color.black));
				updateCenterArea(centerPanel, drawPile, topDiscardPile);
			}
			
			updatePlayerArea(topPanel, topHand , chiefImg, topPhasedOutStuff, topPhase);
		} else if (event.getSource() == player2Hit){
			controller.hit(1);
			JLabel rightHand = new JLabel(("Number of Cards in hand \n" +controller.playerList.get(1).getHandSize()));
			JLabel rightPhase = new JLabel("Phase Number \n " + controller.playerList.get(1).getPhaseNumber(), JLabel.RIGHT);
			JLabel rightPhasedOutStuff = new JLabel(controller.playerList.get(1).phasedOutStuffToString());
			JLabel drawPile = new JLabel("The draw Pile", JLabel.CENTER);
			drawPile.setBorder(BorderFactory.createLineBorder(Color.black));
			if(controller.getTopDiscard() == null){
				JLabel topDiscardPile = new JLabel("The discard pile is empty", JLabel.CENTER);
				topDiscardPile.setBorder(BorderFactory.createLineBorder(Color.black));
				updateCenterArea(centerPanel, drawPile, topDiscardPile);
			}else{
				JLabel topDiscardPile= new JLabel(controller.getTopDiscard().toString(), JLabel.CENTER);
				topDiscardPile.setBorder(BorderFactory.createLineBorder(Color.black));
				updateCenterArea(centerPanel, drawPile, topDiscardPile);
			}
			updatePlayerArea(rightPanel, rightHand , johnsonImg, rightPhasedOutStuff, rightPhase);
		} else if (event.getSource() == player3Hit){
			controller.hit(2);
			JLabel bottomHand = new JLabel("Number of Cards in hand \n" +controller.playerList.get(2).getHandSize(), JLabel.LEFT);
			JLabel bottomPhase = new JLabel("Phase Number \n " + controller.playerList.get(2).getPhaseNumber(), JLabel.RIGHT);
			JLabel bottomPhasedOutStuff = new JLabel(controller.playerList.get(2).phasedOutStuffToString());
			JLabel drawPile = new JLabel("The draw Pile", JLabel.CENTER);
			drawPile.setBorder(BorderFactory.createLineBorder(Color.black));
			if(controller.getTopDiscard() == null){
				JLabel topDiscardPile = new JLabel("The discard pile is empty", JLabel.CENTER);
				topDiscardPile.setBorder(BorderFactory.createLineBorder(Color.black));
				updateCenterArea(centerPanel, drawPile, topDiscardPile);
			}else{
				JLabel topDiscardPile= new JLabel(controller.getTopDiscard().toString(), JLabel.CENTER);
				topDiscardPile.setBorder(BorderFactory.createLineBorder(Color.black));
				updateCenterArea(centerPanel, drawPile, topDiscardPile);
			}
			updatePlayerArea(bottomPanel, bottomHand , arbiterImg, bottomPhasedOutStuff, bottomPhase);
		} else if (event.getSource() == player4Hit){
			controller.hit(3);
			JLabel leftHand = new JLabel("Number of Cards in hand \n" +controller.playerList.get(3).getHandSize());
			JLabel leftPhase = new JLabel("Phase Number \n " + controller.playerList.get(3).getPhaseNumber(), JLabel.RIGHT);
			JLabel leftPhasedOutStuff = new JLabel(controller.playerList.get(3).phasedOutStuffToString());
			JLabel drawPile = new JLabel("The draw Pile", JLabel.CENTER);
			drawPile.setBorder(BorderFactory.createLineBorder(Color.black));
			if(controller.getTopDiscard() == null){
				JLabel topDiscardPile = new JLabel("The discard pile is empty", JLabel.CENTER);
				topDiscardPile.setBorder(BorderFactory.createLineBorder(Color.black));
				updateCenterArea(centerPanel, drawPile, topDiscardPile);
			}else{
				JLabel topDiscardPile= new JLabel(controller.getTopDiscard().toString(), JLabel.CENTER);
				topDiscardPile.setBorder(BorderFactory.createLineBorder(Color.black));
				updateCenterArea(centerPanel, drawPile, topDiscardPile);
			}
			updatePlayerArea(leftPanel, leftHand , cortonaImg, leftPhasedOutStuff, leftPhase);
		} else if (event.getSource() == scorePlayers	){
			controller.scoreRound();
		} else if (event.getSource() == displayScore){
			JOptionPane.showMessageDialog(null, controller.displayScore());
		} else if (event.getSource() == doAWholeRound){
			controller.DoAWholeRound();
			JLabel topHand = new JLabel(("Number of Cards in hand \n" +controller.playerList.get(0).getHandSize()), JLabel.LEFT);
			JLabel topPhase = new JLabel("Phase Number \n " + controller.playerList.get(0).getPhaseNumber(), JLabel.RIGHT);
			JLabel topPhasedOutStuff = new JLabel(controller.playerList.get(0).phasedOutStuffToString());
			JLabel rightHand = new JLabel(("Number of Cards in hand \n" +controller.playerList.get(1).getHandSize()), JLabel.LEFT);
			JLabel rightPhase = new JLabel("Phase Number \n " + controller.playerList.get(1).getPhaseNumber(), JLabel.RIGHT);
			JLabel rightPhasedOutStuff = new JLabel(controller.playerList.get(1).phasedOutStuffToString());
			JLabel bottomHand = new JLabel(("Number of Cards in hand \n" +controller.playerList.get(2).getHandSize()), JLabel.LEFT);
			JLabel bottomPhase = new JLabel("Phase Number \n " + controller.playerList.get(2).getPhaseNumber(), JLabel.RIGHT);
			JLabel bottomPhasedOutStuff = new JLabel(controller.playerList.get(2).phasedOutStuffToString());
			JLabel leftHand = new JLabel(("Number of Cards in hand \n" +controller.playerList.get(3).getHandSize()), JLabel.LEFT);
			JLabel leftPhase = new JLabel("Phase Number \n " + controller.playerList.get(3).getPhaseNumber(), JLabel.RIGHT);
			JLabel leftPhasedOutStuff = new JLabel(controller.playerList.get(3).phasedOutStuffToString());
			JLabel drawPile = new JLabel("The draw Pile", JLabel.CENTER);
			drawPile.setBorder(BorderFactory.createLineBorder(Color.black));
			if(controller.getTopDiscard() == null){
				JLabel topDiscardPile = new JLabel("The discard pile is empty", JLabel.CENTER);
				topDiscardPile.setBorder(BorderFactory.createLineBorder(Color.black));
				updateCenterArea(centerPanel, drawPile, topDiscardPile);
			}else{
				JLabel topDiscardPile= new JLabel(controller.getTopDiscard().toString(), JLabel.CENTER);
				topDiscardPile.setBorder(BorderFactory.createLineBorder(Color.black));
				updateCenterArea(centerPanel, drawPile, topDiscardPile);
			}
			if(controller.playerList.get(0).getPhasedOut() & controller.playerList.get(0).displayPhasedOut){
				JOptionPane.showMessageDialog(null, "Chief phased out "+ controller.playerList.get(0).phasedOutStuffToString());
				controller.playerList.get(2).displayPhasedOut = false;
			}
			updatePlayerArea(topPanel, topHand , chiefImg, topPhasedOutStuff, topPhase);
			if(controller.playerList.get(1).getPhasedOut()& controller.playerList.get(1).displayPhasedOut){
				JOptionPane.showMessageDialog(null, "Josnson phased out "+ controller.playerList.get(1).phasedOutStuffToString());
				controller.playerList.get(1).displayPhasedOut = false;
			}
			updatePlayerArea(rightPanel, rightHand , johnsonImg, rightPhasedOutStuff, rightPhase);
			if(controller.playerList.get(2).getPhasedOut() & controller.playerList.get(2).displayPhasedOut){
				JOptionPane.showMessageDialog(null, "arbiter phased out "+ controller.playerList.get(2).phasedOutStuffToString());
				controller.playerList.get(2).displayPhasedOut = false;
			}
			updatePlayerArea(bottomPanel, bottomHand , arbiterImg, bottomPhasedOutStuff, bottomPhase);
			if(controller.playerList.get(3).getPhasedOut()& controller.playerList.get(3).displayPhasedOut){
				JOptionPane.showMessageDialog(null, "cortona phased out "+ controller.playerList.get(3).phasedOutStuffToString());
				controller.playerList.get(3).displayPhasedOut = false;
			}
			updatePlayerArea(leftPanel, leftHand , cortonaImg, leftPhasedOutStuff, leftPhase);
			if(controller.roundDone){
				JOptionPane.showMessageDialog(null, "Someone emptied their hand the round is over");
				controller.scoreRound();
			}
			
		} else if (event.getSource() == emptyHands){
			controller.emptyHands();
			JLabel topHand = new JLabel(("Number of Cards in hand \n" +controller.playerList.get(0).getHandSize()), JLabel.LEFT);
			JLabel topPhase = new JLabel("Phase Number \n " + controller.playerList.get(0).getPhaseNumber(), JLabel.RIGHT);
			JLabel topPhasedOutStuff = new JLabel(controller.playerList.get(0).phasedOutStuffToString());
			JLabel rightHand = new JLabel(("Number of Cards in hand \n" +controller.playerList.get(1).getHandSize()), JLabel.LEFT);
			JLabel rightPhase = new JLabel("Phase Number \n " + controller.playerList.get(1).getPhaseNumber(), JLabel.RIGHT);
			JLabel rightPhasedOutStuff = new JLabel(controller.playerList.get(1).phasedOutStuffToString());
			JLabel bottomHand = new JLabel(("Number of Cards in hand \n" +controller.playerList.get(2).getHandSize()), JLabel.LEFT);
			JLabel bottomPhase = new JLabel("Phase Number \n " + controller.playerList.get(2).getPhaseNumber(), JLabel.RIGHT);
			JLabel bottomPhasedOutStuff = new JLabel(controller.playerList.get(2).phasedOutStuffToString());
			JLabel leftHand = new JLabel(("Number of Cards in hand \n" +controller.playerList.get(3).getHandSize()), JLabel.LEFT);
			JLabel leftPhase = new JLabel("Phase Number \n " + controller.playerList.get(3).getPhaseNumber(), JLabel.RIGHT);
			JLabel leftPhasedOutStuff = new JLabel(controller.playerList.get(3).phasedOutStuffToString());
			updatePlayerArea(topPanel, topHand , chiefImg, topPhasedOutStuff, topPhase);
			updatePlayerArea(rightPanel, rightHand , johnsonImg, rightPhasedOutStuff, rightPhase);
			updatePlayerArea(bottomPanel, bottomHand , arbiterImg, bottomPhasedOutStuff, bottomPhase);
			updatePlayerArea(leftPanel, leftHand , cortonaImg, leftPhasedOutStuff, leftPhase);
		} else if (event.getSource() == resetPlayer){
			controller.resetPlayer();
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