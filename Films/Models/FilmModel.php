<?php

require_once("Models/Model.php");

class FilmModel extends Model {

    function getAllFilmsTitles(){
        $db=$this->dbConnect();
        $sql="SELECT titre, annee_sortie FROM Film ORDER BY annee_sortie DESC LIMIT 10";
        $result=mysqli_query($db, $sql);
        
        return $result;
    }
}