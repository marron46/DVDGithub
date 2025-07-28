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
		
	//ボタンとフィールドの作成
	JButton RentButton = new JButton("返却");//返却ボタン
	JButton TopButton = new JButton("TOP");  //TOPボタン
	JTextField CodeField = new JTextField(); //DVDコードのテキストフィールド
	
	//DB返却処理
	RentButton.addActionListener(e -> {
		boolean notLate = DB.insertBackDVD(CodeField.getText());
	    if (notLate) {
	        JOptionPane.showMessageDialog(this,"返却が完了しました。");
	    } else {
	        JOptionPane.showMessageDialog(this,"返却が完了しましたが、延滞しています。", "延滞", JOptionPane.WARNING_MESSAGE);
	    }
	});
		
	
	TopButton.addActionListener(e -> Frame.showPanel("TOP"));//TOPに戻る
	
	//ボタンとフィールドの配置
	add(new JLabel("DVDコード"));
	add(CodeField);
	add(RentButton);
	add(TopButton);
	
	}
	
}
