<?php
ob_start();
//session_start();

$ch = curl_init();

curl_setopt($ch, CURLOPT_URL, 'https://test.instamojo.com/api/1.1/payment-requests/');
curl_setopt($ch, CURLOPT_HEADER, FALSE);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, TRUE);
curl_setopt($ch, CURLOPT_FOLLOWLOCATION, TRUE);
curl_setopt($ch, CURLOPT_HTTPHEADER,
            array("X-Api-Key:test_5ef4ba7b8f1c19bb85f7780a0f7",
					"X-Auth-Token:test_797477a5e85ac0a6354e9a8229a"));
$payload = Array(
    'purpose' => $_POST['userId'],
    'amount' => $_POST['recharge'],
    'phone' => '9998270585',
    'buyer_name' => $_POST['userName'],
    'redirect_url' => 'https://tonetic-lights.000webhostapp.com/IDWallet/success.php',
    'send_email' => true,
    'webhook' => '',
    'send_sms' => true,
    'email' => $_POST['userEmail'],
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