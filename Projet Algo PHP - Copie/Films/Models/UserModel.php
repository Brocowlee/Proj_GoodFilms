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

        function getUserId(){
            $db=$this->getDatabaseConnection();
            $sql="SELECT id_utilisateur FROM utilisateur WHERE login='".$_SESSION["login"]."';";
            $result=mysqli_query($db, $sql);
            return $result;
        }

        function getDatabaseConnection(){
            return $this->getConnection();
        }

       



    }