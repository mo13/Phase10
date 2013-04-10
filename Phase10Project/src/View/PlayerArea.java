package View;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.*;


public class PlayerArea implements ActionListener {

	private JPanel Player1Area;
	Player player;// =new Player("Cortona");

	
	public void setPlayer(Player player) {
		this.player = player;
	}

	public void Player1Area(){
		
	//PlayerArea
			Player1Area = new JPanel();
			Player1Area.setLayout(new BoxLayout(Player1Area, BoxLayout.X_AXIS));
			Player1Area.setBackground(Color.BLACK);
			System.out.println("Phase:" + player.getPhaseNumber());
			Player1Area.setBorder(BorderFactory.createEmptyBorder(100,600,0,0));
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