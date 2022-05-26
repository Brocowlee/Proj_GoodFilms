<?php

    require_once("Models/GenreModel.php");

    class GenreController{

        private $genreModel;
        
        function __construct(){
            $this->genreModel = new GenreModel();
        }

        function displayAllGenres(){
            $genres = $this->genreModel->getAllGenres();
<<<<<<< HEAD
            require("Views/Genre.php");
=======
            return $genres;
>>>>>>> origin/Thomas
        }

    }