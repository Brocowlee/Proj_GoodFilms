<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
</head>

<title>Connexion</title>

<body>

    <div id="connexion">

        <h2>Connexion</h2>
        
        <form id="informations" method="POST">
            login : <input type="text" name="login" required></br>
            mot de passe : <input type="password" name="password" required></br>
            <button type="submit" name="action" value="connexion">Connexion</buttom>
        </form>
        <form id="send_to_inscription" method="POST">
            <button type="submit" name="action" value="send_to_inscription">Inscription</buttom>
        </form>
      

    </div>

</body>