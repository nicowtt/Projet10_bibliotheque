-- Remplissage base de donn�es--

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
        ('elevation', 'Stephen King', 'https://image.noelshack.com/fichiers/2019/25/3/1560934849-elevation-stephen-king.jpg', 'Dans la petite ville de Castle Rock, les rumeurs circulent vite. Trop vite. C''est pourquoi Scott Carey ne veut confier son secret � nul autre que son ami le docteur Bob Ellis. Car avec ou sans v�tements, sa balance affiche la m�me chose, et chaque jour son poids diminue invariablement. Que se passera-t-il quand il ne p�sera plus rien ?Scott doit �galement faire face � un autre probl�me : les chiens de ses nouvelles voisines ont d�cid� que sa pelouse �tait le lieu id�al pour faire leurs besoins.
Entre le couple et Scott, la guerre est d�clar�e. Mais lorsqu''il comprend que le comportement des habitants de Castle Rock, y compris le sien, envers les deux femmes mari�es met en p�ril le restaurant qu''elles ont ouvert en ville, il d�cide de mettre son � pouvoir � � contribution pour les aider. Un roman joyeux, exaltant et teint� de tristesse.', 'fantastique', true, false);
     
-- 3/ book (2-7) -> book id 2
INSERT INTO public.book
        (bookname, bookauthor, bookpictureurl, bookdescription, booklabel, allbookreserved, waitreservationfull)
VALUES
        ('player one', 'Ernest Cline', 'https://image.noelshack.com/fichiers/2019/25/3/1560934846-ready-player-one-ernest-cline.jpeg', 'Un monde remis en jeu Un prix ultime �tes-vous pr�t ? Nous sommes en 2044, et la Terre n''est pas belle � voir. Les ressources manquent et les conditions climatiques sont catastrophiques. Comme la majeure partie de l''humanit�, Wade Watts passe son temps dans l''Oasis, un monde virtuel o� chacun peut faire et �tre tout ce qui lui chante. Pour oublier la r�alit�. Oublier les coups de sa tante qui l''a adopt� et la mis�re dans laquelle il vit. Et comme la majeure partie de l''humanit�, Wade r�ve d''�tre celui qui d�crochera le ticket gagnant de la grande loterie. James Halliday, le cr�ateur de l''Oasis, est mort quelques ann�es auparavant sans laisser de successeur. Pour d�cider du sort de sa fortune, il a cr�� une v�ritable chasse au tr�sor qui guidera les plus rus�s vers l''�nigme finale. Battre des records � Pac-Man, r�citer par c�ur des paroles de Devo, ou trouver les failles des jeux vid�o cultes : voil� l''unique moyen d''acc�der � son h�ritage colossal. Des centaines de personnes ont essay�, en vain. Joueurs inv�t�r�s ou grands organismes mondiaux corrompus, tous s''y sont cass� les dents. Wade se dit qu''il serait peut-�tre capable de relever le d�fi. Et il r�sout la premi�re �nigme. Mais l''aventure ne fait que commencer, car d''autres joueurs se joignent � la partie. Ils ne reculeront devant aucun meurtre ni aucune trahison pour obtenir la victoire. Wade n''a plus d''autre choix s''il veut survivre : il doit gagner.', 'fiction', false, false);

-- 3/ book (3-7) -> book id 3
INSERT INTO public.book
        (bookname, bookauthor, bookpictureurl, bookdescription, booklabel, allbookreserved, waitreservationfull)
VALUES
        ('cari Mora', 'Thomas Harris', 'https://image.noelshack.com/fichiers/2019/30/3/1563973871-cari-mora.jpeg', 'Des lingots d�or sommeillent depuis des ann�es sous l�ancienne villa de Pablo Escobar � Miami Beach. Gangs et malfrats se battent pour mettre la main dessus.Aujourd�hui, c�est au tour du mal�fique Hans-Peter Schneider de tenter sa chance. Mais c��tait sans pr�voir la pr�sence de la sublime Cari Mora, qui veille sur les lieux. En mati�re de violence et d�armes � feu, personne n�a rien � lui apprendre.Entre d�sirs et instinct de survie, avidit� et obsessions macabres, le mal se faufile � chaque page. Aucun auteur de ces derni�res d�cennies n�aura autant explor� les d�mons. Thomas Harris, au talent terrifiant, revient ici avec un sixi�me roman �v�nement. ', 'fiction', false, false);

-- 3/ book (4-7) -> book id 4
INSERT INTO public.book
        (bookname, bookauthor, bookpictureurl, bookdescription, booklabel, allbookreserved, waitreservationfull)
VALUES
        ('le signal', 'Maxime Chattam', 'https://image.noelshack.com/fichiers/2019/30/3/1563974338-le-signal.jpg', 'La famille Spencer emm�nage dans la petite ville perdue de Mahingan Falls. Pourtant les nouveaux venus n�y trouvent pas la tranquillit� esp�r�e : suicides myst�rieux, disparitions de jeunes filles et autres accidents peu naturels s�encha�nent, semant l�angoisse chez les enfants Spencer. Ethan Cobb se doit d�enqu�ter.', 'fantastique', false, false);

-- 3/ book (5-7) -> book id 5
INSERT INTO public.book
        (bookname, bookauthor, bookpictureurl, bookdescription, booklabel, allbookreserved, waitreservationfull)
VALUES
        ('american elsewhere', 'Robert Jackson Bennet', 'https://image.noelshack.com/fichiers/2019/30/3/1563974656-albin-michel-imaginaire-robert-jackson-bennett-american-elsewhere-hd.jpg', 'Veill�e par une lune rose, Wink, au Nouveau-Mexique, est une petite ville id�ale. � un d�tail pr�s?: elle ne figure sur aucune carte. Apr�s deux ans d�errance, Mona Bright, ex-flic, vient d�y h�riter de la maison de sa m�re, qui s�est suicid�e trente ans plus t�t. Tr�s vite, Mona s�attache au calme des rues, aux jolis petits pavillons, aux habitants qui semblent encore vivre dans l�utopique douceur des ann�es cinquante. Pourtant, au fil de ses rencontres et de son enqu�te sur le pass� de sa m�re et les circonstances de sa mort (fuyez le naturel�), Mona doit se rendre � l��vidence : une menace plane sur Wink et ses �tranges habitants.
Sera-t-elle vraiment de taille � affronter les forces occultes � l��uvre dans ce lieu hors d�Am�rique ?', 'Science-fiction', false, false);

-- 3/ book (6-7) -> book id 6
INSERT INTO public.book
        (bookname, bookauthor, bookpictureurl, bookdescription, booklabel, allbookreserved, waitreservationfull)
VALUES
        ('la bibliotheque du mount char', 'Scott hawkins', 'https://image.noelshack.com/fichiers/2019/30/4/1564065240-mount-char.jpg', 'Carolyn �tait une jeune Am�ricaine comme les autres. Mais �a, c��tait avant. Avant la mort de ses parents. Avant qu�un myst�rieux personnage, P�re, ne la prenne sous son aile avec d�autres orphelins.
Depuis, Carolyn n�a pas eu tant d�occasions de sortir. Elle et sa fratrie d�adoption ont �t� �lev�s suivant les coutumes anciennes de P�re. Ils ont �tudi� les livres de sa Biblioth�que et appris quelques-uns des secrets de sa puissance. Parfois, ils se sont demand� si leur tuteur intransigeant ne pourrait pas �tre Dieu lui-m�me.
Mais P�re a disparu � peut-�tre m�me est-il mort � et il n�y a maintenant plus personne pour prot�ger la Biblioth�que des f�roces combattants qui cherchent � s�en emparer.
Carolyn se pr�pare pour la bataille qui s�annonce. Le destin de l�univers est en jeu, mais Carolyn a tout pr�vu. Carolyn a un plan. Le seul probl�me, c�est qu�en s�acharnant � cr�er un nouveau dieu elle a oubli� de pr�server ce qui fait d�elle un �tre humain.', 'fantasy', false, false);

-- 3/ book (7-7) -> book id 7
INSERT INTO public.book
        (bookname, bookauthor, bookpictureurl, bookdescription, booklabel, allbookreserved, waitreservationfull)
VALUES
        ('a quelques secondes pr�s', 'Harlan coben', 'https://image.noelshack.com/fichiers/2019/30/4/1564065738-a-quelques-secondes-pres.jpg', '� 16 ans, Mickey Bolitar a d�j� v�cu son lot de trag�dies : la disparition de son p�re, les difficult�s de sa m�re � surmonter ce choc, l�installation chez son oncle Myron avec qui il ne s�entend pas. Des questions sur le pass� de ses parents et sur ce qui est vraiment arriv� � son p�re le taraudent. Et le cauchemar ne s�arr�te pas l�. Ce matin, ce sont les policiers qui le r�veillent pour lui apprendre une terrible nouvelle : son amie Rachel a �t� prise dans une fusillade. Il doit absolument d�couvrir ce qui lui est arriv�. Pour cela, il peut compter sur le soutien de l��nigmatique Ema et du d�jant� Spoon.
Comme son oncle Myron, Mickey ne renonce jamais quand il est question d�aider les siens, mais comment les prot�ger s�il ignore de qui et de quoi exactement ?', 'Policier', false, false);

-- 4/ book catalog (1-9)
-- library id:1 biblioth�que isidore contient le livre id 1: elevation
INSERT INTO public.librarycatalog
        (library_id, book_id, bookiteration)
VALUES
        ( 1, 1, 2);  

-- 4/ book catalog (2-9)
-- library id:1 biblioth�que isidore contient le livre id 2: player one
INSERT INTO public.librarycatalog
        (library_id, book_id, bookiteration)
VALUES
        ( 1, 2, 1);  

-- 4/ book catalog (3-9)
-- library id:2 biblioth�que lejeune contient le livre id 1: elevation
INSERT INTO public.librarycatalog
        (library_id, book_id, bookiteration)
VALUES
        ( 2, 1, 1); 

-- 4/ book catalog (4-9)
-- library id:1 biblioth�que isidore contient le livre id 3: cari mora
INSERT INTO public.librarycatalog
        (library_id, book_id, bookiteration)
VALUES
        ( 1, 3, 3); 

-- 4/ book catalog (5-9)
-- library id:1 biblioth�que isidore contient le livre id 4: le signal
INSERT INTO public.librarycatalog
        (library_id, book_id, bookiteration)
VALUES
        ( 1, 4, 1); 
        
-- 4/ book catalog (6-9)
-- library id:2 biblioth�que le jeune contient le livre id 4: le signal
INSERT INTO public.librarycatalog
        (library_id, book_id, bookiteration)
VALUES
        ( 2, 4, 1);
        
-- 4/ book catalog (7-9)
-- library id:1 biblioth�que isidore contient le livre id 5: american elsewhere
INSERT INTO public.librarycatalog
        (library_id, book_id, bookiteration)
VALUES
        ( 2, 5, 2);
        
-- 4/ book catalog (8-9)
-- library id:1 biblioth�que isidore contient le livre id 6: la bibliotheque de mount char
INSERT INTO public.librarycatalog
        (library_id, book_id, bookiteration)
VALUES
        ( 1, 6, 1);

-- 4/ book catalog (9-9)
-- library id:1 biblioth�que isidore contient le livre id 7: a quelques secondes pr?s
INSERT INTO public.librarycatalog
        (library_id, book_id, bookiteration)
VALUES
        ( 1, 7, 2);
        
-- 4/ book catalog (9-9)
-- library id:2 biblioth�que lejeune contient le livre id 7: a quelques secondes pr?s
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
        