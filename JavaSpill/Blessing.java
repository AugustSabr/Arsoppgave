import java.io.Serializable;
public class Blessing  implements Serializable{
  private String blessingType;
  private int blessingEffect;

  public Blessing(String bt, int be) {// tar inn to verdier og lager en, men når blessingen bir knyttet til spilleren må den være enten: power, luck, destruction, health, eller protection, får å kunne ha noe effect
    this.blessingType = bt;
    this.blessingEffect = be;
  }

  public String getType(){
    return this.blessingType;
  }

  public int getEffect(){
    return this.blessingEffect;
  }
}