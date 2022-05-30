<?php 
    require_once("Models/UserModel.php");
    require_once("Models/FilmModel.php");

    class UserController{

        private $userModel;
        private $filmModel;

        function __construct(){
            //Nous avons besoin ici des fonctions présentes dans UserModel et FilmModel
            $this->userModel = new UserModel();
            $this->filmModel = new FilmModel();
        }


        function displayConnexion(){
            //Lorsque l'utilisateur a rentré son login et mdp et que l'on souhaite vérifier

            //On vérifie si les informations sont bien transmises 
            if(isset($_POST["login"]) && isset($_POST["password"])){
                
                //On appelle la fonction verifyUser qui décrypte le mdp de la bdd et le compare avec celui de l'input
                if($this->userModel->verifyUser($_POST["login"], $_POST["password"])){
                    //Si tout est bon, on récupère les infos nécessaires...
                    $_SESSION["connexion"]="ok";
                    $_SESSION["login"]=$_POST["login"];
                    $_SESSION["mdp"]=$_POST["password"];
                    $val=$this->userModel->getUserId();
                    $_SESSION["user_id"] = $val->fetch_assoc();
                    $liste_films = $this->filmModel->getAllFilmsTitles();
                    //... et on affiche l'accueil
                    require("Views/Accueil.php");
                }
                else {
                    //Sinon on retourne à la page de connexion avec la variable connexion qui change de valeur
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
            //Lorsque l'utilisateur appuie sur le bouton d'inscription, on affiche l'inscription
            require("Views/Inscription.php");
        }
    

        function createUtilisateur(){
            //est appelée lorsque l'utilisateur a rentré un login et un mdp et appuie sur le bouton pour s'inscrire
            
            $login = $_POST["login"]; 
            //On vérifie que le login entré par l'utilisateur n'est pas déjà présent dans la base de données
            if($this->userModel->alreadyExist($login)){
                //Si il est présent, on mets la variable erreur à vraie et on réaffiche l'inscription
                $inscription_error = true;

                require("Views/Inscription.php");
            } else {
                //Si le login n'existe pas dans la bdd, alors on chiffre le mdp,
                $password = $_POST["password"];
                $salt = "$2a$10$8l".substr(str_replace('+', '.', base64_encode(pack('N4', mt_rand(), mt_rand(), mt_rand(), mt_rand()))), 0, 22);
                $hash = crypt($password, $salt);
                
                //on ajoute le nouvel utilisateur à la bdd
                $this->userModel->createUser($login, $password, $salt, $hash);
                
                //et on affiche la page de connexion
                require("Views/Connexion.php");
               
            }

        }
       
        function displayOneUtilisateur(){
            //Lorsque l'on cherche à afficher le profil d'un utilisateur


            $donnees = $this->userModel->getOneUtilisateur($_GET["target_utilisateur"]);
            //On a les logins de l'utilisateur connecté et celui dont on souhaite voir le profil
            $login = $_SESSION["login"] ;
            $utilisateur_target = $_GET["target_utilisateur"];


            //On vérifie si l'utilisateur connecté est celui dont on souahite afficher le profil et, si non, si ces deux utilisateurs sont amis
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

            //On retire les mdp et login de la session et on affiche la connexion

            unset($_SESSION['login']);
            unset($_SESSION['mdp']);
            $_SESSION['connexion']="deconnecte";
            require("Views/Connexion.php");
        }

        function displayAccueil(){
            //affiche l'accueil
            $userName = $_GET["login"];
            $liste_films = $this->filmModel->getAllFilmsTitles();
            require("Views/Accueil.php");
        }

        function addToFriend(){
            //Lorsque l'utilisateur est sur le profil de quelqu'un d'autre et souhaite suivre cette personne

            $target = $_GET["suivre"];
            $login = $_SESSION["login"];
            
            //On rajoute dans la table le lien entre ces deux utilisateurs
            $this->userModel->addFriend($login, $target);


            //On récupère les infos et on affiche
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

            //Lorsque l'utilisateur veut arrêter de suivre un autre utilisateur
            $target = $_GET["fuir"];
            $login = $_SESSION["login"];


            //On récupère les id des deux utilisateurs et on supprime dans la table des amis
            $id1 = $this->userModel->getUserId($login);
            $userID = $id1->fetch_array()["id_utilisateur"];
            $id2 = $this->userModel->getUserId($target);
            $targetID = $id2->fetch_array()["id_utilisateur"];


            $this->userModel->removeFriend($userID, $targetID);




            //On récupère les informations de la page sur laquelle on était et on ré-affiche la même

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

            //Lorsqu'on modifie une note 
            $titre = $_GET["film"];
            $login = $_SESSION["login"];


            $id_u = $this->userModel->getUserID();
            $val = $id_u->fetch_assoc();
            $id_utilisateur = $val["id_utilisateur"];
            $id_f = $this->filmModel->getOneFilm($titre, $login);
            $id_film = $id_f["id_film"];

            //On appelle la fonction du modèle pour changer la note
            $this->filmModel-> changeMark($id_utilisateur, $id_film, $_GET["new_note"]);

            //On reprend les infos pour afficher un film
            $donnees = $this->filmModel->getOneFilm($titre, $login);
            $last_comments = $this->filmModel->showLastComments($titre); 
            $realisateur = $this->filmModel->getRealisateur($id_film);
            $has_note = $this->filmModel->hasNote($id_film);
            $avg = $this->filmModel->getNoteMean($id_film);
            require("Views/Film.php");
        }

        function displayDeleteMark(){

            //Lorsque l'on supprime la note que l'on a mise a un film
            $titre = $_GET["film"];
            $login = $_SESSION["login"];



            $id_u = $this->userModel->getUserID();
            $val = $id_u->fetch_assoc();
            $id_utilisateur = $val["id_utilisateur"];
            $id_f = $this->filmModel->getOneFilm($titre, $login);
            $id_film = $id_f["id_film"];
 
            //On appelle la fonction du modèle pour supprimer la note
            $this->filmModel->deleteMark($id_utilisateur, $id_film);
            $donnees = $this->filmModel->getOneFilm($titre, $login);
            $last_comments = $this->filmModel->showLastComments($titre); 
            $realisateur = $this->filmModel->getRealisateur($id_film);
            $has_note = $this->filmModel->hasNote($id_film);
            $avg = $this->filmModel->getNoteMean($id_film);
            require("Views/Film.php");
        }

        function addComment(){

            //Lorsque l'on ajoute un commentaire à un film

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
