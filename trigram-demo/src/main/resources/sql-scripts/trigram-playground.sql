SELECT COUNT(*)
FROM customer;

SELECT *
FROM customer
WHERE address_street ILIKE '%stitu%';

SELECT *
FROM customer
WHERE 'constit' <% address_street;

SELECT DISTINCT address_street,
                word_similarity('constitution', address_street) AS similarity_score
FROM customer
ORDER BY similarity_score DESC;

SELECT show_trgm('constitucion'), show_trgm('Constitución');

-----

CREATE EXTENSION unaccent;

SELECT *
FROM customer
WHERE unaccent('constitucion') <% address_street;

SELECT DISTINCT address_street,
                word_similarity(unaccent('constitucion'), unaccent(address_street)) AS similarity_score
FROM customer
ORDER BY similarity_score DESC;

SELECT unaccent('Avenida de la Constitución');
SELECT show_trgm(unaccent('Avenida de la Constitución'));
