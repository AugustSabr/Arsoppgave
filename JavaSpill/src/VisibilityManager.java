//ender på va som er synlig
public class VisibilityManager {
  UI ui;
  public VisibilityManager(UI userInterface){
    ui = userInterface;
  }
  public void showTitleScreen(){
    //titlescreen
    ui.titleNamePanel.setVisible(true);
    ui.startButtonPanel.setVisible(true);
    ui.updatePanel.setVisible(true);

    //inputname
    ui.inputTextPanel.setVisible(false);

    //gamescreen
    ui.playerPanel.setVisible(false);
    ui.mainTextPanel.setVisible(false);
    ui.diceButtonPanel.setVisible(false);
    ui.choiceButtonPanel.setVisible(false);
  }
  public void enterName(){
    //titlescreen
    ui.titleNamePanel.setVisible(false);
    ui.startButtonPanel.setVisible(false);
    ui.updatePanel.setVisible(false);

    //inputname
    ui.inputTextPanel.setVisible(true);
    ui.inputTextField.requestFocus();

    ui.playerPanel.setVisible(false);
    ui.mainTextPanel.setVisible(true);
    ui.diceButtonPanel.setVisible(false);
    ui.choiceButtonPanel.setVisible(false);
  }
  public void showGamescreen(){
    //titlescreen
    ui.titleNamePanel.setVisible(false);
    ui.startButtonPanel.setVisible(false);
    ui.updatePanel.setVisible(false);


    //inputname
    ui.inputTextPanel.setVisible(false);
    
    //gamescreen
    ui.playerPanel.setVisible(true);
    ui.mainTextPanel.setVisible(true);
    ui.diceButtonPanel.setVisible(true);
    ui.choiceButtonPanel.setVisible(true);

    ui.choice1.setVisible(true);
    ui.choice2.setVisible(true);
    ui.choice3.setVisible(true);
  }
}
