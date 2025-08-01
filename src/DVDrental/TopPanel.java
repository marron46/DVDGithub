package DVDrental;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class TopPanel extends JPanel {
	
	public TopPanel(MainFrame frame) {
		
		setLayout(new GridLayout(6, 1, 10, 10));
		
		JButton bookButton = new JButton("DVDの登録");
		bookButton.addActionListener(e -> frame.showPanel("DVD"));
		
		JButton memberButton = new JButton("会員登録");
		memberButton.addActionListener(e -> frame.showPanel("MEMBER"));
		
		JButton rentButton = new JButton("貸出処理");
		rentButton.addActionListener(e -> frame.showPanel("RENT"));
		
		JButton bivebackButton = new JButton("返却処理");
		bivebackButton.addActionListener(e -> frame.showPanel("BIVEBACK"));
		
		JButton zaikoButton = new JButton("在庫一覧");
		zaikoButton.addActionListener(e -> frame.showPanel("ZAIKO"));
		
		JButton rankingButton = new JButton("ランキング");
		rankingButton.addActionListener(e -> frame.showPanel("RANKING"));
		
		add(bookButton);
        add(memberButton);
        add(rentButton);
        add(bivebackButton);
        add(zaikoButton);
        add(rankingButton);
	}

}
