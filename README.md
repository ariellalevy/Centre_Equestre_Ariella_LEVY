# Centre_Equestre_Ariella_LEVY
Ce document présente l'application web a réalisée pour le moodule de programmation web
## Consigne
Le but de ce projet est de réaliser une aplication web respectant les users story ce trouvant a l'adresse suivante : https://learning.esiea.fr/mod/book/view.php?id=2743. Le projet doit contenir une base de donnée et les mot de passe ne doivent pas être stocker en claire(utilisation d'une fonction de hashage avec sel). Le framework angulars est conseillé en front et Spring Boost est fortement recommander pour le back.
## Technique et language utiliser
Pour réaliser ce projet j'ai choisis d'utiliser **Angulars avec Typescript** pour le front et **Spring Boot java** pour le back
## Récupération du projet
pour récupéré le code source du projet et le tester en local il faut réaliser les étapes suivantes:
##### Pré-requis
pour récupérer le code source et l'executer en local il faut installer les logiels suivante:
* installer [git](https://git-scm.com/downloads)
1. back
    * installer un éditeur de texte tel que [intellij](https://www.jetbrains.com/fr-fr/idea/download/#section=windows)
    * installer [maven](https://maven.apache.org/download.cgi)
    * installer [JDK 1.8](https://www.oracle.com/java/technologies/javase-downloads.html) ou version superieur
    * installer [mySQL](https://dev.mysql.com/downloads/connector/j/)
    * (facultatif) installer [postman](https://www.postman.com/downloads/)
2. front
    * installer un éditeur de texte tel que [VisualStudioCode](https://code.visualstudio.com/download)
    * installer [nodeJs](https://nodejs.org/fr/download/)
##### récuperation du code source
pour récupérer le code source il faut cloner le projet avec la commande suivante:
`git clone --recurse-submodules https://github.com/ariellalevy/Centre_Equestre_Ariella_LEVY.git`
##### exécution du back
1. Mise en place de la base de donnée
    * Ouvrir MySql et crée la base de donnée avec la commande suivante:
    `CREATE DATABASE springjdbc`
    Nb. si le mot de basse du compte root de mySql n'est pas celui indiquer dans le fichier `application.properties` il faudra penser a le changer dans le fichier
2. Mise en place de maven
    * soit via intellij faite `maven -> lifecycle -> install`
    * soit via la ligne de commande avec la commande suivante `mvn install`
3. Lancement de l'application
    * pour lancer l'application il faut lancer la classe `MainApplication`
##### exécution du front
1. Installation du dossier node module suivre les commandes
    * `cd Centre-Equestre-GUI`
    * `npm install`
    * `ng serve`
    * [http://localhost:4200/](http://localhost:4200/)
## Lien du site
si vous souhaitez seulement utiliser le site sans regarder le code voila le lien du site web: [lien](http://localhost:4200/)

Il vous faudra aussi ajouter l'extension [CORS](https://chrome.google.com/webstore/detail/allow-cors-access-control/lhobafahddgcelffkeicbaginigeejlf?hl=fr) il faudra l'activé pour que le site fonctionne.
## Démo
voici la démonstration de chacune des actions possibles
* Action commun a chacun des rôles.
    * Inscription

    * mot de passe oublier
    * recuperation de mot de passe
![alt text](https://raw.githubusercontent.com/ariellalevy/ariellalevy.github.io/master/Acceuil.png)
* Rôle "superAdmin"
* Rôle "administateur"
* Rôle "moniteur"
* Role "cavalier"