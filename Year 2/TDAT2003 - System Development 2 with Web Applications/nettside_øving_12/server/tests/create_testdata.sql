INSERT INTO TestKategori(navn) VALUES
('test katekori 1'),
('test kategori 2'),
('test kategori 3');

INSERT INTO TestArtikkel (overskrift, innhold,  bilde_source, forfatter, viktighet, kategori_id) VALUES
('Overskrift1','test 1 test 1 test 1 test 1','https://storage.googleapis.com/gd-wagtail-prod-assets/images/evolving_google_identity_2x.max-4000x2000.jpegquality-90.jpg','Mr Beast',1,1),
('Overskrift2','test 2 test 2 test 2 test 1','https://www.brands-magazine.com/wp-content/uploads/2019/09/BING-770x513.jpg','Olsen',2,1),
('Overskrift3','test 3 test 3 test 3 test 1','https://5.imimg.com/data5/HK/RM/MY-4390697/teddy-bear-500x500.jpg','Benny',1,2),
('Overskrift4','test 4 test 4 test 4 test 1','https://upload.wikimedia.org/wikipedia/commons/f/f4/Florida_Box_Turtle_Digon3_re-edited.jpg','Mr Turtle',2,2),
('Overskrift5','test 5 test 5 test 5 test 1','https://cdn.britannica.com/17/83817-050-67C814CD/Mount-Everest.jpg','Mountain man',2,3),
('Overskrift6','test 6 test 6 test 6 test 1','https://res.cloudinary.com/norgesgruppen/images/c_scale,dpr_auto,f_auto,q_auto:eco,w_2300/uamrfyhyvuhn3cppszrl/3-grunner-til-a-spise-tranebr','Syltetøydama',1,3);

INSERT INTO TestKommentarer(FORFATTER, INNHOLD, ARTIKKEL_ID) VALUES
('en', 'skskksksksksk', 1),
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



