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
                
                $_SESSION["connexion"]="ok";
                $_SESSION["login"]=$_POST["login"];
                $_SESSION["mdp"]=$_POST["mdp"];
                $_SESSION["user_id"] = $this->userModel->getUserId();
                $firstName = $this->userModel->getUserFirstName();
                
                return $firstName;
            }
            else {
                $_SESSION["connexion"]="error";
                return false;
            }  
        }

        function displayDeconnexion(){
            unset($_SESSION['login']);
            unset($_SESSION['mdp']);
            $_SESSION['connexion']="deconnecte";
        }

        function displayAccueil(){
            return $this->userModel->getUserFirstName();
        }
    }