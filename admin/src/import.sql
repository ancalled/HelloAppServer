INSERT INTO admin_users(id, email, pass) VALUES (1, 'fowler_1973@bk.ru', '12345');


INSERT INTO business_companies(id, name) VALUES (1, 'Арасан');
INSERT INTO business_companies(id, name) VALUES (2, 'Cafeteria');
INSERT INTO business_companies(id, name) VALUES (3, 'Шоколадница');
INSERT INTO business_companies(id, name) VALUES (4, 'Мадлен');
INSERT INTO business_companies(id, name) VALUES (5, 'Гедонизм');
INSERT INTO business_companies(id, name) VALUES (6, 'Чимбулак');

INSERT INTO business_users(id, email, pass, company_id) VALUES (1, 'hotadmin@mail.ru', '123', 3);
INSERT INTO business_users(id, email, pass, company_id) VALUES (2, 'fowler_1973@bk.ru', '12345', 4);


#------------------------------------------------------------------------------------------------------------------------

INSERT INTO campaigns(id, title, company_id, rate, startFrom, goodThrough) VALUES (1, 'Скидон на влажный массаж', 1, 20, '2013-02-01', '2013-04-01');
INSERT INTO campaigns(id, title, company_id, rate, startFrom, goodThrough) VALUES (2, 'Скидки на кофеюшечку', 2, 10, '2013-02-01', '2013-04-15');
INSERT INTO campaigns(id, title, company_id, rate, startFrom, goodThrough) VALUES (3, 'Слопай маффин!', 3, 10, '2013-02-01', '2013-03-25');
INSERT INTO campaigns(id, title, company_id, rate, startFrom, goodThrough) VALUES (4, 'Абонимент на кулинарные курсы', 4, 10, '2013-02-01', '2013-03-25');
INSERT INTO campaigns(id, title, company_id, rate, startFrom, goodThrough) VALUES (5, 'Недели винных погребов Кызыл-Орды', 5, 15, '2013-02-01', '2013-03-29');
INSERT INTO campaigns(id, title, company_id, rate, startFrom, goodThrough) VALUES (6, 'Санные вечеринки в горах!', 6, 25, '2013-02-01', '2013-04-05');

#------------------------------------------------------------------------------------------------------------------------


INSERT INTO users(id, name, pass) VALUES (1, 'user1', '12345');
INSERT INTO users(id, name, pass) VALUES (2, 'user2', '12345');
INSERT INTO users(id, name, pass) VALUES (3, 'user3', '12345');

# INSERT INTO discount_stats(id, discount_id, user_id, whenApllied) VALUES (1, 3, 1, '2013-04-05 13:15');
# INSERT INTO discount_stats(id, discount_id, user_id, whenApllied) VALUES (2, 3, 2, '2013-04-06 20:56');




