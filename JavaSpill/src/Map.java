import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.awt.event.ActionEvent;


public class Map {

  ArrayList<ArrayList<String>> mapLayout1 = new ArrayList<ArrayList<String>>();

  ArrayList<ArrayList<String>> structureLayout1 = new ArrayList<ArrayList<String>>();

  public int x, y, floor;
  private String facing, position, structure;

  private UI ui;
  private Encounter en;
  private _KeyListener key;
  private VisibilityManager vm;


  public Map(UI u, Encounter e, VisibilityManager v, GameInventory in, int _x, int _y, String f) {

    ui = u;
    en = e;
    vm = v;

    x = _x;
    y = _y;
    facing = f;
    floor = 1;
    in.addMap(this);
    e.addMap(this);
    ui.addMap(this);

    mapLayout1.add(new ArrayList<String>(Arrays.asList(" ", " ", " ", "d", "i", " ", " ", " ", " ", " ")));
    mapLayout1.add(new ArrayList<String>(Arrays.asList(" ", " ", " ", " ", "e", " ", " ", " ", " ", " ")));
    mapLayout1.add(new ArrayList<String>(Arrays.asList(" ", " ", " ", " ", "e", " ", " ", " ", " ", " ")));
    mapLayout1.add(new ArrayList<String>(Arrays.asList(" ", "a", "h", "i", "k", "b", "a", " ", " ", " ")));
    mapLayout1.add(new ArrayList<String>(Arrays.asList(" ", "g", "n", "o", "n", "f", "o", "b", " ", " ")));
    mapLayout1.add(new ArrayList<String>(Arrays.asList("d", "f", "f", "o", "f", "f", "j", " ", " ", " ")));
    mapLayout1.add(new ArrayList<String>(Arrays.asList("d", "f", "l", "o", "l", "i", " ", " ", " ", " ")));
    mapLayout1.add(new ArrayList<String>(Arrays.asList(" ", " ", "c", "c", "e", "k", "b", " ", " ", " ")));
    mapLayout1.add(new ArrayList<String>(Arrays.asList(" ", " ", "c", " ", "k", "m", " ", " ", " ", " ")));
    mapLayout1.add(new ArrayList<String>(Arrays.asList(" ", " ", " ", " ", "c", "c", " ", " ", " ", " ")));
    maps.add(mapLayout1);

    structureLayout1.add(new ArrayList<String>(Arrays.asList(" ", " ", " ", " ", " ", " ", " ", " ", " ", " ")));
    structureLayout1.add(new ArrayList<String>(Arrays.asList(" ", " ", " ", " ", " ", " ", " ", " ", " ", " ")));
    structureLayout1.add(new ArrayList<String>(Arrays.asList(" ", " ", " ", " ", " ", " ", " ", " ", " ", " ")));
    structureLayout1.add(new ArrayList<String>(Arrays.asList(" ", "s", " ", " ", " ", " ", " ", " ", " ", " ")));
    structureLayout1.add(new ArrayList<String>(Arrays.asList(" ", " ", " ", " ", " ", " ", " ", " ", " ", " ")));
    structureLayout1.add(new ArrayList<String>(Arrays.asList(" ", " ", " ", " ", " ", " ", " ", " ", " ", " ")));
    structureLayout1.add(new ArrayList<String>(Arrays.asList(" ", " ", " ", " ", " ", " ", " ", " ", " ", " ")));
    structureLayout1.add(new ArrayList<String>(Arrays.asList(" ", " ", "p", " ", " ", " ", " ", " ", " ", " ")));
    structureLayout1.add(new ArrayList<String>(Arrays.asList(" ", " ", "r", " ", " ", " ", " ", " ", " ", " ")));
    structureLayout1.add(new ArrayList<String>(Arrays.asList(" ", " ", " ", " ", " ", " ", " ", " ", " ", " ")));
    structureLayouts.add(structureLayout1);

    ui.mapString.add(new ArrayList<String>(Arrays.asList(" ", " ", " ", " ", " ", " ", " ", " ", " ", " ")));
    ui.mapString.add(new ArrayList<String>(Arrays.asList(" ", " ", " ", " ", " ", " ", " ", " ", " ", " ")));
    ui.mapString.add(new ArrayList<String>(Arrays.asList(" ", " ", " ", " ", " ", " ", " ", " ", " ", " ")));
    ui.mapString.add(new ArrayList<String>(Arrays.asList(" ", " ", " ", " ", " ", " ", " ", " ", " ", " ")));
    ui.mapString.add(new ArrayList<String>(Arrays.asList(" ", " ", " ", " ", " ", " ", " ", " ", " ", " ")));
    ui.mapString.add(new ArrayList<String>(Arrays.asList(" ", " ", " ", " ", " ", " ", " ", " ", " ", " ")));
    ui.mapString.add(new ArrayList<String>(Arrays.asList(" ", " ", " ", " ", " ", " ", " ", " ", " ", " ")));
    ui.mapString.add(new ArrayList<String>(Arrays.asList(" ", " ", " ", " ", " ", " ", " ", " ", " ", " ")));
    ui.mapString.add(new ArrayList<String>(Arrays.asList(" ", " ", " ", " ", " ", " ", " ", " ", " ", " ")));
    ui.mapString.add(new ArrayList<String>(Arrays.asList(" ", " ", " ", " ", " ", " ", " ", " ", " ", " ")));

    draw();
  }
  public void addKeyListener(_KeyListener k){
    key = k;
  }

  public ArrayList<ArrayList<ArrayList<String>>> maps = new ArrayList<ArrayList<ArrayList<String>>>();
  public ArrayList<ArrayList<ArrayList<String>>> structureLayouts = new ArrayList<ArrayList<ArrayList<String>>>();
  

  public void createFloor(){
    System.out.println();
    ui.mapString.clear();
    boolean shop=true;
    ArrayList<ArrayList<String>> mapLayout = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> structureLayout = new ArrayList<ArrayList<String>>();

    for (int i = 0; i < 10; i++) {
      ArrayList<String> uiMap = new ArrayList<String>();
      ArrayList<String> ylevelMap = new ArrayList<String>();
      ArrayList<String> ylevelStructure = new ArrayList<String>();
      for (int j = 0; j < 10; j++) {
        uiMap.add(" ");
        ArrayList<String> roomPossibilities = new ArrayList<String>(Arrays.asList(" ", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o"));
        boolean a1=false;
        boolean a2=true;

        boolean b1=false;
        boolean b2=true;

        boolean c1=false;
        boolean c2=true;

        boolean d1=false;
        boolean d2=true;

        String[] upCheck = {"a", "e", "h", "i", "k", "l", "m", "o"};
        boolean up=false;
        for (int k = 0; k < upCheck.length; k++) {
          try {
            if(mapLayout.get(i-1).get(j) == "a"){
              a1=true;
            }
            if(mapLayout.get(i-1).get(j) == "c"){
              c2=true;
            }
            if(mapLayout.get(i-1).get(j) == upCheck[k]){
              up=true;
              b2=false;
              d2=false;
              break;
            }
          } catch (Exception e) {}
        }

        String[] leftCheck = {"d", "f", "g", "h", "k", "l", "n", "o"};
        boolean left=false;
        for (int k = 0; k < leftCheck.length; k++) {
          try {
            if(ylevelMap.get(j-1) == "b"){
              b1=true;
            }
            if(ylevelMap.get(j-1) == "d"){
              d1=true;
            }
            if(ylevelMap.get(j-1) == leftCheck[k]){
              left=true;
              a2=false;
              c2=false;
              break;
            }
          } catch (Exception e) {}
        }
        if(up){
          roomPossibilities.remove(" ");
          roomPossibilities.remove("b");
          roomPossibilities.remove("d");
          roomPossibilities.remove("f");
          roomPossibilities.remove("h");
          roomPossibilities.remove("i");
          roomPossibilities.remove("l");
          if(a1 && a2 && shop){
            // System.out.println(roomPossibilities);
            // System.out.println(i+" "+j);
            if((int)Math.floor(Math.random()*5) == 0){
              structureLayout.get(i-1).set(j, "r");
              ylevelMap.add("a");
              ylevelStructure.add("p");
              System.out.println("a: "+ i+" "+j);
              shop=false;
              continue;
            } 
          }
          roomPossibilities.remove("a");
        } else {
          roomPossibilities.remove("e");
          roomPossibilities.remove("g");
          roomPossibilities.remove("j");
          roomPossibilities.remove("k");
          roomPossibilities.remove("m");
          roomPossibilities.remove("n");
          roomPossibilities.remove("o");
          if(c1 && c2 && shop){
            if((int)Math.floor(Math.random()*5) == 0){
              structureLayout.get(i-1).set(j, "p");
              ylevelMap.add("c");
              ylevelStructure.add("q");
              System.out.println("c: "+ i+" "+j);
              shop=false;
              continue;
            } 
          }
          roomPossibilities.remove("c");
        }

        if(left){
          roomPossibilities.remove(" ");
          roomPossibilities.remove("a");
          roomPossibilities.remove("c");
          roomPossibilities.remove("d");
          roomPossibilities.remove("e");
          roomPossibilities.remove("g");
          roomPossibilities.remove("h");
          roomPossibilities.remove("k");
          if(d1 && d2 && shop){
            if((int)Math.floor(Math.random()*5) == 0){
              ylevelStructure.set(j-1, "r");
              ylevelMap.add("d");
              ylevelStructure.add("p");
              System.out.println("d: "+ i+" "+j);
              shop=false;
              continue;
            }
          }
          roomPossibilities.remove("d");
        } else {
          roomPossibilities.remove("f");
          roomPossibilities.remove("i");
          roomPossibilities.remove("j");
          roomPossibilities.remove("l");
          roomPossibilities.remove("m");
          roomPossibilities.remove("n");
          roomPossibilities.remove("o");
          if(b1 && b2 && shop){
            if((int)Math.floor(Math.random()*5) == 0){
              ylevelStructure.set(j-1, "p");
              ylevelMap.add("b");
              ylevelStructure.add("r");
              System.out.println("b: "+ i+" "+j);
              shop=false;
              continue;
            }
          }
          roomPossibilities.remove("b");
        }
        if(i==9){
          roomPossibilities.remove("a");
          roomPossibilities.remove("e");
          roomPossibilities.remove("h");
          roomPossibilities.remove("i");
          roomPossibilities.remove("k");
          roomPossibilities.remove("l");
          roomPossibilities.remove("m");
          roomPossibilities.remove("o");
        }
        if(j==9){
          roomPossibilities.remove("d");
          roomPossibilities.remove("f");
          roomPossibilities.remove("g");
          roomPossibilities.remove("h");
          roomPossibilities.remove("k");
          roomPossibilities.remove("l");
          roomPossibilities.remove("n");
          roomPossibilities.remove("o");
        }
        if(i == y && j == x){
          roomPossibilities.remove(" ");
          if(facing == "north"){
            roomPossibilities.remove("b");
            roomPossibilities.remove("d");
            roomPossibilities.remove("f");
            roomPossibilities.remove("g");
            roomPossibilities.remove("j");
            roomPossibilities.remove("n");
          }
          if(facing == "south"){
            roomPossibilities.remove("b");
            roomPossibilities.remove("d");
            roomPossibilities.remove("f");
            roomPossibilities.remove("h");
            roomPossibilities.remove("i");
            roomPossibilities.remove("l");
          }
          if(facing == "east"){
            roomPossibilities.remove("a");
            roomPossibilities.remove("c");
            roomPossibilities.remove("e");
            roomPossibilities.remove("g");
            roomPossibilities.remove("h");
            roomPossibilities.remove("k");
          }
          if(facing == "west"){
            roomPossibilities.remove("a");
            roomPossibilities.remove("c");
            roomPossibilities.remove("e");
            roomPossibilities.remove("i");
            roomPossibilities.remove("j");
            roomPossibilities.remove("m");
          }
          ylevelMap.add(roomPossibilities.get(new Random().nextInt(roomPossibilities.size())));
          ylevelStructure.add("s");
          continue;
        }
        // lav sjangse for at funksjonen fjernes f. eks hvis du ser sør og det er gang mot venstre men ikke opp fjernes alle muligheter
        ylevelMap.add(roomPossibilities.get(new Random().nextInt(roomPossibilities.size())));
        ylevelStructure.add(" ");
      }
      ui.mapString.add(uiMap);
      mapLayout.add(ylevelMap);
      structureLayout.add(ylevelStructure);
    }
    maps.add(mapLayout);
    structureLayouts.add(structureLayout);


    position = maps.get(floor-1).get(y).get(x);
    System.out.println(position);
  }

  public void move(String s){
    en.newMonster = null;
    key.z = "";
    key.x = "";
    key.c = "openInventory";
    switch(s){
      case "up":
        if(ui.roomImgLabel.getIcon().toString().equals("localFiles/img/rooms/crossroad.png") || ui.roomImgLabel.getIcon().toString().equals("localFiles/img/rooms/front.png") || ui.roomImgLabel.getIcon().toString().equals("localFiles/img/rooms/frontRight.png") || ui.roomImgLabel.getIcon().toString().equals("localFiles/img/rooms/frontLeft.png")){
          switch(facing){
            case "north": y=y-1; draw(); break;
            case "south": y++; draw(); break;
            case "east": x++; draw(); break;
            case "west": x=x-1; draw(); break;
          }
        }break;
      case "down":
      if(position == "a" && facing == "south" || position == "b" && facing == "west" || position == "c" && facing == "north" || position == "d" && facing == "east"){}else{
        switch(facing){
          case "north": y++; facing="south"; draw(); break;
          case "south": y=y-1; facing="north"; draw(); break;
          case "east": x=x-1; facing="west"; draw(); break;
          case "west": x++; facing="east"; draw(); break;
        }
      } break;
      case "right":
      if(ui.roomImgLabel.getIcon().toString().equals("localFiles/img/rooms/crossroad.png") || ui.roomImgLabel.getIcon().toString().equals("localFiles/img/rooms/frontRight.png") || ui.roomImgLabel.getIcon().toString().equals("localFiles/img/rooms/right.png") || ui.roomImgLabel.getIcon().toString().equals("localFiles/img/rooms/t-intersection.png")){
        switch(facing){
          case "north": x++; facing="east"; draw(); break;
          case "south": x=x-1; facing="west"; draw(); break;
          case "east": y++; facing="south"; draw(); break;
          case "west": y=y-1; facing="north"; draw(); break;
        }
      }break;
      case "left":
      if(ui.roomImgLabel.getIcon().toString().equals("localFiles/img/rooms/crossroad.png") || ui.roomImgLabel.getIcon().toString().equals("localFiles/img/rooms/frontLeft.png") || ui.roomImgLabel.getIcon().toString().equals("localFiles/img/rooms/left.png") || ui.roomImgLabel.getIcon().toString().equals("localFiles/img/rooms/t-intersection.png")){
        switch(facing){
          case "north": x=x-1; facing="west"; draw(); break;
          case "south": x++; facing="east"; draw(); break;
          case "east": y=y-1; facing="north"; draw(); break;
          case "west": y++; facing="south"; draw(); break;
        }
      }break;
    }
  }

  public void draw(){
    ui.mainTextArea.setText("");
    structure = structureLayouts.get(floor-1).get(y).get(x);
    // System.out.println(structureLayouts.get(floor-1));
    position = maps.get(floor-1).get(y).get(x);
    ui.mapString.get(y).set(x, position);
    ui.drawMap();
    // System.out.println("pos: "+position);
    ui.drawplayer();
    ui.drawRoom("blackScreen");
    ui.drawStructure("");
    ui.drawEncounter("","");

    Timer timer = new Timer(100, (ActionListener) new ActionListener() {
      public void actionPerformed(ActionEvent e){
        switch(position){
          case "a":
            switch(facing){
              case "north": ui.drawRoom("wall"); break;
              case "south": ui.drawRoom("front"); break;
            } break;
          case "b":
            switch(facing){
              case "east": ui.drawRoom("wall"); break;
              case "west": ui.drawRoom("front"); break;
            } break;
          case "c":
            switch(facing){
              case "north": ui.drawRoom("front"); break;
              case "south": ui.drawRoom("wall"); break;
            } break;
          case "d":
            switch(facing){
              case "east": ui.drawRoom("front"); break;
              case "west": ui.drawRoom("wall"); break;
            } break;
          case "e": ui.drawRoom("front"); break;
          case "f": ui.drawRoom("front"); break;
          case "g":
            switch(facing){
              case "south": ui.drawRoom("left"); break;
              case "west": ui.drawRoom("right"); break;
            } break;
          case "h":
            switch(facing){
              case "north": ui.drawRoom("right"); break;
              case "west": ui.drawRoom("left"); break;
            } break;
          case "i":
            switch(facing){
              case "north": ui.drawRoom("left"); break;
              case "east": ui.drawRoom("right"); break;
            } break;
          case "j":
            switch(facing){
              case "south": ui.drawRoom("right"); break;
              case "east": ui.drawRoom("left"); break;
            } break;
          case "k":
            switch(facing){
              case "north": ui.drawRoom("frontRight"); break;
              case "south": ui.drawRoom("frontLeft"); break;
              case "west": ui.drawRoom("t-intersection"); break;
            } break;
          case "l":
            switch(facing){
              case "north": ui.drawRoom("t-intersection"); break;
              case "east": ui.drawRoom("frontRight"); break;
              case "west": ui.drawRoom("frontLeft"); break;
            } break;
          case "m":
            switch(facing){
              case "north": ui.drawRoom("frontLeft"); break;
              case "south": ui.drawRoom("frontRight"); break;
              case "east": ui.drawRoom("t-intersection"); break;
            } break;
          case "n":
            switch(facing){
              case "south": ui.drawRoom("t-intersection"); break;
              case "east": ui.drawRoom("frontLeft"); break;
              case "west": ui.drawRoom("frontRight"); break;
            } break;
          case "o": ui.drawRoom("crossroad");break;
        }
        if(structure == " "){
          en.newEncounter();
        }
        switch(structure){
          case "p": ui.drawStructure("door"); key.z = "checkDoor"; break;
          case "q": if(position == "a" && facing == "south" || position == "b" && facing == "west" || position == "c" && facing == "north" || position == "d" && facing == "east"){}else{ui.drawStructure("openDoor"); key.z = "goThruDoor";} break;
          case "r": ui.drawStructure("shop"); key.z = "talkToShopkeeper"; break;
          case "s": ui.drawStructure("hole"); en.hole(); break;
        }
      }
    }); timer.setRepeats(false);
    timer.start();    
  }

  public String getFacing() {
    return facing;
  }
}


//hvis du går inn i en vegg resettes zxc