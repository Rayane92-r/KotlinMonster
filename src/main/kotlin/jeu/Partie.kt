package org.ldv.jeu

import org.ldv.dresseur.Entraineur
import org.ldv.monde.Zone
import org.ldv.monstre.IndividuMonstre

class Partie(
    var id: Int,
    var joueur: Entraineur,
    var zone: Zone
) {
    fun choixStarter() {

        // Crée les trois monstres de départ.
        val espece1 = zone.especesMonstres.find { it.nom == "Springleaf" }
        val espece2 = zone.especesMonstres.find { it.nom == "Flamkip" }
        val espece3 = zone.especesMonstres.find { it.nom == "Aquamy" }

        if (espece1 == null || espece2 == null || espece3 == null) {
            println("Les espèces de départ sont introuvables dans cette zone.")
            return
        }

        val monstre1 = IndividuMonstre(
            id = 1,
            nom = "Springleaf",
            espece = espece1,
            entraineur = joueur,
            expInit = 0.0
        )

        val monstre2 = IndividuMonstre(
            id = 2,
            nom = "Flamkip",
            espece = espece2,
            entraineur = joueur,
            expInit = 0.0
        )

        val monstre3 = IndividuMonstre(
            id = 3,
            nom = "Aquamy",
            espece = espece3,
            entraineur = joueur,
            expInit = 0.0
        )

        // Affiche les trois monstres proposés.
        println("Choisissez votre monstre de depart : ")
        println("1 - ${monstre1.nom}")
        println("2 - ${monstre2.nom}")
        println("3 - ${monstre3.nom}")

        // Lit le choix du joueur.
        print("Votre choix :")
        val choixSelection = readln().toIntOrNull()

        // Sélectionne le monstre correspondant au choix.
        val starter = when (choixSelection) {
            1 -> monstre1
            2 -> monstre2
            3 -> monstre3
            else -> {
                println("Choix invalide.")
                return
            }
        }

        // Demande au joueur de renommer son monstre.
        starter.renommer()

        // Ajoute le monstre à l'équipe du joueur.
        joueur.equipeMonstre.add(starter)

        // Associe le monstre à son entraîneur.
        starter.entraineur = joueur


        println("${starter.nom} rejoint votre equipe !")

    }

}