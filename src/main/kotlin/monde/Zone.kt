package org.ldv.monde

import org.ldv.monstre.EspeceMonstre

/**
 * Représente une zone du monde, comme une route, une caverne ou une mer.
 *
 * Les zones forment une chaîne de routes. Chaque zone peut être reliée
 * à une zone précédente et à une zone suivante.
 * Une zone permet de rechercher des monstres sauvages et de se déplacer.
 *
 * @property id Identifiant unique de la zone.
 * @property nom Nom de la zone.
 * @property expZone Quantité d'expérience associée à la zone.
 * @property especesMonstres Liste mutable des espèces de monstres présentes.
 * @property zoneSuivante Zone suivante dans la chaîne, ou null si elle n'existe pas.
 * @property zonePrecedante Zone précédente dans la chaîne, ou null si elle n'existe pas.
 */
class Zone(
    var id: Int,
    var nom: String,
    var expZone: Int,
    var especesMonstres: MutableList<EspeceMonstre> = mutableListOf(),
    var zoneSuivante: Zone? = null,
    var zonePrecedante: Zone? = null
) {

    // TODO faire la méthode genereMonstre()

    // TODO faire la méthode rencontreMonstre()
}