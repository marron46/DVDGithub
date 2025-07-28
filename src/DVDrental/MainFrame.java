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
		
		cardPanel.add(new TopPanel(this), "TOP");
		cardPanel.add(new DVDs(this), "DVD");
		cardPanel.add(new MemberPanel(this), "MEMBER");
		cardPanel.add(new RentPanel(this), "RENT");
		cardPanel.add(new BiveBack(this), "BIVEBACK");
		cardPanel.add(new zaiko(this), "ZAIKO");
		cardPanel.add(new Ranking(this), "RANKING");		
		add(cardPanel);
		setVisible(true);
	}
	
	public void showPanel(String name) {
        cardLayout.show(cardPanel, name);
    }
}
