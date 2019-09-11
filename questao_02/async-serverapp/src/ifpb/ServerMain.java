/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;


public class ServerMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {
      ServerBuilder serverBuilder = ServerBuilder.forPort(10992);
		serverBuilder.addService(new ServerImp());
		Server server = serverBuilder.build();
		server.start();
	    System.out.println("Servidor inicializado");
	    server.awaitTermination();

    }
    
}
