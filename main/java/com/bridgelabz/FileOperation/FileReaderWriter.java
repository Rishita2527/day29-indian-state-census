package com.bridgelabz.FileOperation;

import com.bridgelabz.Exceptions.CustomizedExceptions;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;


     // Purpose : To perform all file operations on Files

    public class FileReaderWriter {
         //Purpose : To get total number of Records in CSV File
        public int getTotalRecords(String filePath) throws CustomizedExceptions {
            AtomicBoolean firstLine = new AtomicBoolean(true);
            AtomicInteger count = new AtomicInteger();
            try {
                Files.lines(Paths.get(filePath))
                        .forEach(lines -> {
                            if (lines.startsWith("State")) {
                                firstLine.set(false);
                            } else {
                                lines.split("\n");
                                count.getAndIncrement();
                                System.out.println(lines);
                            }

                        });
                System.out.println("\nCount : " + count);

            } catch (IOException e) {
                throw new CustomizedExceptions("Enter Correct File Name");
            }
            int countOfRecords = count.intValue();
            return countOfRecords;
        }


         // Purpose : To give incorrect delimiter and throw customized Exception

        public boolean checkDelimiter(String filePath){
            boolean flag = true;
            try {
                Scanner in = new Scanner(Paths.get(filePath));
                in.useDelimiter(",");
                Pattern result = in.delimiter();
                System.out.println(result);

                if(result.pattern().equals(";"))
                    flag = true;
                else {
                    flag = false;
                    throw new CustomizedExceptions("Enter Correct Delimeter in CSV File");
                }
            } catch (IOException | CustomizedExceptions e) {
                e.printStackTrace();
            }
            return flag;
        }


         // Purpose : To give Incorrect CSV Header and throw customized exception

        public boolean checkCSVHeader(String filePath, ArrayList<String> values) throws IOException, CustomizedExceptions {
            boolean flag = true;
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = br.readLine();

            String header[] = line.split(",");

            for(int i = 0; i < header.length && i < values.size() ; i++){
                if(values.get(i).equals(header[i]))
                    flag = true;
                else{
                    flag = false;
                    throw new CustomizedExceptions("Enter Correct CSV Headers");
                }
            }
            return flag;
        }
}
