INSERT INTO post (id, title, type, body, image_url, creation_time_stamp, update_time_stamp)
VALUES (1, 'Whisper', 'App',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur faucibus vitae arcu eget ultrices. Donec cursus dignissim ante nec porttitor. Cras iaculis cursus mattis. Morbi et blandit quam. Phasellus tempus ante et commodo consectetur. Aenean elementum enim vel metus vulputate, et porttitor arcu iaculis. Suspendisse potenti. Nulla scelerisque ipsum eget felis hendrerit porta ac sed turpis. Cras sed lacus eu sapien varius mollis. Praesent et justo vestibulum, ullamcorper urna at, aliquet purus. Etiam sed vestibulum eros. Phasellus fermentum urna elit, nec consectetur orci gravida tristique.',
        'https://icdn6.digitaltrends.com/image/digitaltrends/whisper-secret-768x768.jpg',
        '2020-12-10 10:13',
        '2020-12-10 10:13'),
       (2, 'Vox', 'Youtube Channel',
        'Pellentesque ultrices at sem quis sollicitudin. Cras fermentum placerat metus nec aliquet. Curabitur hendrerit varius auctor. Curabitur diam felis, egestas nec mi in, dignissim suscipit elit. Cras quam felis, commodo ac cursus vel, accumsan ut nunc. Aenean at sem augue. Praesent non ultrices velit. Quisque enim sem, consectetur vel libero sed, placerat venenatis lacus. Morbi eu urna eu justo sagittis tempus auctor vitae nibh. Maecenas hendrerit sit amet metus vel venenatis. Vivamus sit amet lorem ut nunc sagittis ultrices in in neque. Aenean nulla nisl, tincidunt sed fringilla eu, rhoncus vel neque. Nunc quis ex at nisi imperdiet accumsan eget nec mi. Suspendisse potenti. Phasellus enim leo, accumsan et metus convallis, molestie ultrices tortor.',
        'https://cdn.vox-cdn.com/thumbor/jzp6Qx96UVwgflZvRTBnapUAEM0=/1400x1050/filters:format(jpeg)/cdn.vox-cdn.com/uploads/chorus_asset/file/18980610/crossword_yellow__1_.jpg',
        '2020-12-11 14:03',
        '2020-12-11 14:03'),
       (3, 'Love Death & Robots', 'Netflix Series',
        'Ut vestibulum arcu et urna semper, vel fermentum nibh pulvinar. Quisque tincidunt leo lectus, eu luctus nisi ultricies sed. Mauris blandit, felis quis elementum convallis, lorem eros lacinia dui, et aliquet ipsum metus nec risus. Aenean lacinia lobortis mauris, vel tristique purus varius at. Nam vel magna imperdiet, fringilla arcu tincidunt, iaculis libero. Maecenas at hendrerit lorem. Nam sed fringilla arcu. Ut iaculis vehicula mollis. Mauris malesuada, dui tempus rutrum rutrum, sapien ipsum mattis lacus, ac laoreet est est a sem. Pellentesque euismod leo tellus, id dignissim diam volutpat a. Phasellus ultricies dignissim enim. Quisque enim eros, vestibulum at tincidunt eget, cursus non nibh. Pellentesque id libero ultricies, viverra tellus id, sodales erat.',
        'https://www.indiewire.com/wp-content/uploads/2019/02/Love-Death-and-Robots-Netflix-2.jpg',
        '2020-12-12 21:16',
        '2020-12-13 21:16'),
       (4, 'Sandra And Woo', 'Webpage',
        'In euismod, risus sed lacinia ornare, nunc nulla iaculis tellus, at venenatis ipsum dolor at turpis. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Sed porta vehicula velit eget accumsan. Aliquam rhoncus est elit, non elementum libero accumsan sit amet. Vivamus et enim id metus condimentum fermentum id a dui. Pellentesque sed velit a augue sagittis lacinia sed sed ante. Vivamus augue dolor, viverra eget suscipit vestibulum, convallis eget odio. Phasellus interdum justo at consectetur porta. Ut blandit auctor arcu quis feugiat. Pellentesque eu mi et enim aliquam tincidunt. Suspendisse ultrices mauris quam, vel cursus metus iaculis ut. Vestibulum euismod in ex non commodo. Integer sit amet massa ultrices, bibendum justo id, dapibus erat',
        'http://www.sandraandwoo.com/comics/2016-01-04-0749-dating-strategy.png',
        '2020-12-14 18:47',
        '2020-12-14 18:47');


INSERT INTO role (id, role_name)
VALUES (1, 'ADMIN'),
       (2, 'USER');

INSERT INTO user (id, username, password, nickname, avatar_url)
VALUES (1, 'admin', '{bcrypt}$2y$12$dlIh7.olDvlfRUA8ODvR4.CL7i7NVLhD4YB9593e/L27l/ZTSxdmK', 'ADMIN',
        'https://cdn.pixabay.com/photo/2016/10/31/00/31/settings-1784649_960_720.png'),
       (2, 'jonas@post.lt', '{bcrypt}$2y$12$7EpdJx/NKl0hYLlnjP5q1uFX1Ene6s3.DbI95hCIq1Xy5yA8/oKw6', 'Vingiu Jonas',
        'https://ih1.redbubble.net/image.233704944.3693/flat,750x1000,075,f.u3.jpg'),
       (3, 'bite@post.lt', '{bcrypt}$2y$12$Y6KSFoZ0Zy34EBysAWnfCOB7sAqSq9PpiSyein94LDpCl03O52ICS', 'Bitė Vilimaitė',
        'https://i.pinimg.com/236x/6e/44/4d/6e444dc57ec98ffbaabd288f40f00eae--kiss-art-a-kiss.jpg'),
       (4, 'thug@post.lt', '{bcrypt}$2y$12$OYfZdbBU8qhKVeb6ormO8unuIWUAVBHs5/qTTP0iLhh2T99SKusEe', 'Finansinis Nusikaltėlis',
        'https://assets.awwwards.com/awards/images/2013/06/secret-life-heroes%20(44).jpg');


INSERT INTO role_users (role_id, users_id)
VALUES (1, 1),
       (2, 2),
       (2, 3),
       (2, 4);


INSERT INTO comment (id, post_id, user_id, body, creation_time_stamp, update_time_stamp)
VALUES (1, 1, 2,
        'I see you have something to talk about. Well, I have something to shout about. Infact something to sing about. But I''ll just keep quiet and let you carry on.',
        '2020-12-10 10:20',
        '2020-12-10 10:20'),
       (2, 1, 3,
        'I like to wax my legs and stick the hair on my back. Why? Because it keeps my back warm. There''s method in my madness.',
        '2020-12-10 10:33',
        '2020-12-10 10:33'),
       (3, 1, 2,
        'If you really wanted to do that, then why wouldn''t you do that? Instead you do this. It makes no sense.',
        '2020-12-10 10:35',
        '2020-12-10 10:35'),
        (4, 1, 4,
        'Look! In the sky. It''s a bird, it''s a plane. Or is it a hellicopter? No actually I think it is a bird. Or maybe I''m just seeing things. Who knows... After 10 shots of Whiskey things start to get a bit strange.',
        '2020-12-10 16:01',
        '2020-12-10 16:01'),
        (5, 2, 4,
        'If I roll once and you roll twice. What does that mean?',
        '2020-12-11 15:27',
        '2020-12-10 15:27'),
        (6, 2, 1,
        'Please keep your comments clean and concentrate on the topic.',
        '2020-12-11 15:30',
        '2020-12-10 15:30'),
        (7, 2, 4,
        'Clean is for the weak.',
        '2020-12-11 15:27',
        '2020-12-10 15:27'),
        (8, 3, 3,
        'It''s gonna take a lot to take me away from you There''s nothing that a hundred men or more could ever do I bless the rains down in Africa Gonna take some time to do the things we never have.',
        '2020-12-13 09:40',
        '2020-12-13 09:40'),
        (9, 3, 2,
        'Toto?',
        '2020-12-13 10:32',
        '2020-12-13 10:32'),
        (10, 4, 2,
        'Best thing ever!',
        '2020-12-14 23:59',
        '2020-12-14 23:59');

INSERT INTO user_comments (comments_id, user_id)
VALUES (1, 2),
       (2, 3),
       (3, 2),
       (4, 4),
       (5, 4),
       (6, 1),
       (7, 4),
       (8, 3),
       (9, 2),
       (10, 2);

INSERT INTO post_comments (comments_id, post_id)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (4, 1),
       (5, 2),
       (6, 2),
       (7, 2),
       (8, 3),
       (9, 3),
       (10, 4);




