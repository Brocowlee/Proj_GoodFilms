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
            <?php while($donnees = $films_recherche->fetch_array()){ ?>
                
                <li>
                    <form id="form_film" method='POST'>
                        <input type="hidden" name="film" value="<?php echo $donnees["titre"] ?>">
                        <?php echo  $donnees["titre"]?>
                        <button type="submit" name="action" value="un_film">
                            Détails
                        </button>
                    </form>
                </li>      
            <?php } ?>     
        </ul>


    </body>
</content>