package com.example.datawarehouse.IO;

import com.example.datawarehouse.model.Lottery;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class WriteFile {
    static Path path = Paths.get("store");
    static String fullPath = path.toAbsolutePath().toString()+"\\";
    public static void init(Path path) {
        try {
            Files.createDirectory(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void write(String nameFile, List<Lottery> lotteries){
        init(path);
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fullPath+nameFile)))) {
            bw.write("Company");
            bw.write(",");
            bw.write("Province");
            bw.write(",");
            bw.write("Issue Date");
            bw.write(",");
            bw.write("Prize 0");
            bw.write(",");
            bw.write("Prize 1");
            bw.write(",");
            bw.write("Prize 2");
            bw.write(",");
            bw.write("Prize 3");
            bw.write(",");
            bw.write("Prize 4");
            bw.write(",");
            bw.write("Prize 5");
            bw.write(",");
            bw.write("Prize 6");
            bw.write(",");
            bw.write("Prize 7");
            bw.write(",");
            bw.write("Prize 8");
            bw.write(",");
            bw.write("Info Prize");
            bw.newLine();
            for (Lottery lottery : lotteries) {
                bw.write(lottery.getCompany());
                bw.write(",");
                bw.write(lottery.getProvince());
                bw.write(",");
                bw.write(lottery.getIssueDate());
                bw.write(",");
                bw.write(lottery.getPrize0());
                bw.write(",");
                bw.write(lottery.getPrize1());
                bw.write(",");
                bw.write(lottery.getPrize2());
                bw.write(",");
                bw.write(lottery.getPrize3());
                bw.write(",");
                bw.write(lottery.getPrize4());
                bw.write(",");
                bw.write(lottery.getPrize5());
                bw.write(",");
                bw.write(lottery.getPrize6());
                bw.write(",");
                bw.write(lottery.getPrize7());
                bw.write(",");
                bw.write(lottery.getPrize8());
                bw.newLine();
                ;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
