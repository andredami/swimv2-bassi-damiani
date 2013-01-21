package it.polimi.ingsw2.swim.session.remote;

import it.polimi.ingsw2.swim.entities.Abuse;
import it.polimi.ingsw2.swim.exceptions.AlreadyHandledException;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface AbuseManagerRemote {

	List<Abuse> getAbuseList();

	void markAbuseAsHandled(String abuseId) throws AlreadyHandledException;

	void removeAbuse(String abuseId);

}
