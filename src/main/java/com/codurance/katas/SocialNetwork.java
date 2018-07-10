package com.codurance.katas;

import java.util.*;

public class SocialNetwork {

    private final Console console;
    private final Clock clock;

    private Map<String, List<String>> mpu = new HashMap<>();
    private Map<String, List<String>> fpu = new HashMap<>();
    private Map<String, Date> md = new HashMap<>();
    private Map<String, String> ma = new HashMap<>();
    String command = "";

    public SocialNetwork(Console console, Clock clock) {
        this.console = console;
        this.clock = clock;
    }

    public void execute() {
        command= console.readLineFromInput();
        while (!command.equals("exit")) {
            if (command.split(" -> ").length == 2) {
                if (mpu.get(command.split(" -> ")[0]) == null) {
                    mpu.put(command.split(" -> ")[0], new ArrayList<>());
                }
                mpu.get(command.split(" -> ")[0]).add(command.split(" -> ")[1]);
                md.put(command.split(" -> ")[1], clock.now());
                ma.put(command.split(" -> ")[1], command.split(" -> ")[0]);
            }
            else if (command.split(" follows ").length == 2){
               if (fpu.get(command.split(" follows ")[0]) == null){
                   fpu.put(command.split(" follows ")[0], new ArrayList<>());
               }
               fpu.get(command.split(" follows ")[0]).add(command.split(" follows ")[1]);
            }
            else if (command.contains("wall")){
                List<String> m = this.mpu.get(command.split(" wall")[0]);
                for (String f : fpu.get(command.split(" wall")[0])){
                    m.addAll(this.mpu.get(f));
                }
                Comparator<String> l = (Comparator<String>) (s, t1) -> md.get(t1).compareTo(md.get(s));
                Collections.sort(m, l);
                for (String e : m){
                    console.writeLineToOutput(String.format("%s - %s (%s ago)", ma.get(e), e, clock.getDelay(md.get(e), clock.now())));
                }
            }
            else {
                List<String> m = this.mpu.get(command);
                Comparator<String> l = (Comparator<String>) (s, t1) -> md.get(t1).compareTo(md.get(s));
                Collections.sort(m, l);
                for (String e : m){
                    console.writeLineToOutput(String.format("%s (%s ago)", e, clock.getDelay(md.get(e), clock.now())));
                }
            }
            command = console.readLineFromInput();
        }
    }
}
