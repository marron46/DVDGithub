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
		setLayout(new GridLayout(3,2));
		
		//ボタンとフィールドの作成
		JButton Button = new JButton("登録");
		JButton TopButton = new JButton("TOP");
		JTextField CodeField = new JTextField();
		JTextField TitleFeild = new JTextField();
		
		//登録ボタンが押されたらコードとタイトル
		Button.addActionListener(e->{
    		
	    	DB.insertDVDs(CodeField.getText(),TitleFeild.getText());
	    	JOptionPane.showMessageDialog(this, "DVDを登録しました。");
	    	});
	    	
	    	//TOPに戻るボタン
		TopButton.addActionListener(e->frame.showPanel("TOP"));
	    	//パネルに部品追加
	    	
	    	add(new JLabel("コード"));
	    	add(CodeField);
	    	add(new JLabel("タイトル"));
	    	add(TitleFeild);
	    	add(Button);
	    	add(TopButton);
	}

}
