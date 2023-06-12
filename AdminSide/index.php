<?php
    session_start();
    if (isset($_SESSION['privileges'])) {
      unset($_SESSION['privileges']);
    }
    include './error_log_start.php';
  ?>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="css/style.css" rel="stylesheet" type="text/css" />
  <title>Homepage</title>
</head>
<body id="ibody">

<span id="modalButton" class="loginORout">Logg inn</span>

<!-- The Modal -->
<div id="myModal" class="modal" style="margin: 0px;">
  <!-- Modal content -->
  <div class="modal-content">
    <span class="close loginORout">&times;</span>
    <p>Vennligst logg inn:</p>
      <form method="post">
        <label for="username">Brukernavn:</label>
        <input type="text" name="username" /><br />
        <label for="passord">Passord:</label>
        <input type="password" name="passord" /><br />
        
        <input type="submit" value="Logg inn" name="submit" />
      </form>

      <?php
        include './php/connect.php';
        if(isset($_POST['submit'])){
          //Gjøre om POST-data til variabler
          $usrn = pg_escape_string($conn, $_POST['username']);            
          $pwd = pg_escape_string($conn, $_POST['passord']);
        
          $sql = 'SELECT * FROM "webTables"."users" where username='."'$usrn'";
        
          $result = pg_query($conn, $sql)
            or die('Error connecting to database.');
        
          if (0 < pg_num_rows($result)) {
            while($row = pg_fetch_row($result)) {
              if (password_verify($pwd, $row[2])) {
                pg_query($conn, 'UPDATE "webTables".users SET "usrLoginTime" = '."'now()'".' WHERE "id"='."'$row[0]';");
                $_SESSION["privileges"] = $row[3];
                header("Location: admin.php");
              } else {
                echo '<p>feil brukernavn eller passord</p>';
              }
            }
          } else {
            echo '<p>feil brukernavn eller passord</p>';
          }
        }
        pg_close($conn);
      ?>

  </div>
</div>
<script>
  //Modal
var modal = document.getElementById("myModal");
var openModal = document.getElementById("modalButton");
var closeModal = document.getElementsByClassName("close")[0];

openModal.onclick = function(){
  modal.style.display = "block";
  calculate()
}

closeModal.onclick = function() {
  modal.style.display = "none";
}

window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}
</script>

<div id="packaging">
  <h1>Velkommen til GAME sin hjemmeside</h1>
  <div id="faqDiv">
    <h2 id="h1">Ofte stilte spørsmål (FAQ)</h2>
    <?php
      include 'php/connect.php';
      $sql = 'SELECT * FROM "webTables".faqs;';
      $result = pg_query($conn, $sql);

      if (0 < pg_num_rows($result)) {
        while($row = pg_fetch_row($result)) {
          if ($row[6] == "y"){
            echo "
            <button type='button' class='faqCollapsible'>" . $row[2]. "</button>
            <div class='faqContent'>
              <h3>" . $row[1]. "</h3>
              <p>" . $row[3]. "</p>

              <h4>" . $row[4]. "</h4>
              <p>" . $row[6]. "</p>
            </div>
            ";
          }
        }
      } else {
        echo "0 results";
      }
      pg_close($conn);
    ?>
    <div>
      <h3 id="h1">Still ditt eget spørsmål:</h3>
      <form id="faqAsk" method="POST" name="" action="php/insert.php">
        <input type="hidden" name="dbtable" value="faq">
        <label>Navn:</label>
        <input type='text' name='qName' style='width: 50%' placeholder="denne informasjonen vil bli publisert så ikke del sensetiv informasjon">
        <label>Spørsmål overskrift:</label>
        <input type='text' name='qTitle' style='width: 50%' placeholder="en overskrift til spørsmålet">
        <label for='type'>Spørsmål:</label>
        <textarea name='question' cols='10' rows='5' style='width: 50%'></textarea>
        <button type="submit" name="insert" style='width: 100px' onclick="submitAlert()">Submit</button>
      </form>
    </div>
  </div>
</div>

<script>
  var faqColl = document.getElementsByClassName("faqCollapsible");
  var i;

  for (i = 0; i < faqColl.length; i++) {
    faqColl[i].addEventListener("click", function() {
      this.classList.toggle("faqActive");
      var faqContent = this.nextElementSibling;
      if (faqContent.style.display === "block") {
        faqContent.style.display = "none";
      } else {
        faqContent.style.display = "block";
      }
    });
  }

  function submitAlert(){
    alert("Takk for spørsmål, etter noen har sett på det og svart vil det komme på nettsiden");
  }
  //response to form
</script>



</body>
</html>