package com.techelevator.filereader;

import com.techelevator.transactions.Transaction;

import java.io.*;

public class LogFileWriter {

    public void writeToLog(String logFile, String transaction) throws FileNotFoundException, IOException {
        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(logFile, true));
             BufferedWriter bufferedWriter = new BufferedWriter(printWriter)) {
            bufferedWriter.write(transaction);
            bufferedWriter.newLine();
        }
    }
}
