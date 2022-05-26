<!DOCTYPE HTML>
<html>

<head>
    <meta charset="UTF-8">
</head>

<content>

    <?php require("Views/header.php"); ?>

    <body>
        <h1>Vous nous avez manqué <?php echo $userName->fetch_assoc()["prenom_u"] ?></h1>
        </br>

        <h3>Voici les derniers films sortis :</h3>

        <ul>
            <?php while($donnees = $liste_films->fetch_array()){ ?>
                
                <li>
                <form method='POST' >
                    <select name="film">
                        <option value="<?php $donnees['titre'] ?>">
                            <?php echo  $donnees['titre'] ?>
                        </option>
                    </select>
                    <button type="submit" name="action" value="un_film">
                        Détails
                    </button>   
                </form>
                </li>      
            <?php } ?>     
        </ul>


    </body>
</content>

