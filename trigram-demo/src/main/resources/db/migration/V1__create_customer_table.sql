CREATE TABLE customer
(
    id                    UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    contract_number       TEXT NOT NULL,
    address_zip_code      TEXT NOT NULL,
    address_city          TEXT NOT NULL,
    address_street        TEXT NOT NULL,
    address_street_number TEXT NOT NULL,
    contact_first_name    TEXT NOT NULL,
    contact_last_name     TEXT NOT NULL,
    contact_email         TEXT NOT NULL,
    contact_phone         TEXT NOT NULL
);

