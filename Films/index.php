<?php
   
    if (!isset($_SESSION)){
        session_start();
    }
    $_SESSION['connexion']="deconnecte";

<<<<<<< HEAD
    //import tous les controlleurs 
=======
    //import tout les controlleurs 
>>>>>>> origin/Thomas
    require_once("Controllers/FilmController.php");
    require_once("Controllers/UserController.php");
    require_once("Controllers/GenreController.php");

<<<<<<< HEAD
    //Initialiser tous les controlleurs
=======
    //Declarer les controlleurs
>>>>>>> origin/Thomas
    $filmController = new FilmController();
    $userController = new UserController();
    $genreController = new GenreController();


    if(isset($_POST["action"])){

        //différentes routes

        switch($_POST["action"]){

            case "connexion" :
                //Affichage de la Connexion
<<<<<<< HEAD
                $userController->displayConnexion();
=======
                $userName = $userController->displayConnexion();

                if($userName == FALSE){

                    require("Views/Connexion.php");
                }
                else{

                    $liste_films = $filmController->displayFilmsTitles();
                    require("Views/Accueil.php");
                }
>>>>>>> origin/Thomas
                break;
            
            case "deconnexion" :
                //Affichage de la déconnexion
                $userController->displayDeconnexion();
<<<<<<< HEAD
=======
                require("Views/Connexion.php");
>>>>>>> origin/Thomas
                break;

            case "accueil" :
                //si on clique sur le bouton d'accueil
<<<<<<< HEAD
                $userController->displayAccueil();
=======
                $userName = $userController->displayAccueil();
                $liste_films = $filmController->displayFilmsTitles();
                require("Views/Accueil.php");
>>>>>>> origin/Thomas
                break;
            
            case "mes_films" :
                //Si on clique sur le bouton mes films
<<<<<<< HEAD
                $filmController->displayMyFilms();
=======
                $mes_films = $filmController->displayMyFilms();

                require("Views/MesFilms.php");

>>>>>>> origin/Thomas
                break;

            case "genre" :
                //Si on clique sur le bouton de recherche par genre
<<<<<<< HEAD
                $genreController->displayAllGenres();
                break;

            case "un_genre" : 
                //Si on choisit un genre pour en voir les films
                $filmController->displayOneGenre();
=======
                $genres = $genreController->displayAllGenres();

                require("Views/Genre.php");

>>>>>>> origin/Thomas
                break;
            
            case "recherche" :
                //Si l'utilisateur fait une recherche
<<<<<<< HEAD
                $filmController -> displayResearch();
=======
                $films_recherche = $filmController -> displayResearch();

                require("Views/Recherche.php");

>>>>>>> origin/Thomas
                break;
            
            case "un_film" : 
                //Si l'utilisateur veut voir les détails d'un film
<<<<<<< HEAD
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
=======
                $titre = $_POST["film"];
                echo "Tout va bien";
                echo $titre;
                $film = $filmController->displayOneFilm();

                require("Views/Film.php");

                break;

>>>>>>> origin/Thomas
        }
    }
    else {
        //Charger l'accueil par défaut
        require("Views/Connexion.php");

        
    }