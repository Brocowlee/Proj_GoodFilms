<!DOCTYPE html>
<html>
<link href="css\Connexion.css" rel="stylesheet">
<head>
    <meta charset="UTF-8">
</head>

<title>Connexion</title>

<body>

    <div id="connexion">

        <h2>Connexion</h2>
        
        <form id="informations" method="POST">
            <div id="login">Login :</div> <input id="barreLogin" type="text" name="login" required></br>
            <div id="mdp">Mot de passe :</div> <input id="barreMdp" type="password" name="password" required></br>
            <button id="connexionBtn" type="submit" name="action" value="connexion">Connexion</buttom>
        </form>
<<<<<<< HEAD
        <form id="send_to_inscription">
            <button type="submit" name="action" value="send_to_inscription">Inscription</buttom>
=======
        <form id="send_to_inscription" method="POST">
            <button id="inscriptionBtn" type="submit" name="action" value="send_to_inscription">Inscription</buttom>
>>>>>>> origin/connexion_css
        </form>
      

    </div>
    <?php include_once ("footer.php"); ?>
</body>
</html>