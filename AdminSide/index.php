<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="css/style.css" rel="stylesheet" type="text/css" />
  <title>Homepage</title>
  <?php
    session_start();
    if (isset($_SESSION['privileges'])) {
      unset($_SESSION['privileges']);
    }
    include 'php/connect.php';
  ?>
</head>
<body id="ibody">

<span id="modalButton" class="loginORout">Login as admin</span>

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
        if(isset($_POST['submit'])){
          //Gjøre om POST-data til variabler
          $usrn = mysqli_real_escape_string($conn, $_POST['username']);            
          $pwd = mysqli_real_escape_string($conn, $_POST['passord']);
        
          $sql = "SELECT * FROM users where username='$usrn'";
        
          $result = mysqli_query($conn, $sql)
            or die('Error connecting to database.');
        
          if (mysqli_num_rows($result) > 0) {
            while($row = mysqli_fetch_assoc($result)) {
              if (password_verify($pwd, $row["password"])) {
                mysqli_query($conn, "UPDATE users SET usrLoginTime = CURRENT_TIMESTAMP() WHERE username='$usrn'");
                $_SESSION["privileges"] = $row["privileges"];
                header("Location: admin.php");
              } else {
                echo '<p>feil brukernavn eller passord</p>';
              }
            }
          } else {
            echo '<p>feil brukernavn eller passord</p>';
          }
        }
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
      $sql = "SELECT * FROM `faqs`";
      $result = mysqli_query($conn, $sql);

      if (mysqli_num_rows($result) > 0) {
        while($row = mysqli_fetch_assoc($result)) {
          if ($row["seen"] == "y"){
            echo "
            <button type='button' class='faqCollapsible'>" . $row["qTitle"]. "</button>
            <div class='faqContent'>
              <h3>" . $row["qName"]. "</h3>
              <p>" . $row["question"]. "</p>

              <h4>" . $row["aName"]. "</h4>
              <p>" . $row["answer"]. "</p>
            </div>
            ";
          }
        }
      } else {
        echo "0 results";
      }

      mysqli_close($conn);
    ?>
  </div>
  <div>
    <h3 id="h1">Still ditt eget spørsmål:</h3>
    <form id="faqAsk" method="POST" name="" action="php/insert.php">
      <input type="hidden" name="dbtable" value="faq">
      <label>Name:</label>
      <input type='text' name='qName' style='width: 50%' placeholder="this wil be public information do not share sensetive information">
      <label>Question title:</label>
      <input type='text' name='qTitle' style='width: 50%' placeholder="a tilte to youre question">
      <label for='type'>Question:</label>
      <textarea name='question' cols='10' rows='5' style='width: 50%'></textarea>
      <button type="submit" name="insert" style='width: 100px'>Submit</button>
    </form>
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

  var insert = document.getElementsByName()
</script>



</body>
</html>