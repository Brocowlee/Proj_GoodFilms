<!DOCTYPE HTML>

<head>
    <?php require("Views/header.php"); ?>
    <link rel="stylesheet" href="public/accueilStyle.css">
</head>

<body>
    <h1>Vous nous avez manqué <?= $_SESSION["login"] ?></h1>
    </br>

    <h2>Voici les derniers films sortis :</h2>

    <div id="liste_films">
        <?php while($donnees = $liste_films->fetch_array()){ ?>
            
            <div id="un_film">
                <form id="form_film" method='POST'>
                    <input type="hidden" name="film" value="<?php echo $donnees['titre'] ?>">
                    <button id="bouton_film" type="submit" name="action" value="un_film">
                        <img src="<?php echo $donnees['image']?>"  width="200" height="300">
                    </button>
                </form>
            </div>      
        <?php } ?>     
    </div>


</body>

