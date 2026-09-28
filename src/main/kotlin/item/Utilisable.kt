package org.ldv.item

// Importer la classe IndividuMonstre.
import org.ldv.monstre.IndividuMonstre

// Importer la classe Entraineur.
import org.ldv.dresseur.Entraineur

/**
 * Interface définissant le comportement d'un objet utilisable.
 */
interface Utilisable {

    /**
     * Applique l'effet de l'objet sur un monstre.
     *
     * @param cible Le monstre sur lequel agir.
     * @param joueur L'entraîneur qui utilise l'objet.
     * @return true si l'action a réussi, false sinon.
     */
    fun utiliser(cible: IndividuMonstre): Boolean
}