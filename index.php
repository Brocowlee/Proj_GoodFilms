<?php   
session_start();

include_once "db.php";

include_once "modele.php";

$result=getFilmsTitles();

include_once "page_connexion.php";






?>