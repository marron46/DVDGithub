package DVDrental;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DVDs extends JPanel{
	public DVDs(MainFrame frame) {
		//レイアウトの作成
		setLayout(new GridLayout(4,3));
		
		//ボタンとフィールドの作成
		JButton Button = new JButton("登録");
		JButton TopButton = new JButton("TOP");
		JTextField CodeField = new JTextField();
		JTextField TitleField = new JTextField();
		JTextField totalField = new JTextField();
		
		//登録ボタンが押されたらコードとタイトル
		Button.addActionListener(e->{
    		
	    	DB.insertDVDs(CodeField.getText(),TitleField.getText(),totalField.getText());
	    	JOptionPane.showMessageDialog(this, "DVDを登録しました。");
	    	});
	    	
	    	//TOPに戻るボタン
		TopButton.addActionListener(e->frame.showPanel("TOP"));
	    	//パネルに部品追加
	    	
	    	add(new JLabel("コード"));
	    	add(CodeField);
	    	add(new JLabel("タイトル"));
	    	add(TitleField);
	    	add(new JLabel("在庫数"));
	    	add(totalField);
	    	add(Button);
	    	add(TopButton);
	}

}
