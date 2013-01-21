package it.polimi.ingsw2.swim.session.remote;

import it.polimi.ingsw2.swim.entities.Ability;
import it.polimi.ingsw2.swim.exceptions.DuplicateAbilityException;
import it.polimi.ingsw2.swim.exceptions.DuplicateAliasException;
import it.polimi.ingsw2.swim.exceptions.NoSuchAbilityException;
import it.polimi.ingsw2.swim.exceptions.NoSuchRequestException;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

@Remote
public interface RequestManagerRemote {

	void addNewAbility(String name, String description, Set<String> alias,
			Set<String> stubAbilities) throws DuplicateAliasException,
			DuplicateAbilityException;

	void addAbilityAsAlias(String abilityId, String name,
			Set<String> stubAbilities) throws DuplicateAliasException,
			NoSuchAbilityException, DuplicateAbilityException;

	void deleteRequest(String abilityId) throws NoSuchRequestException;

	List<Ability> retriveRequestsList();

	List<Ability> retriveRequestsList(String searchKey);

}
