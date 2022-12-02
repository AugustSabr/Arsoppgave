<?php
  include 'connect.php';
  if (isset($_POST["action"])){
    $dbtable = mysqli_real_escape_string($conn, $_POST['dbtable']);
    $id = $_POST['id'];

    if ($_POST["action"] == 'update'){
      $tierExists = mysqli_num_rows(mysqli_query($conn, "SHOW COLUMNS FROM ".$dbtable."s LIKE '".$dbtable."Tier'"));
      $effectExists = mysqli_num_rows(mysqli_query($conn, "SHOW COLUMNS FROM ".$dbtable."s LIKE '".$dbtable."Effect'"));
      $damageExists = mysqli_num_rows(mysqli_query($conn, "SHOW COLUMNS FROM ".$dbtable."s LIKE '".$dbtable."Damage'"));
      $healthExists = mysqli_num_rows(mysqli_query($conn, "SHOW COLUMNS FROM ".$dbtable."s LIKE '".$dbtable."Health'"));
        
      $type = mysqli_real_escape_string($conn, $_POST[$dbtable.'Type']);
      $sql = "UPDATE ".$dbtable."s SET ".$dbtable."Type = '$type' WHERE id=$id;";
  
      if($tierExists != 0){
        $tier =  mysqli_real_escape_string($conn, $_POST[$dbtable.'Tier']);
        $sql .= "UPDATE ".$dbtable."s SET ".$dbtable."Tier = $tier WHERE id=$id;";
      }
      if($effectExists != 0){
        $effect =  mysqli_real_escape_string($conn, $_POST[$dbtable.'Effect']);
        $sql .= "UPDATE ".$dbtable."s SET ".$dbtable."Effect = $effect WHERE id=$id;";
      }
      if($damageExists != 0){
        $damage =  mysqli_real_escape_string($conn, $_POST[$dbtable.'Damage']);
        $sql .= "UPDATE ".$dbtable."s SET ".$dbtable."Damage = $damage WHERE id=$id;";
      }
      if($healthExists != 0){
        $health =  mysqli_real_escape_string($conn, $_POST[$dbtable.'Health']);
        $sql .= "UPDATE ".$dbtable."s SET ".$dbtable."Health = $health WHERE id=$id;";
      }
    } else if ($_POST["action"] == 'remove'){
      $sql = "DELETE FROM ".$dbtable."s WHERE id=$id;";
    }

    if (mysqli_multi_query($conn, $sql)) {
      header("Location: ../");
    } else {
      echo "Error updating record: " . mysqli_error($conn);
    }
  } else {
    echo '<p>du skal ikke være her</p>';
  }

