<!DOCTYPE HTML>
<html>

<head>
    <title>Nom du film</title>
    <meta content="info">
    <meta charset="UTF-8">
</head>


<content>
<div id="menu_haut">
<a href="accueil.php"><div id="accueil">Good2Watch<div></a>
<a href="liste_films.php"><div id="liste_films">Mes Films<div></a>
<a href="recherche.php"><div id="recherche">Recherche par genre<div></a>
<input type="search" name="recherche" placeholder="Recherche par titre">
<a href="logout.php"><div id="connexion">Déconnexion<div></a>
</div>



    <h3>
        Film recherché
    </h3>

    <a href="personne.php">Réalisateur</a> :
    </br>
    Résumé :
    </br>

    <form action=".">
        <label for='note-select'>
        Note donnée (Nulle si pas regardé):
        </label>
        <select name='nom_zone' id='zone-select'>
            <option value="0">0</option>
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
        </select>
        
        <button type='submit'>Valider</button>
    </form>
    </br>

    Liste des <a href="personne.php">acteurs<a>

</content>
</html>