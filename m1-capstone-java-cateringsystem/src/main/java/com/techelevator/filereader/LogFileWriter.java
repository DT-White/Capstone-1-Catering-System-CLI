package com.techelevator.filereader;

import com.techelevator.transactions.Transaction;

import java.io.*;

/*
    This class should contain any and all details of access to the log file
 */
public class LogFileWriter {
    private File logFile = new File("Log.txt");
    private File auditFile = new File("TotalSales.rpt");

    public void writeToLog(Transaction transaction)  {
        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(logFile,true)); BufferedWriter bufferedWriter = new BufferedWriter(printWriter)) {
bufferedWriter.write(transaction.toString());
bufferedWriter.newLine();
        }
catch (IOException e){
    System.out.println("catch me when I fall");
}
    }
}
