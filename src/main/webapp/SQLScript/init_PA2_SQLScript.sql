use pa2;
# Make sure you are connect to the db and have a schema named "pa2"
create table if not exists phone_information (
	phone_name varchar(100) NOT NULL,
    phone_brand varchar(100) NOT NULL,
    phone_description varchar(300) NOT NULL,
    phone_price float NOT NULL,
    phone_rating float default 0,
    phone_rate_count int unsigned default 0,
    primary key (phone_name)
);

drop table phone_information;