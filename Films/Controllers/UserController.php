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

                        $sql= "SELECT mot_de_passe, salt FROM utilisateur WHERE login = '$login' LIMIT 1";
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

        function displayInscription(){
            require("Views/Inscription.php");
        }
        

        
        function createUtilisateur(){


            $login = $_POST["login"]; 

            if($this->userModel->alreadyExist($login)){

                echo "Ce login est déjà utilisé !";

                require("Views/Inscription.php");
            } else {

                $password = $_POST["password"];
                
                $salt = "$2a$10$8l".substr(str_replace('+', '.', base64_encode(pack('N4', mt_rand(), mt_rand(), mt_rand(), mt_rand()))), 0, 22);
                $hash = crypt($password, $salt);
    
                $this->userModel->createUser($login, $password, $salt, $hash);
    
                require("Views/Connexion.php");
               
            }

        }
       
        function displayOneUtilisateur(){
            $donnees = $this->userModel->getOneUtilisateur();
            require("Views/Utilisateur.php");
        }

        function displayLastFilmsWatched($target){
            return $this->userModel->getFilmsWatched($target);
        }

        function displayFriendList($target){
            return $this->userModel->getFriendList($target);
        }

        function displayLastComments($target){
            return $this->userModel->getLastComments($target);
        }
    
        function displayDeconnexion(){
            unset($_SESSION['login']);
            unset($_SESSION['mdp']);
            $_SESSION['connexion']="deconnecte";
            require("Views/Connexion.php");
        }

        function displayAccueil(){
            $userName = $_POST["login"];
            $liste_films = $this->filmModel->getAllFilmsTitles();
            require("Views/Accueil.php");
        }

        function addToFriend(){
            $target = $_POST["suivre"];
            $user = $_SESSION["login"];
            
            $this->userModel->addFriend($user, $target);
            require("Views/Utilisateur.php");
        }

        function removeFromFriend(){
            $target = $_POST["fuir"];
            $user = $_SESSION["login"];

            $this->userModel->removeFriend($user, $target);
            require("Views/Utilisateur.php");
        }



        function displayModifyMark(){
            $titre = $_POST["film"];
            $login = $_SESSION["login"];


            $id_u = $this->userModel->getUserID();
            $val = $id_u->fetch_assoc();
            $id_utilisateur = $val["id_utilisateur"];
            $id_f = $this->filmModel->getOneFilm($titre, $login);
            $id_film = $id_f["id_film"];


            $this->filmModel-> changeMark($id_utilisateur, $id_film, $_POST["new_note"]);
            $donnees = $this->filmModel->getOneFilm($titre, $login);
            $last_comments = $this->filmModel->showLastComments($titre); 
            require("Views/Film.php");
        }

        function displayDeleteMark(){
            $titre = $_POST["film"];
            $login = $_SESSION["login"];



            $id_u = $this->userModel->getUserID();
            $val = $id_u->fetch_assoc();
            $id_utilisateur = $val["id_utilisateur"];
            $id_f = $this->filmModel->getOneFilm($titre, $login);
            $id_film = $id_f["id_film"];
 
            $this->filmModel->deleteMark($id_utilisateur, $id_film);
            $donnees = $this->filmModel->getOneFilm($titre, $login);
            $last_comments = $this->filmModel->showLastComments($titre); 
            require("Views/Film.php");
        }

        function addComment(){

            $titre = $_POST["film"];
            $login = $_SESSION["login"];

            // partie pour l'id utilisateur
            $id_u = $this->userModel->getUserID();
            $val = $id_u->fetch_assoc();
            $id_utilisateur = $val["id_utilisateur"];

            // partie pour la date
            $date = new DateTime();
            $datetime = $date->format('Y-m-d H:i:s');

            // partie pour le commentaire
            $commentaire = $_POST['ajouter_commentaire'];

            $this->filmModel->addComment($titre, $id_utilisateur, $commentaire, $datetime);
            $donnees = $this->filmModel->getOneFilm($titre, $login);
            $last_comments = $this->filmModel->showLastComments($titre); 
            require("Views/Film.php");
        }

        

    
    }