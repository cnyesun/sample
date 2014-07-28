package com.yesun.sample.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RmiRemoteHello extends UnicastRemoteObject implements IRmiRemote {

	protected RmiRemoteHello() throws RemoteException {
		//super();
	}

	private static final long serialVersionUID = 1L;

	@Override
	public String CallRemote(String client) throws RemoteException {
		return "hi, " + client + ", i am yesun";
	}
	

}
