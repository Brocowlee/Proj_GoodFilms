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
        }
        else{
            echo "</br>Vous n'avez pas noté ce film";
        }
        ?>
    </body>
</content>
