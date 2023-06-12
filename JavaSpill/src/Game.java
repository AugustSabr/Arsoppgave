import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// import java.io.FileOutputStream;
// import java.io.ObjectOutputStream;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
public class Game {
  private Player player;
  private UI ui = new UI(this);
  private VisibilityManager vm = new VisibilityManager(ui);
  private ChoiceHandler cHandler = new ChoiceHandler();
  private Map map;
  private _KeyListener key;
  private GameInventory in;
  private Encounter e;
  public String c10, c11;
  public static void main(String[] args) {
    new Game();
  }

  public Game() {
    ui.createUI(cHandler);
    vm.showTitleScreen();
  }


  public class ChoiceHandler implements ActionListener{// ChoiceHandler arver fra ActionListener
    public void actionPerformed(ActionEvent event){//ActionCommand bruker for å skille knappene fra hverandre. en slags id.
      String yourChoice = event.getActionCommand();
      ui.window.requestFocus();

      switch(yourChoice){//isteden for å endre ActionCommand endrer jeg en tekst variabel nextPosition for å velge hva hovedknappene gjør
        case "start":
          in = new GameInventory();//lager en inventory av de lokale filene
          // room = new Room(game, ui, vm, in); //lager et room og sender med alle nødvendige objects så room kan endre på de underveis
          vm.enterName(); break;
        case "update": new UpdateLocalFiles(); break;
        case "makePlayer": makePlayer(); break;
        case "c0": ui.selctbutton(0); break;
        case "c1": ui.selctbutton(1); break;
        case "c2": ui.selctbutton(2); break;
        case "c3": ui.selctbutton(3); break;
        case "c4": ui.selctbutton(4); break;
        case "c5": ui.selctbutton(5); break;
        case "c6": ui.selctbutton(6); break;
        case "c7": ui.selctbutton(7); break;
        case "c8": ui.selctbutton(8); break;
        case "c9": ui.selctbutton(9); break;
        case "c10": e.selectPosition(c10); break;
        case "c11": e.selectPosition(c11); break;
      }
    }
  }
  
  public void makePlayer(){
    try {
      ObjectInputStream input = new ObjectInputStream(new FileInputStream("saves/" + ui.inputTextField.getText() + ".dat"));
      player = (Player) input.readObject();
      input.close();
      ui.mainTextArea.setText(player.getName() + " is back for more");
    } catch (Exception e){
      player = new Player(ui.inputTextField.getText());
      ui.mainTextArea.setText(player.getName() + " started a new game");

      System.err.println("Couldnt load game. Created new player.");
      System.err.println("Error message: " + e + "\n");
      // e.printStackTrace();
    }
    ui.addGameInventory(in);
    ui.addPlayer(player);
    vm.showGamescreen();
    e = new Encounter(ui, player, in, vm, this);
    ui.addEncounter(e);
    map = new Map(ui, e, vm, in, 3, 0, "east");
    // ui.drawMap();
    key = new _KeyListener(map, ui, e);
    vm.addKeyListener(key);
  }
}