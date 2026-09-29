package org.ldv.dresseur

// Importer la classe IndividuMonstre.
import org.ldv.monstre.IndividuMonstre
import org.ldv.item.Item

/**
 * Représente un entraîneur dans le contexte du jeu.
 *
 * Un entraîneur gère une équipe de monstres, une boîte de stockage,
 * un sac d'objets et une somme d'argent.
 *
 * @property id Identifiant unique de l'entraîneur.
 * @property nom Nom de l'entraîneur.
 * @property argents Quantité d'argent possédée.
 */
class Entraineur(
    // Identifiant de l'entraîneur.
    var id: Int,

    // Nom de l'entraîneur.
    var nom: String,

    // Argent de l'entraîneur.
    var argents: Int,

    // Liste des monstres présents dans l'équipe.
    var equipeMonstre: MutableList<IndividuMonstre> = mutableListOf(),

    // Liste des monstres stockés dans la boîte.
    var boiteMonstre: MutableList<IndividuMonstre> = mutableListOf(),

    // Liste des objets présents dans le sac.
    var sacAItems: MutableList<Item> = mutableListOf()
) {
    /**
     * Affiche les détails de l'entraîneur.
     */
    fun afficheDetail() {
        // Afficher le nom de l'entraîneur.
        println("Dresseur : ${this.nom}")

        // Afficher l'argent possédé.
        println("Argents : ${this.argents}")
    }
    fun modifierOrdreEquipe() {
        if (equipeMonstre.size < 2) {
            println("Il faut au moins deux monstres dans l'équipe.")
            return
        }

        println("Équipe actuelle :")
        equipeMonstre.forEachIndexed { index, monstre ->
            println("${index + 1} - ${monstre.nom}")
        }

        print("Entrez la position du premier monstre : ")
        val position1 = readln().toIntOrNull()

        if (position1 == null || position1 !in 1..equipeMonstre.size) {
            println("Position invalide.")
            return
        }

        print("Entrez la nouvelle position du monstre : ")
        val position2 = readln().toIntOrNull()

        if (position2 == null || position2 !in 1..equipeMonstre.size) {
            println("Position invalide.")
            return
        }

        val index1 = position1 - 1
        val index2 = position2 - 1

        val monstreTemporaire = equipeMonstre[index1]
        equipeMonstre[index1] = equipeMonstre[index2]
        equipeMonstre[index2] = monstreTemporaire

        println("Ordre de l'équipe modifié !")

        equipeMonstre.forEachIndexed { index, monstre ->
            println("${index + 1} - ${monstre.nom}")
        }
    }

    fun examinerEquipe() {
        while (true) {
            println("\n=== ÉQUIPE DU JOUEUR ===")

            if (equipeMonstre.isEmpty()) {
                println("Votre équipe est vide.")
                return
            }

            // Affiche les monstres avec leur position.
            equipeMonstre.forEachIndexed { index, monstre ->
                println("${index + 1} - ${monstre.nom}")
            }

            println("\nTapez le numéro d'un monstre pour voir ses détails.")
            println("Tapez 'm' pour modifier l'ordre de l'équipe.")
            println("Tapez 'q' pour revenir au menu principal.")

            print("Votre choix : ")
            val choix = readln().trim()

            when (choix.lowercase()) {
                "q" -> return

                "m" -> modifierOrdreEquipe()

                else -> {
                    val position = choix.toIntOrNull()

                    if (position == null ||
                        position !in 1..equipeMonstre.size
                    ) {
                        println("Choix invalide.")
                    } else {
                        val monstre = equipeMonstre[position - 1]

                        println("\n=== DÉTAILS DU MONSTRE ===")
                        println(monstre)
                    }
                }
            }
        }
    }
}