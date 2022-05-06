<!DOCTYPE HTML>

<head>
<title>Connexion</title>
<meta content="info">
<meta charset="UTF-8">
</head>


<html>

<head>
<title>Good2Watch</title>
<meta content="info">
<meta charset="UTF-8">
</head>



<content>

<body>



    <div id="connexion">
    <h2>Connexion</h2>
 
    <form id="informations" action="login.php" method="POST">
        login : <input type="text" name="login" required></br>
        mot de passe : <input type="password" name="mdp" required></br>
        <button type="submit">Connexion</buttom>
    </form>
    </div>

    <ul>
    <?php
    while($donnees = $result->fetch_array())
    {
    ?>
    <li>
        <?php echo $donnees['titre']; ?>

    </li>

    <?php } ?>

    </ul>

</body>

</content>

</html>


