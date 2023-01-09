import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
public class Room {
  private String[] roomTypes = {"dark", "bright", "clean", "quiet", "big", "small", "familiar", "different", "new", "green", "old", "scarry", "beautiful", "haunted", "pretty", "strange", "cursed", "blody"};
  private String roomType, importantchoice1, importantchoice2;// importantchoice er egtlig en midlertidig løsning
  private static int dungeonFloor = 1;  
  private int
  randomEncounter,
  min = 0,
  max = roomTypes.length - 1,
  enemyHealth,
  enemyDefence,
  enemyDamage;

  private Player player;
  private Weapon newWeapon;
  private Armor newArmor;
  private Blessing blessing;
  private Enemy enemy;

  private Game game;
  private UI ui;
  private VisibilityManager vm;
  private GameInventory in;

  public static int getDungeonFloor(){
    return dungeonFloor;
  }

  public Room(Game g, UI u, VisibilityManager v, GameInventory i){
    game = g;
    ui = u;
    vm = v;
    in = i;
  }

  //kalles vær gang man går til et nytt rom. gir et unikt adjectiv til rommet, og bestemmer hva som er i det
  private void newRoom(){
    int randomtype = (int)(Math.floor(Math.random()*(max-min+1)+min));
    this.randomEncounter = (int)Math.floor(Math.random()*6);
    roomType = roomTypes[randomtype] + " room.";
  }

  //dette er funksjonen som blir kallet av ChoiceHandler, og som bestemmer hva som sjer i og mellom rom
  public void selectPosition(String nextPosition){
    switch(nextPosition){
      case "new room":
        newRoom();
        switch(this.randomEncounter){
          case 0: foundHealingPotion(); break;
          case 1: foundWeapon(); break;
          case 2: foundArmor(); break;
          case 3: encounteredEnemy(); break;
          case 4: 
            if (Room.dungeonFloor >= 2 && player.getMaxHealth() == 100 && (int)(Math.floor(Math.random()*2)) == 0) {
              maxHealthPotion();
            } else {
              encounteredEnemy();
            }
            break;
          case 5: 
            if (Room.dungeonFloor >= 2 && player.getBlessing() == null && (int)(Math.floor(Math.random()*2)) == 0) {
              foundBlessing();
            } else {
              encounteredEnemy();
            }
            break;
          }
        break;
      case "make player": makePlayer(); break;
      case "doorway": doorway(); break;
      case "new floor": newFloor(); break;
      case "save": save(); break;
      case "stats": stats(); break;
      case "heal": heal(); break;
      case "upgrade": upgrade(); break;
      case "upgradeWeapon": upgradeWeapon(); break;
      case "upgradeArmor": upgradeArmor(); break;
      case "getHealingPotion": getHealingPotion(); break;
      case "getWeapon": getWeapon(); break;
      case "dontGetWeapon": dontGetWeapon(); break;
      case "getArmor": getArmor(); break;
      case "dontGetArmor": dontGetArmor(); break;
      case "attackRoll": attackRoll(); break;
      case "attackPlayer": attackPlayer(); break;
      case "attackEnemy": attackEnemy(); break;
      case "lootEnemy": lootEnemy(); break;
      case "lootWeapon": lootWeapon(); break;
      case "lootArmor": lootArmor(); break;
      case "escapeRoll": escapeRoll(); break;
      case "run": run(); break;
      case "DrinkMaxHealthPotion": DrinkMaxHealthPotion(); break;
      case "acceptBlessing": acceptBlessing(); break;

      case "funRoll": funRoll(); break;//gjør egt ingenting, kun visuelt
    }
  }

  public void funRoll(){
    ui.diceTextField.setText("" + (int)Math.floor(Math.random()*(25)));
  }

  //sjekker om navnet du skrev inn alerede har en lokal lagret spiller
  public void makePlayer(){
    player = Game.getPlayer();
    try {
      ObjectInputStream input = new ObjectInputStream(new FileInputStream("saves/" + ui.inputTextField.getText() + ".dat"));
      player = (Player) input.readObject();
      input.close();
      ui.mainTextArea.setText(player.getName() + " is back for more");
    } catch (Exception e){
      player = new Player(ui.inputTextField.getText());
      ui.mainTextArea.setText(player.getName() + " started a new game");

      System.err.println("Couldnt load game. Created new player.");
      System.err.println("Error message: " + e + "\n");
      // e.printStackTrace();
    }
    Start();
  }

  private void Start(){
    vm.showGamescreen();
    ui.floorLabelInt.setText("" + dungeonFloor);
    ui.goldLabelInt.setText("" + player.getGold());
    ui.hpLabelInt.setText(player.getHealth() + "/" + player.getMaxHealth());

    ui.choice1.setText("Enter the dungon");
    ui.choice2.setText("Look at stats");
    ui.choice3.setText("");

    game.nextPosition1 = "new room";
    game.nextPosition2 = "stats";
    game.nextPosition3 = "";

    importantchoice1 = ui.choice1.getText();
    importantchoice2 = game.nextPosition1;
  }

  private void doorway(){
    if(Room.dungeonFloor < 3 && Math.floor(Math.random()*10) == 0){// liten sjanse for å kunne gå ned til et farligere nivå
      ui.mainTextArea.setText(player.getName() + " found a stairway down to a higher floor.");
      
      ui.choice1.setText("Go up");
      ui.choice2.setText("Stay on this floor");
      ui.choice3.setText("");

      game.nextPosition1 = "new floor";
      game.nextPosition2 = "doorway";
      game.nextPosition3 = "";
    } else {
      ui.mainTextArea.setText("Ready for a new room?");

      ui.choice1.setText("Enter a new room");
      ui.choice2.setText("Look at stats");
      ui.choice3.setText("Save player");
  
      game.nextPosition1 = "new room";
      game.nextPosition2 = "stats";
      game.nextPosition3 = "save";
  
      importantchoice1 = ui.choice1.getText();
      importantchoice2 = game.nextPosition1;
    }
  }

  private void newFloor(){
    Room.dungeonFloor += 1;
    ui.floorLabelInt.setText("" + dungeonFloor);
    ui.mainTextArea.setText(player.getName() + " went up to a floor.\nIts more dangerous here");

    ui.choice1.setText(">");
    ui.choice2.setText("");
    ui.choice3.setText("");

    game.nextPosition1 = "doorway";
    game.nextPosition2 = "";
    game.nextPosition3 = "";
  }

  // lagrer spilleren
  private void save(){
    try {
      ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("saves/" + player.getName() + ".dat"));
      output.writeObject(player);
      output.close();

      ui.mainTextArea.setText(player.getName() + " is saved");

      ui.choice1.setText(">");
      ui.choice2.setText("");
      ui.choice3.setText("");
  
      game.nextPosition1 = "doorway";
      game.nextPosition2 = "";
      game.nextPosition3 = "";
    } catch (Exception e){
      ui.mainTextArea.setText("Couldnt save, check console for details");
      System.err.println("Couldnt save");
      System.err.println("Error message: " + e + "\n");
      // e.printStackTrace();
      
      ui.choice1.setText("continue anyway");
      ui.choice2.setText("");
      ui.choice3.setText("");
  
      game.nextPosition1 = "doorway";
      game.nextPosition2 = "";
      game.nextPosition3 = "";
    }
  }

  private void stats(){
    ui.choice1.setText(importantchoice1);
    game.nextPosition1 = importantchoice2;

    if (player.getWeapon() != null && player.getArmor() != null){
      ui.mainTextArea.setText("Healing potions: " + player.getPotions() + "\nBase damage: " + player.getBaseDamage() + "\n\nWeapon: " + player.getWeapon().getType() + "\nWeapon damage: " + player.getWeapon().getDamage() + "\n\nArmor: " + player.getArmor().getType() + "\nDefence: " + player.getTotalDefence() + "\n\nTotal damage: " + player.getTotalDamage());
    } else if (player.getWeapon() != null){
      ui.mainTextArea.setText("Healing potions: " + player.getPotions() + "\nBase damage: " + player.getBaseDamage() + "\n\nWeapon: " + player.getWeapon().getType() + "\nWeapon damage: " + player.getWeapon().getDamage() + "\n\nArmor:\nDefence: " + player.getTotalDefence() + "\n\nTotal damage: " + player.getTotalDamage());
    } else if (player.getArmor() != null){
      ui.mainTextArea.setText("Healing potions: " + player.getPotions() + "\nBase damage: " + player.getBaseDamage() + "\n\nWeapon:\nWeapon damage:\n\nArmor: " + player.getArmor().getType() + "\nDefence: " + player.getTotalDefence() + "\n\nTotal damage: " + player.getTotalDamage());
    } else {
      ui.mainTextArea.setText("Healing potions: " + player.getPotions() + "\nBase damage: " + player.getBaseDamage() + "\n\nWeapon:\nWeapon damage:\n\nArmor:\nDefence: " + player.getTotalDefence() + "\n\nTotal damage: " + player.getTotalDamage());
    }

    ui.choice2.setText("Use healing potion");
    game.nextPosition2 = "heal";

    if(player.getGold() >= 150){
      ui.choice3.setText("upgrade gear");
      game.nextPosition3 = "upgrade";
    } else {
      ui.choice3.setText("");
      game.nextPosition3 = "";
    }
  }

  private void heal(){
    if (player.getPotions() > 0){
      player.heal();
      ui.mainTextArea.setText(player.getName() + " is back to " + player.getHealth() + " health.\n" + player.getName() + " has " + player.getPotions() + " healing potions left.\n");
      ui.hpLabelInt.setText(player.getHealth() + "/" + player.getMaxHealth());
    } else {
      ui.mainTextArea.setText(player.getName() + " dont have enough healing potions");
    }

    ui.choice2.setText("Back to stats");
    ui.choice3.setText("");

    game.nextPosition2 = "stats";
    game.nextPosition3 = "";
  }

  private void upgrade(){
    ui.mainTextArea.setText("You have 150 gold or more. Do you want to spend 150 reinforcing some gear?\nDo you want to upgrade your weapon: " + player.getWeapon().getType() + "\nOr youre Armor: " + player.getArmor().getType());

    ui.choice1.setText("Weapon");
    ui.choice2.setText("Armor");
    ui.choice3.setText("Back to stats");

    game.nextPosition1 = "upgradeWeapon";
    game.nextPosition2 = "upgradeArmor";
    game.nextPosition3 = "stats";
  }

  private void upgradeWeapon(){
    if(player.getWeapon().getType().toLowerCase().indexOf("reinforced".toLowerCase()) != -1 ) {
      ui.mainTextArea.setText("This weapon is already reinforced");
    } else {
      player.getWeapon().reinforceWeapon();
      player.subtractGold(150);
      ui.goldLabelInt.setText("" + player.getGold());
      ui.mainTextArea.setText(player.getName() + " now has a " + player.getWeapon().getType() + " that deals " + player.getWeapon().getDamage()+ " damage.");
    }
    ui.choice1.setText("Back to stats");
    ui.choice2.setText("");
    ui.choice3.setText("");

    game.nextPosition1 = "stats";
    game.nextPosition2 = "";
    game.nextPosition3 = "";
  }

  private void upgradeArmor(){
    if(player.getArmor().getType().toLowerCase().indexOf("reinforced".toLowerCase()) != -1 ) {
      ui.mainTextArea.setText("This armor is already reinforced");
    } else {
      player.getArmor().reinforceArmor();
      player.subtractGold(150);
      ui.goldLabelInt.setText("" + player.getGold());
      ui.mainTextArea.setText(player.getName() + " now has a " + player.getArmor().getType() + ". It has " + player.getArmor().getDefence()+ " defence.");
    }
    ui.choice1.setText("Back to stats");
    ui.choice2.setText("");
    ui.choice3.setText("");

    game.nextPosition1 = "stats";
    game.nextPosition2 = "";
    game.nextPosition3 = "";
  }

  private void foundHealingPotion(){
    ui.mainTextArea.setText(player.getName() + " enters a " + roomType + "\nIn the room you see a healing potion.\n" + player.getName() + " has " + player.getPotions() + " healing potions.\nDo you want to pick up one more?");

    ui.choice1.setText("Pick up");
    ui.choice2.setText("Dont pick up");
    ui.choice3.setText("");

    game.nextPosition1 = "getHealingPotion";
    game.nextPosition2 = "doorway";
    game.nextPosition3 = "";
  }
  private void getHealingPotion(){
    if(player.getPotions() < 5){
      player.addPotions(1);
      ui.mainTextArea.setText(player.getName() + " picked up a healing potion.");
    } else {
      ui.mainTextArea.setText(player.getName() + " cant hold more than 5 healing potions");
    }

    ui.choice1.setText(">");
    ui.choice2.setText("");
    ui.choice3.setText("");

    game.nextPosition1 = "doorway";
    game.nextPosition2 = "";
    game.nextPosition3 = "";
  }

  private void foundWeapon(){
    newWeapon = in.getWeapon(-1);
    if (player.getWeapon() != null){
      ui.mainTextArea.setText("In the room you see a " + newWeapon.getType() + ". It deals " + newWeapon.getDamage() + " damage.\n" + player.getName() + " has a " + player.getWeapon().getType() + " that deals " + player.getWeapon().getDamage()+ " damage.\nIf you pick up the new weapon, you discard the old one.");
    }else{
      ui.mainTextArea.setText("In the room you see a " + newWeapon.getType() + ". It deals " + newWeapon.getDamage() + " damage.\n" + player.getName() + " has no weapon.");
    }

    ui.choice1.setText("Pick up");
    ui.choice2.setText("Dont pick up");
    ui.choice3.setText("");

    game.nextPosition1 = "getWeapon";
    game.nextPosition2 = "dontGetWeapon";
    game.nextPosition3 = "";
  }
  private void getWeapon(){
    player.setWeapon(newWeapon);
    ui.mainTextArea.setText(player.getName() + " now has a " + player.getWeapon().getType() + " that deals " + player.getWeapon().getDamage()+ " damage.");

    ui.choice1.setText(">");
    ui.choice2.setText("");
    ui.choice3.setText("");

    game.nextPosition1 = "doorway";
    game.nextPosition2 = "";
    game.nextPosition3 = "";
  }
  private void dontGetWeapon(){
    if (player.getWeapon() != null){
      ui.mainTextArea.setText(player.getName() + " did not get the " + newWeapon.getType() + ".\n" + player.getName() + " still has the " + player.getWeapon().getType() + ".");
    }else{
      ui.mainTextArea.setText(player.getName() + " did not get the " + newWeapon.getType() + ".\n" + player.getName() + " still has no weapon.");
    }

    ui.choice1.setText(">");
    ui.choice2.setText("");
    ui.choice3.setText("");

    game.nextPosition1 = "doorway";
    game.nextPosition2 = "";
    game.nextPosition3 = "";
  }

  private void foundArmor(){
    newArmor = in.getArmor(-1);
    if (player.getArmor() != null){
      ui.mainTextArea.setText("In the room you see a " + newArmor.getType() + ". It has " + newArmor.getDefence() + " defence.\n" + player.getName() + " has " + player.getArmor().getType() + ". It has " + player.getArmor().getDefence()+ " defence.\nIf you pick up the new armor, you discard the old.");
    }else{
      ui.mainTextArea.setText("In the room you see a " + newArmor.getType() + ". It has " + newArmor.getDefence() + " defence.\n" + player.getName() + " has no armor.");
    }

    ui.choice1.setText("Pick up");
    ui.choice2.setText("Dont pick up");
    ui.choice3.setText("");

    game.nextPosition1 = "getArmor";
    game.nextPosition2 = "dontGetArmor";
    game.nextPosition3 = "";
  }
  private void getArmor(){
    player.setArmor(newArmor);
    ui.mainTextArea.setText(player.getName() + " now has a " + player.getArmor().getType() + ". It has " + player.getArmor().getDefence()+ " defence.");

    ui.choice1.setText(">");
    ui.choice2.setText("");
    ui.choice3.setText("");

    game.nextPosition1 = "doorway";
    game.nextPosition2 = "";
    game.nextPosition3 = "";
  }
  private void dontGetArmor(){
    if (player.getArmor() != null){
      ui.mainTextArea.setText(player.getName() + " did not get the " + newArmor.getType() + ".\n" + player.getName() + " still has the " + player.getArmor().getType() + ".");
    }else{
      ui.mainTextArea.setText(player.getName() + " did not get the " + newArmor.getType() + ".\n" + player.getName() + " still has no armor.");
    }

    ui.choice1.setText(">");
    ui.choice2.setText("");
    ui.choice3.setText("");

    game.nextPosition1 = "doorway";
    game.nextPosition2 = "";
    game.nextPosition3 = "";
  }

  private void encounteredEnemy(){
    enemy = in.getEnemy(-1).giveGear();
    enemyDamage = enemy.getBaseDamage();
    enemyHealth = enemy.getHealth();
    enemyDefence = 0;

    if (enemy.getWeapon() != null && enemy.getArmor() != null){
      enemyDamage += enemy.getWeapon().getDamage();
      enemyDefence = enemy.getArmor().getDefence();
      ui.mainTextArea.setText(player.getName() + " encountered a " + enemy.getType() + " with " + enemyHealth + " health.\nThe " + enemy.getType() + " has a " + enemy.getWeapon().getType() + ", and can do " + enemyDamage + " damage.\nThe " + enemy.getType() + " has " + enemy.getArmor().getType() + " with " + enemyDefence + " defence.");
    }else if (enemy.getWeapon() != null){
      enemyDamage += enemy.getWeapon().getDamage();
      ui.mainTextArea.setText(player.getName() + " encountered a " + enemy.getType() + " with " + enemyHealth + " health.\nThe " + enemy.getType() + " has a " + enemy.getWeapon().getType() + ", and can do " + enemyDamage + " damage.");
    } else if (enemy.getArmor() != null){
      enemyDefence = enemy.getArmor().getDefence();
      ui.mainTextArea.setText(player.getName() + " encountered a " + enemy.getType() + " with " + enemyHealth + " health.\nThe " + enemy.getType() + " has " + enemy.getArmor().getType() + " with " + enemyDefence + " defence.\nThe " + enemy.getType() + " has no weapon, but can stil do " + enemy.getBaseDamage() + " damage.");
    } else {
      ui.mainTextArea.setText(player.getName() + " encountered a " + enemy.getType() + " with " + enemyHealth + " health.\nThe " + enemy.getType() + " has no weapon, but can stil do " + enemy.getBaseDamage() + " damage.");
    }

    ui.choice1.setText("Attack");
    ui.choice2.setText("Player stats");
    ui.choice3.setText("Run away");

    game.nextPosition1 = "attackRoll";
    game.nextPosition2 = "stats";
    game.nextPosition3 = "escapeRoll";

    importantchoice1 = ui.choice1.getText();
    importantchoice2 = game.nextPosition1;
  }

  // gjør at du må kaste en slags ternign får å se om du gjør ekstra skade
  public void attackRoll(){
    ui.mainTextArea.setText("Roll to se if you crit. You have to get " + player.getCritChance() + " or higher.");

    ui.choice1.setText("");
    ui.choice2.setText("");
    ui.choice3.setText("");

    game.nextPosition1 = "";
    game.nextPosition2 = "";
    game.nextPosition3 = "";

    game.roll = "attackPlayer";
  }

  private void attackPlayer(){
    ui.diceTextField.setText("" + (int)Math.floor(Math.random()*(25)));

    int damage = player.getTotalDamage();
    
    if(Integer.parseInt(ui.diceTextField.getText()) >= player.getCritChance()){//sjekker hva du fikk når du "kasta terningen"
      damage = (int)Math.round(damage * player.getCritMultiplier());
    }

    damage = Math.max(0, damage - enemyDefence);// gjør at du ikke kan gjøre negativ skade(som ville gitt fienden liv)
    enemyHealth -= damage;
    if(this.enemyHealth > 0){
      ui.mainTextArea.setText(player.getName() + " attacks the " + enemy.getType() + " and deals " + damage + " damage\nThe " + enemy.getType() + " has " + this.enemyHealth + " health left");

      ui.choice1.setText(">");
      ui.choice2.setText("");
      ui.choice3.setText("");
  
      game.nextPosition1 = "attackEnemy";
      game.nextPosition2 = "";
      game.nextPosition3 = "";
    }else{
      player.addGold(enemy.getBaseDamage());
      ui.goldLabelInt.setText("" + player.getGold());
      ui.mainTextArea.setText(player.getName() + " attacks the " + enemy.getType() + " and deals " + damage + " damage\nThe " + enemy.getType() + " was slayed\n" + player.getName() + " now has " + player.getGold() + " gold.");

      ui.choice1.setText(">");
      ui.choice2.setText("");
      ui.choice3.setText("");
  
      game.nextPosition1 = "lootEnemy";
      game.nextPosition2 = "";
      game.nextPosition3 = "";
    }
  }

  private void attackEnemy(){
    int playerDefence = player.getTotalDefence();

    int playerHealth = player.getHealth();
    playerHealth -=  Math.max(0, enemyDamage - playerDefence);
    player.changeHealth(playerHealth);
    ui.hpLabelInt.setText(player.getHealth() + "/" + player.getMaxHealth());

    if(playerHealth > 0){
      ui.mainTextArea.setText("The " + enemy.getType() + " attacks the " + player.getName() + " and deals " + Math.max(0, enemyDamage - playerDefence) + " damage.");

      ui.choice1.setText("Attack");
      ui.choice2.setText("Player stats");
      ui.choice3.setText("Run away");
  
      game.nextPosition1 = "attackRoll";
      game.nextPosition2 = "stats";
      game.nextPosition3 = "escapeRoll";

      importantchoice1 = ui.choice1.getText();
      importantchoice2 = game.nextPosition1;
    }else{// death screen
      ui.mainTextArea.setText("The " + enemy.getType() + " attacks the " + player.getName() + " and deals " + Math.max(0, enemyDamage - playerDefence) + " damage.\n" + player.getName() + " was slayed.\nGame over\n\n\n\n\n:(");

      ui.playerPanel.setVisible(false);
      ui.choiceButtonPanel.setVisible(false);
    }
  }

  private void lootEnemy(){
    if(enemy.getWeapon() != null){
      ui.mainTextArea.setText("The " + enemy.getType() + " had a " + enemy.getWeapon().getType() + " roll to se if it dropped. You have to get 17 or higher.");
  
      ui.choice1.setText("");
      ui.choice2.setText("");
      ui.choice3.setText("");
  
      game.nextPosition1 = "";
      game.nextPosition2 = "";
      game.nextPosition3 = "";
  
      game.roll = "lootWeapon";

    }else if( enemy.getArmor() != null){      
      ui.mainTextArea.setText("The " + enemy.getType() + " had a " + enemy.getArmor().getType() + " roll to se if it dropped. You have to get 17 or higher.");
  
      ui.choice1.setText("");
      ui.choice2.setText("");
      ui.choice3.setText("");
  
      game.nextPosition1 = "";
      game.nextPosition2 = "";
      game.nextPosition3 = "";
  
      game.roll = "lootArmor";

    }else{
      ui.mainTextArea.setText("The " + enemy.getType() + " dropped nothing");

      ui.choice1.setText(">");
      ui.choice2.setText("");
      ui.choice3.setText("");
  
      game.nextPosition1 = "doorway";
      game.nextPosition2 = "";
      game.nextPosition3 = "";
    }
  }

  private void lootWeapon(){
    ui.diceTextField.setText("" + (int)Math.floor(Math.random()*(25)));
    
    if(Integer.parseInt(ui.diceTextField.getText()) >= 17){
      newWeapon = enemy.getWeapon();
      if (player.getWeapon() != null){
        ui.mainTextArea.setText("The " + enemy.getType() + " dropped it's " + enemy.getWeapon().getType() + ". It does " + enemy.getWeapon().getDamage() + " damage.\n" + player.getName() + " has a " + player.getWeapon().getType() + " that deals " + player.getWeapon().getDamage()+ " damage.\nIf you pick up the new weapon, you discard the old one.");
      }else{
        ui.mainTextArea.setText("The " + enemy.getType() + " dropped it's " + enemy.getWeapon().getType() + ". It does " + enemy.getWeapon().getDamage() + " damage.\n" + player.getName() + " has no weapon.");
      }
      ui.choice1.setText("Pick up");
      ui.choice2.setText("Dont pick up");
      ui.choice3.setText("");

      game.nextPosition1 = "getWeapon";
      game.nextPosition2 = "dontGetWeapon";
      game.nextPosition3 = "";
    } else{
      ui.mainTextArea.setText(player.getName() + " coudnt pick up the weapon");
      ui.choice1.setText(">");
      ui.choice2.setText("");
      ui.choice3.setText("");
  
      game.nextPosition1 = "doorway";
      game.nextPosition2 = "";
      game.nextPosition3 = "";
    }
  }

  private void lootArmor(){
    ui.diceTextField.setText("" + (int)Math.floor(Math.random()*(25)));
    
    if(Integer.parseInt(ui.diceTextField.getText()) >= 17){
      newArmor = enemy.getArmor();
      if (player.getArmor() != null){
        ui.mainTextArea.setText("The " + enemy.getType() + " dropped it's " + enemy.getArmor().getType() + " with " + enemy.getArmor().getDefence() + " defence.\n" + player.getName() + " has " + player.getArmor().getType() + ". It has " + player.getArmor().getDefence()+ " defence.\nIf you pick up the new armor, you discard the old.");
      }else{
        ui.mainTextArea.setText("The " + enemy.getType() + " dropped it's " + enemy.getArmor().getType() + " with " + enemy.getArmor().getDefence() + " defence.\n" + player.getName() + " has no armor.");
      }
      ui.choice1.setText("Pick up");
      ui.choice2.setText("Dont pick up");
      ui.choice3.setText("");
    
      game.nextPosition1 = "getArmor";
      game.nextPosition2 = "dontGetArmor";
      game.nextPosition3 = "";
    } else{
      ui.mainTextArea.setText(player.getName() + " coudnt pick up the armor");
      ui.choice1.setText(">");
      ui.choice2.setText("");
      ui.choice3.setText("");
  
      game.nextPosition1 = "doorway";
      game.nextPosition2 = "";
      game.nextPosition3 = "";
    }
  }

    // gjør at du må kaste en slags ternign får å se om du kan stikke av. Om du kommer unna kan regnes prosentvis 1 - spiller skade / fiende liv.
  public void escapeRoll(){
    if (Math.round(24/(1/(player.getTotalDamage()/(double)enemy.getHealth()))) <= 24) {
      ui.mainTextArea.setText("Roll to se if you can escape. You have to get " +  Math.round(24/(1/(player.getTotalDamage()/(double)enemy.getHealth()))) + " or higher.");

      ui.choice3.setText("");
      game.nextPosition3 = "";
  
      game.roll = "run";
    } else {
      ui.mainTextArea.setText(player.getName() + " coudnt escape.");
      
      ui.choice3.setText("");
      game.nextPosition3 = "";
    }
  }

  private void run(){
    ui.diceTextField.setText("" + (int)Math.floor(Math.random()*(25)));

    if(Integer.parseInt(ui.diceTextField.getText()) >= Math.round(24/(1/(player.getTotalDamage()/(double)enemy.getHealth())))){
      ui.mainTextArea.setText(player.getName() + " successfully escaped.");

      ui.choice1.setText(">");
      ui.choice2.setText("");
      ui.choice3.setText("");
  
      game.nextPosition1 = "doorway";
      game.nextPosition2 = "";
      game.nextPosition3 = "";
    } else {
      ui.mainTextArea.setText(player.getName() + " coudnt escape.");
      
      ui.choice3.setText("");
      game.nextPosition3 = "";
    }
  }

  private void maxHealthPotion(){
    ui.mainTextArea.setText(player.getName() + " enters a " + roomType + "\nIn the middle of the room there is a potion on a pedistal.\nIt smells awful, could be poisonous?\n" + player.getName() + " has " + player.getPotions() + " healing potions.");

    ui.choice1.setText("Drink it");
    ui.choice2.setText("Dont drink it");
    ui.choice3.setText("");

    game.nextPosition1 = "DrinkMaxHealthPotion";
    game.nextPosition2 = "doorway";
    game.nextPosition3 = "";
  }

  private void DrinkMaxHealthPotion(){
    if ((int)(Math.floor(Math.random()*2)) == 0) {
      player.changeMaxHealth(50);
      ui.mainTextArea.setText(player.getName() + " now has new max healh of " + player.getMaxHealth() + " max healh");
    }else{
      player.changeHealth((int)(Math.floor(Math.random()*20)+1));
      ui.mainTextArea.setText(player.getName() + " drank posion.");
    }
    ui.hpLabelInt.setText(player.getHealth() + "/" + player.getMaxHealth());

    ui.choice1.setText(">");
    ui.choice2.setText("");
    ui.choice3.setText("");

    game.nextPosition1 = "doorway";
    game.nextPosition2 = "";
    game.nextPosition3 = "";
  }

  private void foundBlessing(){
    blessing = in.getBlessing(-1);
    ui.mainTextArea.setText("In the middle of the room there is a glowing orb on a pedistal\nIt is a " + blessing.getType()  + " blessing, a player can only have one blessing, and can not change it.\nDo you accept it");

    ui.choice1.setText("Accept it");
    ui.choice2.setText("dont accept it");
    ui.choice3.setText("");

    game.nextPosition1 = "acceptBlessing";
    game.nextPosition2 = "doorway";
    game.nextPosition3 = "";
  }

  private void acceptBlessing(){
    player.setBlessing(blessing);
    ui.hpLabelInt.setText(player.getHealth() + "/" + player.getMaxHealth());

    ui.hpLabelInt.setText(player.getHealth() + "/" + player.getMaxHealth());
    ui.mainTextArea.setText(player.getName() + " now has the " + player.getBlessing().getType() + " blessing");

    ui.choice1.setText(">");
    ui.choice2.setText("");
    ui.choice3.setText("");

    game.nextPosition1 = "doorway";
    game.nextPosition2 = "";
    game.nextPosition3 = "";
  }
}

//importantchoice
//cant flee from stats