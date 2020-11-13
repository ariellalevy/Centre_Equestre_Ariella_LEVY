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
    * changer dans le fichier src/asset/config.json 51.15.243.169 par localhost
    * `ng serve --ssl=true` ou `npm start`
    * [https://localhost:4200/](https://localhost:4200/)
## Lien du site
si vous souhaitez seulement utiliser le site sans regarder le code voila le lien du site web: [lien](https://centre-equestre-gui.vercel.app/principal)

Il vous faudra aussi ajouter l'extension [CORS](https://chrome.google.com/webstore/detail/allow-cors-access-control/lhobafahddgcelffkeicbaginigeejlf?hl=fr) il faudra l'activé pour que le site fonctionne.

Malheuresement je n'ai pas reussi a gérer l'envoie de mail pour le déploiement donc dans le liens ci dessus il n'y a pas donc pas d'envoie d'email **mais** en local cela fonctionne.

## Démo
voici la démonstration de chacune des actions possibles. Pour touver les videos cliquer sur ce [lien](https://etesiea-my.sharepoint.com/:f:/g/personal/levy_et_esiea_fr/EgzPY4C8QLdAijTiFEwQNrIBt3tNURUvs6yMEzbLZRkaRQ?e=0jjb9w)

* Action commun a chacun des rôles.
    * Inscription
        * Dossier capture video: CentreEquestreInscription.mp4
    * mot de passe oublier
        * Dossier capture video: CentreEquestreDemandeDeMdp.mp4
    * recuperation de mot de passe
        * Dossier capture video: CentreEquestreRecuperationMdp.mp4
    * Information utilisateur (modification d'informations & modification de mot de passe)
        * Dossier capture video: CentreEquestreInformationUser.mp4
* Action commun au role "administateur", "moniteur" et "cavalier"
    * Dossier capture video: CentreEquestreConnexionDeconnexion.mp4
* Rôle "superAdmin": les credentials defini par defaut sont id:SuperAdmin mdp:admin
    * connexion SuperAdmin
        * Dossier capture video: CentreEquestreSuperAdminConnexion.mp4
    * list des utlisateur
        * Dossier capture video: CentreEquestreSuperAdminList.mp4
    * création des administateurs
        * Dossier capture video: CentreEquestreSuperAdminCreation.mp4

nb: pour se connecter au superAdmin il faut cliquer sur l'icone cavalier dans la page de connexion :-)
* Rôle "administateur"
    * Gestion des chevaux:
        * Dossier capture video: CentreEquestreAdministrateurChaval.mp4
    * Gestion des utilisateur:
        * Dossier capture video: CentreEquestreAdministrateurUtilisateur.mp4
* Rôle "moniteur"
    * Gestion des Cours:
        * Dossier capture video: CentreEquestreMoniteurCour.mp4
    * Planning:
        * Dossier capture video: CentreEquestreMoniteurPlanning.mp4
* Role "cavalier"
    * S'inscrire a un cour
        * Dossier capture video: CentreEquestreCavalierInscriptionCour.mp4
    * visualiser son planning de cour
        * Dossier capture video: CentreEquestreCavalierPlanningCours.mp4