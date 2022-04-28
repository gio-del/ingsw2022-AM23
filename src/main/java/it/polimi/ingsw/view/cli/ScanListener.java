package it.polimi.ingsw.view.cli;

import java.util.Scanner;

public class ScanListener extends Thread {
    private Request request = Request.IGNORE;
    private final Cli cli;
    private final Scanner scanner;
    private boolean running = true;

    public ScanListener(Cli cli){
        this.cli = cli;
        scanner = new Scanner(System.in);
    }

    @Override
    public void run(){
        while(running){
            if(scanner.hasNextLine()){
                switch (request){
                    case IP -> {
                        request = Request.IGNORE;
                        cli.checkIP(scanner.nextLine());
                    }
                    case NICKNAME -> {
                        request = Request.IGNORE;
                        cli.checkNickName(scanner.nextLine());
                    }
                    case GAME_MODE -> {
                        request = Request.IGNORE;
                        cli.checkGameMode(scanner.nextLine());
                    }
                }
            }
        }
    }

    public void setRequest(Request request){
        this.request = request;
    }

    /**
     * Converts the String into a number
     * @param value
     * @return
     */
    public int converterToInt(String value){
        int number;
        try {
            number = Integer.parseInt(value);
            return number;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

}