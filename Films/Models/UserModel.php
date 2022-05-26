<?php
    
    require_once("Model.php");

    class UserModel extends Model{

        function verifyUser(){
            $db=$this->getDatabaseConnection();
            $sql="SELECT * FROM utilisateur WHERE login='".$_POST["login"]."' and mot_de_passe = '".$_POST["password"]."';";
            $result=mysqli_query($db, $sql);
            if($result->fetch_array()==NULL){
                return FALSE;
            }
            else{
                return TRUE;
            }
        }

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
    
        function removeFriend($user, $target){
            $db=$this->getDatabaseConnection();
            $sql="DELETE FROM amis WHERE ((select id_utilisateur from utilisateur where login = '".$user."') and (select id_utilisateur from utilisateur where login = '".$target."'));";
            $result=mysqli_query($db, $sql);
        }

        function getOneUtilisateur(){

            $db=$this->getDatabaseConnection();
     
            $sql="SELECT id_utilisateur, login FROM utilisateur WHERE utilisateur.login = '".$_POST["target_utilisateur"]."'";
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
            $sql="select commentaire.commentaire, commentaire.id_film from commentaire where id_utilisateur = '".$target."' Order by date desc;";
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

        function getDatabaseConnection(){
            return $this->getConnection();
        }

    }