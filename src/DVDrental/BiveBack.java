package DVDrental;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BiveBack extends JPanel{
		
		public BiveBack(MainFrame Frame){
		setLayout(new GridLayout(3,2));//レイアウトの作成
		
		JButton RentButton = new JButton("返却");
		JButton TopButton = new JButton("TOP");
		JTextField CodeField = new JTextField();//ボタンとフィールドの作成
		
		RentButton.addActionListener(e -> {
			DB.BackDVD(CodeField.getText());
			JOptionPane.showMessageDialog(this,"返却が完了しました。");//返却処理
		});
		
		TopButton.addActionListener(e -> Frame.showPanel("TOP"));//TOPに戻る
		
		add(new JLabel("DVDコード"));
		add(CodeField);
		add(RentButton);
		add(TopButton);//ボタンとフィールドの配置
		
		}
	}


}
