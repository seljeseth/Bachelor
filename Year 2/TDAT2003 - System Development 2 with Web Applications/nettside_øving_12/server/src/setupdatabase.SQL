DROP TABLE Kommentarer;
DROP TABLE Artikkel;
DROP TABLE Kategori;



CREATE TABLE Kategori
(

    kategori_id int NOT NULL AUTO_INCREMENT,
    navn        VARCHAR(30),
    PRIMARY KEY (kategori_id)
);


CREATE TABLE Artikkel
(

    id           int          NOT NULL AUTO_INCREMENT,
    overskrift   VARCHAR(300) NOT NULL,
    innhold      VARCHAR(5000),
    tidspunkt    DATETIME     NOT NULL DEFAULT current_timestamp,
    bilde_source TEXT,
    forfatter    varchar(50),
    viktighet    SMALLINT,
    kategori_id  int,
    PRIMARY KEY (id),
    FOREIGN KEY (kategori_id) REFERENCES Kategori (kategori_id)


);

CREATE TABLE Kommentarer
(
    kommentar_id int      not null auto_increment,
    tidspunkt    DATETIME NOT NULL DEFAULT current_timestamp,
    forfatter    varchar(30),
    innhold      varchar(250),
    artikkel_id  int,
    PRIMARY KEY (kommentar_id),
    FOREIGN KEY (artikkel_id) references Artikkel (id)


);

INSERT INTO Kategori(navn)
VALUES ('Nyheter'),
       ('Sport'),
       ('Lifestyle');

INSERT INTO Artikkel (overskrift, innhold, bilde_source, forfatter, viktighet, kategori_id)
VALUES ('Ny Tursti Oppdaget',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent interdum luctus feugiat. Ut lectus diam, varius a lacus in, finibus condimentum orci. Suspendisse potenti. In euismod, tortor vel lacinia luctus, nulla ligula feugiat elit, nec ultrices nisl sem eu diam. Phasellus venenatis metus ut dapibus ornare. Fusce ultricies nec lorem vel blandit. Morbi orci eros, malesuada quis libero in, varius fringilla urna. Aliquam arcu nunc, condimentum nec eros id, egestas maximus libero. Maecenas ullamcorper sapien metus, fermentum tincidunt quam ultrices eget. Suspendisse enim augue, viverra at eros posuere, molestie gravida lectus. Pellentesque nec neque quis arcu vehicula dignissim. Donec pulvinar et justo non varius. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Praesent vel lobortis diam. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Mauris pharetra at ipsum in gravida.

        Nunc vitae nisl rhoncus, ornare ex quis, maximus nibh. Nam quis elit aliquam sem laoreet feugiat. Etiam massa nulla, convallis eu arcu et, pellentesque vestibulum mauris. Donec feugiat varius ipsum vel dapibus. Integer sodales ac massa ac fermentum. Pellentesque a pellentesque est. Sed elementum mattis diam, ut mollis dolor hendrerit aliquam. Donec eget condimentum nunc. Duis in diam luctus augue placerat bibendum id quis nulla.

        Sed finibus feugiat risus in aliquam. Phasellus rhoncus arcu vel libero dictum, sit amet pellentesque diam egestas. Aenean eu dapibus lectus. Quisque aliquam tristique erat ut aliquet. Fusce malesuada diam neque, non convallis ligula fermentum ac. Praesent massa augue, viverra nec rutrum sed, mollis nec felis. Aenean tempor elementum dapibus. Vestibulum consequat nisl eget metus eleifend, at varius mauris tincidunt. Donec maximus, sapien at luctus bibendum, lectus lorem sodales nunc, id sollicitudin leo sapien quis nunc. Praesent molestie lorem arcu, sit amet condimentum ex commodo et. Cras sed lobortis risus. Praesent nec ullamcorper erat, ut accumsan augue.

        Donec non metus nec purus tempor aliquet. Pellentesque tristique tellus ut efficitur sollicitudin. Nullam fringilla nibh vel lectus bibendum, non vulputate nibh pulvinar. Vivamus nec consequat tellus. Ut a accumsan purus, vitae bibendum tortor. Nulla eleifend congue sem. Pellentesque nec tristique nibh. Nam vehicula, lectus quis feugiat pellentesque, purus elit mollis augue, nec tempor ante leo ac quam. Nam feugiat blandit nunc, eu suscipit purus scelerisque vitae. Maecenas a quam urna. Suspendisse porttitor pharetra semper. Morbi sed nulla ac felis suscipit pulvinar. Nam suscipit risus sapien, facilisis egestas ante tempor euismod. Donec malesuada eros consequat fringilla interdum. Maecenas eu vehicula diam, ut commodo diam.

        In laoreet est nisl, sit amet tristique orci sagittis nec. In hac habitasse platea dictumst. Nunc ante neque, molestie quis tempor in, porta in mauris. Nunc vel quam in elit condimentum scelerisque ac vel massa. Proin lacinia pellentesque tristique. Quisque sit amet sagittis ex, ac pulvinar sapien. Nulla eu nisi pellentesque justo efficitur viverra a a augue. In semper augue ultrices sem commodo sodales. Curabitur ut magna quis metus commodo accumsan a et dui. In hac habitasse platea dictumst. Praesent quis risus sit amet diam faucibus laoreet eget eget nunc.',
        'https://cosmos-images2.imgix.net/file/spina/photo/20565/191010_nature.jpg?ixlib=rails-2.1.4&auto=format&ch=Width%2CDPR&fit=max&w=835
        ', 'Mr Beast', 1, 1),

       ('De hotteste reisetipsene',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent interdum luctus feugiat. Ut lectus diam, varius a lacus in, finibus condimentum orci. Suspendisse potenti. In euismod, tortor vel lacinia luctus, nulla ligula feugiat elit, nec ultrices nisl sem eu diam. Phasellus venenatis metus ut dapibus ornare. Fusce ultricies nec lorem vel blandit. Morbi orci eros, malesuada quis libero in, varius fringilla urna. Aliquam arcu nunc, condimentum nec eros id, egestas maximus libero. Maecenas ullamcorper sapien metus, fermentum tincidunt quam ultrices eget. Suspendisse enim augue, viverra at eros posuere, molestie gravida lectus. Pellentesque nec neque quis arcu vehicula dignissim. Donec pulvinar et justo non varius. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Praesent vel lobortis diam. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Mauris pharetra at ipsum in gravida.

        Nunc vitae nisl rhoncus, ornare ex quis, maximus nibh. Nam quis elit aliquam sem laoreet feugiat. Etiam massa nulla, convallis eu arcu et, pellentesque vestibulum mauris. Donec feugiat varius ipsum vel dapibus. Integer sodales ac massa ac fermentum. Pellentesque a pellentesque est. Sed elementum mattis diam, ut mollis dolor hendrerit aliquam. Donec eget condimentum nunc. Duis in diam luctus augue placerat bibendum id quis nulla.

        Sed finibus feugiat risus in aliquam. Phasellus rhoncus arcu vel libero dictum, sit amet pellentesque diam egestas. Aenean eu dapibus lectus. Quisque aliquam tristique erat ut aliquet. Fusce malesuada diam neque, non convallis ligula fermentum ac. Praesent massa augue, viverra nec rutrum sed, mollis nec felis. Aenean tempor elementum dapibus. Vestibulum consequat nisl eget metus eleifend, at varius mauris tincidunt. Donec maximus, sapien at luctus bibendum, lectus lorem sodales nunc, id sollicitudin leo sapien quis nunc. Praesent molestie lorem arcu, sit amet condimentum ex commodo et. Cras sed lobortis risus. Praesent nec ullamcorper erat, ut accumsan augue.

        Donec non metus nec purus tempor aliquet. Pellentesque tristique tellus ut efficitur sollicitudin. Nullam fringilla nibh vel lectus bibendum, non vulputate nibh pulvinar. Vivamus nec consequat tellus. Ut a accumsan purus, vitae bibendum tortor. Nulla eleifend congue sem. Pellentesque nec tristique nibh. Nam vehicula, lectus quis feugiat pellentesque, purus elit mollis augue, nec tempor ante leo ac quam. Nam feugiat blandit nunc, eu suscipit purus scelerisque vitae. Maecenas a quam urna. Suspendisse porttitor pharetra semper. Morbi sed nulla ac felis suscipit pulvinar. Nam suscipit risus sapien, facilisis egestas ante tempor euismod. Donec malesuada eros consequat fringilla interdum. Maecenas eu vehicula diam, ut commodo diam.

        In laoreet est nisl, sit amet tristique orci sagittis nec. In hac habitasse platea dictumst. Nunc ante neque, molestie quis tempor in, porta in mauris. Nunc vel quam in elit condimentum scelerisque ac vel massa. Proin lacinia pellentesque tristique. Quisque sit amet sagittis ex, ac pulvinar sapien. Nulla eu nisi pellentesque justo efficitur viverra a a augue. In semper augue ultrices sem commodo sodales. Curabitur ut magna quis metus commodo accumsan a et dui. In hac habitasse platea dictumst. Praesent quis risus sit amet diam faucibus laoreet eget eget nunc.',
        'https://images.unsplash.com/photo-1551127076-157d9b939bc9?ixlib=rb-1.2.1&w=1000&q=80',
        'Olsen', 1, 3),
       ('Mistet bjørn funnet igjen etter 20 år',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent interdum luctus feugiat. Ut lectus diam, varius a lacus in, finibus condimentum orci. Suspendisse potenti. In euismod, tortor vel lacinia luctus, nulla ligula feugiat elit, nec ultrices nisl sem eu diam. Phasellus venenatis metus ut dapibus ornare. Fusce ultricies nec lorem vel blandit. Morbi orci eros, malesuada quis libero in, varius fringilla urna. Aliquam arcu nunc, condimentum nec eros id, egestas maximus libero. Maecenas ullamcorper sapien metus, fermentum tincidunt quam ultrices eget. Suspendisse enim augue, viverra at eros posuere, molestie gravida lectus. Pellentesque nec neque quis arcu vehicula dignissim. Donec pulvinar et justo non varius. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Praesent vel lobortis diam. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Mauris pharetra at ipsum in gravida.

       Nunc vitae nisl rhoncus, ornare ex quis, maximus nibh. Nam quis elit aliquam sem laoreet feugiat. Etiam massa nulla, convallis eu arcu et, pellentesque vestibulum mauris. Donec feugiat varius ipsum vel dapibus. Integer sodales ac massa ac fermentum. Pellentesque a pellentesque est. Sed elementum mattis diam, ut mollis dolor hendrerit aliquam. Donec eget condimentum nunc. Duis in diam luctus augue placerat bibendum id quis nulla.

       Sed finibus feugiat risus in aliquam. Phasellus rhoncus arcu vel libero dictum, sit amet pellentesque diam egestas. Aenean eu dapibus lectus. Quisque aliquam tristique erat ut aliquet. Fusce malesuada diam neque, non convallis ligula fermentum ac. Praesent massa augue, viverra nec rutrum sed, mollis nec felis. Aenean tempor elementum dapibus. Vestibulum consequat nisl eget metus eleifend, at varius mauris tincidunt. Donec maximus, sapien at luctus bibendum, lectus lorem sodales nunc, id sollicitudin leo sapien quis nunc. Praesent molestie lorem arcu, sit amet condimentum ex commodo et. Cras sed lobortis risus. Praesent nec ullamcorper erat, ut accumsan augue.

       Donec non metus nec purus tempor aliquet. Pellentesque tristique tellus ut efficitur sollicitudin. Nullam fringilla nibh vel lectus bibendum, non vulputate nibh pulvinar. Vivamus nec consequat tellus. Ut a accumsan purus, vitae bibendum tortor. Nulla eleifend congue sem. Pellentesque nec tristique nibh. Nam vehicula, lectus quis feugiat pellentesque, purus elit mollis augue, nec tempor ante leo ac quam. Nam feugiat blandit nunc, eu suscipit purus scelerisque vitae. Maecenas a quam urna. Suspendisse porttitor pharetra semper. Morbi sed nulla ac felis suscipit pulvinar. Nam suscipit risus sapien, facilisis egestas ante tempor euismod. Donec malesuada eros consequat fringilla interdum. Maecenas eu vehicula diam, ut commodo diam.

       In laoreet est nisl, sit amet tristique orci sagittis nec. In hac habitasse platea dictumst. Nunc ante neque, molestie quis tempor in, porta in mauris. Nunc vel quam in elit condimentum scelerisque ac vel massa. Proin lacinia pellentesque tristique. Quisque sit amet sagittis ex, ac pulvinar sapien. Nulla eu nisi pellentesque justo efficitur viverra a a augue. In semper augue ultrices sem commodo sodales. Curabitur ut magna quis metus commodo accumsan a et dui. In hac habitasse platea dictumst. Praesent quis risus sit amet diam faucibus laoreet eget eget nunc.',
        'https://5.imimg.com/data5/HK/RM/MY-4390697/teddy-bear-500x500.jpg', 'Benny', 1, 1),
       ('Skilpadder elsker dette trikset',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent interdum luctus feugiat. Ut lectus diam, varius a lacus in, finibus condimentum orci. Suspendisse potenti. In euismod, tortor vel lacinia luctus, nulla ligula feugiat elit, nec ultrices nisl sem eu diam. Phasellus venenatis metus ut dapibus ornare. Fusce ultricies nec lorem vel blandit. Morbi orci eros, malesuada quis libero in, varius fringilla urna. Aliquam arcu nunc, condimentum nec eros id, egestas maximus libero. Maecenas ullamcorper sapien metus, fermentum tincidunt quam ultrices eget. Suspendisse enim augue, viverra at eros posuere, molestie gravida lectus. Pellentesque nec neque quis arcu vehicula dignissim. Donec pulvinar et justo non varius. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Praesent vel lobortis diam. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Mauris pharetra at ipsum in gravida.

       Nunc vitae nisl rhoncus, ornare ex quis, maximus nibh. Nam quis elit aliquam sem laoreet feugiat. Etiam massa nulla, convallis eu arcu et, pellentesque vestibulum mauris. Donec feugiat varius ipsum vel dapibus. Integer sodales ac massa ac fermentum. Pellentesque a pellentesque est. Sed elementum mattis diam, ut mollis dolor hendrerit aliquam. Donec eget condimentum nunc. Duis in diam luctus augue placerat bibendum id quis nulla.

       Sed finibus feugiat risus in aliquam. Phasellus rhoncus arcu vel libero dictum, sit amet pellentesque diam egestas. Aenean eu dapibus lectus. Quisque aliquam tristique erat ut aliquet. Fusce malesuada diam neque, non convallis ligula fermentum ac. Praesent massa augue, viverra nec rutrum sed, mollis nec felis. Aenean tempor elementum dapibus. Vestibulum consequat nisl eget metus eleifend, at varius mauris tincidunt. Donec maximus, sapien at luctus bibendum, lectus lorem sodales nunc, id sollicitudin leo sapien quis nunc. Praesent molestie lorem arcu, sit amet condimentum ex commodo et. Cras sed lobortis risus. Praesent nec ullamcorper erat, ut accumsan augue.

       Donec non metus nec purus tempor aliquet. Pellentesque tristique tellus ut efficitur sollicitudin. Nullam fringilla nibh vel lectus bibendum, non vulputate nibh pulvinar. Vivamus nec consequat tellus. Ut a accumsan purus, vitae bibendum tortor. Nulla eleifend congue sem. Pellentesque nec tristique nibh. Nam vehicula, lectus quis feugiat pellentesque, purus elit mollis augue, nec tempor ante leo ac quam. Nam feugiat blandit nunc, eu suscipit purus scelerisque vitae. Maecenas a quam urna. Suspendisse porttitor pharetra semper. Morbi sed nulla ac felis suscipit pulvinar. Nam suscipit risus sapien, facilisis egestas ante tempor euismod. Donec malesuada eros consequat fringilla interdum. Maecenas eu vehicula diam, ut commodo diam.

       In laoreet est nisl, sit amet tristique orci sagittis nec. In hac habitasse platea dictumst. Nunc ante neque, molestie quis tempor in, porta in mauris. Nunc vel quam in elit condimentum scelerisque ac vel massa. Proin lacinia pellentesque tristique. Quisque sit amet sagittis ex, ac pulvinar sapien. Nulla eu nisi pellentesque justo efficitur viverra a a augue. In semper augue ultrices sem commodo sodales. Curabitur ut magna quis metus commodo accumsan a et dui. In hac habitasse platea dictumst. Praesent quis risus sit amet diam faucibus laoreet eget eget nunc.',
        'https://upload.wikimedia.org/wikipedia/commons/f/f4/Florida_Box_Turtle_Digon3_re-edited.jpg', 'Mr Turtle', 1,
        3),
       ('Mjølner i tippeligaen',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent interdum luctus feugiat. Ut lectus diam, varius a lacus in, finibus condimentum orci. Suspendisse potenti. In euismod, tortor vel lacinia luctus, nulla ligula feugiat elit, nec ultrices nisl sem eu diam. Phasellus venenatis metus ut dapibus ornare. Fusce ultricies nec lorem vel blandit. Morbi orci eros, malesuada quis libero in, varius fringilla urna. Aliquam arcu nunc, condimentum nec eros id, egestas maximus libero. Maecenas ullamcorper sapien metus, fermentum tincidunt quam ultrices eget. Suspendisse enim augue, viverra at eros posuere, molestie gravida lectus. Pellentesque nec neque quis arcu vehicula dignissim. Donec pulvinar et justo non varius. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Praesent vel lobortis diam. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Mauris pharetra at ipsum in gravida.

       Nunc vitae nisl rhoncus, ornare ex quis, maximus nibh. Nam quis elit aliquam sem laoreet feugiat. Etiam massa nulla, convallis eu arcu et, pellentesque vestibulum mauris. Donec feugiat varius ipsum vel dapibus. Integer sodales ac massa ac fermentum. Pellentesque a pellentesque est. Sed elementum mattis diam, ut mollis dolor hendrerit aliquam. Donec eget condimentum nunc. Duis in diam luctus augue placerat bibendum id quis nulla.

       Sed finibus feugiat risus in aliquam. Phasellus rhoncus arcu vel libero dictum, sit amet pellentesque diam egestas. Aenean eu dapibus lectus. Quisque aliquam tristique erat ut aliquet. Fusce malesuada diam neque, non convallis ligula fermentum ac. Praesent massa augue, viverra nec rutrum sed, mollis nec felis. Aenean tempor elementum dapibus. Vestibulum consequat nisl eget metus eleifend, at varius mauris tincidunt. Donec maximus, sapien at luctus bibendum, lectus lorem sodales nunc, id sollicitudin leo sapien quis nunc. Praesent molestie lorem arcu, sit amet condimentum ex commodo et. Cras sed lobortis risus. Praesent nec ullamcorper erat, ut accumsan augue.

       Donec non metus nec purus tempor aliquet. Pellentesque tristique tellus ut efficitur sollicitudin. Nullam fringilla nibh vel lectus bibendum, non vulputate nibh pulvinar. Vivamus nec consequat tellus. Ut a accumsan purus, vitae bibendum tortor. Nulla eleifend congue sem. Pellentesque nec tristique nibh. Nam vehicula, lectus quis feugiat pellentesque, purus elit mollis augue, nec tempor ante leo ac quam. Nam feugiat blandit nunc, eu suscipit purus scelerisque vitae. Maecenas a quam urna. Suspendisse porttitor pharetra semper. Morbi sed nulla ac felis suscipit pulvinar. Nam suscipit risus sapien, facilisis egestas ante tempor euismod. Donec malesuada eros consequat fringilla interdum. Maecenas eu vehicula diam, ut commodo diam.

       In laoreet est nisl, sit amet tristique orci sagittis nec. In hac habitasse platea dictumst. Nunc ante neque, molestie quis tempor in, porta in mauris. Nunc vel quam in elit condimentum scelerisque ac vel massa. Proin lacinia pellentesque tristique. Quisque sit amet sagittis ex, ac pulvinar sapien. Nulla eu nisi pellentesque justo efficitur viverra a a augue. In semper augue ultrices sem commodo sodales. Curabitur ut magna quis metus commodo accumsan a et dui. In hac habitasse platea dictumst. Praesent quis risus sit amet diam faucibus laoreet eget eget nunc.'
           ,
        'https://g.acdn.no/obscura/API/dynamic/r1/ece5/tr_1080_586_l_f/0000/frem/2018/7/13/12/1531476463753.jpg?chk=0D8EF8',
        'FotballMann123', 1, 2),
       ('Vil noen ha denne oppskriften?',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent interdum luctus feugiat. Ut lectus diam, varius a lacus in, finibus condimentum orci. Suspendisse potenti. In euismod, tortor vel lacinia luctus, nulla ligula feugiat elit, nec ultrices nisl sem eu diam. Phasellus venenatis metus ut dapibus ornare. Fusce ultricies nec lorem vel blandit. Morbi orci eros, malesuada quis libero in, varius fringilla urna. Aliquam arcu nunc, condimentum nec eros id, egestas maximus libero. Maecenas ullamcorper sapien metus, fermentum tincidunt quam ultrices eget. Suspendisse enim augue, viverra at eros posuere, molestie gravida lectus. Pellentesque nec neque quis arcu vehicula dignissim. Donec pulvinar et justo non varius. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Praesent vel lobortis diam. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Mauris pharetra at ipsum in gravida.

       Nunc vitae nisl rhoncus, ornare ex quis, maximus nibh. Nam quis elit aliquam sem laoreet feugiat. Etiam massa nulla, convallis eu arcu et, pellentesque vestibulum mauris. Donec feugiat varius ipsum vel dapibus. Integer sodales ac massa ac fermentum. Pellentesque a pellentesque est. Sed elementum mattis diam, ut mollis dolor hendrerit aliquam. Donec eget condimentum nunc. Duis in diam luctus augue placerat bibendum id quis nulla.

       Sed finibus feugiat risus in aliquam. Phasellus rhoncus arcu vel libero dictum, sit amet pellentesque diam egestas. Aenean eu dapibus lectus. Quisque aliquam tristique erat ut aliquet. Fusce malesuada diam neque, non convallis ligula fermentum ac. Praesent massa augue, viverra nec rutrum sed, mollis nec felis. Aenean tempor elementum dapibus. Vestibulum consequat nisl eget metus eleifend, at varius mauris tincidunt. Donec maximus, sapien at luctus bibendum, lectus lorem sodales nunc, id sollicitudin leo sapien quis nunc. Praesent molestie lorem arcu, sit amet condimentum ex commodo et. Cras sed lobortis risus. Praesent nec ullamcorper erat, ut accumsan augue.

       Donec non metus nec purus tempor aliquet. Pellentesque tristique tellus ut efficitur sollicitudin. Nullam fringilla nibh vel lectus bibendum, non vulputate nibh pulvinar. Vivamus nec consequat tellus. Ut a accumsan purus, vitae bibendum tortor. Nulla eleifend congue sem. Pellentesque nec tristique nibh. Nam vehicula, lectus quis feugiat pellentesque, purus elit mollis augue, nec tempor ante leo ac quam. Nam feugiat blandit nunc, eu suscipit purus scelerisque vitae. Maecenas a quam urna. Suspendisse porttitor pharetra semper. Morbi sed nulla ac felis suscipit pulvinar. Nam suscipit risus sapien, facilisis egestas ante tempor euismod. Donec malesuada eros consequat fringilla interdum. Maecenas eu vehicula diam, ut commodo diam.

       In laoreet est nisl, sit amet tristique orci sagittis nec. In hac habitasse platea dictumst. Nunc ante neque, molestie quis tempor in, porta in mauris. Nunc vel quam in elit condimentum scelerisque ac vel massa. Proin lacinia pellentesque tristique. Quisque sit amet sagittis ex, ac pulvinar sapien. Nulla eu nisi pellentesque justo efficitur viverra a a augue. In semper augue ultrices sem commodo sodales. Curabitur ut magna quis metus commodo accumsan a et dui. In hac habitasse platea dictumst. Praesent quis risus sit amet diam faucibus laoreet eget eget nunc.',
        'https://res.cloudinary.com/norgesgruppen/images/c_scale,dpr_auto,f_auto,q_auto:eco,w_2300/uamrfyhyvuhn3cppszrl/3-grunner-til-a-spise-tranebr',
        'Syltetøydama', 1, 3);

INSERT INTO Kommentarer(FORFATTER, INNHOLD, ARTIKKEL_ID)
VALUES ('en', 'skskksksksksk', 1),
       ('to', 'lollolololololol', 1),
       ('tre', 'hmhmhmmhmhmh', 2),
       ('fire', 'nicenicenice', 2),
       ('fem', 'femfemfem', 3),
       ('seks', 'seks seks seks', 4),
       ('sju', 's j u sju s j u', 5),
       ('åtte', 'åååååååååå', 6),
       ('ni', 'løsmdaslødømlsad', 6),
       ('ti', 'testetesteteset', 3),
       ('elve', 'elon musk', 4),
       ('tolv', 'skskskkskslolololosksksksk', 5);

