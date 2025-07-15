package DVDrental;
	
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

	public class RentPanel extends JPanel{
		public RentPanel(MainFrame frame) {
	    	
	    	setLayout (new GridLayout(3, 2));//レイアウトに新しいGridLayout(3, 2)
	    	
	        //ボタン，フィールド作成
	    	JTextField memberIDField = new JTextField();//会員IDのテキストフィールド 
	    	JTextField DVDcodeField = new JTextField();//DVDコードのテキストフィールド
	    	JButton rental = new JButton("レンタル");//レンタル処理ボタン
	    	JButton top = new JButton("TOP");//TOPに戻るボタン

	    	//↓DB実行
	    	rental.addActionListener(e -> { 
	    		DB.insertRent(memberIDField.getText(),DVDcodeField.getText());//getTextで入力した値を獲得する
	        	JOptionPane.showMessageDialog(this,"レンタル処理が完了しました");
	    	});

	    	top.addActionListener(e -> frame.showPanel("TOP"));//TOPに戻る

	        //部品追加
	    	add(new JLabel("会員ID"));
	    	add(memberIDField);
	    	add(new JLabel("DVDコード"));
	    	add(DVDcodeField);
	    	add(rental);
	    	add(top);
    }
}
	
	//public static void insertRent(String memberid,String DVDcode) {
		//try(Connection conn = DriverManager.getConnection(URL,USER,PASS);
				//PreparedStatement ps = conn.prepareStatement("UPDATE dvd SET is_lent = true WHERE code = ?")){
				//ps.setString(1,DVDcode);
				//ps.executeUpdate();
			//}catch(SQLException e){
				//e.printStackTrace();
			//}
	//}
