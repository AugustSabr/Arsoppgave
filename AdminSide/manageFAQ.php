<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="css/style.css" rel="stylesheet" type="text/css" />
  <title>Manage FAQ</title>
</head>
<body>
<?php
  session_start();
  if($_SESSION["privileges"] != "all" && $_SESSION["privileges"] != "web"){
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
  <div id="publicFAQ">
    <h3>godkjente spørsmål</h3>
    <?php
      include 'php/connect.php';
      $sql = 'SELECT * FROM "webTables".faqs  ORDER BY "id" ASC;';
      $result = pg_query($conn, $sql);

      if (0 < pg_num_rows($result)) {
        while($row = pg_fetch_row($result)) {
          if($row[6] == "y"){
            echo "
            <form method='POST' name='' action='php/update.php'>
            <input type='hidden' name='dbtable' value='faq'>
            <label for='type'>Id: " . $row[0]. "</label>
            <input type='hidden' name='id' value=" . $row[0]. ">
            <label for='type'>Question Name:</label>
            <input type='text' name='qName' style='width: 100px' value='" . $row[1] . "'>
            <label for='type'>Question Title:</label>
            <input type='text' name='qTitle' style='width: 100px' value='" . $row[2] . "'>
            <label for='type'>Question:</label>
            <textarea name='question' cols='30' rows='1'>" . $row[3] . "</textarea>
            <label>answer Name</label>
            <input type='text' name='aName' style='width: 100px' value='" . $row[4]. "'>
            <label>answer</label>
            <textarea name='answer' cols='30' rows='1'>" . $row[5] . "</textarea>
            <label>besvart: y/n</label>
            <input type='text' name='show' style='width: 20px' value='" . $row[6]. "'>
            <button type='hidden' name='action' value='update'>update</button>
            <button type='submit' name='action' value='remove'>delete</button>
            </form>";
            echo "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
          }
        }
      } 
    ?>
  </div>
  <div id="privateFAQ">
    <h3>usvarte spørsmål</h3>
    <?php
      $result = pg_query($conn, $sql);
      if (0 < pg_num_rows($result)) {
        while($row = pg_fetch_row($result)) {
          if($row[6] != "y"){
            echo "
            <form method='POST' name='' action='php/update.php'>
            <input type='hidden' name='dbtable' value='faq'>
            <label for='type'>Id: " . $row[0]. "</label>
            <input type='hidden' name='id' value=" . $row[0]. ">
            <label for='type'>Question Name:</label>
            <input type='text' name='qName' style='width: 100px' value='" . $row[1] . "'>
            <label for='type'>Question Title:</label>
            <input type='text' name='qTitle' style='width: 100px' value='" . $row[2] . "'>
            <label for='type'>Question:</label>
            <textarea name='question' cols='30' rows='1'>" . $row[3] . "</textarea>
            <label>answer Name</label>
            <input type='text' name='aName' style='width: 100px' value='" . $row[4]. "'>
            <label>answer</label>
            <textarea name='answer' cols='30' rows='1'>" . $row[5] . "</textarea>
            <label>besvart: y/n</label>
            <input type='text' name='show' style='width: 20px' value='" . $row[6]. "'>
            <button type='hidden' name='action' value='update'>update</button>
            <button type='submit' name='action' value='remove'>delete</button>
            </form>";
            echo "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
          }
        }
      }
      pg_close($conn);
    ?>
  </div>
</body>
</html>