<?php

    session_start();

    //import tout les controlleurs 
    require_once("Controllers/FilmController.php");
    require_once("Controllers/UserController.php");

    //Declarer les controlleurs
    $filmController = new FilmController();
    $userController = new UserController();


    if(isset($_POST["action"])){

        //différentes routes

        switch($_POST["action"]){

            case "connexion" :
                //Affichage de la Connexion
                $userController->displayConnexion();
                $filmController->displayFilmsTitles();

                break;
        }
    }
    else {
        //Charger l'accueil par défaut
        require("Views/Connexion.php");

        
    }