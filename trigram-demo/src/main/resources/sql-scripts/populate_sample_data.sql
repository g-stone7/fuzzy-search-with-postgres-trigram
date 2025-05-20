DO
$$
    BEGIN
        FOR i IN 1..100
            LOOP
                INSERT INTO customer (contract_number,
                                      address_zip_code,
                                      address_city,
                                      address_street,
                                      address_street_number,
                                      contact_first_name,
                                      contact_last_name,
                                      contact_email,
                                      contact_phone)
                VALUES (CONCAT('CN-', LPAD((1000 + (random() * 8999)::int)::text, 4, '0'), '-',
                               LPAD((1000 + (random() * 8999)::int)::text, 4, '0')),
                        LPAD((10000 + (random() * 89999)::int)::text, 5, '0'),
                        CASE
                            WHEN random() < 0.1 THEN 'Barcelona'
                            WHEN random() < 0.2 THEN 'Madrid'
                            WHEN random() < 0.3 THEN 'Valencia'
                            WHEN random() < 0.4 THEN 'Sevilla'
                            WHEN random() < 0.5 THEN 'Cordoba'
                            WHEN random() < 0.6 THEN 'Malaga'
                            WHEN random() < 0.7 THEN 'Murcia'
                            WHEN random() < 0.8 THEN 'Palma de Mallorca'
                            WHEN random() < 0.9 THEN 'Bilbao'
                            ELSE 'Granada'
                            END,
                        CASE
                            WHEN random() < 0.1 THEN 'Calle Mayor'
                            WHEN random() < 0.2 THEN 'Avenida de la Libertad'
                            WHEN random() < 0.3 THEN 'Calle de la Paz'
                            WHEN random() < 0.4 THEN 'Calle del Sol'
                            WHEN random() < 0.5 THEN 'Calle de la Esperanza'
                            WHEN random() < 0.6 THEN 'Avenida de la Constitucion'
                            WHEN random() < 0.7 THEN 'Calle de la Libertad'
                            WHEN random() < 0.8 THEN 'Calle del Rio'
                            WHEN random() < 0.9 THEN 'Avenida del Mar'
                            ELSE 'Calle de la Montana'
                            END,
                        (1 + random() * 999)::int::text,
                        CASE
                            WHEN random() < 0.1 THEN 'Juan'
                            WHEN random() < 0.2 THEN 'Maria'
                            WHEN random() < 0.3 THEN 'Jose'
                            WHEN random() < 0.4 THEN 'Ana'
                            WHEN random() < 0.5 THEN 'Luis'
                            WHEN random() < 0.6 THEN 'Laura'
                            WHEN random() < 0.7 THEN 'Francisco'
                            WHEN random() < 0.8 THEN 'Isabel'
                            WHEN random() < 0.9 THEN 'David'
                            ELSE 'Carmen'
                            END,
                        CASE
                            WHEN random() < 0.1 THEN 'Garcia'
                            WHEN random() < 0.2 THEN 'Martinez'
                            WHEN random() < 0.3 THEN 'Lopez'
                            WHEN random() < 0.4 THEN 'Perez'
                            WHEN random() < 0.5 THEN 'Sanchez'
                            WHEN random() < 0.6 THEN 'Ramirez'
                            WHEN random() < 0.7 THEN 'Torres'
                            WHEN random() < 0.8 THEN 'Hernandez'
                            WHEN random() < 0.9 THEN 'Gonzalez'
                            ELSE 'Fernandez'
                            END,
                        CONCAT('user', i, '@example.es'),
                        CONCAT('+34', LPAD((100000000 + (random() * 99999999)::int)::text, 9, '0')));
            END LOOP;
    END
$$;
