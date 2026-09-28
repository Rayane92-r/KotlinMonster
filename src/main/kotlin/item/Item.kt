package org.ldv.item

/**
 * Représente un objet du jeu.
 *
 * @property id Identifiant unique de l'objet.
 * @property nom Nom de l'objet.
 * @property description Description de l'objet.
 */
/**
 * Représente un objet du jeu.
 */
open class Item(
    // Identifiant de l'objet.
    var id: Int,

    // Nom de l'objet.
    var nom: String,

    // Description de l'objet.
    var description: String
)