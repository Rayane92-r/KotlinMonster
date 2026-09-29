package org.ldv

import org.ldv.dresseur.Entraineur
import org.ldv.item.Badge
import org.ldv.monde.Zone
import org.ldv.monstre.EspeceMonstre
import org.ldv.monstre.IndividuMonstre
import org.ldv.item.MonsterKube
import org.ldv.jeu.Partie
import org.ldv.monstre.CombatMonstre

var joueur = Entraineur(1, "Sacha", 100)

var rival = Entraineur(2, "Rival", 500)

/*
 * Création de l'espèce Springleaf.
 * Identifiant : 1.
 * Type : Graine.
 * Statistiques de base : attaque 9, défense 11, vitesse 10.
 * Attaque spéciale : 12, défense spéciale : 14, points de vie : 60.
 * Modificateurs : attaque 6.5, défense 9.0, vitesse 8.0.
 * Modificateurs spéciaux : attaque 7.0, défense 10.0, points de vie 34.0.
 * Description : Petit monstre espiègle rond comme une graine, adore le soleil.
 * Particularité : Sa feuille sur la tête indique son humeur.
 * Caractère : Curieux, amical, timide.
 */
var especeSpringleaf = EspeceMonstre(
    1, "Springleaf", "Graine",
    9, 11, 10, 12, 14, 60,
    6.5, 9.0, 8.0, 7.0, 10.0, 34.0,
    "Petit monstre espiègle rond comme une graine, adore le soleil.",
    "Sa feuille sur la tête indique son humeur.",
    "Curieux, amical, timide"
)

/*
 * Création de l'espèce Flamkip.
 * Identifiant : 4.
 * Type : Animal.
 * Statistiques de base : attaque 12, défense 8, vitesse 13.
 * Attaque spéciale : 16, défense spéciale : 7, points de vie : 50.
 * Modificateurs : attaque 10.0, défense 5.5, vitesse 9.5.
 * Modificateurs spéciaux : attaque 9.5, défense 6.5, points de vie 22.0.
 * Description : Petit animal entouré de flammes, déteste le froid.
 * Particularité : Sa flamme change d'intensité selon son énergie.
 * Caractère : Impulsif, joueur, loyal.
 */
var especeFlamkip = EspeceMonstre(
    4, "Flamkip", "Animal",
    12, 8, 13, 16, 7, 50,
    10.0, 5.5, 9.5, 9.5, 6.5, 22.0,
    "Petit animal entouré de flammes, déteste le froid.",
    "Sa flamme change d'intensité selon son énergie.",
    "Impulsif, joueur, loyal"
)

/*
 * Création de l'espèce Aquamy.
 * Identifiant : 7.
 * Type : Météo.
 * Statistiques de base : attaque 10, défense 11, vitesse 9.
 * Attaque spéciale : 14, défense spéciale : 14, points de vie : 55.
 * Modificateurs : attaque 9.0, défense 10.0, vitesse 7.5.
 * Modificateurs spéciaux : attaque 12.0, défense 12.0, points de vie 27.0.
 * Description : Créature vaporeuse semblable à un nuage, produit des gouttes pures.
 * Particularité : Fait baisser la température en s'endormant.
 * Caractère : Calme, rêveur, mystérieux.
 */
var especeAquamy = EspeceMonstre(
    7, "Aquamy", "Meteo",
    10, 11, 9, 14, 14, 55,
    9.0, 10.0, 7.5, 12.0, 12.0, 27.0,
    "Créature vaporeuse semblable à un nuage, produit des gouttes pures.",
    "Fait baisser la température en s'endormant.",
    "Calme, rêveur, mystérieux"
)

/*
 * Création de l'espèce Laoumi.
 * Identifiant : 8.
 * Type : Animal.
 * Statistiques de base : attaque 11, défense 10, vitesse 9.
 * Attaque spéciale : 8, défense spéciale : 11, points de vie : 58.
 * Modificateurs : attaque 11.0, défense 8.0, vitesse 7.0.
 * Modificateurs spéciaux : attaque 6.0, défense 11.5, points de vie 23.0.
 * Description : Petit ours au pelage soyeux, aime se tenir debout.
 * Particularité : Son grognement est mignon mais il protège ses amis.
 * Caractère : Affectueux, protecteur, gourmand.
 */
var especeLaoumi = EspeceMonstre(
    8, "Laoumi", "Animal",
    11, 10, 9, 8, 11, 58,
    11.0, 8.0, 7.0, 6.0, 11.5, 23.0,
    "Petit ours au pelage soyeux, aime se tenir debout.",
    "Son grognement est mignon mais il protège ses amis.",
    "Affectueux, protecteur, gourmand"
)

/*
 * Création de l'espèce Bugsface.
 * Identifiant : 10.
 * Type : Insecte.
 * Statistiques de base : attaque 10, défense 13, vitesse 8.
 * Attaque spéciale : 7, défense spéciale : 13, points de vie : 45.
 * Modificateurs : attaque 7.0, défense 11.0, vitesse 6.5.
 * Modificateurs spéciaux : attaque 8.0, défense 11.5, points de vie 21.0.
 * Description : Insecte à carapace luisante, se déplace par bonds et vibre des antennes.
 * Particularité : Sa carapace devient plus dure après chaque mue.
 * Caractère : Travailleur, sociable, infatigable.
 */
var especeBugsface = EspeceMonstre(
    10, "Bugsface", "Insecte",
    10, 13, 8, 7, 13, 45,
    7.0, 11.0, 6.5, 8.0, 11.5, 21.0,
    "Insecte à carapace luisante, se déplace par bonds et vibre des antennes.",
    "Sa carapace devient plus dure après chaque mue.",
    "Travailleur, sociable, infatigable"
)

/*
 * Création de l'espèce Galum.
 * Identifiant : 13.
 * Type : Minéral.
 * Statistiques de base : attaque 12, défense 15, vitesse 6.
 * Attaque spéciale : 8, défense spéciale : 12, points de vie : 55.
 * Modificateurs : attaque 9.0, défense 13.0, vitesse 4.0.
 * Modificateurs spéciaux : attaque 6.5, défense 10.5, points de vie 13.0.
 * Description : Golem ancien de pierre, yeux lumineux en garde.
 * Particularité : Peut rester immobile des heures comme une statue.
 * Caractère : Sérieux, stoïque, fiable.
 */
var especeGalum = EspeceMonstre(
    13, "Galum", "Minéral",
    12, 15, 6, 8, 12, 55,
    9.0, 13.0, 4.0, 6.5, 10.5, 13.0,
    "Golem ancien de pierre, yeux lumineux en garde.",
    "Peut rester immobile des heures comme une statue.",
    "Sérieux, stoïque, fiable"
)

// Création du Kube.
var kube1 = MonsterKube(
    id = 1,
    nom = "Kube",
    description = "Un petit kube pour capturer un monstre",
    chanceCapture = 50.0
)
// Création de la première zone.
// Création de la première zone.
var route1 = Zone(
    1,
    "Route 1",
    600,
    especesMonstres = mutableListOf(
        especeSpringleaf,
        especeFlamkip,
        especeAquamy,
        especeLaoumi,
        especeBugsface
    )
)

// Création de la deuxième zone.
var route2 = Zone(
    2,
    "Route 2",
    800,
    especesMonstres = mutableListOf(
        especeSpringleaf,
        especeGalum
    )
)

// Création de la troisième zone.
var route3 = Zone(
    3,
    "Route 3",
    30,
    mutableListOf(especeBugsface, especeGalum)
)

/**
 * Crée une nouvelle partie.
 */
fun nouvellePartie(): Partie {

    // Affiche le message d'introduction.
    println("Bienvenue dans le monde des monstres !")

    // Demande le nom du joueur.
    print("Entrez le nom de votre joueur : ")
    val nom = readln()

    // Modifie le nom du joueur.
    joueur.nom = nom

    // Crée et retourne une nouvelle partie.
    return Partie(
        id = 1,
        joueur = joueur,
        zone = route1
    )
}

fun main() {
    // Définir les zones suivantes et précédentes.
    route1.zoneSuivante = route2
    route2.zonePrecedente = route1

    // Ajouter le Kube dans le sac du joueur.
    joueur.sacAItems.add(kube1)

    // Créer une nouvelle partie.
    val partie = nouvellePartie()

    // Choisir le monstre de départ.
    partie.choixStarter()

    // Lancer le jeu.
    partie.zone.jouer()
}
/**
 * Change la couleur du message donné selon le nom de la couleur spécifié.
 * Cette fonction utilise les codes d'échappement ANSI pour appliquer une couleur à la sortie console.
 *
 * @param message Le message auquel la couleur sera appliquée.
 * @param couleur Le nom de la couleur à appliquer.
 * @return Le message coloré sous forme de chaîne.
 */
fun changeCouleur(message: String, couleur: String = ""): String {
    val reset = "\u001B[0m"

    val codeCouleur = when (couleur.lowercase()) {
        "rouge" -> "\u001B[31m"
        "vert" -> "\u001B[32m"
        "jaune" -> "\u001B[33m"
        "bleu" -> "\u001B[34m"
        "magenta" -> "\u001B[35m"
        "cyan" -> "\u001B[36m"
        "blanc" -> "\u001B[37m"
        "marron" -> "\u001B[38;5;130m"
        else -> ""
    }

    return "$codeCouleur$message$reset"
}