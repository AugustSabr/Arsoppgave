import java.util.ArrayList;

import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Encounter {
  private Game game;
  private UI ui;
  private Item i;
  private _KeyListener key;
  private Map map;
  private GameInventory in;
  private VisibilityManager vm;

  private Player player;
  private int randomEncounter, shopWeapons, shopArmors, shopItems;
  public int newMonsterHealth;
  private Item newItem;
  private Weapon newWeapon;
  private Armor newArmor;
  public Monster newMonster;

  public String buttonPanalUse = "inventory";

  ArrayList<Object> shopInventory = new ArrayList<Object>();
  
  public String getshopInventoryString(int i) {
    if(shopInventory.get(i).getClass() == Weapon.class){
      Weapon weapon = (Weapon) shopInventory.get(i);
      return weapon.getType();
    }
    if(shopInventory.get(i).getClass() == Armor.class){
      Armor armor = (Armor) shopInventory.get(i);
      return armor.getType();
    }
    if(shopInventory.get(i).getClass() == Item.class){
      Item item = (Item) shopInventory.get(i);
      return item.getType();
    }
    return null;
  }


  private String[] shopSmalltalk = {"<shopkeeper>\nCan I help you with something specific, or are you just here to waste my time?", "<shopkeeper>\nIf you're not going to buy anything, please don't touch the merchandise.", "<shopkeeper>\nI don't have all day to stand here and chat. If you're not going to make a purchase, I suggest you leave.", "<shopkeeper>\nIf you don't know what you want, maybe you should come back when you do.", "<shopkeeper>\nI'm sorry, but I'm not in the mood for small talk right now. Is there something specific you're looking for?"};
  
  public Encounter(UI u, Player p, GameInventory gi, VisibilityManager v, Game g) {
    game = g;
    ui = u;
    in = gi;
    vm = v;

    player = p;
    updateStats();
  }

  public void addKeyListener(_KeyListener k) {
    key = k;
    key.c = "openInventory";
  }
  public void addMap(Map m){
    map = m;
    updateShop();
  }

  public void selectPosition(String nextPosition){
    switch(nextPosition){
      case "getItem": getItem(); break;
      case "checkDoor": checkDoor(); break;
      case "openDoor": openDoor(); break;
      case "goThruDoor": goThruDoor(); break;
      case "talkToShopkeeper": talkToShopkeeper(); break;
      case "buyFromShop": buyFromShop(); break;
      case "selectItemsToSell": selectItemsToSell(); break;
      case "leaveShop": leaveShop(); break;
      case "sellToShop": sellToShop(); break;
      case "selectItemsToBuy": selectItemsToBuy(); break;
      case "shopSmalltalk": shopSmalltalk(); break;
      case "openInventory": openInventory(); break;
      case "closeInventory": closeInventory(); break;
      case "getWeapon": getWeapon(); break;
      case "drop": drop(); break;
      case "equipWeapon": equipWeapon(); break;
      case "getArmor": getArmor(); break;
      case "equipArmor": equipArmor(); break;
      case "attackPlayer": attackPlayer(); break;
      case "attackMonster": attackMonster(); break;
      case "heal": heal(); break;
      case "escape": escape(); break;
      case "hole": hole(); break;
      case "jumpIntoHole": jumpIntoHole(); break;
      // case "lootArmor": lootArmor(); break;
      // case "escapeRoll": escapeRoll(); break;
      // case "run": run(); break;
      // case "DrinkMaxHealthPotion": DrinkMaxHealthPotion(); break;
      // case "acceptBlessing": acceptBlessing(); break;
    }
    updateStats();
  }

  public void newEncounter(){
    this.randomEncounter = (int)Math.floor(Math.random()*100);

    ui.drawEncounter("","");
    if(randomEncounter < 10*(1+((player.getLuck()-1)/10))){
      foundItem();
    }else if(randomEncounter < 20*(1+((player.getLuck()-1)/10))){
      foundWeapon();
    }else if(randomEncounter < 30*(1+((player.getLuck()-1)/10))){
      foundArmor();
    }else if(randomEncounter < 30*(1+((player.getLuck()-1)/10)) + 2.5*(1+((player.getLuck()-1)/10))){
      //blessing
    }else if(randomEncounter > 90){
      encounterMonster();
    }
  }

  public void noEncounter(){
    ui.drawEncounter("","");
  }

  public void updateStats(){
    int i = 0;
    ui.statLabelList.get(i).setText("Username:");i++;
    ui.statLabelList.get(i).setText(player.getName());i++;
    ui.statLabelList.get(i).setText("HP:");i++;
    ui.statLabelList.get(i).setText(player.getHealth()+"/"+player.getMaxHealth());i++;
    ui.statLabelList.get(i).setText("EXP:");i++;
    ui.statLabelList.get(i).setText(player.getExp()+"");i++;
    ui.statLabelList.get(i).setText("Gold:");i++;
    ui.statLabelList.get(i).setText(player.getGold()+"");i++;
    ui.statLabelList.get(i).setText("Inventory:");i++;
    ui.statLabelList.get(i).setText(player.inventory.size()+"/10");i++;
    ui.statLabelList.get(i).setText("Strength:");i++;
    ui.statLabelList.get(i).setText(player.getTotalDamage()+"");i++;
    ui.statLabelList.get(i).setText("Speed:");i++;
    ui.statLabelList.get(i).setText(player.getSpeed()+"");i++;
    ui.statLabelList.get(i).setText("Endurance:");i++;
    ui.statLabelList.get(i).setText(player.getTotalDefence()+"");i++;
    ui.statLabelList.get(i).setText("Luck:");i++;
    ui.statLabelList.get(i).setText(player.getLuck()+"");
  }

  private void openInventory(){
    vm.showchoiceButtons();
    ui.selectedButtons.clear();
    for(int i = 0; i < ui.buttonList.size()-2; i++){
      ui.buttonList.get(i).setText(null);
    }
    for(int i = 0; i < player.inventory.size(); i++){
      ui.buttonList.get(i).setText(player.getInventoryString(i));
    }
    ui.drawButtons();
    ui.buttonList.get(11).setText("Drop");
    int i = 0;
    ui.itemInfoLabelList.get(i).setText("");i++;
    ui.itemInfoLabelList.get(i).setText("");i++;
    ui.itemInfoLabelList.get(i).setText("");i++;
    ui.itemInfoLabelList.get(i).setText("");i++;
    ui.itemInfoLabelList.get(i).setText("");i++;
    ui.itemInfoLabelList.get(i).setText("");
    game.c11 = "drop";
    key.c = "closeInventory";
  }

  private void closeInventory(){
    vm.dontShowchoiceButtons();
    game.c11 = "";
    key.c = "openInventory";
  }

  private void drop(){
    if(ui.selectedButtons.size() != 0){
      if(player.inventory.get((int)ui.selectedButtons.get(0)) == player.getMyWeapon()){
      player.setMyWeapon(null);
      }
      if(player.inventory.get((int)ui.selectedButtons.get(0)) == player.getMyArmor()){
        player.setMyArmor(null);
        }
      player.inventory.remove((int)ui.selectedButtons.get(0));
    }
    openInventory();
  }

  private void equipWeapon(){
    for(int i = 0; i < player.inventory.size(); i++){
      if(player.getInventoryString(i) == ui.buttonList.get((int)ui.selectedButtons.get(0)).getText()){
        player.setMyWeapon((Weapon)player.inventory.get(ui.selectedButtons.get(0)));
        break;
      }
    }
    openInventory();
  }

  private void equipArmor(){
    for(int i = 0; i < player.inventory.size(); i++){
      if(player.getInventoryString(i) == ui.buttonList.get((int)ui.selectedButtons.get(0)).getText()){
        player.setMyArmor((Armor)player.inventory.get(ui.selectedButtons.get(0)));
        break;
      }
    }
    openInventory();
  }

  private void heal(){
    player.heal();
    ui.mainTextArea.setText("You drank the potion, and are now at full health ");
    player.inventory.remove((int)ui.selectedButtons.get(0));
    openInventory();
  }

  
  public void hole(){
    ui.mainTextArea.setText("You see a hole to a lower floor");
    key.z = "jumpIntoHole";
  }

  private void jumpIntoHole(){
    map.createFloor();
    map.floor++;
    map.draw();
    ui.drawMap();

    // Timer timer = new Timer(20, (ActionListener) new ActionListener() {
      // public void actionPerformed(ActionEvent e){
        // map.createFloor();
        // map.floor++;
        // map.draw();
        // ui.drawMap();
      // }
    // }); timer.setRepeats(true);
    // timer.start();
  }

  private void foundItem(){
    newItem = in.getItem(-1);
    ui.drawEncounter("items", newItem.getPath());
    ui.mainTextArea.setText("You see a "+newItem.getType());
    key.z = "getItem";
  }

  private void getItem(){
    if(player.inventory.size() >= 10){
      ui.mainTextArea.setText("you carry to many items");
    } else {
      player.inventory.add(newItem);
      ui.mainTextArea.setText(newItem.getType()+" is now in youre inventory");
      ui.drawEncounter("","");
      key.z = "";
    }
  }

  private void foundWeapon(){
    newWeapon = in.getWeapon(-1);
    ui.drawEncounter("weapons", newWeapon.getPath());
    ui.mainTextArea.setText("You see a "+ newWeapon.getType());
    key.z = "getWeapon";
  }

  private void getWeapon(){
    if(player.inventory.size() >= 10){
      ui.mainTextArea.setText("you carry to many items");
    } else {
      player.inventory.add(newWeapon);
      ui.mainTextArea.setText(newWeapon.getType()+" is now in youre inventory");
      ui.drawEncounter("","");
      key.z = "";
    }
  }

  private void foundArmor(){
    newArmor = in.getArmor(-1);
    ui.drawEncounter("armors", newArmor.getPath());
    ui.mainTextArea.setText("You see a "+ newArmor.getType());
    key.z = "getArmor";
  }

  private void getArmor(){
    if(player.inventory.size() >= 10){
      ui.mainTextArea.setText("you carry to many items");
    } else {
      player.inventory.add(newArmor);
      ui.mainTextArea.setText(newArmor.getType()+" is now in youre inventory");
      ui.drawEncounter("","");
      key.z = "";
    }
  }

  private void encounterMonster(){
    newMonster = in.getMonster(-1);
    newMonsterHealth = newMonster.getHealth();
    ui.mainTextArea.setText(player.getName() + " encountered a " + newMonster.getName());
    ui.drawEncounter("monsters", newMonster.getPath());
    key.inCombat = true;
    if(player.getSpeed() >= newMonster.getSpeed()){
      turnPlayer();
    } else {
      key.z = "attackMonster";
    }
    
  }

  private void turnPlayer(){
    key.z = "attackPlayer";
    key.x = "escape";
  }

  private void attackPlayer(){
    key.x = "";
    int damage = player.getTotalDamage();

    damage = Math.max(0, damage - newMonster.getEndurance());
    newMonsterHealth -= damage;
    ui.drawAttack();
    if(newMonsterHealth > 0){
      ui.mainTextArea.setText(player.getName() + " attacks the " + newMonster.getName() + " and deals " + damage + " damage");

      key.z = "attackMonster";

    }else{
      // player.addGold(enemy.getBaseDamage());
      // ui.goldIntLabel.setText("" + player.getGold());
      ui.mainTextArea.setText(player.getName() + " attacks the " + newMonster.getName() + " and deals " + damage + " damage\nThe " + newMonster.getName() + " was slayed");
      key.inCombat = false;
      key.z = "";
    }
  }

  private void attackMonster(){
    int playerHealth = player.getHealth();
    int damage = (int)Math.max(0, newMonster.getTotalDamage()* 1-(player.getTotalDefence()/100.0));
    playerHealth -=  damage;
    playerHealth = Math.max(0, playerHealth);
    player.changeHealth(playerHealth);
    if(playerHealth > 0){
      ui.mainTextArea.setText("The " + newMonster.getName() + " attacks the " + player.getName() + " and deals " + damage + " damage.");
      turnPlayer();
    }else{// death screen
      ui.mainTextArea.setText("The " + newMonster.getName() + " attacks the " + player.getName() + " and deals " + damage + " damage.\n" + player.getName() + " was slayed.\nGame over\n\n\n\n\n:(");
      
      key.z = "";
      key.x = "";
      key.c = "";
    }
  }

  private void escape(){
    key.x = "";
    if(player.inventory.size() == 0 && (int)Math.floor(Math.random()*2) == 0){
      ui.mainTextArea.setText("The " + newMonster.getName() + " has its guard down, run quickly");
      key.inCombat = false;
      Timer timer = new Timer(3000, (ActionListener) new ActionListener() {
        public void actionPerformed(ActionEvent e){
          if(newMonster != null){
            ui.mainTextArea.setText("You took to long");
            key.inCombat = true;
            key.z = "attackPlayer";
          }
        }
      }); timer.setRepeats(false);
      timer.start(); 
    }
    if(player.getSpeed() * (1+Math.floor(Math.random()*10)) > newMonster.getSpeed() * (1+Math.floor(Math.random()*10))){
      ui.mainTextArea.setText("The " + newMonster.getName() + " has its guard down, run quickly");
      key.inCombat = false;
      Timer timer = new Timer(500, (ActionListener) new ActionListener() {
        public void actionPerformed(ActionEvent e){
          if(newMonster != null){
            ui.mainTextArea.setText("You took to long");
            key.inCombat = true;
            key.z = "attackPlayer";
          }
        }
      }); timer.setRepeats(false);
      timer.start(); 
    } else {
      ui.mainTextArea.setText("You looked for an opening to escape, but the " + newMonster.getName() + " was ready for your attempt");
      key.z = "attackPlayer";
    }
  }

  private void checkDoor(){
    ui.mainTextArea.setText("You try to open the door\nIt is locked. Maybe you can open it using a key?");
    for (int i = 0; i < player.inventory.size(); i++) {
      if(player.getInventoryString(i).equals("key")){
        key.z = "openDoor";
        break;
      }
    }
  }

  private void openDoor(){
    ui.drawStructure("openDoor");
    map.structureLayouts.get(map.floor-1).get(map.y).set(map.x, "q");
    ui.mainTextArea.setText("You open door using a key");
    for (int i = 0; i < player.inventory.size(); i++) {
      if(player.getInventoryString(i).equals("key")){
        player.inventory.remove(i);
        break;
      }
    }

    key.z = "goThruDoor";
  }

  private void goThruDoor(){
    switch(map.getFacing()){
      case "north": map.y=map.y-1; map.draw(); break;
      case "south": map.y++; map.draw(); break;
      case "east": map.x++; map.draw(); break;
      case "west": map.x=map.x-1; map.draw(); break;
    }
  }

  private void updateShop(){
    shopWeapons = (int)Math.floor(Math.random()*3) + 1;
    shopArmors = (int)Math.floor(Math.random()*3) + 1;
    shopItems = (int)Math.floor(Math.random()*3) + 1;

  
    for(int i = 0; i < shopWeapons; i++){
      shopInventory.add(in.getWeapon(-1));
    }
    for(int i = 0; i < shopArmors; i++){
      shopInventory.add(in.getArmor(-1));
    }
    for(int i = 0; i < shopItems; i++){
      shopInventory.add(in.getItem(-1));
    }
  }
  private void talkToShopkeeper(){
    ui.mainTextArea.setText("<shopkeeper>\nWhat do you want?\n\n[z]: buy     [x]: sell     [c]: small talk");
    key.z = "selectItemsToBuy";
    key.x = "selectItemsToSell";
    key.c = "shopSmalltalk";
    game.c11 = "leaveShop";
    ui.buttonList.get(10).setText("");
  }
  private void shopSmalltalk(){
    ui.mainTextArea.setText(shopSmalltalk[(int)Math.floor(Math.random()*shopSmalltalk.length)]);
    key.z = "selectItemsToBuy";
    key.x = "selectItemsToSell";
    key.c = "shopSmalltalk";
  }

  private void selectItemsToBuy(){
    buttonPanalUse = "shopBuy";
    ui.mainTextArea.setText("<shopkeeper>\nEverything has a price. I can't just give things away for free?");
    game.c10 = "buyFromShop";
    ui.selectedButtons.clear();
    ui.chageButton(null);
    vm.showchoiceButtons();
    for(int i = 0; i < ui.buttonList.size()-2; i++){
      ui.buttonList.get(i).setText(null);
    }
    for(int i = 0; i < shopInventory.size(); i++){
      ui.buttonList.get(i).setText(getshopInventoryString(i));
    }
    ui.stringShopCounterLabel.setText("Buy:");
    ui.updateSellCounter();
    ui.buttonList.get(11).setText("Leave shop");
    key.z = "";
    key.x = "";
  }

  private void buyFromShop(){

    if(player.getGold() >= Integer.parseInt(ui.intShopCounterLabel.getText())){
      if(player.inventory.size() + ui.selectedButtons.size() <= 10){
        // ui.mainTextArea.setText("<shopkeeper>\nIts a good price, don't you think?");
        for (int i = shopInventory.size()-1; i >= 0; i--) {
          for(int j = 0; j < ui.selectedButtons.size(); j++){
            if(getshopInventoryString(i).equals(ui.buttonList.get(ui.selectedButtons.get(j)).getText())){
              ui.selectedButtons.remove(j);
              player.inventory.add(shopInventory.get(i));
              shopInventory.remove(i);
              break;
            }
          }
        }
        player.subtractGold(Integer.parseInt(ui.intShopCounterLabel.getText()));
        selectItemsToBuy();
      } else {
        ui.mainTextArea.setText("<shopkeeper>\nYou can't carry all this stuff, maybe you want to sell me sothing?");
      }
    } else {
      ui.mainTextArea.setText("<shopkeeper>\nLooks like youre a little short on coin. Don't waste my time");
    }
  }

  private void selectItemsToSell(){
    buttonPanalUse = "shopSell";
    ui.mainTextArea.setText("<shopkeeper>\nWhat do you want to sell?");
    game.c10 = "sellToShop";
    vm.showchoiceButtons();
    ui.selectedButtons.clear();
    ui.chageButton(null);
    for(int i = 0; i < ui.buttonList.size()-2; i++){
      ui.buttonList.get(i).setText(null);
    }
    for(int i = 0; i < player.inventory.size(); i++){
      ui.buttonList.get(i).setText(player.getInventoryString(i));
    }
    
    ui.stringShopCounterLabel.setText("Sell:");
    ui.updateSellCounter();
    ui.buttonList.get(11).setText("Leave shop");
    key.z = "";
    key.x = "";
  }

  private void sellToShop(){
    // ui.mainTextArea.setText("<shopkeeper>\nWhat a steal.");
    for (int i = player.inventory.size()-1; i >= 0; i--) {
      for(int j = 0; j < ui.selectedButtons.size(); j++){
        if(player.getInventoryString(i).equals(ui.buttonList.get(ui.selectedButtons.get(j)).getText())){
          if(player.inventory.get(i) == player.getMyWeapon()){
            player.setMyWeapon(null);
          }
          if(player.inventory.get(i) == player.getMyArmor()){
            player.setMyArmor(null);
          }
          ui.selectedButtons.remove(j);
          player.inventory.remove(i);
          break;
        }
      }
    }
    player.addGold((int)Integer.parseInt(ui.intShopCounterLabel.getText()));
    selectItemsToSell();
  }

  private void leaveShop(){
    ui.mainTextArea.setText("<shopkeeper>\nbye");
    vm.dontShowchoiceButtons();
    key.z = "talkToShopkeeper";
    key.x = "";
    key.c = "openInventory";
    game.c10 = "";
    game.c11 = "";
    buttonPanalUse = "inventory";
    ui.stringShopCounterLabel.setText("");
    ui.intShopCounterLabel.setText("");
  }
}
