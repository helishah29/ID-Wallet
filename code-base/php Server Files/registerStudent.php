<?php

include 'db_connect.php';

$enrollment=$_POST["enrollment"];
$id=$_POST["idno"];
$name=$_POST["name"];
$balance=$_POST["balance"];
$password=$_POST["password"];
$pin=$_POST["pin"];

$result=mysqli_query($link,"insert into Student values('$id','$enrollment','$name','$balance','$password','$pin')");

if($result)
{
    echo "Successful";
}

mysqli_close($link);

?>