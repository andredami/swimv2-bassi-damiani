package it.polimi.ingsw2.swim.entities;

import it.polimi.ingsw2.swim.exceptions.AlreadyActiveException;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;

/**
 * Represents an Ability {@link User}s can list in order to say they can help
 * other user in this application field.
 * 
 * @author Andrea Damiani
 * @category Entity
 * @serial
 * @see User
 */
@Entity
public class Ability implements Serializable {

	private static final long serialVersionUID = -214793458991333187L;

	/**
	 * Is the short name that uniquely identifies the {@link Ability}.
	 * 
	 * @category Primary Key
	 */
	@Id
	@NotEmpty
	@NotNull
	private String name;

	/**
	 * Is the complete description of the {@link Ability}. This field allows to
	 * better identify the {@link Ability} for humans.
	 */
	@Lob
	@NotEmpty
	@NotNull
	private String description;

	/**
	 * Signals whether the {@link Ability} is active or still only requested. If
	 * TRUE the {@link Ability} is Requested, thus not available to user
	 * listing. If FALSE the {@link Ability} is Active, thus all users can list
	 * it.
	 */
	private boolean isStub;

	/**
	 * Lists all the {@link User}s that have endorsed the {@link Ability}. All
	 * the {@link User}s listed want the {@link Ability} to be actively added to
	 * the bunch of available abilities.
	 * 
	 * @category Many-to-Many Relations
	 * @see User
	 */
	@ManyToMany
	private Set<User> subscribers;

	/**
	 * Lists all the {@link User}s that have declared to master the
	 * {@link Ability}.
	 * 
	 * @category Many-to-Many Relations
	 * @see User
	 */
	@ManyToMany
	private Set<User> users;

	/**
	 * List all the {@link Alias} the {@link Ability} have. This field avoids
	 * similar or identical abilities to be stored in separated entities. This
	 * will enhance the search experience among abilities.
	 * 
	 * @category Many-to-Many Relations
	 * @see Alias
	 */
	@OneToMany(mappedBy = "ability", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<Alias> alias;

	public Ability() {
		super();
		this.subscribers = new HashSet<User>();
		this.users = new HashSet<User>();
		this.alias = new HashSet<Alias>();
	}

	public Ability(String name, String description, Set<String> alias) {
		this();
		this.name = name;
		this.description = description;
		for (String a : alias) {
			this.alias.add(new Alias(a, this));
		}
		this.isStub = false;
	}

	/**
	 * Constructs a new {@link Ability} in Request state and conveniently
	 * register the first subscribers.
	 * 
	 * @param name
	 *            The name of the requested {@link Ability}.
	 * @param description
	 *            The description of the requested {@link Ability}.
	 * @param subscriber
	 *            The {@link User} who requested the ability.
	 */
	public Ability(String name, String description, User subscriber) {
		super();
		this.name = name;
		this.description = description;
		this.isStub = true;
		this.addSubscriber(subscriber);
	}

	String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}

	String getDescription() {
		return description;
	}

	void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Signals whether the {@link Ability} is active or still only requested. If
	 * TRUE the {@link Ability} is Requested, thus not available to user
	 * listing. If FALSE the {@link Ability} is Active, thus all users can list
	 * it.
	 */
	public Boolean isStub() {
		return isStub;
	}

	/**
	 * Makes the {@link Ability} to pass from Requested state to Active state.
	 * If called on an {@link Ability} that is still Active in the system, an
	 * {@link AlreadyActiveException} will be thrown.
	 * 
	 * @throws AlreadyActiveException
	 * 
	 */
	void confirm() throws AlreadyActiveException {
		if (this.isStub = false) {
			throw new AlreadyActiveException();
		}
		this.isStub = false;
	}

	Set<User> getSubscribers() {
		return subscribers;
	}

	boolean isSubscriber(User user) {
		return this.subscribers.contains(user);
	}

	public void addSubscriber(User subscriber) {
		this.subscribers.add(subscriber);
	}

	void clearSubscriber() {
		this.subscribers.clear();
	}

	Set<User> getUsers() {
		return users;
	}

	void addUser(User users) {
		this.users.add(users);
	}

	void removeUser(User user) {
		this.users.remove(user);
	}

	Set<Alias> getAlias() {
		return alias;
	}

	void addAlias(Alias alias) {
		this.alias.add(alias);
	}

	void addAlias(String alias) {
		this.alias.add(new Alias(alias, this));
	}

	void removeAlias(Alias alias) {
		this.alias.remove(alias);
	}

	void removeAlias(String alias) {
		for (Alias a : this.alias) {
			if (a.getName().equals(alias)) {
				this.alias.remove(a);
				return;
			}
		}
	}

	void clearAlias() {
		this.alias.clear();
	}
}
