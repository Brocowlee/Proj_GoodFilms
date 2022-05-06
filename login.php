<?php 
include_once "modele.php";
$_SESSION['login']=$_POST['login'];
$_SESSION['mdp']=$_POST['mdp'];

if (getUser($_SESSION['login'])!=NULL && getUser($_SESSION['login'])["mdp"]==$_SESSION['mdp']){
    header("Location: accueil.php");
    Exit();
}

else {
    header("Location: index.php");
    Exit();
}