package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainFrame extends JFrame implements ActionListener {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final JPanel rtwPanel = RTWGamePanel.getInstance();
    private final JPanel startPanel_ = new JPanel();
    private final JTabbedPane tabPane_ = new JTabbedPane();

    public MainFrame() {
	setFrameProperties();
	setComponentAttributes();
	setLayout();
	registerActionListener();
    }

    public static void main(String[] args) {
	JFrame test = new MainFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub

    }

    private void registerActionListener() {
	// TODO Auto-generated method stub

    }

    private void setComponentAttributes() {
	// TODO Auto-generated method stub

    }

    private void setFrameProperties() {
	setTitle("SWDarts");

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	this.setMinimumSize(new java.awt.Dimension(940, 712));
	this.setPreferredSize(new java.awt.Dimension(940, 712));
	this.setLocation(
		(int) ((screenSize.getWidth()) / 2) - (getWidth() / 2), 0);
	this.setVisible(true);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    private void setLayout() {
	getContentPane().setLayout(
		new BoxLayout(getContentPane(), BoxLayout.LINE_AXIS));

	JPanel contentPanel = new JPanel();
	contentPanel.setLayout(new GridLayout(1, 1));
	contentPanel.add(startPanel_);

	tabPane_.addTab("Start", startPanel_);
	tabPane_.addTab("RTW", rtwPanel);
	getContentPane().add(tabPane_);

    }

}
