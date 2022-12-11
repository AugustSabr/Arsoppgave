import java.io.Serializable;
public class Armor implements Serializable{
  private String armorType;
  private int tier, armorDefence;

  public Armor(String at, int tr, int ad) {// tar inn tre verdier og lager en rusning
    armorType = at;
    tier = tr;
    armorDefence = ad;
  }

  public Armor reinforceArmor() {// oppgraderer rustningen med 20 defence
    armorType = "reinforced " + armorType;
    armorDefence = armorDefence + 20;
    return null;
  }

  public int getTier() {
    return tier;
  }
  
  public String getType(){
    return this.armorType;
  }

  public int getDefence(){
    return this.armorDefence;
  }
}