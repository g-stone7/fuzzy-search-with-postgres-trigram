SELECT similarity('Hello World', 'Hello World'),
       similarity('Hello', 'Hello World'),
       similarity('Bye World', 'Hello World'),
       similarity('orld', 'Hello World');
SHOW pg_trgm.similarity_threshold;

SELECT word_similarity('Hello World', 'Hello World'),
       word_similarity('Hello', 'Hello World'),
       word_similarity('Bye World', 'Hello World'),
       word_similarity('orld', 'Hello World');
SHOW pg_trgm.word_similarity_threshold;

SELECT strict_word_similarity('Hello World', 'Hello World'),
       strict_word_similarity('Hello', 'Hello World'),
       strict_word_similarity('Bye World', 'Hello World'),
       strict_word_similarity('orld', 'Hello World');
SHOW pg_trgm.strict_word_similarity_threshold;

SELECT COUNT(*)
FROM customer;

SELECT *
FROM customer
WHERE address_street ILIKE '%stitu%';

SELECT *
FROM customer
WHERE 'stitu' <% address_street;

SELECT DISTINCT address_street,
                word_similarity('constitucion', address_street) as score
FROM customer
ORDER BY score DESC;

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
