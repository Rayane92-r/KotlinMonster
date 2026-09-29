
package org.ldv.monde

import org.ldv.joueur
import org.ldv.monstre.CombatMonstre
import org.ldv.monstre.EspeceMonstre
import org.ldv.monstre.IndividuMonstre
import kotlin.random.Random

/**
 * Représente une zone du monde.
 *
 * @property id Identifiant unique de la zone.
 * @property nom Nom de la zone.
 * @property expZone Expérience associée à la zone.
 * @property especesMonstres Espèces présentes dans la zone.
 * @property zoneSuivante Zone suivante.
 * @property zonePrecedente Zone précédente.
 */
class Zone(
    var id: Int,
    var nom: String,
    var expZone: Int,
    var especesMonstres: MutableList<EspeceMonstre> = mutableListOf(),
    var zoneSuivante: Zone? = null,
    var zonePrecedente: Zone? = null
) {

    /**
     * Génère un monstre aléatoire appartenant à la zone.
     */
    fun genereMonstre(): IndividuMonstre {

        // Choisit une espèce au hasard.
        val especeChoisie = especesMonstres.random()

        // Calcule une variation entre -20 % et +20 %.
        val variation = Random.nextDouble(-0.2, 0.2)

        // Calcule l'expérience du monstre.
        val experience = expZone * (1 + variation)

        // Crée et retourne le monstre sauvage.
        return IndividuMonstre(
            id = Random.nextInt(1, 1_000_000),
            nom = especeChoisie.nom,
            espece = especeChoisie,
            entraineur = null,
            expInit = experience
        )
    }

    /**
     * Démarre un combat contre un monstre sauvage.
     */
    fun rencontreMonstre() {

        // Génère un monstre sauvage.
        val monstreSauvage = genereMonstre()

        // Recherche le premier monstre vivant de l'équipe.
        val premierMonstre = joueur.equipeMonstre.firstOrNull {
            it.pv > 0
        }

        // Vérifie que le joueur possède un monstre vivant.
        if (premierMonstre == null) {
            println("Aucun monstre vivant dans l'équipe du joueur.")
            return
        }

        // Crée et lance le combat.
        val combat = CombatMonstre(premierMonstre, monstreSauvage)
        combat.lanceCombat()
    }

    /**
     * Affiche le menu de la zone et permet au joueur d'agir.
     */
    fun jouer() {
        var continuer = true

        while (continuer) {
            println("\n=== $nom ===")
            println("1 - Rencontrer un monstre sauvage")
            println("2 - Examiner l'équipe de monstres")
            println("3 - Aller à la zone suivante")
            println("4 - Aller à la zone précédente")
            println("q - Quitter")

            print("Votre choix : ")
            val choix = readln()

            when (choix) {
                "1" -> {
                    rencontreMonstre()
                }

                "2" -> {
                    joueur.examinerEquipe()
                }

                "3" -> {
                    if (zoneSuivante != null) {
                        val nouvelleZone = zoneSuivante!!
                        println("Vous arrivez dans ${nouvelleZone.nom}.")
                        nouvelleZone.jouer()
                        continuer = false
                    } else {
                        println("Il n'y a pas de zone suivante.")
                    }
                }

                "4" -> {
                    if (zonePrecedente != null) {
                        val nouvelleZone = zonePrecedente!!
                        println("Vous revenez dans ${nouvelleZone.nom}.")
                        nouvelleZone.jouer()
                        continuer = false
                    } else {
                        println("Il n'y a pas de zone précédente.")
                    }
                }

                "q" -> {
                    continuer = false
                    println("Fin de la partie.")
                }

                else -> {
                    println("Choix invalide.")
                }
            }
        }
    }
}