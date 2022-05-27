<!DOCTYPE HTML>
<html>

<head>
    <meta charset="UTF-8">
</head>

<content>

    <?php require("header.php"); ?>

    <body>
            
            <?php 
 



            if ($soi){
                echo'<h1>Votre profil</h1>';
                $utilisateur_target = $login;
            } 

                
            else{
                if(isset($_POST["suivre"])){
                    $utilisateur_target = $_POST["suivre"];
                }
                else if(isset($_POST["fuir"])){
                    $utilisateur_target = $_POST["fuir"];
                }

                echo'<h1>Profil de '.$utilisateur_target.'</h1>';
                
                
                if($friend){
                ?>
                    <form id="form_fuir" method='POST'>
                        <input type="hidden" name="fuir" value="<?= $utilisateur_target ?>">                            
                        <button type="submit" name="action" value="fuir">Arrêter de suivre</button>
                    </form>

                <?php } else { ?>

                    <form id="form_suivre" method='POST'>
                        <input type="hidden" name="suivre" value="<?= $utilisateur_target ?>">
                        <button type="submit" name="action" value="suivre">Suivre</button>
                    </form>

                <?php }             

            }             

            
            ?>

        <div id="amis">
            <h3>Personnes suivies :</h3>

            <?php  ?>            
            
            <ul>
                <?php while($donnees = $liste_amis->fetch_array()){ 

                    $login = $donnees['login']; 
                    echo'<li><form id="form_amis" method="POST">'; 
                    echo'<input type="hidden" name="target_utilisateur" value="'.$login.'"/>';
                    echo'<button type="submit" name="action" value="target_utilisateur">'.$login.'</button>';
                    echo'</form></li>';


                } ?>  
            </ul>       

        </div>  

        <div id="films">

            <h3>Films regardés :</h3>
                <ul>
                    <?php while($donnees = $films_watched->fetch_array()){ ?>
                
                        <li>
                            <form id="form_film" method='POST'>
                                <input type="hidden" name="film" value="<?= $donnees['titre'] ?>">
                                <button type="submit" name="action" value="un_film">
                                    <img src="<?php echo $donnees['image']?>">
                                </button>
                            </form>
                        </li> 
                           
                    <?php } ?>  

                </ul>
        </div>  

        <div id="commentaire">
            <h3>Derniers commentaires :</h3>

            <ul>
                <?php 
                while($donnees = $last_comments->fetch_array()){ 
                    echo "<li>".$donnees['titre']." : ".$donnees['commentaire']."</li> " ;
                } 
                ?>  

            </ul>


        </div>

    
    </body>
</content>