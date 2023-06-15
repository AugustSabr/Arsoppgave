<?php
  session_start();
  include 'connect.php';
  if(!$_SESSION["privileges"] == "all" && !$_SESSION["privileges"] == "web" && !$_SESSION["privileges"] == "game"){
    header("Location: ../index.php");
  }
  if (isset($_POST["action"])){
    $dbtable = $_POST['dbtable'];
    $id = $_POST['id'];

    if($dbtable == 'weapon' || $dbtable == 'armor' || $dbtable == 'blessing'|| $dbtable == 'enemy'){
      $location = "Location: ../tables.php";
      $schema = '"gameTables"';

      if ($_POST["action"] == 'update'){
        $tierExists = pg_num_rows(pg_query($conn, "SELECT column_name FROM information_schema.columns WHERE table_name='".$dbtable."s' and column_name='".$dbtable."Tier';"));
        $effectExists = pg_num_rows(pg_query($conn, "SELECT column_name FROM information_schema.columns WHERE table_name='".$dbtable."s' and column_name='".$dbtable."Effect';"));
        $damageExists = pg_num_rows(pg_query($conn, "SELECT column_name FROM information_schema.columns WHERE table_name='".$dbtable."s' and column_name='".$dbtable."Damage';"));
        $healthExists = pg_num_rows(pg_query($conn, "SELECT column_name FROM information_schema.columns WHERE table_name='".$dbtable."s' and column_name='".$dbtable."Health';"));
        $speedExists = pg_num_rows(pg_query($conn, "SELECT column_name FROM information_schema.columns WHERE table_name='".$dbtable."s' and column_name='speed';"));
        $pathExists = pg_num_rows(pg_query($conn, "SELECT column_name FROM information_schema.columns WHERE table_name='".$dbtable."s' and column_name='path';"));
        $valueExists = pg_num_rows(pg_query($conn, "SELECT column_name FROM information_schema.columns WHERE table_name='".$dbtable."s' and column_name='value';"));
        $enduranceExists = pg_num_rows(pg_query($conn, "SELECT column_name FROM information_schema.columns WHERE table_name='".$dbtable."s' and column_name='endurance';"));

        $type = pg_escape_string($conn, $_POST[$dbtable.'Type']);
        $sql = 'UPDATE "gameTables".'.$dbtable.'s SET "'.$dbtable.'Type" = '."'$type' WHERE id='$id';";
        if($tierExists != 0){
          $tier =  pg_escape_string($conn, $_POST[$dbtable.'Tier']);
          $sql .= 'UPDATE "gameTables".'.$dbtable.'s SET "'.$dbtable.'Tier" = '."'$tier' WHERE id='$id';";
        }
        if($effectExists != 0){
          $effect =  pg_escape_string($conn, $_POST[$dbtable.'Effect']);
          $sql .= 'UPDATE "gameTables".'.$dbtable.'s SET "'.$dbtable.'Effect" = '."'$effect' WHERE id='$id';";
        }
        if($damageExists != 0){
          $damage =  pg_escape_string($conn, $_POST[$dbtable.'Damage']);
          $sql .= 'UPDATE "gameTables".'.$dbtable.'s SET "'.$dbtable.'Damage" = '."'$damage' WHERE id='$id';";
        }
        if($healthExists != 0){
          $health =  pg_escape_string($conn, $_POST[$dbtable.'Health']);
          $sql .= 'UPDATE "gameTables".'.$dbtable.'s SET "'.$dbtable.'Health" = '."'$health' WHERE id='$id';";
        }
        if($speedExists != 0){
          $speed =  pg_escape_string($conn, $_POST['speed']);
          $sql .= 'UPDATE "gameTables".'.$dbtable.'s SET speed = '."'$speed' WHERE id='$id';";
        }
        if($pathExists != 0){
          $path =  pg_escape_string($conn, $_POST['path']);
          $sql .= 'UPDATE "gameTables".'.$dbtable.'s SET path = '."'$path' WHERE id='$id';";
        }
        if($valueExists != 0){
          $value =  pg_escape_string($conn, $_POST['value']);
          $sql .= 'UPDATE "gameTables".'.$dbtable.'s SET value = '."'$value' WHERE id='$id';";
        }
        if($enduranceExists != 0){
          $endurance =  pg_escape_string($conn, $_POST['endurance']);
          $sql .= 'UPDATE "gameTables".'.$dbtable.'s SET endurance = '."'$endurance' WHERE id='$id';";
        }
      }
    } else if($dbtable == 'user'){
      $location = "Location: ../manageProfiles.php";
      $schema = '"webTables"';
      if ($_POST["action"] == 'update'){
        $username =  pg_escape_string($conn, $_POST['username']);
        $password =  pg_escape_string($conn, $_POST['password']);
        $privileges =  pg_escape_string($conn, $_POST['privileges']);
        if(empty($password)){
          $sql = 'UPDATE "webTables".'.$dbtable.'s SET "username" = '."'$username'".', "privileges" = '."'$privileges' WHERE id='$id';";
        } else {
          $hashedPwd = password_hash($password, PASSWORD_DEFAULT);
          $sql = 'UPDATE "webTables".'.$dbtable.'s SET "username" = '."'$username'".', "password" = '."'$hashedPwd'".', "privileges" = '."'$privileges' WHERE id='$id';";
        }
      }
    } else if($dbtable == 'faq'){
      $location = "Location: ../manageFAQ.php";
      $schema = '"webTables"';
      if ($_POST["action"] == 'update'){
        $qName =  pg_escape_string($conn, $_POST['qName']);
        $qTitle =  pg_escape_string($conn, $_POST['qTitle']);
        $question =  pg_escape_string($conn, $_POST['question']);
        $aName =  pg_escape_string($conn, $_POST['aName']);
        $answer =  pg_escape_string($conn, $_POST['answer']);
        $show =  pg_escape_string($conn, $_POST['show']);

        $sql = 'UPDATE "webTables".'.$dbtable.'s SET "qName" = '."'$qName'".', "qTitle" = '."'$qTitle'".', "question" = '."'$question'".', "aName" = '."'$aName'".', "answer" = '."'$answer'".', "show" = '."'$show' WHERE id='$id';";
      }
    }
    if ($_POST["action"] == 'remove'){
      $sql = "DELETE FROM $schema.".$dbtable."s WHERE id='$id';";
    }
    if (pg_query($conn, $sql)) {
      pg_close($conn);
      header($location);
    } else {
      echo "Error ";
      pg_close($conn);
    }
  } else {
    echo '<p>du skal ikke være her</p>';
  }