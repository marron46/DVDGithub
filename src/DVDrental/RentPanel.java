package DVDrental;
	
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RentPanel extends JPanel {
    public RentPanel(MainFrame frame) {
        setLayout(new GridLayout(3, 2));//レイアウトに新しいGridLayout(3, 2)
        
        //ボタン，フィールド作成
        JTextField memberIdField = new JTextField();//会員IDのテキストフィールド 
        JTextField dvdCodeField = new JTextField();//DVDコードのテキストフィールド
        JButton lendBtn = new JButton("貸出");//レンタル処理ボタン
        JButton backBtn = new JButton("戻る");//TOPに戻るボタン

    	//↓DB実行
        lendBtn.addActionListener(e -> {
            boolean success = DB.insertRent(memberIdField.getText(), dvdCodeField.getText());//getTextで入力した値を獲得する
            if (success) {
                JOptionPane.showMessageDialog(this, "貸出処理完了");
            } else {
                JOptionPane.showMessageDialog(this, "在庫がありません。貸出できません。", "エラー", JOptionPane.ERROR_MESSAGE);
            }
        });
        backBtn.addActionListener(e -> frame.showPanel("TOP"));//TOPに戻る

        //部品追加
        add(new JLabel("会員ID")); add(memberIdField);
        add(new JLabel("DVDコード")); add(dvdCodeField);
        add(lendBtn); add(backBtn);
    }
}
