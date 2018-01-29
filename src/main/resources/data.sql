INSERT INTO
    user
        (id, email, name, password, user_id)
    VALUES
        (null, 'sehwankim@woowahan.com', 'sehwan', '940411', 'skim451'),
        (null, 'foo@bar.com', 'foo', '123', 'foobar');


INSERT INTO
    question
        (id, created_at, author_id, title, content)
    VALUES
        (null, CURRENT_TIMESTAMP, 1, 'Hello, I am pobi.', 'blah blah'),
        (null, CURRENT_TIMESTAMP, 2, 'This is Woowa Brothers.', '123 123')