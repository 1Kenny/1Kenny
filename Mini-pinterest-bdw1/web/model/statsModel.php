

<?php

function getKnockers($database) {
	$query = "SELECT username, count(IDSleeve) AS num FROM Game NATURAL JOIN  Users NATURAL JOIN Sleeves WHERE (knocked % 2) = 1 GROUP BY userID ORDER BY num DESC LIMIT 3";
	$req = $database->prepare($query);
	$req->execute(array());
	return $req->fetchAll();
}

function getCardScore($carte) {
	$num = substr($carte[0], 0, -1);
	if($num == 1) {
		return 11;
	}
	else if($num > 9) {
		return 10;
	}
	else {
		return $num;
	}
}

function getScore($database, $hand) {
	$hand = explode("|", $hand);
	$scores = array(0, 0, 0, 0);
	for($i = 0; $i < 3; $i++) {
		$query = "SELECT codeC FROM Cartes WHERE idC = ?";
		$req = $database->prepare($query);
		$req->execute(array($hand[$i]));
		$carte = $req->fetch();
		$type = substr($carte[0], -1);
		if($type == 'C') {
			$scores[0] += getCardScore($carte);
		}
		else if($type = 'K') {
			$scores[1] += getCardScore($carte);
		}
		else if($type = 'T') {
			$scores[2] += getCardScore($carte);
		}
		else {
			$scores[3] += getCardScore($carte);
		}
	}
	return max($scores);
}

function getCheaters($database) {
	$query = "SELECT username, playerHand, userID FROM Game NATURAL JOIN  Users NATURAL JOIN Sleeves WHERE won = 1 ORDER BY userID";
	$req = $database->prepare($query);
	$req->execute(array());
	$answ = $req->fetchAll();
	$bestCheater = array(array(0, 0), array(0, 0), array(0, 0));
	$oldPlayerName = 0;
	$curPlayerID = 0;
	$curPlayerScore = 0;
	foreach($answ as $sleeve) {
		$curScore = getScore($database, $sleeve['playerHand']);
		if($curScore == 31) {
			if($curPlayerID == $sleeve['userID']) {
				$curPlayerScore++;
				$oldPlayerName = $sleeve['username'];
			}
			else {
				if($curPlayerScore > $bestCheater[0][1]) {
					$tmpName = $bestCheater[0][0];
					$tmpScore = $bestCheater[0][1];
					$bestCheater[0] = array($oldPlayerName, $curPlayerScore);
					$curPlayerScore = $tmpScore;
					$oldPlayerName = $tmpName;
				}
				if($curPlayerScore > $bestCheater[1][1]) {
					$tmpName = $bestCheater[1][0];
					$tmpScore = $bestCheater[1][1];
					$bestCheater[1] = array($oldPlayerName, $curPlayerScore);
					$curPlayerScore = $tmpScore;
					$oldPlayerName = $tmpName;
				}
				if($curPlayerScore > $bestCheater[2][1]) {
					$bestCheater[2] = array($oldPlayerName, $curPlayerScore);
				}
				
				$curPlayerID = $sleeve['userID'];
				$curPlayerScore = 1;	
			}
		}
	}
	if($curPlayerScore != 0) {
		if($curPlayerScore > $bestCheater[0][1]) {
			$tmpName = $bestCheater[0][0];
			$tmpScore = $bestCheater[0][1];
			$bestCheater[0] = array($oldPlayerName, $curPlayerScore);
			$curPlayerScore = $tmpScore;
			$oldPlayerName = $tmpName;
		}
		if($curPlayerScore > $bestCheater[1][1]) {
			$tmpName = $bestCheater[1][0];
			$tmpScore = $bestCheater[1][1];
			$bestCheater[1] = array($oldPlayerName, $curPlayerScore);
			$curPlayerScore = $tmpScore;
			$oldPlayerName = $tmpName;
		}
		if($curPlayerScore > $bestCheater[2][1]) {
			$tmpName = $bestCheater[2][0];
			$tmpScore = $bestCheater[2][1];
			$bestCheater[2] = array($oldPlayerName, $curPlayerScore);
			$curPlayerScore = $tmpScore;
			$oldPlayerName = $tmpName;
		}
	}
	return $bestCheater;
}


