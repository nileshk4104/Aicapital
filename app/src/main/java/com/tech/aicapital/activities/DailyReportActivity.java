//package com.tech.aicapital.activities;
//
//import android.content.ActivityNotFoundException;
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.content.pm.ResolveInfo;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Environment;
//import android.view.View;
//import android.widget.Button;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.content.FileProvider;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.tech.aicapital.BuildConfig;
//import com.tech.aicapital.CheckPermission;
//import com.tech.aicapital.R;
//
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.GregorianCalendar;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
//
//import com.itextpdf.text.BaseColor;
//import com.itextpdf.text.Chunk;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Element;
//import com.itextpdf.text.Font;
//import com.itextpdf.text.PageSize;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.Phrase;
//import com.itextpdf.text.Rectangle;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
//import com.itextpdf.text.pdf.draw.DottedLineSeparator;
//import com.itextpdf.text.pdf.draw.LineSeparator;
//import com.tech.aicapital.php.adapter.DailyReportAdapter;
//import com.tech.aicapital.php.datalists.DailyReportData;
//
///**
// * Created by Hadi on 2/23/2018.
// */
//
//public class DailyReportActivity extends AppCompatActivity {
//
//    @BindView(R.id.recyclerView)
//    RecyclerView recyclerView;
//    @BindView(R.id.fab_add_button)
//    FloatingActionButton fab_add_button;
//    @BindView(R.id.tvGroupname)
//    TextView tvGroupname;
//    @BindView(R.id.layout_header)
//    LinearLayout layout_header;
//    @BindView(R.id.layout_header3)
//    LinearLayout layout_header3;
//    @BindView(R.id.layout_header4)
//    LinearLayout layout_header4;
//    @BindView(R.id.totaltv_emi_paid)
//    public TextView totaltv_emi_paid;
//    @BindView(R.id.btnPdf)
//    public Button btnPdf;
//    @BindView(R.id.totaltv_loan_paid)
//    public TextView totaltv_loan_paid;
//    @BindView(R.id.totaltv_intrest_paid)
//    public TextView totaltv_intrest_paid;
//    @BindView(R.id.totaltv_collection)
//    public TextView totaltv_collection;
//    @BindView(R.id.totaltv_distribute)
//    public TextView totaltv_distribute;
//    @BindView(R.id.totaltv_fine_paid)
//    public TextView totaltv_fine_paid;
//
//    @BindView(R.id.totaltv_amt_distributed)
//    public TextView totaltv_amt_distributed;
//
//    DailyReportAdapter mAdapter3;
//    PdfWriter writer;
//
//    Uri path;
//    int position;
//    String exAmount,exReason,groupname,groupId,dates;
//    List<DailyReportData> dailyReportData=new ArrayList<>();;
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.listview);
//        ButterKnife.bind(this);
//
//        Intent intent=getIntent();
//        groupId=intent.getStringExtra("groupId");
//        groupname=intent.getStringExtra("groupname");
//
//        exAmount=intent.getStringExtra("exAmount");
//        exReason=intent.getStringExtra("exReason");
//        position=intent.getIntExtra("position",0);
//
//        dates=intent.getStringExtra("dates");
//
//        layout_header.setVisibility(View.VISIBLE);
//        layout_header3.setVisibility(View.VISIBLE);
//        layout_header4.setVisibility(View.VISIBLE);
//        btnPdf.setVisibility(View.VISIBLE);
//
//        CheckPermission.checkReadExternalStoragePermission(this);
//        btnPdf.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                generatePdf();
//            }
//        });
//
//        fab_add_button.setVisibility(View.GONE);
//        tvGroupname.setText(groupname);
//    }
//    private void generatePdf() {
//        Toast.makeText(getApplicationContext(),"Ok", Toast.LENGTH_SHORT).show();
//        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy");
//        SimpleDateFormat tm = new SimpleDateFormat("HH mm a");
//        String dates = df.format(Calendar.getInstance().getTime());
//        String times = tm.format(Calendar.getInstance().getTime());
//        String FILE = Environment.getExternalStorageDirectory().toString() + "/PDF/" + dates + ".pdf";
//        Document document = new Document(PageSize.A4);
//        String root = Environment.getExternalStorageDirectory().toString();
//        File myDir = new File(root + "/PDF");
//        myDir.mkdirs();
//        try {
//            PdfWriter.getInstance(document, new FileOutputStream(FILE));
//            writer = PdfWriter.getInstance(document, new FileOutputStream(FILE));
//            // SET HEADER FOOTER FROM HERE
////            HeaderFooterPageEvent event = new HeaderFooterPageEvent(PassbookActivity.this);
////            writer.setPageEvent(event);
//            document.open();
//            addMetaData(document);
//            addTitlePage(document);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (DocumentException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        document.close();
////                hideProgressBar();
//        Toast.makeText(getApplicationContext(), "Report is Generated " + FILE, Toast.LENGTH_LONG).show();
//        viewPdf(dates + ".pdf", "PDF");
//    }
//    private void grantAllUriPermissions(Context context, Intent intent, Uri uri) {
//        List<ResolveInfo> resInfoList = context.getPackageManager().queryIntentActivities(intent,
//                PackageManager.MATCH_DEFAULT_ONLY);
//        for (ResolveInfo resolveInfo : resInfoList) {
//            String packageName = resolveInfo.activityInfo.packageName;
//            context.grantUriPermission(packageName, uri,
//                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        }
//    }
//    private void viewPdf(String file, String directory) {
//        File pdfFile = new File(Environment.getExternalStorageDirectory() + "/" + directory + "/" + file);
//        path = FileProvider.getUriForFile(DailyReportActivity.this,
//                BuildConfig.APPLICATION_ID + ".provider", pdfFile);
//        // Setting the intent for pdf reader
//        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
//        pdfIntent.setDataAndType(path, "application/pdf");
//        pdfIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        grantAllUriPermissions(getApplicationContext(), pdfIntent, path);
//
//        try {
//            startActivity(pdfIntent);
//        } catch (ActivityNotFoundException e) {
//            Toast.makeText(DailyReportActivity.this, "Can't read pdf file",
//                    Toast.LENGTH_SHORT).show();
//        }
//    }
//    public void addMetaData(Document document) {
//        document.addTitle("Patient Report");
//        document.addSubject("Order Details");
//        document.addKeywords("Tabel no,	Bill no, Bill Amt");
//        document.addAuthor("TAG");
//        document.addCreator("TAG");
//    }
//    public void addTitlePage(Document document) throws DocumentException
//    {
//        int day, month, year;
//        String g;
//        GregorianCalendar date = new GregorianCalendar();
//        day = date.get(Calendar.DAY_OF_MONTH);
//        month = date.get(Calendar.MONTH);
//        year = date.get(Calendar.YEAR);
//        g = day + "/" + (+month + 1) + "/" + year;
//        // Font Style for Document
//
//        Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD, BaseColor.BLUE);
//        Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 22,
//                Font.BOLD, BaseColor.RED);
//        Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
//        Font smallBoldd = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD,BaseColor.BLUE);
//        Font smallBolddarkred = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(100, 0, 0));
//        Font normal = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
//
//        Font normalgreen = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL,new BaseColor(0, 150, 0));
//        Font normalred = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL,new BaseColor(225, 0, 0));
//
//        Paragraph paragraph = new Paragraph("");
//        Paragraph paragraphInfo = new Paragraph("");
//
//        Paragraph p = new Paragraph("");
//        DottedLineSeparator dottedline = new DottedLineSeparator();
//        dottedline.setOffset(-2);
//        dottedline.setGap(2f);
//        p.add(dottedline);
//
//        float[] columnWidths = {2.7f,1f,1f,1f, 1f, 1f, 1f,1f,1.3f, 1.4f, 1.4f};
//        float[] columnWidthsPinfo = {3f, 4f, 3f, 4f};
//        //create PDF table with the given widths
//
//        PdfPTable table = new PdfPTable(columnWidths);
//        // set table width a percentage of the page width
//        table.setWidthPercentage(100f);
//        insertCell2(table, "Member name", Element.ALIGN_CENTER, 1, smallBold);
//        insertCell2(table, "Total Share", Element.ALIGN_CENTER, 1, smallBoldd);
//        insertCell2(table, "Total Initial", Element.ALIGN_CENTER, 1, smallBoldd);
//        insertCell2(table, "Todays Share paid", Element.ALIGN_CENTER, 1, normal);
//        insertCell2(table, "Todays Loan paid", Element.ALIGN_CENTER, 1, normal);
//        insertCell2(table, "Todays Intrest paid", Element.ALIGN_CENTER, 1, normal);
//        insertCell2(table, "Todays Fine paid", Element.ALIGN_CENTER, 1, normal);
//        insertCell2(table, "Todays loan Taken", Element.ALIGN_CENTER, 1, normal);
//
//        insertCell2(table, "Loan remain", Element.ALIGN_CENTER, 1, normalred);
//
//        insertCell2(table, "Collection ", Element.ALIGN_CENTER, 1, normalgreen);
//        insertCell2(table, "Distribution", Element.ALIGN_CENTER, 1, normalred);
//        table.setHeaderRows(1);
//        List<DailyReportData> testResultses= dailyReportData;
//        double finalemipaid=0,totalLoanRemain=0,ttcollection=0,ttdistribute=0,todaysloanpaid=0,todaysintrestpaid=0,todaysfinepaid=0,todaysloantaken=0;
//
//        double totalofshare=0;
//        for(int i=0;i<testResultses.size();i++)
//        {
//            double totalpaid=0.0,totaldue=0.0;
//
//            totalpaid=totalpaid+ Double.parseDouble(testResultses.get(i).getPaidEmiAmt())+
//            Double.parseDouble(testResultses.get(i).getPaidLoanAmt())+
//            Double.parseDouble(testResultses.get(i).getPaidIntrestAmt())+
//            Double.parseDouble(testResultses.get(i).getPaidFineAmt());
//
//            totaldue= Double.parseDouble(testResultses.get(i).getNewLoanTaken());
//            totalLoanRemain=totalLoanRemain+ Double.parseDouble(testResultses.get(i).getAvailLoanAmt());
//            finalemipaid=finalemipaid+ Double.parseDouble(testResultses.get(i).getPaidEmiAmt());
//            todaysloanpaid=todaysloanpaid+ Double.parseDouble(testResultses.get(i).getPaidLoanAmt());
//            todaysintrestpaid=todaysintrestpaid+ Double.parseDouble(testResultses.get(i).getPaidIntrestAmt());
//            todaysfinepaid=todaysfinepaid+ Double.parseDouble(testResultses.get(i).getPaidFineAmt());
//            todaysloantaken=todaysloantaken+ Double.parseDouble(testResultses.get(i).getNewLoanTaken());
//            double tshare= Double.parseDouble(testResultses.get(i).getTotalpaid());
//            double tinitial= Double.parseDouble(testResultses.get(i).getInitialAmt());
//            double totalshare=tshare+tinitial;
//            totalofshare=totalofshare+totalshare;
//            ttcollection=ttcollection+totalpaid;
//            ttdistribute=ttdistribute+totaldue;
//            insertCell2(table, testResultses.get(i).getMemberName(),Element.ALIGN_CENTER, 1, normal);
//            insertCell2(table, String.valueOf(totalshare),Element.ALIGN_CENTER,2, smallBoldd);
//            insertCell2(table,testResultses.get(i).getPaidEmiAmt(),Element.ALIGN_CENTER,1, normal);
//            insertCell2(table, testResultses.get(i).getPaidLoanAmt(),Element.ALIGN_CENTER, 1, normal);
//            insertCell2(table, testResultses.get(i).getPaidIntrestAmt(),Element.ALIGN_CENTER, 1, normal);
//            insertCell2(table, testResultses.get(i).getPaidFineAmt(),Element.ALIGN_CENTER, 1, normal);
//            insertCell2(table,testResultses.get(i).getNewLoanTaken(),Element.ALIGN_CENTER,1, normal);
//            insertCell2(table,testResultses.get(i).getAvailLoanAmt(),Element.ALIGN_CENTER,1, normalred);
//            insertCell2(table, String.valueOf(totalpaid),Element.ALIGN_CENTER,1, normalgreen);
//            insertCell2(table, String.valueOf(totaldue),Element.ALIGN_CENTER,1, normalred);
//
//        }
//
//        insertCell2(table, "TOTAL", Element.ALIGN_LEFT, 1, smallBold);
//        insertCell2(table, String.valueOf(totalofshare), Element.ALIGN_LEFT, 2, smallBoldd);
////        insertCell2(table, "", Element.ALIGN_LEFT, 1, smallBold);
//        insertCell2(table, String.valueOf(finalemipaid), Element.ALIGN_LEFT, 1, normalgreen);
//        insertCell2(table, String.valueOf(todaysloanpaid), Element.ALIGN_LEFT, 1, normalgreen);
//        insertCell2(table, String.valueOf(todaysintrestpaid), Element.ALIGN_LEFT, 1, normalgreen);
//        insertCell2(table, String.valueOf(todaysfinepaid), Element.ALIGN_LEFT, 1, normalgreen);
//        insertCell2(table, String.valueOf(todaysloantaken), Element.ALIGN_LEFT, 1, normalred);
//        insertCell2(table, String.valueOf(totalLoanRemain), Element.ALIGN_LEFT, 1, normalred);
//        insertCell2(table, String.valueOf(ttcollection), Element.ALIGN_LEFT, 1, normalgreen);
//        insertCell2(table, String.valueOf(ttdistribute), Element.ALIGN_LEFT, 1, normalred);
//
//        LineSeparator ls = new LineSeparator();
//        document.add(new Chunk(ls));
//        Paragraph reprtName=new Paragraph();
//        reprtName.setAlignment(Element.ALIGN_CENTER);
//        reprtName.setFont(titleFont);
//        reprtName.add("\n D.B.M. FINANCE SERVICE");
//        document.add(reprtName);
//
//        Paragraph reprtName3=new Paragraph();
//        reprtName3.setAlignment(Element.ALIGN_CENTER);
//        reprtName3.setFont(normal);
//        reprtName3.add("The more you save , The more u safe");
//        document.add(reprtName3);
//
//        Paragraph reprtName5=new Paragraph();
//        reprtName5.setAlignment(Element.ALIGN_CENTER);
//        reprtName5.setFont(normal);
//        reprtName5.add("A/p Ralegan Siddhi, Teh: Parner Dist: Ahmednagar 414302");
//        document.add(reprtName5);
//
//        Paragraph reprtName4=new Paragraph();
//        reprtName4.setAlignment(Element.ALIGN_CENTER);
//        reprtName4.setFont(catFont);
//        reprtName4.add(groupname+"\n\n"+dates);
//        document.add(reprtName4);
//
//
//        document.add(new Chunk(ls));
//
//        Paragraph reprtName2=new Paragraph();
//        reprtName2.setAlignment(Element.ALIGN_CENTER);
//        reprtName2.add("\n\n");
//        document.add(reprtName2);
//        paragraph.add(table);
//        document.add(paragraph);
//
//        Paragraph reprtName7=new Paragraph();
//        reprtName7.setAlignment(Element.ALIGN_CENTER);
//        reprtName7.setFont(smallBold);
//        double expen= Double.parseDouble(exAmount);
//
//        if(position==0){
//            reprtName7.add("\n\nTotal Collection : "+ String.valueOf(totalofshare)+"\t Total Distribute : "+
//                    String.valueOf(ttdistribute)+"\t Expenditure : "+exAmount+ "->  \n "
//                    + String.valueOf(totalofshare)+"\t-"+ String.valueOf(ttdistribute)+"\t-"+
//                    String.valueOf(expen)+"\t = \t"+ String.valueOf(totalofshare-ttdistribute-expen));
//            document.add(reprtName7);
//        }else{
//            reprtName7.add("\n\nTotal Collection : "+ String.valueOf(ttcollection)+" Total Distribute : "+
//                    String.valueOf(ttdistribute)+" Expenditure : "+exAmount+ "-->  \n "
//                    + String.valueOf(ttcollection)+"-"+ String.valueOf(ttdistribute)+"-"+ String.valueOf(expen)+" = "+
//                    String.valueOf(ttcollection-ttdistribute-expen));
//            document.add(reprtName7);
//        }
//
//
//    }
//    private void insertCell2(PdfPTable table2, String text, int align, int colspan, Font font)
//    {
//        //create a new cell with the specified Text and Font
//        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
//        //set the cell alignment
//        cell.setHorizontalAlignment(align);
//        //set the cell column span in case you want to merge two or more cells
//        cell.setColspan(colspan);
//        cell.setBorder(Rectangle.PTABLE);
//        //in case there is no text and you wan to create an empty row
//        if (text.trim().equalsIgnoreCase("")) {
//            cell.setMinimumHeight(10f);
//        }
//        //add the call to the table
//        table2.addCell(cell);
//    }
//
//
//
//
//    public void onSuccessDailyReport(final List<DailyReportData> transactionDataLists)
//    {
//        dailyReportData=transactionDataLists;
//        mAdapter3  = new DailyReportAdapter(getApplicationContext(),position, transactionDataLists,"",true, new
//                DailyReportAdapter.OnItemClickListener(){
//                    @Override
//                    public void onListItemClick(int position) {
//                    }
//                    @Override
//                    public void onUpdateQty(int position, String qty) {
//                    }
//                });
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setAdapter(mAdapter3);
//        double totalemi = 0,totalintrest=0,totalfine=0,totaldistribute=0,totalloan=0;
//
//        for(int i=0;i<transactionDataLists.size();i++)
//        {
//           double emi= Double.parseDouble( transactionDataLists.get(i).getPaidEmiAmt());
//           double loan= Double.parseDouble( transactionDataLists.get(i).getPaidLoanAmt());
//           double intrest= Double.parseDouble( transactionDataLists.get(i).getPaidIntrestAmt());
//           double fine= Double.parseDouble( transactionDataLists.get(i).getPaidFineAmt());
//           double distribute= Double.parseDouble( transactionDataLists.get(i).getNewLoanTaken());
//            totalemi=totalemi+emi;
//            totalintrest=totalintrest+intrest;
//            totalfine=totalfine+fine;
//            totaldistribute=totaldistribute+distribute;
//            totalloan=totalloan+loan;
//        }
//        totaltv_emi_paid.setText(String.valueOf(totalemi));
//        totaltv_loan_paid.setText(String.valueOf(totalloan));
//        totaltv_intrest_paid.setText(String.valueOf(totalintrest));
//        totaltv_fine_paid.setText(String.valueOf(totalfine));
//        totaltv_amt_distributed.setText(String.valueOf(totaldistribute));
//
//       double totalcollection =totalintrest+totalfine+totalloan+totalemi;
//
//        totaltv_collection.setText(String.valueOf(totalcollection));
//        totaltv_distribute.setText(String.valueOf(totaldistribute));
//
//
//    }
//
//}
