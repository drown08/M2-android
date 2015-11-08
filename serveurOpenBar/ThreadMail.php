<?php

class ThreadMail {

	private $to;
	private $subject;
	private $msg;
	private $headers;


	public function __construct($__pseudo,$__mail,$__mdp) {

		$this->to = $__mail;
		$this->subject = "[OpenBar] Reset password";
		$this->msg = "Bonjour ". $__pseudo .",\n voici votre nouveau mot de passe :\n".
						$__mdp."\n\n\n\nCe mot de passe nous est inconnu et a".
						" été généré automatiquement.";
		$this->headers = 'From: noreply@openbar.com' . "\r\n" .
     						'Reply-To: noreply@openbar.com' . "\r\n" .
     						'X-Mailer: PHP/' . phpversion();
	}

	public function run() {
		$this->sendMail();
	}

	private function sendMail() {
		if(mail($this->to,$this->subject,$this->msg,$this->headers)){
			echo "send";
		} else {
			echo "no send";
		}
	}
}



?>