package gui;

import com.alee.laf.WebLookAndFeel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainFrame extends JFrame implements ActionListener {

    /**
     *
     */
    private static MainFrame instance_;
    private static final long serialVersionUID = 1L;

    private  JPanel startPanel_;
    private  JTabbedPane tabPane_;

    private MainFrame() {

        setFrameProperties();
        setComponentAttributes();
        setLayout();
        registerActionListener();
    }

    public static MainFrame getInstance() {
        if (instance_ == null) instance_ = new MainFrame();
        return instance_;
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

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    private void setLayout() {
        System.setProperty("awt.useSystemAAFontSettings","on");
        System.setProperty("swing.aatext", "true");
        getContentPane().setLayout(
                new BoxLayout(getContentPane(), BoxLayout.LINE_AXIS));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(1, 1));
        startPanel_ = new GameStartPanel();
        tabPane_ = new JTabbedPane();
        TargetPracticePanel tppanel = new TargetPracticePanel();


        getContentPane().add(tabPane_);
        contentPanel.add(startPanel_);
        tabPane_.addTab("Start", startPanel_);
        tabPane_.addTab("RTW", RTWGamePanel.getInstance());
        tabPane_.addTab("Target Practice", tppanel.getTargetPracticePanel());
        tabPane_.addTab("501", FiveOOnePanel.getInstance().getFooPanel());
        tabPane_.addTab("Bob's 27",new JPanel());
        tabPane_.addTab("Cricket",new JPanel());
        tabPane_.addTab("170",new JPanel());
        tabPane_.addTab("Statistiken",new JPanel());
        tabPane_.setEnabledAt(1, false);
        //tabPane_.setEnabledAt(2, false);
        //tabPane_.setEnabledAt(3, false);
        tabPane_.setEnabledAt(4, false);
        tabPane_.setEnabledAt(5, false);
        tabPane_.setEnabledAt(6, false);
        tabPane_.setSelectedIndex(3);

        this.setVisible(true);
        FiveOOnePanel.getInstance().getFooPanel().requestFocus();
        FiveOOnePanel.getInstance().getFirstThrowField().requestFocus();


    }

    public void addRTWGame(){
        tabPane_.addTab("RTW", RTWGamePanel.getInstance());
        tabPane_.setSelectedComponent(RTWGamePanel.getInstance());
    }

    public void resetFoo() {
        tabPane_.removeTabAt(3);
        tabPane_.insertTab("501",null,FiveOOnePanel.getInstance().getFooPanel(),null,3);
        tabPane_.setSelectedIndex(3);

    }
}
