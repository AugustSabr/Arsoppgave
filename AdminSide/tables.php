<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="css/style.css" rel="stylesheet" type="text/css" />
  <title>Admin Side</title>
</head>
<body>
<?php
  include 'php/connect.php';
  session_start();
  if($_SESSION["privileges"] != "all" && $_SESSION["privileges"] != "game"){
    header("Location: admin.php");
  }
?>
  <span id="adminPage" class="loginORout">admin meny</span>
  <script>
    var adminPage = document.getElementById("adminPage");
    adminPage.onclick = function() {
      window.location = "admin.php";
    }
  </script>
  <h1 id="h1">Game items oversikt</h1>
  <div id="gameItems">
    <div>
      <?php
      include 'php/connect.php';
      $sql = 'SELECT * FROM "gameTables".weapons ORDER BY "weaponTier" ASC, "weaponEffect" ASC;';
      $result = pg_query($conn, $sql);

      if (0 < pg_num_rows($result)) {
        echo "<h2>Weapons</h2>";
        while($row = pg_fetch_row($result)) {
          echo "
          <form method='POST' name='' action='php/update.php'>
          <input type='hidden' name='dbtable' value='weapon'>
          <label for='type'>Id: " . $row[0]. "</label>
          <input type='hidden' name='id' value=" . $row[0]. ">
          <label for='type'>Tier:</label>
          <input type='text' name='weaponTier' style='width: 10px' value='" . $row[1] . "'>
          <label for='type'>Type:</label>
          <input type='text' name='weaponType' style='width: 85px' value='" . $row[2] . "'>
          <label>Damage:</label>
          <input type='number' name='weaponEffect' style='width: 20px' value='" . $row[3]. "'>
          <label>Path:</label>
          <input type='text' name='path' style='width: 85px' value='" . $row[4]. "'>
          <label>Value:</label>
          <input type='number' name='value' style='width: 20px' value='" . $row[5]. "'>
          <button type='hidden' name='action' value='update'>update</button>
          <button type='submit' name='action' value='remove'>delete</button>
          </form>";
          echo "------------------------------------------------------------------------------------------------------------------------";
        }
      } else {
        echo "0 results";
      }
      
      pg_close($conn);
      ?>
      <form method="POST" name="" action="php/insert.php">
        <input type="hidden" name="dbtable" value="weapon">
        <label>Tier:</label>
        <input type="number" name="weaponTier" style='width: 10px;'>
        <label for="type">Weapon type:</label>
        <input type="text" name="weaponType" id="weaponType" style='width: 85px'>
        <label>Weapon damage:</label>
        <input type="number" name="weaponEffect" style='width: 20px'>
        <label>Path:</label>
        <input type="text" name="path" style='width: 85px'>
        <label>Value:</label>
        <input type="number" name="value" style='width: 20px'>

        <button type="submit" name="insert">Save</button>
      </form>
    </div>

    <div>
      <?php
      include 'php/connect.php';
      $sql = 'SELECT * FROM "gameTables".armors ORDER BY "armorTier" ASC, "armorEffect" ASC;';
      $result = pg_query($conn, $sql);

      if (0 < pg_num_rows($result)) {
        echo "<h2>Armors</h2>";
        while($row = pg_fetch_row($result)) {
          echo "
          <form method='POST' name='' action='php/update.php'>
          <input type='hidden' name='dbtable' value='armor'>
          <label for='type'>Id: " . $row[0]. "</label>
          <input type='hidden' name='id' value=" . $row[0]. ">
          <label for='type'>Tier:</label>
          <input type='text' name='armorTier' style='width: 10px' value='" . $row[1] . "'>
          <label for='type'>Type:</label>
          <input type='text' name='armorType' style='width: 85px' value='" . $row[2] . "'>
          <label>Defence:</label>
          <input type='number' name='armorEffect' style='width: 20px' value='" . $row[3]. "'>
          <label>Path:</label>
          <input type='text' name='path' style='width: 85px' value='" . $row[4]. "'>
          <label>Value:</label>
          <input type='number' name='value' style='width: 20px' value='" . $row[5]. "'>
          <button type='hidden' name='action' value='update'>update</button>
          <button type='submit' name='action' value='remove'>delete</button>
          </form>";

          echo "-----------------------------------------------------------------------------------------------------------------------";
        }
      } else {
        echo "0 results";
      }

      pg_close($conn);
      ?>
      <form method="POST" action="php/insert.php">
        <input type='hidden' name='dbtable' value="armor">
        <label>Tier:</label>
        <input type="number" name="armorTier" style='width: 10px;'>
        <label for="type" >Armor type:</label>
        <input type="text" name="armorType" style='width: 85px'>
        <label>Armor defence:</label>
        <input type="number" name="armorEffect" style='width: 20px'>
        <label>Path:</label>
        <input type="text" name="path" style='width: 85px'>
        <label>Value:</label>
        <input type="number" name="value" style='width: 20px'>
        <button type="submit" name="insert">Save</button>
      </form>

      <?php
      include 'php/connect.php';
      $sql = 'SELECT * FROM "gameTables".blessings ORDER BY "id" ASC;';
      $result = pg_query($conn, $sql);

      if (0 < pg_num_rows($result)) {
        echo "<h2>Blessings</h2>";
        while($row = pg_fetch_row($result)) {
          echo "
          <form method='POST' name='' action='php/update.php'>
          <input type='hidden' name='dbtable' value='blessing'>
          <label for='type'>Id: " . $row[0]. "</label>
          <input type='hidden' name='id' value=" . $row[0]. ">
          <label for='type'>Type:</label>
          <input type='text' name='blessingType' style='width: 85px' value='" . $row[1] . "'>
          <label>Effect:</label>
          <input type='number' name='blessingEffect' style='width: 20px' value='" . $row[2]. "'>
          <button type='hidden' name='action' value='update'>update</button>
          <button type='submit' name='action' value='remove'>delete</button>
          </form>";

          echo "--------------------------------------------------------------------";
        }
      } else {
        echo "0 results";
      }

      pg_close($conn);
      ?>
      <form method="POST" name="" action="php/insert.php">
        <input type='hidden' name='dbtable' value="blessing">
        <label for="type">Blessing type:</label>
        <input type="text" name="blessingType" style='width: 85px'>
        <label>Blessing effect:</label>
        <input type="number" name="blessingEffect" style='width: 20px'>
        <button type="submit" name="insert">Save</button>
      </form>
    </div>

    <div>
      <?php
      include 'php/connect.php';
      $sql = 'SELECT * FROM "gameTables".enemys ORDER BY "enemyTier" ASC, "enemyHealth" ASC, "enemyDamage" ASC;';
      $result = pg_query($conn, $sql);

      if (0 < pg_num_rows($result)) {
        echo "<h2>Enemies</h2>";
        while($row = pg_fetch_row($result)) {
          echo "
          <form method='POST' name='' action='php/update.php'>
          <input type='hidden' name='dbtable' value='enemy'>
          <label for='type'>Id: " . $row[0]. "</label>
          <input type='hidden' name='id' value=" . $row[0]. ">
          <label for='type'>Tier:</label>
          <input type='text' name='enemyTier' style='width: 10px' value='" . $row[1] . "'>
          <label for='type'>Type:</label>
          <input type='text' name='enemyType' style='width: 85px' value='" . $row[2] . "'>
          <label>Health:</label>
          <input type='number' name='enemyHealth' style='width: 20px' value='" . $row[3]. "'>
          <label>Damage:</label>
          <input type='number' name='enemyDamage' style='width: 20px' value='" . $row[4]. "'>
          <label>Path:</label>
          <input type='text' name='path' style='width: 85px' value='" . $row[5]. "'>
          <label>Speed:</label>
          <input type='number' name='speed' style='width: 20px' value='" . $row[6]. "'>
          <label>Endurance:</label>
          <input type='number' name='endurance' style='width: 20px' value='" . $row[7]. "'>
          <button type='hidden' name='action' value='update'>update</button>
          <button type='submit' name='action' value='remove'>delete</button>
          </form>";

          echo "-----------------------------------------------------------------------------------------------------------------------------------------------------------------";
        }
      } else {
        echo "0 results";
      }

      pg_close($conn);
      ?>
      <form method="POST" name="" action="php/insert.php">
        <input type='hidden' name='dbtable' value="enemy">
        <label>Tier:</label>
        <input type="number" name="enemyTier" style='width: 10px;'>
        <label for="type">Enemy type:</label>
        <input type="text" name="enemyType" style='width: 85px'>
        <label>Health:</label>
        <input type="number" name="enemyHealth" style='width: 20px;'>
        <label>Damage:</label>
        <input type="number" name="enemyDamage" style='width: 20px'>
        <label>Path:</label>
        <input type="text" name="path" style='width: 85px'>
        <label>Speed:</label>
        <input type="number" name="speed" style='width: 20px'>
        <label>Endurance:</label>
        <input type="number" name="endurance" style='width: 20px'>
        <button type="submit" name="insert">Save</button>
      </form>
    </div>
  </div>
</body>
</html>
