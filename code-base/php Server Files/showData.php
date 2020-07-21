<?php
$servername = "localhost";
$username = "id4315936_heli";
$password = "helish123";
$dbname = "id4315936_idwallet";

$link=mysqli_connect($servername,$username,$password,$dbname);
$result=mysqli_query($link,"select * from Student");
$data=array();

$rows=mysqli_num_rows($result);
while($rec=mysqli_fetch_assoc($result))
{
$data[]=$rec;
}

header('Content-Type: application/json');
echo json_encode(array("std"=>$data));
mysqli_close($link);

?>