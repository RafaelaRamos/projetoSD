/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.com.br.gRpc;

/**
 *
 * @author Cliente
 */
public class ServiceMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        	System.out.println("Inicializado o serviço de Sender");

		//inicializar o serviço para sender
		Server serverSender = ServerBuilder.forPort(10990)
				.addService(new SenderImpl())
				.build();
		serverSender.start();
		serverSender.awaitTermination();
	}
    }
    
}
