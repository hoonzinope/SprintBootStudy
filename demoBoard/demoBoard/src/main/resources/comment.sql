CREATE TABLE `comment` (
  `seq` bigint NOT NULL AUTO_INCREMENT,
  `postSeq` bigint DEFAULT NULL,
  `REF_` int DEFAULT NULL COMMENT '댓글 그룹',
  `REF_ORDER` int DEFAULT NULL COMMENT '대댓글 작성시 순서',
  `REF_LEVEL` int DEFAULT NULL COMMENT '대댓글 깊이',
  `user` varchar(20) DEFAULT NULL,
  `ip` varchar(100) DEFAULT NULL,
  `content` varchar(100) DEFAULT NULL,
  `createAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `parent` bigint DEFAULT '-1',
  PRIMARY KEY (`seq`),
  KEY `postSeq` (`postSeq`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`postSeq`) REFERENCES `post` (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci