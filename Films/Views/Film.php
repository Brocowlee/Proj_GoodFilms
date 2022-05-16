<!DOCTYPE HTML>
<html>

<head>
    <meta charset="UTF-8">
</head>

<content>

    <?php require("Views/header.php"); ?>

    <body>
        <h1><?php echo $titre ?> </h1>
        année de sortie : <?php echo $donnees['annee_sortie'] ?>

        <h2>Résumé</h2>
        <?php echo $donnees['resume'] ?>
        </br>
        <?php 
            if (isset($donnees['note'])){
                echo "Vous avez mis la note de ".$donnees['note']."/10";
        ?>
            <form id="form_film" method='POST'>
                <input type="hidden" name="film" value="<?php echo $donnees['titre'] ?>">
                <button type="submit" name="action" value="sup_note">
                    Supprimer la note
                </button>
            </form>

        <?php

            }
            else{
                echo "</br>Vous n'avez pas noté ce film";
            }
        ?>
        <form id="note_film" method='POST'>
            <input type="hidden" name="film" value="<?php echo $donnees['titre'] ?>">
            Noter ce film : 
            <select name="new_note">
                <option value="">--Choisissez une note--</option>
                <option value=1>1</option>
                <option value=2>2</option>
                <option value=3>3</option>
                <option value=4>4</option>
                <option value=5>5</option>
                <option value=6>6</option>
                <option value=7>7</option>
                <option value=8>8</option>
                <option value=9>9</option>
                <option value=10>10</option>
                </select>
                <button type="submit" name="action" value="notation">
                Valider
            </button>
        </form>
    </body>
</content>
