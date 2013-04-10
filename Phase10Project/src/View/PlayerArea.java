package View;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.*;


public class PlayerArea implements ActionListener {

	Player player;// =new Player("Cortona");

	
	

	public  PlayerArea(Player player){
		
			JPanel Player0Area = new JPanel();
			Player0Area.setLayout(new BoxLayout(Player0Area, BoxLayout.X_AXIS));
			Player0Area.setBorder(BorderFactory.createEmptyBorder(100,600,0,0));
			Player0Area.setPreferredSize(new Dimension(600,600));
			System.out.println("Phase:" + player.getPhaseNumber());
			Player0Area.setBackground(Color.BLACK);
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}

  public void showPhase() {
  }

  public void showHand() {
  }
  
//  public void addPlayerArea(Player player)
//	{
//	  PlayerArea player1Area = new PlayerArea();
//	  player1Area.setPlayer(player);
//	  
//	  PlayerArea player2Area = new PlayerArea();
//	  player2Area.setPlayer(player);
//	  
//	  PlayerArea player3Area = new PlayerArea();
//	  player3Area.setPlayer(player);
//	  
//	  PlayerArea player4Area = new PlayerArea();
//	  player4Area.setPlayer(player);
//	  
//	
//	}





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