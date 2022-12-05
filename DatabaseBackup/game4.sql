-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 05, 2022 at 10:04 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `game4`
--

-- --------------------------------------------------------

--
-- Table structure for table `armors`
--

CREATE TABLE `armors` (
  `id` int(11) NOT NULL,
  `armorTier` int(11) DEFAULT NULL,
  `armorType` varchar(255) DEFAULT NULL,
  `armorEffect` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `armors`
--

INSERT INTO `armors` (`id`, `armorTier`, `armorType`, `armorEffect`) VALUES
(1, 1, 'worn coths', 10),
(2, 1, 'wooden armor', 15),
(3, 1, 'cactus armor', 20),
(4, 2, 'mage robe', 35),
(5, 2, 'assassin cloak', 40),
(6, 2, 'gladiator armor', 55),
(7, 2, 'iron armor', 60),
(8, 3, 'gold armor', 70),
(9, 3, 'viking armor', 70),
(10, 3, 'armor of achilles', 85);

-- --------------------------------------------------------

--
-- Table structure for table `blessings`
--

CREATE TABLE `blessings` (
  `id` int(11) NOT NULL,
  `blessingType` varchar(255) DEFAULT NULL,
  `blessingEffect` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `blessings`
--

INSERT INTO `blessings` (`id`, `blessingType`, `blessingEffect`) VALUES
(1, 'power', 10),
(2, 'luck', 16),
(3, 'destruction', 210),
(4, 'health', 50),
(5, 'protection', 15);

-- --------------------------------------------------------

--
-- Table structure for table `enemys`
--

CREATE TABLE `enemys` (
  `id` int(11) NOT NULL,
  `enemyTier` int(11) DEFAULT NULL,
  `enemyType` varchar(255) DEFAULT NULL,
  `enemyHealth` int(11) DEFAULT NULL,
  `enemyDamage` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `enemys`
--

INSERT INTO `enemys` (`id`, `enemyTier`, `enemyType`, `enemyHealth`, `enemyDamage`) VALUES
(1, 1, 'slime', 50, 10),
(2, 1, 'goblin scout', 60, 15),
(3, 1, 'wolf', 70, 15),
(4, 1, 'dark elf', 80, 20),
(5, 1, 'skeleton', 90, 20),
(6, 2, 'ghost', 110, 25),
(7, 2, 'giant scorpion', 120, 30),
(8, 2, 'dark knight', 140, 30),
(9, 2, 'wisard', 150, 30),
(10, 2, 'witch doctor', 150, 40),
(11, 2, 'pharaoh', 160, 40),
(12, 2, 'shadow creature', 175, 50),
(13, 3, 'orc', 220, 60),
(14, 3, 'oger', 250, 70),
(15, 3, 'troll', 270, 80),
(16, 3, 'minotaur', 285, 90),
(17, 3, 'golem', 300, 100),
(18, 3, 'cyclops', 315, 100),
(19, 3, 'frost giant', 330, 100);

-- --------------------------------------------------------

--
-- Table structure for table `weapons`
--

CREATE TABLE `weapons` (
  `id` int(11) NOT NULL,
  `weaponTier` int(11) DEFAULT NULL,
  `weaponType` varchar(255) DEFAULT NULL,
  `weaponEffect` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `weapons`
--

INSERT INTO `weapons` (`id`, `weaponTier`, `weaponType`, `weaponEffect`) VALUES
(1, 1, 'stick', 10),
(2, 1, 'boxing gloves', 15),
(3, 1, 'torch', 15),
(4, 1, 'whip', 15),
(5, 1, 'dagger', 20),
(6, 1, 'shield', 20),
(7, 1, 'bat', 25),
(8, 1, 'picaxe', 25),
(9, 2, 'bow', 30),
(10, 2, 'spear', 30),
(11, 2, 'club', 35),
(12, 2, 'crossbow', 40),
(13, 2, 'hammer', 40),
(14, 2, 'axe', 45),
(15, 2, 'sword', 45),
(16, 2, 'ice staff', 50),
(17, 3, 'sickle', 60),
(18, 3, 'fire tome', 65),
(19, 3, 'trident', 70),
(20, 3, 'chainsaw', 85),
(21, 3, 'exalibur', 100);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `armors`
--
ALTER TABLE `armors`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `blessings`
--
ALTER TABLE `blessings`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `enemys`
--
ALTER TABLE `enemys`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `weapons`
--
ALTER TABLE `weapons`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `armors`
--
ALTER TABLE `armors`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `blessings`
--
ALTER TABLE `blessings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `enemys`
--
ALTER TABLE `enemys`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `weapons`
--
ALTER TABLE `weapons`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
