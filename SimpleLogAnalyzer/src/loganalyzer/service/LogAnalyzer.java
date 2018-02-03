package loganalyzer.service;

import loganalyzer.entity.HttpMethod;
import loganalyzer.entity.LogToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;

import static java.time.LocalDateTime.parse;
import static java.time.format.DateTimeFormatter.ofPattern;


public class LogAnalyzer implements ILogAnalyzer {

    public Collection<LogToken> getTokenFromFile(String path,
                                                 LocalDateTime timeFrom,
                                                 LocalDateTime timeTo) {
        Collection<LogToken> list = new LinkedList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String str;
            do {
                str = bufferedReader.readLine();
                if (str != null) {
                    LogToken logTokenEntry = parseLine(str);
                    if (isInRange(logTokenEntry.getTime(), timeFrom, timeTo)) {
                        list.add(logTokenEntry);
                    }
                }
            } while (str != null);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private boolean isInRange(LocalDateTime time, LocalDateTime timeFrom,
                              LocalDateTime timeTo) {
        return (timeFrom.isBefore(time) && timeTo.isAfter(time));
    }


    private LogToken parseLine(String str) {
        LogToken logToken = new LogToken();
        logToken.setTime(parse(str.substring(str.indexOf('[') + 1, str.indexOf(']') - 6), ofPattern("dd/MMM/yyyy:HH:mm:ss")));
        int startIndexOfMessage = str.indexOf('"') + 1;
        int lastIndexOfMessage = str.lastIndexOf('"');
        String httpMethod = str.substring(startIndexOfMessage, startIndexOfMessage + 4).trim();
        logToken.setMethod(HttpMethod.getHttpMethod(httpMethod));
        logToken.setMessage(str.substring(startIndexOfMessage + 4, lastIndexOfMessage));
        return logToken;
    }


}
