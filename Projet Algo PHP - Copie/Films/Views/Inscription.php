<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
</head>

<title>Inscription</title>

<body>

    <div id="incription">

        <h2>Inscription</h2>
        
        <?php  
        include ("../Models/UserModel.php");

        echo "Veuillez renseigner un identifiant et un mot de passe :";

            if ($_SERVER["REQUEST_METHOD"] == "POST") {

                if(isset($_POST["action"])){
                    echo "test";
                }  else {
                    $UserModel = new UserModel();
                    $db = $UserModel->getDatabaseConnection();
    
                    $login = $_POST["login"]; 
                    $password = $_POST["password"];
                    
                    $result = mysql_query("SELECT mot_de_passe, salt FROM utilisateur WHERE login='$login' LIMIT 1");
                    $row = mysql_fetch_assoc($result);
                    echo $row['option_value'];

                    $salt = "$2a$10$8l".substr(str_replace('+', '.', base64_encode(pack('N4', mt_rand(), mt_rand(), mt_rand(), mt_rand()))), 0, 22);
                    $hash = crypt($password, $salt);
    
                    echo $salt;
    
                    $sql = "INSERT INTO `utilisateur` (`login`, `mot_de_passe`,`admin`, `salt`) VALUES ('$login', '$hash', 0, '$salt');";
                    $result=mysqli_query($db, $sql);
                }

               


               

            }

        ?>
        
        <form id="inscription" method="POST">
            login : <input type="text" name="login" required></br>
            mot de passe : <input type="password" name="password" required></br>
            <button type="submit" name="action" value="inscription">S'inscrire</buttom>
        </form>

    </div>

</body>