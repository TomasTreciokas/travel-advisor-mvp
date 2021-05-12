CREATE TABLE item
(
    id   BIGINT IDENTITY (1,1) NOT NULL,
    type VARCHAR(255)          NULL,
    name VARCHAR(255)          NULL,
    CONSTRAINT PK__item_id primary key (id)
);

CREATE TABLE rest_destination
(
    id         BIGINT IDENTITY (1,1) NOT NULL,
    weather    VARCHAR(255)          NULL,
    rest_place VARCHAR(255)          NULL,
    advise_id  BIGINT                NULL,
    CONSTRAINT PK__rest_destination_id primary key (id)
);

CREATE TABLE backpack
(
    id        BIGINT IDENTITY (1,1) NOT NULL,
    advise_id BIGINT                NULL,
    CONSTRAINT PK__backpack_id primary key (id)
);

CREATE TABLE advise
(
    id          BIGINT IDENTITY (1,1) NOT NULL,
    backpack_id BIGINT                NULL,
    CONSTRAINT PK__advise_id primary key (id)
);

CREATE TABLE backpack_item
(
    backpack_id BIGINT NOT NULL,
    item_id     BIGINT NOT NULL
);

ALTER TABLE advise
    ADD CONSTRAINT FK__backpack_id__advise__backpack FOREIGN KEY (backpack_id) REFERENCES backpack (id);

ALTER TABLE backpack
    ADD CONSTRAINT FK__advise_id__backpack__advise FOREIGN KEY (advise_id) REFERENCES advise (id);

ALTER TABLE rest_destination
    ADD CONSTRAINT FK__rest_destination__advise_id__advise FOREIGN KEY (advise_id)
        REFERENCES advise (id);

ALTER TABLE backpack_item
    ADD CONSTRAINT FK__backpack_item__item FOREIGN KEY (item_id) REFERENCES item (id);

ALTER TABLE backpack_item
    ADD CONSTRAINT FK__backpack_item__backpack FOREIGN KEY (backpack_id) REFERENCES backpack (id);