/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015
 * Автор: Снаров И.А.
 */
package pricemerger.core;

import java.io.Serializable;

/**
 * Класс, содержащий информацию о том, как прошла процедура слияния. Экземпляр класса создается на сервере и принимается клиентом
 *
 * @author snarov
 */
public class Status implements Serializable {

	enum State {

		FINISHED,
		CANCELLED,
		ERROR
	}

	private State state;
	private String errMsg;

	//Сколько позицией слито, добавлено, конфликтует
	private int merged;
	private int added;
	private int conflicts;

	//Сколько предложений обновлено, добавлено
	private int offersRefreshed;
	private int offersAdded;

	/**
	 * @return the state
	 */
	public State getState() {
		return state;
	}

	/**
	 * @return the errMsg
	 */
	public String getErrMsg() {
		return errMsg;
	}

	/**
	 * @return the merged
	 */
	public int getMerged() {
		return merged;
	}

	/**
	 * @return the added
	 */
	public int getAdded() {
		return added;
	}

	/**
	 * @return the conflicts
	 */
	public int getConflicts() {
		return conflicts;
	}

	/**
	 * @return the offersRefreshed
	 */
	public int getOffersRefreshed() {
		return offersRefreshed;
	}

	/**
	 * @return the offersAdded
	 */
	public int getOffersAdded() {
		return offersAdded;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(State state) {
		this.state = state;
	}

	/**
	 * @param errMsg the errMsg to set
	 */
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	/**
	 * @param merged the merged to set
	 */
	public void setMerged(int merged) {
		this.merged = merged;
	}

	/**
	 * @param added the added to set
	 */
	public void setAdded(int added) {
		this.added = added;
	}

	/**
	 * @param conflicts the conflicts to set
	 */
	public void setConflicts(int conflicts) {
		this.conflicts = conflicts;
	}

	/**
	 * @param offersRefreshed the offersRefreshed to set
	 */
	public void setOffersRefreshed(int offersRefreshed) {
		this.offersRefreshed = offersRefreshed;
	}

	/**
	 * @param offersAdded the offersAdded to set
	 */
	public void setOffersAdded(int offersAdded) {
		this.offersAdded = offersAdded;
	}

}
