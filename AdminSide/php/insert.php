<?php
  session_start();
  include 'connect.php';

  if (isset($_POST["insert"])){
    $dbtable = mysqli_real_escape_string($conn, $_POST['dbtable']);

    if($dbtable == 'weapon' || $dbtable == 'armor' || $dbtable == 'blessing'){
      $location = "Location: ../tables.php";
      $type = mysqli_real_escape_string($conn, $_POST[$dbtable.'Type']);

      $tierExists = mysqli_num_rows(mysqli_query($conn, "SHOW COLUMNS FROM ".$dbtable."s LIKE '".$dbtable."Tier'"));
      $effectExists = mysqli_num_rows(mysqli_query($conn, "SHOW COLUMNS FROM ".$dbtable."s LIKE '".$dbtable."Effect'"));
      $damageExists = mysqli_num_rows(mysqli_query($conn, "SHOW COLUMNS FROM ".$dbtable."s LIKE '".$dbtable."Damage'"));
      $healthExists = mysqli_num_rows(mysqli_query($conn, "SHOW COLUMNS FROM ".$dbtable."s LIKE '".$dbtable."Health'"));

      $sql = "INSERT INTO ".$dbtable."s (".$dbtable."Type) VALUES ('$type');";

      if($tierExists != 0){
        $tier =  mysqli_real_escape_string($conn, $_POST[$dbtable.'Tier']);
        $sql .= "UPDATE ".$dbtable."s SET ".$dbtable."Tier = $tier WHERE ".$dbtable."Type='$type';";
      }
      if($effectExists != 0){
        $effect =  mysqli_real_escape_string($conn, $_POST[$dbtable.'Effect']);
        $sql .= "UPDATE ".$dbtable."s SET ".$dbtable."Effect = $effect WHERE ".$dbtable."Type='$type';";
      }
      if($damageExists != 0){
        $damage =  mysqli_real_escape_string($conn, $_POST[$dbtable.'Damage']);
        $sql .= "UPDATE ".$dbtable."s SET ".$dbtable."Damage = $damage WHERE ".$dbtable."Type='$type';";
      }
      if($healthExists != 0){
        $health =  mysqli_real_escape_string($conn, $_POST[$dbtable.'Health']);
        $sql .= "UPDATE ".$dbtable."s SET ".$dbtable."Health = $health WHERE ".$dbtable."Type='$type';";
      }
    } else if($dbtable == 'user'){
      $location = "Location: ../manageProfiles.php";
      $username =  mysqli_real_escape_string($conn, $_POST['username']);
      $password =  mysqli_real_escape_string($conn, $_POST['password']);
      $hashedPwd = password_hash($password, PASSWORD_DEFAULT);
      $privileges =  mysqli_real_escape_string($conn, $_POST['privileges']);

      $sql = "INSERT INTO users (username, password, privileges) VALUES ('$username', '$hashedPwd', '$privileges');";
    } else if($dbtable == 'faq'){
      $location = "Location: ../index.php";
      $qName =  mysqli_real_escape_string($conn, $_POST['qName']);
      $qTitle =  mysqli_real_escape_string($conn, $_POST['qTitle']);
      $question = mysqli_real_escape_string($conn, $_POST['question']);

      $sql = "INSERT INTO faqs (qName, qTitle, question) VALUES ('$qName', '$qTitle', '$question');";
    }

    if (mysqli_multi_query($conn, $sql)) {
      header($location);
    } else {
      echo "Error: " . $sql . "<br>" . mysqli_error($conn);
    }
  } else {
    echo '<p>du skal ikke være her</p>';
  }

  if(!$_SESSION["privileges"] == "all" && !$_SESSION["privileges"] == "web" && !$_SESSION["privileges"] == "game"){
    header("Location: ../index.php");
  }