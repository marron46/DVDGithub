package DVDrental;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class zaiko extends JPanel{
	//フィールドを追加
	DefaultListModel<String> listModel;
    JList<String> zaikoList;
    JButton reloadButton;
    JTextArea text = new JTextArea();
    JButton topButton = new JButton("TOP");
	public zaiko(MainFrame frame) {
        //レイアウト追加
        setLayout(new BorderLayout());
        //リストを追加
        listModel = new DefaultListModel<>();
        zaikoList = new JList<>(listModel);
        reloadButton = new JButton("在庫一覧を表示");
        //ボタンを押したときの処理
        reloadButton.addActionListener(e -> loadZaikoList());
        topButton.addActionListener(e -> frame.showPanel("TOP"));
        //配置
        add(reloadButton, BorderLayout.NORTH);
        add(new JScrollPane(zaikoList), BorderLayout.CENTER);
        add(topButton, BorderLayout.SOUTH);
        
    }
	//更新処理
	private void loadZaikoList() {
        listModel.clear();
        List<String> list = DB.listAll();
        for (String item : list) {
            listModel.addElement(item);
        }
	}
	
}
