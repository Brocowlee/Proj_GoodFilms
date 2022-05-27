<!DOCTYPE html>
<html>
<link href="public\inscriptionStyle.css" rel="stylesheet">
<head>
    <meta charset="UTF-8">
</head>

<title>Inscription</title>

<body>
<div id="global">
    <div id="inscription">
    
        <h2>Inscription</h2>
        <div id="indication">
        <?php  
        if (isset($inscription_error)){
            if ($inscription_error){
                echo "Ce login est déjà utilisé !";
                echo "</br></br>";
            }
        }

        echo "Veuillez renseigner un identifiant et un mot de passe :";

           
        ?>
        </div>
        
        <form id="info" method="POST">
        <div id="login">Login :</div> <input type="text" name="login" required></br>
        <div id="mdp">Mot de passe :</div> <input type="password" name="password" required></br>
            <button id="inscrireBtn" type="submit" name="action" value="inscription">S'inscrire</buttom>
        </form>
        <form id="retour" method="GET">
        <button id="connecterBtn" type="submit" name="action" value="deconnexion">Se connecter</buttom>
        </form>

    </div>
    </div>
    <?php include_once ("footer.php"); ?>
</body>