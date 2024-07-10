# partielSC

## 2. Qu’est ce que du code propre ?
Un code propre est :

- un code qui passe tous ses tests (à partir du moment où on a bien travaillé en TDD).
- un code qui exprime l'intention, donc pas de nom de variables en x, i, j.
- un code qui respecte le DRY (Don't Repeat Yourself), c.-à-d. qui ne se répète pas trop.

## 3. De votre expérience de l’agilité en entreprise, en vous basant sur les piliers du manifeste vu en cours. Que pourriez vous améliorer dans la gestion de vos projets ?
Je n'ai pas forcément d'expérience de l'agilité dans mon entreprise car je suis le seul développeur.
Mes logiciels sont effectivement fonctionnels, mais est-ce que je peux dire qu'ils soient bien conçus ? J'essaye de m'améliorer dans ce sens, j'applique les bases du clean code dans mes nouveaux projets car je sais que je vais devoir revenir dessus plus tard. Revenir sur mes premiers projets d'il y a un an est une vraie peine et donc j'essaye de m'éviter ce genre de problèmes dans l'avenir.
Je n'ai malheureusement aucun contact avec le client donc difficile d'appliquer ce pilier.

## 4. code review

Le premier problème que je vois, c'est la fonction processOrder qui fait TOUT, c.-à-d. qu'elle vérifie, enregistre, envoie l'email, met à jour et applique la remise. Ceci rend intestable le code, en tout cas pas de manière précise.
Les dépendances database, email et inventory sont créées directement dans le constructeur. Comment assurer la fiabilité des tests ? Si l'une de ces dépendances vient un jour à changer, qu'est-ce qui garantit que tout ne va pas casser ?
La remise est appliquée en dernier ? Donc lorsque la commande est enregistrée dans la bdd, la remise n'est pas prise en compte ?
Je trouve bizarre de devoir faire le calcul de la remise dans l'appel de la fonction setTotalPrice. Peut-être ajouter une fonction pour la remise, mais ce n'est pas dans le ticket du client donc HS en soi.

Le code alternatif proposé utilise de l'injection de dépendance pour éviter le problème de changement de database ou de système d'inventaire.
Toutes les fonctionnalités du processus des commandes sont dans des fonctions uniques, ce qui permet de faire des tests unitaires et de supprimer les commentaires qui n'ont plus besoin d'expliquer le rôle des fonctions.
J'ai aussi modifié l'ordre de process pour éviter l'enregistrement dans la bdd avant la remise.

