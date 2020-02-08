package com.klymenko.proxyservice.service;

import com.klymenko.proxyservice.thrift.Fibonacci;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FibonacciService {

    @Value("${fibonacci.server.address}")
    private String address;

    @Value("${fibonacci.server.port}")
    private Integer port;

    public List<Long> getFibonacci(Long number) {
        try(TTransport transport = new TSocket(address, port)) {
            transport.open();

            TProtocol protocol = new TBinaryProtocol(transport);
            Fibonacci.Client client = new Fibonacci.Client(protocol);

            return client.calculate(number);

        } catch (TException x) {
            throw new RuntimeException(x);
        }
    }

}
