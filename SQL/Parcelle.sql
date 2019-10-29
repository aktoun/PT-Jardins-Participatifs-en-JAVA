-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Oct 09, 2019 at 03:02 PM
-- Server version: 10.1.34-MariaDB
-- PHP Version: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bernardl`
--

-- --------------------------------------------------------

--
-- Table structure for table `Parcelle`
--

CREATE TABLE `Parcelle` (
  `NomJardin` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Id` int(11) NOT NULL,
  `dimx` int(11) NOT NULL,
  `dimy` int(11) NOT NULL,
  `Mere` tinyint(1) NOT NULL,
  `Couper` tinyint(1) NOT NULL,
  `Orientation` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Fille1` int(11) DEFAULT NULL,
  `Fille2` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Parcelle`
--
ALTER TABLE `Parcelle`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `Fille1` (`Fille1`,`Fille2`),
  ADD KEY `ok` (`NomJardin`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Parcelle`
--
ALTER TABLE `Parcelle`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Parcelle`
--
ALTER TABLE `Parcelle`
  ADD CONSTRAINT `ok` FOREIGN KEY (`NomJardin`) REFERENCES `Jardin` (`Nom`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
