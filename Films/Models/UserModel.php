<?php
    
    require_once("Model.php");

    class UserModel extends Model {

        function verifyUser(){
            $db=$this->dbConnect();
            $sql="SELECT * FROM Utilisateur WHERE login='".$_POST["login"]."' and mdp = '".$_POST["mdp"]."';";
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
            $sql="SELECT prenom_u FROM Utilisateur WHERE login='".$_POST["login"]."' and mdp = '".$_POST["mdp"]."';";
            $result=mysqli_query($db, $sql);
            return $result;
        }

        function getDatabaseConnection(){
            return $this->dbConnect();
        }



    }