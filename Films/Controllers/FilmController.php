<?php

    require_once("Models/FilmModel.php");

    class FilmController{

        private $filmModel;
        
        function __construct(){
            $this->filmModel = new FilmModel();
        }

        function displayOneFilm(){
            $titre = $_POST["film"];
            $login = $_SESSION["login"];
            $donnees = $this->filmModel->getOneFilm($titre, $login);
            $last_comments = $this->filmModel->showLastComments($titre); 
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
            $amis_recherche = $this->filmModel->getResearchFriend();
            return $amis_recherche;
        }

        function displayOneGenre(){
            $un_genre = $this->filmModel->getFilmsOneGenre();
            $genre = $_POST["genre"];
            require("Views/UnGenre.php");
        }

        function displayOneFilmID(){
            $id = $this->filmModel->getFilmID();
            return $id;
        }

        function displayNoteMean($id){
            $avg = $this->filmModel->getNoteMean($id);
            return $avg;
        }

        function hasNote($id){
            $bool = $this->filmModel->hasNote($id);
            return $bool;
        }



        function changeNote($id_u, $id_f){
            $this -> filmModel -> changeMark($id_u, $id_f, $_POST["new_note"]);
        }

        function suppNote($id_u, $id_f){
            $this -> filmModel -> deleteMark($id_u, $id_f);
        }


    }