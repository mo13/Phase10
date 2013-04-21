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
    
	private PlayerArea player1Area, player2Area,player3Area, player4Area;

	private JTextField textField;
	private JFrame frame;
	private JButton button1, button2, button3, button4, button5, button6, button7, button8;
	private JPanel contentPane, leftPanel, rightPanel, topPanel, bottomPanel, centerPanel;
	private JLabel img;
	private JMenuBar menuBar;
	private JMenuItem setupMenuItem, exitMenu, phaseSearcherStrategy, preventerStrategy, lowestScoreStrategy, cardCounterStrategy,
					  scorePlayers, phaseTracker, displayScore, exitRound, showOrder, resetOrder, resetDrawPile, draw, playPhase, hit,
					  checkHit, discard, finishTurn, playerStrategy, player1, player2, player3, player4;
	private JMenu menu, round, playerOptions, scoring;
	
	public void createUI() {
	// Frame
		frame = new JFrame();
		frame.setTitle("Phase 10");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
		contentPane = new JPanel(new BorderLayout());

	
		
	// The Menu Bar
		menuBar = new JMenuBar();
		
		
	//Game Menu
		menu = new JMenu("Game");
		menuBar.add(menu);		
		setupMenuItem = new JMenuItem("Setup Board");
		setupMenuItem.addActionListener(this);
		menu.add(setupMenuItem);

		
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
		
		playerStrategy = new JMenu("Set Player Strategy");
		playerStrategy.addActionListener(unimplementedMenu_Click("This will set allow you to set player strategies."));
		playerOptions.add(playerStrategy);
		
		player1 = new JMenu("Master Chief");
		player1.addActionListener(unimplementedMenu_Click("This allows you to set player 1's strategy"));
		playerStrategy.add(player1);
		
		player2 = new JMenu("Cortona");
		player2.addActionListener(unimplementedMenu_Click("This allows you to set player 1's strategy"));
		playerStrategy.add(player2);
		
		player3 = new JMenu("Johnson");
		player3.addActionListener(unimplementedMenu_Click("This allows you to set player 1's strategy"));
		playerStrategy.add(player3);
		
		player4 = new JMenu("Arbiter");
		player4.addActionListener(unimplementedMenu_Click("This allows you to set player 1's strategy"));
		playerStrategy.add(player4);
		
		phaseSearcherStrategy = new JMenuItem("Phase Searcher Strategy");
		phaseSearcherStrategy.addActionListener(unimplementedMenu_Click("This will implement the phase searcher strategy."));
		player1.add(phaseSearcherStrategy);
		
		preventerStrategy = new JMenuItem("Preventer Strategy");
		preventerStrategy.addActionListener(unimplementedMenu_Click("This will implement the preventer strategy."));
		player1.add(preventerStrategy);
		
		lowestScoreStrategy = new JMenuItem("Lowest Score Strategy");
		lowestScoreStrategy.addActionListener(unimplementedMenu_Click("This will implement the lowest score strategy."));
		player1.add(lowestScoreStrategy);
		
		cardCounterStrategy = new JMenuItem("Card Counter Strategy");
		cardCounterStrategy.addActionListener(unimplementedMenu_Click("This will implement the card counter strategy."));
		player1.add(cardCounterStrategy);
		
		exitMenu = new JMenu("Exit game");
		exitMenu.addActionListener(this);
		menuBar.add(exitMenu);
		

		
	//top
		
	
		topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		topPanel.setBackground(Color.BLUE);
		img = new JLabel(new ImageIcon("chief.jpg"));
		topPanel.add(img);
		img.setSize(new Dimension(50,50));
		
		topPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		
		
	//left
		leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.setBackground(Color.BLUE);
		
		img = new JLabel(new ImageIcon("johnson.jpg"));
		img.setSize(new Dimension(100,100));
		leftPanel.add(img);
		leftPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

	//center
		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.setSize(new Dimension(200,200));
		centerPanel.setBackground(Color.RED);
		
	//right
		rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		img = new JLabel(new ImageIcon("cortona.jpg"));
		img.setSize(new Dimension(100,100));
		rightPanel.add(img);
		rightPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		rightPanel.setBackground(Color.BLUE);
	//bottom
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
		bottomPanel.setBackground(Color.BLUE);
		
		img = new JLabel(new ImageIcon("arbiter.jpg"));
		img.setSize(new Dimension(100,100));
		bottomPanel.add(img);
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		
		contentPane.add(bottomPanel, BorderLayout.PAGE_END);
		contentPane.add(leftPanel,BorderLayout.LINE_START);
		contentPane.add(topPanel, BorderLayout.PAGE_START);
		contentPane.add(rightPanel, BorderLayout.LINE_END);
		contentPane.add(centerPanel, BorderLayout.CENTER);
		frame.setVisible(true);
		frame.setContentPane(contentPane);
		//frame.add(contentPane);
		frame.setJMenuBar(menuBar);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,800);
		frame.pack();
	}
	
	


	@Override
	public void actionPerformed(ActionEvent event){
		   if (event.getSource() == setupMenuItem){
				textField.setText("Board reset.");
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
