INSERT INTO users (id, email, full_name, password, username)
VALUES (1, 'joro@gmail.com', 'Georgi Valkov', 'f73d13d2e96b73b27ec6ad48c95cca99ed3da4af2ca13fd7c89f91f8c4cdcb3c5ea2a277c4b444f2', 'joro'),
       (2, 'pesho@gmail.com', 'Peter Valkov', 'dd5cef29db6d06cf02acafce98101ff7d833f7eae0d942627e1a33b29c029adf4d2ffe6816be945a', 'pesho'),
       (3, 'sasho@gmail.com', 'Sasho Petrov', 'dcd989f448489777dcd63c27fc0eda7ec9bd08a387ac88eeb64d356cee0c61452d2331d11db619a0', 'sasho');

INSERT INTO ships (created, category_id, health, id, power, user_id, name)
VALUES ('2023-10-03', 1, 22, 1, 22, 1, 'joros ship'),
       ('2023-10-02', 3, 44, 2, 44, 2, 'peshos ship'),
       ('2022-09-20', 2, 108, 3, 10, 3, 'sashos first ship'),
       ('2021-08-15', 1, 65, 4, 26, 3, 'sashos second ship');