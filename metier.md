# Entrepôts pour une chaîne de magasins

Lors des TPs de TIW1-IS 2017-2018, on se placera le plus souvent dans le cadre d'un ensemble d'entrepôts de marchandises pour une chaîne de magasins.

On s'appuiera sur une vision métier simplifiée du fonctionnement de ces entrepôts, décrite dans le ce document.

## Données

On connaît, pour chaque entrepôt, son nom et sa capacité de stockage (en m<sup>3</sup>).

Pour chaque marchandise (possédant une référence, un nom, une description et un volume unitaire), on connait la quantité actuellement stockée dans chaque entrepôt.

On connaît également la liste des opérations (approvisionnement par un fournisseur ou livraison à un magasin) passée et à venir. 
Chacune des ces opérations possède une date de création, une date prévue et, le cas échéant, une date effective.
 
 Un schéma de base de données est proposé pour représenter ces informations dans le fichier [entrepots.sql](metier-spec/src/main/resources/entrepots.sql)
 
## Opérations
 
 Il est possible de créer, mettre à jour et retrouver des entrepôts et des marchandises.
 Il est également possible de créer des livraisons ou des approvisionnements.
 
 Une livraison ne peut être effectuée que s'il y a suffisement de la marchandise à livrer disponible dans l'entrepôt. 
 De même, un entrepôt ne peut être approvisionner que s'il reste de la place dedans.
 Ces vérifications sont effectutées à deux moments: sur le prévisionnel à la création des livraisons/approvisionnements et sur l'historique réelle en ce qui concerne la livraison effective/réception.
 
 Les opérations sont spécifiées dans le package `fr.univlyon1.tiw1.metier.spec` du module `metier-spec`.
 