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
    }