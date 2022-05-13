-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:3308
-- Généré le : ven. 06 mai 2022 à 08:39
-- Version du serveur :  5.7.33
-- Version de PHP : 7.4.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gonaya`
--

-- --------------------------------------------------------

--
-- Structure de la table `Film`
--

CREATE TABLE `Film` (
  `id_f` int(15) NOT NULL,
  `titre` varchar(25) NOT NULL,
  `resume` varchar(300) NOT NULL,
  `annee_sortie` int(4) NOT NULL,
  `duree` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `Film`
--

INSERT INTO `Film` (`id_f`, `titre`, `resume`, `annee_sortie`, `duree`) VALUES
(1, 'Avatar', 'Pandora bla bla bla...', 2016, '0'),
(2, 'Titanic', 'Glou glou', 1998, '0'),
(3, 'Dick Johnson Is Dead', 'As her father nears the end of his life, filmmaker Kirsten Johnson stages his death in inventive and comical ways to help them both face the inevitable.', 2020, '90 min'),
(4, 'Sankofa', 'On a photo shoot in Ghana, an American model slips back in time, becomes enslaved on a plantation and bears witness to the agony of her ancestral past.', 1993, '125 min'),
(5, 'The Starling', 'A woman adjusting to life after a loss contends with a feisty bird that\'s taken over her garden — and a husband who\'s struggling to find a way forward.', 2021, '104 min'),
(6, 'Je Suis Karl', 'After most of her family is murdered in a terrorist bombing, a young woman is unknowingly lured into joining the very group that killed them.', 2021, '127 min'),
(7, 'Intrusion', 'After a deadly home invasion at a couple’s new dream house, the traumatized wife searches for answers — and learns the real danger is just beginning.', 2021, '94 min'),
(8, 'Avvai Shanmughi', 'Newly divorced and denied visitation rights with his daughter, a doting father disguises himself as a gray-haired nanny in order to spend time with her.', 1996, '161 min'),
(9, 'Jeans', 'When the father of the man she loves insists that his twin sons marry twin sisters, a woman creates an alter ego that might be a bit too convincing.', 1998, '166 min'),
(10, 'Minsara Kanavu', 'A tangled love triangle ensues when a man falls for a woman studying to become a nun — and she falls for the friend he enlists to help him pursue her.', 1997, '147 min'),
(11, 'Grown Ups', 'Mourning the loss of their beloved junior high basketball coach, five middle-aged pals reunite at a lake house and rediscover the joys of being a kid.', 2010, '103 min'),
(12, 'Dark Skies', 'A family’s idyllic suburban life shatters when an alien force invades their home, and as they struggle to convince others of the deadly threat.', 2013, '97 min'),
(13, 'Paranoia', 'Blackmailed by his company\'s CEO, a low-level employee finds himself forced to spy on the boss\'s rival and former mentor.', 2013, '106 min'),
(14, 'Ankahi Kahaniya', 'As big city life buzzes around them, lonely souls discover surprising sources of connection and companionship in three tales of love, loss and longing.', 2021, '111 min'),
(15, 'The Stronghold', 'Tired of the small-time grind, three Marseille cops get a chance to bust a major drug network. But lines blur when a key informant makes a big ask.', 2021, '105 min'),
(16, 'Birth of the Dragon', 'A young Bruce Lee angers kung fu traditionalists by teaching outsiders, leading to a showdown with a Shaolin master in this film based on real events.', 2017, '96 min'),
(17, 'Jaws', 'When an insatiable great white shark terrorizes Amity Island, a police chief, an oceanographer and a grizzled shark hunter seek to destroy the beast.', 1975, '124 min'),
(18, 'Jaws 2', 'Four years after the last deadly shark attacks, police chief Martin Brody fights to protect Amity Island from another killer great white.', 1978, '116 min'),
(19, 'Jaws 3', 'After the staff of a marine theme park try to capture a young great white shark, they discover its mother has invaded the enclosure and is out for blood.', 1983, '98 min'),
(20, 'Jaws: The Revenge', 'After another deadly shark attack, Ellen Brody has had enough of Amity Island and moves to the Caribbean – but a great white shark follows her there.', 1987, '91 min'),
(21, 'My Heroes Were Cowboys', 'Robin Wiltshire\'s painful childhood was rescued by Westerns. Now he lives on the frontier of his dreams, training the horses he loves for the big screen.', 2021, '23 min'),
(22, 'Safe House', 'Young CIA operative Matt Weston must get a dangerous criminal out of an agency safe house that\'s come under attack and get him to a securer location.', 2012, '115 min'),
(23, 'Training Day', 'A rookie cop with one day to prove himself to a veteran LAPD narcotics officer receives a crash course in his mentor\'s questionable brand of justice.', 2001, '122 min'),
(24, 'Nightbooks', 'Scary story fan Alex must tell a spine-tingling tale every night — or stay trapped with his new friend in a wicked witch\'s magical apartment forever.', 2021, '103 min'),
(25, 'Schumacher', 'Through exclusive interviews and archival footage, this documentary traces an intimate portrait of seven-time Formula 1 champion Michael Schumacher.', 2021, '113 min'),
(26, 'King of Boys', 'When a powerful businesswoman’s political ambitions are threatened by her underworld connections, the ensuing power struggle could cost her everything.', 2018, '182 min'),
(27, 'You vs. Wild: Out Cold', 'After a plane crash leaves Bear with amnesia, he must make choices to save the missing pilot and survive in this high-stakes interactive adventure.', 2021, '106 min'),
(28, 'Tughlaq Durbar', 'A budding politician has devious plans to rise in the ranks — until an unexpected new presence begins to interfere with his every crooked move.', 2020, '145 min'),
(29, 'Tughlaq Durbar (Telugu)', 'A budding politician has devious plans to rise in the ranks — until an unexpected new presence begins to interfere with his every crooked move.', 2021, '145 min'),
(30, 'Kate', 'Slipped a fatal poison on her final job, a ruthless assassin working in Tokyo has less than 24 hours to find out who ordered the hit and exact revenge.', 2021, '106 min'),
(31, 'Omo Ghetto: the Saga', 'Twins are reunited as a good-hearted female gangster and her uptight rich sister take on family, crime, cops and all of the trouble that follows them.', 2020, '147 min'),
(32, 'Prey', 'A hiking trip into the wild turns into a desperate bid for survival for five friends on the run from a mysterious shooter.', 2021, '87 min'),
(33, 'Paradise Hills', 'Uma wakes up in a lush tropical facility designed to turn willful girls into perfect ladies. That’s bad enough, but its real purpose is even worse.', 2019, '95 min'),
(34, 'JJ+E', 'Elisabeth and John-John live in the same city, but they inhabit different worlds. Can a passionate first love break through class and cultural barriers?', 2021, '91 min'),
(35, 'Show Dogs', 'A rough and tough police dog must go undercover with an FBI agent as a prim and proper pet at a dog show to save a baby panda from an illegal sale.', 2018, '90 min'),
(36, 'Untold: Breaking Point', 'Under pressure to continue a winning tradition in American tennis, Mardy Fish faced mental health challenges that changed his life on and off the court.', 2021, '80 min'),
(37, 'Shadow Parties', 'A family faces destruction in a long-running conflict between communities that pits relatives against each other amid attacks and reprisals.', 2020, '117 min'),
(38, 'Angamaly Diaries', 'After growing up amidst the gang wars of his hometown, Vincent forms an entrepreneurial squad of his own and ends up on the wrong side of the law.', 2017, '128 min'),
(39, 'A Champion Heart', 'When a grieving teen must work off her debt to a ranch, she cares for a wounded horse that teaches her more about healing than she expected.', 2018, '90 min'),
(40, 'Worth', 'In the wake of the Sept. 11 attacks, a lawyer faces an emotional reckoning as he attempts to put a dollar value on the lives lost. Based on real events.', 2021, '119 min'),
(41, 'Afterlife of the Party', 'Cassie lives to party... until she dies in a freak accident. Now this social butterfly needs to right her wrongs on Earth if she wants to earn her wings.', 2021, '110 min'),
(42, 'Anjaam', 'A wealthy industrialist’s dangerous obsession with a flight attendant destroys her world, until she takes matters into her own hands to exact revenge.', 1994, '143 min'),
(43, 'Bright Star', 'This drama details the passionate three-year romance between Romantic poet John Keats – who died tragically at age 25 – and his great love and muse.', 2009, '119 min'),
(44, 'Dhanak', 'A movie-loving 10-year-old and her blind little brother trek to meet Indian superstar Shah Rukh Khan for help in getting the boy an eye operation.', 2015, '114 min'),
(45, 'Final Account', 'This documentary stitches together never-before-seen interviews with the last living generation of people who participated in Hitler\'s Third Reich.', 2021, '94 min'),
(46, 'Gurgaon', 'When the daughter of a wealthy family returns from college, she gets a frosty welcome from her brother, who has problems – and plans – of his own.', 2017, '106 min'),
(47, 'Here and There', 'After meeting through a heated exchange on social media, two people with different backgrounds begin an online romance in the midst of a pandemic.', 2020, '99 min'),
(48, 'In the Cut', 'After embarking on an affair with the cop probing the murder of a young woman, an insular schoolteacher suspects her lover was involved in the crime.', 2003, '118 min'),
(49, 'Shikara', 'A couple must strive to remain resilient after regional hostilities drive them from their beloved home into a refugee camp.', 2020, '115 min'),
(50, 'A Cinderella Story', 'Teen Sam meets the boy of her dreams at a dance before returning to toil in her stepmother\'s diner. Can her lost cell phone bring them together?', 2004, '95 min'),
(51, 'An Unfinished Life', 'A grieving widow and her daughter move in with her estranged father-in-law in Wyoming, where time allows them to heal and forgive.', 2005, '108 min'),
(52, 'Chappie', 'In a futuristic society where an indestructible robot police force keeps crime at bay, a lone droid evolves to the next level of artificial intelligence.', 2015, '121 min'),
(53, 'Clear and Present Danger', 'When the president\'s friend is murdered, CIA Deputy Director Jack Ryan becomes unwittingly involved in an illegal war against a Colombian drug cartel.', 1994, '142 min'),
(54, 'Cliffhanger', 'Ranger Gabe Walker and his partner are called to rescue a group of stranded climbers, only to learn the climbers are actually thieving hijackers.', 1993, '113 min'),
(55, 'Cold Mountain', 'This drama follows a wounded Civil War soldier making the long journey home, while his faraway love fights for survival on her deceased father\'s farm.', 2003, '154 min'),
(56, 'Dear John', 'While on summer leave, a U.S. soldier falls for a college student. But when he\'s forced to reenlist, their handwritten letters hold the lovers together.', 2010, '108 min'),
(57, 'Do the Right Thing', 'On a sweltering day in Brooklyn, simmering racial tensions between residents rise to the surface and ignite rage, violence and tragedy.', 1989, '120 min'),
(58, 'Extraction', 'When a retired CIA agent is kidnapped, his son, a government analyst, embarks on an unauthorized mission to find him and halt a terrorist plot.', 2015, '82 min'),
(59, 'Freedom Writers', 'While her at-risk students are reading classics such as \"The Diary of Anne Frank,\" a teacher asks them to keep journals about their troubled lives.', 2007, '124 min'),
(60, 'Green Lantern', 'Test pilot Hal Jordan harnesses glowing new powers for good when he wears an otherworldly ring and helps an intergalactic force stop a powerful threat.', 2011, '114 min'),
(61, 'House Party', 'Grounded by his strict father, Kid risks life and limb to go to his best friend Play\'s big bash but experiences one obstacle after another.', 1990, '104 min'),
(62, 'House Party 2', 'Kid goes off to college with scholarship money but when Play loses Kid\'s tuition funds to a shady music promoter, they devise a wild plan to raise cash.', 1991, '94 min'),
(63, 'House Party 3', 'After Kid gets engaged, Play plans to throw the biggest bachelor party ever. But every celebration for these two always comes with complications.', 1994, '94 min'),
(64, 'I Got the Hook Up', 'After getting their hands on a misdirected shipment of cell phones, two hustlers try to cash in by hawking the merchandise from the back of their van.', 1998, '93 min'),
(65, 'In Too Deep', 'Rookie cop Jeffrey Cole poses as a drug dealer to take down a crime lord and soon gets caught up in an underworld of bribery, intimidation and murder.', 1999, '97 min'),
(66, 'Initial D', 'By day, an 18-year-old delivers tofu for his father, a retired race car driver; but by night, it\'s the teen\'s turn to take the wheel.', 2005, '109 min'),
(67, 'Labyrinth', 'In Jim Henson\'s fantasy, teen Sarah embarks on a life-altering quest to rescue her little brother from the clutches of a treacherous goblin.', 1986, '101 min'),
(68, 'Letters to Juliet', 'By responding to a letter addressed to Shakespeare\'s tragic heroine Juliet Capulet, an American woman in Verona, Italy, is led in search of romance.', 2010, '105 min'),
(69, 'Level 16', 'In a bleak academy that teaches girls the virtues of passivity, two students uncover the ghastly purpose behind their training and resolve to escape.', 2018, '102 min'),
(70, 'Love Don\'t Cost a Thing', 'A nerdy teen tries to make himself cool by association when he convinces a popular cheerleader to pose as his girlfriend.', 2003, '101 min'),
(71, 'Love in a Puff', 'When the Hong Kong government enacts a ban on smoking cigarettes indoors, the new law drives hard-core smokers outside, facilitating unlikely connections.', 2010, '103 min'),
(72, 'Mars Attacks!', 'As flying saucers head for Earth, the president of the United States prepares to welcome alien visitors but soon learns they\'re not coming in peace.', 1996, '106 min'),
(73, 'Marshall', 'This biopic of Thurgood Marshall, the first Black U.S. Supreme Court justice, centers on his pivotal work in a sensational case as an NAACP lawyer.', 2017, '118 min'),
(74, 'My Boss\'s Daughter', 'A young man house-sits for his mean boss, hoping to use it as an opportunity to win the heart of the boss\'s daughter, on whom he\'s long had a crush.', 2003, '86 min'),
(75, 'Mystery Men', 'A team of far-from-super heroes try to earn respect by springing into action when brave and dashing Captain Amazing disappears.', 1999, '121 min'),
(76, 'Open Season 2', 'Elliot the buck and his forest-dwelling cohorts must rescue their dachshund pal from some spoiled pets bent on returning him to domesticity.', 2008, '76 min'),
(77, 'Osmosis Jones', 'Peter and Bobby Farrelly outdo themselves with this partially animated tale about an out-of-shape 40-year-old man who\'s the host to various organisms.', 2001, '95 min'),
(78, 'Poseidon', 'A tidal wave spells disaster for a ship of New Year\'s Eve revelers when it capsizes the mammoth vessel, sending passengers into a battle for survival.', 2006, '98 min'),
(79, 'Rhyme & Reason', 'The world and culture of rap song topics such as race, violence, police, family and sex are examined by hip-hop performers from both coasts.', 1997, '89 min'),
(80, 'School of Rock', 'Musician Dewey Finn gets a job as a fourth-grade substitute teacher, where he secretly begins teaching his students the finer points of rock \'n\' roll.', 2003, '110 min'),
(81, 'Snervous Tyler Oakley', 'The inspiring Internet star and LGBT advocate shares an intimate view of his life and relationships during his international \"Slumber Party\" tour.', 2015, '83 min'),
(82, 'Tears of the Sun', 'A Navy SEAL is sent to a war-torn African jungle to rescue a doctor, only to realize he must also save the refugees in the physician\'s care.', 2003, '121 min'),
(83, 'The Blue Lagoon', 'Two shipwrecked children, stranded for years on a deserted island, fall in love as teenagers and attempt to forge a life in the isolated paradise.', 1980, '105 min'),
(84, 'The Golden Child', 'A fast-talking L.A. social worker goes through a series of traps and terrors to find a kidnapped Tibetan child with mystical powers.', 1986, '94 min'),
(85, 'The Guns of Navarone', 'During World War II, British forces launch an attack designed to take out the massive Nazi cannons that guard a critical sea channel.', 1961, '156 min'),
(86, 'The Interview', 'Seth Rogen and James Franco star in this provocative comedy about two journalists recruited by the CIA after they arrange an interview with Kim Jong-un.', 2014, '112 min'),
(87, 'The Nutty Professor', 'After being made fun of for his weight, a kind and brainy professor takes a dose of a revolutionary formula that changes more than just his appearance.', 1996, '95 min'),
(88, 'In the Line of Fire', 'A twisted yet ingenious killer torments a veteran Secret Service agent who\'s haunted by his failure years ago to save President John F. Kennedy.', 1993, '129 min'),
(89, 'Untold: Crime & Penalties', 'They were the bad boys of hockey — a team bought by a man with mob ties, run by his 17-year-old son, and with a rep for being as violent as they were good.', 2021, '86 min'),
(90, '2 Alone in Paris', 'A bumbling Paris policeman is doggedly determined to capture the master thief that repeatedly eludes him, even when they\'re the last two men on Earth.', 2008, '97 min'),
(91, 'Thimmarusu', 'Eight years after a young man is framed for murder, an up-and-coming lawyer re-opens the case, beginning a tricky mission to find the real culprit.', 2021, '125 min'),
(92, 'Wind River', 'A tracker with the U.S. Fish and Wildlife Service assists a rookie FBI agent who\'s investigating a teen girl\'s murder on a remote Wyoming reservation.', 2017, '107 min'),
(93, 'C Kkompany', 'Three broke friends pose as an underworld gang for extortion, but their plan takes on a life of its own when their phony company becomes famous.', 2008, '127 min'),
(94, 'He\'s All That', 'An influencer specializing in makeovers bets she can transform an unpopular classmate into prom king in this remake of the teen classic \"She\'s All That.\"', 2021, '92 min'),
(95, 'Koi Aap Sa', 'Star athlete Rohan has his eye on a beautiful art student. But when his best friend Simran experiences a crisis, he drops everything to help her.', 2006, '135 min'),
(96, 'Krishna Cottage', 'True love is put to the test when another woman comes between a pair of star-crossed young lovers in this thriller.', 2004, '124 min'),
(97, 'Kucch To Hai', 'A student tries to steal a test from a teacher\'s home, leaving him for dead after an accident. A string of murders may be the professor\'s revenge.', 2003, '136 min'),
(98, 'Kyaa Kool Hai Hum', 'Longtime friends Rahul and Karan head to Mumbai intent on making their dreams come true, but both men are suddenly saddled with bad luck.', 2005, '165 min'),
(99, 'Kyaa Kool Hain Hum 3', 'When an unlikely porn actor falls for a woman outside the industry, he employs his co-stars as a stand-in traditional family to impress her father.', 2016, '124 min'),
(100, 'Kyaa Super Kool Hain Hum', 'An aspiring actor and a struggling DJ team up to pursue the ladies they love and a diamond that rightfully belongs to their oversexed dog.', 2012, '136 min'),
(101, 'LSD: Love, Sex Aur Dhokha', 'This provocative drama examines how the voyeuristic nature of modern society affects three unusual couples in Northern India.', 2010, '112 min'),
(102, 'Ragini MMS', 'A couple out to have a sensuous weekend at a house outside of Mumbai finds it rigged with surveillance cameras and occupied by an evil entity.', 2011, '93 min'),
(103, 'Ragini MMS 2', 'The horror continues when Ragini\'s video goes viral and a sleazy director decides to make a movie about the incident in the original house.', 2014, '113 min'),
(104, 'Shootout at Lokhandwala', 'Based on a true story, this action film follows an incident that stunned a nation in the early 1990s. In Mumbai, India, the notorious gangster Maya holds off veteran cop Khan and a force of more than 200 policemen in a six-hour bloody gunfight.', 2007, '116 min'),
(105, 'Shor In the City', 'When three small-time Mumbai crooks steal a bag on a train, they find that it\'s filled with weapons and realize that their lives may be in danger.', 2011, '106 min'),
(106, 'The Dirty Picture', 'After running away from home in search of movie stardom, a village girl rises to become a prominent sex symbol.', 2011, '145 min'),
(107, 'Really Love', 'A rising Black painter tries to break into a competitive art world while balancing an unexpected romance with an ambitious law student.', 2020, '95 min'),
(108, 'The November Man', 'An ex-CIA agent emerges from retirement to protect an important witness, but he soon discovers that old friends can make the most dangerous enemies.', 2014, '108 min'),
(109, 'The Old Ways', 'A reporter visits her birthplace in Veracruz for a story about tribal culture, only to be kidnapped by locals who believe she\'s demonically possessed.', 2020, '90 min'),
(110, 'The River Runner', 'In this documentary, a kayaker sets out to become the first man to paddle the four great rivers that flow from Tibet\'s sacred Mount Kailash.', 2021, '86 min'),
(111, 'The Water Man', 'Desperate to save his ailing mother, 11-year-old Gunner runs away from home on a quest to find a mythic figure rumored to have the power to cheat death.', 2021, '92 min'),
(112, 'Count Me In', 'This documentary features some of rock\'s greatest drummers as they come together in an inspiring rhythmic journey about the power of human connection.', 2021, '82 min'),
(113, 'Untold: Caitlyn Jenner', 'Caitlyn Jenner\'s unlikely path to Olympic glory was inspirational. But her more challenging road to embracing her true self proved even more meaningful.', 2021, '70 min'),
(114, 'Boomika', 'Paranormal activity at a lush, abandoned property alarms a group eager to redevelop the site, but the eerie events may not be as unearthly as they think.', 2021, '122 min'),
(115, 'Boomika (Hindi)', 'Paranormal activity at a lush, abandoned property alarms a group eager to redevelop the site, but the eerie events may not be as unearthly as they think.', 2021, '122 min'),
(116, 'Boomika (Malayalam)', 'Paranormal activity at a lush, abandoned property alarms a group eager to redevelop the site, but the eerie events may not be as unearthly as they think.', 2021, '122 min'),
(117, 'Boomika (Telugu)', 'Paranormal activity at a lush, abandoned property alarms a group eager to redevelop the site, but the eerie events may not be as unearthly as they think.', 2021, '122 min'),
(118, 'Gunshot', 'After a clash at a protest ends in bloodshed, a forensic doctor and a journalist embark on a search for the elusive truth.', 2018, '96 min'),
(119, 'Man in Love', 'When he meets a debt-ridden woman who\'s caring for her ailing father, a debt collector with a heart of gold sets out to win her love.', 2021, '115 min'),
(120, 'Sweet Girl', 'He lost the love of his life to a pharmaceutical company\'s greed. Now his daughter is without a mother, and he\'s without justice. For now.', 2021, '110 min'),
(121, 'The Loud House Movie', 'With his parents and all 10 sisters in tow, Lincoln Loud heads to Scotland and learns that royalty runs in the family in this global musical journey!', 2021, '88 min'),
(122, 'A Faraway Land', 'In the Faroe Islands, a married woman meets a reporter filming a documentary on overseas Filipino workers, which soon sparks a complicated love story.', 2020, '102 min'),
(123, 'Like Crazy', 'A spirited exchange student and an American man fall in love in LA. But their romance faces an uphill battle when she is forced to return to England.', 2011, '91 min'),
(124, 'Black Island', 'The dark secrets of a seemingly peaceful island threaten to swallow up an orphaned student when he grows close to a mysterious new teacher.', 2021, '105 min'),
(125, 'Bombay', 'Years after they elope against their family’s wishes, a couple and their sons become caught in the throes of conflict in their city.', 1995, '135 min'),
(126, 'Nneka The Pretty Serpent', 'Years after the mysterious murder of her parents, a traumatized woman gains supernatural powers that aid in her quest for revenge against the killers.', 2020, '140 min'),
(127, 'O Kadhal Kanmani', 'It\'s love at first sight for a young couple repelled by the concept of marriage, but their view shifts when they meet their older-couple neighbors.', 2015, '133 min'),
(128, 'Out of my league', 'Tired of swiping through the dating scene with her besties in tow, a charming yet awkward woman with a rare genetic disorder sets her sights on love.', 2020, '92 min'),
(129, 'Pahuna', 'Fleeing unrest in their native Nepal, three young siblings separated from their parents must beat the odds to survive on their own in India.', 2018, '82 min'),
(130, 'Walk of Shame', 'After a night of boozy carousing, a local newscaster finds herself stranded on the streets of LA just hours before the job interview of a lifetime.', 2014, '94 min'),
(131, 'The Piano', 'With her daughter and her beloved piano in tow, a mute Scottish woman arrives in New Zealand, where a gruff loner sets out to seduce her.', 1993, '121 min'),
(132, 'Unroyal', 'When a rude and arrogant princess finds herself relying on a gatekeeper after a terrible accident, it may be enough to make her change her ways.', 2020, '97 min'),
(133, 'Beckett', 'An American tourist goes on the run in Greece after a tragic accident plunges him into a political conspiracy that makes him a target for assassination.', 2021, '110 min'),
(134, 'Brother Jekwu', 'Chasing wild success, a village hustler follows his cousin from Nigeria to Kenya and stumbles into the shady business affairs of a notorious overlord.', 2016, '101 min'),
(135, 'La diosa del asfalto', 'A woman from a tough neighborhood outside Mexico City comes home a rock star, inadvertently provoking a confrontation with the ghosts of her past.', 2020, '127 min'),
(136, 'Misha and the Wolves', 'As a little girl, she escaped the Holocaust and lived among wolves. Or did she? An extraordinary story elicits sympathy — then draws scrutiny.', 2021, '90 min');

-- --------------------------------------------------------

--
-- Structure de la table `Genre`
--

CREATE TABLE `Genre` (
  `id_g` int(15) NOT NULL,
  `genre` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `genres2films`
--

CREATE TABLE `genres2films` (
  `id_g` int(15) NOT NULL,
  `id_f` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `joue`
--

CREATE TABLE `joue` (
  `id_p` int(15) NOT NULL,
  `id_f` int(15) NOT NULL,
  `personnage` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `note`
--

CREATE TABLE `note` (
  `id_u` int(15) NOT NULL,
  `id_f` int(15) NOT NULL,
  `note` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `note`
--

INSERT INTO `note` (`id_u`, `id_f`, `note`) VALUES
(1, 1, 7),
(1, 2, 8);

-- --------------------------------------------------------

--
-- Structure de la table `Personne`
--

CREATE TABLE `Personne` (
  `id_p` int(15) NOT NULL,
  `nom` varchar(25) NOT NULL,
  `prenom` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `Personne`
--

INSERT INTO `Personne` (`id_p`, `nom`, `prenom`) VALUES
(1, 'Cameron', 'James');

-- --------------------------------------------------------

--
-- Structure de la table `realise`
--

CREATE TABLE `realise` (
  `id_f` int(15) NOT NULL,
  `id_p` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `realise`
--

INSERT INTO `realise` (`id_f`, `id_p`) VALUES
(1, 1),
(2, 1);

-- --------------------------------------------------------

--
-- Structure de la table `Utilisateur`
--

CREATE TABLE `Utilisateur` (
  `id_u` int(15) NOT NULL, 
  `nom_u` varchar(25) NOT NULL,
  `prenom_u` varchar(25) NOT NULL,
  `admin` tinyint(1) NOT NULL,
  `login` varchar(25) NOT NULL,
  `mdp` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `Utilisateur`
--

INSERT INTO `Utilisateur` (`id_u`, `nom_u`, `prenom_u`, `admin`, `login`, `mdp`) VALUES
(1, 'Takhashi', 'Vincent', 1, 'azerty', 'azerty');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `Film`
--
ALTER TABLE `Film`
  ADD PRIMARY KEY (`id_f`);

--
-- Index pour la table `Genre`
--
ALTER TABLE `Genre`
  ADD PRIMARY KEY (`id_g`);

--
-- Index pour la table `genres2films`
--
ALTER TABLE `genres2films`
  ADD PRIMARY KEY (`id_g`,`id_f`);

--
-- Index pour la table `note`
--
ALTER TABLE `note`
  ADD PRIMARY KEY (`id_u`,`id_f`);

--
-- Index pour la table `Personne`
--
ALTER TABLE `Personne`
  ADD PRIMARY KEY (`id_p`);

--
-- Index pour la table `Utilisateur`
--
ALTER TABLE `Utilisateur`
  ADD PRIMARY KEY (`id_u`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `Film`
--
ALTER TABLE `Film`
  MODIFY `id_f` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=137;

--
-- AUTO_INCREMENT pour la table `Genre`
--
ALTER TABLE `Genre`
  MODIFY `id_g` int(15) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `Personne`
--
ALTER TABLE `Personne`
  MODIFY `id_p` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `Utilisateur`
--
ALTER TABLE `Utilisateur`
  MODIFY `id_u` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
