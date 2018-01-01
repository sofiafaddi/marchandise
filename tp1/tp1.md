# TP1 - Révisions Java, prise en main de l'environnement de développement

Ce TP a pour objectif de réviser la programmation Java, ainsi que de prendre en main l'environnement de la forge logicielle du département, de construction et d'analyse de code.
Ce TP n'est pas à rendre, mais il vous permettra de faire les autres TP dans de meilleures conditions.
Les questions demandant une réalisation technique (développement Java/Web) sont assez indépendantes les unes des autres.
Vous pouvez par exemple réaliser le travail sur l'interface Web sans implémenter tous les DAOs.

Une fois le TP terminé, vous devez:
 * pouvoir naviguer facilement dans le code métier, avoir compris son fonctionnement;
 * être capable de lancer la compilation, les tests automatisés depuis un terminal et depuis votre IDE.
 
De plus, vous devriez avoir amélioré vos compétences afin de:
 * pouvoir lire un audit de code dans Sonarqube, être capable de corriger les problèmes tout en étant critique quand aux remontées _brutes_ de l'outil;
 * être capable d'évaluer la pertinence de vos tests automatisés et être conscient.e des problèmes potentiels qui ne seraient pas détectés
 * savoir utiliser la chaîne d'intégration continue
 

## Découverte du projet "gestion d'entrepôts"

Si ce n'est pas déjà fait:
> * Lire la description du métier qui servira à illustrer les différents TP de cette année: [metier.md](../metier.md).
> * Lire les consignes sur les TPs: [README.md](../README.md).

Le module `metier-spec` contient un ensemble d'interfaces et de classes décrivant le métier de l'application.
`metier-base` fournit une implémentation basique de la spécification du métier: DAO en mémoire et composants métiers pour la gestion des entrepôts et des marchandises.
`metier-test` fournit du code générique permettant de tester les DAOs spécifiés dans `metier-base`.


> Lire chaque classe/interface des modules `metier-*` et faire le lien entre les différentes méthodes et le métier, comprendre le fonctionnement du code 

Utiliser pour cela un IDE avancé, de préférence [IntelliJ IDEA](https://www.jetbrains.com/idea/) ou [Eclipse](http://www.eclipse.org/).

## Environnement Maven

Avant de se lancer dans la partie développement du TP, il est important de comprendre comment il va se construire.
C'est [Maven](https://maven.apache.org/) qui sera majoritairement utilisé dans les TPs.
Chaque module contient un fichier de configuration `pom.xml`.

> Lire les fichier `pom.xml` des modules `metier-*`, de `tp1`, et de la racine du dépôt.

On peut remarquer que les fichiers de `metier-base` et de `tp1` sont presque vides. 
Seules les dépendances sont décrites dans ces fichiers.
L'essentiel de la configuration se trouve dans le fichier à la racine, ce qui permet de partager et de maintenir en cohérence un ensemble de paramètres de configuration (versions de différentes bibliothèques utilisées, configuration des plugins, etc).
Le système d'héritage de Maven va en effet permettre aux sous-modules de récupérer les informations de depuis le fichier situé à la racine.

> Depuis un terminal, lancer la phase d'installation via `mvn install`. 
> Regarder la sortie et essayer de comprendre les différentes actions effectuées par cette commande. 
> Essayer la commande avec les phases suivantes pour comprendre les leurs dépendances:
> * compile
> * test-compile
> * test
> * package
> * install

Si vous disposez d'un serveur [SonarQube](https://www.sonarqube.org/), il est possible de lancer une analyse via `mvn -Dsonar.projectKey=monprojet:master sonar:sonar`.

Suivre ensuite les [instructions](http://maven.apache.org/ide.html) pour lancer la phase d'installation (et l'analyse sonarqube) depuis votre IDE.

## Révisions liaisons classes - données

On souhaite réaliser des DAOs de différents types en plus des DAO _in memory_ fournis dans le module `metier-base`.

### DAO relationnel
> Dans le module `tp1`, utiliser JPA pour créer des DAOs qui s'interfaceront avec une base de données relationnelle:
> * On utilisera le schema relationnel spécifié par le fichier [entrepots.sql](../metier-spec/src/main/resources/entrepots.sql)
> * Tester votre mapping objet-relationnel en écrivant un/des test-s unitaire-s dédié-s avec une base de donnée H2 résidant en mémoire.
> * Tester vos DAOs en étendant les tests abstraits fournis par dans le module `metier-test` et en utilisant une base de donnée H2 résidant en mémoire.
> * Essayer de produire un code qui passe un maximum de règles de qualité SonarQube.

### DAO XML
> Toujours dans le module tp1, créer des DAOs fonctionnant à base de fichiers XML et utilisant JAXB.
> * On respecterera le schema XML [entrepots.xsd](../metier-spec/src/main/resources/entrepots.xsd).
> * Par défaut, les données seront sauvegardées dans un fichier `data/entrepots.xml`.
> * Tester et assurer la qualité du code.

### DAO JSON
> Dans le module `tp1`, créer un dernier ensemble de DAOs fonctionnant sur la base de fichiers JSON
> * Respecter le [schema JSON](http://json-schema.org) fourni dans [entrepots.json](../metier-spec/src/main/resources/entrepots.json).
> * Par défaut, les données seront sauvegardées dans un fichier `data/entrepots.json`.
> * Tester et assurer la qualité du code.


### Remarques / liens utiles:
* [Cours de M1 sur les ORM](http://liris.cnrs.fr/ecoquery/dokuwiki/lib/exe/fetch.php?media=enseignement:bdav:mapping-objets-relationnel-xml.pdf)
* [Tutoriel JPA](https://docs.oracle.com/javaee/6/tutorial/doc/bnbpz.html)
* [H2](http://h2database.com/html/main.html) et [configuration pour une BD en mémoire](http://h2database.com/html/features.html#in_memory_databases)
* Un exemple de fichier [`persistence.xml`](../exemples/persistence.xml) se trouve dans le répertoire `exemples` à la racine.
* Un test JUnit simple peut être trouvé dans le module `metier-spec`, dans la classe `MarchandiseDTOTest`.
* Le module `metier-base` peut servir de source d'inspiration pour le codage des DAOs et les tests unitaires. 
* Quelques annotations qui peuvent être intéressantes:
    * [@ElementCollection](http://docs.oracle.com/javaee/7/api/index.html?javax/persistence/ElementCollection.html), [@JoinTable](http://docs.oracle.com/javaee/7/api/index.html?javax/persistence/JoinTable.html), [@MapKeyJoinColumn](http://docs.oracle.com/javaee/7/api/index.html?javax/persistence/MapKeyJoinColumn.html)
    * [@GeneratedValue](http://docs.oracle.com/javaee/7/api/index.html?javax/persistence/GeneratedValue.html), [@SequenceGenerator](http://docs.oracle.com/javaee/7/api/index.html?javax/persistence/SequenceGenerator.html)

Pour le test des mappings
  * Pour le DAO JPA: faire un test de mise en place d'un gestionnaire d'entités
  * Pour le DAT XML: utiliser l'API de validation Java (`javax.xml.validation.Schema`), par exemple avec le code suivant: 
  
```java
Schema schema = SchemaFactory
                  .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                  .newSchema(new StreamSource(EntrepotDAO.class.getResourceAsStream("/entrepots.xsd")));
Source monDocument = ... ;
schema.newValidator().validate(monDocument);
```

La classe `org.everit.json.schema.Schema` permet de tester de manière similaire le type des documents JSON.

## Application et interface Web

Le module `tp1-web` contient une mini application Web Java.
Elle contient une partie interface utilisateur dont le point d'entrée est le
fichier `index.html`.
Elle contient également une servlet servant à gérer les entrepôts.

L'application peut s'exécuter via maven en lançant depuis le
répertoire `tp1-web` la commande `mvn jetty:run`, puis en se rendant sur [http://localhost:8080/](http://localhost:8080).

### Affichage du contenu d'un entrepôt

> Ajouter une dépendance de `tp1-web` vers `tp1`
> Modifier le code de la méthode `doGet` de la servlet de façon à:
>  1. Utiliser un des DAO (de votre choix) pour charger le bon entrepôt
>  2. Envoyer la liste des marchandises contenues dans l'entrepôt au format JSON

### Gestion plus complète d'un entrepôt

> Compléter les fonctionnalités de l'interface afin de pouvoir:
> * Gérer (ajouter, supprimer, mettre à jour) des marchandises
> * Gérer des livraisons et des approvisionnements

