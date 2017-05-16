-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 15, 2017 at 08:27 PM
-- Server version: 5.6.24
-- PHP Version: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `db_sak`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `id_admin` int(10) NOT NULL,
  `nama_admin` varchar(30) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `level` varchar(30) NOT NULL
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id_admin`, `nama_admin`, `username`, `password`, `level`) VALUES
(1, 'Muhammad Sarman Noorlah', 'superadmin', 'sa123456', 'Superadmin'),
(2, 'Resty Primadana', 'restyresty', 'resty1234', 'Admin'),
(3, 'Gino Favian', 'gino', '12345', 'Admin'),
(5, 'Muhammad Hardianto', 'adminardi', 'ardi123', 'Admin');

-- --------------------------------------------------------

--
-- Table structure for table `fasilitas`
--

CREATE TABLE IF NOT EXISTS `fasilitas` (
  `id_fasilitas` int(10) NOT NULL,
  `nama_fasilitas` varchar(30) NOT NULL,
  `harga_fasilitas` int(20) NOT NULL
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fasilitas`
--

INSERT INTO `fasilitas` (`id_fasilitas`, `nama_fasilitas`, `harga_fasilitas`) VALUES
(1, 'TV', 30000),
(3, 'AC', 50000),
(4, 'WIFI', 50000),
(5, 'KORAN HARIAN', 30000),
(6, 'SARAPAN PAGI', 40000);

--
-- Triggers `fasilitas`
--
DELIMITER $$
CREATE TRIGGER `tr_add_logIDFasilitas` AFTER INSERT ON `fasilitas`
 FOR EACH ROW begin
insert into logidfasillitas values (new.id_fasilitas);
end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `kamar`
--

CREATE TABLE IF NOT EXISTS `kamar` (
  `id_kamar` int(10) NOT NULL,
  `nama_kamar` varchar(30) NOT NULL,
  `harga_sewa` int(20) NOT NULL,
  `status` enum('Kosong','Terisi') NOT NULL
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kamar`
--

INSERT INTO `kamar` (`id_kamar`, `nama_kamar`, `harga_sewa`, `status`) VALUES
(1, 'Kamar C01', 450000, 'Terisi'),
(3, 'Kamar B01', 750000, 'Terisi'),
(7, 'Kamar B02', 750000, 'Kosong'),
(16, 'Kamar A01', 900000, 'Terisi'),
(18, 'Kamar A02', 900000, 'Kosong'),
(19, 'Kamar A03', 900000, 'Kosong'),
(20, 'Kamar C02', 450000, 'Kosong'),
(21, 'Kamar C03', 450000, 'Kosong'),
(22, 'Kamar B03', 750000, 'Kosong');

--
-- Triggers `kamar`
--
DELIMITER $$
CREATE TRIGGER `tr_add_logIDKamar` AFTER INSERT ON `kamar`
 FOR EACH ROW begin
insert into logidkamar values (new.id_kamar);
end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `kamarfasilitas`
--

CREATE TABLE IF NOT EXISTS `kamarfasilitas` (
  `id_kamarfasilitas` int(10) NOT NULL,
  `id_kamar` int(10) NOT NULL,
  `id_fasilitas` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `logidfasillitas`
--

CREATE TABLE IF NOT EXISTS `logidfasillitas` (
  `id_fasilitas` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `logidfasillitas`
--

INSERT INTO `logidfasillitas` (`id_fasilitas`) VALUES
(17),
(18);

-- --------------------------------------------------------

--
-- Table structure for table `logidkamar`
--

CREATE TABLE IF NOT EXISTS `logidkamar` (
  `id_kamar` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `logidkamar`
--

INSERT INTO `logidkamar` (`id_kamar`) VALUES
(16),
(17),
(18),
(19),
(20),
(21),
(22);

-- --------------------------------------------------------

--
-- Table structure for table `logidtransaksi`
--

CREATE TABLE IF NOT EXISTS `logidtransaksi` (
  `id_transaksi` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `logidtransaksi`
--

INSERT INTO `logidtransaksi` (`id_transaksi`) VALUES
(21),
(22),
(23),
(24);

-- --------------------------------------------------------

--
-- Table structure for table `penyewa`
--

CREATE TABLE IF NOT EXISTS `penyewa` (
  `id_penyewa` int(10) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `alamat` varchar(30) NOT NULL,
  `tempat_lahir` varchar(15) NOT NULL,
  `tgl_lahir` date NOT NULL,
  `jk` varchar(15) NOT NULL,
  `noktp` varchar(20) NOT NULL,
  `email` varchar(40) NOT NULL,
  `nohp` varchar(30) NOT NULL,
  `pekerjaan` varchar(30) NOT NULL,
  `alamat_kerja` varchar(30) NOT NULL,
  `notelp_kerja` varchar(20) NOT NULL,
  `foto_wajah` varchar(250) NOT NULL,
  `foto_ktp` varchar(250) NOT NULL,
  `tgl_terdaftar` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=MyISAM AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `penyewa`
--

INSERT INTO `penyewa` (`id_penyewa`, `nama`, `alamat`, `tempat_lahir`, `tgl_lahir`, `jk`, `noktp`, `email`, `nohp`, `pekerjaan`, `alamat_kerja`, `notelp_kerja`, `foto_wajah`, `foto_ktp`, `tgl_terdaftar`) VALUES
(18, 'Arif Fahrizal', 'Jalan Pramuka', 'Samarinda', '1996-05-22', 'Pria', '6509019040489483', 'ariefcaem@gmail.com', '089964000442', 'mahasiswa', 'jalan barong tongkok', '-', 'pas1.jpg', 'KTP3.jpg', '2017-05-14 06:17:44'),
(19, 'Fernando Eldapati', 'Jalan Damai Sentosa', 'Samarinda', '1999-02-22', 'Pria', '6401000179929001', 'frnndoelll@gmail.com', '089788657213', 'Mahasiswa', 'Jalan Barong Tongkok', '-', 'pas4.jpg', 'KTP4.jpg', '2017-05-14 06:17:44'),
(20, 'Rizki afriani', 'Samarinda', 'Samarinda', '1997-04-16', 'Wanita', '0897768364474', 'ikikeren@gmail.com', '0897768364474', 'Mahasiswa', 'FKTI', '-', 'pas4.jpg', 'KTP3.jpg', '2017-05-14 13:34:13');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE IF NOT EXISTS `transaksi` (
  `id_transaksi` int(10) NOT NULL,
  `id_admin` int(10) NOT NULL,
  `id_penyewa` int(10) NOT NULL,
  `tgl_masuk` date NOT NULL,
  `tgl_keluar` date NOT NULL,
  `id_kamar` int(10) NOT NULL,
  `periode` int(5) NOT NULL,
  `biaya` int(30) NOT NULL
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `id_admin`, `id_penyewa`, `tgl_masuk`, `tgl_keluar`, `id_kamar`, `periode`, `biaya`) VALUES
(18, 2, 19, '2017-05-15', '2017-06-15', 3, 1, 500000),
(22, 1, 18, '2017-05-15', '2017-06-15', 3, 1, 500000),
(24, 1, 18, '2017-05-16', '2017-06-16', 16, 1, 1100000);

--
-- Triggers `transaksi`
--
DELIMITER $$
CREATE TRIGGER `tr_add_logIDTrans` AFTER INSERT ON `transaksi`
 FOR EACH ROW begin
insert into logIDTransaksi values (new.id_transaksi);
end
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_admin`);

--
-- Indexes for table `fasilitas`
--
ALTER TABLE `fasilitas`
  ADD PRIMARY KEY (`id_fasilitas`);

--
-- Indexes for table `kamar`
--
ALTER TABLE `kamar`
  ADD PRIMARY KEY (`id_kamar`);

--
-- Indexes for table `kamarfasilitas`
--
ALTER TABLE `kamarfasilitas`
  ADD PRIMARY KEY (`id_kamarfasilitas`);

--
-- Indexes for table `penyewa`
--
ALTER TABLE `penyewa`
  ADD PRIMARY KEY (`id_penyewa`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id_admin` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `fasilitas`
--
ALTER TABLE `fasilitas`
  MODIFY `id_fasilitas` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT for table `kamar`
--
ALTER TABLE `kamar`
  MODIFY `id_kamar` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=23;
--
-- AUTO_INCREMENT for table `kamarfasilitas`
--
ALTER TABLE `kamarfasilitas`
  MODIFY `id_kamarfasilitas` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `penyewa`
--
ALTER TABLE `penyewa`
  MODIFY `id_penyewa` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=35;
--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id_transaksi` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=25;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
