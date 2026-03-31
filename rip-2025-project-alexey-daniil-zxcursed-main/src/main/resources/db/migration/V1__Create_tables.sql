CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    profile_picture VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE album (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    cover_image VARCHAR(255),
    release_date DATE,
    description TEXT
);

CREATE TABLE song (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    lyrics TEXT,
    album_id BIGINT REFERENCES album(id)
);

CREATE TABLE annotation (
    id BIGSERIAL PRIMARY KEY,
    text TEXT NOT NULL,
    start_offset INTEGER,
    end_offset INTEGER,
    user_id BIGINT REFERENCES users(id),
    song_id BIGINT REFERENCES song(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
-- Таблица для комментариев к альбомам
CREATE TABLE album_comment (
    id BIGSERIAL PRIMARY KEY,
    text TEXT NOT NULL,
    user_id BIGINT REFERENCES users(id),
    album_id BIGINT REFERENCES album(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Таблица для комментариев к песням
CREATE TABLE song_comment (
    id BIGSERIAL PRIMARY KEY,
    text TEXT NOT NULL,
    user_id BIGINT REFERENCES users(id),
    song_id BIGINT REFERENCES song(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Таблица для профилей пользователей
CREATE TABLE user_profile (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT UNIQUE REFERENCES users(id),
    bio TEXT,
    avatar_path VARCHAR(255),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE rating (
    id BIGSERIAL PRIMARY KEY,
    rating_value INTEGER NOT NULL,
    user_id BIGINT REFERENCES users(id),
    song_id BIGINT REFERENCES song(id),
    album_id BIGINT REFERENCES album(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);