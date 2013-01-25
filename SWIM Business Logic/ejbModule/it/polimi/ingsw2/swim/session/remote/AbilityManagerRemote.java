package it.polimi.ingsw2.swim.session.remote;

import it.polimi.ingsw2.swim.entities.Ability;
import it.polimi.ingsw2.swim.exceptions.DuplicateAbilityException;
import it.polimi.ingsw2.swim.exceptions.NoSuchAbilityException;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

@Remote
public interface AbilityManagerRemote {

	void editAbilityName(String abilityId, String name)
			throws DuplicateAbilityException, NoSuchAbilityException;

	void editAbilityDescription(String abilityId, String description)
			throws DuplicateAbilityException, NoSuchAbilityException;

	void removeAbilityAlias(String abilityId, Set<String> aliasId)
			throws NoSuchAbilityException;

	void addAbilityAlias(String abilityId, Set<String> aliasId)
			throws NoSuchAbilityException;

	List<Ability> retriveAbilityList();

	List<Ability> retriveAbilityList(String searchKey);

	void deleteAbility(String abilityId) throws NoSuchAbilityException;

}
