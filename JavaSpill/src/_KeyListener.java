import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class _KeyListener implements KeyListener {
  private Map map;
  private UI ui;
  private Encounter en;
  public String z = "", x = "", c = "", keyEffect = "walking";
  public Boolean inCombat = false;

  public _KeyListener(Map m, UI u, Encounter e) {
    map = m;
    ui = u;
    en = e;
    ui.window.addKeyListener(this);
    ui.choiceButtonPanel.addKeyListener(this);
    e.addKeyListener(this);
    map.addKeyListener(this);
  }

  @Override
  public void keyTyped(KeyEvent e) {}

  @Override
  public void keyPressed(KeyEvent e) {}

  @Override
  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      if(keyEffect == "walking" && inCombat == false){
        map.move("up");
      }
      if(keyEffect == "inventory"){
        ui.chageButton("up");
      }
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      if(keyEffect == "walking" && inCombat == false){
        map.move("down");
      }
      if(keyEffect == "inventory"){
        ui.chageButton("down");
      }
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      if(keyEffect == "walking" && inCombat == false){
        map.move("right");
      }
      if(keyEffect == "inventory"){
        ui.chageButton("right");
      }    }
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      if(keyEffect == "walking" && inCombat == false){
        map.move("left");
      }
      if(keyEffect == "inventory"){
        ui.chageButton("left");
      }
    }

    if (e.getKeyCode() == KeyEvent.VK_Z) {
      if(keyEffect == "walking"){
        en.selectPosition(z);
      }else if(keyEffect == "inventory"){
        ui.buttonList.get(ui.pos).doClick();
      }
    }
    if (e.getKeyCode() == KeyEvent.VK_X) {
      en.selectPosition(x);
    }
    if (e.getKeyCode() == KeyEvent.VK_C) {
      en.selectPosition(c);
    }
  }
}