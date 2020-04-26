package com.sticks.panels;

import com.client.ClientController;
import com.sticks.status.GameStatus;
import com.sticks.status.PlayerEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainPanel extends JFrame {
    ClientController clientController;

    private JPanel rootPanel;
    private JPanel jPanel;
    private JLabel infoLabel;
    private JButton connect;
    private  GamePanel gamePanel;

    static final int frameWidth = 600;
    static final int frameHeight = 620;
    static final int gridWidth = 450 + 11;
    static final int topSpace = 25;
    static final int betweenSpace = 5;
    static final int downSpace = 40;
    static final int labelWidth = 400;
    static final int buttonWidth = 150;
    static final int buttonHeight = 35;
    Color backgroundColor = new Color(255, 255, 255);

    public MainPanel(){
        super("Sticks Game");

        setLayout(null);
        setBackground(backgroundColor);
        exit();
        createElements();

        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(frameWidth, frameHeight);
        setVisible(true);
        gamePanel = new GamePanel(jPanel);

        SwingUtilities.updateComponentTreeUI(this);
    }

    private void createElements() {
        rootPanel = new JPanel(null);
        rootPanel.setBackground(backgroundColor);
        rootPanel.setBounds(0, 0, frameWidth, frameHeight);
        add(rootPanel);

        jPanel = new JPanel();
        jPanel.setBounds((frameWidth - gridWidth) / 2, topSpace, gridWidth, gridWidth);
        jPanel.setBackground(backgroundColor);
        rootPanel.add(jPanel);

        infoLabel = new JLabel();
        int labelHeight = frameHeight - topSpace - betweenSpace - downSpace - gridWidth;
        infoLabel.setBounds((frameWidth - labelWidth - buttonWidth) / 2,
                topSpace + gridWidth + betweenSpace, labelWidth, labelHeight);
        infoLabel.setBackground(Color.blue);
        setInfo(null, null, false);
        rootPanel.add(infoLabel);

        connect = new JButton("Connect");
        connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientController.connected();
                connect.setEnabled(false);
                gamePanel.enableField();
            }
        });
        connect.setBounds((frameWidth - labelWidth - buttonWidth) / 2 + labelWidth,
                topSpace + gridWidth + betweenSpace + (labelHeight - buttonHeight) / 2, buttonWidth, buttonHeight);
        rootPanel.add(connect);
        rootPanel.add(infoLabel);
    }

    public void setInfo(GameStatus gameStatus, PlayerEnum player, boolean turn) {
        if (gameStatus == null) {
            infoLabel.setText(Message.TRY_CONNECT.toString());
            return;
        }
        switch (gameStatus) {
            case NEW_GAME:
                infoLabel.setText(Message.WAIT_FOR_ANOTHER_PLAYER.toString());
                break;
            case CONTINUE:
                if (turn)
                    infoLabel.setText(Message.YOUR_TURN.toString());
                else infoLabel.setText(Message.ANOTHER_TURN.toString());
                break;
            case FIRST_WON:
                if (player == PlayerEnum.player1)
                    infoLabel.setText(Message.WINNER.toString());
                else if (player == PlayerEnum.player2)
                    infoLabel.setText(Message.LOSER.toString());
                break;
            case SECOND_WON:
                if (player == PlayerEnum.player2)
                    infoLabel.setText(Message.WINNER.toString());
                else if (player == PlayerEnum.player2) //player2
                    infoLabel.setText(Message.LOSER.toString());
                break;
            default:
                infoLabel.setText(Message.TRY_CONNECT.toString());
                break;
        }
    }

    private void exit() {
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent windowEvent) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                Object[] options = {"Yes", "No"};
                int answer = JOptionPane
                        .showOptionDialog(e.getWindow(), "Do you want to close the window?",
                                "Confirmation", JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE, null, options,
                                options[0]);
                if (answer == 0) {
                    e.getWindow().setVisible(false);
                    clientController.disconnected();
                    System.exit(0);
                }
            }

            @Override
            public void windowClosed(WindowEvent windowEvent) {

            }

            @Override
            public void windowIconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeiconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowActivated(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeactivated(WindowEvent windowEvent) {

            }
        });
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setClientController(ClientController clientController) {
        this.clientController = clientController;
    }
}
