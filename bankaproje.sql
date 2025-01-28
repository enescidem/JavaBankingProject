-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 28 Oca 2025, 14:52:18
-- Sunucu sürümü: 10.4.32-MariaDB
-- PHP Sürümü: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `bankaproje`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `girissayfasi`
--

CREATE TABLE `girissayfasi` (
  `HesapNo` int(4) NOT NULL,
  `TcKimlikNo` bigint(11) NOT NULL,
  `Ad` text NOT NULL,
  `Soyad` text NOT NULL,
  `Telefon` bigint(10) NOT NULL,
  `Sifre` text NOT NULL,
  `Bakiye` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `girissayfasi`
--

INSERT INTO `girissayfasi` (`HesapNo`, `TcKimlikNo`, `Ad`, `Soyad`, `Telefon`, `Sifre`, `Bakiye`) VALUES
(18, 12345678900, 'Enes', 'Çidem', 2223334455, '1234', 100),
(19, 11122233344, 'Osman', 'Gültekin', 2223334466, 'o14', 20);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `hareketdokumu`
--

CREATE TABLE `hareketdokumu` (
  `HareketDokumu_ıd` int(11) NOT NULL,
  `HesapNo` int(11) NOT NULL,
  `Islem` text NOT NULL,
  `Tarih` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `hareketdokumu`
--

INSERT INTO `hareketdokumu` (`HareketDokumu_ıd`, `HesapNo`, `Islem`, `Tarih`) VALUES
(236, 18, 'Bakiye', '2025-01-24 11:41:13'),
(237, 18, 'Bakiye', '2025-01-28 16:39:41'),
(238, 19, 'Para Yatırma', '2025-01-28 16:41:15'),
(239, 19, 'Bakiye', '2025-01-28 16:41:17'),
(240, 19, 'Para Çekme', '2025-01-28 16:41:22'),
(241, 19, 'Havale', '2025-01-28 16:41:53'),
(242, 19, 'Bakiye', '2025-01-28 16:42:02');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `girissayfasi`
--
ALTER TABLE `girissayfasi`
  ADD PRIMARY KEY (`HesapNo`),
  ADD UNIQUE KEY `TcKimlikNo` (`TcKimlikNo`),
  ADD UNIQUE KEY `Telefon` (`Telefon`);

--
-- Tablo için indeksler `hareketdokumu`
--
ALTER TABLE `hareketdokumu`
  ADD PRIMARY KEY (`HareketDokumu_ıd`),
  ADD KEY `HesapNo_Assemble` (`HesapNo`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `girissayfasi`
--
ALTER TABLE `girissayfasi`
  MODIFY `HesapNo` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- Tablo için AUTO_INCREMENT değeri `hareketdokumu`
--
ALTER TABLE `hareketdokumu`
  MODIFY `HareketDokumu_ıd` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=243;

--
-- Dökümü yapılmış tablolar için kısıtlamalar
--

--
-- Tablo kısıtlamaları `hareketdokumu`
--
ALTER TABLE `hareketdokumu`
  ADD CONSTRAINT `HesapNo_Assemble` FOREIGN KEY (`HesapNo`) REFERENCES `girissayfasi` (`HesapNo`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
