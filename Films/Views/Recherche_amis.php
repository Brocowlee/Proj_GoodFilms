<!DOCTYPE HTML>
<html>

<head>
    <meta charset="UTF-8">
</head>

<content>

    <?php require("Views/header.php"); ?>

    <body>
        <h1>Votre recherche : </h1>
        </br>

        <ul>
            <?php 
            
            $filmController = new FilmController();

            $friends_research = $filmController->displayResearchFriend();
            
            while($donnees = $friends_research->fetch_array()){ ?>        

                    <?php $login = $donnees['login']; 

                        echo'<li><form id="form_amis" method="POST">'; 

                        echo'<input type="hidden" name="target_utilisateur" value="'.$login.'"/>';
                        echo'<button type="submit" name="action" value="target_utilisateur">'.$login.'</button>';

                        echo'</form></li>';

                 } ?>     
        </ul>


    </body>
</content>