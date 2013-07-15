INSERT INTO admin_users(id, email, pass) VALUES (1, 'fowler_1973@bk.ru', '12345');
#
#
INSERT INTO partner_companies(id, name) VALUES (1, 'Арасан');
INSERT INTO partner_companies(id, name) VALUES (2, 'Cafeteria');
INSERT INTO partner_companies(id, name) VALUES (3, 'Шоколадница');
INSERT INTO partner_companies(id, name) VALUES (4, 'Мадлен');
INSERT INTO partner_companies(id, name) VALUES (5, 'Гедонизм');
INSERT INTO partner_companies(id, name) VALUES (6, 'Чимбулак');
#
INSERT INTO partner_users(id, email, pass, company_id) VALUES (1, 'hotadmin@mail.ru', '123', 3);
INSERT INTO partner_users(id, email, pass, company_id) VALUES (2, 'fowler_1973@bk.ru', '12345', 4);
#
# INSERT INTO partner_confirmers(id, name, company_id, code) VALUES (1, 'Confirmer 1', 1, '1001');
# INSERT INTO partner_confirmers(id, name, company_id, code) VALUES (2, 'Confirmer 2', 2, '1002');
INSERT INTO partner_confirmers(id, name, company_id, code) VALUES (3, 'Confirmer 3', 3, '1003');
INSERT INTO partner_confirmers(id, name, company_id, code) VALUES (4, 'Confirmer 4', 4, '1004');
# INSERT INTO partner_confirmers(id, name, company_id, code) VALUES (5, 'Confirmer 5', 5, '1005');
# INSERT INTO partner_confirmers(id, name, company_id, code) VALUES (6, 'Confirmer 6', 6, '1006');
#
#
# #------------------------------------------------------------------------------------------------------------------------
#
INSERT INTO campaigns(id, title, company_id, rate, startFrom, goodThrough, needConfirm, needSign) VALUES (1, 'Скидон на влажный массаж', 3, 20, '2013-06-01', '2013-07-01', false, true);
INSERT INTO campaigns(id, title, company_id, rate, startFrom, goodThrough, needConfirm, needSign) VALUES (2, 'Скидки на кофеюшечку', 4, 10, '2013-04-01', '2013-08-15', false, true);
INSERT INTO campaigns(id, title, company_id, rate, startFrom, goodThrough, needConfirm, needSign) VALUES (3, 'Слопай маффин!', 4, 10, '2013-02-01', '2013-07-25', false, true);
INSERT INTO campaigns(id, title, company_id, rate, startFrom, goodThrough, needConfirm, needSign) VALUES (4, 'Абонимент на кулинарные курсы', 4, 10, '2013-05-01', '2013-05-29', false, true);
INSERT INTO campaigns(id, title, company_id, rate, startFrom, goodThrough, needConfirm, needSign) VALUES (5, 'Недели винных погребов Кызыл-Орды', 5, 15, '2013-03-01', '2013-07-29', false, true);
INSERT INTO campaigns(id, title, company_id, rate, startFrom, goodThrough, needConfirm, needSign) VALUES (6, 'Санные вечеринки в горах!', 6, 25, '2013-02-01', '2013-09-05', true, true);
#
# #------------------------------------------------------------------------------------------------------------------------
#
#
INSERT into auth_tokens(id, token, whenGenerated) values (1, 'test_token1', '2013-05-18 12:34:00');
INSERT into auth_tokens(id, token, whenGenerated) values (2, 'test_token2', '2013-05-18 12:35:00');
INSERT into auth_tokens(id, token, whenGenerated) values (3, 'test_token3', '2013-05-18 12:36:00');

INSERT INTO customer_users(name, pass, authToken_id) VALUES ('user1', '12345', 1);
INSERT INTO customer_users(name, pass, authToken_id) VALUES ('user2', '12345', 2);
INSERT INTO customer_users(name, pass, authToken_id) VALUES ('user3', '12345', 3);
#
# # INSERT INTO discount_stats(id, discount_id, user_id, whenApllied) VALUES (1, 3, 1, '2013-04-05 13:15');
# # INSERT INTO discount_stats(id, discount_id, user_id, whenApllied) VALUES (2, 3, 2, '2013-04-06 20:56');
#
#
#
#
