package com.example.uvaa;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

//import com.itextpdf.html2pdf.HtmlConverter;
//import com.itextpdf.io.source.ByteArrayOutputStream;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.PageSize;
//import com.itextpdf.text.html.simpleparser.HTMLWorker;
//import com.itextpdf.text.pdf.PdfWriter;


import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.layout.element.IElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

public class generate_bill extends AppCompatActivity {
    Spinner s,s1;
    Button b;
    String[] month= {"January","February","March","April","May","June","July","August","September","October","November","December"};
    MyDbHelper db = new MyDbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_bill);


        s = findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(generate_bill.this,android.R.layout.simple_spinner_item,month);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);

        String[] visi = db.get_visitors();
        s1 = findViewById(R.id.spinner6);
        ArrayAdapter<String> adapter1=new ArrayAdapter<>(generate_bill.this, android.R.layout.simple_spinner_item,visi);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter1);

        b = findViewById(R.id.button10);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mon= s.getSelectedItem().toString();
                int index=s.getSelectedItemPosition();
                String[][] ent= db.get_entry(mon,index);
                int[] lecadd = new int[6];
                Arrays.fill(lecadd,0);
                int[] pracadd = new int[6];
                Arrays.fill(pracadd,0);

                for(int i=0, k=0;i<12;i+=2,k++)
                {
                    for(int j=0;j<10;j+=2) {
                        if(ent[i][j]!="")
                        {
                            lecadd[k] = lecadd[k]+Integer.parseInt(ent[i][j]);
                        }
                        if(ent[i][j+1]!="")
                        {
                            pracadd[k] = pracadd[k]+Integer.parseInt(ent[i][j+1]);
                        }
                    }
                }

                int monlec = lecadd[0]+lecadd[1]+lecadd[2]+lecadd[3]+lecadd[4]+lecadd[5];
                int monprac = pracadd[0]+pracadd[1]+pracadd[2]+pracadd[3]+pracadd[4]+pracadd[5];

                File directory = getExternalFilesDir(null);
                // Create a new HTML file
                File htmlFile = new File(directory, "newfile.html");

                try {
                    // Write some HTML content to the file
                    FileWriter writer = new FileWriter(htmlFile);
                    writer.write("<html>\n" +
                            "\n" +
                            "<head>\n" +
                            "    <title>Bill Report</title>\n" +
                            "    <style>\n" +
                            "        h2 {text-align: center;}\n" +
                            "\n" +
                            "        table, th, td {\n" +
                            "  \t\t\tborder: 1px solid black;\n" +
                            "  \t\t\tborder-collapse: collapse;\n" +
                            "        }\n" +
                            "        th.left-align {\n" +
                            "            text-align: left;\n" +
                            "        }\n" +
                            "\n" +
                            "        th:not(.left-align) {\n" +
                            "            text-align: center;\n" +
                            "        }\n" +
                            "        .bordered {\n" +
                            "    \t\t\t\twidth: 1400px;\n" +
                            "    \t\t\t\theight: 750px;\n" +
                            "    \t\t\t\tpadding: 20px;\n" +
                            "    \t\t\t\tborder: 2px solid black;\n" +
                            "  \t\t\t}\n" +
                            "\n" +
                            "    </style>\n" +
                            "</head>\n" +
                            "\n" +
                            "<body>\n" +
                            "    <div class=\"bordered\">\n" +
                            "\n" +
                            "        <br><br>\n" +
                            "        <h2>PROFORMA - I</h2>\n" +
                            "        <br>\n" +
                            "        <label for=\"fname\">Name of the staff :  &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp; Office order No : &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp; Dated :-</label><br>\n" +
                            "        <label for=\"fname\">Designation : Lecturer in IT  &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp; Department : Information Technology </label>\n" +
                            "        <table style=\"width:100%\">\n" +
                            "            <tr>\n" +
                            "                <th rowspan=\"3\" style=\"width:9%\">Month & Year</th>\n" +
                            "                <th rowspan=\"3\" style=\"width:4%\">Day</th>\n" +
                            "                <th colspan=\"10\" style=\"width:38%\">Week & Date</th>\n" +
                            "                <th rowspan=\"3\" style=\"width:9%\">Class</th>\n" +
                            "                <th rowspan=\"3\" style=\"width:9%\">Subject & Course Code</th>\n" +
                            "                <th colspan=\"2\" style=\"width:15%\">Total Load Hr.</th>\n" +
                            "                <th rowspan=\"3\" style=\"width:17%\">Renumeration</th>\n" +
                            "            </tr>\n" +
                            "            <tr>\n" +
                            "                <th  colspan=\"2\">1</th>\n" +
                            "                <th  colspan=\"2\">2</th>\n" +
                            "                <th colspan=\"2\">3</th>\n" +
                            "                <th colspan=\"2\">4</th>\n" +
                            "                <th colspan=\"2\">5</th>\n" +
                            "                <th rowspan=\"2\">L</th>\n" +
                            "                <th rowspan=\"2\">P</th>\n" +
                            "            </tr>\n" +
                            "            <tr>\n" +
                            "                <th>L</th>\n" +
                            "                <th>P</th>\n" +
                            "                <th>L</th>\n" +
                            "                <th>P</th>\n" +
                            "                <th>L</th>\n" +
                            "                <th>P</th>\n" +
                            "                <th>L</th>\n" +
                            "                <th>P</th>\n" +
                            "                <th>L</th>\n" +
                            "                <th>P</th>\n" +
                            "            </tr>\n" +
                            "\n" +
                            "            <tr>\n" +
                            "                <th rowspan=\"12\">"+mon+"-2023</th>\n" +
                            "                <th rowspan=\"2\">MON</th>\n" +
                            "                \n" +
                            "                <th>&ensp;"+ent[0][0]+"</th>\n" +
                            "                <th>"+ent[0][1]+"</th>\n" +
                            "                <th>"+ent[0][2]+"</th>\n" +
                            "                <th>"+ent[0][3]+"</th>\n" +
                            "                <th>"+ent[0][4]+"</th>\n" +
                            "                <th>"+ent[0][5]+"</th>\n" +
                            "                <th>"+ent[0][6]+"</th>\n" +
                            "                <th>"+ent[0][7]+"</th>\n" +
                            "                <th>"+ent[0][8]+"</th>\n" +
                            "                <th>"+ent[0][9]+"</th>\n" +
                            "                <th rowspan=\"12\"></th>\n" +
                            "                <th rowspan=\"12\"></th>\n" +
                            "                <th>"+lecadd[0]+"</th>\n" +
                            "                <th>"+pracadd[0]+"</th>\n" +
                            "                <th rowspan=\"6\">"+monlec*800+"</th>\n" +
                            "            </tr>\n" +
                            "            <tr>\n" +
                            "                \n" +
                            "                <th>&ensp;</th>\n" +
                            "                <th> </th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "            </tr>\n" +
                            "            <tr>\n" +
                            "                <th rowspan=\"2\">TUE</th>\n" +
                            "                \n" +
                            "                <th>&ensp;"+ent[2][0]+"</th>\n" +
                            "                <th>&ensp;"+ent[2][1]+"</th>\n" +
                            "                <th>"+ent[2][2]+"</th>\n" +
                            "                <th>"+ent[2][3]+"</th>\n" +
                            "                <th>"+ent[2][4]+"</th>\n" +
                            "                <th>"+ent[2][5]+"</th>\n" +
                            "                <th>"+ent[2][6]+"</th>\n" +
                            "                <th>"+ent[2][7]+"</th>\n" +
                            "                <th>"+ent[2][8]+"</th>\n" +
                            "                <th>"+ent[2][9]+"</th>\n" +
                            "                <th>"+lecadd[1]+"</th>\n" +
                            "                <th>"+pracadd[1]+"</th>\n" +
                            "            </tr>\n" +
                            "            <tr>\n" +
                            "                \n" +
                            "                <th>&ensp;</th>\n" +
                            "                <th> </th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "            </tr>            \n" +
                            "            <tr>\n" +
                            "                <th rowspan=\"2\">WED</th>\n" +
                            "                \n" +
                            "                <th>&ensp;"+ent[4][0]+"</th>\n" +
                            "                <th>&ensp;"+ent[4][1]+"</th>\n" +
                            "                <th>"+ent[4][2]+"</th>\n" +
                            "                <th>"+ent[4][3]+"</th>\n" +
                            "                <th>"+ent[4][4]+"</th>\n" +
                            "                <th>"+ent[4][5]+"</th>\n" +
                            "                <th>"+ent[4][6]+"</th>\n" +
                            "                <th>"+ent[4][7]+"</th>\n" +
                            "                <th>"+ent[4][8]+"</th>\n" +
                            "                <th>"+ent[4][9]+"</th>\n" +
                            "                <th>"+lecadd[2]+"</th>\n" +
                            "                <th>"+pracadd[2]+"</th>\n" +
                            "            </tr>\n" +
                            "            <tr>\n" +
                            "                \n" +
                            "                <th>&ensp;</th>\n" +
                            "                <th> </th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "            </tr>   \n" +
                            "            <tr>\n" +
                            "                <th rowspan=\"2\">THU</th>\n" +
                            "                \n" +
                            "                <th>&ensp;"+ent[6][0]+"</th>\n" +
                            "                <th>&ensp;"+ent[6][1]+"</th>\n" +
                            "                <th>"+ent[6][2]+"</th>\n" +
                            "                <th>"+ent[6][3]+"</th>\n" +
                            "                <th>"+ent[6][4]+"</th>\n" +
                            "                <th>"+ent[6][5]+"</th>\n" +
                            "                <th>"+ent[6][6]+"</th>\n" +
                            "                <th>"+ent[6][7]+"</th>\n" +
                            "                <th>"+ent[6][8]+"</th>\n" +
                            "                <th>"+ent[6][9]+"</th>\n" +
                            "                <th>"+lecadd[3]+"</th>\n" +
                            "                <th>"+pracadd[3]+"</th>\n" +
                            "                <th rowspan=\"6\">"+monprac*800+"</th>" +
                            "            </tr>\n" +
                            "            <tr>\n" +
                            "                \n" +
                            "                <th>&ensp;</th>\n" +
                            "                <th> </th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "            </tr>\n" +
                            "            <tr>\n" +
                            "                <th rowspan=\"2\">FRI</th>\n" +
                            "                \n" +
                            "                <th>&ensp;"+ent[8][0]+"</th>\n" +
                            "                <th>&ensp;"+ent[8][1]+"</th>\n" +
                            "                <th>"+ent[8][2]+"</th>\n" +
                            "                <th>"+ent[8][3]+"</th>\n" +
                            "                <th>"+ent[8][4]+"</th>\n" +
                            "                <th>"+ent[8][5]+"</th>\n" +
                            "                <th>"+ent[8][6]+"</th>\n" +
                            "                <th>"+ent[8][7]+"</th>\n" +
                            "                <th>"+ent[8][8]+"</th>\n" +
                            "                <th>"+ent[8][9]+"</th>\n" +
                            "                <th>"+lecadd[4]+"</th>\n" +
                            "                <th>"+pracadd[4]+"</th>\n" +
                            "            </tr>\n" +
                            "            <tr>\n" +
                            "                \n" +
                            "                <th>&ensp;</th>\n" +
                            "                <th> </th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "            </tr>\n" +
                            "            <tr>\n" +
                            "                <th rowspan=\"2\">SAT</th>\n" +
                            "                \n" +
                            "                <th>&ensp;"+ent[10][0]+"</th>\n" +
                            "                <th>&ensp;"+ent[10][1]+"</th>\n" +
                            "                <th>"+ent[10][2]+"</th>\n" +
                            "                <th>"+ent[10][3]+"</th>\n" +
                            "                <th>"+ent[10][4]+"</th>\n" +
                            "                <th>"+ent[10][5]+"</th>\n" +
                            "                <th>"+ent[10][6]+"</th>\n" +
                            "                <th>"+ent[10][7]+"</th>\n" +
                            "                <th>"+ent[10][8]+"</th>\n" +
                            "                <th>"+ent[10][9]+"</th>\n" +
                            "                <th>"+lecadd[5]+"</th>\n" +
                            "                <th>"+pracadd[5]+"</th>\n" +
                            "            </tr>\n" +
                            "            <tr>\n" +
                            "                \n" +
                            "                <th>&ensp;</th>\n" +
                            "                <th> </th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "                <th></th>\n" +
                            "            </tr>\n" +
                            "\n" +
                            "            <tr>\n" +
                            "                <th colspan=\"14\"></th>\n" +
                            "                <th>"+monlec+"</th>\n" +
                            "                <th>"+monprac+"</th>\n" +
                            "                <th>Total</th>\n" +
                            "            </tr>\n" +
                            "            <tr>\n" +
                            "                <th colspan=\"17\" class=\"left-align\">Total Amount in Rs:&ensp;&ensp;"+(monlec+monprac)*800+"</th>\n" +
                            "            </tr>\n" +
                            "\n" +
                            "        </table>\n" +
                            "        <br><br><br><br><label for=\"fname\">STAFF &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;VERIFYING OFFICER&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;HOD&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;PRINCIPAL</label>\n" +
                            "        <label for=\"fname\">&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;Govt. Polytechnic, Sambhajinagar</label>\n" +
                            "    \n" +
                            "    </div>\n" +
                            "</body>\n" +
                            "</html>");
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
    }
    public void createHtmlFile() {
        // Get the directory where you want to create the file

    }

}