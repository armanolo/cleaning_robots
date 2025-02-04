package com.vwdigitalhub.cleaningrobots;

import com.vwdigitalhub.cleaningrobots.infrastructure.ManageRobotHandler;

public class Main {

    public static void main(String[] args) {
        ManageRobotHandler manageRobotHandler = new ManageRobotHandler();
        if(args.length == 0){
            System.err.println("a proper input is needed");
            return;
        }

        String input = "";
        if(args.length == 1){
            input = args[0].replace("\\n","\n");
        }else{
            input = String.join("\n", args);
        }

        try {
            String result = manageRobotHandler.manage(input);
            System.out.println(result);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}