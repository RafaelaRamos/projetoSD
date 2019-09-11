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
import io.grpc.stub.StreamObserver;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {

   private static void sendAndResultMessage(String id, String text,ServiceGrpc.ServiceStub stub ) throws InterruptedException, ExecutionException{
                Requisicao req = Requisicao.newBuilder().setId(id).setText(text).build();
                System.out.println("mandando mensagem");
                stub.sendResponse(req, new StreamObserver<Resposta> () {

			private Resposta resultado;

			@Override
			public void onNext(Resposta res) {
				this.resultado = res;
			}

			@Override
			public void onError(Throwable throwable) {
				
                             System.out.println("Servidor nao recebeu sua  mensagem");
			}

			@Override
			public void onCompleted() {
				System.out.println("Mensagem recebida"+resultado.getHash());
			}
		});

    }
   
    //utilizando listenableFuture 
		/*while(true){
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
			}*/
		//}
  // }
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 10999).usePlaintext().build();
	ServiceGrpc.ServiceStub stub = ServiceGrpc.newStub(channel);
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
                                        sendAndResultMessage(ix, mx, stub);
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (ExecutionException ex) {
                                        Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                      
                                    } 
                                    };
                                   
				 t.start();	
				
			 };
                         while(true){
                             
                            
                         }


    }
    
    
}
			
		
                 
               
    
 
	
		
		


