public class creaturesSuperClass {
  protected  String name;
  protected  int 
  health,
  strength,
  speed,
  // critChance,
  endurance;
  // protected  double critMultiplier = 1.5;
  protected  Weapon myWeapon;
  protected  Armor myArmor;
  
  public String getName(){
    return name;
  }
  public int getHealth(){
    return health;
  }
  public int getEndurance() {
    return endurance;
  }
  public int getSpeed() {
    return speed;
  }
  public int getStrength() {
    return strength;
  }
  public Weapon getMyWeapon(){
    if (myWeapon != null){
      return myWeapon;
    }
    else{
      return null;
    }
  }

  public Armor getMyArmor(){
    if (myArmor != null){
      return myArmor;
    }
    else{
      return null;
    }
  }

  public int getTotalDamage(){
    if (myWeapon != null){
      return myWeapon.getDamage() + strength;
    }
    return strength;
  }

  public int getTotalDefence(){
    if (myArmor != null){
      return myArmor.getDefence() + endurance;
    }
    return endurance;
  }

  // public int getCritChance() {
  //   return critChance;
  // }

  // public double getCritMultiplier() {
  //   return critMultiplier;
  // }

}

