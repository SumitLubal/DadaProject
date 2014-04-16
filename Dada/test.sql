-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 04, 2014 at 01:31 PM
-- Server version: 5.5.32
-- PHP Version: 5.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `test`
--
CREATE DATABASE IF NOT EXISTS `test` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `test`;

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `test_multi_sets`()
    DETERMINISTIC
begin
        select user() as first_col;
        select user() as first_col, now() as second_col;
        select user() as first_col, now() as second_col, now() as third_col;
        end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `auth`
--

CREATE TABLE IF NOT EXISTS `auth` (
  `user` varchar(20) NOT NULL,
  `pass` varchar(20) NOT NULL,
  `pat1x` int(11) NOT NULL,
  `pat1y` int(11) NOT NULL,
  `pat2x` int(11) NOT NULL,
  `pat2y` int(11) NOT NULL,
  `pat3x` int(11) NOT NULL,
  `pat3y` int(11) NOT NULL,
  `type` varchar(20) NOT NULL,
  `filepath` varchar(200) NOT NULL,
  PRIMARY KEY (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `auth`
--

INSERT INTO `auth` (`user`, `pass`, `pat1x`, `pat1y`, `pat2x`, `pat2y`, `pat3x`, `pat3y`, `type`, `filepath`) VALUES
('a', 'a', 0, 0, 0, 0, 0, 0, 'user', 'F:/Image/HD/3D-skull-horriifying-terrific1printableprintable.jpg'),
('abhi', 'abhi', 0, 0, 0, 0, 0, 0, 'user', 'F:/Image/Google-Wallpaper-Full-HD-Pics.jpg'),
('dada', 'dada', 43, 134, 445, 725, 920, 604, 'admin', 'F:/Image/Memes/526322_347378735394968_1378163215_n.jpg'),
('h', 'h', 427, 350, 812, 297, 648, 325, 'user', 'F:/Image/Memes/425292_531544770237258_1581213122_n.jpg'),
('moresh', 'moresh', 590, 115, 243, 496, 1258, 403, 'user', 'F:/Image/Metal Gear Rising Revengence/metal-gear-rising-revengeance-16912-1920x1080.jpg'),
('sharan', 'sharan', 0, 0, 0, 0, 0, 0, 'user', 'F:/Image/Metal Gear Rising Revengence/metal_gear_rising_revengeance.jpg'),
('test', 'test', 0, 0, 0, 0, 0, 0, 'user', 'E:/a.jpg'),
('vaibhav', 'vaibhav', 0, 0, 0, 0, 0, 0, 'user', 'F:/Image/Metal Gear Rising Revengence/metal-gear-rising-revengeance-16912-1920x1080.jpg');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
