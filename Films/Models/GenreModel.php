<?php

require_once("Models/Model.php");

class GenreModel extends Model {

    function getAllGenres(){
        $db=$this->getDatabaseConnection();
        $sql="SELECT DISTINCT genre FROM film, genre, genres2films WHERE genre.id_genre = genres2films.id_genre and film.id_film=genres2films.id_film";
        $result=mysqli_query($db, $sql);
        
        return $result;
    }



}


