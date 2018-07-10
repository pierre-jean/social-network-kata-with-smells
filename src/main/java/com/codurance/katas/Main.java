package com.codurance.katas;

public class Main {

    public static void main(String[] args){
        SocialNetwork socialNetwork = new SocialNetwork(new TerminalConsole(), new Clock());

        socialNetwork.execute();
    }
}
