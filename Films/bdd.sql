-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:3308
-- Généré le : Dim 15 mai 2022 à 16:49
-- Version du serveur :  5.7.33
-- Version de PHP : 7.4.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

-- --------------------------------------------------------

--
-- Structure de la table `Genre`
--

CREATE TABLE `Genre` (
  `id_Genre` int(15) NOT NULL,
  `genre` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `Genre`
--

INSERT INTO `Genre` (`id_Genre`, `genre`) VALUES
(1, 'Action & Aventure'),
(2, 'Comédie'),
(3, 'Crime & Thriller'),
(4, 'Drame'),
(5, 'Horreur'),
(6, 'Mystère & Thriller'),
(7, 'Fantastique'),
(8, 'Histoire'),
(9, 'Science-Fiction'),
(10, 'Animation'),
(11, 'Comédie Romantique'),
(12, 'Sport & Fitness'),
(13, 'Film de guerre'),
(14, 'Musique & Comédie Musicale'),
(15, 'Documentaire'),
(16, 'Pour enfants'),
(17, 'Western'),
(18, 'Made in Europe');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `Genre`
--
ALTER TABLE `Genre`
  ADD PRIMARY KEY (`id_Genre`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
