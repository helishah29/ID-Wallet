<?php

include 'db_connect.php';

$idno=$_POST["idno"];
$name=$_POST["name"];
$department=$_POST["department"];
$password=$_POST["password"];
$pin=$_POST["pin"];

$result=mysqli_query($link,"insert into Faculty values('$idno','$name','$password','$pin','$department')");

if($result)
{
    echo "Successful";
}

mysqli_close($link);

?>