CREATE TABLE `comment` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `entity_id` int(10) DEFAULT NULL,
  `status` int(10) DEFAULT NULL,
  `content` varchar(100) DEFAULT NULL,
  `entity_type` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `login_ticket` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) DEFAULT NULL,
  `ticket` varchar(50) DEFAULT NULL,
  `expired` date DEFAULT NULL,
  `status` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

CREATE TABLE `message` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `from_id` int(10) DEFAULT NULL,
  `to_id` int(10) DEFAULT NULL,
  `content` varchar(50) DEFAULT NULL,
  `has_read` int(10) DEFAULT '0',
  `conversation_id` varchar(10) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

CREATE TABLE `question` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `title` varchar(10) DEFAULT NULL,
  `content` varchar(100) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `user_id` int(10) DEFAULT NULL,
  `comment_count` int(10) DEFAULT NULL,
  `attention_count` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `salt` varchar(10) DEFAULT NULL,
  `head_url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
