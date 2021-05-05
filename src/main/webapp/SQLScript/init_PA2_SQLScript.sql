using pa2
# Make sure you are connect to the db and have a schema named 'pa2'
create table if not exists phone_information (
	phone_name varchar(100) NOT NULL,
    phone_brand varchar(100) NOT NULL,
    phone_colors varchar(100) NOT NULL,
    phone_price float NOT NULL,
    phone_rating float default 0,
    phone_rate_count int unsigned default 0,
    phone_description varchar(300) NOT NULL,
    primary key (phone_name)
);

insert into phone_information(phone_name, phone_brand, phone_colors, phone_price, phone_description)
values('iPhone 12', 'Apple', 'red, blue, white, black', 999.99, 'Apple Phone'),
       ('iPhone 11', 'Apple', 'red, blue, white, black', 799.99, 'Apple Phone'),
      ('iPhone 10', 'Apple', 'red, blue, white, black', 599.99, 'Apple Phone'),
      ('iPhone 9', 'Apple', 'red, blue, white, black', 399.99, 'Apple Phone'),
      ('iPhone 8', 'Apple', 'red, blue, white, black', 199.99, 'Apple Phone');

insert into phone_information(phone_name, phone_brand, phone_colors, phone_price, phone_description)
values('Galaxy 5G', 'Samsung', 'red, blue, white, black', 999.99, 'Samsung Phone'),
      ('Galaxy Fold', 'Samsung', 'red, blue, white, black', 799.99, 'Samsung Phone'),
      ('Galaxy Note', 'Samsung', 'red, blue, white, black', 599.99, 'Samsung Phone'),
      ('Galaxy S', 'Samsung', 'red, blue, white, black', 399.99, 'Samsung Phone'),
      ('Galaxy Z Flip', 'Samsung', 'red, blue, white, black', 199.99, 'Samsung Phone');

select * from phone_information;

#drop table phone_information;