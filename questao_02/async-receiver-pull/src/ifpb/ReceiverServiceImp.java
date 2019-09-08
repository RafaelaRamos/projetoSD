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

/**
 *
 * @author rafaela
 */
public class ReceiverServiceImp extends ServiceImplBase {
    	private final ManagedChannel channel;
	private ServiceGrpc.ServiceStub  receiver;

    public ReceiverServiceImp() {
        this.channel = ManagedChannelBuilder.forAddress("localhost", 10992)
							.usePlaintext()
							.build();
    }
    
    public void sendResponse(Requisicao request, StreamObserver<Resposta> responseObserver) {
                
            receiver =ServiceGrpc.newStub(channel);
            
            receiver.sendResponse(request, new StreamObserver<Resposta>() {

			private Resposta result;

			@Override
			public void onNext(Resposta res) {
				this.result = res;
			}

			@Override
			public void onError(Throwable throwable) {
				responseObserver.onError(throwable);
			}

			@Override
			public void onCompleted() {
				responseObserver.onNext(result);
				responseObserver.onCompleted();
			}
		});

    }

}
