import java.util.ArrayList;

public class Player extends creaturesSuperClass{
    private int 
    maxHealth = 100,
    luck = 1,
    exp = 0,
    gold = 0;
    // private Blessing blessing;

    ArrayList<Object> inventory = new ArrayList<Object>();
  
    public String getInventoryString(int i) {
      if(inventory.get(i).getClass() == Weapon.class){
        Weapon weapon = (Weapon) inventory.get(i);
        return weapon.getType();
      }
      if(inventory.get(i).getClass() == Armor.class){
        Armor armor = (Armor) inventory.get(i);
        return armor.getType();
      }
      if(inventory.get(i).getClass() == Item.class){
        Item item = (Item) inventory.get(i);
        return item.getType();
      }
      return null;
    }

    public Player(String n){
      name = n;
      health = maxHealth;
      strength = 20;
      speed = 5;
      endurance = 0;
    }

    public int getMaxHealth(){
      return maxHealth;
    }
    public void changeMaxHealth(int upMaxHealth){
      maxHealth += upMaxHealth;
      health = maxHealth;
    }
    public void changeHealth(int h){
      health = h;
    }

    public int getExp() {
      return exp;
    }
  

    public void heal(){
      health = maxHealth;
    }
    
    public void addGold(int g){
      gold += g;
    }
    public void subtractGold(int g){
      gold -= g;
    }
    public int getGold(){
      return gold;
    }
    public int getLuck() {
      return luck;
    }
  
    public void setMyWeapon(Weapon weapon){
      myWeapon = weapon;
    }

    public void setMyArmor(Armor armor){
      myArmor = armor;
    }

    // public void setBlessing(Blessing blessing){//måten jeg setter blessings gjør at kun disse 5 typer blessings vil ha noen effect. det er egt ikke optimalt, men jeg ser ingen annen måte og gjøre det på pga hver blessing gjør noe helt forskjeldig
    //   blessing = blessing;
    //   if(blessing.getType().equals("power")){
    //     basedamage += blessing.getEffect();
    //   }else if(blessing.getType().equals("luck")){
    //     critChance = blessing.getEffect() + 1;// 1/3
    //   }else if(blessing.getType().equals("destruction")){
    //   critMultiplier = (double)blessing.getEffect()/100;
    //   }else if(blessing.getType().equals("health")){
    //     changeMaxHealth(blessing.getEffect());
    //   }else if(blessing.getType().equals("protection")){
    //     baseDefence += blessing.getEffect();
    //   }
    // }
  
    // public Blessing getBlessing(){
    //   return blessing;
    // }
  }

  //dept???
  //progresjon^?