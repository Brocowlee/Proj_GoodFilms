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
                <?php echo $donnees['titre']." : ".$donnees['note']." ";?>
                <button name="action" value="<?php $donnees['titre'] ?>">
                    détails
                </button>
            </li>      
        <?php } ?> 
        </ul>


    
    </body>
</content>