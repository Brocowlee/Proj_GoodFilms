<!DOCTYPE html>
<html>
<link href="css\Connexion.css" rel="stylesheet">
<head>
    <meta charset="UTF-8">
</head>

<title>Connexion</title>

<body>
    <!--<img src="https://www.le-monde-du-stickers.fr/10649-large_default/sticker-cinema-bobine-de-film.jpg" alt="error"> -->

    <div id="connexion">

        <h2>Connexion</h2>
        

        <?php 
        if(isset($_SESSION["connexion"])){
            if ($_SESSION["connexion"]=="error"){
                echo "login ou mot de passe invalide. Veuillez réessayer :";
            }
        
            else {
                echo "Veuillez renseigner votre identifiant et votre mot de passe :";
            }
        } 
        ?>
        
        <form id="informations" method="POST">
            login : <input type="text" name="login" required></br>
            mot de passe : <input type="password" name="mdp" required></br>
            <button type="submit" name="action" value="connexion">Connexion</buttom>
        </form>


    </div>
    <?php include_once ("footer.php"); ?>
</body>
