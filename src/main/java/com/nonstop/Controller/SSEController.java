package com.nonstop.Controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
@RequestMapping("/sse")
public class SSEController {
    @GetMapping("/get")
    @ResponseBody
    @Operation(summary = "後端主動通知")
    public void getData(HttpServletResponse response){
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("utf-8");

        try{
            PrintWriter pw = response.getWriter();
            while (true){
                if (pw.checkError()){
                    System.out.println("Client disconnect!");
                    break;
                }
                Thread.sleep(2000);
                pw.write("data:通知測試" + Math.random() +"\n\n");
                pw.flush();
            }
        }catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
    }
}
