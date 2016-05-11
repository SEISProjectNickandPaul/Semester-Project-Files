<?php
    $con = mysqli_connect("107.180.54.182", "mpan0590", "Murder21", "Kashe");
    
    $username = $_POST["username"];
    $password = $_POST["password"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM User WHERE username = ? AND password = ?");
    mysqli_stmt_bind_param($statement, "ss", $username, $password);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $userID, $name, $email, $username, $password, $phonenumber, $bal);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;  
        $response["name"] = $name;
        $response["email"] = $email;
        $response["username"] = $username;
        $response["password"] = $password;
        $response["phonenumber"] = $phonenumber;
        $response["bal"] = $bal;
    }
    
    echo json_encode($response);
?>
