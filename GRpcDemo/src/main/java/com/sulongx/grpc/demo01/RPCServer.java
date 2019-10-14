package com.sulongx.grpc.demo01;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.util.logging.Logger;

/**
 * RPC服务端
 * @author Sulongx
 */
public class RPCServer {

    static class GreeterImpl {

    }

    //日志
    private Logger logger = Logger.getLogger(RPCServer.class.getName());

    //端口
    private final Integer SERVER_PORT;
    //服务
    private Server server;


    public RPCServer(Integer port){
        this.SERVER_PORT = port;
    }


    /**
     * 启动服务
     */
    public void start(){
        server = ServerBuilder.forPort(SERVER_PORT)
                .addService(new GreeterImpl())
                .build()
                .start();
        logger.info("GRPCServer is starting,listening on :" + SERVER_PORT);
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                RPCServer.this.stop();
                System.err.println("GRPCServer is shutdown");
            }
        });
    }

    //关闭服务
    private void stop(){
        if(server != null){
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if(server != null){
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final RPCServer server = new RPCServer(9527);
        server.start();
        server.blockUntilShutdown();
    }

}
