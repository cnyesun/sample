package com.yesun.sample.rmi;

import java.rmi.RemoteException;

public interface IRmiRemote {
	
	String CallRemote(String client) throws RemoteException;

}
