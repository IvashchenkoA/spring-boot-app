
INSERT INTO Address (city, street, house_umber)
VALUES
    ('Warsaw', 'Marszalkowska', '1A'),
    ('Warsaw', 'Jana Pawla II', '2B'),
    ('Krakow', 'Florianska', '3C'),
    ('Wroclaw', 'Rynek', '4D'),
    ('Gdansk', 'Dluga', '5E');


INSERT INTO Branch (description, IdAddress)
VALUES
    ('Warsaw Branch 1', 1),
    ('Warsaw Branch 2', 2),
    ('Krakow Branch', 3),
    ('Wroclaw Branch', 4),
    ('Gdansk Branch', 5);