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
                $userName = $userController->displayConnexion();

                if($userName == FALSE){

                    require("Views/Connexion.php");
                }
                else{

                    $liste_films = $filmController->displayFilmsTitles();
                    require("Views/Accueil.php");
                }
                break;
            
            case "deconnexion" :
                //Affichage de la déconnexion
                $userController->displayDeconnexion();
                require("Views/Connexion.php");
                break;

            case "accueil" :
                //si on clique sur le bouton d'accueil
                $userName = $userController->displayAccueil();
                $liste_films = $filmController->displayFilmsTitles();
                require("Views/Accueil.php");
                break;
            
            case "mes_films" :
                //Si on clique sur le bouton mes films
                $mes_films = $filmController->displayMyFilms();

                require("Views/MesFilms.php");

                break;

            case "genre" :
                //Si on clique sur le bouton de recherche par genre
                $genres = $genreController->displayAllGenres();

                require("Views/Genre.php");

                break;
            
            case "recherche" :
                //Si l'utilisateur fait une recherche
                $films_recherche = $filmController -> displayResearch();

                require("Views/Recherche.php");

                break;
            
            case "un_film" : 
                //Si l'utilisateur veut voir les détails d'un film
                $titre = $_POST["film"];
                $donnees = $filmController->displayOneFilm();

                require("Views/Film.php");

                break;

        }
    }
    else {
        //Charger l'accueil par défaut
        require("Views/Connexion.php");

        
    }