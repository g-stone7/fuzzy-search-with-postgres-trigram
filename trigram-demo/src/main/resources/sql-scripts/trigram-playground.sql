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
WHERE address_city ILIKE '%arcelo%';

SELECT *
FROM customer
WHERE 'arcelo' <% address_city;

SELECT DISTINCT address_city,
                word_similarity('arcelon', address_city) as score
FROM customer
ORDER BY score DESC;

SELECT show_trgm('Barcelona'), show_trgm('Bárßêlònä');
