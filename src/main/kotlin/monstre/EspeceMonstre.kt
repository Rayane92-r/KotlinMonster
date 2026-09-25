package org.ldv.monstre

import java.io.File
/**
 * Représente une espèce de monstre dans le jeu.
 *
 * Cette classe définit les caractéristiques de base d'une espèce,
 * ses statistiques, ses modificateurs et sa description.
 *
 * @property id Identifiant unique de l'espèce de monstre.
 * @property nom Nom de l'espèce de monstre.
 * @property type Type du monstre, utilisé pour identifier sa catégorie.
 * @property baseAttaque Valeur de base de la statistique d'attaque.
 * @property baseDefense Valeur de base de la statistique de défense.
 * @property baseVitesse Valeur de base de la statistique de vitesse.
 * @property baseAttaqueSpe Valeur de base de l'attaque spéciale.
 * @property baseDefenseSpe Valeur de base de la défense spéciale.
 * @property basePv Valeur de base des points de vie.
 * @property modAttaque Modificateur appliqué à l'attaque.
 * @property modDefense Modificateur appliqué à la défense.
 * @property modVitesse Modificateur appliqué à la vitesse.
 * @property modAttaqueSpe Modificateur appliqué à l'attaque spéciale.
 * @property modDefenseSpe Modificateur appliqué à la défense spéciale.
 * @property modPv Modificateur appliqué aux points de vie.
 * @property description Description générale de l'espèce.
 * @property particularites Particularités propres à l'espèce.
 * @property caractères Caractéristiques complémentaires de l'espèce.
 */
class EspeceMonstre(
    var id: Int,
    var nom: String,
    var type: String,
    val baseAttaque: Int,
    val baseDefense: Int,
    val baseVitesse: Int,
    val baseAttaqueSpe: Int,
    val baseDefenseSpe: Int,
    val basePv: Int,
    val modAttaque: Double,
    val modDefense: Double,
    val modVitesse: Double,
    val modAttaqueSpe: Double,
    val modDefenseSpe: Double,
    val modPv: Double,
    val description: String = "",
    val particularites: String = "",
    val caracteres: String = ""
) {

    /**
     * Affiche la représentation artistique ASCII du monstre.
     *
     * @param deFace Détermine si l'art affiché est de face (true)
     * ou de dos (false).
     * @return Une chaîne de caractères contenant l'art ASCII du monstre.
     */
    fun afficheArt(deFace: Boolean = true): String {
        val nomFichier = if (deFace) "front" else "back"

        val art = File(
            "src/main/resources/art/${this.nom.lowercase()}/$nomFichier.txt"
        ).readText()

        val safeArt = art.replace("/", "∕")

        return safeArt.replace("\\u001B", "\u001B")
    }
}