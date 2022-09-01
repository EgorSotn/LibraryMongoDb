package org.example.home.controller;

import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class ControllerMessengerIO implements ControllerMessenger{
    private final BufferedReader bufferedReader;
    private final PrintStream out;
    private static java.util.logging.Logger log = Logger.getLogger(ControllerMessengerIO.class.getName());
    public ControllerMessengerIO(){
        this.out = System.out;
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }
    @Override
    public void textMessage(String message) {
        out.println(message);
    }

    @Override
    public String getAsk(){
        String answer = "";
        try {
            answer = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            log.log(Level.SEVERE, String.valueOf(e));
        }
        return answer;
    }
}
