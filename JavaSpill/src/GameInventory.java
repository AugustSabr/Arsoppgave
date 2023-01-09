import java.io.File;  // Import the File class
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

public class GameInventory {
  //arrays med nesten alle objects spillet vil trenge
  ArrayList<Weapon> Weapons = new ArrayList<Weapon>();
  ArrayList<Armor> Armors = new ArrayList<Armor>();
  ArrayList<Blessing> Blessings = new ArrayList<Blessing>();
  ArrayList<Enemy> Enemys = new ArrayList<Enemy>();

  private String[] categories = {"weapon", "armor", "blessing", "enemy"};
  private int floor;

  public GameInventory(){
    for (int i = 0; i < categories.length; i++){
      try {
        File myObj = new File("localFiles/" + categories[i] + ".txt");//henter info fra relevant fil
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
          switch(categories[i]){//lager objects av all infoen i filen
            case "weapon": Weapons.add(new Weapon(myReader.nextLine(), Integer.parseInt(myReader.nextLine()), Integer.parseInt(myReader.nextLine()))); break;
            case "armor": Armors.add(new Armor(myReader.nextLine(), Integer.parseInt(myReader.nextLine()), Integer.parseInt(myReader.nextLine()))); break;
            case "blessing": Blessings.add(new Blessing(myReader.nextLine(), Integer.parseInt(myReader.nextLine()))); break;
            case "enemy": Enemys.add(new Enemy(this, myReader.nextLine(), Integer.parseInt(myReader.nextLine()), Integer.parseInt(myReader.nextLine()), Integer.parseInt(myReader.nextLine()))); break;
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

  public Weapon getWeapon(int i) {
    if (i == -1){//hvis du kaller med en annen verdi får du våpenet med den indexen
      this.floor = Room.getDungeonFloor();
      Weapon w = Weapons.get((int)(Math.floor(Math.random() * Weapons.size())));
      while(w.getTier() > this.floor){//passer på at funkjsonen ikke returnerer et våpen men forhøy tier
        w = Weapons.get((int)(Math.floor(Math.random() * Weapons.size())));
      }
      return w;
    } else {
      return Weapons.get(i);
    }
  }

  public Armor getArmor(int i) {
    if (i == -1){
      this.floor = Room.getDungeonFloor();
      Armor a = Armors.get((int)(Math.floor(Math.random() * Armors.size())));
      while(a.getTier() > this.floor){
        a = Armors.get((int)(Math.floor(Math.random() * Armors.size())));
      }
      return a;
    } else {
      return Armors.get(i);
    }
  }

  public Blessing getBlessing(int i) {
    if (i == -1){
      return Blessings.get((int)(Math.floor(Math.random() * Blessings.size())));
    } else{
      return Blessings.get(i);
    }
  }

  public Enemy getEnemy(int i) {
    if (i == -1){
      this.floor = Room.getDungeonFloor();
      Enemy e = Enemys.get((int)(Math.floor(Math.random() * Enemys.size())));
      while(e.getTier() > this.floor){
        e = Enemys.get((int)(Math.floor(Math.random() * Enemys.size())));
      }
      return e;
    } else {
      return Enemys.get(i);
    }
  }
}
