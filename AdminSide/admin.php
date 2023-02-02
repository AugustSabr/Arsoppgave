<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="css/style.css" rel="stylesheet" type="text/css" />
  <title>Admin page</title>
</head>
<body>
<?php
  session_start();
  if(!$_SESSION["privileges"] == "all" && !$_SESSION["privileges"] == "web" && !$_SESSION["privileges"] == "game"){
    header("Location: index.php");
  }
?>
<span id="logout" class="loginORout">Logout</span>
<h1>Admin menu</h1>
<button id="faq">manage faq</button>
<button id="game">game items</button>
<button id="profile">manage profiles</button>
<script>
    var logout = document.getElementById("logout");
    var faq = document.getElementById("faq");
    var game = document.getElementById("game");
    var profile = document.getElementById("profile");
    
    logout.onclick = function() {
      window.location = "index.php";
    }
    faq.onclick = function() {
      window.location = "manageFAQ.php";
    }
    game.onclick = function() {
      window.location = "tables.php";
    }
    profile.onclick = function() {
      window.location = "manageProfiles.php";
    }
</script>
</body>
</html>