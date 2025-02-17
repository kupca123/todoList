INSERT INTO todo_entry (id,
                        created_at,
                        title,
                        finished)

VALUES (nextval('todo_entry_seq'),
        current_timestamp,
        'Buy milk',
        false);

--        (nextval('todo_entry_seq'),
--         current_timestamp,
--         'Pickup children from school',
--         false),
--
--        (nextval('todo_entry_seq'),
--         current_timestamp,
--         'Go to the gym',
--         false);
