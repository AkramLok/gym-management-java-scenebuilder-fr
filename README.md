# gym-management-java-javafx-scenebuilder-fr
## Description:
GymApp est une application de gestion complète pour les salles de sport. Elle offre une plateforme centralisée pour la gestion des employés, des clients, de la planification des cours, ainsi que des équipements et des salles au sein d'une salle de sport. Accès soit en tant qu'administrateur ou propriétaire. Développée en utilisant les technologies Java, JavaFX, SceneBuilder, WAMP, et MySQL, GymApp propose une solution robuste et conviviale pour les responsables de salles de sport.

## Base de Données
La base de données comporte 10 tables principales :
- Abonnement
- Administrateur
- Client
- Coach
- Employé
- Login
- Paiement
- Planning
- Propriétaire
- Salle et Équipement

## Technologies
- Java
- JavaFX
- SceneBuilder
- WAMP
- MySQL

## Fonctionnalités Principales

### Gestion des Employés
Votre application offre une gestion complète des employés, y compris les administrateurs, les coachs, etc.

### Gestion des Clients
Permet l'ajout, la modification et la suppression des clients, avec un suivi des abonnements et des paiements.

### Planification des Cours
Intégration complète de la planification des cours pour une gestion efficace des activités dans la salle de sport.

### Gestion des Équipements et des Salles
Permet la gestion des équipements dans la salle de sport ainsi que la planification des salles.

## Configuration et Déploiement

### Configuration de la Base de Données
Assurez-vous de configurer correctement la base de données lors du déploiement.

### Dépendances
Vérifiez que votre application est distribuable avec toutes les dépendances nécessaires.

## Tests

### Tests Unitaires
Implémentation de tests unitaires pour garantir le bon fonctionnement de chaque composant.

### Tests d'Intégration
Tests d'intégration pour s'assurer que toutes les fonctionnalités fonctionnent correctement ensemble.

## Setup
1. Installer WAMP :
   Téléchargez et installez WAMP 3.3.0 à partir du site officiel. Assurez-vous de choisir la version appropriée pour votre système d'exploitation (WAMP 64 bits ou 32 bits).
   
2. Cloner ou télécharger le dépôt "GymApp" et l'importer en eclipse 2022-09:
   Récupérez les fichiers du projet "GymApp" en clonant le dépôt depuis un système de contrôle de version comme Git ou en téléchargeant le fichier ZIP du projet depuis sa source.
   
3. Installer JavaFX (17.0.7), SceneBuilder (20.0.0) avec java 17 :
   Reinclure les Jars de JavaFX dans le build path du projet et les dépendances nécessaires.

4. Inclure mysql-connector-j-8.0.33 au build path du projet :
    Inclure le jar de JDBC MySql connector dans le build path du projet.

5. Démarrer WAMP ou XAMPP :
   Lancez le panneau de contrôle de WAMP ou XAMPP et démarrez les services Apache et MySQL (double-click wamp et vérifie que les services sont démarrés).

6. Importer la base de données :
   Accédez à phpMyAdmin en entrant "localhost/phpmyadmin/" dans votre navigateur web. Connectez-vous en utilisant le nom d'utilisateur "root" (sans mot de passe). Ensuite, créez une nouvelle base de données nommée "gym_db".
   Une fois la base de données créée, rendez-vous dans l'onglet "Importation" et importez la dernière version du fichier de base de données "gym_db.sql" situé dans le dossier du projet.

Si tout a été configuré correctement, démarrez le projet en eclipse en run main avec configurations, vous devriez maintenant avoir accès au login de "GymApp" et vous pouvez commencer à utiliser l'application ( vous pouvez entrer en tant qu'un administrateur ou propriétaire [super admin]). Si vous rencontrez des problèmes lors du processus de configuration, voir photos.rar et vérifiez à nouveau les étapes.

GymApp a été créée par Outman OURICH, Ayoub AIT ABIDALLA et Akram ACHIBANE.
