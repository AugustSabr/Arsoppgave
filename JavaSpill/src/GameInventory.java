import java.io.File;  // Import the File class
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

import javax.crypto.ShortBufferException;
import javax.sound.sampled.SourceDataLine;

public class GameInventory {
  Map map;
  //arrays med nesten alle objects spillet vil trenge
  ArrayList<Weapon> Weapons = new ArrayList<Weapon>();
  ArrayList<Item> Items = new ArrayList<Item>();
  ArrayList<Armor> Armors = new ArrayList<Armor>();
  // ArrayList<Blessing> Blessings = new ArrayList<Blessing>();
  ArrayList<Monster> Monsters = new ArrayList<Monster>();

  private String[] categories = {"weapon", "item","armor", "blessing", "enemy"};

  
  public GameInventory(){
    for (int i = 0; i < categories.length; i++){
      try {
        File myObj = new File("localFiles/txt/" + categories[i] + ".txt");//henter info fra relevant fil
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
          switch(categories[i]){//lager objects av all infoen i filen
            case "weapon": Weapons.add(new Weapon(myReader.nextLine(), myReader.nextLine(), Integer.parseInt(myReader.nextLine()), Integer.parseInt(myReader.nextLine()), Integer.parseInt(myReader.nextLine()))); break;
            case "item": Items.add(new Item(myReader.nextLine(), myReader.nextLine(), Integer.parseInt(myReader.nextLine()), Integer.parseInt(myReader.nextLine()))); break;
            case "armor": Armors.add(new Armor(myReader.nextLine(), myReader.nextLine(), Integer.parseInt(myReader.nextLine()), Integer.parseInt(myReader.nextLine()), Integer.parseInt(myReader.nextLine()))); break;
            // case "blessing": Blessings.add(new Blessing(myReader.nextLine(), Integer.parseInt(myReader.nextLine()))); break;
            case "enemy": Monsters.add(new Monster(myReader.nextLine(), myReader.nextLine(), Integer.parseInt(myReader.nextLine()), Integer.parseInt(myReader.nextLine()), Integer.parseInt(myReader.nextLine()), Integer.parseInt(myReader.nextLine()), Integer.parseInt(myReader.nextLine()), this)); break;
          }
        }
        myReader.close();
      } catch (Exception e) {
        System.err.println("Couldnt read locale files. They are either incompatible with the games software, or non existent");
        System.err.println("Error message: " + e + "\n");
        // e.printStackTrace();
      }
    }
  }
  public void addMap(Map m) {
    map = m;
  }
  
  public Weapon getWeapon(int i) {
    Weapon w1;
    Weapon w2;
    if (i == -1){//hvis du kaller med en annen verdi får du våpenet med den indexen
      w1 = Weapons.get((int)(Math.floor(Math.random() * Weapons.size())));
      while(w1.getTier() > map.floor){//passer på at funkjsonen ikke returnerer et våpen men forhøy tier
        w1 = Weapons.get((int)(Math.floor(Math.random() * Weapons.size())));
      }
      w2 = new Weapon(w1.getType(), w1.getPath(), w1.getDamage(), w1.getValue(), w1.getTier());
      return w2;
    } else {
      w1 = Weapons.get(i);
      w2 = new Weapon(w1.getType(), w1.getPath(), w1.getDamage(), w1.getValue(), w1.getTier());
      return w2;
    }
  }

  public Item getItem(int i) {
    Item i1;
    Item i2;
    if (i == -1){//hvis du kaller med en annen verdi får du våpenet med den indexen
      i1 = Items.get((int)(Math.floor(Math.random() * Items.size())));
      i2 = new Item(i1.getType(), i1.getPath(), i1.getTier(), i1.getValue());
      return i2;
    } else {
      i1 = Items.get(i);
      i2 = new Item(i1.getType(), i1.getPath(), i1.getTier(), i1.getValue());
      return i2;
    }
  }

  public Armor getArmor(int i) {
    Armor a1;
    Armor a2;
    if (i == -1){//hvis du kaller med en annen verdi får du våpenet med den indexen
      a1 = Armors.get((int)(Math.floor(Math.random() * Armors.size())));
      while(a1.getTier() > map.floor){//passer på at funkjsonen ikke returnerer et våpen men forhøy tier
        a1 = Armors.get((int)(Math.floor(Math.random() * Armors.size())));
      }
      a2 = new Armor(a1.getType(), a1.getPath(), a1.getDefence(), a1.getValue(), a1.getTier());
      return a2;
    } else {
      a1 = Armors.get(i);
      a2 = new Armor(a1.getType(), a1.getPath(), a1.getDefence(), a1.getValue(), a1.getTier());
      return a2;
    }
  }

  // public Blessing getBlessing(int i) {
  //   if (i == -1){
  //     return Blessings.get((int)(Math.floor(Math.random() * Blessings.size())));
  //   } else{
  //     return Blessings.get(i);
  //   }
  // }

  public Monster getMonster(int i) {
    if (i == -1){
      Monster e = Monsters.get((int)(Math.floor(Math.random() * Monsters.size())));
      while(e.getTier() > map.floor){
        e = Monsters.get((int)(Math.floor(Math.random() * Monsters.size())));
      }
      return e;
    } else {
      return Monsters.get(i);
    }
  }
}
