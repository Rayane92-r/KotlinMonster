package org.ldv.monstre

import org.ldv.item.Utilisable
import org.ldv.joueur

/**
 * Représente un combat entre le monstre du joueur et un monstre sauvage.
 */
class CombatMonstre (
    // Monstre du joueur.
    var monstreJoueur: IndividuMonstre,
    // Monstre sauvage.
    var monstreSauvage: IndividuMonstre


) {
    // Numéro du tour de combat, initialisé à 1.
    var round: Int = 1

    /**
     * Vérifie si le joueur a perdu le combat.
     *
     * @return true si aucun monstre de l'équipe n'a de PV supérieur à 0,
     * sinon false.
     */
    fun gameOver(): Boolean {
        // Vérifier si aucun monstre de l'équipe n'a de PV supérieur à 0.
        return monstreJoueur.entraineur?.equipeMonstre?.none { it.pv > 0 }
            ?: (monstreJoueur.pv <= 0)
    }

    /**
     * Vérifie si le joueur a gagné le combat.
     *
     * @return true si le joueur a gagné, sinon false.
     */
    fun joueurGagne(): Boolean {

        // Vérifier si le monstre sauvage n'a plus de PV.
        if (monstreSauvage.pv <= 0) {

            // Afficher le message de victoire.
            println("${monstreJoueur.nom} a gagné !")

            // Calculer l'expérience gagnée.
            val gainExp = monstreSauvage.exp * 0.20

            // Ajouter l'expérience au monstre du joueur.
            monstreJoueur.exp += gainExp

            // Afficher l'expérience gagnée.
            println("${monstreJoueur.nom} gagne $gainExp exp")

            // Indiquer que le joueur a gagné.
            return true
        }

        // Vérifier si le monstre sauvage a été capturé par le joueur.
        if (monstreSauvage.entraineur == monstreJoueur.entraineur) {

            // Afficher le message de capture.
            println("${monstreSauvage.nom} a été capturé !")

            // Indiquer que le joueur a gagné.
            return true
        }

        // Indiquer que le joueur n'a pas encore gagné.
        return false
    }

    /**
     * Fait attaquer le monstre sauvage.
     */
    fun actionAdversaire() {
        // Vérifier si le monstre sauvage a encore des PV.
        if (monstreSauvage.pv > 0) {

            // Faire attaquer le monstre sauvage contre le monstre du joueur.
            monstreSauvage.attaquer(monstreJoueur)
        }
    }

    // Permet au joueur de choisir une action pendant le combat.
    fun actionJoueur(): Boolean {

        // Vérifier si le combat est terminé.
        if (gameOver()) {
            return false
        }

        // Afficher les actions disponibles.
        println("Menu des actions :")
        println("1 - Attaquer")
        println("2 - Utiliser un objet")
        println("3 - Changer de monstre")

        // Lire le choix du joueur.
        print("Choisissez une action : ")
        val choixAction = readLine()?.toIntOrNull() ?: 0

        when (choixAction) {

            // Le joueur attaque le monstre sauvage.
            1 -> {
                monstreJoueur.attaquer(monstreSauvage)
            }

            // Le joueur utilise un objet.
            2 -> {
                // Afficher le contenu du sac.
                println("Sac à objets :")
                joueur.sacAItems.forEachIndexed { index, item ->
                    println("$index - ${item.nom}")
                }

                // Lire l'objet choisi.
                print("Choisissez un objet : ")
                val indexChoix = readLine()?.toIntOrNull() ?: -1

                // Vérifier que l'indice est valide.
                if (indexChoix in joueur.sacAItems.indices) {
                    val objetChoisi = joueur.sacAItems[indexChoix]

                    // Vérifier si l'objet peut être utilisé.
                    if (objetChoisi is Utilisable) {

                        // Utiliser l'objet sur le monstre sauvage.
                        val captureReussie = objetChoisi.utiliser(monstreSauvage)

                        // Terminer le combat si la capture réussit.
                        if (captureReussie) {
                            return false
                        }
                    } else {
                        println("Objet non utilisable.")
                    }
                } else {
                    println("Choix invalide.")
                }
            }

            // Le joueur change de monstre.
            3 -> {
                // Afficher les monstres de l'équipe.
                println("Équipe de monstres :")
                joueur.equipeMonstre.forEachIndexed { index, monstre ->
                    println("$index - ${monstre.nom} (PV : ${monstre.pv})")
                }

                // Lire le monstre choisi.
                print("Choisissez un monstre : ")
                val indexChoix = readLine()?.toIntOrNull() ?: -1

                // Vérifier que l'indice est valide.
                if (indexChoix in joueur.equipeMonstre.indices) {
                    val choixMonstre = joueur.equipeMonstre[indexChoix]

                    // Vérifier si le monstre peut combattre.
                    if (choixMonstre.pv <= 0) {
                        println("Impossible ! Ce monstre est KO.")
                    } else {
                        // Remplacer le monstre actuel.
                        println("${choixMonstre.nom} remplace ${monstreJoueur.nom}.")
                        monstreJoueur = choixMonstre
                    }
                } else {
                    println("Choix invalide.")
                }
            }

            // Gérer un choix invalide.
            else -> {
                println("Choix invalide.")
            }
        }

        // Indiquer que le combat peut continuer.
        return true
    }

    fun afficheCombat() {
        // Afficher le numéro du round.
        println("======= Début Round : $round =======")

        // Afficher le niveau et les PV du monstre sauvage.
        println("Niveau : ${monstreSauvage.niveau}")
        println("PV : ${monstreSauvage.pv}/${monstreSauvage.pvMax}")

        // Afficher le dessin de face du monstre sauvage.
        println(monstreSauvage.espece.afficheArt(true))

        // Afficher le dessin de dos du monstre du joueur.
        println(monstreJoueur.espece.afficheArt(false))

        // Afficher le niveau et les PV du monstre du joueur.
        println("Niveau : ${monstreJoueur.niveau}")
        println("PV : ${monstreJoueur.pv}/${monstreJoueur.pvMax}")
    }

    // Faire jouer les deux monstres pendant le combat.
    fun jouer() {

        // Déterminer si le monstre du joueur est le plus rapide.
        val joueurPlusRapide = (monstreJoueur.vitesse >= monstreSauvage.vitesse)

        // Afficher les informations du combat.

        afficheCombat()

        // Vérifier si le monstre du joueur attaque en premier.
        if (joueurPlusRapide) {


            val continuer = actionJoueur()
            if (!continuer) return

            // Vérifier si le monstre sauvage est vaincu.
            if (joueurGagne()) return

            actionAdversaire()

            // Vérifier si le joueur a perdu.
            if (gameOver()) return


        } else {

            actionAdversaire()

            // Vérifier si le joueur a perdu.
            if (gameOver()) return


            // Vérifier si le joueur a gagné.
            if (joueurGagne()) return

            // Faire choisir une action au joueur.
            val continuer = actionJoueur()

            // Arrêter le combat si le joueur ne souhaite pas continuer.
            if (!continuer) return

            // Vérifier si le joueur a gagné.
            if (joueurGagne()) return

        }

        // Incrémenter le numéro du tour.
        round++

    }

    fun lanceCombat() {
        while (!gameOver() && !joueurGagne()) {
            this.jouer()
            println("======== Fin du Round : $round ========")
            round++
        }

        if (gameOver()) {
            if (gameOver()) {

                // Restaure les PV de tous les monstres de l'équipe.
                joueur.equipeMonstre.forEach {
                    it.pv = it.pvMax
                }

                // Affiche le message de fin du combat.
                println("Game Over !")

                // Affiche les PV de chaque monstre après la restauration.
                joueur.equipeMonstre.forEach {
                    println("${it.nom} : ${it.pv}/${it.pvMax} PV")
                }
            }
        }
    }
}




