package DVDrental;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MemberPanel extends JPanel {
	
	boolean hankaku = true;
	
	public MemberPanel(MainFrame frame) {
		
		setLayout(new GridLayout(4,2));	//レイアウトに新しいの追加
		//宣言
		JTextField idText = new JTextField();
		JTextField nameText = new JTextField();
		JButton torokuButton = new JButton("登録");
		JButton topButton = new JButton("TOP");
		
		//登録ボタンが押された時の処理
		torokuButton.addActionListener(e ->{
			DB.insertMember(idText.getText(),nameText.getText());
			if(hantei(idText.getText())){
				System.out.println("全角文字が含まれています");
				hankaku = false;
			}else {
				hankaku = true;
			}
			if(hankaku == true) {
				JOptionPane.showMessageDialog(this, "会員登録が完了しました。");
			}else {
				JOptionPane.showMessageDialog(this, "全角文字が含まれています。");
			}
		});
		
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
	
	public static boolean hantei(String idText) {
		
		for(int i = 0; i < idText.length(); i++) {
			char ch = idText.charAt(i);
			if(ch < 0x0020 || ch > 0x007E) {
				return true;
			}
		}
		return false;
	}

}
