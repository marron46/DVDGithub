package DVDrental;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	
	private CardLayout cardLayout;
	private JPanel cardPanel;
	
	public MainFrame() {
		
		setTitle("DVDレンタルシステム");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 400);
		
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		
		cardPanel.add(new Top(this), "TOP");
		cardPanel.add(new DVDs(this), "DVD");
		cardPanel.add(new Member(this), "MEMBER");
		cardPanel.add(new Rent(this), "RENTAL");
		cardPanel.add(new BiveBack(this), "RETURN");
		
		add(cardPanel);
		setVisible(true);
	}
	
	public void showPanel(String name) {
        cardLayout.show(cardPanel, name);
    }
}
