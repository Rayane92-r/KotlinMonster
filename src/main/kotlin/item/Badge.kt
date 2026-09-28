package org.ldv.item

// Importer la classe Entraineur.
import org.ldv.dresseur.Entraineur

/**
 * Représente un badge obtenu par un entraîneur.
 *
 * @property champion Entraîneur ayant obtenu le badge.
 */
class Badge(
    // Identifiant du badge.
    id: Int,

    // Nom du badge.
    nom: String,

    // Description du badge.
    description: String,

    // Entraîneur ayant obtenu le badge.
    var champion: Entraineur
) : Item(id, nom, description)