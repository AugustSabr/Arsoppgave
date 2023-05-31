import java.io.Serializable;
public class Weapon extends objectSuperClass implements Serializable {
  private int weaponDamage;

  public Weapon(String ty, String p, int wd, int va, int ti) {// tar inn tre verdier og lager et våpen
    type = ty;
    path = p;
    weaponDamage = wd;
    value = va;
    tier = ti;
  }

  // public Weapon reinforceWeapon() {// oppgraderer våpene med 30 skade
  //   type = "reinforced " + type;
  //   weaponDamage = weaponDamage + 30;
  //   return null;
  // }

  public int getDamage() {
    return weaponDamage;
  }
}