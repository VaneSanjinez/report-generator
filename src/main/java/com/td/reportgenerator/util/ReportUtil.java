package com.td.reportgenerator.util;

import com.td.reportgenerator.model.Report;
import com.td.reportgenerator.model.ReportDetails;
import com.td.reportgenerator.model.ReportInfo;
import org.json.JSONObject;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
public class ReportUtil {

    public static void writeToFile(String location, String fileName, String content){
        try {
            File file = new File(location+ fileName +".txt");

            // if file doesnt exists, then create new one
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();

            System.out.println("Finished");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exportReport(Report report){
        List<ReportDetails> rd = report.getReportDetails();
        ReportInfo ri = report.getReportInfo();

        String title = "Project Name,";
        title += "Project URL,";
        title += "Project Member,";
        title += "Current Date\n";


        String content= "";

        content += title;
        content = content + ri.getProjectName();
        content = content +  "," + ri.getProjectUrl();
        content = content +  "," + ri.getProjectMember();
        content = content +  "," + ri.getCurrentDate();

        writeToFile("C:\\Users\\vanessa.sanjinez\\Documents\\", "rep5", content);

//        for(ReportDetails reportDetails: rd){
//            content = content + ",";
//        }
    }
}
