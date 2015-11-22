<?php
// if text data was posted
try{
$db = new PDO('mysql:host=localhost;dbname=bd_open_bar','root','');
}
catch(Exception $e)
{
	die('Erreur : '.$e->getMessage());
}
require_once('ThreadMail.php');
//$Get_url = "http://" . $_SERVER['HTTP_HOST'] . $_SERVER['REQUEST_URl'];
//echo $Get_url;
if(isset($_GET)){
	if(isset($_GET["id"]))
	{
		
		//echo $_GET["id"]."<br>"; 
		//echo "<html> id = ".$_GET["id"]." </html>";
		if(isset($_GET["ctrl"])) {
			//echo $_GET["ctrl"]."<br>";
			//echo $_GET["pseudo"]."<br>";
			//echo $_GET["mail"]."<br>";
			switch ($_GET["ctrl"]) {
				case 'mail':
					$mdp = getRandomPassword();
					//echo $mdp."<br>";
					$sendMail = new ThreadMail($_GET["pseudo"],$_GET["mail"],$mdp);
					$sendMail->run(); 
					/*if($sendMail->start())//Pas inclus l'api pthread encore
					{
						echo "Thread of client ".$_GET["pseudo"]."executed<br>";
					}*/
					break;

				case 'users':
					$requete= 'select * from user';
					ReturnResultFromDB($db,$requete); 
					break;
				case 'bars':
					$requete = 'select * from bar';
					ReturnResultFromDB($db,$requete);
					break;
				case'vPseudo':
					$requete = "select id_user from user where pseudo_user ='".$_GET['pseudo']."'";
					ReturnBooleanFromDB($db,$requete);
					break;
				case 'sign' :
					ReturnInsertUserFromDB($db,$_GET['pseudo'],$_GET['mdp'],$_GET['mail']);
					break;
				case 'verifySign' :
					ReturnVerifyLogin($db,$_GET['pseudo'],$_GET['mdp']);
					break;
				default:
					# code...
					break;
			}
		} else {
			echo "bad controlleur : ".$_GET["ctrl"]." !!";
		}
	} else {
		print_r($_GET);
	}
}else{
	echo "<html>Enfin la communication est instaurée ! Je peux aller dodo//</html>";
}

 function getRandomPassword() {
    $alphabet = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890';
    $pass = array();
    $alphaLength = strlen($alphabet) - 1; 
        for ($i = 0; $i < 9; $i++) {
        $n = rand(0, $alphaLength);
        $pass[] = $alphabet[$n];
    }
    return implode($pass); 
}

function ReturnResultFromDB($db,$req) {
	$result = array();
	$rq = $db->prepare($req);
	$rq->execute();
	$result = $rq->fetchAll(PDO::FETCH_ASSOC);
	echo (json_encode($result));
}

function ReturnBooleanFromDB($db,$req) {
	$result = array();
	$rq = $db->prepare($req);
	$rq->execute();
	$result = $rq->fetchAll(PDO::FETCH_ASSOC);
	if(count($result) == 0){
		echo "ok";
	} else {
		echo "not";
	}
}

function ReturnInsertUserFromDB($db,$pseudo,$password,$mail) {
	$req = $db->prepare('INSERT INTO user (pseudo_user, password_user, mail_user) VALUES (:pseudo, :password, :mail)');
	$req->bindParam(':pseudo',$pseudo, PDO::PARAM_STR);
	$req->bindParam(':password',$password, PDO::PARAM_STR);
	$req->bindParam(':mail',$mail, PDO::PARAM_STR);
	if($req->execute() !== TRUE){
		var_dump($req->errorInfo());
		echo "not";
	} else {
		echo "ok";
	}
}

function ReturnVerifyLogin($db,$pseudo,$password) {
	//Faille pseudo = pseudo -- ????
	$result=array();
	$req = $db->prepare('SELECT id_user FROM user  WHERE pseudo_user = :pseudo AND password_user = :password ');
	$req->bindParam(':pseudo',$pseudo, PDO::PARAM_STR);
	$req->bindParam(':password',$password, PDO::PARAM_STR);
	if($req->execute() !== TRUE){
		//var_dump($req->errorInfo());
		echo "not";
	} else {
		$result = $req->fetchAll(PDO::FETCH_ASSOC);
		if(count($result)>0){
			echo (json_encode($result));	
		} else {
			echo "not";
		}
		
	}
}

?>