package it.polimi.ingsw2.swim.session.remote;

import it.polimi.ingsw2.swim.entities.Ability;

import java.util.List;

public interface AbilitySearchRemote {

	List<Ability> findAbility(String name);

}
