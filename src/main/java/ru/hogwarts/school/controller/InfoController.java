package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.IntStream;
import java.util.stream.Stream;

@RestController
@RequestMapping("/info")
public class InfoController {

    @Value("${server.port}")
    private int port;
    @GetMapping("/getPort")
    public int getPort(){
        return port;
    }

    @GetMapping("/get-sum1")
    public String getSum1(){
        long startTime = System.currentTimeMillis();

        int sum = Stream.iterate(1, a -> a + 1) .
                limit(1_000_000) .
                reduce(0, (a, b) -> a + b );

        long finishTime = System.currentTimeMillis();

        return "Количество времени:  " + (finishTime-startTime) + "мс." + " потраченное на вычисление суммы = " + sum;
    }


    @GetMapping("/get-sum2")
    public String getSum2(){
        long startTime = System.currentTimeMillis();

        int sum = IntStream.
                iterate(1, a -> a + 1) .
                limit(1_000_000) .
                sum();

        long finishTime = System.currentTimeMillis();

        return "Количество времени:  " + (finishTime-startTime) + "мс." + " потраченное на вычисление суммы = " + sum;
    }


    @GetMapping("/get-sum3")
    public String getSum3(){
        long startTime = System.currentTimeMillis();

        int sum = 0;
        for (int i = 0; i < 1000000; ++i) {
            sum += i;
        }

        long finishTime = System.currentTimeMillis();

        return "Количество времени:  " + (finishTime-startTime) + "мс." + " потраченное на вычисление суммы = " + sum;
    }

}
