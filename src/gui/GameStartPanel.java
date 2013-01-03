package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import app.Controller;
import app.Game;
import app.RTWStrategy;
import com.alee.laf.separator.WebSeparator;
import com.alee.utils.SwingUtils;

public class GameStartPanel extends JPanel implements ActionListener {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JComboBox<Game.games> game = new JComboBox<Game.games>(Game.games.values());
    JComboBox<RTWStrategy.strategies> strategy = new JComboBox<RTWStrategy.strategies>(
            RTWStrategy.strategies.values());
    JButton startButton = new JButton("Start");
    JLabel gameLabel = new JLabel("Spiel");
    JLabel typeLabel = new JLabel("Variante");

    public GameStartPanel() {
        setLayout();
        startButton.addActionListener(this);
    }

    private void setLayout() {

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets = new Insets(5,5,5,5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(gameLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(typeLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(game, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(strategy, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        this.add(startButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        this.add(new JSeparator(JSeparator.HORIZONTAL), gbc);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startButton){
            if(game.getSelectedItem() == Game.games.TargetPractice){
                Controller.getInstance().initiateTargetPractice();
            }
            Controller.getInstance().initiateRTWGame(
                    (RTWStrategy.strategies) strategy.getSelectedItem());
        }

    }

}
