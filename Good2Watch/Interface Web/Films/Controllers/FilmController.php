<?php

    require_once("Models/FilmModel.php");

    class FilmController{

        private $filmModel;
        
        function __construct(){
            //On crée un nouvel objet de type FilmModel afin d'avoir accès aux fonctions qui y sont déclarées
            $this->filmModel = new FilmModel();
        }

        function displayOneFilm(){
            //Cette fonction est appelée lorsque l'utilisateur clique sur un film en particulier, on récupère les infos et on affiche la vue

            //infos nécessaires pour afficher la vue
            $titre = $_GET["film"];
            $login = $_SESSION["login"];
            $donnees = $this->filmModel->getOneFilm($titre, $login);
            $last_comments = $this->filmModel->showLastComments($titre); 
            $id_film = $donnees['id_film'];
            $realisateur = $this->filmModel->getRealisateur($id_film);
            $has_note = $this->filmModel->hasNote($id_film);
            $avg = $this->filmModel->getNoteMean($id_film);

            //affichage de la vue
            require("Views/Film.php");
        }

        function displayResearch(){
            // est appelée quand on souhaite afficher les résultats d'une recherche de films
            $films_recherche = $this->filmModel->getResearchFilm();
            require("Views/Recherche.php");
        }

         function displayResearchFriend(){
            //est appelée quand on souhaite afficher les résultats de la recherche d'amis
            $friends_research = $this->filmModel->getResearchFriend();
            require("Views/Recherche_amis.php");
        }

        function displayOneGenre(){
            //est appelée lorsque l'utilisateur choisit un genre dans la liste des genres proposés, on affiche la liste des films qui correspondent à ce genre
            $un_genre = $this->filmModel->getFilmsOneGenre();
            $genre = $_GET["genre"];
            require("Views/UnGenre.php");
        }

    }
