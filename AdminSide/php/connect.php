<?php
$IP = "host=localhost";
$Port = "port=5432";
$database = "dbname=game4";
$username = "user=Webuser";
$password = "password=123";

$conn_string = "$IP $Port $database $username $password";
$conn = pg_connect("$conn_string") or die ('Could not connect: ' . pg_last_error());
?>
<!-- host=10.0.0.70 port=5432 dbname=game4 user=postgres passwodownload Downloadrd=123 -->