-- Remplissage base de données--

-- 1/utilisateur (1-2)-- ->id1

INSERT INTO public.libraryuser
        (userfirstname, username, userpassword, useremail)
VALUES
        ('michel', 'apeupres','$2a$10$izLvX7nRBB6qohlBCiGEzOHwlCLRoUwAJ0hChn.JAnhXDZp2MT3P.', 'apeupres.michel@gmail.com');

-- 1/utilisateur (2-2)-- ->id2

INSERT INTO public.libraryuser
        (userfirstname, username, userpassword, useremail)
VALUES
        ('bruno', 'michu', '$2a$10$Loe8.FEvR6CQL6GwhzX2ieJKd0SwkZi1ChwEY36i1fQxh.7zfiyTS', 'michu.bruno@gmail.com');

-- 2/library (1-2) -> id1
INSERT INTO public.library
        (libraryname)
VALUES
        ('isidore');

-- 2/library (2-2) -> id2
INSERT INTO public.library
        (libraryname)
VALUES
        ('lejeune');
        
-- 3/ book (1-7) -> book id 1
INSERT INTO public.book
        (bookname, bookauthor, bookpictureurl, bookdescription, booklabel, allbookreserved)
VALUES
        ('elevation', 'Stephen King', 'https://image.noelshack.com/fichiers/2019/25/3/1560934849-elevation-stephen-king.jpg', 'Dans la petite ville de Castle Rock, les rumeurs circulent vite. Trop vite. C''est pourquoi Scott Carey ne veut confier son secret à nul autre que son ami le docteur Bob Ellis. Car avec ou sans vêtements, sa balance affiche la même chose, et chaque jour son poids diminue invariablement. Que se passera-t-il quand il ne pèsera plus rien ?Scott doit également faire face à un autre problème : les chiens de ses nouvelles voisines ont décidé que sa pelouse était le lieu idéal pour faire leurs besoins.
Entre le couple et Scott, la guerre est déclarée. Mais lorsqu''il comprend que le comportement des habitants de Castle Rock, y compris le sien, envers les deux femmes mariées met en péril le restaurant qu''elles ont ouvert en ville, il décide de mettre son « pouvoir » à contribution pour les aider. Un roman joyeux, exaltant et teinté de tristesse. Entertainment Weekly.Édition illustrée.', 'fantastique', false);
     
-- 3/ book (2-7) -> book id 2
INSERT INTO public.book
        (bookname, bookauthor, bookpictureurl, bookdescription, booklabel, allbookreserved)
VALUES
        ('player one', 'Ernest Cline', 'https://image.noelshack.com/fichiers/2019/25/3/1560934846-ready-player-one-ernest-cline.jpeg', 'Un monde remis en jeu Un prix ultime Êtes-vous prêt ? Nous sommes en 2044, et la Terre n''est pas belle à voir. Les ressources manquent et les conditions climatiques sont catastrophiques. Comme la majeure partie de l''humanité, Wade Watts passe son temps dans l''Oasis, un monde virtuel où chacun peut faire et être tout ce qui lui chante. Pour oublier la réalité. Oublier les coups de sa tante qui l''a adopté et la misère dans laquelle il vit. Et comme la majeure partie de l''humanité, Wade rêve d''être celui qui décrochera le ticket gagnant de la grande loterie. James Halliday, le créateur de l''Oasis, est mort quelques années auparavant sans laisser de successeur. Pour décider du sort de sa fortune, il a créé une véritable chasse au trésor qui guidera les plus rusés vers l''énigme finale. Battre des records à Pac-Man, réciter par cœur des paroles de Devo, ou trouver les failles des jeux vidéo cultes : voilà l''unique moyen d''accéder à son héritage colossal. Des centaines de personnes ont essayé, en vain. Joueurs invétérés ou grands organismes mondiaux corrompus, tous s''y sont cassé les dents. Wade se dit qu''il serait peut-être capable de relever le défi. Et il résout la première énigme. Mais l''aventure ne fait que commencer, car d''autres joueurs se joignent à la partie. Ils ne reculeront devant aucun meurtre ni aucune trahison pour obtenir la victoire. Wade n''a plus d''autre choix s''il veut survivre : il doit gagner.', 'fiction', true);

-- 3/ book (3-7) -> book id 3
INSERT INTO public.book
        (bookname, bookauthor, bookpictureurl, bookdescription, booklabel, allbookreserved)
VALUES
        ('cari Mora', 'Thomas Harris', 'https://image.noelshack.com/fichiers/2019/30/3/1563973871-cari-mora.jpeg', 'Des lingots d’or sommeillent depuis des années sous l’ancienne villa de Pablo Escobar à Miami Beach. Gangs et malfrats se battent pour mettre la main dessus.Aujourd’hui, c’est au tour du maléfique Hans-Peter Schneider de tenter sa chance. Mais c’était sans prévoir la présence de la sublime Cari Mora, qui veille sur les lieux. En matière de violence et d’armes à feu, personne n’a rien à lui apprendre.Entre désirs et instinct de survie, avidité et obsessions macabres, le mal se faufile à chaque page. Aucun auteur de ces dernières décennies n’aura autant exploré les démons. Thomas Harris, au talent terrifiant, revient ici avec un sixième roman événement. APRÈS DOUZE ANS D’ABSENCE,CARI MORA SIGNE LE RETOURD’UN MAÎTRE ABSOLU DU THRILLER.', 'fiction', false);

-- 3/ book (4-7) -> book id 4
INSERT INTO public.book
        (bookname, bookauthor, bookpictureurl, bookdescription, booklabel, allbookreserved)
VALUES
        ('le signal', 'Maxime Chattam', 'https://image.noelshack.com/fichiers/2019/30/3/1563974338-le-signal.jpg', 'La famille Spencer emménage dans la petite ville perdue de Mahingan Falls. Pourtant les nouveaux venus n’y trouvent pas la tranquillité espérée: suicides mystérieux, disparitions de jeunes filles et autres accidents peu naturels s’enchaînent, semant l’angoisse chez les enfants Spencer. Ethan Cobb se doit d’enquêter.', 'fantastique', false);

-- 3/ book (5-7) -> book id 5
INSERT INTO public.book
        (bookname, bookauthor, bookpictureurl, bookdescription, booklabel, allbookreserved)
VALUES
        ('american elsewhere', 'Robert Jackson Bennet', 'https://image.noelshack.com/fichiers/2019/30/3/1563974656-albin-michel-imaginaire-robert-jackson-bennett-american-elsewhere-hd.jpg', 'Veillée par une lune rose, Wink, au Nouveau-Mexique, est une petite ville idéale. À un détail près: elle ne figure sur aucune carte. Après deux ans d’errance, Mona Bright, ex-flic, vient d’y hériter de la maison de sa mère, qui s’est suicidée trente ans plus tôt. Très vite, Mona s’attache au calme des rues, aux jolis petits pavillons, aux habitants qui semblent encore vivre dans l’utopique douceur des années cinquante. Pourtant, au fil de ses rencontres et de son enquête sur le passé de sa mère et les circonstances de sa mort (fuyez le naturel…), Mona doit se rendre à l’évidence : une menace plane sur Wink et ses étranges habitants. Sera-t-elle vraiment de taille à affronter les forces occultes à l’œuvre dans ce lieu hors d’Amérique ?', 'Science-fiction', false);

-- 3/ book (6-7) -> book id 6
INSERT INTO public.book
        (bookname, bookauthor, bookpictureurl, bookdescription, booklabel, allbookreserved)
VALUES
        ('la bibliotheque du mount char', 'Scott hawkins', 'https://image.noelshack.com/fichiers/2019/30/4/1564065240-mount-char.jpg', 'Carolyn était une jeune Américaine comme les autres. Mais ça, c’était avant. Avant la mort de ses parents. Avant qu’un mystérieux personnage, Père, ne la prenne sous son aile avec d’autres orphelins.
Depuis, Carolyn n’a pas eu tant d’occasions de sortir. Elle et sa fratrie d’adoption ont été élevés suivant les coutumes anciennes de Père. Ils ont étudié les livres de sa Bibliothèque et appris quelques-uns des secrets de sa puissance. Parfois, ils se sont demandé si leur tuteur intransigeant ne pourrait pas être Dieu lui-même.
Mais Père a disparu – peut-être même est-il mort – et il n’y a maintenant plus personne pour protéger la Bibliothèque des féroces combattants qui cherchent à s’en emparer.
Carolyn se prépare pour la bataille qui s’annonce. Le destin de l’univers est en jeu, mais Carolyn a tout prévu. Carolyn a un plan. Le seul problème, c’est qu’en s’acharnant à créer un nouveau dieu elle a oublié de préserver ce qui fait d’elle un être humain.', 'fantasy', false);

-- 3/ book (7-7) -> book id 7
INSERT INTO public.book
        (bookname, bookauthor, bookpictureurl, bookdescription, booklabel, allbookreserved)
VALUES
        ('a quelques secondes près', 'Harlan coben', 'https://image.noelshack.com/fichiers/2019/30/4/1564065738-a-quelques-secondes-pres.jpg', 'Chez les Bolitar, enquêter est une affaire de famille…
À 16 ans, Mickey Bolitar a déjà vécu son lot de tragédies : la disparition de son père, les difficultés de sa mère à surmonter ce choc, l’installation chez son oncle Myron avec qui il ne s’entend pas. Des questions sur le passé de ses parents et sur ce qui est vraiment arrivé à son père le taraudent. Et le cauchemar ne s’arrête pas là. Ce matin, ce sont les policiers qui le réveillent pour lui apprendre une terrible nouvelle : son amie Rachel a été prise dans une fusillade. Il doit absolument découvrir ce qui lui est arrivé. Pour cela, il peut compter sur le soutien de l’énigmatique Ema et du déjanté Spoon.
Comme son oncle Myron, Mickey ne renonce jamais quand il est question d’aider les siens, mais comment les protéger s’il ignore de qui et de quoi exactement ?', 'Policier', false);

-- 4/ book catalog (1-9)
-- library id:1 bibliothèque isidore contient le livre id 1: elevation
INSERT INTO public.librarycatalog
        (library_id, book_id, bookiteration)
VALUES
        ( 1, 1, 2);  

-- 4/ book catalog (2-9)
-- library id:1 bibliothèque isidore contient le livre id 2: player one
INSERT INTO public.librarycatalog
        (library_id, book_id, bookiteration)
VALUES
        ( 1, 2, 1);  

-- 4/ book catalog (3-9)
-- library id:2 bibliothèque lejeune contient le livre id 1: elevation
INSERT INTO public.librarycatalog
        (library_id, book_id, bookiteration)
VALUES
        ( 2, 1, 1); 

-- 4/ book catalog (4-9)
-- library id:1 bibliothèque isidore contient le livre id 3: cari mora
INSERT INTO public.librarycatalog
        (library_id, book_id, bookiteration)
VALUES
        ( 1, 3, 3); 

-- 4/ book catalog (5-9)
-- library id:1 bibliothèque isidore contient le livre id 4: le signal
INSERT INTO public.librarycatalog
        (library_id, book_id, bookiteration)
VALUES
        ( 1, 4, 1); 
        
-- 4/ book catalog (6-9)
-- library id:2 bibliothèque le jeune contient le livre id 4: le signal
INSERT INTO public.librarycatalog
        (library_id, book_id, bookiteration)
VALUES
        ( 2, 4, 1);
        
-- 4/ book catalog (7-9)
-- library id:1 bibliothèque isidore contient le livre id 5: american elsewhere
INSERT INTO public.librarycatalog
        (library_id, book_id, bookiteration)
VALUES
        ( 2, 5, 2);
        
-- 4/ book catalog (8-9)
-- library id:1 bibliothèque isidore contient le livre id 6: la bibliotheque de mount char
INSERT INTO public.librarycatalog
        (library_id, book_id, bookiteration)
VALUES
        ( 1, 6, 1);

-- 4/ book catalog (9-9)
-- library id:1 bibliothèque isidore contient le livre id 7: a quelques secondes près
INSERT INTO public.librarycatalog
        (library_id, book_id, bookiteration)
VALUES
        ( 1, 7, 2);
        
-- 4/ book catalog (9-9)
-- library id:2 bibliothèque lejeune contient le livre id 7: a quelques secondes près
INSERT INTO public.librarycatalog
        (library_id, book_id, bookiteration)
VALUES
        ( 2, 7, 1);
        
-- 5/ reservation 
-- utilisateur id1:1   book id:1  library id:1
INSERT INTO public.bookreservation
        (beginofreservationdate, endofreservationdate, extensionofreservation, bookback, user_id, book_id, library_id)
VALUES
        ('19/06/2019', '15/07/2019', false, false, 1, 2, 1);
        
-- Explication
--5 livres different avec diferente iteration
--1 reservation  du livre id 2 est dans la bibliotheque id 1 -> réservé -> il n'y a donc plus d'exemplaire dans aucune bibliotheque de la ville