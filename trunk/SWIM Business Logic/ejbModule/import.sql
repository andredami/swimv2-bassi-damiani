INSERT INTO `administrator` (`email`, `password`, `username`) VALUES ('admin1@admin.it', '21232f297a57a5a743894a0e4a801fc3', 'admin');
INSERT INTO `swimdb`.`administrator` (`id`, `email`, `password`, `username`) VALUES ('2', 'admin2@admin.it', 'af37fe3a18f9f542caa335cd96d84da5', 'admin2');
INSERT INTO `swimdb`.`ability` (`name`, `description`, `isStub`) VALUES ('Esempio: Carpentiere', 'Esempio: si diletta in lavori manuali', 0);
INSERT INTO `swimdb`.`ability` (`name`, `description`, `isStub`) VALUES ('Esempio 2: Parrucchiere', 'Esempio2 : cura i capelli delle persone', 0);
INSERT INTO `swimdb`.`ability` (`name`, `description`, `isStub`) VALUES ('Esempio 3: Acconciatore', 'Esempio3 : crea acconciature per persone', 1);
INSERT INTO `swimdb`.`abuse` (`id`, `descriprion`, `email`, `handled`) VALUES ('1', 'Ho ricevuto una richiesta volgare da parte di utente1', 'prova@prova.it', false);
INSERT INTO `swimdb`.`abuse` (`id`, `descriprion`, `email`, `handled`) VALUES ('2', 'mi sono rotto i coglioni', 'paolo@bassi.it', 0);
INSERT INTO `swimdb`.`ability_user` (`abilities_name`, `users_id`, `Ability_name`, `subscribers_id`) VALUES ('carpentiere', '1', 'parrucchiere', '1');

INSERT INTO `swimdb`.`user` (`id`, `birthdate`, `email`, `gender`, `firstname`, `surname`, `password`, `city`, `province`, `street`, `streetNumber`, `zip`, `evaluation`, `evaluationCount`, `mobile`, `skype`, `status`, `telephone`) VALUES ('1', '1990-12-12', 'utente1@mail.com', '0', 'Name1', 'Surname1', '15888f173cd7e92aff952c26b831dd3d', 'Milano', 'Milano', 'Garibaldi', '12', '20100', '2.5', '10', '3201023738', 'utente1@mail.it', '1', '0371236584');
INSERT INTO `swimdb`.`user` (`id`, `birthdate`, `email`, `gender`, `firstname`, `surname`, `password`, `city`, `province`, `street`, `streetNumber`, `zip`, `evaluation`, `evaluationCount`, `mobile`, `skype`, `status`, `telephone`) VALUES ('2', '1976-11-23', 'utente2@mail.com', '1', 'Name2', 'Surname2', '2882854ae011e792634b7904a910a0f8', 'Roma', 'Roma', 'Rose', '34', '21324', '1', '4', '3262345754', 'utente2@mail.it', '1', '0371562356');
INSERT INTO `swimdb`.`user` (`id`, `birthdate`, `email`, `gender`, `firstname`, `surname`, `password`, `city`, `province`, `street`, `streetNumber`, `zip`, `evaluation`, `evaluationCount`, `mobile`, `skype`, `status`, `telephone`) VALUES ('3', '1991-11-11', 'utente3@mail.com', '1', 'Name3', 'Surname3', '74ad50605a2b9f7533ecebe0215482ef', 'Napoli', 'Napoli', 'Platani', '11', '12343', '4', '11', '3292354235', 'utente3@mail.it', '1', '0371234754');



