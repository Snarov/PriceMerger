/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015
 * Автор: Снаров И.А.
 */
package pricemerger;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import pricemerger.core.IPriceMergerCore;
import pricemerger.core.PriceMergerCore;

/**
 * Программа, непосредственно производящая сопоставление и слияние прайсов. Создает, удаляет, обновляет записи в БД. Является
 * ядром всей программной системы управления прайсами. Управляется с помощью CLI интерфейса либо с помощью RMI для удаленных
 * клиентов.
 *
 * @author snarov
 */
public class PriceMerger {

	public static Parameters parameters = new Parameters();
	public static PriceMergerCore priceMergerCore;

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {

		JCommander jc = new JCommander(parameters);
		try {
			jc.parse(args);
		} catch (ParameterException ex) {
			jc.usage();
			System.exit(1);
		}

		if (parameters.isHelp()) {
			jc.usage();
		} else {
			priceMergerCore = new PriceMergerCore();

			if (parameters.isRemote()) {
				try {
					IPriceMergerCore priceMergerCoreStub = (IPriceMergerCore) UnicastRemoteObject.exportObject(priceMergerCore, 0);

					try (ObjectOutputStream oos = new ObjectOutputStream(System.out)) {
						oos.writeObject(priceMergerCoreStub);
					} catch (IOException ex) {
						Logger.getLogger(PriceMerger.class.getName()).log(Level.SEVERE, null, ex);
						System.err.println("Error sending core stub: " + ex.getMessage());
					}

				} catch (RemoteException ex) {
					Logger.getLogger(PriceMerger.class.getName()).log(Level.SEVERE, null, ex);
					System.err.println("Error exporting core stub: " + ex.getMessage());
				}
			}else{
				
			}
		}

	}

}
