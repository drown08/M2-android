<?php
// if text data was posted
$GOOGLE_API_KEY="AIzaSyCceG-_79VEXIiPTUpLx6ssKWsCFbxfZfg";
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
				case 'addKey':
					AddUserToken($db,$_GET['token_user'],$_GET['id_user']);
					break;
				case 'getKey' :
					$token = GetTokenByUserId($db,$_GET['id_user']);
					GCMPushMessage($token,$GOOGLE_API_KEY);
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

function AddUserToken($db,$token,$id_user) {
	$result=array();
	$requete = $db->prepare("INSERT INTO gcm_ids (gcm_token,fk_user_id) VALUES (:token,:id)");
	$requete->bindParam(':token',$token,PDO::PARAM_STR);
	$requete->bindParam(':id',$id_user,PDO::PARAM_INT);
	if($requete->execute() !== TRUE){
		var_dump($requete->errorInfo());
		echo "not";
	} else {
		echo "ok";
	}
}

function GetTokenByUserId($db,$id_user) {
	$result=array();
	$requete = $db->prepare("SELECT t.gcm_token FROM gcm_ids t WHERE t.fk_user_id = :id");
	$requete->bindParam(":id",$id_user);
	if($requete->execute() !== TRUE){
		//var_dump($req->errorInfo());
		echo "not";
	} else {
		$result = $requete->fetchAll(PDO::FETCH_ASSOC);
		if(count($result)>0){
			echo "ok";
			 return $result[0];	
		} else {
			echo "not";
			return array();
		}
		
	}

}

function GCMPushMessage($token,$api_key){
	$url='https://gcm-http.googleapis.com/gcm/send';
	$deviceId = setDevices($token);
	$title = "A Google Push Test";
	$msg = "Oh my gad ! I've done my test that's all folks !";
	send($url,$deviceId,$api_key,$title,$msg);
	echo "fuck";
}

function setDevices($token) {
	if(is_array($token)) {
		return $token;
	} else {
		return array($token);
	}
}

function send($url,$deviceId,$api_key,$title,$msg, $collapse_key=null,$time_to_live=null){
	if(!is_array($deviceId) || count($deviceId) == 0) {
		echo "NO device set";
		return;
	}
	if(strlen($api_key) < 8) {
		echo "Server API Key not set";
		return;
	}
	$fields = array(
		'registrations_id' => $deviceId,
		'data' => array("message" => $msg,
						"title" => $title)
	);
	if($time_to_live!=null){
		$fields['time_to_live'] = $time_to_live;
	}
	if($collapse_key!=null){
		$fields['collapse_key'] = $collapse_key;
	}
	$headers = array(
		'Authorization: key='.$api_key,
		'Content-Type: application/json'
	);
	//On ouvre connection
	$connex = curl_init();
	//On set l'url
	curl_setopt($connex, CURLOPT_URL, $url);
	//On set les options et confi d'envoi
	curl_setopt($connex, CURLOPT_POST, true);
	curl_setopt($connex, CURLOPT_HTTPHEADER, $headers);
	curl_setopt($connex, CURLOPT_RETURNTRANSFER, true);
	curl_setopt($connex, CURLOPT_POSTFIELDS, json_encode($fields));
	//Suppression pb avec certification HTTP
	curl_setopt($connex, CURLOPT_SSL_VERIFYHOST, false);
	curl_setopt($connex, CURLOPT_SSL_VERIFYPEER, false);
	//Envoi de la rq post
	echo "coucou";
	$result = curl_exec($connex);
	echo "lol";
	//Close connection
	curl_close($connex);
	echo "result";
	echo "result :".$result;
	return;
}

?>