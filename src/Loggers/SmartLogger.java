package Loggers;

import java.time.LocalDateTime;

public class SmartLogger implements Logger {
    private int i = 0;
    @Override
    public void log(String msg) {
        i++;
        if (msg.toLowerCase().contains("error")) {
            System.out.println("Error" + i + " " + "[" + LocalDateTime.now() + "]" + " " + msg);
        } else {
            System.out.println("INFO#" + i + " " + "[" + LocalDateTime.now() + "]" + " " + msg);
        }
    }
}
