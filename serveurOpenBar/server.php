<?php
// if text data was posted

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
				
				default:
					# code...
					break;
			}
		} else {
			echo "caca : ".$_GET["ctrl"]." !!";
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

?>