<?php
    $con = mysqli_connect("107.180.54.182", "mpan0590", "Murder21", "Kashe");
    
    $name = $_POST["name"];
    $email = $_POST["email"];
    $username = $_POST["username"];
    $password = $_POST["password"];
    $phonenumber = $_POST["phonenumber"];
    $bal = $_POST["bal"];

    $statement = mysqli_prepare($con, "INSERT INTO User (name, email, username, password, phonenumber, bal) VALUES (?, ?, ?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "sssssi", $name, $email, $username, $password, $phonenumber, $bal);
    mysqli_stmt_execute($statement);
 
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>
