CREATE TABLE IF NOT EXISTS Summary (
    user_mac_address VARCHAR(255) PRIMARY KEY,
    date_of_search DATE NOT NULL,
    time_of_search TIME NOT NULL,
    webapp_url VARCHAR(255),
    summary TEXT
    );
