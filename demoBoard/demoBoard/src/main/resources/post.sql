CREATE TABLE `post` (
  `seq` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(50) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `content` varchar(100) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `user` varchar(20) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `ip` varchar(50) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `createAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci