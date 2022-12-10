import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Game {
  public static Player player;
  ChoiceHandler cHandler = new ChoiceHandler();// choiceHandler er en class jeg lagde selv. sel lenger nede
  UI ui = new UI();
  VisibilityManager vm = new VisibilityManager(ui);//sender med UI fordi det er egt bare det VisibilityManager gjør visible eller ikke 
  Game game = this;
  GameInventory in;
  Room room;

  String nextPosition1, nextPosition2, nextPosition3, roll = "funRoll";
  public static void main(String[] args) {
    new Game();
  }

  public Game(){
    ui.createUI(cHandler);//sender med choiceHandler for at knappene jeg lager i UI skal ha en effect
    vm.showTitleScreen();
  }
  
  public class ChoiceHandler implements ActionListener{// ChoiceHandler arver fra ActionListener
    public void actionPerformed(ActionEvent event){//ActionCommand bruker for å skille knappene fra hverandre. en slags id.
      String yourChoice = event.getActionCommand();

      switch(yourChoice){//isteden for å endre ActionCommand endrer jeg en tekst variabel nextPosition for å velge hva hovedknappene gjør
        case "start":
          in = new GameInventory();//lager en inventory av de lokale filene
          room = new Room(game, ui, vm, in); //lager et room og sender med alle nødvendige objects så room kan endre på de underveis
          vm.enterName(); room.enterName(); break;
        case "update": new UpdateLocalFiles(); break;
        case "c1": room.selectPosition(nextPosition1); break;
        case "c2": room.selectPosition(nextPosition2); break;
        case "c3": room.selectPosition(nextPosition3); break;
        case "roll": room.selectPosition(roll); roll = "funRoll"; break;// skjører roll, og edrer den til funRoll så du ikke kan prøve omigjen
      }
    }
  }
  
  public static Player getPlayer(){
    return player;
  }
}