<!DOCTYPE HTML>
<html>

<head>
    <meta charset="UTF-8">
</head>

<content>

    <?php require("Views/header.php"); ?>

    <body>
        <h1>Vos films :</h1>
        <ul>
        <?php while($donnees = $mes_films->fetch_array()){ ?>
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
                <?php echo $donnees['titre']." : ".$donnees['note']." ";?>
                <button name="action" value="<?php $donnees['titre'] ?>">
                    détails
                </button>
>>>>>>> origin/Thomas
            </li>      
        <?php } ?> 
        </ul>


    
    </body>
</content>