<!DOCTYPE HTML>
<html>

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="public/genreStyle.css">
</head>



    <?php require("Views/header.php"); ?>

    <body>

    <div id="page_genre">
        <h3>Liste des genres :</h3>

        <div id="liste_genres">
            <?php while($donnees = $genres->fetch_array()){ ?>
                
                <div id="un genre">
                    <form id="form_film" method='POST'>
                        <input type="hidden" name="genre" value="<?php echo $donnees['genre'] ?>">
                        <button id="bouton_genre" type="submit" name="action" value="un_genre">
                            <?php echo  $donnees['genre'] ?>
                        </button>
                    </form>
                </div>

            <?php } ?>     
        </div>
    </div>


    </body>


