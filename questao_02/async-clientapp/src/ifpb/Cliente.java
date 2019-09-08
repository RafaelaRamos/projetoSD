/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb;

import com.google.common.util.concurrent.ListenableFuture;
import ifpb.grpc.Requisicao;
import ifpb.grpc.Resposta;
import ifpb.grpc.ServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafaela
 */
public class Cliente {

   private static void sendAndResultMessage(String id, String text,ServiceGrpc.ServiceFutureStub futureStub ) throws InterruptedException, ExecutionException{
                Requisicao req = Requisicao.newBuilder().setId(id).setText(text).build();
		while(true){
			//
			Thread.sleep(2000);
			//
			System.out.println("Verificando se há resposta.");
			ListenableFuture<Resposta> fut = futureStub.sendResponse(req);
			if (fut == null) {
				continue;
			}
			else {
				System.out.println("Recebido resultado para mensagem "+ fut.get().getId() + fut.get().getHash());
				break;
			}
		}
   }
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 10999).usePlaintext().build();
	ServiceGrpc.ServiceFutureStub futureStub = ServiceGrpc.newFutureStub(channel);
        String id ="askjdlkasjd";
        String text ="Hello World";
      
      
		for (int i = 0; i < 100; i++){
			//
                        //id =String.valueOf(i);
			final String ix = id + "#" + i;
			final String mx = text + "#" + i;
			//
			Thread t = new Thread(){
				public void run() {
					
                                    try {
                                        sendAndResultMessage(ix, mx, futureStub);
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (ExecutionException ex) {
                                        Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                                    }
					
					
				};
			};
			t.start();
		}
    }
 
	
		
		
}

