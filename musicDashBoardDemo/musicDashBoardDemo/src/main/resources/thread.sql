create table thread (
	`seq` bigint auto_increment,
    `name` varchar(50) default null,
    `content` varchar(512) default null,
    `result_seq` bigint default null,
    `parent` bigint default -1,
    `group` integer default 0,
    `createAt` timestamp default current_timestamp,
    primary key(`seq`),
    foreign key (`result_seq`) references result(`seq`)
);