-- phpMyAdmin SQL Dump
-- version 4.0.10.7
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 10, 2016 at 06:32 PM
-- Server version: 5.5.45-cll-lve
-- PHP Version: 5.4.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `Kashe`
--

-- --------------------------------------------------------

--
-- Table structure for table `Trans`
--

CREATE TABLE IF NOT EXISTS `Trans` (
  `transID` int(11) NOT NULL AUTO_INCREMENT,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `username` varchar(80) COLLATE latin1_danish_ci NOT NULL,
  `phonenumber` varchar(20) COLLATE latin1_danish_ci NOT NULL,
  `points` int(200) NOT NULL,
  PRIMARY KEY (`transID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_danish_ci AUTO_INCREMENT=20 ;

--
-- Dumping data for table `Trans`
--

INSERT INTO `Trans` (`transID`, `date`, `username`, `phonenumber`, `points`) VALUES
(1, '2016-05-03 21:38:42', 'davis1234', '6123456789', 40),
(2, '2016-05-03 21:44:40', 'mpan1234', '2345678901', 128),
(3, '2016-05-03 21:45:06', 'mpan1234', '6547896354', 45),
(4, '2016-05-03 21:45:51', 'mpan1234', '2345678901', 30),
(5, '2016-05-07 02:03:32', 'wave21', '6123456789', 100),
(6, '2016-05-07 14:52:53', 'mpan1234', '6123456789', 100),
(7, '2016-05-07 15:00:14', 'mpan1234', '2345678901', 100),
(8, '2016-05-09 01:35:10', 'mpan1234', '6123456789', 100),
(9, '2016-05-10 00:45:34', 'send', '123456789', 230),
(10, '2016-05-10 00:45:45', 'send', '123456789', 230),
(11, '2016-05-10 15:44:17', '', '8963524578', 400),
(12, '2016-05-10 15:44:37', '', '8963524578', 400),
(13, '2016-05-10 15:52:03', '', '6123456789', 50),
(14, '2016-05-10 15:52:08', '', '6123456789', 50),
(15, '2016-05-10 15:52:39', 'mpan1234', '8963524578', 50),
(16, '2016-05-10 15:53:35', 'mpan1234', '8963524578', 110),
(17, '2016-05-10 15:54:14', '', '6123456789', 1060),
(18, '2016-05-10 16:00:47', 'mpan1234', '8963524578', 400),
(19, '2016-05-11 00:26:52', 'mpan1234', '3392360302', 345);

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE IF NOT EXISTS `User` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(90) COLLATE latin1_danish_ci NOT NULL,
  `email` varchar(200) COLLATE latin1_danish_ci NOT NULL,
  `username` varchar(80) COLLATE latin1_danish_ci NOT NULL,
  `password` varchar(80) COLLATE latin1_danish_ci NOT NULL,
  `phonenumber` varchar(20) COLLATE latin1_danish_ci NOT NULL,
  `bal` int(200) NOT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_danish_ci AUTO_INCREMENT=10 ;

--
-- Dumping data for table `User`
--

-- The tables in the database are loaded with test data.

INSERT INTO `User` (`userID`, `name`, `email`, `username`, `password`, `phonenumber`, `bal`) VALUES
(1, 'Paul Mpanga', 'gonxo@gmail.com', 'mpan1234', 'Rift21', '6123456789', 234),
(2, 'Andrew Davis', 'davis@yahoo.com', 'davis1234', 'davis21', '2345678901', 278),
(3, 'Steve Martin', 'steve@gmail.com', 'martin21', 'martin21', '6547896354', 178),
(5, 'Send Wave', 'wave@gmail.com', 'wave21', 'wave21', '2315869087', 590),
(6, 'Raymond Blanch', 'blancho@gmail.com', 'blancho', 'blancho21', '8972563485', 100),
(7, 'David Solomon', 'david@gmail.com', 'david23', 'david23', '8963524578', 900),
(8, 'Nick Larsen', 'paulsmpanga@gmail.com', 'nlarsen', '1234', '3392360302', 845),
(9, 'Brad Rubin', 'iamawesome@awesomeness.com', 'iamawesome', 'iamthebestguy', '9034532365', 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
