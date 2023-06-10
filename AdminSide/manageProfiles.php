<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="css/style.css" rel="stylesheet" type="text/css" />
  <title>Manage profiles</title>
</head>
<body>
<?php
  session_start();
  if($_SESSION["privileges"] != "all"){
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
  <h1 id="h1">Brukeroversikt</h1>
  <?php
    include 'php/connect.php';
    $sql = "SELECT * FROM `users`";
    $result = mysqli_query($conn, $sql);

    if (mysqli_num_rows($result) > 0) {
      echo "<h2>Admin brukere</h2>";
      while($row = mysqli_fetch_assoc($result)) {
        echo "
        <form method='POST' name='' action='php/update.php'>
        <input type='hidden' name='dbtable' value='user'>
        <label for='type'>Id: " . $row["id"]. "</label>
        <input type='hidden' name='id' value=" . $row["id"]. ">
        <label for='type'>Username:</label>
        <input type='text' name='username' style='width: 120px' value='" . $row["username"] . "'>
        <label for='type'>New Password:</label>
        <input type='text' name='password' style='width: 120px' value=''>
        <label>Privileges:</label>
        <input type='text' name='privileges' style='width: 45px' value='" . $row["privileges"]. "'>
        <label>Last login/create date: " . $row["usrLoginTime"]. "</label>
        <button type='hidden' name='action' value='update'>update</button>
        <button type='submit' name='action' value='remove'>delete</button>
        </form>";
        echo "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
      }
    } else {
      echo "0 results";
    }

    mysqli_close($conn);
  ?>
  <form method="POST" name="" action="php/insert.php">
    <input type="hidden" name="dbtable" value="user">
    <label>Username:</label>
    <input type="text" name="username" style='width: 120px;'>
    <label for="type">Password:</label>
    <input type="text" name="password" id="password" style='width: 120px'>
    <label>Privileges:</label>
    <input type="text" name="privileges" style='width: 45px'>
    <button type="submit" name="insert">Save</button>
  </form>
</body>
</html>