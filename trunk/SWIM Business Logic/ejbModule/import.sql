INSERT INTO `swimdb`.`administrator` (`email`, `password`, `username`) VALUES ('admin1@admin.it', '21232f297a57a5a743894a0e4a801fc3', 'admin');
INSERT INTO `swimdb`.`administrator` (`id`, `email`, `password`, `username`) VALUES ('2', 'admin2@admin.it', 'af37fe3a18f9f542caa335cd96d84da5', 'admin2');

INSERT INTO `swimdb`.`user` (`id`, `birthdate`, `email`, `gender`, `firstname`, `surname`, `password`, `city`, `province`, `street`, `streetNumber`, `zip`, `evaluation`, `evaluationCount`, `mobile`, `skype`, `status`, `telephone`) VALUES ('1', '1990-12-12', 'utente1@mail.com', '0', 'Name1', 'Surname1', '15888f173cd7e92aff952c26b831dd3d', 'Milano', 'Milano', 'Garibaldi', '12', '20100', '2.5', '10', '3201023738', 'utente1@mail.it', '1', '0371236584');
INSERT INTO `swimdb`.`user` (`id`, `birthdate`, `email`, `gender`, `firstname`, `surname`, `password`, `city`, `province`, `street`, `streetNumber`, `zip`, `evaluation`, `evaluationCount`, `mobile`, `skype`, `status`, `telephone`) VALUES ('2', '1976-11-23', 'utente2@mail.com', '1', 'Name2', 'Surname2', '2882854ae011e792634b7904a910a0f8', 'Roma', 'Roma', 'Rose', '34', '21324', '1', '4', '3262345754', 'utente2@mail.it', '0', '0371562356');
INSERT INTO `swimdb`.`user` (`id`, `birthdate`, `email`, `gender`, `firstname`, `surname`, `password`, `city`, `province`, `street`, `streetNumber`, `zip`, `evaluation`, `evaluationCount`, `mobile`, `skype`, `status`, `telephone`) VALUES ('3', '1991-11-11', 'utente3@mail.com', '1', 'Name3', 'Surname3', '74ad50605a2b9f7533ecebe0215482ef', 'Napoli', 'Napoli', 'Platani', '11', '12343', '4', '11', '3292354235', 'utente3@mail.it', '2', '0371234754');
INSERT INTO `swimdb`.`user` (`id`, `birthdate`, `email`, `gender`, `firstname`, `surname`, `password`, `city`, `province`, `street`, `streetNumber`, `zip`, `evaluation`, `evaluationCount`, `mobile`, `skype`, `status`, `telephone`) VALUES ('4', '1991-11-12', 'utente4@mail.com', '0', 'Name4', 'Surname4', '74ad50605a2b9f7533ecebe0215482ef', 'Genova', 'Genova', 'Olmi', '13', '12343', '4', '11', '3292354235', 'utente4@mail.it', '1', '0371224754');
INSERT INTO `swimdb`.`user` (`id`, `birthdate`, `email`, `gender`, `firstname`, `surname`, `password`, `city`, `province`, `street`, `streetNumber`, `zip`, `evaluation`, `evaluationCount`, `mobile`, `skype`, `status`, `telephone`) VALUES ('5', '1991-11-09', 'utente5@mail.com', '1', 'Name5', 'Surname5', '74ad50605a2b9f7533ecebe0215482ef', 'Palermo', 'Palermo', 'Margherite', '15', '12343', '4', '20', '3292354235', 'utente5@mail.it', '1', '0333234754');


INSERT INTO `swimdb`.`ability` (`name`, `description`, `isStub`) VALUES ('ESEMPIO: Carpentiere', 'ESEMPIO: si diletta in lavori manuali', 0);
INSERT INTO `swimdb`.`ability` (`name`, `description`, `isStub`) VALUES ('ESEMPIO: Parrucchiere', 'ESEMPIO: cura i capelli delle persone', 0);
INSERT INTO `swimdb`.`ability` (`name`, `description`, `isStub`) VALUES ('ESEMPIO: Acconciatore', 'ESEMPIO: crea acconciature per persone', 1);

INSERT INTO `swimdb`.`abuse` (`id`, `descriprion`, `email`, `handled`) VALUES ('1', 'ESEMPIO: Ho ricevuto una richiesta volgare da parte di utente1', 'prova@prova.it', false);
INSERT INTO `swimdb`.`abuse` (`id`, `descriprion`, `email`, `handled`) VALUES ('2', 'ESEMPIO: Testo di esempio per un abuso già gestito', 'esempio@abuso.it', true);
INSERT INTO `swimdb`.`abuse` (`id`, `descriprion`, `email`, `handled`) VALUES ('3', 'ESEMPIO: Testo di esempio per un abuso non già gestito', 'esempio2@abuso.it', false);

INSERT INTO `swimdb`.`ability_user` (`abilities_name`, `users_id`, `Ability_name`, `subscribers_id`) VALUES ('ESEMPIO: Carpentiere', '1', 'ESEMPIO: Carpentiere', '1');
INSERT INTO `swimdb`.`ability_user` (`abilities_name`, `users_id`, `Ability_name`, `subscribers_id`) VALUES ('ESEMPIO: Acconciatore', '1', 'ESEMPIO: Acconciatore', '1');
INSERT INTO `swimdb`.`ability_user` (`abilities_name`, `users_id`, `Ability_name`, `subscribers_id`) VALUES ('ESEMPIO: Parrucchiere', '3', 'ESEMPIO: Parrucchiere', '3');
INSERT INTO `swimdb`.`ability_user` (`abilities_name`, `users_id`, `Ability_name`, `subscribers_id`) VALUES ('ESEMPIO: Carpentiere', '4', 'ESEMPIO: Carpentiere', '4');
INSERT INTO `swimdb`.`ability_user` (`abilities_name`, `users_id`, `Ability_name`, `subscribers_id`) VALUES ('ESEMPIO: Parrucchiere', '5', 'ESEMPIO: Parrucchiere', '5');

INSERT INTO `swimdb`.`alias` (`name`, `ability_name`) VALUES ('muratore', 'ESEMPIO: Carpentiere');
INSERT INTO `swimdb`.`alias` (`name`, `ability_name`) VALUES ('parrucchiera', 'ESEMPIO: Parrucchiere');
INSERT INTO `swimdb`.`alias` (`name`, `ability_name`) VALUES ('barbiere', 'ESEMPIO: Parrucchiere');

INSERT INTO `swimdb`.`notification` (`id`, `description`, `readByUser`, `timestamp`, `addressee_id`) VALUES ('1', 'ESEMPIO: Testo di richiesta di aiuto in attesa', false, '2013-01-25', '1');
INSERT INTO `swimdb`.`notification` (`id`, `description`, `readByUser`, `timestamp`, `addressee_id`) VALUES ('2', 'ESEMPIO: Testo di richiesta di aiuto accettata', true, '2013-01-01', '1');
INSERT INTO `swimdb`.`notification` (`id`, `description`, `readByUser`, `timestamp`, `addressee_id`) VALUES ('3', 'ESEMPIO: Testo di richiesta di aiuto conclusa', true, '2012-12-01', '1');
INSERT INTO `swimdb`.`notification` (`id`, `description`, `readByUser`, `timestamp`, `addressee_id`) VALUES ('4', 'ESEMPIO: Testo di messaggio di risposta A', true, '2013-01-02', '4');
INSERT INTO `swimdb`.`notification` (`id`, `description`, `readByUser`, `timestamp`, `addressee_id`) VALUES ('5', 'ESEMPIO: Testo di messaggio di risposta B', true, '2013-01-03', '1');
INSERT INTO `swimdb`.`notification` (`id`, `description`, `readByUser`, `timestamp`, `addressee_id`) VALUES ('6', 'ESEMPIO: Testo di messaggio di risposta C', true, '2013-01-04', '4');
INSERT INTO `swimdb`.`notification` (`id`, `description`, `readByUser`, `timestamp`, `addressee_id`) VALUES ('7', 'ESEMPIO: Richiesta di amicizia', false, '2013-01-25', '1');
INSERT INTO `swimdb`.`notification` (`id`, `description`, `readByUser`, `timestamp`, `addressee_id`) VALUES ('8', 'ESEMPIO: Notifica da parte del team amministrativo', false, '2013-01-26', '1');

INSERT INTO `swimdb`.`friendshiprequest` (`id`, `sender_id`) VALUES ('7', '5');

INSERT INTO `swimdb`.`help` (`helpedFeedback`, `helperFeedback`, `state`, `id`, `ability_name`, `sender_id`) VALUES (null, null, '0', '1', 'ESEMPIO: Carpentiere', '4');
INSERT INTO `swimdb`.`help` (`helpedFeedback`, `helperFeedback`, `state`, `id`, `ability_name`, `sender_id`) VALUES (null, null, '1', '2', 'ESEMPIO: Carpentiere', '3');
INSERT INTO `swimdb`.`help` (`helpedFeedback`, `helperFeedback`, `state`, `id`, `ability_name`, `sender_id`) VALUES ('4', '5', '2', '3', 'ESEMPIO: Carpentiere', '4');

INSERT INTO `swimdb`.`message` (`id`, `helpRelation_id`, `sender_id`) VALUES ('4', '1', '1');
INSERT INTO `swimdb`.`message` (`id`, `helpRelation_id`, `sender_id`) VALUES ('5', '1', '4');
INSERT INTO `swimdb`.`message` (`id`, `helpRelation_id`, `sender_id`) VALUES ('6', '1', '1');

INSERT INTO `swimdb`.`user_user` (`User_id`, `friendships_id`) VALUES ('1', '4');
INSERT INTO `swimdb`.`user_user` (`User_id`, `friendships_id`) VALUES ('4', '1');
INSERT INTO `swimdb`.`user_user` (`User_id`, `friendships_id`) VALUES ('1', '3');
INSERT INTO `swimdb`.`user_user` (`User_id`, `friendships_id`) VALUES ('3', '1');