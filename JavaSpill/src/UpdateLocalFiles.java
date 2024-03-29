// libaries fra postgresql-42.6.0.jar
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.io.FileWriter;

public class UpdateLocalFiles {

  // info-en du trenger for å koble til database;
  private String ip = "84.215.89.206";
  private String port = "5432";
  private String username = "Gameuser";
  private String password = "K3A*M5Y3!*iP^7";
  private String database = "game4";

  private String[] categories = {"weapon", "armor", "enemy"};
  
  public UpdateLocalFiles(){
    try {
      Connection con = getConection();
      for (int i = 0; i < categories.length; i++){// repeterer dette for alle relevante tabellene
        PreparedStatement statement = con.prepareStatement("SELECT * FROM \"gameTables\"."+ categories[i]+"s ORDER BY \"id\" ASC;");
        ResultSet result = statement.executeQuery();
        
        // sjekker hvilke kolloner som finnes i tabbelen for å vite hva den skal lagre
        Boolean tierExists = con.prepareStatement("SELECT column_name FROM information_schema.columns WHERE table_name='" + categories[i] + "s' and column_name='" + categories[i] + "Tier';").executeQuery().next();
        Boolean effectExists = con.prepareStatement("SELECT column_name FROM information_schema.columns WHERE table_name='" + categories[i] + "s' and column_name='" + categories[i] + "Effect';").executeQuery().next();
        Boolean damageExists = con.prepareStatement("SELECT column_name FROM information_schema.columns WHERE table_name='" + categories[i] + "s' and column_name='" + categories[i] + "Damage';").executeQuery().next();
        Boolean healthExists = con.prepareStatement("SELECT column_name FROM information_schema.columns WHERE table_name='" + categories[i] + "s' and column_name='" + categories[i] + "Health';").executeQuery().next();
        Boolean speedExists = con.prepareStatement("SELECT column_name FROM information_schema.columns WHERE table_name='" + categories[i] + "s' and column_name='speed';").executeQuery().next();
        Boolean pathExists = con.prepareStatement("SELECT column_name FROM information_schema.columns WHERE table_name='" + categories[i] + "s' and column_name='path';").executeQuery().next();
        Boolean valueExists = con.prepareStatement("SELECT column_name FROM information_schema.columns WHERE table_name='" + categories[i] + "s' and column_name='value';").executeQuery().next();
        Boolean enduranceExists = con.prepareStatement("SELECT column_name FROM information_schema.columns WHERE table_name='" + categories[i] + "s' and column_name='endurance';").executeQuery().next();

        ArrayList<String> array = new ArrayList<String>();
        while(result.next()){//går gjennom alle rader i tabbelen
          array.add(result.getString(categories[i] + "Type"));

          // hvis det finnnes legger vi det til i tabbellen
          if(pathExists != false){
            array.add(result.getString("path"));
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
          if(enduranceExists != false){
            array.add(result.getString("endurance"));
          }
          if(speedExists != false){
            array.add(result.getString("speed"));
          }
          if(valueExists != false){
            array.add(result.getString("value"));
          }
          if(tierExists != false){
            array.add(result.getString(categories[i] + "Tier"));
          }
        }

        //lager og skriver i en fil
        FileWriter myWriter = new FileWriter("localFiles/txt/" + categories[i] +".txt");
        for(int g = 0; g < array.size(); g++){
          myWriter.write(array.get(g) + System.lineSeparator());
        }
        myWriter.close();
      }
      System.err.println("\nSuccessfully updated locale files.");
    } catch (Exception e) {
      System.err.println("\nCouldnt update locale files. Continues with old files.");
      System.err.println("Error message: " + e + "\n");
      // e.printStackTrace();
    }
  }

  public Connection getConection() throws Exception{
    Connection conn = null;
    try {
      Class.forName("org.postgresql.Driver");
      conn = DriverManager.getConnection("jdbc:postgresql://"+ip+":" + port + "/" + database, username, password);
    } catch (SQLException  e) {
      System.err.println("\nCouldnt connect to the database. Do you have internet?");
      System.err.println("Error message: " + e.getMessage() + "\n");
    }
    return conn;
  }
}