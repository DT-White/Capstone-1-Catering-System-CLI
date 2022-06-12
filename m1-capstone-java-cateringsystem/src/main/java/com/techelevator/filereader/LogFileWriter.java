package com.techelevator.filereader;

import com.techelevator.transactions.Transaction;

import java.io.*;

public class LogFileWriter {

    public void writeToLog(Transaction transaction) throws FileNotFoundException, IOException {
        File logFile = new File("Log.txt");
        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(logFile, true)); BufferedWriter bufferedWriter = new BufferedWriter(printWriter)) {
            bufferedWriter.write(transaction.toString());
            bufferedWriter.newLine();
        }
    }
}
