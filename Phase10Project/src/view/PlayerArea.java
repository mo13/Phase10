package view;

import javax.swing.*;

import model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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