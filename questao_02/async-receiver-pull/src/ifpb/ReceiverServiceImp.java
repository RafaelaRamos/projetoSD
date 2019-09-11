/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb;

import ifpb.grpc.Requisicao;
import ifpb.grpc.Resposta;
import ifpb.grpc.ServiceGrpc;
import ifpb.grpc.ServiceGrpc.ServiceImplBase;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ReceiverServiceImp extends ServiceImplBase {
    	private final ManagedChannel channel;
	private ServiceGrpc.ServiceStub  receiver;
        private static int tentativa =3;

    public ReceiverServiceImp() {
        this.channel = ManagedChannelBuilder.forAddress("localhost", 10992)
							.usePlaintext()
							.build();
    }
    
    public void sendResponse(Requisicao request, StreamObserver<Resposta> responseObserver) {
                
            receiver =ServiceGrpc.newStub(channel);
            
            receiver.sendResponse(request, new StreamObserver<Resposta>() {

			private Resposta resposta;

			@Override
			public void onNext(Resposta res) {
				this.resposta = res;
			}

			@Override
			public void onError(Throwable throwable) {
                            if(tentativa!=0){
				 try {
                                     
                                Thread.sleep(2000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(ReceiverServiceImp.class.getName()).log(Level.SEVERE, null, ex);
                            }
				receiver.sendResponse(request, this);
			}
                            else{
                            
                            throwable.printStackTrace();
                            }
                            tentativa--;
                        }

			@Override
			public void onCompleted() {
				responseObserver.onNext(resposta);
				responseObserver.onCompleted();
			}
		});

    }

}
