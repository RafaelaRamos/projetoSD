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
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {
       System.out.println("Servico de envio Inicializado...");

		//inicializar o serviço para sender
		Server serverSender = ServerBuilder.forPort(10999)
				.addService(new SenderServiceImpl())
				.build();
		serverSender.start();
		serverSender.awaitTermination();
	}

    }
    

