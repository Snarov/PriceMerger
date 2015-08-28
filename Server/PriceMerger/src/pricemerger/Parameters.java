/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015
 * Автор: Снаров И.А.
 */
package pricemerger;

import com.beust.jcommander.Parameter;

/**
 *
 * @author snarov
 */
public class Parameters {

	@Parameter(names = {"-h", "--help"}, help = true)
	private boolean help;

	@Parameter(names = {"-r", "--remote"}, description = "указывает на то, что программа работает с удаленным клиентом. "
			+ "Не использовать эту опцию из командной строки! Опция используется диспетчером подключений для организации работы"
			+ " с удаленными клиентами")
	private boolean remote = false;

	/**
	 * @return the help
	 */
	public boolean isHelp() {
		return help;
	}

	/**
	 * @return the remote
	 */
	public boolean isRemote() {
		return remote;
	}
}
