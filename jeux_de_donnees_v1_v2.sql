-- Remplissage base de données--

-- 1/utilisateur (1-3)-- ->id1

INSERT INTO public.libraryuser
(userfirstname, username, userpassword, useremail)
VALUES
('michel', 'apeupres','$2a$10$izLvX7nRBB6qohlBCiGEzOHwlCLRoUwAJ0hChn.JAnhXDZp2MT3P.', 'apeupres.michel@gmail.com');

-- 1/utilisateur (2-3)-- ->id2

INSERT INTO public.libraryuser
(userfirstname, username, userpassword, useremail)
VALUES
('bruno', 'michu', '$2a$10$ZrNev/FCEyfKp3.Zc/irx.OrtFuqL7X6t.tJytIOiYLQ458k2jasO', 'michu.bruno@gmail.com');

-- 1/utilisateur (3-3)-- ->id2

INSERT INTO public.libraryuser
(userfirstname, username, userpassword, useremail)
VALUES
('alain', 'deloin', '$2a$10$wNxNPwWl3.2U1l8kn0I0NeOM0CQaTDCY/tvlGv/TVV/0OBBcJGbAK', 'deloin.alain@gmail.com');

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
(bookname, bookauthor, bookpictureurl, bookdescription, booklabel, allbookreserved, waitreservationfull)
VALUES
('elevation', 'Stephen King', 'https://image.noelshack.com/fichiers/2020/07/3/1581497717-elevation-stephen-king.jpg', 'Dans la petite ville de Castle Rock, les rumeurs circulent vite. Trop vite. C''est pourquoi Scott Carey ne veut confier son secret à nul autre que son ami le docteur Bob Ellis. Car avec ou sans vêtements, sa balance affiche la même chose, et chaque jour son poids diminue invariablement. Que se passera-t-il quand il ne pèsera plus rien ?Scott doit également faire face à un autre problème : les chiens de ses nouvelles voisines ont décidé que sa pelouse était le lieu idéal pour faire leurs besoins.
Entre le couple et Scott, la guerre est déclarée. Mais lorsqu''il comprend que le comportement des habitants de Castle Rock, y compris le sien, envers les deux femmes mariées met en péril le restaurant qu''elles ont ouvert en ville, il décide de mettre son « pouvoir » à contribution pour les aider. Un roman joyeux, exaltant et teinté de tristesse.', 'fantastique', true, false);

-- 3/ book (2-7) -> book id 2
INSERT INTO public.book
(bookname, bookauthor, bookpictureurl, bookdescription, booklabel, allbookreserved, waitreservationfull)
VALUES
('player one', 'Ernest Cline', 'https://image.noelshack.com/fichiers/2020/07/3/1581497747-ready-player-one-ernest-cline.jpeg', 'Un monde remis en jeu Un prix ultime Êtes-vous prêt ? Nous sommes en 2044, et la Terre n''est pas belle à voir. Les ressources manquent et les conditions climatiques sont catastrophiques. Comme la majeure partie de l''humanité, Wade Watts passe son temps dans l''Oasis, un monde virtuel où chacun peut faire et être tout ce qui lui chante. Pour oublier la réalité. Oublier les coups de sa tante qui l''a adopté et la misère dans laquelle il vit. Et comme la majeure partie de l''humanité, Wade rêve d''être celui qui décrochera le ticket gagnant de la grande loterie. James Halliday, le créateur de l''Oasis, est mort quelques années auparavant sans laisser de successeur. Pour décider du sort de sa fortune, il a créé une véritable chasse au trésor qui guidera les plus rusés vers l''énigme finale. Wade n''a plus d''autre choix s''il veut survivre : il doit gagner.', 'fiction', false, false);

-- 3/ book (3-7) -> book id 3
INSERT INTO public.book
(bookname, bookauthor, bookpictureurl, bookdescription, booklabel, allbookreserved, waitreservationfull)
VALUES
('cari Mora', 'Thomas Harris', 'https://image.noelshack.com/fichiers/2020/07/3/1581497781-cari-mora.jpeg', 'Des lingots d’or sommeillent depuis des années sous l’ancienne villa de Pablo Escobar à Miami Beach. Gangs et malfrats se battent pour mettre la main dessus.Aujourd’hui, c’est au tour du maléfique Hans-Peter Schneider de tenter sa chance. Mais c’était sans prévoir la présence de la sublime Cari Mora, qui veille sur les lieux. En matière de violence et d’armes à feu, personne n’a rien à lui apprendre.Entre désirs et instinct de survie, avidité et obsessions macabres, le mal se faufile à chaque page. Aucun auteur de ces dernières décennies n’aura autant exploré les démons. Thomas Harris, au talent terrifiant, revient ici avec un sixième roman événement. ', 'fiction', false, false);

-- 3/ book (4-7) -> book id 4
INSERT INTO public.book
(bookname, bookauthor, bookpictureurl, bookdescription, booklabel, allbookreserved, waitreservationfull)
VALUES
('le signal', 'Maxime Chattam', 'https://image.noelshack.com/fichiers/2020/07/3/1581497806-le-signal.jpg', 'La famille Spencer emménage dans la petite ville perdue de Mahingan Falls. Pourtant les nouveaux venus n’y trouvent pas la tranquillité espérée : suicides mystérieux, disparitions de jeunes filles et autres accidents peu naturels s’enchaînent, semant l’angoisse chez les enfants Spencer. Ethan Cobb se doit d’enquêter.', 'fantastique', false, false);

-- 3/ book (5-7) -> book id 5
INSERT INTO public.book
(bookname, bookauthor, bookpictureurl, bookdescription, booklabel, allbookreserved, waitreservationfull)
VALUES
('american elsewhere', 'Robert Jackson Bennet', 'https://image.noelshack.com/fichiers/2020/07/3/1581497829-albin-michel-imaginaire-robert-jackson-bennett-american-elsewhere-hd.jpg', 'Veillée par une lune rose, Wink, au Nouveau-Mexique, est une petite ville idéale. À un détail près?: elle ne figure sur aucune carte. Après deux ans d’errance, Mona Bright, ex-flic, vient d’y hériter de la maison de sa mère, qui s’est suicidée trente ans plus tôt. Très vite, Mona s’attache au calme des rues, aux jolis petits pavillons, aux habitants qui semblent encore vivre dans l’utopique douceur des années cinquante. Pourtant, au fil de ses rencontres et de son enquête sur le passé de sa mère et les circonstances de sa mort (fuyez le naturel…), Mona doit se rendre à l’évidence : une menace plane sur Wink et ses étranges habitants.
Sera-t-elle vraiment de taille à affronter les forces occultes à l’œuvre dans ce lieu hors d’Amérique ?', 'Science-fiction', false, false);

-- 3/ book (6-7) -> book id 6
INSERT INTO public.book
(bookname, bookauthor, bookpictureurl, bookdescription, booklabel, allbookreserved, waitreservationfull)
VALUES
('la bibliotheque du mount char', 'Scott hawkins', 'https://image.noelshack.com/fichiers/2020/07/3/1581497858-mount-char.jpg', 'Carolyn était une jeune Américaine comme les autres. Mais ça, c’était avant. Avant la mort de ses parents. Avant qu’un mystérieux personnage, Père, ne la prenne sous son aile avec d’autres orphelins.
Depuis, Carolyn n’a pas eu tant d’occasions de sortir. Elle et sa fratrie d’adoption ont été élevés suivant les coutumes anciennes de Père. Ils ont étudié les livres de sa Bibliothèque et appris quelques-uns des secrets de sa puissance. Parfois, ils se sont demandé si leur tuteur intransigeant ne pourrait pas être Dieu lui-même.
Mais Père a disparu – peut-être même est-il mort – et il n’y a maintenant plus personne pour protéger la Bibliothèque des féroces combattants qui cherchent à s’en emparer.
Carolyn se prépare pour la bataille qui s’annonce. Le destin de l’univers est en jeu, mais Carolyn a tout prévu. Carolyn a un plan.', 'fantasy', false, false);

-- 3/ book (7-7) -> book id 7
INSERT INTO public.book
(bookname, bookauthor, bookpictureurl, bookdescription, booklabel, allbookreserved, waitreservationfull)
VALUES
('a quelques secondes prés', 'Harlan coben', 'https://image.noelshack.com/fichiers/2020/07/3/1581497873-a-quelques-secondes-pres.jpg', 'À 16 ans, Mickey Bolitar a déjà vécu son lot de tragédies : la disparition de son père, les difficultés de sa mère à surmonter ce choc, l’installation chez son oncle Myron avec qui il ne s’entend pas. Des questions sur le passé de ses parents et sur ce qui est vraiment arrivé à son père le taraudent. Et le cauchemar ne s’arrête pas là. Ce matin, ce sont les policiers qui le réveillent pour lui apprendre une terrible nouvelle : son amie Rachel a été prise dans une fusillade. Il doit absolument découvrir ce qui lui est arrivé. Pour cela, il peut compter sur le soutien de l’énigmatique Ema et du déjanté Spoon.
Comme son oncle Myron, Mickey ne renonce jamais quand il est question d’aider les siens, mais comment les protéger s’il ignore de qui et de quoi exactement ?', 'Policier', false, false);

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
-- library id:1 bibliothèque isidore contient le livre id 7: a quelques secondes pr?s
INSERT INTO public.librarycatalog
(library_id, book_id, bookiteration)
VALUES
( 1, 7, 2);

-- 4/ book catalog (9-9)
-- library id:2 bibliothèque lejeune contient le livre id 7: a quelques secondes pr?s
INSERT INTO public.librarycatalog
(library_id, book_id, bookiteration)
VALUES
( 2, 7, 1);

-- 5/ reservation de livre
-- utilisateur id1:1   book id:1  library id:1
INSERT INTO public.bookreservation
(beginofreservationdate, endofreservationdate, extensionofreservation, bookback, bookbackdate, user_id, book_id, library_id)
VALUES
('10/10/2019 08:10:00', '10/11/2019 08:10:00', false, false, null, 1, 1, 1);

-- utilisateur id1:2   book id:1  library id:1
INSERT INTO public.bookreservation
(beginofreservationdate, endofreservationdate, extensionofreservation, bookback, bookbackdate, user_id, book_id, library_id)
VALUES
('9/10/2019 08:30:00', '09/11/2019 08:30:00', false, false, null, 2, 1, 1);

-- utilisateur id1:3   book id:1  library id:2
INSERT INTO public.bookreservation
(beginofreservationdate, endofreservationdate, extensionofreservation, bookback, bookbackdate, user_id, book_id, library_id)
VALUES
('8/10/2019 08:40:00', '08/11/2019 08:40:00', false, false, null, 3, 1, 2);

-- Explication
--5 livres different avec diferente iteration
--3 reservation  du livre id 1 est dans les bibliotheque -> il n'y a donc plus d'exemplaire dans aucune bibliotheque de la ville

-- 6/ liste d'attente de reservation (1-1)
-- aucune
