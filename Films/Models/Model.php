<?php

class Model {
    
    private $conn;

    private function initConnection(){
        /*https://tp-epua.univ-smb.fr/~takahasv/*/
        /*Connexion à la base de données sur le serveur tp-epua*/
        $con = @mysqli_connect("localhost", "root", "");    
        if (mysqli_connect_errno()) {
            $msg = "erreur ". mysqli_connect_error();
        } else {  
            $msg = "connecté au serveur " . mysqli_get_host_info($con);
            /*Sélection de la base de données*/
            mysqli_select_db($con, "nicolath"); 
            /*mysqli_select_db($conn, "etu"); */ /*sélection de la base sous la VM info642*/
            /*Encodage UTF8 pour les échanges avecla BD*/
            mysqli_query($con, "SET NAMES UTF8");
        }
        $this->conn = $con;
    }

    protected function getConnection(){

        if(!isset($this->conn)){
            $this->initConnection();
        }

        return $this->conn;
    }
}