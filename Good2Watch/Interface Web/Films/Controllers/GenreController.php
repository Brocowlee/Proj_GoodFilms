<?php

    require_once("Models/GenreModel.php");

    class GenreController{

        private $genreModel;
        
        function __construct(){
            //On crée l'objet de type GenreModel pour pouvoir avoir accès aux fonctions qu'il contient
            $this->genreModel = new GenreModel();
        }

        function displayAllGenres(){
            //page de recherche par genre, on récupère la liste de tous les genres
            $genres = $this->genreModel->getAllGenres();
            require("Views/Genre.php");
        }

    }