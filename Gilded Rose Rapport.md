# TP Gilded Rose

## Fischbach Jean-Gabriel & Gaudin Guillaume

-------

## Outils

Pour ce TP, on utilise l'IDE Intellij avec le plugin SonarLint qui nous permet une analyse de qualité immédiate et dans l'IDE. Pour les tests unitaires, on utilise le framework junit  (4.1), et GitHub pour le partage du code.

## Tests unitaires

On établit une liste de comportements désirés en fonction du code existant, pour créer les tests unitaires. Pour cela, on se base sur le document Gilded Rose Requirements:

On établit une liste de comportements désirés en fonction du code existant, pour créer les tests unitaires. Pour cela, on se base sur le document Gilded Rose Requirements:

*(légende: les méthodes sont suivies d'une parenthèse vide, les attributs sont en italique, les classes ont une majuscule, les phrases précédées d'un ! contrindiquent la consigne précédente)*

- updateQuality() décrémente d'un point la *quality* puis le *sellIn* de tous les Items
  - ! updateQuality() incrémente d'un point la *quality* des Items "Aged Brie", et de deux points si *sellIn* est négatif ou nul
  - ! updateQuality() ne décrémente ni la *quality* ni le *sellIn* des Items "Sulfuras"
  - ! updateQuality() n'incrémente pas la *quality* d'un Item si cette dernière est d'au moins 50
  - ! updateQuality() incrémente la *quality* des Items "Backstage passes" : d'un point si *sellIn* > 10 , de deux points si 10 $\ge$ *sellIn* > 5 , de trois points si *sellIn* $\le$ 5
    - ! updateQuality() met la *quality* à 0 (ou l'y maintient) si *sellIn* est négatif
  - ! updateQuality() décrémente de deux points la *quality* des Items dont le *name* commence par "Conjured"
- *quality* est un entier inférieur ou égal à 50
  - ! La *quality* des Items "Sulfuras" est de 80
- *sellIn* est un entier positif ou nul
- *name* est une chaîne de caractères alphanumériques


Pour implémenter ces test unitaires, on choisit d'utiliser le framework junit, comme suggéré dans le Kata. On implémente une classe de test pour chaque classe métier, et à l'intérieur de chacune, une méthode de test pour chaque méthode métier, et une dernière méthode de test pour ses attributs.

Pour GildedRose.java, on assure le comportement d'updateQuality() de la manière suivante: pour chaque condition à valider, on crée un objet GildedRose témoin et un objet GildedRose de test, au départ identiques. On manipule l'objet témoin tel qu'on s'attend à ce que updateQuality() le transforme, puis on exécute updateQuality() sur l'objet test, et on s'assure que les deux objets soient identiques.

Pour cela, on utilise la méthode assertEquals() fournie par junit, ce qui nous amène à surcharger equals() dans GildedRose, ce qu'on fait en s'assurant que ce code nouvellement ajouté est de qualité et n'alourdit pas le travail de refactoring.

Notes : 

- Il apparait comme judicieux de séparer les deux tests à effectuer sur les items "Sulfuras": il faut s'assurer que *sellIn* et *quality* ne changent pas. Toutefois, puisqu'on effectue le test de validation sur l'ensemble de l'objet, les deux tests seraient identiques et leur résultat (réussite ou échec) seraient inter-dépendants. Toutefois, si lors de modifications futures du code, il s'avérait que la séparation de ces tests était nécessaire, il serait tout à fait possible d'affiner le assertEquals() en testant sur les attributs de l'Item. On décide pour le moment de maintenir une unique procédure afin d'apporter en clarté par un code uniforme.
- Idéalement, il faudrait assurer dans les constructeurs d'Item que certaines règles sont respectées (par exemple, la qualité ne peut dépasser 50 que si son nom est "Sulfuras"). Toutefois, puisqu'un des prédicats du Kata est que la modification d'Item est interdite, implémenter des tests unitaires pour des options de refactoring qui ne nous sont pas autorisées n'est pas pertinent.