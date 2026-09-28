package org.ldv.item

// Importer la classe IndividuMonstre.
import org.ldv.monstre.IndividuMonstre

// Importer la classe Entraineur.
import org.ldv.dresseur.Entraineur

// Représenter un Kube permettant de capturer un monstre.
class MonsterKube(
    // Identifiant du Kube.
    id: Int,

    // Nom du Kube.
    nom: String,

    // Description du Kube.
    description: String,

    // Pourcentage de chance de capture.
    var chanceCapture: Double,

    // Entraîneur qui utilise le Kube.
    var joueur: Entraineur? = null

) : Item(id, nom, description), Utilisable {

    // Utiliser le Kube sur un monstre.
    override fun utiliser(cible: IndividuMonstre): Boolean {

        // Afficher le lancement du Kube.
        println("Vous lancez le Monster Kube !")

        // Vérifier si le monstre appartient déjà à un entraîneur.
        if (cible.entraineur != null) {
            println("Le monstre ne peut pas être capturé.")
            return false
        }

        // Vérifier qu'un joueur est associé au Kube.
        val entraineur = joueur

        // Arrêter si aucun entraîneur n'est défini.
        if (entraineur == null) {
            println("Aucun entraîneur n'est associé au Kube.")
            return false
        }

        // Calculer le ratio de vie du monstre.
        val ratioVie = cible.pv.toDouble() / cible.pvMax

        // Calculer les chances de capture selon les PV restants.
        val chanceEffective =
            (chanceCapture * (1.5 - ratioVie)).coerceAtLeast(5.0)

        // Effectuer un tirage aléatoire entre 0 et 100.
        val nbAleatoire = kotlin.random.Random.nextDouble(0.0, 100.0)

        // Vérifier si la capture réussit.
        if (nbAleatoire < chanceEffective) {

            // Afficher le message de réussite.
            println("Le monstre est capturé !")

            // Demander un nouveau nom au joueur.
            var nouveauNom: String

            do {
                // Demander le nom du monstre.
                print("Donnez un nouveau nom au monstre : ")

                // Lire le nom saisi.
                nouveauNom = readLine() ?: ""

                // Répéter tant que le nom est vide.
            } while (nouveauNom.isBlank())

            // Modifier le nom du monstre.
            cible.nom = nouveauNom

            // Vérifier si l'équipe contient déjà 6 monstres.
            if (entraineur.equipeMonstre.size >= 6) {

                // Ajouter le monstre dans la boîte.
                entraineur.boiteMonstre.add(cible)

                // Afficher le message de stockage.
                println("Le monstre a été ajouté à la boîte.")

            } else {

                // Ajouter le monstre dans l'équipe.
                entraineur.equipeMonstre.add(cible)

                // Afficher le message d'ajout.
                println("Le monstre a été ajouté à l'équipe.")
            }

            // Associer le monstre à son entraîneur.
            cible.entraineur = entraineur

            // Indiquer que la capture a réussi.
            return true

        } else {

            // Afficher le message d'échec.
            println("Presque ! Le Kube n'a pas pu capturer le monstre.")

            // Indiquer que la capture a échoué.
            return false
        }
    }
}