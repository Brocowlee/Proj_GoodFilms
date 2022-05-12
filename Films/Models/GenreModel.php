<?php

require_once("Models/Model.php");

class GenreModel extends Model {

    function getAllGenres(){
        $db=$this->dbConnect();
        $sql="SELECT DISTINCT genre FROM Film, Genre WHERE Genre.id_g = Film.id_g";
        $result=mysqli_query($db, $sql);
        
        return $result;
    }

}


