-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 17 oct. 2023 à 20:11
-- Version du serveur : 10.4.27-MariaDB
-- Version de PHP : 8.0.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `besttrip2_pi`
--

-- --------------------------------------------------------

--
-- Structure de la table `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `description` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `categories`
--

INSERT INTO `categories` (`id`, `nom`, `description`) VALUES
(1, 'Sport', 'Catégorie pour les événements sportifs'),
(2, 'Culture', 'Catégorie pour les événements culturels'),
(3, 'Loisirs', 'Catégorie pour les événements de loisirs'),
(4, 'Aventure', 'Catégorie pour les événements d\'aventure'),
(5, 'Bien-Être', 'Catégorie pour les événements de bien-être'),
(6, 'Autre', 'Autres catégories d\'événements');

-- --------------------------------------------------------

--
-- Structure de la table `evenements`
--

CREATE TABLE `evenements` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `dateDebut` date DEFAULT NULL,
  `description` text DEFAULT NULL,
  `categorie_id` int(11) DEFAULT NULL,
  `lieu` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `tarif` decimal(10,2) DEFAULT NULL,
  `places_disponibles` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `evenements`
--

INSERT INTO `evenements` (`id`, `nom`, `dateDebut`, `description`, `categorie_id`, `lieu`, `image`, `tarif`, `places_disponibles`) VALUES
(1, 'Voyage1', '2022-02-02', 'TUNIS TUNIS', 1, 'Tunis', 'C:\\Users\\ILYESS LASS\\Downloads\\wetransfer_besttrip2_2023-10-15_0140\\BestTrip2\\src\\besttrip2\\360_F_313597831_Bv3LoRBJnZU7ggCQIPUtVDOdju2Ksqfu.jpg', NULL, NULL),
(2, 'Événement 3', '2023-02-04', 'Description de l\'événement 3', 2, 'Lieu 3', 'C:\\Users\\ILYESS LASS\\Downloads\\wetransfer_besttrip2_2023-10-15_0140\\BestTrip2\\src\\besttrip2\\360_F_313597831_Bv3LoRBJnZU7ggCQIPUtVDOdju2Ksqfu.jpg', NULL, NULL),
(3, 'Événement 4', '2023-02-05', 'Description de l\'événement 4', 3, 'Lieu 4', 'C:\\Users\\ILYESS LASS\\Downloads\\wetransfer_besttrip2_2023-10-15_0140\\BestTrip2\\src\\besttrip2\\360_F_313597831_Bv3LoRBJnZU7ggCQIPUtVDOdju2Ksqfu.jpg', NULL, NULL),
(11, 'haapy', '2023-10-13', 'jawjaw', 3, 'Paris', 'C:\\Users\\ILYESS LASS\\Downloads\\wetransfer_besttrip2_2023-10-15_0140\\BestTrip2\\src\\besttrip2\\360_F_313597831_Bv3LoRBJnZU7ggCQIPUtVDOdju2Ksqfu.jpg', '120.00', 15);

-- --------------------------------------------------------

--
-- Structure de la table `participants`
--

CREATE TABLE `participants` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `participants`
--

INSERT INTO `participants` (`id`, `nom`, `prenom`, `email`, `telephone`) VALUES
(1, 'John', 'Doe', 'john.doe@example.com', '555-1234'),
(2, 'Jane', 'Smith', 'jane.smith@example.com', '555-5678'),
(3, 'Alice', 'Johnson', 'alice.johnson@example.com', '555-9999'),
(4, 'Nom_1', 'Prenom_1', 'email1@example.com', '+1-1111111111'),
(5, 'Nom_2', 'Prenom_2', 'email2@example.com', '+1-2222222222'),
(6, 'Nom_3', 'Prenom_3', 'email3@example.com', '+1-3333333333'),
(7, 'Nom_4', 'Prenom_4', 'email4@example.com', '+1-4444444444'),
(8, 'Nom_5', 'Prenom_5', 'email5@example.com', '+1-5555555555');

-- --------------------------------------------------------

--
-- Structure de la table `participations`
--

CREATE TABLE `participations` (
  `id` int(11) NOT NULL,
  `evenement_id` int(11) DEFAULT NULL,
  `participant_id` int(11) DEFAULT NULL,
  `statut` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `reservations`
--

CREATE TABLE `reservations` (
  `id` int(11) NOT NULL,
  `places_reservees` int(11) DEFAULT NULL,
  `evenement_id` int(11) DEFAULT NULL,
  `participant_id` int(11) DEFAULT NULL,
  `dateheure_reservation` datetime DEFAULT NULL,
  `validate` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `reservations`
--

INSERT INTO `reservations` (`id`, `places_reservees`, `evenement_id`, `participant_id`, `dateheure_reservation`, `validate`) VALUES
(2, 3, 1, 1, '2023-10-15 14:00:00', 1),
(3, 2, 2, 2, '2023-10-16 15:30:00', 1),
(4, 5, 3, 3, '2023-10-17 12:45:00', 0),
(5, 3, 1, 1, '2023-10-17 18:50:29', 0),
(6, 3, 11, 1, '2023-10-17 18:54:24', 1),
(7, 2, 11, 1, '2023-10-17 18:56:36', 0);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `evenements`
--
ALTER TABLE `evenements`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_categorie` (`categorie_id`);

--
-- Index pour la table `participants`
--
ALTER TABLE `participants`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `participations`
--
ALTER TABLE `participations`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_evenement` (`evenement_id`),
  ADD KEY `fk_participant` (`participant_id`);

--
-- Index pour la table `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_evenement_reservation` (`evenement_id`),
  ADD KEY `fk_participant_reservation` (`participant_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT pour la table `evenements`
--
ALTER TABLE `evenements`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `participants`
--
ALTER TABLE `participants`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `participations`
--
ALTER TABLE `participations`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `reservations`
--
ALTER TABLE `reservations`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `evenements`
--
ALTER TABLE `evenements`
  ADD CONSTRAINT `fk_categorie` FOREIGN KEY (`categorie_id`) REFERENCES `categories` (`id`) ON DELETE SET NULL;

--
-- Contraintes pour la table `participations`
--
ALTER TABLE `participations`
  ADD CONSTRAINT `fk_evenement` FOREIGN KEY (`evenement_id`) REFERENCES `evenements` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_participant` FOREIGN KEY (`participant_id`) REFERENCES `participants` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `reservations`
--
ALTER TABLE `reservations`
  ADD CONSTRAINT `fk_evenement_reservation` FOREIGN KEY (`evenement_id`) REFERENCES `evenements` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
