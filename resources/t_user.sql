CREATE TABLE `t_user` (
                          `user_id` int(11) NOT NULL AUTO_INCREMENT,
                          `user_name` varchar(30) DEFAULT NULL,
                          `credits` int(11) DEFAULT NULL,
                          `passwd` varchar(32) DEFAULT NULL,
                          `last_visit` datetime DEFAULT NULL,
                          `last_ip` varchar(23) DEFAULT NULL,
                          `age` int(11) NOT NULL,
                          PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;