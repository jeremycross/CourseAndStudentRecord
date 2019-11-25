/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package package1;

import java.io.*;
import java.util.Arrays;
import java.util.Calendar;
import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author jdyno
 */
public class StudentRecord extends javax.swing.JFrame {
    
private final File stuFile = new File("D:\\jdyno\\Documents\\Grade12Programming\\StudentData.txt"), courseFile = new File("D:\\jdyno\\Documents\\Grade12Programming\\CourseData.txt");
private String[][] stuRecord = new String[1000][29], courseRecord = new String [1000][3], grade9 = new String[1000][29], grade10 = new String[1000][29], grade11 = new String[1000][29], grade12 = new String[1000][29];
private BufferedReader studentFileReader, courseFileReader;
private PrintWriter studentFileWriter, courseFileWriter;
private ButtonGroup bg = new ButtonGroup();

private final Calendar cal = Calendar.getInstance();
private int studentCap = 0, coursecap = 0, current = 1, foundIndex;
private double todayY, todayM, todayD;
    /**
     * Creates new form StudentRecord
     */
    public StudentRecord() {
        initComponents();
        bg.add(rbAll);
        bg.add(rbGr9);
        bg.add(rbGr10);
        bg.add(rbGr11);
        bg.add(rbGr12);
        todayY = cal.get(YEAR);
        todayM = cal.get(MONTH)+1;
        todayD = cal.get(DATE);
        getFileInfo(stuFile);
        getFileInfo(courseFile);
        tableCreation();
        popCourseCombos();
        displayStudentInfo(current);
    }
    
    private void getFileInfo(File file){
        
        if(file == stuFile){
         studentCap = 0;
         try
        {
            studentFileReader = new BufferedReader(new FileReader(stuFile));
        }

        catch (FileNotFoundException e)
        {
            System.out.println("The data file cannot be opened! Error Number - " + e);
        }
        
        String line = null;
        String[] lineSplit;
        int iCount = 1;

        boolean done = false;
        
            do
            {
              try
                { // Try to Read the next line from the file
                    line = studentFileReader.readLine();
                }

                catch (IOException e)
                { // Handle any errors
                    System.out.println("There is a problem at line " + iCount
                        + " Error number - " + e);
                }

                if (line == null)
                {
                    done = true; // No more data in the file - You're done!
                }

                else
                { // More data
                    studentCap++;
                    
                    lineSplit = line.split(",");
                    stuRecord[iCount] = lineSplit; // Insert in the array
                
                    if(stuRecord[iCount] == null){
                        System.out.println("Read Failed");
                    }
                    else{
                        for(int i = 0; i < stuRecord[iCount].length; i++){
                           System.out.print(stuRecord[iCount][i]);
                        }
                    }
                    iCount++; // Increment the counter
                }
                
            } while (!done);
                    createGradeArrays();
        }
        
         if(file == courseFile){

         try
        {
            courseFileReader = new BufferedReader(new FileReader(courseFile));
        }

        catch (FileNotFoundException e)
        {
            System.out.println("The data file cannot be opened! Error Number - " + e);
        }
        
        String line1 = null;
        String[] lineSplit1;
        int iCount1 = 0;

        boolean done = false;
        
            do
            {
              try
                { // Try to Read the next line from the file
                    line1 = courseFileReader.readLine();
                }

                catch (IOException e)
                { // Handle any errors
                    System.out.println("There is a problem at line " + iCount1
                        + " Error number - " + e);
                }

                if (line1 == null)
                {
                    done = true; // No more data in the file - You're done!
                }

                else
                { // More data
                    coursecap++;
                    
                    lineSplit1 = line1.split(",");
                    courseRecord[iCount1] = lineSplit1; // Insert in the array
                
                    if(courseRecord[iCount1] == null){
                        System.out.println("Read Failed");
                    }
                    else{
                        for(int i = 0; i < courseRecord[iCount1].length; i++){
                           System.out.print(courseRecord[iCount1][i]);
                        }
                    }
                    iCount1++; // Increment the counter
                }
                
            } while (!done);
        }

}
    
    private void writeToFile(File writeFile){
        if(writeFile == stuFile){
            ArrayIndex0();//sets current record to array index 0 and saves it
            removeElt(stuRecord, current); //remove duplicate element
            try {
                //INIT STUDENT FILE WRITER            
                studentFileWriter = new PrintWriter(new BufferedWriter(new FileWriter(stuFile)), true);  // flush mode
            }

            catch (IOException e) {
                System.out.println("Student writer could not be opened, error #: " + e);
            }
            
            
            //LOOP THROUGH ALL STUDENTS AND PRINT INFORMATION
            for(int i = 0; i < studentCap; i++){
                studentFileWriter.println(stuRecord[i][0] + "," + stuRecord[i][1]+ "," +stuRecord[i][2]+ "," +stuRecord[i][3]+ "," +stuRecord[i][4]+ "," +stuRecord[i][5]+ "," +stuRecord[i][6]+
                         "," +stuRecord[i][7]+ "," +stuRecord[i][8]+ "," +stuRecord[i][9]+ "," +stuRecord[i][10]+ "," +stuRecord[i][11]+ "," +stuRecord[i][12]+ "," +stuRecord[i][13]+
                         "," +stuRecord[i][14]+ "," +stuRecord[i][15]+ "," +stuRecord[i][16]+ "," +stuRecord[i][17]+ "," +stuRecord[i][18]+ "," +stuRecord[i][19]+ "," +stuRecord[i][20]+
                         "," +stuRecord[i][21]+","+stuRecord[i][22]+ "," +stuRecord[i][23]+ "," +stuRecord[i][24]+ "," +stuRecord[i][25]+ "," +stuRecord[i][26]+ "," +stuRecord[i][27]+
                         "," +stuRecord[i][28]);
            }
            
            studentFileWriter.close();
    }
        if(writeFile == courseFile){
            
            try {
                //INIT STUDENT FILE WRITER            
                courseFileWriter = new PrintWriter(new BufferedWriter(new FileWriter(courseFile)), true);  // flush mode
            }

            catch (IOException e) {
                System.out.println("Student writer could not be opened, error #: " + e);
            }
            
            
            
            //LOOP THROUGH ALL STUDENTS AND PRINT INFORMATION 
            for(int j =0; j < coursecap; j++){
                for(int i = 0; i < courseRecord[j].length; i++){
                    courseFileWriter.print(courseRecord[j][i]+",");
                }
                courseFileWriter.println();
            }
            courseFileWriter.close();
    }
    }
    
    private void ArrayIndex0(){
        stuRecord[0][1] = StuNameText.getText();
        stuRecord[0][0] = lastText.getText();
        stuRecord[0][2] = StuNumText.getText();
        stuRecord[0][3] = GradeNumText.getText();
        stuRecord[0][4] = AddressText.getText();
        stuRecord[0][6] = CityText.getText();
        stuRecord[0][5] = ProvinceText.getText();
        stuRecord[0][7] = PCText.getText();
        stuRecord[0][8] = PhoneText.getText();
        stuRecord[0][9] = YearCombo.getSelectedItem().toString();
        stuRecord[0][10] = MonthCombo.getSelectedItem().toString();
        stuRecord[0][11] = DayCombo.getSelectedItem().toString();
        stuRecord[0][12] = GenderCombo.getSelectedItem().toString();
        
        stuRecord[0][13] = crs1Combo.getSelectedItem().toString();
        stuRecord[0][15] = crs2Combo.getSelectedItem().toString();
        stuRecord[0][17] = crs3Combo.getSelectedItem().toString();
        stuRecord[0][19] = crs4Combo.getSelectedItem().toString();
        stuRecord[0][21] = crs5Combo.getSelectedItem().toString();
        stuRecord[0][23] = crs6Combo.getSelectedItem().toString();
        stuRecord[0][25] = crs7Combo.getSelectedItem().toString();
        stuRecord[0][27] = crs8Combo.getSelectedItem().toString();
        
        stuRecord[0][14] = crs1Mrk.getText();
        stuRecord[0][16] = crs2Mrk.getText();
        stuRecord[0][18] = crs3Mrk.getText();
        stuRecord[0][20] = crs4Mrk.getText();
        stuRecord[0][22] = crs5Mrk.getText();
        stuRecord[0][24] = crs6Mrk.getText();
        stuRecord[0][26] = crs7Mrk.getText();
        stuRecord[0][28] = crs8Mrk.getText();
        
        
}
    
    private void tableCreation(){
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
        new String [][] {
                courseRecord[0],
                courseRecord[1],
                courseRecord[2],
                courseRecord[3],
                courseRecord[4],
                courseRecord[5],
                courseRecord[6],
                courseRecord[7],
                courseRecord[8],
                courseRecord[9],
                courseRecord[10],
                courseRecord[11],
                courseRecord[12],
                courseRecord[13],
                courseRecord[14],
                courseRecord[15],
                courseRecord[16],
                courseRecord[17],
                courseRecord[18],
                courseRecord[19],
                courseRecord[20],
                courseRecord[21],
                courseRecord[22],
                courseRecord[23],
                courseRecord[24],
                courseRecord[25],
                courseRecord[26],
                courseRecord[27],
                courseRecord[28],
                courseRecord[29],
                courseRecord[30],
                courseRecord[31],
                courseRecord[32],
                courseRecord[33],
                courseRecord[34],
                courseRecord[35],
                courseRecord[36],
                courseRecord[37],
                courseRecord[38],
                courseRecord[39],
                courseRecord[40],
                courseRecord[41],
                courseRecord[42],
                courseRecord[43],
                courseRecord[44],
                courseRecord[45],
                courseRecord[46],
                courseRecord[47],
                courseRecord[48],
                courseRecord[49],
                courseRecord[50]
            },
            new String [] {
                "Code", "Grade", "Description"
            }
        ));
        jTable1.setEnabled(false);
        jScrollPane1.setViewportView(jTable1);
    }
    
    public static void removeElt( String [][] arr, int remIndex )
    {
        for ( int i = remIndex ; i < arr.length - 1 ; i++ )
        {
            arr[ i ] = arr[ i + 1 ] ; 
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField2 = new javax.swing.JTextField();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        stuName = new javax.swing.JLabel();
        address = new javax.swing.JLabel();
        city = new javax.swing.JLabel();
        StuNameText = new javax.swing.JTextField();
        AddressText = new javax.swing.JTextField();
        CityText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        StuNumText = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        ProvinceText = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        PCText = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        GradeNumText = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        PhoneText = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        GenderCombo = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        AgeText = new javax.swing.JTextField();
        YearCombo = new javax.swing.JComboBox<>();
        MonthCombo = new javax.swing.JComboBox<>();
        DayCombo = new javax.swing.JComboBox<>();
        AverageGradeText = new javax.swing.JTextField();
        PrevButton = new javax.swing.JButton();
        NextButton = new javax.swing.JButton();
        AddButton = new javax.swing.JButton();
        FindbyNumButton = new javax.swing.JButton();
        DelButton = new javax.swing.JButton();
        SaveButton = new javax.swing.JButton();
        EditButton = new javax.swing.JButton();
        searchBox = new javax.swing.JTextField();
        jlabel14 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        crs5Combo = new javax.swing.JComboBox<>();
        crs6Combo = new javax.swing.JComboBox<>();
        crs7Combo = new javax.swing.JComboBox<>();
        crs8Combo = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        crs1Combo = new javax.swing.JComboBox<>();
        crs2Combo = new javax.swing.JComboBox<>();
        crs3Combo = new javax.swing.JComboBox<>();
        crs4Combo = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        crs1Mrk = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        crs3Mrk = new javax.swing.JTextField();
        crs4Mrk = new javax.swing.JTextField();
        crs2Mrk = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        crs5Mrk = new javax.swing.JTextField();
        crs6Mrk = new javax.swing.JTextField();
        crs7Mrk = new javax.swing.JTextField();
        crs8Mrk = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel19 = new javax.swing.JLabel();
        lastText = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        AlphaSortCheck = new javax.swing.JCheckBox();
        FindSearchResult = new javax.swing.JButton();
        FindbyNameButton = new javax.swing.JButton();
        rbGr9 = new javax.swing.JRadioButton();
        rbGr10 = new javax.swing.JRadioButton();
        rbGr11 = new javax.swing.JRadioButton();
        rbGr12 = new javax.swing.JRadioButton();
        rbAll = new javax.swing.JRadioButton();
        ListStudents = new javax.swing.JButton();
        rbmin = new javax.swing.JRadioButton();
        rbmed = new javax.swing.JRadioButton();
        rbALL = new javax.swing.JRadioButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        saveButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jTextField2.setText("jTextField2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane2.setMinimumSize(new java.awt.Dimension(700, 600));
        jTabbedPane2.setPreferredSize(new java.awt.Dimension(700, 600));

        stuName.setText("Student Name: First");

        address.setText("Address");

        city.setText("City");

        StuNameText.setEditable(false);
        StuNameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StuNameTextActionPerformed(evt);
            }
        });

        AddressText.setEditable(false);

        CityText.setEditable(false);
        CityText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CityTextActionPerformed(evt);
            }
        });

        jLabel4.setText("Student Number");

        StuNumText.setEditable(false);
        StuNumText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StuNumTextActionPerformed(evt);
            }
        });

        jLabel5.setText("Province");

        ProvinceText.setEditable(false);

        jLabel1.setText("Postal Code");

        PCText.setEditable(false);

        jLabel2.setText("Grade");

        GradeNumText.setEditable(false);
        GradeNumText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GradeNumTextActionPerformed(evt);
            }
        });

        jLabel3.setText("Home Phone");

        PhoneText.setEditable(false);
        PhoneText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PhoneTextActionPerformed(evt);
            }
        });

        jLabel6.setText("Date of Birth");

        jLabel7.setText("Gender");

        jLabel8.setText("Average");

        GenderCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female", "Other" }));
        GenderCombo.setEnabled(false);

        jLabel9.setText("Age");

        AgeText.setEditable(false);
        AgeText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgeTextActionPerformed(evt);
            }
        });

        YearCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010" }));
        YearCombo.setEnabled(false);

        MonthCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        MonthCombo.setEnabled(false);

        DayCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        DayCombo.setEnabled(false);

        AverageGradeText.setEditable(false);

        PrevButton.setText("<--Prev");
        PrevButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrevButtonActionPerformed(evt);
            }
        });

        NextButton.setText("Next -->");
        NextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextButtonActionPerformed(evt);
            }
        });

        AddButton.setText("Add");
        AddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddButtonActionPerformed(evt);
            }
        });

        FindbyNumButton.setText("Find by Number");
        FindbyNumButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FindbyNumButtonActionPerformed(evt);
            }
        });

        DelButton.setText("Delete");
        DelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DelButtonActionPerformed(evt);
            }
        });

        SaveButton.setText("Save");
        SaveButton.setEnabled(false);
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        EditButton.setText("Edit");
        EditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditButtonActionPerformed(evt);
            }
        });

        searchBox.setText("Search...");
        searchBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBoxActionPerformed(evt);
            }
        });

        jlabel14.setText("Course 5");

        jLabel11.setText("Course 6");

        jLabel12.setText("Course 7");

        jLabel13.setText("Course 8");

        crs5Combo.setEnabled(false);

        crs6Combo.setEnabled(false);

        crs7Combo.setEnabled(false);
        crs7Combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crs7ComboActionPerformed(evt);
            }
        });

        crs8Combo.setEnabled(false);

        jLabel14.setText("Course 2");

        jLabel15.setText("Course 3");

        jLabel16.setText("Course 4");

        crs1Combo.setEnabled(false);

        crs2Combo.setEnabled(false);

        crs3Combo.setEnabled(false);

        crs4Combo.setEnabled(false);

        jLabel17.setText("Course 1");

        crs1Mrk.setEditable(false);

        jLabel10.setText("Mark");

        crs3Mrk.setEditable(false);

        crs4Mrk.setEditable(false);

        crs2Mrk.setEditable(false);

        jLabel18.setText("Mark");

        crs5Mrk.setEditable(false);

        crs6Mrk.setEditable(false);

        crs7Mrk.setEditable(false);

        crs8Mrk.setEditable(false);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Search results: ");
        jScrollPane2.setViewportView(jTextArea1);

        jLabel19.setText("Last");

        lastText.setEditable(false);
        lastText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastTextActionPerformed(evt);
            }
        });

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane3.setViewportView(jTextArea2);

        AlphaSortCheck.setText("Sort Alphabetically");
        AlphaSortCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlphaSortCheckActionPerformed(evt);
            }
        });

        FindSearchResult.setText("Go to Student");
        FindSearchResult.setEnabled(false);
        FindSearchResult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FindSearchResultActionPerformed(evt);
            }
        });

        FindbyNameButton.setText("Find by Surname");
        FindbyNameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FindbyNameButtonActionPerformed(evt);
            }
        });

        rbGr9.setText("Grade 9");

        rbGr10.setText("Grade 10");

        rbGr11.setText("Grade 11");

        rbGr12.setText("Grade 12");

        rbAll.setSelected(true);
        rbAll.setText("All");

        ListStudents.setText("List Students");
        ListStudents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListStudentsActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbmin);
        rbmin.setSelected(true);
        rbmin.setText("Minimal");

        buttonGroup1.add(rbmed);
        rbmed.setText("Medium");

        buttonGroup1.add(rbALL);
        rbALL.setText("All");
        rbALL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbALLActionPerformed(evt);
            }
        });

        jButton1.setText("Select Student");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel20.setText("Limit Students");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(FindbyNumButton, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(FindbyNameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(searchBox))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(stuName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(StuNameText, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lastText, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(city, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(address)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel16))
                                .addGap(67, 67, 67)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(78, 78, 78)
                                        .addComponent(jLabel10))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(crs4Combo, 0, 64, Short.MAX_VALUE)
                                            .addComponent(crs3Combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(crs2Combo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(crs1Combo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel5Layout.createSequentialGroup()
                                                    .addGap(4, 4, 4)
                                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(crs1Mrk, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(crs2Mrk, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(crs3Mrk, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(crs4Mrk, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(PCText, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                                    .addComponent(ProvinceText, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(CityText)
                                    .addComponent(AddressText)
                                    .addComponent(PhoneText, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8)
                                .addComponent(jLabel6)
                                .addComponent(jlabel14)
                                .addComponent(jLabel11)
                                .addComponent(jLabel12)
                                .addComponent(jLabel13)
                                .addComponent(jLabel7))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(DelButton)
                                .addGap(41, 41, 41)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(AgeText, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(AverageGradeText, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(StuNumText, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(GradeNumText))
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(YearCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(MonthCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel18)
                                                    .addComponent(DayCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addGap(14, 14, 14))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(EditButton)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(crs6Combo, javax.swing.GroupLayout.Alignment.TRAILING, 0, 62, Short.MAX_VALUE)
                                            .addComponent(crs7Combo, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(crs8Combo, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(crs5Combo, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(crs5Mrk, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(crs6Mrk, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(crs7Mrk, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(crs8Mrk, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(GenderCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(AlphaSortCheck)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 49, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(SaveButton)
                                        .addGap(106, 106, 106)
                                        .addComponent(NextButton))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(ListStudents)
                                            .addComponent(rbmin)
                                            .addComponent(rbmed)
                                            .addComponent(rbALL)
                                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(48, 48, 48)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(rbGr9)
                                            .addComponent(rbGr10)
                                            .addComponent(rbGr11)
                                            .addComponent(rbGr12)
                                            .addComponent(rbAll)
                                            .addComponent(jLabel20))
                                        .addGap(29, 29, 29)))
                                .addGap(5, 5, 5))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(PrevButton)
                                .addGap(150, 150, 150)
                                .addComponent(AddButton)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(353, 353, 353)
                .addComponent(FindSearchResult, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(stuName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(StuNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(StuNumText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(GradeNumText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel19)
                            .addComponent(lastText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(address)
                            .addComponent(AddressText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(YearCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MonthCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DayCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(city)
                            .addComponent(CityText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AgeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(ProvinceText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(GenderCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PCText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel8)
                            .addComponent(AverageGradeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane3))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(PhoneText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(AlphaSortCheck)))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlabel14)
                            .addComponent(crs5Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(crs1Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(crs1Mrk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(crs5Mrk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(crs2Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(crs6Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(crs2Mrk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(crs6Mrk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(crs7Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(crs3Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(crs3Mrk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(crs7Mrk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(crs8Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(crs4Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(crs4Mrk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(crs8Mrk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ListStudents)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbGr9)
                            .addComponent(rbmin))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbGr10)
                            .addComponent(rbmed))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbGr11)
                            .addComponent(rbALL))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(rbGr12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbAll))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(41, 41, 41)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PrevButton)
                    .addComponent(AddButton)
                    .addComponent(DelButton)
                    .addComponent(EditButton)
                    .addComponent(SaveButton)
                    .addComponent(NextButton))
                .addGap(38, 38, 38)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FindbyNumButton)
                    .addComponent(FindbyNameButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(FindSearchResult)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Students", jPanel5);

        jButton3.setText("Edit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        saveButton2.setText("Save");
        saveButton2.setEnabled(false);
        saveButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButton2ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                courseRecord[0],
                courseRecord[1],
                courseRecord[2],
                courseRecord[3],
                courseRecord[4],
                courseRecord[5],
                courseRecord[6]

            },
            new String [] {
                "Code", "Grade", "Description"
            }
        ));
        jTable1.setEnabled(false);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 838, Short.MAX_VALUE)
                        .addComponent(saveButton2))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 947, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(saveButton2))
                .addGap(22, 22, 22))
        );

        jTabbedPane2.addTab("Courses", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1010, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 753, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CityTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CityTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CityTextActionPerformed

    private void StuNameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StuNameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StuNameTextActionPerformed

    private void StuNumTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StuNumTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StuNumTextActionPerformed

    private void GradeNumTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GradeNumTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GradeNumTextActionPerformed

    private void PhoneTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PhoneTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PhoneTextActionPerformed

    private void AgeTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgeTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AgeTextActionPerformed

    private void EditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditButtonActionPerformed
       bComponentUse(true);
    }//GEN-LAST:event_EditButtonActionPerformed

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
       bComponentUse(false);
       writeToFile(stuFile);
       getFileInfo(stuFile);
       popCourseCombos();
       displayStudentInfo(current);
       
    }//GEN-LAST:event_SaveButtonActionPerformed

    private void crs7ComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crs7ComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_crs7ComboActionPerformed

    private void lastTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lastTextActionPerformed

    private void saveButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButton2ActionPerformed
        bTableUse(false);
        courseEdit();
        writeToFile(courseFile);
       getFileInfo(courseFile);
       tableCreation();
       popCourseCombos();
       displayStudentInfo(current);
    }//GEN-LAST:event_saveButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        bTableUse(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void AlphaSortCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlphaSortCheckActionPerformed
        if(rbGr9.isSelected()){
            System.out.println("pineapple");
            sortStudents(grade9);           
        }
        else if(rbGr10.isSelected()){
            sortStudents(grade10);
        }
        else if(rbGr11.isSelected()){
            sortStudents(grade11);
        }
        else if(rbGr12.isSelected()){
            sortStudents(grade12);
        }
        else{
            sortStudents(stuRecord);            
        }
    }//GEN-LAST:event_AlphaSortCheckActionPerformed

    private void PrevButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrevButtonActionPerformed
        if(current == 1){  
        }
        else{
            if(rbGr9.isSelected()){
            int cancelled = current;
            System.out.println(cancelled + "--cancelled");
            
            
            current = prevButt(grade9, current);
            System.out.println(current + "--current");
            if(current != cancelled){
                System.out.println("cancelled2");
                displayStudentInfo(current);
            }
            else{
                System.out.println("actually cancelled");
            }
        }
        else if(rbGr10.isSelected()){
            int cancelled = current;
            System.out.println(cancelled + "--cancelled");
            
            
            current = prevButt(grade10, current);
            System.out.println(current + "--current");
            if(current != cancelled){
                System.out.println("cancelled2");
                displayStudentInfo(current);
            }
            else{
                System.out.println("actually cancelled");
            }
        }
        else if(rbGr11.isSelected()){
            int cancelled = current;
            System.out.println(cancelled + "--cancelled");
            
            
            current = prevButt(grade11, current);
            System.out.println(current + "--current");
            if(current != cancelled){
                System.out.println("cancelled2");
                displayStudentInfo(current);
            }
            else{
                System.out.println("actually cancelled");
            }
        }
        else if(rbGr12.isSelected()){
            int cancelled = current;
            System.out.println(cancelled + "--cancelled");
            
            
            current = prevButt(grade12, current);
            System.out.println(current + "--current");
            if(current != cancelled){
                System.out.println("cancelled2");
                displayStudentInfo(current);
            }
            else{
                System.out.println("actually cancelled");
            }
        }
        else{   
                current--;
                displayStudentInfo(current);
        }
        }  
          
        
    }//GEN-LAST:event_PrevButtonActionPerformed

    private void NextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextButtonActionPerformed
        if(current == studentCap){
            
            }
        else{

     
        if(rbGr9.isSelected()){
            int cancelled = current;
            System.out.println(cancelled + "--cancelled");
            
            
            current = nextButt(grade9, current);
            System.out.println(current + "--current");
            if(current != cancelled){
                System.out.println("cancelled2");
                displayStudentInfo(current);
            }
            else{
                System.out.println("actually cancelled");
            }
        }
        else if(rbGr10.isSelected()){
            int cancelled = current;
            System.out.println(cancelled + "--cancelled");
            
            
            current = nextButt(grade10, current);
            System.out.println(current + "--current");
            if(current != cancelled){
                System.out.println("cancelled2");
                displayStudentInfo(current);
            }
            else{
                System.out.println("actually cancelled");
            }
        }
        else if(rbGr11.isSelected()){
            int cancelled = current;
            System.out.println(cancelled + "--cancelled");
            
            
            current = nextButt(grade11, current);
            System.out.println(current + "--current");
            if(current != cancelled){
                System.out.println("cancelled2");
                displayStudentInfo(current);
            }
            else{
                System.out.println("actually cancelled");
            }
        }
        else if(rbGr12.isSelected()){
            int cancelled = current;
            System.out.println(cancelled + "--cancelled");
            
            
            current = nextButt(grade12, current);
            System.out.println(current + "--current");
            if(current != cancelled){
                System.out.println("cancelled2");
                displayStudentInfo(current);
            }
            else{
                System.out.println("actually cancelled");
            }
        }
        else{
            current++;
            displayStudentInfo(current);
        }
        
        }
            
        
    }//GEN-LAST:event_NextButtonActionPerformed

    private void AddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddButtonActionPerformed
        studentCap++;
        current = studentCap;
        defaultView();
        bComponentUse(true);
    }//GEN-LAST:event_AddButtonActionPerformed

    private void DelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DelButtonActionPerformed
        if(current == 1){
            removeElt(stuRecord, current);
            studentCap--;
            displayStudentInfo(current);
        }
        else{
        removeElt(stuRecord, current);
        studentCap--;
        current--;
        displayStudentInfo(current);
        }
    }//GEN-LAST:event_DelButtonActionPerformed

    private void searchBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchBoxActionPerformed

    private void FindbyNumButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FindbyNumButtonActionPerformed
        foundIndex = 0;
        foundIndex = findStudent(foundIndex);
        if(foundIndex == -1){
            jTextArea1.append("\nNo student with that number was found");
        }
        else{
            FindSearchResult.setEnabled(true);
            jTextArea1.append("\n" + stuRecord[foundIndex][0] + ", " + stuRecord[foundIndex][1] + ", STU#: " + stuRecord[foundIndex][2] + "  Record#" + foundIndex);
        }
    }//GEN-LAST:event_FindbyNumButtonActionPerformed

    private void FindbyNameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FindbyNameButtonActionPerformed
        foundIndex = 1;
        foundIndex = findStudent(foundIndex);
        if(foundIndex == -1){
            jTextArea1.append("\nNo student with that number was found");
        }
        else{
            FindSearchResult.setEnabled(true);
            jTextArea1.append("\n" + stuRecord[foundIndex][0] + ", " + stuRecord[foundIndex][1] + ", STU#: " + stuRecord[foundIndex][2] + "  Record#" + foundIndex);
        }
    }//GEN-LAST:event_FindbyNameButtonActionPerformed

    private void FindSearchResultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FindSearchResultActionPerformed
        FindSearchResult.setEnabled(false);
        jTextArea1.setText("Search Results:");
        current = foundIndex;
        displayStudentInfo(current);
    }//GEN-LAST:event_FindSearchResultActionPerformed

    private void ListStudentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListStudentsActionPerformed
        int jumbalaya = -1;
        if(rbmin.isSelected()){
            jumbalaya = 0;
            ListStudentsMethod(jumbalaya);
        }
        else if(rbmed.isSelected()){
            jumbalaya = 1;
            ListStudentsMethod(jumbalaya);
        }
        else{
            jumbalaya = 2;
            ListStudentsMethod(jumbalaya);
        }
    }//GEN-LAST:event_ListStudentsActionPerformed

    private void rbALLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbALLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbALLActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int p;
        p = (studentCap - jComboBox1.getSelectedIndex());
        current = p;
        displayStudentInfo(current);
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private int findStudent(int gumbo){
        int foundStudentIndex = -1;
        String search = searchBox.getText();
        
        if(gumbo == 0){
            int i = 1;
            while(i <= studentCap){
                if(search.equalsIgnoreCase(stuRecord[i][2])){
                    foundStudentIndex = i;
                    break;
                }
            i++;    
            }
        }
        else{
            int j = 1;
            while(j <= studentCap){                 
                if(search.equalsIgnoreCase(stuRecord[j][0])){
                    foundStudentIndex = j;
                    break;
                }
            j++;    
            }
            
        }
        
        return foundStudentIndex;
        
    }
    
    private int nextButt(String [][] ar, int qwer){
        int cancelled = qwer;
        int cancelled2 = qwer;
        qwer++;
        if(ar == grade9){
            while(qwer < studentCap){
                if(stuRecord[qwer][3].equals("9")){

                    break;
                }
                cancelled2++;
                qwer++;
                if(qwer == studentCap){
                    cancelled2++;
                }
            }
            if(cancelled2 == qwer){
                return cancelled;
            }
            else{
                return qwer;
            }

        }
        else if(ar == grade10){
            while(qwer < studentCap){
                if(stuRecord[qwer][3].equals("10")){
                    break;
                }
                cancelled2++;
                qwer++;
                if(qwer == studentCap){
                    cancelled2++;
                }
            }
            if(cancelled2 == qwer){
                System.out.println("cancelled");
                return cancelled;
            }
            else{
                System.out.println("returned");
                return qwer;
            }
        }
        else if(ar == grade11){
            while(qwer < studentCap){
                if(stuRecord[qwer][3].equals("11")){ 
                    break;
                }
                cancelled2++;
                qwer++;
                if(qwer == studentCap){
                    cancelled2++;
                }
            }
            if(cancelled2 == qwer){
                return cancelled;
            }
            else{
                return qwer;
            }
        }
        else{
            while(qwer < studentCap){
                if(stuRecord[qwer][3].equals("12")){
                    break;
                }
                cancelled2++;
                qwer++;
                if(qwer == studentCap){
                    cancelled2++;
                }
            }
            if(cancelled2 == qwer){
                return cancelled;
            }
            else{
                return qwer;
            }
        }
        
    }
    
    private int prevButt(String[][] ar, int qwer){
        int cancelled = qwer;
        int cancelled2 = qwer;
        qwer--;
        if(ar == grade9){
            while(qwer >= 1){
                if(stuRecord[qwer][3].equals("9")){
                    break;
                }
                cancelled2--;
                qwer--;
                if(qwer == 0){
                    cancelled2--;
                }
            }
            if(cancelled2 == qwer){
                return cancelled;
            }
            else{
                return qwer;
            }

        }
        else if(ar == grade10){
            while(qwer >= 1){
                if(stuRecord[qwer][3].equals("10")){ 
                    break;
                }
                cancelled2--;
                qwer--;
                System.out.println(cancelled2 + " // " + qwer);
                if(qwer == 0){
                    cancelled2--;
                }
            }
            if(cancelled2 == qwer){
                return cancelled;
            }
            else{
                return qwer;
            }
        }
        else if(ar == grade11){
            while(qwer >= 1){
                if(stuRecord[qwer][3].equals("11")){ 
                    break;
                }
                cancelled2--;
                qwer--;
                System.out.println(cancelled2 + " // " + qwer);
                if(qwer == 0){
                    cancelled2--;
                }
            }
            if(cancelled2 == qwer){
                return cancelled;
            }
            else{
                return qwer;
            }
        }
        else{
            while(qwer >= 1){
              System.out.println("studentrecord[qwer][3]" + stuRecord[qwer][3]);  
            System.out.println(cancelled2 + " // " + qwer + "---before");
                if(stuRecord[qwer][3].equals("12")){
                    System.out.println("broke");
                    break;
                }
                cancelled2--;
                qwer--;
                System.out.println(cancelled2 + " // " + qwer + "----after");
                if(qwer == 0){
                 System.out.println("cancelled2 subtract1");
                    cancelled2--;
                }
            }
            if(cancelled2 == qwer){
                return cancelled;
            }
            else{
                return qwer;
            }
        }
        
    }
    
    
    private void ListStudentsMethod(int i){
            jTextArea2.setText("");
        if(i == 0){
            for(int j = 1; j <= studentCap; j++){
                
                jTextArea2.append(stuRecord[j][0] + ", " + stuRecord[j][1]+ " | STU# " +stuRecord[j][2]  + " | Record#" + j + "\n");
            }
        }
        else if(i == 1){
            for(int j = 1; j <= studentCap; j++){
                jTextArea2.append(stuRecord[j][0] + ", " + stuRecord[j][1]+ " | STU# " +stuRecord[j][2]+ " | Grade " +stuRecord[j][3]+ " | " +stuRecord[j][12] +" | DOB: " +stuRecord[j][9]+ "/" +stuRecord[j][10]+ "/" +stuRecord[j][11]+ "  | Record#" + j+ "\n" );
            }
        }
        else {
                for(int j = 1; j <= studentCap; j++){
                
            jTextArea2.append(stuRecord[j][0] + ", " + stuRecord[j][1]+ "  | STU# " +stuRecord[j][2]+ " | Grade " +stuRecord[j][3]+ " | " +stuRecord[j][12] +" | DOB: " +stuRecord[j][9]+ "/" +stuRecord[j][10]+ "/" +stuRecord[j][11]+  " | Address: " +
                    stuRecord[j][4] + ", "+ stuRecord[j][6] + ", " + stuRecord[j][5] + ", "  + stuRecord[j][7] + " | Phone# " + stuRecord[j][8] + " | Record#" + j + "\n" );
                }
            }
        }
     
    
    private void createGradeArrays(){
        int gr9in = 0, gr10in = 0, gr11in = 0, gr12in = 0;
        for(int j = 1; j <= studentCap; j++){
            
                if(stuRecord[j][3].equals("9")){
                    for(int i = 0; i < stuRecord[j].length; i++){
                        grade9[gr9in][i] = stuRecord[j][i];
                    }
                    System.out.println(gr9in + ": added to grade9");
                    System.out.print("  " + grade9[gr9in][0]);
                    gr9in++;
                }
                else if(stuRecord[j][3].equals("10")){
                    for(int i = 0; i < stuRecord[j].length; i++){
                        grade10[gr10in][i] = stuRecord[j][i];
                    }
                    System.out.println(gr10in + ": added to grade10");
                    System.out.print("  " + grade10[gr10in][0]);
                    gr10in++;
                }
                else if(stuRecord[j][3].equals("11")){
                    for(int i = 0; i < stuRecord[j].length; i++){
                        grade11[gr11in][i] = stuRecord[j][i];
                    }
                    System.out.println(gr11in + ": added to grade11");
                    System.out.print("  " + grade11[gr11in][0]);
                    gr11in++;
                }
                else{
                    for(int i = 0; i < stuRecord[j].length; i++){
                        grade12[gr12in][i] = stuRecord[j][i];
                    }
                    System.out.println(gr12in + ": added to grade12");
                    System.out.print("  " + grade12[gr12in][0]);
                    gr12in++;
                }
            }
        }
    
    
    private void displayStudentInfo(int iD){
        String[]ar = stuRecord[iD];
        String[]dob = new String [3]; 
        dob[0] = ar[9];
        dob[1] = ar[10];
        dob[2] = ar[11];
        
        double year, month, day, m1, m2, m3, m4, m5, m6, m7, m8;
        double age, avg, amt = 8;
        
        StuNameText.setText(ar[1]);
        lastText.setText(ar[0]);
        StuNumText.setText(ar[2]);
        GradeNumText.setText(ar[3]);
        AddressText.setText(ar[4]);
        ProvinceText.setText(ar[5]);
        CityText.setText(ar[6]);
        PCText.setText(ar[7]);
        PhoneText.setText(ar[8]);
        
        YearCombo.setSelectedIndex(isElement(dob[0], YearCombo));
        MonthCombo.setSelectedIndex(isElement(dob[1], MonthCombo));
        DayCombo.setSelectedIndex(isElement(dob[2], DayCombo));
        
        year = Double.parseDouble(YearCombo.getSelectedItem().toString());
        month = Double.parseDouble(MonthCombo.getSelectedItem().toString());
        day = Double.parseDouble(DayCombo.getSelectedItem().toString());
        
        age = ((todayY * 365.25 + (todayM * 30) + todayD) - (year * 365.25 + month*30 + day))/365.25;
        
        AgeText.setText((int) age + "");
        
        GenderCombo.setSelectedIndex(isElement(ar[12], GenderCombo));
        
        crs1Combo.setSelectedIndex(isElement(ar[13], crs1Combo));
        crs2Combo.setSelectedIndex(isElement(ar[15], crs2Combo));
        crs3Combo.setSelectedIndex(isElement(ar[17], crs3Combo));
        crs4Combo.setSelectedIndex(isElement(ar[19], crs4Combo));
        crs5Combo.setSelectedIndex(isElement(ar[21], crs5Combo));
        crs6Combo.setSelectedIndex(isElement(ar[23], crs6Combo));
        crs7Combo.setSelectedIndex(isElement(ar[25], crs7Combo));
        crs8Combo.setSelectedIndex(isElement(ar[27], crs8Combo));
        
        crs1Mrk.setText(ar[14]);
        crs2Mrk.setText(ar[16]);
        crs3Mrk.setText(ar[18]);
        crs4Mrk.setText(ar[20]);
        crs5Mrk.setText(ar[22]);
        crs6Mrk.setText(ar[24]);
        crs7Mrk.setText(ar[26]);
        crs8Mrk.setText(ar[28]);
        
        if(crs1Mrk.getText() == null){
            amt--;
        }
        if(crs2Mrk.getText() == null){
            amt--;
        }
        if(crs3Mrk.getText() == null){
            amt--;
        }
        if(crs4Mrk.getText() == null){
            amt--;
        }
        if(crs5Mrk.getText() == null){
            amt--;
        }
        if(crs6Mrk.getText() == null){
            amt--;
        }
        if(crs7Mrk.getText() == null){
            amt--;
        }
        if(crs8Mrk.getText() == null){
            amt--;
        }
        
        m1 = Double.parseDouble(crs1Mrk.getText());
        m2 = Double.parseDouble(crs2Mrk.getText());
        m3 = Double.parseDouble(crs3Mrk.getText());
        m4 = Double.parseDouble(crs4Mrk.getText());
        m5 = Double.parseDouble(crs5Mrk.getText());
        m6 = Double.parseDouble(crs6Mrk.getText());
        m7 = Double.parseDouble(crs7Mrk.getText());
        m8 = Double.parseDouble(crs8Mrk.getText());

        avg = (m1 + m2 + m3 + m4 + m5 + m6 + m7 + m8) / amt;
        
        AverageGradeText.setText((int)avg +"");
    }
    
    private void defaultView(){
        StuNameText.setText("");
        lastText.setText("");
        StuNumText.setText("");
        GradeNumText.setText("");
        AddressText.setText("");
        ProvinceText.setText("");
        CityText.setText("");
        PCText.setText("");
        PhoneText.setText("");
        
                
        AgeText.setText("");
        
        crs1Mrk.setText("");
        crs2Mrk.setText("");
        crs3Mrk.setText("");
        crs4Mrk.setText("");
        crs5Mrk.setText("");
        crs6Mrk.setText("");
        crs7Mrk.setText("");
        crs8Mrk.setText("");
        AverageGradeText.setText("");
    }
        
    
    private void sortStudents(String[][] ar){
        String [] toBeSorted = new String[1000];
        int iloopCap = 0;
        
        while(iloopCap < ar.length){
            if(ar[iloopCap][0] == null){
                System.out.println(iloopCap);
                break; 
            }
            iloopCap++;
        }
        
        
        if(ar == stuRecord){
            jTextArea2.setText("");
        
        for(int i = 0; i < studentCap; i++){
            toBeSorted[i] = (ar[i+1][0]+ ", " + ar[i+1][1] +  ",   STU#: "+ ar[i+1][2] + "   | Record#" + (i+1));
            System.out.println(toBeSorted[i]);
            System.out.println(i + "//" + studentCap);
        }
        
        Arrays.sort(toBeSorted, 0, studentCap);
        
        for(int i = 0; i < studentCap; i++){
            jTextArea2.append(toBeSorted[i] + "\n");
            System.out.println(toBeSorted[i] + "\n");
            System.out.println(i + "//" + studentCap);
        }
        
        }
        else if(ar == grade9 || ar == grade10 || ar == grade11 || ar == grade12){
            jTextArea2.setText("");
            
            for(int i = 0; i < iloopCap; i++){
                toBeSorted[i] = (ar[i][0]+ ", " + ar[i][1] + "   | Record#" + (i+1));
                System.out.println(toBeSorted[i]);
                System.out.println(i + "//" + iloopCap);
            }
        
            Arrays.sort(toBeSorted, 0, iloopCap);
        
            for(int i = 0; i < iloopCap; i++){
                jTextArea2.append(toBeSorted[i] + "\n");
                System.out.println(toBeSorted[i] + "\n");
                System.out.println(i + "//" + iloopCap);
            }
        }
    }
    
    private void courseEdit(){
        coursecap = 0;
        
        
        for(int i = 0; i < 50; i++){
            for(int j = 0; j < courseRecord[0].length; j++){
                courseRecord[i][j] = (String) jTable1.getModel().getValueAt(i, j);
            }
        }
        
        while(coursecap < 50){
            if(courseRecord[coursecap][0] == null){
                break;
                
            }
            coursecap++;
        }
    }
        
    private void bComponentUse(boolean usable){
        AddressText.setEditable(usable);
        CityText.setEditable(usable);
        DayCombo.setEnabled(usable);
        GenderCombo.setEnabled(usable);
        GradeNumText.setEditable(usable);
        MonthCombo.setEnabled(usable);
        PCText.setEditable(usable);
        PhoneText.setEditable(usable);
        ProvinceText.setEditable(usable);
        YearCombo.setEnabled(usable);
        StuNameText.setEditable(usable);
        lastText.setEditable(usable);
        StuNumText.setEditable(usable);
        SaveButton.setEnabled(usable);
        crs1Combo.setEnabled(usable);
        crs2Combo.setEnabled(usable);
        crs3Combo.setEnabled(usable);
        crs4Combo.setEnabled(usable);
        crs5Combo.setEnabled(usable);
        crs6Combo.setEnabled(usable);
        crs7Combo.setEnabled(usable);
        crs8Combo.setEnabled(usable);
        crs1Mrk.setEditable(usable);
        crs2Mrk.setEditable(usable);
        crs3Mrk.setEditable(usable);
        crs4Mrk.setEditable(usable);
        crs5Mrk.setEditable(usable);
        crs6Mrk.setEditable(usable);
        crs7Mrk.setEditable(usable);
        crs8Mrk.setEditable(usable);
        EditButton.setEnabled(!usable);
    }
    
    private void bTableUse(boolean edit){
        jTable1.setEnabled(edit);
        jButton3.setEnabled(!edit);
        saveButton2.setEnabled(edit);
    }
    
    private int isElement(String name, JComboBox box){
        int ID = -1;
        
        for(int i = 0; i < box.getItemCount(); i++){
            if(box.getItemAt(i).toString().equalsIgnoreCase(name)){
                ID = i;
                break;
            }
        }
        
        return ID;
    }
    
    private void popCourseCombos(){
        
       crs1Combo.removeAllItems();
       crs2Combo.removeAllItems();
       crs3Combo.removeAllItems();
       crs4Combo.removeAllItems();
       crs5Combo.removeAllItems();
       crs6Combo.removeAllItems();
       crs7Combo.removeAllItems();
       crs8Combo.removeAllItems();
       jComboBox1.removeAllItems();
       
        for(int i = 0; i < coursecap; i++){
            crs1Combo.addItem(courseRecord[i][0]);
            crs2Combo.addItem(courseRecord[i][0]);
            crs3Combo.addItem(courseRecord[i][0]);
            crs4Combo.addItem(courseRecord[i][0]);
            crs5Combo.addItem(courseRecord[i][0]);
            crs6Combo.addItem(courseRecord[i][0]);
            crs7Combo.addItem(courseRecord[i][0]);
            crs8Combo.addItem(courseRecord[i][0]);
        }
        for(int i = studentCap; i >= 1; i--){
            jComboBox1.addItem(stuRecord[i][0]);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentRecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    //</editor-fold>
    
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentRecord().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddButton;
    private javax.swing.JTextField AddressText;
    private javax.swing.JTextField AgeText;
    private javax.swing.JCheckBox AlphaSortCheck;
    private javax.swing.JTextField AverageGradeText;
    private javax.swing.JTextField CityText;
    private javax.swing.JComboBox<String> DayCombo;
    private javax.swing.JButton DelButton;
    private javax.swing.JButton EditButton;
    private javax.swing.JButton FindSearchResult;
    private javax.swing.JButton FindbyNameButton;
    private javax.swing.JButton FindbyNumButton;
    private javax.swing.JComboBox<String> GenderCombo;
    private javax.swing.JTextField GradeNumText;
    private javax.swing.JButton ListStudents;
    private javax.swing.JComboBox<String> MonthCombo;
    private javax.swing.JButton NextButton;
    private javax.swing.JTextField PCText;
    private javax.swing.JTextField PhoneText;
    private javax.swing.JButton PrevButton;
    private javax.swing.JTextField ProvinceText;
    private javax.swing.JButton SaveButton;
    private javax.swing.JTextField StuNameText;
    private javax.swing.JTextField StuNumText;
    private javax.swing.JComboBox<String> YearCombo;
    private javax.swing.JLabel address;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel city;
    private javax.swing.JComboBox<String> crs1Combo;
    private javax.swing.JTextField crs1Mrk;
    private javax.swing.JComboBox<String> crs2Combo;
    private javax.swing.JTextField crs2Mrk;
    private javax.swing.JComboBox<String> crs3Combo;
    private javax.swing.JTextField crs3Mrk;
    private javax.swing.JComboBox<String> crs4Combo;
    private javax.swing.JTextField crs4Mrk;
    private javax.swing.JComboBox<String> crs5Combo;
    private javax.swing.JTextField crs5Mrk;
    private javax.swing.JComboBox<String> crs6Combo;
    private javax.swing.JTextField crs6Mrk;
    private javax.swing.JComboBox<String> crs7Combo;
    private javax.swing.JTextField crs7Mrk;
    private javax.swing.JComboBox<String> crs8Combo;
    private javax.swing.JTextField crs8Mrk;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel jlabel14;
    private javax.swing.JTextField lastText;
    private javax.swing.JRadioButton rbALL;
    private javax.swing.JRadioButton rbAll;
    private javax.swing.JRadioButton rbGr10;
    private javax.swing.JRadioButton rbGr11;
    private javax.swing.JRadioButton rbGr12;
    private javax.swing.JRadioButton rbGr9;
    private javax.swing.JRadioButton rbmed;
    private javax.swing.JRadioButton rbmin;
    private javax.swing.JButton saveButton2;
    private javax.swing.JTextField searchBox;
    private javax.swing.JLabel stuName;
    // End of variables declaration//GEN-END:variables
}
