import java.awt.Color;
import java.awt.Font;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

//hele filen er lager egt bare de tre displayene jeg bruker iløpet av filen. funcksjonene er alle funksjoner fra librariene jeg bruker
public class UI {
  JFrame window;
  ImageIcon img;
  Container con;
  JPanel titleNamePanel, startButtonPanel, updatePanel, mainTextPanel, choiceButtonPanel, playerPanel, inputTextPanel, diceButtonPanel;
  JLabel titleNameLabel, nameLabel, nameLabelString, hpLabel, hpLabelInt;
  JButton startButton, updateButton, choice1, choice2, choice3, diceButton;
  JTextArea mainTextArea, inputTextArea, diceTextArea;
  Font titleFont = new Font("Times New Roman", Font.PLAIN, 128), normalFont = new Font("Times New Roman", Font.PLAIN, 25);

  public void createUI(Game.ChoiceHandler cHandler){
    //window
    window = new JFrame();
    window.setTitle("Game");
    window.setSize(900, 700);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.getContentPane().setBackground(Color.black);
    window.setLayout(null);
    window.setVisible(true);
    img = new ImageIcon("icon/gameicon.png");
    window.setIconImage(img.getImage());
    con = window.getContentPane();

    //titalscreen
    titleNamePanel = new JPanel();
    titleNamePanel.setBounds(100, 100, 700, 150);
    titleNamePanel.setBackground(Color.black);

    titleNameLabel = new JLabel("GAME");
    titleNameLabel.setForeground(Color.white);
    titleNameLabel.setFont(titleFont);

    startButtonPanel = new JPanel();
    startButtonPanel.setBounds(300, 400, 300, 100);
    startButtonPanel.setBackground(Color.black);

    startButton = new JButton("START");
    startButton.setBackground(Color.black);
    startButton.setForeground(Color.white);
    startButton.setFont(normalFont);
    startButton.setFocusPainted(false);
    startButton.addActionListener(cHandler);
    startButton.setActionCommand("start");

    updatePanel = new JPanel();
    updatePanel.setBounds(50, 550, 120, 50);
    updatePanel.setBackground(Color.black);

    updateButton = new JButton("Update");
    updateButton.setBackground(Color.black);
    updateButton.setForeground(Color.white);
    updateButton.setFont(normalFont);
    updateButton.setFocusPainted(false);
    updateButton.addActionListener(cHandler);
    updateButton.setActionCommand("update");

    titleNamePanel.add(titleNameLabel);
    startButtonPanel.add(startButton);
    updatePanel.add(updateButton);

    con.add(titleNamePanel);
    con.add(startButtonPanel);
    con.add(updatePanel);

    
    //name input
    inputTextPanel = new JPanel();
    inputTextPanel.setBounds(375, 300, 150, 100);
    inputTextPanel.setBackground(Color.black);

    inputTextArea = new JTextArea("enter here|");
    inputTextArea.setBounds(375, 300, 150, 100);
    inputTextArea.setBackground(Color.blue);
    inputTextArea.setForeground(Color.white);
    inputTextArea.setFont(normalFont);
    inputTextArea.setEditable(true);
    inputTextPanel.add(inputTextArea);


    con.add(inputTextPanel);

    //Game Screen
    //main taxt area
    mainTextPanel = new JPanel();
    mainTextPanel.setBounds(175, 100, 550, 350);
    mainTextPanel.setBackground(Color.black);

    mainTextArea = new JTextArea("Enter a player name, if the name has a saved game you will continue an old run. When you are ready press the button (dont press enter)");
    mainTextArea.setBounds(175, 100, 550, 350);
    mainTextArea.setBackground(Color.black);
    mainTextArea.setForeground(Color.white);
    mainTextArea.setFont(normalFont);
    mainTextArea.setLineWrap(true);
    mainTextArea.setWrapStyleWord(true);
    mainTextArea.setEditable(false);
    mainTextPanel.add(mainTextArea);

    //choice buttons
    choiceButtonPanel = new JPanel();
    choiceButtonPanel.setBounds(300, 475, 300, 150);
    choiceButtonPanel.setBackground(Color.black);
    choiceButtonPanel.setLayout(new GridLayout(3,1));

    choice1 = new JButton();
    choice1.setBackground(Color.black);
    choice1.setForeground(Color.white);
    choice1.setFont(normalFont);
    choice1.setFocusPainted(false);
    choice1.addActionListener(cHandler);
    choice1.setActionCommand("c1");

    choice2 = new JButton();
    choice2.setBackground(Color.black);
    choice2.setForeground(Color.white);
    choice2.setFont(normalFont);
    choice2.setFocusPainted(false);
    choice2.addActionListener(cHandler);
    choice2.setActionCommand("c2");

    choice3 = new JButton();
    choice3.setBackground(Color.black);
    choice3.setForeground(Color.white);
    choice3.setFont(normalFont);
    choice3.setFocusPainted(false);
    choice3.addActionListener(cHandler);
    choice3.setActionCommand("c3");

    //top panel
    playerPanel = new JPanel();
    playerPanel.setBounds(150, 15, 600, 50);
    playerPanel.setBackground(Color.black);
    playerPanel.setLayout(new GridLayout(1,4));

    nameLabel = new JLabel("Player:");
    nameLabel.setFont(normalFont);
    nameLabel.setForeground(Color.white);
    
    nameLabelString = new JLabel();
    nameLabelString.setFont(normalFont);
    nameLabelString.setForeground(Color.white);

    hpLabel = new JLabel("HP:");
    hpLabel.setFont(normalFont);
    hpLabel.setForeground(Color.white);

    hpLabelInt = new JLabel();
    hpLabelInt.setFont(normalFont);
    hpLabelInt.setForeground(Color.white);

    //dice
    diceButtonPanel = new JPanel();
    diceButtonPanel.setBounds(700, 450, 100, 150);
    diceButtonPanel.setBackground(Color.black);
    diceButtonPanel.setLayout(new GridLayout(2,1));
    
    diceTextArea = new JTextArea("24");
    diceTextArea.setBackground(Color.red);
    diceTextArea.setForeground(Color.white);
    diceTextArea.setFont(normalFont);
    diceTextArea.setBounds(700, 450, 20, 20);
    diceTextArea.setEditable(false);

    diceButton = new JButton("roll");
    diceButton.setBackground(Color.green);
    diceButton.setForeground(Color.white);
    diceButton.setFont(normalFont);
    diceButton.setFocusPainted(false);
    diceButton.addActionListener(cHandler);
    diceButton.setActionCommand("roll");

    diceButtonPanel.add(diceTextArea);
    diceButtonPanel.add(diceButton);

    //add
    choiceButtonPanel.add(choice1);
    choiceButtonPanel.add(choice2);
    choiceButtonPanel.add(choice3);
    
    playerPanel.add(nameLabel);
    playerPanel.add(nameLabelString);
    playerPanel.add(hpLabel);
    playerPanel.add(hpLabelInt);

    con.add(playerPanel);
    con.add(mainTextPanel);
    con.add(choiceButtonPanel);
    con.add(diceButtonPanel);

    //setVisible
    con.setVisible(false);
    con.setVisible(true);
    
  }
}
