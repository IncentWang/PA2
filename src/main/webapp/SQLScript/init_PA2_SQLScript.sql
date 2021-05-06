# Make sure you are connect to the db and have a schema named 'pa2'
use pa2;
create table if not exists phone_information (
	phone_name varchar(100) NOT NULL,
    phone_brand varchar(100) NOT NULL,
    phone_colors varchar(100) NOT NULL,
    phone_price float NOT NULL,
    phone_rating float default 0,
    phone_rate_count int unsigned default 0,
    phone_description varchar(300) NOT NULL,
    image_path varchar(100) NOT NULL,
    primary key (phone_name)
);

insert into phone_information(phone_name, phone_brand, phone_colors, phone_price, phone_description, image_path)
values('iPhone 12', 'Apple', 'red, blue, white, black', 999.99, 'Apple Phone', 'https://i.loli.net/2021/05/05/3sNOo9KS7QDjIqf.jpg'),
       ('iPhone 11', 'Apple', 'red, blue, white, black', 799.99, 'Apple Phone', 'https://i.loli.net/2021/05/05/95lN2sWwOcyXBhJ.jpg'),
      ('iPhone 10', 'Apple', 'red, blue, white, black', 599.99, 'Apple Phone', 'https://i.loli.net/2021/05/05/jDyZMImOKB21qkn.png'),
      ('iPhone 8', 'Apple', 'red, blue, white, black', 399.99, 'Apple Phone', 'https://i.loli.net/2021/05/05/nNDBJ4QFvabgw19.jpg'),
      ('iPhone 7', 'Apple', 'red, blue, white, black', 199.99, 'Apple Phone', 'https://i.loli.net/2021/05/05/TKWBt7Ceo3v8Xdh.jpg');

insert into phone_information(phone_name, phone_brand, phone_colors, phone_price, phone_description, image_path)
values('Galaxy 5G', 'Samsung', 'red, blue, white, black', 999.99, 'Samsung Phone', 'https://i.loli.net/2021/05/05/gcBpdve3Gtar4s6.jpg'),
      ('Galaxy Fold', 'Samsung', 'red, blue, white, black', 799.99, 'Samsung Phone', 'https://i.loli.net/2021/05/05/QxsO1NhvbkCqXr4.jpg' ),
      ('Galaxy Note', 'Samsung', 'red, blue, white, black', 599.99, 'Samsung Phone', 'https://i.loli.net/2021/05/05/9BhaTnpeg3dYxZt.jpg'),
      ('Galaxy S', 'Samsung', 'red, blue, white, black', 399.99, 'Samsung Phone', 'https://i.loli.net/2021/05/05/OkIiFHzJ6xcWp2f.jpg'),
      ('Galaxy Z Flip', 'Samsung', 'red, blue, white, black', 199.99, 'Samsung Phone', 'https://i.loli.net/2021/05/05/Uev1NklBbV2gd68.jpg');
      
use pa2;
SELECT * FROM pa2.phone_information;

use pa2;
drop table phone_information;