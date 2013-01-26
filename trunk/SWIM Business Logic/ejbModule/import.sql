INSERT INTO `administrator` (`email`, `password`, `username`) VALUES ('admin@admin.it', '21232f297a57a5a743894a0e4a801fc3', 'admin');
INSERT INTO `swimdb`.`ability` (`name`, `description`, `isStub`) VALUES ('carpentiere', 'mette i mattoni', 0);
INSERT INTO `swimdb`.`ability` (`name`, `description`, `isStub`) VALUES ('parrucchiere', 'mette a posto i capelli', 0);
INSERT INTO `swimdb`.`abuse` (`id`, `descriprion`, `email`, `handled`) VALUES ('1', 'mi hai rotto le palle', 'prova@prova.it', false);
INSERT INTO `swimdb`.`user` (`id`, `birthdate`, `email`, `gender`, `firstname`, `surname`, `password`, `status`) VALUES ('1', '2012-12-02', 'bassi.paolo@user.it', '0', 'paolo', 'bassi', '21232f297a57a5a743894a0e4a801fc3', '1');
INSERT INTO `swimdb`.`administrator` (`id`, `email`, `password`, `username`) VALUES ('2', 'paolo@admin.it', '21232f297a57a5a743894a0e4a801fc3', 'paolo');
INSERT INTO `swimdb`.`abuse` (`id`, `descriprion`, `email`, `handled`) VALUES ('2', 'mi sono rotto i coglioni', 'paolo@bassi.it', 0);
INSERT INTO `swimdb`.`ability_user` (`abilities_name`, `users_id`, `Ability_name`, `subscribers_id`) VALUES ('carpentiere', '1', 'parrucchiere', '1');




