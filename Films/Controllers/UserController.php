<?php 
    require_once("Models/UserModel.php");
    require_once("Models/FilmModel.php");

    class UserController{

        private $userModel;
        private $filmModel;

        function __construct(){
            $this->userModel = new UserModel();
            $this->filmModel = new FilmModel();
        }

        function displayConnexion(){
            $user = $this->userModel->verifyUser();
            
            if ($user == TRUE){

                //Liste des variables nécessaires pour l'affichage
                
                $_SESSION["connexion"]="ok";
                $_SESSION["login"]=$_POST["login"];
                $_SESSION["mdp"]=$_POST["mdp"];
                $val=$this->userModel->getUserId();
                $_SESSION["user_id"] = $val->fetch_assoc();
                $firstName = $this->userModel->getUserFirstName();
                
                $liste_films = $this->filmModel->getAllFilmsTitles();
                require("Views/Accueil.php");
            }
            else {
                $_SESSION["connexion"]="error";
                require("Views/Connexion.php");
            }  
        }

        function displayDeconnexion(){
            unset($_SESSION['login']);
            unset($_SESSION['mdp']);
            $_SESSION['connexion']="deconnecte";
            require("Views/Connexion.php");
        }

        function displayAccueil(){
            $userName = $this->userModel->getUserFirstName();
            $liste_films = $this->filmModel->getAllFilmsTitles();
            require("Views/Accueil.php");
        }

        function displayModifyMark(){
            $id_u = $this->userModel->getUserID();
            $val = $id_u->fetch_assoc();
            $id_utilisateur = $val["id_u"];
            $id_f = $this->filmModel->getOneFilm();
            $id_film = $id_f["id_f"];
            $titre = $_POST["film"];
            $this->filmModel-> changeMark($id_utilisateur, $id_film, $_POST["new_note"]);
            $donnees = $this->filmModel->getOneFilm();
            require("Views/Film.php");
        }

        function displayDeleteMark(){
            $id_u = $this->userModel->getUserID();
            $val = $id_u->fetch_assoc();
            $id_utilisateur = $val["id_u"];
            $id_f = $this->filmModel->getOneFilm();
            $id_film = $id_f["id_f"];
            $titre = $_POST["film"];
            $this->filmModel->deleteMark($id_utilisateur, $id_film);
            $donnees = $this->filmModel->getOneFilm();
            require("Views/Film.php");
        }
    }