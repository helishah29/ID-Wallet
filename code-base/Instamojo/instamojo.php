<?php
ob_start();
session_start();

$ch = curl_init();

curl_setopt($ch, CURLOPT_URL, 'https://test.instamojo.com/api/1.1/payment-requests/');
curl_setopt($ch, CURLOPT_HEADER, FALSE);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, TRUE);
curl_setopt($ch, CURLOPT_FOLLOWLOCATION, TRUE);
curl_setopt($ch, CURLOPT_HTTPHEADER,
            array("X-Api-Key:c965a2a55d9598fda8dde9e6d6fb91d8",
					"X-Auth-Token:df1b389883c04e8c597364973f4fe5fa"));
$payload = Array(
    'purpose' => $_SESSION['selectedToll'],
    'amount' => $_SESSION['Amount'],
    'phone' => $_SESSION['userContact'],
    'buyer_name' => $_SESSION['userName'],
    'redirect_url' => 'https://bharavijoshi01.000webhostapp.com/HL_App/success.php',
    'send_email' => true,
    'webhook' => '',
    'send_sms' => true,
    'email' => $_SESSION['userEmail'],
    'allow_repeated_payments' => false
);
curl_setopt($ch, CURLOPT_POST, true);
curl_setopt($ch, CURLOPT_POSTFIELDS, http_build_query($payload));
$response = curl_exec($ch);
curl_close($ch); 

$json_decode=json_decode($response,true);
$long_url=$json_decode['payment_request']['longurl'];
print_r($json_decode);
echo "<br><br><br>".$long_url;
header("Location: $long_url");
?>