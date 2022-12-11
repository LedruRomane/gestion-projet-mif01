# Gestion de Projet et Génie Logiciel, M1, département informatique, Lyon 1

## Rendu de projet

* un répertoire ``` /mes ```

* un fichier maven (``` mes/pom.xml ```) pour le build du projet

* les sources (fichiers java ``` mes/src/ ```)

* le rapport en PDF ``` rapport.pdf ```

## Enseignants et contacts

* [Matthieu Moy](https://matthieu-moy.fr/) (responsable du cours)
* [Lionel Medini](https://perso.liris.cnrs.fr/lionel.medini/)
* Lucien Ndjie (email : prénom.nom@ens-lyon.fr)
<!-- * D: [Paul Iannetta](https://perso.ens-lyon.fr/paul.iannetta/research/) (email : prénom.nom@ens-lyon.fr)
* E: Nicolas Levy (email : prénom.nom@ens-lyon.fr) -->

## Plan du rapport
```
Mon espace santé
├── 1. Présentation globale
│   ├── A. Présentation du sujet
│   └── B. L'existant
│
├── 2. Patterns
│   ├── A. Patrons de conception
│   │   ├── I. Modèle-Vue-Contrôleur
│   │   └── II. Data Access Object
│   │
│   ├── B. Pattern de création
│   │   ├── I. Singleton
│   │   └── II. Fabrique
│   │
│   ├── C. Pattern de comportement
│   │   ├── I. Observateur (Observer)
│   │   ├── II. Fonction de rappel (Callback)
│   │   └── III. Stratégie
│   │
│   └── D. Ajouts Interface
│
├── 3. Ethique
│
└── 4. Tests
    ├── A. Tests des intéractions
    └── B. Tests des évènements
```
## How to run

Comment clone et lancer l'application en local puis l'utiliser: 

1. Git clone ``` git clone git@forge.univ-lyon1.fr:p2105081/mif01-2022.git ```
2. Avoir au préalable installé sur sa machine Openjdk (java) et maven (mvn).
3. Ouvrir le projet avec Visual Studio Code ou Intelliji.
4. Lancer un terminal ``` mvn compile ```
5. Une fois la compilation terminée ``` mvn exec:java ```
6. Pour tester: ``` mvn test ```

## Visuels

![](https://i.imgur.com/1sH7ilg.jpg)
![](https://i.imgur.com/nI0Q7Vk.jpg)