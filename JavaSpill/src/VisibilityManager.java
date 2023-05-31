//ender på va som er synlig
public class VisibilityManager {
  private UI ui;
  private _KeyListener key;
  public VisibilityManager(UI userInterface){
    ui = userInterface;
  }
  public void addKeyListener(_KeyListener k) {
    key = k;
  }
  public void showTitleScreen(){
    //titlescreen
    ui.titleNamePanel.setVisible(true);
    ui.startButtonPanel.setVisible(true);
    ui.startButtonPanel.requestFocus();
    ui.updateButtonPanel.setVisible(true);

    //inputname
    ui.inputTextPanel.setVisible(false);

    //gamescreen
    ui.encounterImgLabel.setVisible(false);
    ui.roomImgLabel.setVisible(false);
    ui.mainTextPanel.setVisible(false);
    ui.statPanel.setVisible(false);
    ui.choiceButtonPanel.setVisible(false);
    ui.itemInfoPanel.setVisible(false);
    ui.playerPosLabel.setVisible(false);
    ui.mapPanel.setVisible(false);

  }
  public void enterName(){
    //titlescreen
    ui.titleNamePanel.setVisible(false);
    ui.startButtonPanel.setVisible(false);
    ui.updateButtonPanel.setVisible(false);

    //inputname
    ui.inputTextPanel.setVisible(true);
    ui.inputTextField.requestFocus();

    ui.mainTextPanel.setVisible(true);
  }
  public void showGamescreen(){
    //titlescreen
    ui.window.requestFocus();

    //inputname
    ui.inputTextPanel.setVisible(false);
    
    //gamescreen
    ui.encounterImgLabel.setVisible(true);
    ui.roomImgLabel.setVisible(true);
    ui.statPanel.setVisible(true);
    ui.playerPosLabel.setVisible(true);
    ui.mapPanel.setVisible(true);
  }
  public void showchoiceButtons(){
    ui.statPanel.setVisible(false);
    ui.choiceButtonPanel.setVisible(true);
    ui.playerPosLabel.setVisible(false);
    ui.mapPanel.setVisible(false);
    key.keyEffect = "inventory";
  }
  public void dontShowchoiceButtons(){
    ui.statPanel.setVisible(true);
    ui.choiceButtonPanel.setVisible(false);
    ui.itemInfoPanel.setVisible(false);
    ui.playerPosLabel.setVisible(true);
    ui.mapPanel.setVisible(true);
    key.keyEffect = "walking";
  }
}
