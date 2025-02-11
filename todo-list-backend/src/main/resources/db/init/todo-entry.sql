INSERT INTO todo_entry (id,
                        created_at,
                        title,
                        finished)

VALUES (nextval('todo_entry_seq'),
        current_timestamp,
        'Buy milk',
        false);