package DVDrental;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class zaiko extends JPanel{
	public zaiko(MainFrame frame) {
        //レイアウト追加
        setLayout(new BorderLayout());
        
        //表示フィールド追加
        JButton topButton = new JButton("TOP");
        JTextArea text = new JTextArea();
        //Listを作成string型
        ArrayList<String> List = new ArrayList<>();
        List = DB.listAll();
        text.append(List+"\n");
        /*for(int i = 0; i < List.size(); i++) {
        	text.append(List+"\n");
        }*/
        //TOP戻るボタン
        topButton.addActionListener(e -> frame.showPanel("TOP"));
        //パネルに部品の追加
        add(new JScrollPane(text), BorderLayout.CENTER);
        add(topButton, BorderLayout.SOUTH);
    }
	
	
}
