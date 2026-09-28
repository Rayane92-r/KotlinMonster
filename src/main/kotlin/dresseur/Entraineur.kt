package org.ldv.dresseur

// Importer la classe IndividuMonstre.
import org.ldv.monstre.IndividuMonstre

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
    var boiteMonstre: MutableList<IndividuMonstre> = mutableListOf()

    // TODO sacAKube : à compléter avec le type de la classe MonsterKube.
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
}