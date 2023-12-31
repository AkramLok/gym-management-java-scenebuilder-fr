-- --------------------------------------------------------
-- Hôte:                         127.0.0.1
-- Version du serveur:           10.4.27-MariaDB - mariadb.org binary distribution
-- SE du serveur:                Win64
-- HeidiSQL Version:             12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Listage de la structure de table gym_db. abonnement
CREATE TABLE IF NOT EXISTS `abonnement` (
  `code` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) NOT NULL,
  `durée` int(11) NOT NULL,
  `tarif` decimal(10,2) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table gym_db.abonnement : ~3 rows (environ)
INSERT INTO `abonnement` (`code`, `type`, `durée`, `tarif`) VALUES
	(1, 'Mensuel', 30, 800.00),
	(2, 'Trimestriel', 90, 2300.00),
	(3, 'Annuel', 365, 4000.00);

-- Listage de la structure de table gym_db. administrateur
CREATE TABLE IF NOT EXISTS `administrateur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `adresse` varchar(100) NOT NULL,
  `tél` varchar(20) NOT NULL,
  `login` varchar(50) NOT NULL,
  `passwd` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table gym_db.administrateur : ~1 rows (environ)
INSERT INTO `administrateur` (`id`, `nom`, `prenom`, `adresse`, `tél`, `login`, `passwd`) VALUES
	(2, 'ahmed', 'khalid', 'Dchira', '0656999910', 'admin', 'admin'),
	(5, 'alaoui', 'youssef', 'agadir', '0788234567', 'admin', 'admin123');

-- Listage de la structure de table gym_db. client
CREATE TABLE IF NOT EXISTS `client` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cin` varchar(255) NOT NULL,
  `nom` varchar(50) NOT NULL DEFAULT '',
  `prénom` varchar(50) NOT NULL DEFAULT '',
  `adresse` varchar(100) NOT NULL DEFAULT '',
  `tél` varchar(20) NOT NULL DEFAULT '',
  `Gender` varchar(50) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table gym_db.client : ~14 rows (environ)
INSERT INTO `client` (`id`, `cin`, `nom`, `prénom`, `adresse`, `tél`, `Gender`) VALUES
	(16, 'J754134', 'Achibane', 'Akram', 'Agadir', '0645614591', 'Homme'),
	(20, 'L14242', 'Taha', 'Yaakoub', 'Talborjt', '0315161170', 'Homme'),
	(22, 'J123446', 'Ait Abidalla', 'Ayoub', 'Agadir', '061331164', 'Homme'),
	(23, 'JB1234', 'kamal', 'khalid', 'agadir', '0666345699', 'Homme'),
	(25, 'JB3211', 'alaoui', 'mohamed', 'tilila', '0666413590', 'Homme'),
	(26, 'J754100', 'Achibane', 'ilyass', 'inzegan', '0656991024', 'Homme'),
	(29, 'J123411', 'Ait daoud', 'mehdi', 'Agadir', '0613314410', 'Homme'),
	(32, 'T12345', 'alami', 'kaoutar', 'Tilila', '0612548967', 'Femme'),
	(33, 'T12456', 'kamal', 'lamya', 'agadir', '0666894590', 'Femme'),
	(34, 'T2234', 'ajli', 'oumaima', 'agadir', '0677893421', 'Femme'),
	(36, 'JB5588', 'karimi', 'leila', 'tilila', '0666413999', 'Homme'),
	(37, 'J123', 'test', 'testt', 'agadir', '0650', 'Homme'),
	(38, 'j23334', 'qwer', 'rdt', 'kkl', '9778', 'Homme'),
	(39, 'jb123', 'ab', 'test', 'testtt', '098', 'Homme');

-- Listage de la structure de table gym_db. coach
CREATE TABLE IF NOT EXISTS `coach` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL DEFAULT '',
  `prénom` varchar(50) NOT NULL DEFAULT '',
  `tél` varchar(20) NOT NULL DEFAULT '',
  `adresse` varchar(100) NOT NULL DEFAULT '',
  `salaire` varchar(50) NOT NULL DEFAULT '',
  `planningid` int(11) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table gym_db.coach : ~5 rows (environ)
INSERT INTO `coach` (`id`, `nom`, `prénom`, `tél`, `adresse`, `salaire`, `planningid`) VALUES
	(1, 'alaoui', 'mohamed', 'agadir', '0601234566', '4000.0', 29),
	(2, 'Airaki', 'Amine', 'Agadir', '0600033454', '5000.0', 30),
	(3, 'daouidi', 'ayman', 'agadir', '0623456899', '4000.0', 26),
	(4, 'Yasser', 'Ahmed', 'Agadir', '073210213', '2000.0', 22);

-- Listage de la structure de table gym_db. employé
CREATE TABLE IF NOT EXISTS `employé` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `prénom` varchar(50) NOT NULL,
  `adresse` varchar(100) NOT NULL,
  `tél` varchar(20) NOT NULL,
  `salaire` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table gym_db.employé : ~0 rows (environ)

-- Listage de la structure de table gym_db. login
CREATE TABLE IF NOT EXISTS `login` (
  `id` int(11) NOT NULL,
  `passwd` varchar(50) NOT NULL,
  `loginType` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table gym_db.login : ~0 rows (environ)

-- Listage de la structure de table gym_db. paiement
CREATE TABLE IF NOT EXISTS `paiement` (
  `code` int(11) NOT NULL AUTO_INCREMENT,
  `Id_client` int(11) DEFAULT NULL,
  `Type_Ab` varchar(50) DEFAULT '',
  `Date_ab` date DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table gym_db.paiement : ~23 rows (environ)
INSERT INTO `paiement` (`code`, `Id_client`, `Type_Ab`, `Date_ab`) VALUES
	(4, 16, 'Trimestriel', '2023-06-04'),
	(5, 17, 'Mensuel', '2023-03-05'),
	(8, 20, 'Mensuel', '2023-06-05'),
	(10, 22, 'Mensuel', '2023-06-05'),
	(11, 20, 'Mensuel', '2023-07-06'),
	(12, 17, 'Mensuel', '2023-06-05'),
	(13, 16, 'Mensuel', '2023-09-03'),
	(14, 16, 'Mensuel', '2023-10-04'),
	(15, 16, 'Trimestriel', '2023-11-04'),
	(16, 16, 'Annuel', '2024-02-03'),
	(17, 23, 'Mensuel', '2023-06-05'),
	(18, 24, 'Mensuel', '2023-06-05'),
	(19, 25, 'Mensuel', '2023-06-05'),
	(20, 26, 'Mensuel', '2023-06-05'),
	(21, 27, 'Trimestriel', '2023-06-05'),
	(22, 28, 'Annuel', '2023-06-05'),
	(23, 29, 'Trimestriel', '2023-06-05'),
	(24, 30, 'Trimestriel', '2023-06-05'),
	(25, 31, 'Trimestriel', '2023-06-07'),
	(26, 32, 'Mensuel', '2023-06-07'),
	(27, 33, 'Trimestriel', '2023-06-07'),
	(28, 34, 'Mensuel', '2023-06-07'),
	(30, 16, 'Mensuel', '2025-02-03');

-- Listage de la structure de table gym_db. planning
CREATE TABLE IF NOT EXISTS `planning` (
  `code` int(11) NOT NULL AUTO_INCREMENT,
  `JourPresence` varchar(255) NOT NULL,
  `Matin` varchar(255) NOT NULL,
  `Apresmidi` varchar(255) NOT NULL,
  `Nuit` varchar(255) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table gym_db.planning : ~5 rows (environ)
INSERT INTO `planning` (`code`, `JourPresence`, `Matin`, `Apresmidi`, `Nuit`) VALUES
	(1, '1010110', '1000010', '1010000', '1000110'),
	(22, '0101000', '0000000', '0001000', '0100000'),
	(26, '1101010', '0000010', '1001010', '0101000'),
	(29, '1100000', '1000000', '1100000', '0100000'),
	(30, '1100110', '0100010', '0100000', '1000110');

-- Listage de la structure de table gym_db. propriétaire
CREATE TABLE IF NOT EXISTS `propriétaire` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `adresse` varchar(100) NOT NULL,
  `tél` varchar(20) NOT NULL,
  `login` varchar(50) NOT NULL,
  `passwd` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table gym_db.propriétaire : ~0 rows (environ)
INSERT INTO `propriétaire` (`id`, `nom`, `prenom`, `adresse`, `tél`, `login`, `passwd`) VALUES
	(1, '', '', '', '', 'boss', 'boss');

-- Listage de la structure de table gym_db. salle
CREATE TABLE IF NOT EXISTS `salle` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `type` varchar(50) NOT NULL,
  `tél` varchar(20) NOT NULL,
  `adresse` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table gym_db.salle : ~0 rows (environ)

-- Listage de la structure de table gym_db. équipement
CREATE TABLE IF NOT EXISTS `équipement` (
  `code` int(11) NOT NULL AUTO_INCREMENT,
  `libellé` varchar(50) NOT NULL DEFAULT '',
  `nombre` int(11) DEFAULT NULL,
  `status` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table gym_db.équipement : ~15 rows (environ)
INSERT INTO `équipement` (`code`, `libellé`, `nombre`, `status`) VALUES
	(1, 'haltères ', 25, 'bon-état'),
	(2, 'barre fixe', 4, 'endommagé'),
	(3, 'Velo', 10, 'endommagé'),
	(4, 'tapis roulant', 10, 'endommagé'),
	(5, 'anc d’entraînement', 10, ''),
	(6, 'Barres', 20, ''),
	(7, 'La cage à squat', 2, 'endommagé'),
	(8, 'Bench press', 3, ''),
	(9, 'INCLINE Bench Press', 5, ''),
	(10, 'DECLINE Bench Press', 3, ''),
	(11, 'CURL BENCH', 1, 'endommagé'),
	(12, 'SHOULDER PRESS', 2, ''),
	(13, 'LATERAL RAISES', 3, ''),
	(14, 'CABLE ROW', 1, 'endommagé');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
