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
            $film = $this->filmModel->getOneFilm();
            return $film;
        }

        function displayMyFilms(){
            $mes_films = $this->filmModel->getMyFilms();
            return $mes_films;
        }

        function displayResearch(){
            $recherche = $this->filmModel->getResearchFilm();
            return $recherche;
        }

        function displayOneGenre(){
            $films_du_genre = $this->filmModel->getFilmsOneGenre();
            return $films_du_genre;
        }

        function displayOneFilmID(){
            $id = $this->filmModel->getFilmID();
            return $id;
        }

        function changeNote($id_u, $id_f){
            $this -> filmModel -> changeMark($id_u, $id_f, $_POST["new_note"]);
        }
    }