<?php
$IP = "localhost";
$username = "Admin";
$password = "HmBw1yvcYpkawfFu";
$database = "game4";

$conn = mysqli_connect($IP, $username, $password, $database);

if (!$conn) {
  die("Connection failed: " . mysqli_connect_error());
}
?>

