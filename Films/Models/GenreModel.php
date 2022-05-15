<?php

require_once("Models/Model.php");

class GenreModel extends Model {

    function getAllGenres(){
        $db=$this->dbConnect();
        $sql="SELECT DISTINCT genre FROM Film, Genre, genres2films WHERE Genre.id_g = genres2films.id_g and Film.id_f=genres2films.id_f";
        $result=mysqli_query($db, $sql);
        
        return $result;
    }

}


