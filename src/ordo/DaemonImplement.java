package ordo;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import formats.Format;
import map.Mapper;
import map.Reducer;
import map.MapReduce;

public class DaemonImplement extends UnicastRemoteObject implements Daemon {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;

	public DaemonImplement(String name) throws RemoteException {
		this.id = name;
	}

	public void runMap(Mapper m, Format reader, Format writer, CallBack cb) throws RemoteException {
		reader.open(Format.OpenMode.R);
		writer.open(Format.OpenMode.W);
		m.map(reader, writer);
		reader.close();
		writer.close();

	}

	public void runReduce(Reducer r, Format reader, Format writer) throws RemoteException {
		reader.open(Format.OpenMode.R);
		writer.open(Format.OpenMode.W);
		reader.close();
		writer.close();
	}

	public static void main(String args[]) {
			int ports = new Integer(args[1]).intValue();
			String ServerName;
			
			try {
				try {
					LocateRegistry.createRegistry(ports);
				} catch (Exception e) {
					System.out.println("Erreur creation registre");
				}
							
				ServerName = args[0];
				DaemonImplement Daemon = new DaemonImplement(ServerName);
				String url = "//" + InetAddress.getLocalHost().getHostAddress() + ":" + ports + "/" + ServerName ;
				Naming.rebind(url,Daemon);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}


