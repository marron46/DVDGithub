package DVDrental;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Member extends JPanel{
	
	public Member(MainFrame frame) {
		
		setLayout(new GridLayout(4,2));	//レイアウトに新しいの追加
		//宣言
		JTextField idText = new JTextField();
		JTextField nameText = new JTextField();
		JButton torokuButton = new JButton("登録");
		JButton topButton = new JButton("TOP");
		
		//登録ボタンが押された時の処理
		torokuButton.addActionListener(e ->{
			DB.insertMember(idText.getText(),nameText.getText());
			JOptionPane.showMessageDialog(this, "会員登録が完了しました。");
		});;
		
		//TOPに戻るボタン
		topButton.addActionListener(e -> frame.showPanel("TOP"));
		//パネルに部品追加
		add(new JLabel("ID"));
		add(idText);
		add(new JLabel("名前"));
		add(nameText);
		add(torokuButton);
		add(topButton);
	}

}
