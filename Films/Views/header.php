<!DOCTYPE HTML>
<html>

<head>
    <meta charset="UTF-8">
</head>

<title>Good2Watch</title>

<content>

    <div id="menu_haut">
        <form method='POST'>
            <input type="hidden" name="login" value="<?= $_SESSION['login'] ?>">
            <button type="submit" name="action" value="accueil">Good2Watch</button>
        </form>
        <form method='POST'><button type="submit" name="action" value="mes_films">Mes Films</button></form>
        <form method='POST'><button type="submit" name="action" value="genre">Recherche par genre</button></form>
        <form method='POST'>
            <input type="search" name="recherche" placeholder="Recherche par titre" required>
            <button type="submit" name="action" value="recherche">Rechercher</button>
        </form>
        <form method='POST'>
            <input type="search" name="recherche_amis" placeholder="Recherche d'amis" required>
            <button type="submit" name="action" value="recherche_amis">Rechercher</button>
        </form>
        <form method='POST'><button type="submit" name="action" value="deconnexion">Déconnexion</button></form>
    </div>

</content>