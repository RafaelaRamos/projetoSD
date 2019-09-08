/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

/**
 *
 * @author rafaela
 */
public class ReceiverMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {
         System.out.println("Servico de recebimento inicializado ");

		
		Server serverSender = ServerBuilder.forPort(9941)
				.addService(new ReceiverServiceImp())
				.build();
		serverSender.start();
		serverSender.awaitTermination();
	}
    }
    

