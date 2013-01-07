/**
 * 
 */
package it.polimi.ingsw2.swim.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.validator.AssertTrue;
import org.hibernate.validator.Max;
import org.hibernate.validator.Min;
import org.hibernate.validator.NotNull;

/**
 * @author Administrator
 *
 */
@Entity
@SequenceGenerator(name="HELP_SEQUENCE")
public class Helps implements Serializable {

	/**
	 * @author Administrator
	 *
	 */
	enum State {
		REQUESTED,
		ACCEPTED,
		CLOSED;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -915761807832799403L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HELP_SEQUENCE")
	private Long id;
	
	@Min(value=0)
	@Max(value=5)
	private Integer helperFeedback = null;
	
	@Min(value=0)
	@Max(value=5)
	private Integer helpedFeedback = null;

	@Enumerated
	@NotNull
	private State state = State.REQUESTED;
	
	@ManyToOne
	@NotNull
	private Ability ability;
	
	@OneToOne
	@NotNull
	private HelpRequest helpRequest;
	
	@OneToMany(mappedBy="helpRelation")
	private List<Message> conversation = new ArrayList<Message>();
	
	public Helps(){
		super();
	}

	/**
	 * @return the helperFeedback
	 */
	Integer getHelperFeedback() {
		return helperFeedback;
	}

	/**
	 * @param helperFeedback the helperFeedback to set
	 */
	void setHelperFeedback(Integer helperFeedback) {
		if(this.helperFeedback != null){
			throw new IllegalStateException();
		}
		if(helperFeedback == null){
			throw new IllegalArgumentException();
		}
		if(this.helpedFeedback != null){
			close();
		}
		this.helperFeedback = helperFeedback;
	}

	/**
	 * @return the helpedFeedback
	 */
	Integer getHelpedFeedback() {
		return helpedFeedback;
	}

	/**
	 * @param helpedFeedback the helpedFeedback to set
	 */
	void setHelpedFeedback(Integer helpedFeedback) {
		if(this.helpedFeedback != null){
			throw new IllegalStateException();
		}
		if(helpedFeedback == null){
			throw new IllegalArgumentException();
		}
		if(this.helperFeedback != null){
			close();
		}
		this.helpedFeedback = helpedFeedback;
	}

	/**
	 * @return the id
	 */
	Long getId() {
		return id;
	}

	/**
	 * @return the state
	 */
	State getState() {
		return state;
	}
	
	void accept(){
		if(this.state != State.REQUESTED){
			throw new IllegalStateException();
		}
		this.state = State.ACCEPTED;
	}
	
	void close(){
		if(this.state != State.ACCEPTED){
			throw new IllegalStateException();
		}
		this.state = State.CLOSED;
	}
	
	@AssertTrue
	boolean checkState(){
		if(this.helperFeedback != null && this.helpedFeedback != null){
			if(this.state!=State.CLOSED){
				return false;
			}
		}
		if(this.helperFeedback == null || this.helpedFeedback == null){
			if(this.state==State.CLOSED){
				return false;
			}
		}
		return true;
	}
	

}
