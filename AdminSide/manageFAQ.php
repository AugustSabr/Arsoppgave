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
      $sql = "SELECT * FROM `faqs`";
      $result = mysqli_query($conn, $sql);

      if (mysqli_num_rows($result) > 0) {
        while($row = mysqli_fetch_assoc($result)) {
          if($row["seen"] == "y"){
            echo "
            <form method='POST' name='' action='php/update.php'>
            <input type='hidden' name='dbtable' value='faq'>
            <label for='type'>Id: " . $row["id"]. "</label>
            <input type='hidden' name='id' value=" . $row["id"]. ">
            <label for='type'>Question Name:</label>
            <input type='text' name='qName' style='width: 100px' value='" . $row["qName"] . "'>
            <label for='type'>Question Title:</label>
            <input type='text' name='qTitle' style='width: 100px' value='" . $row["qTitle"] . "'>
            <label for='type'>Question:</label>
            <textarea name='question' cols='30' rows='1'>" . $row["question"] . "</textarea>
            <label>answer Name</label>
            <input type='text' name='aName' style='width: 100px' value='" . $row["aName"]. "'>
            <label>answer</label>
            <textarea name='answer' cols='30' rows='1'>" . $row["answer"] . "</textarea>
            <label>besvart: y/n</label>
            <input type='text' name='seen' style='width: 20px' value='" . $row["seen"]. "'>
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
      $result = mysqli_query($conn, $sql);
      if (mysqli_num_rows($result) > 0) {
        while($row = mysqli_fetch_assoc($result)) {
          if($row["seen"] != "y"){
            echo "
            <form method='POST' name='' action='php/update.php'>
            <input type='hidden' name='dbtable' value='faq'>
            <label for='type'>Id: " . $row["id"]. "</label>
            <input type='hidden' name='id' value=" . $row["id"]. ">
            <label for='type'>Question Name:</label>
            <input type='text' name='qName' style='width: 100px' value='" . $row["qName"] . "'>
            <label for='type'>Question Title:</label>
            <input type='text' name='qTitle' style='width: 100px' value='" . $row["qTitle"] . "'>
            <label for='type'>Question:</label>
            <textarea name='question' cols='30' rows='1'>" . $row["question"] . "</textarea>
            <label>answer Name</label>
            <input type='text' name='aName' style='width: 100px' value='" . $row["aName"]. "'>
            <label>answer</label>
            <textarea name='answer' cols='30' rows='1'>" . $row["answer"] . "</textarea>
            <label>besvart: y/n</label>
            <input type='text' name='seen' style='width: 20px' value='" . $row["seen"]. "'>
            <button type='hidden' name='action' value='update'>update</button>
            <button type='submit' name='action' value='remove'>delete</button>
            </form>";
            echo "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
          }
        }
      }  
    ?>
  </div>
</body>
</html>