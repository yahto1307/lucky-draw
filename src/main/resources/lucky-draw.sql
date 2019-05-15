create table `tb_activity`(
	`id` bigint(20) not null primary key auto_increment,
	`name` varchar(16) not null,
	`create_at` datetime not null,
	`update_at` datetime not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table `tb_draw_result`(
	`id` bigint(20) not null primary key auto_increment,
	`user_id` bigint(20) not null,
	`activity_id` bigint(20) not null,
	`item_id` bigint(20) not null,
	`is_lucky` int(4) not null,
	`create_at` datetime not null,
	`update_at` datetime not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table `tb_item`(
    `id` bigint(20) not null primary key auto_increment,
    `activity_id` bigint(20) not null,
    `name` varchar(16) not null,
    `total_count` int(16) not null,
    `left_count` int(16) not null,
    `probability` bigint(20) not null,
    `create_at` datetime not null,
    `update_at` datetime not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table `tb_kill_item`(
    `id` bigint(20) not null primary key auto_increment,
    `activity_id` bigint(20) not null,
    `name` varchar(16) not null,
    `total_count` int(16) not null,
    `left_count` int(16) not null,
    `create_at` datetime not null,
    `update_at` datetime not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table `tb_kill_result`(
	`id` bigint(20) not null primary key auto_increment,
	`user_id` bigint(20) not null,
	`activity_id` bigint(20) not null,
	`kill_item_id` bigint(20) not null,
	`is_lucky` int(4) not null,
	`create_at` datetime not null,
	`update_at` datetime not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8;