INSERT INTO imperatives.provider (id, name) VALUES (1, 'EE');
INSERT INTO imperatives.provider (id, name) VALUES (2, 'O2');
INSERT INTO imperatives.provider (id, name) VALUES (3, 'Three');
INSERT INTO imperatives.provider (id, name) VALUES (4, 'Vodafone');
INSERT INTO imperatives.provider (id, name) VALUES (5, 'Virgin');

INSERT INTO imperatives.location (id, city, borough) VALUES (1, 'London', 'Brent');
INSERT INTO imperatives.location (id, city, borough) VALUES (2, 'London', 'Westminster');
INSERT INTO imperatives.location (id, city, borough) VALUES (3, 'London', 'Ealing');
INSERT INTO imperatives.location (id, city, borough) VALUES (4, 'London', 'Camden');
INSERT INTO imperatives.location (id, city, borough) VALUES (5, 'London', 'Hackney');

INSERT INTO imperatives.billing (id, location, provider, amount) VALUES (1, 1, 2, 150.00);
INSERT INTO imperatives.billing (id, location, provider, amount) VALUES (2, 3, 1, 250.00);
INSERT INTO imperatives.billing (id, location, provider, amount) VALUES (3, 4, 4, 250.00);
INSERT INTO imperatives.billing (id, location, provider, amount) VALUES (4, 1, 3, 1250.00);
INSERT INTO imperatives.billing (id, location, provider, amount) VALUES (5, 2, 2, 750.00);
INSERT INTO imperatives.billing (id, location, provider, amount) VALUES (6, 4, 4, 890.00);
INSERT INTO imperatives.billing (id, location, provider, amount) VALUES (7, 1, 2, 999.00);