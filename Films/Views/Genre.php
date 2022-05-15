<!DOCTYPE HTML>
<html>

<head>
    <meta charset="UTF-8">
</head>

<content>

    <?php require("Views/header.php"); ?>

    <body>

        <h3>Liste des genres :</h3>

        <ul>
            <?php while($donnees = $genres->fetch_array()){ ?>
                
                <li>
                    <form id="form_film" method='POST'>
                        <input type="hidden" name="genre" value="<?php echo $donnees['genre'] ?>">
                        <button type="submit" name="action" value="un_genre">
                            <?php echo  $donnees['genre'] ?>
                        </button>
                    </form>
                </li>      
            <?php } ?>     
        </ul>


    </body>
</content>

