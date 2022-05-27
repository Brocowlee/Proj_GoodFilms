<!DOCTYPE HTML>

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="public/headerStyle.css">
    <title>Good2Watch</title>
</head>

<div id="menu_haut">
    <form id="form_accueil" method='POST'>
        <input type="hidden" name="login" value="<?= $_SESSION['login'] ?>">
        <button id="bouton_a" type="submit" name="action" value="accueil">
            <div id="bouton_accueil">
                Good2Watch
            </div>
        </button>
    </form>
    <form id="form_profil" method='POST'>
        <input type="hidden" name="target_utilisateur" value="<?= $_SESSION['login'] ?>">
        <button id="bouton" type="submit" name="action" value="mes_films">
            Mon Profil
        </button>
    </form>
    <form id="form_recherche_genre" method='POST'>
        <button id="bouton" type="submit" name="action" value="genre">
            Recherche par genre
        </button>
    </form>
    <form id="form_recherche" method='POST'>
        <input type="search" name="recherche" placeholder="Recherche par titre" required>
        <button id="bouton" type="submit" name="action" value="recherche">
            Rechercher
        </button>
    </form>
    <form id="form_recherche_amis" method='POST'>
        <input type="search" name="recherche_amis" placeholder="Recherche d'amis" required>
        <button id="bouton" type="submit" name="action" value="recherche_amis">
            Rechercher
        </button>
    </form>
    <form id="form_deconnexion" method='POST'>
        <button id="bouton" type="submit" name="action" value="deconnexion">
            Déconnexion
        </button>
    </form>
</div>

