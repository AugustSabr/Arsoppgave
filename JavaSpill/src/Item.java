import java.io.Serializable;
public class Item  extends objectSuperClass implements Serializable{
  public Item(String ty, String p, int v, int ti){
    type = ty;
    path = p;
    value = v;
    tier = ti;
  }
}
