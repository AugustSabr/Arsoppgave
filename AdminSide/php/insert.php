<?php
  session_start();
  include 'connect.php';

  if (isset($_POST["insert"])){
    $dbtable = pg_escape_string($conn, $_POST['dbtable']);

    if($dbtable == 'weapon' || $dbtable == 'armor' || $dbtable == 'blessing'|| $dbtable == 'enemy'){
      $location = "Location: ../tables.php";
      $type = pg_escape_string($conn, $_POST[$dbtable.'Type']);

      $tierExists = pg_num_rows(pg_query($conn, "SELECT column_name FROM information_schema.columns WHERE table_name='".$dbtable."s' and column_name='".$dbtable."Tier';"));
      $effectExists = pg_num_rows(pg_query($conn, "SELECT column_name FROM information_schema.columns WHERE table_name='".$dbtable."s' and column_name='".$dbtable."Effect';"));
      $damageExists = pg_num_rows(pg_query($conn, "SELECT column_name FROM information_schema.columns WHERE table_name='".$dbtable."s' and column_name='".$dbtable."Damage';"));
      $healthExists = pg_num_rows(pg_query($conn, "SELECT column_name FROM information_schema.columns WHERE table_name='".$dbtable."s' and column_name='".$dbtable."Health';"));
      $speedExists = pg_num_rows(pg_query($conn, "SELECT column_name FROM information_schema.columns WHERE table_name='".$dbtable."s' and column_name='speed';"));
      $pathExists = pg_num_rows(pg_query($conn, "SELECT column_name FROM information_schema.columns WHERE table_name='".$dbtable."s' and column_name='path';"));
      $valueExists = pg_num_rows(pg_query($conn, "SELECT column_name FROM information_schema.columns WHERE table_name='".$dbtable."s' and column_name='value';"));
      $enduranceExists = pg_num_rows(pg_query($conn, "SELECT column_name FROM information_schema.columns WHERE table_name='".$dbtable."s' and column_name='endurance';"));

      $sql = 'INSERT INTO "gameTables".'.$dbtable.'s ("'.$dbtable.'Type") VALUES ('."'".$type."'".');';
      if($tierExists != 0){
        $tier =  pg_escape_string($conn, $_POST[$dbtable.'Tier']);
        $sql .= 'UPDATE "gameTables".'.$dbtable.'s SET "'.$dbtable.'Tier" = '."'$tier' WHERE ".'"'.$dbtable.'Type"='."'$type';";
      }
      if($effectExists != 0){
        $effect =  pg_escape_string($conn, $_POST[$dbtable.'Effect']);
        $sql .= 'UPDATE "gameTables".'.$dbtable.'s SET "'.$dbtable.'Effect" = '."'$effect' WHERE ".'"'.$dbtable.'Type"='."'$type';";
      }
      if($damageExists != 0){
        $damage =  pg_escape_string($conn, $_POST[$dbtable.'Damage']);
        $sql .= 'UPDATE "gameTables".'.$dbtable.'s SET "'.$dbtable.'Damage" = '."'$damage' WHERE ".'"'.$dbtable.'Type"='."'$type';";
      }
      if($healthExists != 0){
        $health =  pg_escape_string($conn, $_POST[$dbtable.'Health']);
        $sql .= 'UPDATE "gameTables".'.$dbtable.'s SET "'.$dbtable.'Health" = '."'$health' WHERE ".'"'.$dbtable.'Type"='."'$type';";
      }
      if($speedExists != 0 && !pg_escape_string($conn, $_POST['speed']) == ""){
        $speed =  pg_escape_string($conn, $_POST['speed']);
        $sql .= 'UPDATE "gameTables".'.$dbtable.'s SET speed = '."'$speed' WHERE ".'"'.$dbtable.'Type"='."'$type';";
      }
      if($pathExists != 0 && !pg_escape_string($conn, $_POST['path']) == ""){
        $path =  pg_escape_string($conn, $_POST['path']);
        $sql .= 'UPDATE "gameTables".'.$dbtable.'s SET path = '."'$path' WHERE ".'"'.$dbtable.'Type"='."'$type';";
      }
      if($valueExists != 0){
        $value =  pg_escape_string($conn, $_POST['value']);
        $sql .= 'UPDATE "gameTables".'.$dbtable.'s SET value = '."'$value' WHERE ".'"'.$dbtable.'Type"='."'$type';";
      }
      if($enduranceExists != 0 && !pg_escape_string($conn, $_POST['endurance']) == ""){
        $endurance =  pg_escape_string($conn, $_POST['endurance']);
        $sql .= 'UPDATE "gameTables".'.$dbtable.'s SET endurance = '."'$endurance' WHERE ".'"'.$dbtable.'Type"='."'$type';";
      }
    } else if($dbtable == 'user'){
      $location = "Location: ../manageProfiles.php";
      $username =  pg_escape_string($conn, $_POST['username']);
      $password =  pg_escape_string($conn, $_POST['password']);
      $hashedPwd = password_hash($password, PASSWORD_DEFAULT);
      $privileges =  pg_escape_string($conn, $_POST['privileges']);

      $sql = 'INSERT INTO "webTables".users ("username", "password", "privileges") VALUES '."('$username', '$hashedPwd', '$privileges');";
    } else if($dbtable == 'faq'){
      $location = "Location: ../index.php";
      $qName =  pg_escape_string($conn, $_POST['qName']);
      $qTitle =  pg_escape_string($conn, $_POST['qTitle']);
      $question = pg_escape_string($conn, $_POST['question']);

      $sql = 'INSERT INTO "webTables".faqs ("qName", "qTitle", "question") VALUES '."('$qName', '$qTitle', '$question');";
    }

    if (pg_query($conn, $sql)) {
      pg_close($conn);
      header($location);
    } else {
      echo "Error";
      pg_close($conn);
    }
  } else {
    echo '<p>du skal ikke være her</p>';
  }

  if(!$_SESSION["privileges"] == "all" && !$_SESSION["privileges"] == "web" && !$_SESSION["privileges"] == "game"){
    header("Location: ../index.php");
  }