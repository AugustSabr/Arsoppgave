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
      $sql = "SELECT * FROM `Weapons`";
      $result = mysqli_query($conn, $sql);

      if (mysqli_num_rows($result) > 0) {
        echo "<h2>Weapons</h2>";
        while($row = mysqli_fetch_assoc($result)) {
          echo "
          <form method='POST' name='' action='php/update.php'>
          <input type='hidden' name='dbtable' value='weapon'>
          <label for='type'>Id: " . $row["id"]. "</label>
          <input type='hidden' name='id' value=" . $row["id"]. ">
          <label for='type'>Tier:</label>
          <input type='text' name='weaponTier' style='width: 10px' value='" . $row["weaponTier"] . "'>
          <label for='type'>Type:</label>
          <input type='text' name='weaponType' style='width: 95px' value='" . $row["weaponType"] . "'>
          <label>Damage:</label>
          <input type='number' name='weaponEffect' style='width: 30px' value='" . $row["weaponEffect"]. "'>
          <button type='hidden' name='action' value='update'>update</button>
          <button type='submit' name='action' value='remove'>delete</button>
          </form>";
          echo "------------------------------------------------------------------------------------";
        }
      } else {
        echo "0 results";
      }
      
      mysqli_close($conn);
      ?>
      <form method="POST" name="" action="php/insert.php">
        <input type="hidden" name="dbtable" value="weapon">
        <label>Tier:</label>
        <input type="number" name="weaponTier" style='width: 10px;'>
        <label for="type">Weapon type:</label>
        <input type="text" name="weaponType" id="weaponType" style='width: 95px'>
        <label>Weapon damage:</label>
        <input type="number" name="weaponEffect" style='width: 30px'>

        <button type="submit" name="insert">Save</button>
      </form>
    </div>

    <div>
      <?php
      include 'php/connect.php';
      $sql = "SELECT * FROM `Armors`";
      $result = mysqli_query($conn, $sql);

      if (mysqli_num_rows($result) > 0) {
        echo "<h2>Armors</h2>";
        while($row = mysqli_fetch_assoc($result)) {
          echo "
          <form method='POST' name='' action='php/update.php'>
          <input type='hidden' name='dbtable' value='armor'>
          <label for='type'>Id: " . $row["id"]. "</label>
          <input type='hidden' name='id' value=" . $row["id"]. ">
          <label for='type'>Tier:</label>
          <input type='text' name='armorTier' style='width: 10px' value='" . $row["armorTier"] . "'>
          <label for='type'>Type:</label>
          <input type='text' name='armorType' style='width: 95px' value='" . $row["armorType"] . "'>
          <label>Defence:</label>
          <input type='number' name='armorEffect' style='width: 30px' value='" . $row["armorEffect"]. "'>
          <button type='hidden' name='action' value='update'>update</button>
          <button type='submit' name='action' value='remove'>delete</button>
          </form>";

          echo "------------------------------------------------------------------------------------";
        }
      } else {
        echo "0 results";
      }

      mysqli_close($conn);
      ?>
      <form method="POST" action="php/insert.php">
        <input type='hidden' name='dbtable' value="armor">
        <label>Tier:</label>
        <input type="number" name="armorTier" style='width: 10px;'>
        <label for="type" >Armor type:</label>
        <input type="text" name="armorType" style='width: 95px'>
        <label>Armor defence:</label>
        <input type="number" name="armorEffect" style='width: 30px'>

        <button type="submit" name="insert">Save</button>
      </form>

      <?php
      include 'php/connect.php';
      $sql = "SELECT * FROM `Blessings`";
      $result = mysqli_query($conn, $sql);

      if (mysqli_num_rows($result) > 0) {
        echo "<h2>Blessings</h2>";
        while($row = mysqli_fetch_assoc($result)) {
          echo "
          <form method='POST' name='' action='php/update.php'>
          <input type='hidden' name='dbtable' value='blessing'>
          <label for='type'>Id: " . $row["id"]. "</label>
          <input type='hidden' name='id' value=" . $row["id"]. ">
          <label for='type'>Type:</label>
          <input type='text' name='blessingType' style='width: 95px' value='" . $row["blessingType"] . "'>
          <label>Effect:</label>
          <input type='number' name='blessingEffect' style='width: 30px' value='" . $row["blessingEffect"]. "'>
          <button type='hidden' name='action' value='update'>update</button>
          <button type='submit' name='action' value='remove'>delete</button>
          </form>";

          echo "----------------------------------------------------------------------";
        }
      } else {
        echo "0 results";
      }

      mysqli_close($conn);
      ?>
      <form method="POST" name="" action="php/insert.php">
        <input type='hidden' name='dbtable' value="blessing">
        <label for="type">Blessing type:</label>
        <input type="text" name="blessingType" style='width: 95px'>
        <label>Blessing effect:</label>
        <input type="number" name="blessingEffect" style='width: 30px'>

        <button type="submit" name="insert">Save</button>
      </form>
    </div>

    <div>
      <?php
      include 'php/connect.php';
      $sql = "SELECT * FROM `enemys`";
      $result = mysqli_query($conn, $sql);

      if (mysqli_num_rows($result) > 0) {
        echo "<h2>Enemies</h2>";
        while($row = mysqli_fetch_assoc($result)) {
          echo "
          <form method='POST' name='' action='php/update.php'>
          <input type='hidden' name='dbtable' value='enemy'>
          <label for='type'>Id: " . $row["id"]. "</label>
          <input type='hidden' name='id' value=" . $row["id"]. ">
          <label for='type'>Tier:</label>
          <input type='text' name='enemyTier' style='width: 10px' value='" . $row["enemyTier"] . "'>
          <label for='type'>Type:</label>
          <input type='text' name='enemyType' style='width: 95px' value='" . $row["enemyType"] . "'>
          <label>Health:</label>
          <input type='number' name='enemyHealth' style='width: 30px' value='" . $row["enemyHealth"]. "'>
          <label>Damage:</label>
          <input type='number' name='enemyDamage' style='width: 30px' value='" . $row["enemyDamage"]. "'>
          <button type='hidden' name='action' value='update'>update</button>
          <button type='submit' name='action' value='remove'>delete</button>
          </form>";

          echo "------------------------------------------------------------------------------------------------------";
        }
      } else {
        echo "0 results";
      }

      mysqli_close($conn);
      ?>
      <form method="POST" name="" action="php/insert.php">
        <input type='hidden' name='dbtable' value="enemy">
        <label>Tier:</label>
        <input type="number" name="enemyTier" style='width: 10px;'>
        <label for="type">Enemy type:</label>
        <input type="text" name="enemyType" style='width: 95px'>
        <label>Health:</label>
        <input type="number" name="enemyHealth" style='width: 30px;'>
        <label>Damage:</label>
        <input type="number" name="enemyDamage" style='width: 30px'>

        <button type="submit" name="insert">Save</button>
      </form>
    </div>
  </div>
</body>
</html>
