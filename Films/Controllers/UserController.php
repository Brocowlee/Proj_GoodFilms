<?php 
    require_once("Models/UserModel.php");

    class UserController{

        private $userModel;

        function __construct(){
            $this->userModel = new UserModel();
        }

        function displayConnexion(){
            $user = $this->userModel->verifyUser();
            
            if ($user == TRUE){

                //Liste des variables nécessaires pour l'affichage
                $firstName = $this->userModel->getUserFirstName();
                $_SESSION["login"]=$_POST["login"];
                $_SESSION["mdp"]=$_POST["mdp"];

                require("Views/Accueil.php");
            }
            else {
                $_SESSION["Connexion"]="error";
                require("Views/Connexion.php");
            }

            
            
        }
    }