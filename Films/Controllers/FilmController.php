<?php

    require_once("Models/FilmModel.php");

    class FilmController{

        private $filmModel;
        
        function __construct(){
            $this->filmModel = new FilmModel();
        }

        function displayFilmsTitles(){

            $films = $this->filmModel->getAllFilmsTitles();

            while($donnees = $films->fetch_array()){
                echo"<li>".$donnees['titre']."</li>";      
            }      
        }
    }