<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
</head>

<title>Inscription</title>

<body>

    <div id="incription">

        <h2>Inscription</h2>
        
        <?php  
        if (isset($inscription_error)){
            if ($inscription_error){
                echo "Ce login est déjà utilisé !";
                echo "</br></br>";
            }
        }

        echo "Veuillez renseigner un identifiant et un mot de passe :";

           
        ?>
        
        <form id="inscription">
            login : <input type="text" name="login" required></br>
            mot de passe : <input type="password" name="password" required></br>
            <button type="submit" name="action" value="inscription">S'inscrire</buttom>
        </form>
        <form id="retour">
        <button type="submit" name="action" value="deconnexion">Se connecter</buttom>
        </form>

    </div>

</body>