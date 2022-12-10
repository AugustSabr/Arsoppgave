import java.io.Serializable;
public class Weapon implements Serializable {
  private String weaponType;
  private int tier, weaponDamage;

  public Weapon(String wt, int tr, int wd) {// tar inn tre verdier og lager et våpen
    weaponType = wt;
    tier = tr;
    weaponDamage = wd;
  }

  public Weapon reinforceWeapon() {// åppgraderer våpene med 30 skade
    weaponType = "reinforced " + weaponType;
    weaponDamage = weaponDamage + 30;
    return null;
  }

  public int getTier() {
    return this.tier;
  }

  public String getType() {
    return this.weaponType;
  }

  public int getDamage() {
    return this.weaponDamage;
  }
}