package DVDrental;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Ranking extends JPanel{
	private DefaultListModel<String> model;
    private JList<String> list;
    private JButton reloadButton;

    public Ranking() {
        setLayout(new BorderLayout());
        model = new DefaultListModel<>();
        list = new JList<>(model);
        reloadButton = new JButton("ランキングを表示");

        reloadButton.addActionListener(e -> loadRanking());

        add(reloadButton, BorderLayout.NORTH);
        add(new JScrollPane(list), BorderLayout.CENTER);
    }

    private void loadRanking() {
        model.clear();
        List<String> ranking = DB.getRanking();
        for (String item : ranking) {
            model.addElement(item);
        }
    }

}
