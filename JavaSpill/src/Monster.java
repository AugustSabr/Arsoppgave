public class Monster extends creaturesSuperClass{
  private String path;
  private int tier;
  private GameInventory in;

  public Monster (String n, String p, int st, int h, int e, int sp, int t, GameInventory i) {
    name = n;
    path = p;
    strength = st;
    health = h;
    endurance = e;
    speed = sp;
    tier = t;
    in = i;
  }

  // public Enemy giveGear() {
  //   int gear = (int)(Math.floor(Math.random()*4));
  //   switch (gear){
  //     case 0: weapon = in.getWeapon(-1); break;
  //     case 1: armor = in.getArmor(-1); break;
  //     case 2: weapon = in.getWeapon(-1); armor = in.getArmor(-1); break;
  //   }
  //   return this;
  // }

  public int getTier() {
    return tier;
  }

  public String getPath() {
    return path;
  }
}