<!DOCTYPE HTML>
<html>

<head>
    <meta charset="UTF-8">
</head>

<content>

    <?php require("Views/header.php"); ?>
    
    <body>
        <h1><?= $titre ?> </h1>
        <img src="<?php echo $donnees['image']?>">
</br>
        année de sortie : <?php echo $donnees['annee_sortie']?> 
</br>
        Durée : <?php echo $donnees['duree']?>
</br>
        <?php

            while($rea_donnees = $realisateur->fetch_array()){ 
                $nom = $rea_donnees['nom']; 
                $prenom = $rea_donnees["prenom"];
                echo "Réalisateur : ".$nom." ".$prenom;
            }

            echo'<br>';
        
                
            if($has_note){
                $value = number_format($avg,1,",",".");
                echo "Note global : ".$value;
            } 

        ?>

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

        <h2>Commentaires</h2>
        <div id="commentaire">    


            <form method='POST'>
                <input type="hidden" name="film" value="<?= $donnees['titre'] ?>">
                <input type="search" name="ajouter_commentaire" placeholder="Ajouter un commentaire" required>
                <button type="submit" name="action" value="commentaire">Ajouter</button>
            </form>


            <?php



                echo '<ul>';
                while($donnees = $last_comments->fetch_array()){ 
                   
                    $login = $donnees['login']; 
                    $commentaire = $donnees["commentaire"];
                    $date = $donnees["date"];
        
                    echo'<li><form id="form_amis" method="POST">'; 
        
                    echo'<input type="hidden" name="target_utilisateur" value="'.$login.'"/>';
                    echo'<button type="submit" name="action" value="target_utilisateur">'.$login.'</button>';
                    
                    echo " : $commentaire        $date</br>";
        
                    echo'</form></li>';
                }

                echo '</ul>';

            ?>
                


        </div>







    </body>
</content>
