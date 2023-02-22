# Proj_GoodFilms

## Contexte
Ce projet a été ralisé par Vincent Takahashi, Thomas Nicolas, Benjamin Guériot et Arthur Gonay dans le cadre de notre école d'ingénieur lors du module PROJ631. Il consiste en la création d'un site web de gestion des films visionnés inspiré du site goodReads. Ce site permet la création de comptes et la connexion d'utilisateurs. Chaque utilisateur peut rechercher des informations sur les films présents à la base de données, les noter et les commenter.
Nous utilisons pour cela une base de données MySQL que nous avons préalablement remplie à partir d'un scraping Python sur le site https://www.justwatch.com

Une interface Java permet également le traitement des informations liées à la base de données.

# Fonctionnalités

## Partie Web

### Gestion des utilisateurs
  - Inscription sur le site
  - Connexion à sa page
  - Suivre d'autres utilisateurs
  - Accéder aux profils

### Gestion des films
  - Pour chaque films on affiche ses informations
  - Affichage des films les plus récents
  - Recherche par titre des films
  - Recherche par genre des films

### Interactions utilisateurs/films
  - Notation des films 
  - commentaires sur les films
  - Ajout des films aux films regardés

## Partie Java

### Gestion des tables
  - Affichage des données des tables
  - Ajout de tables
  - Suppression de tables

### Gestion des données
  - Ajout de données dans une table
  - Modification des données d'une table
  - Supression des données d'une table


# Utilisation

Avec git, dans le repertoire de votre choix :

```
git clone https://github.com/Brocowlee/Proj_GoodFilms.git
```

puis lancez votre serveur php et ouvrez le dossier dans votre navigateur préféré.

**L'utilisateur admin par défaut a comme login : test et comme mot de passe : test.**
