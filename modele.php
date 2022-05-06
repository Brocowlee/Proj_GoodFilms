<?php   

function getFilmsTitles(){
    $sql="select titre from Film";
    $result=mysqli_query($_SESSION["conn"], $sql);
    return $result;
}

function getUser($login){
    $sql="select * from Utilisateur where login=".$login;
    $result=mysqli_query($_SESSION["conn"], $sql);
    return $result;
}
?>


