INSERT INTO album (title, cover_image, release_date, description) VALUES
('DEAD END', '/images/dead-end-cover.jpg', '2023-01-15', 'Первый полноформатный альбом ZXC CURSED'),
('GHOST TOWN', '/images/ghost-town-cover.jpg', '2023-06-20', 'Второй студийный альбом');

INSERT INTO song (title, lyrics, album_id) VALUES
('Мёртвые цветы', 'Текст песни Мёртвые цветы.пппппп..', 1),
('Призрачный танец', 'Текст песни Призрачный танецпппп ппппп...', 1),
('Последний вздох', 'Текст песни Последний вздох...', 2),
('METAMORPHOSIS 3', 'Текст песни METAMORPHOSIS 3.ппппппппппп пкпвыпкв пцп уцп  квикп вымм пи аав..', 1),
('never enough', 'Текст песни never enough...', 1),
('new era', 'Текст песни new era...', 2),
('SHINIGAMI EYES', 'Текст песни SHINIGAMI EYES...', 2),
('Fatality', 'Текст песни Fatality...', 1);
-- Песни без альбома
INSERT INTO song (title, lyrics, album_id) VALUES
('Lost in Thoughts', 'Текст песни без альбома...', NULL),
('Eternal Night', 'Еще одна песня без альбома...', NULL);