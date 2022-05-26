<?php
    
    require_once("Model.php");

    class UserModel extends Model{

        function getUserId($target = null){
            $target = $target ?? $_SESSION['login'];
            $db=$this->getDatabaseConnection();
            $sql="SELECT id_utilisateur FROM utilisateur WHERE login='".$target."';";
            $result= mysqli_query($db, $sql);

            return $result;
        }

        function createUser($login, $password, $salt, $hash){
            $db=$this->getDatabaseConnection();
            $sql = "INSERT INTO `utilisateur` (`login`, `mot_de_passe`,`admin`, `salt`) VALUES ('$login', '$hash', 0, '$salt');";
            $result=mysqli_query($db, $sql);     
        }

        function addFriend($user, $target){
            $db=$this->getDatabaseConnection();
            $sql="insert into amis(id_utilisateur1, id_utilisateur2) VALUE ((select id_utilisateur from utilisateur where login = '".$user."'),(select id_utilisateur from utilisateur where login = '".$target."'));";
            $result=mysqli_query($db, $sql);
        }
    
        function removeFriend($userID, $targetID){
            $db=$this->getDatabaseConnection();
            $sql="DELETE FROM amis WHERE id_utilisateur1=".$userID." AND id_utilisateur2 = ".$targetID." ;";
            $result=mysqli_query($db, $sql);
        }

        function getOneUtilisateur($login){

            $db=$this->getDatabaseConnection();
     
            $sql="SELECT id_utilisateur, login FROM utilisateur WHERE utilisateur.login = '".$login."'";
            $result=mysqli_query($db, $sql);
            $donnees=$result->fetch_assoc();
            return $donnees;
            
        }

        function getFilmsWatched($target){
            $db=$this->getDatabaseConnection();  
            $sql="select titre from film where id_film in ( select id_film from commentaire where id_utilisateur = '".$target."' UNION select id_film from note where id_utilisateur = '".$target."');";
            $result=mysqli_query($db, $sql);
            return $result;
        }

        function getFriendList($target){
            $db=$this->getDatabaseConnection();  
            $sql="select login from utilisateur where id_utilisateur in (select id_utilisateur2 from amis where id_utilisateur1 = '".$target."');";
            $result=mysqli_query($db, $sql);
            return $result;
        }

        function getLastComments($target){
            $db=$this->getDatabaseConnection();  
            $sql="select commentaire.commentaire, commentaire.id_film, film.titre from commentaire, film where id_utilisateur = '".$target."' and commentaire.id_film = film.id_film Order by date desc;";
            $result=mysqli_query($db, $sql);
            return $result;
        }

        function isAlreadyFriendWith($user, $target){
            $db=$this->getDatabaseConnection(); 
            $sql="select count(*) as count from amis where id_utilisateur1 = (select id_utilisateur from utilisateur where login = '".$user."') and id_utilisateur2 = (select id_utilisateur from utilisateur where login = '".$target."');";
            $result=mysqli_query($db, $sql);
            $row = mysqli_fetch_array($result);
            $count = $row["count"];

            return $count > 0 ? True : False;
        }

        function alreadyExist($target){
            $db=$this->getDatabaseConnection(); 
            $sql="select count(*) as count from utilisateur where login = '".$target."';";
            $result=mysqli_query($db, $sql);
            $row = mysqli_fetch_array($result);
            $count = $row["count"];

            return $count > 0 ? True : False;
        }



        function verifyUser($login, $password){
            $db = $this->getDatabaseConnection();
            $sql= "SELECT mot_de_passe, salt FROM utilisateur WHERE login = '$login' LIMIT 1";
            $result = mysqli_query($db, $sql);
                
            $row = $result->fetch_array();
            //Liste des variables nécessaires pour l'affichage

            if(isset($row['mot_de_passe'])){
                $bdd_hash = $row['mot_de_passe'];
                $salt = $row['salt'];
    
                $hash = crypt($password, $salt);
                    
                    if (hash_equals($hash, $bdd_hash)) {
                        return true;
                    }
            }
            return false;
        }

    }