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

        <form method='POST'>
            <?php while($donnees = $films_recherche->fetch_array()){ ?>
                <li>
                    <?php echo $donnees['titre']." : ".$donnees['note']." ";?>
                    <button name="action" value="<?php $donnees['titre'] ?>">
                        détails
                    </button>
                </li>      
            <?php } ?>     
</form>


    </body>
</content>