<?php
session_start();

include 'db_connect.php';

$userId=$_POST["userId"];
$tollAmount=$_POST["tollAmount"];
$tollName=$_POST["selectedToll"];
$carNo=$_POST["selectedCar"];
$tripType=$_POST["tripType"];
$checkStatus=$_POST["checkStatus"];


$sql = "SELECT * FROM App_User WHERE userId = '$userId'";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        $username = $row['userName'];
		$usercontact = $row['userContact'];
		$useremail = $row['userEmail'];
		$BA = $row['userBalanceAmount'];
		$UA = $row['userUsedAmount'];
    }
}

$_SESSION['selectedToll'] = $tollName;
$_SESSION['userContact'] = $usercontact;
$_SESSION['userId'] = $userId;
$_SESSION['userEmail'] = $useremail;
$_SESSION['tripType'] = $tripType;
$_SESSION['selectedCar']=$carNo;
$_SESSION['tollAmount']=$tollAmount;
$_SESSION['BA']=$BA;
$_SESSION['UA']=$UA;


$sql = "Select * from Admin_Toll where tollName='$tollName'";
	$result = $conn->query($sql);
	if ($result->num_rows > 0) {
		// output data of each row
		while($row = $result->fetch_assoc()) {
			$tollId = $row['tollId'];
		}
	}
	else{
			echo "Fail";
	}
	
	$sql = "Select * from App_Car where userId='$userId' and carNo='$carNo'";
	$result = $conn->query($sql);
	if ($result->num_rows > 0) {
		// output data of each row
		while($row = $result->fetch_assoc()) {
			$vehicleType = $row['vehicleType'];
		}
	}
	else{
			echo "Fail";
	}
	
	$_SESSION['tollId'] = $tollId;
	$_SESSION['vehicleType']=$vehicleType;
	
if($checkStatus=="checked")
{
	if($tollAmount<=($BA-$UA))
	{
		$totalAmount=$UA+$tollAmount;


		$sql = "INSERT INTO App_Ticket(userId,tollId,carNo,vehicleType,tripType) VALUES('$userId','$tollId','$carNo','$vehicleType','$tripType')";
		$result = $conn->query($sql);
		if($result>0)
		{
			
			$sql = "UPDATE App_User SET userUsedAmount='$totalAmount' WHERE userId = '$userId'";
			$result = $conn->query($sql);
			
			if ($result === TRUE) 
			{
				header('Location: https://bharavijoshi01.000webhostapp.com/HL_App/finalSuccess.php'); 
			} else {
				echo "Fail";
			}
		}
		else {
				echo "Fail";
		}
	}
	else
	{
		$instaAmount=$tollAmount-($BA-$UA);
		
		$totalAmount=$UA+($BA-$UA);

		
		if($instaAmount>9){
			$_SESSION['Amount'] = $instaAmount;
			include 'instamojo.php';
		}
		else
		{
			$_SESSION['Amount'] = $tollAmount;
			include 'instamojo.php';
		}
	}
}
else
{
		$_SESSION['Amount'] = $tollAmount;
		include 'instamojo.php';
}



?>