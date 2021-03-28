CREATE TABLE `tokens` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `token` varchar(255) NOT NULL,
    `del_flg` tinyint(1) DEFAULT 0,
     PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;