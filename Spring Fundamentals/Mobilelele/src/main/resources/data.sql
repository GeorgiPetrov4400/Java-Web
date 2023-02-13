-- some test users
INSERT INTO users (id, username, first_name, last_name, image_url, is_active, password)
VALUES (1, 'Gosho', 'Georgi', 'Petrov', null, 1,
        '334a0a6d359dfab4ec0c06753f9f02031b411f3eb9234f327ee3b7acaa2a74c8df1d43fd58c871de');

INSERT INTO brands (id, name)
VALUES (1, 'Mazda'),
       (2, 'Honda');

INSERT INTO models (id, name, category, start_year, end_year, brand_id, image_url)
VALUES (1, '6', 'CAR', 2009, 2019, 1, 'https://frankfurt.apollo.olxcdn.com/v1/files/avs5fnzv16fs-BG/image;s=933x700'),
       (2, '3', 'CAR', 2011, 2020, 1,
        'https://mobistatic4.focus.bg/mobile/photosmob/173/1/big1/11662020063646173_iv.jpg'),
       (3, 'CRV', 'CAR', 2008, 2021, 2, 'https://www.auto-data.net/images/f121/Honda-CR-V-III.jpg');