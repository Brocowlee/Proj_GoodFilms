<?php

require_once("Models/Model.php");

class FilmModel extends Model {

    function getAllFilmsTitles(){
        $db=$this->dbConnect();
        $sql="SELECT titre, annee_sortie FROM Film ORDER BY annee_sortie DESC LIMIT 10";
        $result=mysqli_query($db, $sql);
        
        return $result;
    }

    function getOneFilm(){

        $db=$this->dbConnect();
        $sql="SELECT titre, resume, annee_sortie, duree, note FROM Film, Utilisateur, note WHERE Film.titre='".$_POST["film"]."' and Film.id_f=note.id_f and note.id_u=Utilisateur.id_u and Utilisateur.login='".$_SESSION['login']."'";
        $result=mysqli_query($db, $sql);
        $donnees=$result->fetch_assoc();

        if (is_null($donnees)==FALSE){    
            return $donnees;
        }
        else{
            $sql="SELECT titre, resume, annee_sortie, duree FROM Film WHERE Film.titre='".$_POST["film"]."'";
            $result=mysqli_query($db, $sql);
            $donnees=$result->fetch_assoc();
            return $donnees;
        }
        
    }

    function getMyFilms(){
        $db=$this->dbConnect();
        $sql="SELECT titre, note FROM Film, note, Utilisateur WHERE Utilisateur.id_u='".$_SESSION["user_id"]["id_u"]."' and  Utilisateur.id_u = note.id_u and note.id_f = Film.id_f ";
        $result=mysqli_query($db, $sql);

        return $result;
    }

    function getResearchFilm(){
        $db=$this->dbConnect();
        $sql="SELECT titre FROM Film WHERE Film.titre='".$_POST['recherche']."'";
        $result=mysqli_query($db, $sql);
        
        return $result;
    }


    function getFilmsOneGenre(){
        $db=$this->dbConnect();
        $sql="SELECT titre FROM Film, Genre, genres2films WHERE Film.id_f=genres2films.id_f and Genre.id_g=genres2films.id_g and Genre.genre='".$_POST["genre"]."'";
        $result=mysqli_query($db, $sql);

        return $result;
    }

    function getFilmID(){
        $db=$this->dbConnect();
        $sql="SELECT id_f FROM Film WHERE titre='".$_POST["film"]."'";
        $result=mysqli_query($db, $sql);

        return $result;
    }

    function changeMark($id_u, $id_f, $note){
        $db=$this->dbConnect();
        $sql="SELECT * FROM note WHERE id_u='".$id_u."' AND id_f='".$id_f."'";
        $result=mysqli_query($db, $sql);

        if($result->fetch_assoc()==NULL){
            $sql="INSERT INTO note (id_u, id_f, note) VALUES ('".$id_u."','".$id_f."','".$note."')";
            $result=mysqli_query($db, $sql);
        }
        else {
            $sql="UPDATE note SET note='".$note."' WHERE id_u='".$id_u."' AND id_f='".$id_f."'";
            $result=mysqli_query($db, $sql);
        }

        return $result;
    }

    function deleteMark($id_u, $id_f){
        $db=$this->dbConnect();
        $sql="DELETE FROM note WHERE id_u='".$id_u."' AND id_f='".$id_f."'";
        $result=mysqli_query($db, $sql);
    }
}