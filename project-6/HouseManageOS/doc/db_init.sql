create table admin (
`id` varchar(40) not null,
`name` varchar(50) null default null collate 'utf8mb4_general_ci',
`admin` varchar(20),
`password` varchar(100),
primary key (`id`)
)

create table book2 (
`id` INT UNSIGNED AUTO_INCREMENT,
`name` varchar(50) null default null collate 'utf8mb4_general_ci',
`author` varchar(20) collate 'utf8mb4_general_ci',
`price` FLOAT,
`describe` varchar(100) collate 'utf8mb4_general_ci',
primary key (`id`)
)