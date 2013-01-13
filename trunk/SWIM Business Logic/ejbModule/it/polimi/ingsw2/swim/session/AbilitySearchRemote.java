package it.polimi.ingsw2.swim.session;

import it.polimi.ingsw2.swim.entities.Ability;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface AbilitySearchRemote {

	List<Ability> findAbility(String name);

}
