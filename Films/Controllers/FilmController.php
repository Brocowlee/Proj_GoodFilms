<?php

    require_once("Models/FilmModel.php");

    class FilmController{

        private $filmModel;
        
        function __construct(){
            $this->filmModel = new FilmModel();
        }

        function displayOneFilm(){
            $titre = $_GET["film"];
            $login = $_SESSION["login"];
            $donnees = $this->filmModel->getOneFilm($titre, $login);
            $last_comments = $this->filmModel->showLastComments($titre); 
            $id_film = $donnees['id_film'];
            $realisateur = $this->filmModel->getRealisateur($id_film);
            $has_note = $this->filmModel->hasNote($id_film);
            $avg = $this->filmModel->getNoteMean($id_film);
            require("Views/Film.php");
        }

        function displayMyFilms(){
            $mes_films = $this->filmModel->getMyFilms();
            require("Views/Utilisateur.php");
        }

        function displayResearch(){
            $films_recherche = $this->filmModel->getResearchFilm();
            require("Views/Recherche.php");
        }

         function displayResearchFriend(){
            $friends_research = $this->filmModel->getResearchFriend();
            require("Views/Recherche_amis.php");
        }

        function displayOneGenre(){
            $un_genre = $this->filmModel->getFilmsOneGenre();
            $genre = $_GET["genre"];
            require("Views/UnGenre.php");
        }

    }
