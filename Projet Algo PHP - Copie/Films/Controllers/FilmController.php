<?php

    require_once("Models/FilmModel.php");

    class FilmController{

        private $filmModel;
        
        function __construct(){
            $this->filmModel = new FilmModel();
        }

        function displayFilmsTitles(){
            $films = $this->filmModel->getAllFilmsTitles();
            return $films;
        }

        function displayOneFilm(){
            $donnees = $this->filmModel->getOneFilm();
            $titre = $_POST["film"];
            require("Views/Film.php");
        }

        function displayMyFilms(){
            $mes_films = $this->filmModel->getMyFilms();
            require("Views/MesFilms.php");
        }

        function displayResearch(){
            $films_recherche = $this->filmModel->getResearchFilm();
            require("Views/Recherche.php");
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

        function changeNote($id_u, $id_f){
            $this -> filmModel -> changeMark($id_u, $id_f, $_POST["new_note"]);
        }

        function suppNote($id_u, $id_f){
            $this -> filmModel -> deleteMark($id_u, $id_f);
        }
    }