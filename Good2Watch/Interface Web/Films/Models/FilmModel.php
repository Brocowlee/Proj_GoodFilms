<?php

require_once("Models/Model.php");

class FilmModel extends Model {

    function getAllFilmsTitles(){
        //récupère les infos des 21 films les plus récents
        $db=$this->getDatabaseConnection();
        $sql="SELECT titre, annee_sortie, image FROM film ORDER BY annee_sortie DESC LIMIT 21";
        $result=mysqli_query($db, $sql);
        
        return $result;
    }

    function getOneFilm($titre, $login){

        //récupère les infos pour un film
        $db=$this->getDatabaseConnection();
        $sql="SELECT film.id_film, image, titre, resume, annee_sortie, duree, note FROM film, utilisateur, note WHERE film.titre='".$titre."' and film.id_film=note.id_film and note.id_utilisateur=utilisateur.id_utilisateur and utilisateur.login='".$login."'";
        $result=mysqli_query($db, $sql);
        $donnees = $result->fetch_assoc();

        //On vérifie si l'utilisateur a mis une note que l'on peut récupérer
        if (is_null($donnees)==FALSE){    
            return $donnees;
        }
        else{
            //Sinon on récupère que les infos du film
            $sql="SELECT * FROM film WHERE film.titre='".$titre."'";
            $result=mysqli_query($db, $sql);
            $donnees=$result->fetch_assoc();
            return $donnees;
        }
        
    }

    function getMyFilms(){
        //récupère la liste des films qu'à vu un utilisateur
        $db=$this->getDatabaseConnection();
        $sql="SELECT titre, note FROM film, note, utilisateur WHERE utilisateur.login='".$_SESSION["login"]."' and  utilisateur.id_utilisateur = note.id_utilisateur and note.id_film = film.id_film ";
        $result=mysqli_query($db, $sql);
        return $result;
    }

    function getResearchFilm(){
        //récupère la liste des films qui corrspondent à une recherche
        $db=$this->getDatabaseConnection();
        $sql="SELECT titre, image FROM film WHERE film.titre LIKE '%".$_GET['recherche']."%' ORDER BY titre DESC";
        $result=mysqli_query($db, $sql);
        
        return $result;
    }

    function getResearchFriend(){
        //récupère la liste des utilisateurs qui corrspondent à une recherche
        $db=$this->getDatabaseConnection();
        $sql="SELECT login FROM utilisateur WHERE utilisateur.login LIKE '%".$_GET['recherche_amis']."%' ORDER BY login DESC";
        $result=mysqli_query($db, $sql);
        return $result;
    }

    function getFilmsOneGenre(){
        //récupère tous les films d'un genre
        $db=$this->getDatabaseConnection();
        $sql="SELECT titre, image FROM film, genre, genres2films WHERE film.id_film=genres2films.id_film and genre.id_genre=genres2films.id_genre and genre.genre='".$_GET["genre"]."'";
        $result=mysqli_query($db, $sql);

        return $result;
    }

    function getFilmID(){
        //récupère l'id d'un film
        $db=$this->getDatabaseConnection();
        $sql="SELECT id_film FROM film WHERE titre='".$_GET["film"]."'";
        $result=mysqli_query($db, $sql);

        return $result;
    }

    function getFilmFromID($id){
        //récupère le titre d'un film
        $db=$this->getDatabaseConnection();
        $sql="SELECT titre FROM film WHERE id_film='".$id."'";
        $result=mysqli_query($db, $sql);

        return $result;
    }

    function getNoteMean($id){
        //calcule la moyenne des notes des utilisateurs pour un film
        $db=$this->getDatabaseConnection();
        $sql="select AVG(note) as avg from note where id_film = '".$id."';";
        $result=mysqli_query($db, $sql);
        $row = mysqli_fetch_array($result);
        $avg = $row["avg"];
        return $avg;
    }

    function hasNote($id){
        //vériie si un ou plusieurs utilisateurs ont noté le film
        $db=$this->getDatabaseConnection();
        $sql="select count(*) as count from note where id_film = '".$id."';";
        $result=mysqli_query($db, $sql);
        $row = mysqli_fetch_array($result);
        $count = $row["count"];
        return $count > 0 ? True : False;
    }

    function getRealisateur($id_film){
        //récupère le réalisateur d'un film
        $db=$this->getDatabaseConnection();
        $sql="select personne.nom, personne.prenom from film INNER JOIN realise on realise.id_film = film.id_film INNER JOIN personne on personne.id_personne = realise.id_personne WHERE film.id_film = '".$id_film."';";
        $result=mysqli_query($db, $sql);
        return $result;
    }


    function changeMark($id_u, $id_f, $note){

        //change la note d'un film
        $db=$this->getDatabaseConnection();
        $sql="SELECT * FROM note WHERE id_utilisateur='".$id_u."' AND id_film='".$id_f."'";
        $result=mysqli_query($db, $sql);

        if($result->fetch_assoc()==NULL){
            $sql="INSERT INTO note (id_utilisateur, id_film, note) VALUES ('".$id_u."','".$id_f."','".$note."')";
            $result=mysqli_query($db, $sql);
        }
        else {
            $sql="UPDATE note SET note='".$note."' WHERE id_utilisateur='".$id_u."' AND id_film='".$id_f."'";
            $result=mysqli_query($db, $sql);
        }

        return $result;
    }

    function addComment($titre, $id_u, $commentaire, $date){
        //ajoute un commentaire à un film
        $db=$this->getDatabaseConnection();
        $sql = "SELECT id_film FROM film WHERE titre = '$titre' LIMIT 1";
        $result = mysqli_query($db, $sql);
        $row = $result->fetch_array();
        $id_film = $row['id_film']; 
        $sql = "INSERT INTO `commentaire` (`id_utilisateur`, `id_film`,`commentaire`, `date`) VALUES ('$id_u', '$id_film', '$commentaire', '$date');";
        $result = mysqli_query($db, $sql);
    }

    function deleteMark($id_u, $id_f){
        //supprime la note qu'un utilisateur a mis a un film
        $db=$this->getDatabaseConnection();
        $sql="DELETE FROM note WHERE id_utilisateur='".$id_u."' AND id_film='".$id_f."'";
        $result=mysqli_query($db, $sql);
    }

    function showLastComments($titre){
        //Montre les derniers commentaires des utilisateurs pour un film donné
        $db=$this->getDatabaseConnection();
        $sql = "SELECT utilisateur.login, commentaire, date from commentaire INNER JOIN utilisateur on utilisateur.id_utilisateur = commentaire.id_utilisateur where id_film = (SELECT id_film FROM film WHERE titre = '".$titre."' LIMIT 1) ORDER BY date DESC LIMIT 10;";
        $result=mysqli_query($db, $sql);
        return $result;
    }
}
