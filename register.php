<?php

    $bdd = new PDO('mysql:host=localhost;dbname=amphinote', 'root', '');
    $username = $_POST["username"];
	$lastname = $_POST["lastname"];
    $password = $_POST["password"];
	$age = $_POST["age"];
	$email = $_POST["email"];
	
	$statement= $bdd->prepare('INSERT INTO user VALUES ( :username, :lastname, :email, :age, :password)');

	$statement->bindValue(':username', $username, PDO::PARAM_STR);
	$statement->bindValue(':lastname', $lastname, PDO::PARAM_STR);
    $statement->bindValue(':email', $email, PDO::PARAM_STR);
    $statement->bindValue(':age', $age, PDO::PARAM_STR);
    $statement->bindValue(':password', $password, PDO::PARAM_STR);
	
	$statement->execute();
	
	$response = array();
	$response["succes"] = true;
	
	echo json_encode($response);
	
	?>