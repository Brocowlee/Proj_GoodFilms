<!DOCTYPE HTML>
<html>

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="public/rechercheAmiStyle.css">
</head>



<?php require("Views/header.php"); ?>

<body>
    <div id="page_recherche_ami">
    <h1>Votre recherche : </h1>
    </br>

    <div id="liste_utilisateurs">
        <?php 
        
        while($donnees = $friends_research->fetch_array()){ ?>        

                <?php $login = $donnees['login']; 

                    echo'<form id="form_amis" method="POST">'; 

                    echo'<input type="hidden" name="target_utilisateur" value="'.$login.'"/>';
                    echo'<button id="bouton_ami" type="submit" name="action" value="target_utilisateur">'.$login.'</button>';

                    echo'</form>';

                } ?>     
    </div>
    </div>


</body>
