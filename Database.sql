-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 16, 2022 at 04:11 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sb_project_sem2`
--

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `category_id` bigint(20) NOT NULL,
  `sale_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `created_at`, `image`, `name`, `price`, `updated_at`, `category_id`, `sale_id`) VALUES
(4, '2022-08-29 11:03:28', 'O-Long-xoai-kem-coffee.jpg', 'Ô Long Xoài Kem Cà Phê', 31000, '2022-08-29 11:18:23', 2, 1),
(5, '2022-08-30 23:10:19', 'Tra-dao-buoi-hong-tran-chau.jpg', 'Trà Đào Bưởi Hồng Chân Châu', 31000, '2022-08-29 11:18:23', 2, 1),
(6, '2022-08-30 23:21:42', 'Tra-Sua-Matcha-1-copy.jpg', 'Trà Sữa Matcha', 31000, '2022-08-30 23:21:42', 1, 1),
(8, '2022-08-30 23:33:28', 'Tra-O-Long-Sua-2-copy.jpg', 'Trà Sữa Ô Long', 31000, '2022-08-30 23:33:28', 1, 1),
(9, '2022-08-30 23:40:04', 'choco-cafe.png', 'Choco Ngũ Cốc Kem Cà Phê', 31000, '2022-08-30 23:34:45', 3, 2),
(10, '1900-01-30 23:40:07', 'hồng-trà-ngũ-cốc-kem-cafe.png', 'Hồng Trà Ngũ Cốc Kem Cà Phê', 31000, '2022-08-30 23:34:45', 3, 2),
(11, '1900-01-30 23:40:10', 'sua-chua-dau-tam.png', 'Sữa Chua Dâu Tằm Hoàng Kim', 31000, '2022-08-30 23:38:15', 4, 2),
(12, '1900-01-30 23:40:12', 'sua-chua-dau-tam-hat-de-.png', 'Sữa Chua Dâu Tằm Hạt Dẻ', 31000, '2022-08-30 23:38:15', 4, 2),
(13, NULL, '/admin/images/product/', 'ca phe', 31000, NULL, 5, 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK7l29ekt1x29jup80y2iigimyy` (`category_id`),
  ADD KEY `sale_id` (`sale_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FK7l29ekt1x29jup80y2iigimyy` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  ADD CONSTRAINT `FKtgpfnkn7etmfumakc3iq75e95` FOREIGN KEY (`sale_id`) REFERENCES `sale` (`id`),
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`sale_id`) REFERENCES `sale` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
