# TP3 - Spring

Ce TP **n'est pas à rendre**, mais servira de base pour des TPs à venir.

L'objectif de ce TP est de (re)découvrir le framework Spring, puis d'approfondir sur différents sous-projets.

Il est conseillé de réaliser en priorité les parties Spring Boot et Spring Framework (Spring Core Container, Spring MVC et Spring Test).
Il est ensuite possible de choisir l'ordre dans lequel réaliser les autres parties.


Dans ce TP on reprendra l'application de gestion des entrepôts et on l'instanciera dans le framework [Spring](https://spring.io/projects).
On pourra pour cela soit reprendre le travail réalisé pour le TP1, soit utiliser le code de correction partielle du TP1 fourni dans la branche `correction` du dépôt.

## Spring Boot

Le répertoire `tp3` contient un projet quasi-vide avec seulement un fichier `pom.xml`.
Configurer un projet [Spring Boot](https://projects.spring.io/spring-boot/) vide, c'est-à-dire contenant simplement une classe de démarrage Spring Boot (exemple dans le [quickstart](https://projects.spring.io/spring-boot/#quick-start)).

Remarque: le `pom.xml` de la racine (qui les le parent du `pom.xml` du `tp3`) est préconfigurer pour inclure les dépendances Spring Boot ainsi que la configuration du plugin Spring Boot (comme indiqué dans [la documentation](https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#using-boot-maven-without-a-parent)).
Il est donc inutile de changer le parent du `pom.xml` du `tp3` contrairement à ce qui est suggéré dans le quickstart Spring Boot.

Dans la suite du TP, il sera plus simple d'utiliser des dépendances vers les bons _starters_ de Spring Boot, plutôt que directement vers les bibliothèques Spring.

## Spring Framework

Dans cette section, vous allez utiliser les différents modules de [Spring Framework](https://projects.spring.io/spring-framework/), dont vous trouverez une documentation [ici](https://docs.spring.io/spring/docs/4.3.12.RELEASE/spring-framework-reference/htmlsingle/).

### Spring Core Container

Faites les manipulations suivantes pour que votre code puisse être utilisé par [Spring Core Container](https://docs.spring.io/spring/docs/4.3.12.RELEASE/spring-framework-reference/htmlsingle/#overview-core-container):

* Transformez les différentes classes Operations et DAO en beans Spring pour permettre au conteneur de Spring DI de les gérer dans l’application. Vous pouvez au choix dupliquer le code ou faire des extensions de classes. Dans ce dernier cas, il sera probablement nécessaire d'ajouter des _setters_ au classes d'origine afin de faire fonctionner l'injection.

### Spring MVC

Développer une interface Web pour manipuler les entrepôts selon l'architecture [String MVC](https://docs.spring.io/spring/docs/4.3.12.RELEASE/spring-framework-reference/htmlsingle/#mvc).
Il est possible ici de reprendre une bonne partie du code de `tp1-web`.
On fera en sorte que les marchandises et les entrepôts soient exposées comme des ressources REST (en codant l'application côté client avec des pages statiques et de l'AJAX).

### Spring Test

Utiliser [Spring Test](https://docs.spring.io/spring/docs/4.3.12.RELEASE/spring-framework-reference/htmlsingle/#testing) pour écrire des test:
1. Unitaires pour chacun des composants ; utilisez éventuellement 1 ou plusieurs mocks ad hoc
1. D’intégration (1 minimum)

## Spring AOP

Utilisez [Spring AOP](https://docs.spring.io/spring/docs/4.3.12.RELEASE/spring-framework-reference/htmlsingle/#aop-api) pour créer un nouvel aspect chargé de valider les données du formulaire, par exemple l'existance des marchandises à ajouter dans un entrepôt.

## Spring Data

À l'aide de [Spring Data](https://projects.spring.io/spring-data/), recréer des DAOs s’appuyant sur JPA.
On utilisera en particulier une [approche déclarative](https://docs.spring.io/spring/docs/4.3.12.RELEASE/spring-framework-reference/htmlsingle/#transaction) de la gestion des transactions.

## Spring security

À l’aide de [Spring Security](https://projects.spring.io/spring-security/), utilisez l’authentification via OAuth et donner des droits différents à certains utilisateurs (par exemple créer un nouvel entrepôt demande un droit particulier).

