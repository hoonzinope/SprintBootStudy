create table result (
	`seq` bigint auto_increment,
    `name` varchar(100) default null,
    `result_id` varchar(100) default null,
    `type` varchar(50) default null,
    `createAt` timestamp default current_timestamp,
    primary key (`seq`),
    index (`result_id`)
);