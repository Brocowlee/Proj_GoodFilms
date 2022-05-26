<!DOCTYPE HTML>
<html>

<head>
    <meta charset="UTF-8">
</head>

<content>

    <?php require("header.php"); ?>

    <body>
            
            <?php 
            $userModel = new UserModel();
            $filmModel = new FilmModel();
            $userController = new UserController();

            $login = $_SESSION["login"] ;
            
            $utilisateur_target;

            if(isset($_POST["target_utilisateur"])){

                $utilisateur_target = $_POST["target_utilisateur"];

                if($login != $utilisateur_target){
                    echo'<h1>Profil de '.$utilisateur_target.'</h1>';
                    
                    
                    if($userModel->isAlreadyFriendWith($login, $utilisateur_target)){
                    ?>
                        <form id="form_fuir" method='POST'>
                            <input type="hidden" name="fuir" value="<?= $utilisateur_target ?>">                            
                            <button type="submit" name="action" value="fuir">Fuir</button>
                        </form>

                    <?php } else { ?>

                        <form id="form_suivre" method='POST'>
                            <input type="hidden" name="suivre" value="<?= $utilisateur_target ?>">
                            <button type="submit" name="action" value="suivre">Suivre</button>
                        </form>

                    <?php }             



                } else {
                    echo'<h1>Votre profil</h1>';
                    $utilisateur_target = $login;
                }               

            } else {
                echo'<h1>Votre profil</h1>';
                $utilisateur_target = $login;
            }

            $result =  $userModel->getUserId($utilisateur_target);
           
            $row = mysqli_fetch_array($result);
            $id = $row["id_utilisateur"];
            
            ?>

        <div id="films">

            <h3>Films regardés :</h3>
                <?php      
                    $films_watched = $userController->displayLastFilmsWatched($id);
                ?>
                <ul>
                    <?php while($donnees = $films_watched->fetch_array()){ ?>
                
                        <li>
                            <form id="form_film" method='POST'>
                                <input type="hidden" name="film" value="<?= $donnees['titre'] ?>">
                                <?= $donnees['titre'] ?>
                                <button type="submit" name="action" value="un_film">Détails</button>
                            </form>
                        </li> 
                           
                    <?php } ?>  

                </ul>
        </div>

        <div id="amis">
            <h3>Personnes suivies :</h3>

            <?php $liste_amis = $userController->displayFriendList($id); ?>            
            
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

        <div id="commentaire">
            <h3>Derniers commentaires :</h3>
            
            <?php $last_comments = $userController->displayLastComments($id); ?>

            <ul>
                    <?php while($donnees = $last_comments->fetch_array()){ 

                        $result =  $filmModel->getFilmFromId($donnees['id_film']);
           
                        $row = mysqli_fetch_array($result);
                        $titre = $row["titre"];

                        ?>

                        <li>
                             <?= "".$titre." : ".$donnees['commentaire'] ?>
                        </li> 
                    <?php } ?>  

                </ul>


        </div>

    
    </body>
</content>