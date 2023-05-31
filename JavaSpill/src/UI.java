import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.Timer;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class UI {
  Game game;
  Player player;
  GameInventory in;
  Encounter en;
  Map map;

  JFrame window;
  private ImageIcon iconImg;
  private Container con;
  private Dimension roomImgSize, structureImgSize, encounterImgSize, attackImgSize, playerPosImgSize;
  JPanel mainTextPanel, titleNamePanel, startButtonPanel, updateButtonPanel, inputTextPanel, choiceButtonPanel, itemInfoPanel, statPanel, mapPanel;
  JLabel roomImgLabel, structureImgLabel, encounterImgLabel, titleNameLabel, stringShopCounterLabel, intShopCounterLabel, attackLabel, playerPosLabel;
  JTextField inputTextField;
  JTextArea mainTextArea;
  private Font titleFont = new Font("Times New Roman", Font.PLAIN, 128), normalFont = new Font("Times New Roman", Font.PLAIN, 25);
  JButton startButton, updateButton;

  ArrayList<JLabel> statLabelList = new ArrayList<JLabel>();
  ArrayList<JButton> buttonList = new ArrayList<JButton>();
  ArrayList<JLabel> itemInfoLabelList = new ArrayList<JLabel>();
  ArrayList<JLabel> mapLabelList = new ArrayList<JLabel>();
  public ArrayList<ArrayList<String>> mapString = new ArrayList<ArrayList<String>>();

  int[][] buttonListLayout = {
    { 0, 1},
    { 2, 3},
    { 4, 5},
    { 6, 7},
    { 8, 9},
    { 10, 11}
  };

  int y=5, x=1, pos = buttonListLayout[y][x];
  public void chageButton(String d){
    if(pos % 2 == 0 && d == "right"){
      x++;
    }
    if(pos % 2 != 0 && d == "left"){
      x=x-1;
    }
    if(pos > 1 && d == "up"){
      y=y-1;
    }
    if(pos < 10 && d == "down"){
      y++;
    }
    pos = buttonListLayout[y][x];

    if(en.buttonPanalUse == "shopBuy" || en.buttonPanalUse == "shopSell"){
      ArrayList<Object> in;
      if (en.buttonPanalUse == "shopBuy") {
        in = en.shopInventory;
      } else{
        in = player.inventory;
      }
      int j = 0;
      itemInfoLabelList.get(j).setText("");j++;
      itemInfoLabelList.get(j).setText("");j++;
      itemInfoLabelList.get(j).setText("");j++;
      itemInfoLabelList.get(j).setText("");j++;
      itemInfoLabelList.get(j).setText("");j++;
      itemInfoLabelList.get(j).setText("");
      if(in.size() > pos){
        if(in.get(pos).getClass() == Weapon.class){
          int k = 0;
          Weapon w = (Weapon)in.get(pos);
          itemInfoLabelList.get(k).setText("Name:");k++;
          itemInfoLabelList.get(k).setText(w.getType());k++;
          itemInfoLabelList.get(k).setText("Damage:");k++;
          itemInfoLabelList.get(k).setText(w.getDamage()+"");k++;
          itemInfoLabelList.get(k).setText("Value:");k++;
          itemInfoLabelList.get(k).setText(w.getValue()+" gold");
          itemInfoPanel.setVisible(true);
        }else if(in.get(pos).getClass() == Armor.class){
          int k = 0;
          Armor a = (Armor)in.get(pos);
          itemInfoLabelList.get(k).setText("Name:");k++;
          itemInfoLabelList.get(k).setText(a.getType());k++;
          itemInfoLabelList.get(k).setText("Defence:");k++;
          itemInfoLabelList.get(k).setText(a.getDefence()+"");k++;
          itemInfoLabelList.get(k).setText("Value:");k++;
          itemInfoLabelList.get(k).setText(a.getValue()+" gold");
          itemInfoPanel.setVisible(true);
        }else if(in.get(pos).getClass() == Item.class){
          int k = 0;
          Item i = (Item)in.get(pos);
          itemInfoLabelList.get(k).setText("Name:");k++;
          itemInfoLabelList.get(k).setText(i.getType());k++;
          itemInfoLabelList.get(k).setText("Value:");k++;
          itemInfoLabelList.get(k).setText(i.getValue()+" gold");
          itemInfoPanel.setVisible(true);
        }
      }
    }
    drawButtons();
  }

  ArrayList<Integer> selectedButtons = new ArrayList<>();
  public void selctbutton(int me){
    itemInfoPanel.setVisible(false);
    if(en.buttonPanalUse == "shopBuy" || en.buttonPanalUse == "shopSell"){
      if(!buttonCheck(me)){
        selectedButtons.add(me);
      }
      updateSellCounter();
    }
    if(en.buttonPanalUse == "inventory"){
      int j = 0;
      itemInfoLabelList.get(j).setText("");j++;
      itemInfoLabelList.get(j).setText("");j++;
      itemInfoLabelList.get(j).setText("");j++;
      itemInfoLabelList.get(j).setText("");j++;
      itemInfoLabelList.get(j).setText("");j++;
      itemInfoLabelList.get(j).setText("");

      if(!buttonCheck(me)){
        selectedButtons.clear();
        selectedButtons.add(me);
      }
      buttonList.get(10).setText("");
      game.c10 = "";
      if(player.inventory.get(me).getClass() == Weapon.class){
        buttonList.get(10).setText("Equip");
        game.c10 = "equipWeapon";
        int k = 0;
        Weapon w = (Weapon)player.inventory.get(me);
        itemInfoLabelList.get(k).setText("Name:");k++;
        itemInfoLabelList.get(k).setText(w.getType());k++;
        itemInfoLabelList.get(k).setText("Damage:");k++;
        itemInfoLabelList.get(k).setText(w.getDamage()+"");k++;
        itemInfoLabelList.get(k).setText("Value:");k++;
        itemInfoLabelList.get(k).setText(w.getValue()+" gold");
        itemInfoPanel.setVisible(true);
      }
      if(player.inventory.get(me).getClass() == Armor.class){
        buttonList.get(10).setText("Equip");
        game.c10 = "equipArmor";
        int k = 0;
        Armor a = (Armor)player.inventory.get(me);
        itemInfoLabelList.get(k).setText("Name:");k++;
        itemInfoLabelList.get(k).setText(a.getType());k++;
        itemInfoLabelList.get(k).setText("Defence:");k++;
        itemInfoLabelList.get(k).setText(a.getDefence()+"");k++;
        itemInfoLabelList.get(k).setText("Value:");k++;
        itemInfoLabelList.get(k).setText(a.getValue()+" gold");
        itemInfoPanel.setVisible(true);
      }
      if(player.inventory.get(me).getClass() == Item.class){
        int k = 0;
        Item i = (Item)player.inventory.get(me);
        itemInfoLabelList.get(k).setText("Name:");k++;
        itemInfoLabelList.get(k).setText(i.getType());k++;
        itemInfoLabelList.get(k).setText("Value:");k++;
        itemInfoLabelList.get(k).setText(i.getValue()+" gold");
        itemInfoPanel.setVisible(true);
      }
      if(player.getInventoryString(me).equals("healing potion")){
        buttonList.get(10).setText("Use potion");
        game.c10 = "heal";
      }
    }
    drawButtons();
  }
  public void drawButtons(){
    if(en.buttonPanalUse == "inventory" && selectedButtons.size() == 0){
      buttonList.get(10).setText("");
      game.c10 = "";
    }
    for(int i = 0; i < buttonList.size(); i++){
      buttonList.get(i).setBorder(BorderFactory.createLineBorder(Color.white, 1));
    }

    if(en.buttonPanalUse == "inventory" || en.buttonPanalUse == "shopSell"){
      for(int i = 0; i < player.inventory.size(); i++){
        if(player.inventory.get(i) == player.getMyWeapon()){
          buttonList.get(i).setBorder(BorderFactory.createLineBorder(Color.green, 4));
        }
        if(player.inventory.get(i) == player.getMyArmor()){
          buttonList.get(i).setBorder(BorderFactory.createLineBorder(Color.green, 4));
        }
      }
    }

    for(int i = 0; i < selectedButtons.size(); i++){
      buttonList.get(selectedButtons.get(i)).setBorder(BorderFactory.createLineBorder(Color.red, 4));
    }
    buttonList.get(pos).setBorder(BorderFactory.createLineBorder(Color.white, 4));
  }

  public boolean buttonCheck(int check) {
    for(int i = 0; i < selectedButtons.size(); i++){
      if(selectedButtons.get(i) == check){
        selectedButtons.remove(i);
        return true;
      }
    }
    return false;
  }

  int sum;
  public void updateSellCounter() {
    sum = 0;
    for(int i = 0; i < selectedButtons.size(); i++){
      for(int j = 0; j < in.Weapons.size(); j++){
        if(in.Weapons.get(j).getType() == buttonList.get(selectedButtons.get(i)).getText()){
          sum+= in.Weapons.get(j).getValue();
        }
      }
      for(int j = 0; j < in.Armors.size(); j++){
        if(in.Armors.get(j).getType() == buttonList.get(selectedButtons.get(i)).getText()){
          sum+= in.Armors.get(j).getValue();
        }
      }
      for(int j = 0; j < in.Items.size(); j++){
        if(in.Items.get(j).getType() == buttonList.get(selectedButtons.get(i)).getText()){
          sum+= in.Items.get(j).getValue();
        }
      }
    }
    if(stringShopCounterLabel.getText() == "Buy:"){
      sum = sum*2;
    }
    intShopCounterLabel.setText(""+sum);
  }
  public UI(Game g) {
    game = g;
  }
  public void addPlayer(Player p) {
    player = p;
  }
  public void addGameInventory(GameInventory ga) {
    in = ga;
  }
  public void addEncounter(Encounter e) {
    en = e;
  }

  public void addMap(Map m) {
    map = m;
  }

  public void createUI(Game.ChoiceHandler cHandler){
    //window
    window = new JFrame();
    window.setTitle("Game");
    window.setSize(900, 700);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.getContentPane().setBackground(Color.black);
    window.setLayout(null);
    window.setVisible(true);
    iconImg = new ImageIcon("localFiles/img/icons/gameicon.png");
    window.setIconImage(iconImg.getImage());
    con = window.getContentPane();

    //titalscreen
    titleNamePanel = new JPanel();
    titleNamePanel.setBounds(100, 100, 700, 150);
    titleNamePanel.setBackground(Color.black);
    con.add(titleNamePanel);

    titleNameLabel = new JLabel("GAME");
    titleNameLabel.setForeground(Color.white);
    titleNameLabel.setFont(titleFont);
    titleNamePanel.add(titleNameLabel);

    startButtonPanel = new JPanel();
    startButtonPanel.setBounds(300, 400, 300, 100);
    startButtonPanel.setBackground(Color.black);
    con.add(startButtonPanel);

    startButton = new JButton("START");
    startButton.setBackground(Color.black);
    startButton.setForeground(Color.white);
    startButton.setFont(normalFont);
    startButton.setFocusPainted(false);
    startButton.addActionListener(cHandler);
    startButton.setActionCommand("start");
    startButtonPanel.add(startButton);

    updateButtonPanel = new JPanel();
    updateButtonPanel.setBounds(50, 550, 120, 50);
    updateButtonPanel.setBackground(Color.black);
    con.add(updateButtonPanel);

    updateButton = new JButton("Update");
    updateButton.setBackground(Color.black);
    updateButton.setForeground(Color.white);
    updateButton.setFont(normalFont);
    updateButton.setFocusPainted(false);
    updateButton.addActionListener(cHandler);
    updateButton.setActionCommand("update");
    updateButtonPanel.add(updateButton);

    //name input
    inputTextPanel = new JPanel();
    inputTextPanel.setBounds(375, 300, 150, 100);
    inputTextPanel.setBackground(Color.black);
    con.add(inputTextPanel);

    inputTextField = new JTextField("enter name");
    inputTextField.setBounds(375, 300, 200, 100);
    inputTextField.setBackground(Color.black);
    inputTextField.setForeground(Color.white);
    inputTextField.setBorder(null);
    inputTextField.setFont(normalFont);
    inputTextField.setEditable(true);
    inputTextField.addActionListener(cHandler);
    inputTextField.setActionCommand("makePlayer");
    inputTextPanel.add(inputTextField);


    attackLabel = new JLabel();
    con.add(attackLabel);

    encounterImgLabel = new JLabel();
    con.add(encounterImgLabel);

    structureImgLabel = new JLabel();
    con.add(structureImgLabel);

    roomImgLabel = new JLabel();
    con.add(roomImgLabel);

    mainTextPanel = new JPanel();
    mainTextPanel.setBounds(10, 420, 500, 220);
    mainTextPanel.setBackground(Color.black);
    con.add(mainTextPanel);

    mainTextArea = new JTextArea("Replace 'enter here' with your player name, if the name has a saved game on this computer, you will continue an old run. When you are ready press enter");
    mainTextArea.setBounds(10, 420, 500, 220);
    mainTextArea.setBackground(Color.black);
    mainTextArea.setForeground(Color.white);
    mainTextArea.setFont(normalFont);
    mainTextArea.setLineWrap(true);
    mainTextArea.setWrapStyleWord(true);
    mainTextArea.setEditable(false);
    mainTextPanel.add(mainTextArea);

    //statPanel
    statPanel = new JPanel();
    statPanel.setBounds(560, 50, 280, 380);
    statPanel.setBackground(Color.black);
    statPanel.setLayout(new GridLayout(9, 2, 0, 0));
    statPanel.setBorder(BorderFactory.createLineBorder(Color.white, 2));
    con.add(statPanel);

    for(int i = 0; i < 18; i++) {
      JLabel label;
      if(i % 2 == 0){
        label = new JLabel("", SwingConstants.LEFT);
        label.setBorder(new EmptyBorder(0,10,0,0));
      } else{
        label = new JLabel("", SwingConstants.RIGHT);
        label.setBorder(new EmptyBorder(0,0,0,10));
      }
      label.setBackground(Color.black);
      label.setForeground(Color.white);
      label.setFont(normalFont);
      statLabelList.add(label);
      statPanel.add(label);
    }

    //choice buttons
    choiceButtonPanel = new JPanel();
    choiceButtonPanel.setBounds(550, 50, 300, 375);
    choiceButtonPanel.setBackground(Color.black);
    choiceButtonPanel.setLayout(new GridLayout(6, 2, 10, 10));
    con.add(choiceButtonPanel);

    for(int i = 0; i < 12; i++) {
      JButton button = new JButton(null, null);
      if(i==10){
        button.setLayout(new GridLayout(1, 2));

        stringShopCounterLabel = new JLabel("", SwingConstants.CENTER);
        stringShopCounterLabel.setForeground(Color.white);
        stringShopCounterLabel.setFont(normalFont);
        button.add(stringShopCounterLabel);

        intShopCounterLabel = new JLabel("", SwingConstants.CENTER);
        intShopCounterLabel.setForeground(Color.white);
        intShopCounterLabel.setFont(normalFont);
        button.add(intShopCounterLabel);
      }
      button.setBackground(Color.black);
      button.setForeground(Color.white);
      button.setFont(normalFont);
      button.setBorder(BorderFactory.createLineBorder(Color.white, 1));
      button.setFocusPainted(false);
      button.addActionListener(cHandler);
      button.setActionCommand("c"+i);
      buttonList.add(button);
      choiceButtonPanel.add(button);
    }

    //itemInfoPanel
    itemInfoPanel = new JPanel();
    itemInfoPanel.setBounds(550, 435, 300, 100);
    itemInfoPanel.setBackground(Color.black);
    itemInfoPanel.setLayout(new GridLayout(3, 2, 0, 0));
    itemInfoPanel.setBorder(BorderFactory.createLineBorder(Color.red, 1));
    con.add(itemInfoPanel);

    for(int i = 0; i < 6; i++) {
      JLabel label;
      if(i % 2 == 0){
        label = new JLabel("", SwingConstants.LEFT);
        label.setBorder(new EmptyBorder(0,10,0,0));
      } else{
        label = new JLabel("", SwingConstants.RIGHT);
        label.setBorder(new EmptyBorder(0,0,0,10));
      }
      label.setBackground(Color.black);
      label.setForeground(Color.white);
      label.setFont(normalFont);
      itemInfoLabelList.add(label);
      itemInfoPanel.add(label);
    }

    playerPosLabel = new JLabel();
    con.add(playerPosLabel);

    mapPanel = new JPanel();
    mapPanel.setBounds(625, 440, 200, 200);
    mapPanel.setBackground(Color.black);
    mapPanel.setLayout(new GridLayout(10, 10, 0, 0));
    // mapPanel.setBorder(BorderFactory.createLineBorder(Color.white, 1));
    con.add(mapPanel);


    for(int i = 0; i < 100; i++) {
      JLabel label = new JLabel();
      mapLabelList.add(label);
      mapPanel.add(label);
    }
    //setVisible
    con.setVisible(false);
    con.setVisible(true);
    
  }

  public void drawMap(){
    for (int i = 0; i < mapLabelList.size(); i++) {
      mapLabelList.get(i).setIcon(new ImageIcon("localFiles/img/miniMap/"+mapString.get((int)Math.floor(i/10)).get(i % 10)+".png"));
      // mapLabelList.get(i).setIcon(new ImageIcon("localFiles/img/miniMap/"+map.maps.get(map.floor-1).get((int)Math.floor(i/10)).get(i % 10)+".png"));
    }
  }

  public void drawplayer(){
    playerPosLabel.setIcon(new ImageIcon("localFiles/img/miniMap/"+ map.getFacing() + "Player.png"));
    playerPosImgSize = playerPosLabel.getPreferredSize(); 
    playerPosLabel.setBounds(625 + (map.x * 20), 440 + (map.y * 20), playerPosImgSize.width, playerPosImgSize.height);
  }

    public void drawRoom(String file){
    roomImgLabel.setIcon(new ImageIcon("localFiles/img/rooms/"+file+".png"));
    roomImgSize = roomImgLabel.getPreferredSize(); 
    roomImgLabel.setBounds(10, 10, roomImgSize.width, roomImgSize.height);
  }

  public void drawStructure(String file){
    structureImgLabel.setIcon(new ImageIcon("localFiles/img/structure/"+file+".png"));
    structureImgSize = structureImgLabel.getPreferredSize(); 
    structureImgLabel.setBounds(10, 10, structureImgSize.width, structureImgSize.height);
  }

  public void drawEncounter(String category, String file){
    encounterImgLabel.setIcon(new ImageIcon("localFiles/img/"+category+"/"+file));
    encounterImgSize = encounterImgLabel.getPreferredSize(); 
    encounterImgLabel.setBounds(10, 10, encounterImgSize.width, encounterImgSize.height);
  }

  public void drawAttack(){
    attackLabel.setIcon(new ImageIcon("localFiles/img/attack.gif"));
    attackImgSize = attackLabel.getPreferredSize(); 
    attackLabel.setBounds(10, 10, attackImgSize.width, attackImgSize.height);
    Timer timer = new Timer(533, (ActionListener) new ActionListener() {
      public void actionPerformed(ActionEvent e){
        attackLabel.setIcon(new ImageIcon(""));
        if(en.newMonsterHealth <= 0){
          drawEncounter("", "");
        }
      }
    }); timer.setRepeats(false);
    timer.start();  
  }
}