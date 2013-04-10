package View;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.*;



public class Gui implements ActionListener {
	public Gui() {
		createUI();
	}
	private PlayerArea player1Area = new PlayerArea();
	private PlayerArea player2Area = new PlayerArea();
	private PlayerArea player3Area = new PlayerArea();
	private PlayerArea player4Area = new PlayerArea();
	private JFrame frame;
	private JButton button;
	private JPanel contentPane, leftPanel, rightPanel, topPanel, bottomPanel, centerPanel;
	private JLabel img;
	private JMenuBar menuBar;
	private JMenuItem setupMenuItem, exitMenuItem, phaseSearcherStrategy, preventerStrategy, lowestScoreStrategy, cardCounterStrategy,
					  scorePlayers, phaseTracker, displayScore, exitRound, showOrder, resetOrder, resetDrawPile, draw, playPhase, hit,
					  checkHit, discard, finishTurn;
	private JMenu menu, strategies, round, playerOptions, scoring;
	public void createUI() {
	// Frame
		frame = new JFrame();
		frame.setTitle("Phase 10");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,400);
		
		contentPane = new JPanel(new BorderLayout());

		
	// The Menu Bar
		menuBar = new JMenuBar();
		
		
	//Game Menu
		menu = new JMenu("Game");
		menuBar.add(menu);		
		setupMenuItem = new JMenuItem("Setup Board");
		setupMenuItem.addActionListener(unimplementedMenu_Click("This will display the board. "));
		menu.add(setupMenuItem);
		
		exitMenuItem = new JMenuItem("Exit game");
		exitMenuItem.addActionListener(unimplementedMenu_Click("This will exit the game. "));
		menu.add(exitMenuItem);
		
	//Strategies Menu
		strategies = new JMenu("Strategies");
		menuBar.add(strategies);
		
		phaseSearcherStrategy = new JMenuItem("Phase Searcher Strategy");
		phaseSearcherStrategy.addActionListener(unimplementedMenu_Click("This will implement the phase searcher strategy."));
		strategies.add(phaseSearcherStrategy);
		
		preventerStrategy = new JMenuItem("Preventer Strategy");
		preventerStrategy.addActionListener(unimplementedMenu_Click("This will implement the preventer strategy."));
		strategies.add(preventerStrategy);
		
		lowestScoreStrategy = new JMenuItem("Lowest Score Strategy");
		lowestScoreStrategy.addActionListener(unimplementedMenu_Click("This will implement the lowest score strategy."));
		strategies.add(lowestScoreStrategy);
		
		cardCounterStrategy = new JMenuItem("Card Counter Strategy");
		cardCounterStrategy.addActionListener(unimplementedMenu_Click("This will implement the card counter strategy."));
		strategies.add(cardCounterStrategy);
		
	//round Menu
		round = new JMenu("Round Options");
		menuBar.add(round);
		
		showOrder = new JMenuItem("Show Order");
		showOrder.addActionListener(unimplementedMenu_Click("This will show the player order for this round."));
		round.add(showOrder);
		
		resetOrder = new JMenuItem("Reset Order");
		resetOrder.addActionListener(unimplementedMenu_Click("This will reset the player order."));
		round.add(resetOrder);
		
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
		
	//top
		topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		topPanel.setBackground(Color.BLUE);
		button = new JButton("Set Place Holder");
		
		topPanel.add(button);
		img = new JLabel(new ImageIcon("darthvader.jpg"));
		topPanel.add(img);
		button = new JButton("Run Place Holder");
		topPanel.setBorder(BorderFactory.createEmptyBorder(0,300,0,0));
		topPanel.add(button);
	//left
		leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.setBackground(Color.BLUE);
		button = new JButton("Set Place Holder");
		leftPanel.add(button);
		img = new JLabel(new ImageIcon("wolverine.jpg"));
		leftPanel.add(img);
		button = new JButton("Run Place Holder");
		leftPanel.setBorder(BorderFactory.createEmptyBorder(150, 0, 0, 0));
		leftPanel.add(button);
	//center
		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
		centerPanel.setPreferredSize(new Dimension(600,600));
		centerPanel.setBackground(Color.RED);
		
	//right
		rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		button = new JButton("Set Place Holder");
		rightPanel.add(button);
		img = new JLabel(new ImageIcon("yoda.jpg"));
		img.setSize(new Dimension(100,100));
		rightPanel.add(img);
		button = new JButton("Run Place Holder");
		rightPanel.setBorder(BorderFactory.createEmptyBorder(150,0,0,0));
		rightPanel.setBackground(Color.BLUE);
		rightPanel.add(button);
	//bottom
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		bottomPanel.setBackground(Color.BLUE);
		
		button = new JButton("Set Place Holder");
		bottomPanel.add(button);
		img = new JLabel(new ImageIcon("obi-wan.jpeg"));
		bottomPanel.add(img);
		button = new JButton("Run Place Holder");
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(0,300,0,0));
		bottomPanel.add(button);
		
		
		contentPane.add(bottomPanel, BorderLayout.PAGE_END);
		contentPane.add(leftPanel,BorderLayout.LINE_START);
		contentPane.add(topPanel, BorderLayout.PAGE_START);
		contentPane.add(rightPanel, BorderLayout.LINE_END);
		contentPane.add(centerPanel, BorderLayout.CENTER);
		frame.setVisible(true);
		frame.setContentPane(contentPane);
		//frame.add(contentPane);
		frame.setJMenuBar(menuBar);
		frame.pack();
	}
	
	public void addPlayerArea(Player player)
	{
	  PlayerArea player1Area = new PlayerArea();
	  player1Area.setPlayer(player);
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public static ActionListener unimplementedMenu_Click(final String message) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(null, message);
			}
		};
	}

}
