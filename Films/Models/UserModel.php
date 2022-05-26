<?php
    
    require_once("Models/Model.php");

    class UserModel extends Model{

        function verifyUser(){
            $db=$this->dbConnect();
            $sql="SELECT * FROM Utilisateur WHERE login ='".$_POST["login"]."' and mot_de_passe = '".$_POST["mdp"]."';";
            $result=mysqli_query($db, $sql);
            if($result->fetch_array()==NULL){
                return FALSE;
            }
            else{
                return TRUE;
            }
        }

        function getUserFirstName(){
            $db=$this->dbConnect();
            $sql="SELECT login FROM utilisateur WHERE login='".$_SESSION["login"]."' and mot_de_passe = '".$_SESSION["mdp"]."';";
            $result=mysqli_query($db, $sql);
            return $result;
        }

        function getUserId(){
            $db=$this->dbConnect();
            $sql="SELECT id_utilisateur FROM utilisateur WHERE login='".$_SESSION["login"]."' and mot_de_passe = '".$_SESSION["mdp"]."';";
            $result=mysqli_query($db, $sql);
            return $result->fetch_assoc()["id_u"];
        }



    }