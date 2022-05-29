<!DOCTYPE HTML>
<html>

<head>
    <link rel="stylesheet" href="public/utilisateurStyle.css">
</head>



<body>
    
<header>
<?php require("Views/header.php"); ?>
</header>
    <div id="content">
    <?php 
    if ($soi){
        echo'<h1>Votre profil</h1>';
        $utilisateur_target = $login;
    } 

        
    else{
        if(isset($_GET["suivre"])){
            $utilisateur_target = $_GET["suivre"];
        }
        else if(isset($_GET["fuir"])){
            $utilisateur_target = $_GET["fuir"];
        }

        echo'<h1>Profil de '.$utilisateur_target.'</h1>';
        
        
        if($friend){
        ?>
            <form id="form_fuir">
                <input type="hidden" name="fuir" value="<?= $utilisateur_target ?>">                            
                <button id="bouton_ami" type="submit" name="action" value="fuir">Arrêter de suivre</button>
            </form>

        <?php } else { ?>

            <form id="form_suivre">
                <input type="hidden" name="suivre" value="<?= $utilisateur_target ?>">
                <button id="bouton_ami" type="submit" name="action" value="suivre">Suivre</button>
            </form>

        <?php }             

    }             

    
    ?>

    <div id="amis">
        <h3>Personnes suivies :</h3>

        <?php  ?>            
        

        <?php while($donnees = $liste_amis->fetch_array()){ 

            $login = $donnees['login']; 
            echo'<form id="form_amis">'; 
            echo'<input type="hidden" name="target_utilisateur" value="'.$login.'"/>';
            echo'<button id="bouton_ami" type="submit" name="action" value="target_utilisateur">'.$login.'</button>';
            echo'</form>';


        } ?>  
     

    </div>  


        <h3>Films regardés :</h3>
        
    <div id="films">

                <?php while($donnees = $films_watched->fetch_array()){ ?>
            

                        <form id="form_film">
                            <input type="hidden" name="film" value="<?= $donnees['titre'] ?>">
                            <button id="bouton_film" type="submit" name="action" value="un_film" >
                                <img src="<?php echo $donnees['image']?>" width="200" height="300">
                            </button>
                        </form>

                        
                <?php } ?>  


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
    </div>

<footer>
<?php require("footer.php"); ?>
</footer>
</body>
