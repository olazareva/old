package loganalyzer.service;


import loganalyzer.entity.LogToken;

import java.time.LocalDateTime;
import java.util.Collection;

public interface ILogAnalyzer {
        Collection<LogToken> getTokenFromFile(String path, LocalDateTime timeFrom, LocalDateTime timeTo);
}
