package ordo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CallBack extends UnicastRemoteObject implements CallBackInterface {

	private static final long serialVersionUID = 1L;

	public CallBack() throws RemoteException {
	}
}
