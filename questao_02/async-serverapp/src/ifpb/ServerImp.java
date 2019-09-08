/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb;

import ifpb.grpc.Requisicao;
import ifpb.grpc.Resposta;
import ifpb.grpc.ServiceGrpc.ServiceImplBase;
import io.grpc.stub.StreamObserver;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerImp extends ServiceImplBase {

    @Override
    public void sendResponse(Requisicao request, StreamObserver<Resposta> responseObserver) {
        System.out.println(request);
        String id = request.getId();
        String text = request.getText();
        MessageDigest msd;

        try {
            msd = MessageDigest.getInstance("MD5");
            byte[] bhash = msd.digest(text.getBytes());
            BigInteger bi = new BigInteger(bhash);
            Resposta  builder = Resposta.newBuilder().setId(id).setHash(bi.toString()).build();
            System.out.print(builder);
            responseObserver.onNext(builder);
            responseObserver.onCompleted();

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ServerImp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
