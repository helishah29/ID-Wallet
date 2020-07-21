<?php
include 'db_connect.php';

$enrollment=$_POST["EnrollmentNumber"];
$password=$_POST["password"];
//$enrollment="14041010799";
//$password="helish123";

$result=mysqli_query($link,"SELECT * FROM Student WHERE EnrollmentNumber = '$enrollment' AND Password = '$password'");

//$rows=mysqli_num_rows($result);
//echo $result;
if($result)
{$row=mysqli_fetch_array($result);
if (sizeof($row)>0)
{
    echo "Login";
    exit;
}
else
{
    echo "Error";
    exit;
}
}
mysqli_close($link);
?>