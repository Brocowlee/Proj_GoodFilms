<?php

    require_once("Models/GenreModel.php");

    class GenreController{

        private $genreModel;
        
        function __construct(){
            $this->genreModel = new GenreModel();
        }

        function displayAllGenres(){
            $genres = $this->genreModel->getAllGenres();
            require("Views/Genre.php");
        }

    }