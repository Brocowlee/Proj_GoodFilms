<?php
   
    if (!isset($_SESSION)){
        session_start();
    }
    $_SESSION['connexion']="error";

    //import tous les controlleurs 
    require_once("Controllers/FilmController.php");
    require_once("Controllers/UserController.php");
    require_once("Controllers/GenreController.php");

    //Initialiser tous les controlleurs
    $filmController = new FilmController();
    $userController = new UserController();
    $genreController = new GenreController();

    if(isset($_GET["action"])){

        //différentes routes

        switch($_GET["action"]){



            
            case "deconnexion" :
                //Affichage de la déconnexion
                $userController->displayDeconnexion();
                break;

            case "send_to_inscription" :
                //Affichage de l'inscription
                $userController->displayInscription();
                break;

            case "inscription" :
                // Ajoute un utilisateur
                $userController->createUtilisateur();
                break;

            case "accueil" :
                //si on clique sur le bouton d'accueil
                $userController->displayAccueil();
                break;
            
            case "mes_films" :
                //Si on clique sur le bouton mes films
                $userController->displayOneUtilisateur();
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
                //Si l'utilisateur fait une recherche de genre
                $filmController -> displayResearch();
                break;
            
            case "recherche_amis" :
                //Si l'utilisateur fait une recherche d'amis
                 $filmController -> displayResearchFriend();
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

            case "commentaire" :
                //Si l'utilisateur appuie sur le bouton pour ajouter un commentaire
                $userController->addComment();
                break;
            
            case "target_utilisateur" :
                // Si l'utilisateur appuie sur le login d'un autre utilisateur
                $userController->displayOneUtilisateur();
                break;
            
            case "suivre" :
                // Si l'utilisateur suit un autre utilisateur
                $userController->addToFriend();
                break;

            case "fuir" :
                // Si l'utilisateur arrête de suivre un autre utilisateur
                $userController->removeFromFriend();
                break;
        }
    }
    else if (isset($_POST["action"])) {
        if ($_POST["action"] == "connexion"){
                $userController->displayConnexion();
        }
    }
    else {
        //Charger l'accueil par défaut
        $userController->displayConnexion();


        
    }