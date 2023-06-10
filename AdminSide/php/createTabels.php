<?php
  session_start();
  include 'connect.php';
  if($_SESSION["privileges"] != "all" && $_SESSION["privileges"] != "game"){
    header("Location: index.php");
  }
  $items = array("weapon", "armor", "blessing", "enemy");
  
  for ($x = 0; $x < count($items); $x++) {
    if($items[$x] == "blessing"){
      $sql = "CREATE TABLE IF NOT EXISTS ".$items[$x]."s (
        id INT AUTO_INCREMENT PRIMARY KEY,
        ".$items[$x]."Type VARCHAR(255),
        ".$items[$x]."Effect INT(11)
        );";
    } else if($items[$x] == "enemy"){
        $sql = "CREATE TABLE IF NOT EXISTS ".$items[$x]."s (
          id INT AUTO_INCREMENT PRIMARY KEY,
          ".$items[$x]."Tier INT(11),
          ".$items[$x]."Type VARCHAR(255),
          ".$items[$x]."Health INT(11),
          ".$items[$x]."Damage INT(11)
          );";
    }else {
      $sql = "CREATE TABLE IF NOT EXISTS ".$items[$x]."s (
        id INT AUTO_INCREMENT PRIMARY KEY,
        ".$items[$x]."Tier INT(11),
        ".$items[$x]."Type VARCHAR(255),
        ".$items[$x]."Effect INT(11)
        );";
    }

    if (!mysqli_multi_query($conn, $sql)) {
    echo "Error creating table: " . mysqli_error($conn);
    }
  }

