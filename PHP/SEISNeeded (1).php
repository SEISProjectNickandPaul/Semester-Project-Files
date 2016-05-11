<?php
    $con = mysqli_connect("107.180.54.182", "mpan0590", "Murder21", "Kashe");

    $username = $_POST["username"];
    $points = $_POST["points"];
    $phonenumber = $_POST["phonenumber"];

    $stmt1 = mysqli_prepare($con, "UPDATE User SET bal = bal - ? WHERE username=?");
    mysqli_stmt_bind_param($stmt1, "is", $points, $username);
    mysqli_stmt_execute($stmt1);
    mysqli_stmt_close($stmt1);

    $stmt2 = mysqli_prepare($con, "UPDATE User SET bal = bal + ? WHERE phonenumber=?");
    mysqli_stmt_bind_param($stmt2, "is", $points, $phonenumber);
    mysqli_stmt_execute($stmt2);
    mysqli_stmt_close($stmt2);


    $stmt3 = mysqli_prepare($con, "INSERT INTO Trans (username, phonenumber, points) VALUES (?, ?, ?)");
    mysqli_stmt_bind_param($stmt3, "ssi", $username, $phonenumber, $points);
    mysqli_stmt_execute($stmt3);
  


    $statement = mysqli_prepare($con, "SELECT * FROM User WHERE username = ?");
    mysqli_stmt_bind_param($statement, "s", $username);
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
