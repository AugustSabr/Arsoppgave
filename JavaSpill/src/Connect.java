// libaries fra mysql-connector-j-8.0.31.jar
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.io.FileWriter;

public class Connect {

  // info-en du trenger for å koble til database;
  String ip = "192.168.0.106";
  String port = "3306";
  String username = "Klient";
  String password = "n]ua2BeXUF!ci[mK";
  String database = "game4";

  String[] categories = {"weapon", "armor", "blessing", "enemy"};
  
  public Connect(){
    get();
  }

  public void get(){
    try {
      Connection con = getConection();
      for (int i = 0; i < categories.length; i++){// repeterer dette for alle relevante tabellene

        PreparedStatement statement = con.prepareStatement("SELECT * FROM `" + categories[i] + "s`");
        ResultSet result = statement.executeQuery();

        // sjekker hvilke kolloner som finnes i tabbelen for å vite hva den skal lagre
        Boolean tierExists = con.prepareStatement("SHOW COLUMNS FROM " + categories[i] + "s LIKE '" + categories[i] + "Tier'").executeQuery().next();
        Boolean effectExists = con.prepareStatement("SHOW COLUMNS FROM " + categories[i] + "s LIKE '" + categories[i] + "Effect'").executeQuery().next();
        Boolean damageExists = con.prepareStatement("SHOW COLUMNS FROM " + categories[i] + "s LIKE '" + categories[i] + "Damage'").executeQuery().next();
        Boolean healthExists = con.prepareStatement("SHOW COLUMNS FROM " + categories[i] + "s LIKE '" + categories[i] + "Health'").executeQuery().next();

        ArrayList<String> array = new ArrayList<String>();
        while(result.next()){//går gjennom alle rader i tabbelen
          array.add(result.getString(categories[i] + "Type"));

          // hvis det finnnes legger vi det til i tabbellen
          if(tierExists != false){
            array.add(result.getString(categories[i] + "Tier"));
          }
          if(effectExists != false){
            array.add(result.getString(categories[i] + "Effect"));
          }
          if(damageExists != false){
            array.add(result.getString(categories[i] + "Damage"));
          }
          if(healthExists != false){
            array.add(result.getString(categories[i] + "Health"));
          }
        }

        //lager og skriver i en fil
        try {
          FileWriter myWriter = new FileWriter("categories/" + categories[i] +".txt");
          for(int g = 0; g < array.size(); g++){
            myWriter.write(array.get(g) + System.lineSeparator());
          }
          myWriter.close();
        } catch (Exception e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
      }

    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public Connection getConection() throws Exception{// funksjonen for å koble till databasen, denne skjønner jeg ikke helt
    try {
      String driver = "com.mysql.jdbc.Driver";
      String url = "jdbc:mysql://" + ip + ":" + port + "/" + database;
      Class.forName(driver);

      Connection conn = DriverManager.getConnection(url, username, password);

      return conn;
    } catch (Exception e) {
      System.out.println(e);
      return null;
    }
  }
}


