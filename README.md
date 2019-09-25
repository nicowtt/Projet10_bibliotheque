# Open Classrooms_Projet7 bibliotheque
Voici un système de gestion de bibliothèques d'une ville.

Il se compose de trois modules:

**Le site web**<br/>
Le but est de permettre aux usagers de suivre les prêts de leur ouvrages à travers une 
interface web:
 * Rechercher des ouvrages et voir le nombre d'exemplaire disponible.
 * Consulter leurs prêts en cours. 
 * Le prêt d'un ouvrage est prolongeable une seule fois.
 La prolongation ajoute une nouvelle période de prêt(4 semaines) à la période initiale.
 * Création et retour d'un prêt (fonctions qui seront tranférrées dans un futur logiciel 
 pour le personnels).
 
**Un batch**<br/>
Ce logiciel pour le traitement automatisé permettra d'envoyer des mails de relance
aux usagers n'ayant pas rendu les livres en fin de période de prêt. L'envoi sera automatique
à la fréquence d'un par jour.

**L'API web**<br/>
Le site web ainsi que le batch communiqueront avec ce logiciel en REST afin de connaitre
les informations liées à la Base de donnée.

## Les contraintes fonctionnelles
- Application web avec un framework MVC.
- API web en REST (Les clients (site web, batch) communiqueront avec cette API web) -> factorisation
de la logique métier.
- Packaging avec Maven.

## Les fonctions en plus <br/>
**Le site web (architecture multi-modules)**
- site responsive.
- Recherche par nom de livre et autocompletion(au 1er caractère).
- Recherche multicritères (bibliothèque et ou genre de livre (ex: fantastique)).
- Création de compte utilisateur.
- Possibilité d'avoir plusieurs exemplaires d'un livre dans une bibliothèque.
- Visibilité du nombre d'exemplaire disponible par bibliothèque.

**Le batch (architecture multi-modules)**
- Accessibilité du réglage du serveur d'Email par un fichier de configuration externe.

**POUR INFORMATION:**<br/>
Factorisation du module "libraries-model", au niveau code il est utilisé par le site web et
le batch. (pas de répetition de code)

**L'API web -> microserviceBDD (architecture multi-modules)**<br/>
Niveau sécurité:
* Ce microservice hash (Bcrypt) les mots de passes des utilisateurs avant de stocker en base
de donnée. 
* Il fait la comparaison de mot de passe afin de valider l'authentification.
* Il envoi directement la liste (utilisateur + livre en retard) au batch quand il le demande.

## Déploiement

Ce projet multi-application(maven) a été réalisé en Java.

Utilisation:
 - Spring MVC
 - Spring security 
 - Spring Data JPA
 - Thymeleaf
 - microservice REST

Base de donnée: PostgreSQL 9.6.12

**1/Afin de lancer l'application web client sur un serveur apache:**

- Installez TomCat
- Clonez le repository en local.
- Exécutez la commande maven suivante pour l'application web client:
```
    mvn package
```
- Le fichier applicationWebClient-web-0.0.1-SNAPSHOT.war devrait être crée dans le dossier target du module web.
- Copier / coller ce fichier **applicationWebClient-web-0.0.1-SNAPSHOT.war** dans le dossier webapps de tomcat.
- Créez une base de données "bibliotheque" (pgadmin).
- Restaurez la bdd avec le dump ou lancez le script de création Bdd p7 et celui du jeu de données de demo.

**2/Afin de lancer l'application microserviceBDD sur un serveur apache:**
- Exécutez la commande maven suivante pour l'application microserviceBDD:
```
    mvn package
```
- Le fichier microserviceBdd-web-0.0.1-SNAPSHOT.war devrait être crée dans le dossier target du module web.
- Copier / coller ce fichier **microserviceBdd-web-0.0.1-SNAPSHOT.war** dans le dossier webapps de tomcat.
- Afin d'autoriser la connexion de l'application a la BDD, vous devez declarer une 
Data source nommé "jdbc/bibliotheque" dans tomcat.

Réglez cette data source dans le fichier context.xml (repertoire conf de tomcat) dans la balise ```<Context>```:
```
     <Resource name="jdbc/bibliotheque" auth="Container" type="javax.sql.DataSource"
              username="username"
              password="password"
              driverClassName="org.postgresql.Driver"
              url="jdbc:postgresql://localhost:5432/bibliotheque"
              maxTotal="30"
              maxIdle="10"
              validationQuery="select 1" /> 
             
```
(Pour plus d'information: https://tomcat.apache.org/tomcat-9.0-doc/jndi-resources-howto.html#context.xml_configuration)

- Lancer votre serveur Tomcat et rendez-vous à l'adresse :
```
   http://localhost:8080/home
``` 

**3/Afin de lancer l'application batch:**
- Exécutez la commande maven suivante pour l'application batch:
```
    mvn package
```
- Le fichier batchMail-business-0.0.1-SNAPSHOT.jar devrait être crée dans le dossier target du module business.
- Copier coller ce fichier batchMail-business-0.0.1-SNAPSHOT.jar à l'endroit de votre choix sur votre serveur.
- Veuillez mettre le fichier de configuration "application-gmail.properties" au même endroit.
- Ecrivez le bon mot de passe dans ce fichier de configuration.
- Fabriquer une variable d'environnement système:
* pour windows(exemple avec une variable d'environnement temporaire):<br/>
Dans la console rentrez la commande:
```
set CONF_DIR=C:\Users\nicob\Documents\GitHub\Projet7_bibliotheque\batchMail\batchMail-business\target
```
- Fabriquez une tâche planifié (tous les 24h )qui lance la commande:
```
Java -jar batchMail-business-0.0.1-SNAPSHOT.jar
```

## Contribution

1: clone repository

2: Créer une nouvelle branche

2: Faite vos évolutions / changements (git checkout -b my-new-feature)

3: "Commit" les évolutions / changements (git commit -am "add some feature")

4: "Push" la nouvelle branche (git push origin my-new-feature)

5: Créer une nouvelle "pull request"

## Aperçu du site

![alt text](https://github.com/nicowtt/Projet7_bibliotheque/blob/master/ViewSite.jpg)

## Auteur
Nicow

Voir mes autres projets :
[ICI](https://github.com/nicowtt?tab=repositories)

*twitter: nicow@nicowtt*






