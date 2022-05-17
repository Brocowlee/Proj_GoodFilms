<?php

require_once("Models/Model.php");

class GenreModel extends Model {

    function getAllGenres(){
        $db=$this->dbConnect();
        $sql="SELECT DISTINCT genre FROM Film, Genre, genres2films WHERE Genre.id_genre = genres2films.id_genre and Film.id_film=genres2films.id_film";
        $result=mysqli_query($db, $sql);
        
        return $result;
    }

}


