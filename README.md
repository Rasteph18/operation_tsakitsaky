# Opération TSAKITSAKY pour la collecte de fonds

---

**Ce projet est développé avec Spring Boot MVC en utilisant Postgresql pour les données et Tailwind CSS pour le style.**

---

Une association de jeunes étudiants malgaches mène une opération TSAKITSAKY pour récolter des fonds pour leur activité d’aide aux plus démunis. Ainsi, ils doivent vendre des billets de 20 000 Ar et de 30 000 Ar. Chaque pack est constitué de plusieurs produits (tsatsiou, saucisse fumée, boulette chinoise, etc…).

Ils veulent ainsi constituer un système d’information qui permet de faciliter :
- Le suivi des ventes de billets
- La livraison des produits pour certains clients
- Le suivi des achats effectués
- Le bénéfice par billet

Les produits utilisés pour faire les packs sont : Tsatsiou, Saucisse fumée, boulette chinoise, poulet fumé, cochon laqué, beignet de crevette, emballage. Chacun de ces produits a déjà un coût de revient unitaire qui est entré dans le système. Par exemple : 
- Tsatsiou : 10 000 Ar les 100g
- Beignet de crevette : 300 Ar la pièce
- Emballage : 1 000 Ar la pièce

Pour fabriquer un pack de 20 000 Ar par exemple, on utilise une formule qu’on peut entrer et modifier. Par exemple : 
- 1 pack de 20 000 Ar => 40g de tsatsiou + 5 beignets de crevette + …. + 1 emballage

Le système va permettre, par exemple :
- De suivre le nombre de billets vendus, le client, le lieu de livraison, l’état de paiement du billet. On doit aussi savoir qui a vendu le billet.
- D’entrer les achats de matières premières.
- De saisir les sorties des produits livrés.

Il y aura 2 types de profils utilisateurs :
- Un utilisateur normal, où certaines fonctionnalités ne sont pas accessibles (on insère les utilisateurs directement dans la base pour commencer).
- Un super utilisateur où tout est accessible (on insère directement dans la base les comptes super-utilisateurs).
