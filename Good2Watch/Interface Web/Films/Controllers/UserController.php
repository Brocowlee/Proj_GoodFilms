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

            if(isset($_POST["login"]) && isset($_POST["password"])){
                if($this->userModel->verifyUser($_POST["login"], $_POST["password"])){
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



        function displayInscription(){
            require("Views/Inscription.php");
        }
    

        function createUtilisateur(){


            $login = $_POST["login"]; 

            if($this->userModel->alreadyExist($login)){

                $inscription_error = true;

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
            $donnees = $this->userModel->getOneUtilisateur($_GET["target_utilisateur"]);
            
            $login = $_SESSION["login"] ;
            $utilisateur_target = $_GET["target_utilisateur"];

            if($login != $utilisateur_target){
                $soi = false;
                if($this->userModel->isAlreadyFriendWith($login, $utilisateur_target)){
                    $friend = true;
                }
                else{
                    $friend = false;
                }
            }
            else {
                $soi = true;
            }


            $result =  $this->userModel->getUserId($utilisateur_target);
           
            $row = mysqli_fetch_array($result);
            $id = $row["id_utilisateur"];
            $films_watched = $this->userModel->getFilmsWatched($id);

            $liste_amis = $this->userModel->getFriendList($id);
            $last_comments = $this->userModel->getLastComments($id);

            require("Views/Utilisateur.php");
        }


    
        function displayDeconnexion(){
            unset($_SESSION['login']);
            unset($_SESSION['mdp']);
            $_SESSION['connexion']="deconnecte";
            require("Views/Connexion.php");
        }

        function displayAccueil(){
            $userName = $_GET["login"];
            $liste_films = $this->filmModel->getAllFilmsTitles();
            require("Views/Accueil.php");
        }

        function addToFriend(){
            $target = $_GET["suivre"];
            $login = $_SESSION["login"];
            
            $this->userModel->addFriend($login, $target);

            $donnees = $this->userModel->getOneUtilisateur($target);
            $friend = true;
            $soi = false;

            $result =  $this->userModel->getUserId($target);
           
            $row = mysqli_fetch_array($result);
            $id = $row["id_utilisateur"];
            $films_watched = $this->userModel->getFilmsWatched($id);

            $liste_amis = $this->userModel->getFriendList($id);
            $last_comments = $this->userModel->getLastComments($id);

            require("Views/Utilisateur.php");
        }

        function removeFromFriend(){
            $target = $_GET["fuir"];
            $login = $_SESSION["login"];

            $id1 = $this->userModel->getUserId($login);
            $userID = $id1->fetch_array()["id_utilisateur"];
            $id2 = $this->userModel->getUserId($target);
            $targetID = $id2->fetch_array()["id_utilisateur"];


            $this->userModel->removeFriend($userID, $targetID);

            $donnees = $this->userModel->getOneUtilisateur($target);

            $friend = false;
            $soi = false;

            $result =  $this->userModel->getUserId($target);
           
            $row = mysqli_fetch_array($result);
            $id = $row["id_utilisateur"];
            $films_watched = $this->userModel->getFilmsWatched($id);

            $liste_amis = $this->userModel->getFriendList($id);
            $last_comments = $this->userModel->getLastComments($id);

            require("Views/Utilisateur.php");

        }



        function displayModifyMark(){
            $titre = $_GET["film"];
            $login = $_SESSION["login"];


            $id_u = $this->userModel->getUserID();
            $val = $id_u->fetch_assoc();
            $id_utilisateur = $val["id_utilisateur"];
            $id_f = $this->filmModel->getOneFilm($titre, $login);
            $id_film = $id_f["id_film"];


            $this->filmModel-> changeMark($id_utilisateur, $id_film, $_GET["new_note"]);
            $donnees = $this->filmModel->getOneFilm($titre, $login);
            $last_comments = $this->filmModel->showLastComments($titre); 
            $realisateur = $this->filmModel->getRealisateur($id_film);
            $has_note = $this->filmModel->hasNote($id_film);
            $avg = $this->filmModel->getNoteMean($id_film);
            require("Views/Film.php");
        }

        function displayDeleteMark(){
            $titre = $_GET["film"];
            $login = $_SESSION["login"];



            $id_u = $this->userModel->getUserID();
            $val = $id_u->fetch_assoc();
            $id_utilisateur = $val["id_utilisateur"];
            $id_f = $this->filmModel->getOneFilm($titre, $login);
            $id_film = $id_f["id_film"];
 
            $this->filmModel->deleteMark($id_utilisateur, $id_film);
            $donnees = $this->filmModel->getOneFilm($titre, $login);
            $last_comments = $this->filmModel->showLastComments($titre); 
            $realisateur = $this->filmModel->getRealisateur($id_film);
            $has_note = $this->filmModel->hasNote($id_film);
            $avg = $this->filmModel->getNoteMean($id_film);
            require("Views/Film.php");
        }

        function addComment(){

            $titre = $_GET["film"];
            $login = $_SESSION["login"];
            $id_f = $this->filmModel->getOneFilm($titre, $login);
            $id_film = $id_f["id_film"];

            // partie pour l'id utilisateur
            $id_u = $this->userModel->getUserID();
            $val = $id_u->fetch_assoc();
            $id_utilisateur = $val["id_utilisateur"];

            // partie pour la date
            $date = new DateTime();
            $datetime = $date->format('Y-m-d H:i:s');

            // partie pour le commentaire
            $commentaire = $_GET['ajouter_commentaire'];

            $this->filmModel->addComment($titre, $id_utilisateur, $commentaire, $datetime);
            $donnees = $this->filmModel->getOneFilm($titre, $login);
            $last_comments = $this->filmModel->showLastComments($titre); 




            $realisateur = $this->filmModel->getRealisateur($id_film);
            $has_note = $this->filmModel->hasNote($id_film);
            $avg = $this->filmModel->getNoteMean($id_film);
            require("Views/Film.php");
        }

        

    
    }
