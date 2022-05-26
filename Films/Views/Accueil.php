<!DOCTYPE HTML>
<html>

<head>
    <meta charset="UTF-8">
</head>

<content>

    <?php require("Views/header.php"); ?>

    <body>
<<<<<<< HEAD
        <h1>Vous nous avez manqué <?php echo $firstName->fetch_assoc()["prenom_u"] ?></h1>
=======
        <h1>Vous nous avez manqué <?php echo $userName->fetch_assoc()["prenom_u"] ?></h1>
>>>>>>> origin/Thomas
        </br>

        <h3>Voici les derniers films sortis :</h3>

        <ul>
            <?php while($donnees = $liste_films->fetch_array()){ ?>
                
                <li>
<<<<<<< HEAD
                    <form id="form_film" method='POST'>
                        <input type="hidden" name="film" value="<?php echo $donnees['titre'] ?>">
                        <?php echo  $donnees['titre'] ?>
                        <button type="submit" name="action" value="un_film">
                            Détails
                        </button>
                    </form>
=======
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
>>>>>>> origin/Thomas
                </li>      
            <?php } ?>     
        </ul>


    </body>
</content>

