/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javatest;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author snarov
 */
public class JavaTest {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws RemoteException, AlreadyBoundException {
		 Registry registry = LocateRegistry.getRegistry("127.0.0.1");
         registry.bind("Hello", new Remote(){});
	}
	
}