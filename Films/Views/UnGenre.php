<!DOCTYPE HTML>
<html>

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="public/unGenreStyle.css">
</head>

<?php require("Views/header.php"); ?>

<body>
    <div id="global">
    <h1> Films du genre : <?php echo $genre ?> </h1>
<div id="un_genre">
    <?php while($donnees = $un_genre->fetch_array()){ ?>
        <div id="un_film">
        <form id="form_film">
            <input type="hidden" name="film" value="<?php echo $donnees['titre'] ?>">

            <button id="bouton_film" type="submit" name="action" value="un_film">
                <img src="<?php echo $donnees['image']?>" width="200" height="300">
            </button>
        </form>
        </div>
    <?php } ?> 


    </div>
</div>
<?php include_once ("footer.php"); ?>
</body>
