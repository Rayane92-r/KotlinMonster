
package org.ldv.monstre

import org.ldv.dresseur.Entraineur
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.random.Random

/**
 * Représente un individu monstre.
 *
 * Plusieurs individus peuvent appartenir à la même espèce.
 *
 * @property id Identifiant unique de l'individu.
 * @property nom Nom de l'individu.
 * @property espece Espèce du monstre.
 * @property entraineur Entraîneur du monstre ou null.
 * @param expInit Expérience initiale du monstre.
 */
class IndividuMonstre(
    var id: Int,
    var nom: String,

    // Espèce du monstre contenant ses caractéristiques.
    var espece: EspeceMonstre,

    // Entraîneur du monstre, ou null si aucun entraîneur.
    var entraineur: Entraineur?,

    // Expérience initiale du monstre.
    expInit: Double
) {

    // Niveau du monstre.
    var niveau: Int = 1

    // Statistiques de combat générées aléatoirement.
    var attaque: Int = espece.baseAttaque + (-2..2).random()
    var defense: Int = espece.baseDefense + (-2..2).random()
    var vitesse: Int = espece.baseVitesse + (-2..2).random()

    // Statistiques spéciales du monstre.
    var attaquespe: Int =
        espece.baseAttaqueSpe + (-2..2).random()

    var defensespe: Int =
        espece.baseDefenseSpe + (-2..2).random()

    // Points de vie maximum.
    var pvMax: Int = espece.basePv + (-5..5).random()

    // Potentiel aléatoire compris entre 0,5 et 2.
    var potentiel: Double = Random.nextDouble(0.5, 2.0)

    /**
     * Expérience actuelle du monstre.
     *
     * Lorsque l'expérience change, le monstre monte
     * automatiquement de niveau si nécessaire.
     */
    var exp: Double = expInit
        set(value) {

            // Met à jour l'expérience actuelle.
            field = value

            // Vérifie si le monstre est au niveau 1.
            val estNiveau1 = niveau == 1

            // Vérifie si le monstre possède assez d'expérience.
            while (field >= palierExp(niveau + 1)) {

                // Augmente le niveau et les statistiques.
                levelUp()

                // Affiche un message si le monstre
                // n'était pas au niveau 1 au départ.
                if (!estNiveau1) {
                    println(
                        "Le monstre $nom est maintenant niveau $niveau !"
                    )
                }
            }
        }

    // Points de vie actuels du monstre.
    // Ils restent compris entre 0 et pvMax.
    var pv: Int = pvMax
        get() = field
        set(nouveauPv) {
            field = nouveauPv.coerceIn(0, pvMax)
        }

    /**
     * Initialise le niveau en fonction de l'expérience.
     */
    init {
        // Déclenche le setter pour calculer le niveau initial.
        exp = expInit
    }

    /**
     * Calcule l'expérience nécessaire pour atteindre un niveau.
     *
     * @param niveau Niveau cible.
     * @return Expérience cumulée nécessaire.
     */
    fun palierExp(niveau: Int): Double {
        return 100 * (niveau - 1).toDouble().pow(2.0)
    }

    /**
     * Augmente le niveau du monstre et actualise ses statistiques.
     */
    fun levelUp() {

        // Augmente le niveau du monstre.
        niveau++

        // Calcule le gain de points de vie maximum.
        val gainPvMax =
            (espece.modPv * potentiel).roundToInt() +
                    Random.nextInt(-5, 6)

        // Met à jour les points de vie maximum.
        pvMax += gainPvMax

        // Ajoute les points de vie gagnés.
        pv += gainPvMax

        // Augmente l'attaque.
        attaque +=
            (espece.modAttaque * potentiel).roundToInt() +
                    Random.nextInt(-2, 3)

        // Augmente la défense.
        defense +=
            (espece.modDefense * potentiel).roundToInt() +
                    Random.nextInt(-2, 3)

        // Augmente la vitesse.
        vitesse +=
            (espece.modVitesse * potentiel).roundToInt() +
                    Random.nextInt(-2, 3)

        // Augmente l'attaque spéciale.
        attaquespe +=
            (espece.modAttaqueSpe * potentiel).roundToInt() +
                    Random.nextInt(-2, 3)

        // Augmente la défense spéciale.
        defensespe +=
            (espece.modDefenseSpe * potentiel).roundToInt() +
                    Random.nextInt(-2, 3)
    }
    /**
     * Attaque un autre monstre et lui inflige des dégâts.
     *
     * Les dégâts sont calculés avec la formule :
     * dégâts = attaque - (défense / 2), avec un minimum de 1.
     *
     * @param cible Monstre ciblé par l'attaque.
     */
    fun attaquer(cible: IndividuMonstre) {

        // Récupérer les dégâts bruts de l'attaquant
        val degatBrut = this.attaque

        // Calculer les dégâts en prenant en compte la défense de la cible
        var degatTotal = degatBrut - (cible.defense / 2)

        // Garantir un minimum de 1 dégât
        if (degatTotal < 1) {
            degatTotal = 1
        }

        // Enregistrer les points de vie de la cible avant l'attaque
        val pvAvant = cible.pv

        // Retirer les dégâts aux points de vie de la cible
        cible.pv -= degatTotal

        // Enregistrer les points de vie après l'attaque
        val pvApres = cible.pv

        // Afficher le message indiquant les dégâts réellement infligés
        println("$nom inflige ${pvAvant - pvApres} dégâts à ${cible.nom}")
    }
    /**
     * Demande à l'utilisateur de renommer le monstre.
     *
     * Si la saisie est vide, le nom n'est pas modifié.
     */
    fun renommer() {

        // Demander à l'utilisateur s'il souhaite renommer le monstre.
        println("Renommer $nom ?")

        // Lire le nouveau nom saisi par l'utilisateur.
        val nouveauNom = readLine()

        // Vérifier que le nouveau nom n'est pas vide.
        if (!nouveauNom.isNullOrBlank()) {

            // Mettre à jour le nom du monstre.
            this.nom = nouveauNom
        }
    }
    /**
     * Affiche les caractéristiques du monstre à côté de son art ASCII.
     */
    fun afficheDetail() {

        // Récupérer le dessin ASCII de l'espèce du monstre.
        val art = espece.afficheArt()

        // Découper le dessin en plusieurs lignes.
        val artLines = art.lines()

        // Construire la liste des caractéristiques à afficher.
        val details = listOf(
            "Nom : $nom    Niveau : $niveau",
            "Exp : $exp",
            "PV : $pv / $pvMax",
            "Atq : $attaque   Def : $defense   Vitesse : $vitesse",
            "AtqSpe : $attaquespe   DefSpe : $defensespe"
        )

        // Calculer la largeur maximale du dessin ASCII.
        val maxArtWidth = artLines.maxOfOrNull { it.length } ?: 0

        // Déterminer le nombre total de lignes à afficher.
        val maxLines = maxOf(artLines.size, details.size)

        // Parcourir toutes les lignes à afficher.
        for (i in 0 until maxLines) {

            // Récupérer la ligne du dessin si elle existe.
            val artLine = if (i < artLines.size) {
                artLines[i]
            } else {
                ""
            }

            // Récupérer la caractéristique si elle existe.
            val detailLine = if (i < details.size) {
                details[i]
            } else {
                ""
            }

            // Compléter la ligne du dessin pour aligner les caractéristiques.
            val paddedArt = artLine.padEnd(maxArtWidth + 4)

            // Afficher le dessin et la caractéristique sur la même ligne.
            println(paddedArt + detailLine)
        }
    }
}