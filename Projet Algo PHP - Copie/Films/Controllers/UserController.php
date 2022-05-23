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

            if(isset($_SESSION["connexion"])){
                if ($_SESSION["connexion"]=="error"){
                $db = $this->userModel->getDatabaseConnection();

                if(isset($_POST["login"])){
                    if(isset($_POST["password"])){

            
                        $login = $_POST["login"]; 
                        $password = $_POST["password"];

                        $sql= "SELECT mot_de_passe, salt FROM utilisateur WHERE login= '$login' LIMIT 1";
                        $result = mysqli_query($db, $sql);
                            
                        $row = $result->fetch_array();
                        //Liste des variables nécessaires pour l'affichage
                        if(isset($row['mot_de_passe'])){
                            $bdd_hash = $row['mot_de_passe'];
                            $salt = $row['salt'];
                
                            $hash = crypt($password, $salt);
                                
                                if (hash_equals($hash, $bdd_hash)) {
                                    
                                    $_SESSION["connexion"]="ok";
                                    $_SESSION["login"]=$_POST["login"];
                                    $_SESSION["mdp"]=$_POST["password"];
                                    $val=$this->userModel->getUserId();
                                    $_SESSION["user_id"] = $val->fetch_assoc();
                        
                                    $liste_films = $this->filmModel->getAllFilmsTitles();
                                    require("Views/Accueil.php");
                                }
                                else {

                                    $_SESSION["connexion"]="error";
                                    require("Views/Connexion.php");
                                }  
                        }

                        else {

                            $_SESSION["connexion"]="error";
                            require("Views/Connexion.php");
                        }  

                    }
                }
                else {

                    $_SESSION["connexion"]="error";
                    require("Views/Connexion.php");
                }  
            }
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
            echo session_status();
            $userName = $_POST["login"];
            $liste_films = $this->filmModel->getAllFilmsTitles();
            require("Views/Accueil.php");
        }

        function displayModifyMark(){
            $id_u = $this->userModel->getUserID();
            $val = $id_u->fetch_assoc();
            $id_utilisateur = $val["id_utilisateur"];
            $id_f = $this->filmModel->getOneFilm();
            $id_film = $id_f["id_film"];
            $titre = $_POST["film"];
            $this->filmModel-> changeMark($id_utilisateur, $id_film, $_POST["new_note"]);
            $donnees = $this->filmModel->getOneFilm();
            require("Views/Film.php");
        }

        function displayDeleteMark(){
            $id_u = $this->userModel->getUserID();
            $val = $id_u->fetch_assoc();
            $id_utilisateur = $val["id_utilisateur"];
            $id_f = $this->filmModel->getOneFilm();
            $id_film = $id_f["id_film"];
            $titre = $_POST["film"];
            $this->filmModel->deleteMark($id_utilisateur, $id_film);
            $donnees = $this->filmModel->getOneFilm();
            require("Views/Film.php");
        }
    }