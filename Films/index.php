<?php
   
    if (!isset($_SESSION)){
        session_start();
    }
    $_SESSION['connexion']="deconnecte";

    //import tous les controlleurs 
    require_once("Controllers/FilmController.php");
    require_once("Controllers/UserController.php");
    require_once("Controllers/GenreController.php");

    //Initialiser tous les controlleurs
    $filmController = new FilmController();
    $userController = new UserController();
    $genreController = new GenreController();


    if(isset($_POST["action"])){

        //différentes routes

        switch($_POST["action"]){

            case "connexion" :
                //Affichage de la Connexion
                $userController->displayConnexion();
                break;
            
            case "deconnexion" :
                //Affichage de la déconnexion
                $userController->displayDeconnexion();
                break;

            case "accueil" :
                //si on clique sur le bouton d'accueil
                $userController->displayAccueil();
                break;
            
            case "mes_films" :
                //Si on clique sur le bouton mes films
                $filmController->displayMyFilms();
                break;

            case "genre" :
                //Si on clique sur le bouton de recherche par genre
                $genreController->displayAllGenres();
                break;

            case "un_genre" : 
                //Si on choisit un genre pour en voir les films
                $filmController->displayOneGenre();
                break;
            
            case "recherche" :
                //Si l'utilisateur fait une recherche
                $filmController -> displayResearch();
                break;
            
            case "un_film" : 
                //Si l'utilisateur veut voir les détails d'un film
                $filmController->displayOneFilm();
                break;

            case "notation" :
                //Si l'utilisateur change la note d'un film
                $userController->displayModifyMark();
                break;

            case "sup_note" :
                //Si l'utilisateur appuie sur le bouton pour supprimer la note d'un film
                $userController->displayDeleteMark();
                break;
        }
    }
    else {
        //Charger l'accueil par défaut
        require("Views/Connexion.php");

        
    }