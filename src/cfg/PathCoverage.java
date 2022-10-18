package cfg;

import javax.imageio.IIOException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class PathCoverage {
    ArrayList<String>lines;
    ArrayList<ArrayList<Node>>path;
    ArrayList<TestCases>test;
    Parser p;
    String res = "";
    String input = "";

    public PathCoverage(ArrayList<String> lines, ArrayList<ArrayList<Node>> path)  {
        this.lines = lines;
        this.path = path;
        String str = "test3.txt";
        //this.p = new Parser(str);


    }
    public static boolean isNumeric(String strNum) {
        //strNum = strNum.substring(0,strNum.length()-1);
        strNum = strNum.trim();
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public void parseWhole(){
        ArrayList<Node>N;
        ArrayList<Integer> tempLine = new ArrayList<>();

        for(int i=0;i< path.size();i++){
            N = path.get(i);
            for(int j=0;j<N.size();j++) {

                //tempLine = N.get(j).lines;
                for(int k=0;k<N.get(j).lines.size();k++){
                    tempLine.add(N.get(j).lines.get(k));
                    // System.out.println(lines.get(tempLine.get(k)));
                }

            }

            input = "";
            res = "";
            parseLine(tempLine);
            tempLine.clear();
            //   System.out.println(tempLine.size());
        }

    }

    public String preprocess(String t){
        t = t.replace(")","");
        t = t.replace(" ","");
        t = t.replace("{","");
        t = t.trim();
        return t;
    }
    public void parseLine(ArrayList<Integer> a) {
        ArrayList<String>in = new ArrayList<>();
        ArrayList<String>out = new ArrayList<>();

        //System.out.println(a.size());
        //  System.out.println(input);
        for (int i = 0; i < a.size(); i++) {
            int lower, higher;
            String line = lines.get(a.get(i));
            //  System.out.println(line);
            if(line.contains("for")){
                String t[] = line.split(";");
                System.out.println(t[0]);
                String l[] = t[0].split("=");
                System.out.println(l[1]);
                String m[] = t[1].split("<");
                System.out.println(m[1]);

                if(t[2].contains("++")){
                    System.out.println(t[2]);
                    lower = Integer.parseInt(l[1]);
                    higher = Integer.parseInt(m[1]);
                    System.out.println("he");
                    //System.out.println(lower+" "higher);
                }

                else if(t[2].contains("--")){
                    System.out.println(t[2]);
                    lower = Integer.parseInt(m[1]);
                    higher = Integer.parseInt(l[1]);
                }



            }

            if (line.contains("if")) {
                boolean elseVar = false;
                if(lines.get(a.get(i+1)).contains("else")){
                    elseVar = true;
                    //System.out.println(elseVar);
                }
                if (line.contains(">=")) {
                    String t[] = line.split(">=");
                    t[1] = preprocess(t[1]);

                    if(!elseVar) {
                        input += t[1];
                        in.add(t[1]);

                    }

                    else {
                        //     input = "";
                        //System.out.println(Integer.parseInt(t[1]) - 1);
                        input += (Integer.parseInt(t[1]) - 1);
                        in.add(String.valueOf(Integer.parseInt(t[1]) - 1));
                        // System.out.println("in"+input);
                    }
                    //System.out.println();

                    //    System.out.println("hello" + t[1]);
                } else if (line.contains("<=")) {
                    String t[] = line.split("<=");
                    t[1] = preprocess(t[1]);
                    if(!elseVar) {
                        input += t[1];
                        in.add(t[1]);
                    }


                    else {
                        //   input = "";
                        //System.out.println(Integer.parseInt(t[1]) - 1);
                        input += (Integer.parseInt(t[1]) + 1);
                        in.add((Integer.parseInt(t[1]) + 1)+"");
                        // System.out.println("in"+input);
                    }

                } else if (line.contains(">")) {
                    String t[] = line.split(">");
                    t[1] = preprocess(t[1]);
                    if(!elseVar) {
                        if (isNumeric(t[1])) {
                            input += (Integer.parseInt(t[1]) + 1);
                            in.add((Integer.parseInt(t[1]) + 1)+"");
                        }
                        //  System.out.println("in"+input);
                    }
                    else{

                        //  input = "";
                        if (isNumeric(t[1])) {
                            input += (Integer.parseInt(t[1]) - 1);
                            in.add((Integer.parseInt(t[1]) - 1)+"");
                        }
                    }
                } else if (line.contains("<")) {
                    String t[] = line.split("<");
                    t[1] = preprocess(t[1]);
                    if(!elseVar) {
                        if (isNumeric(t[1])) {
                            input += (Integer.parseInt(t[1]) - 1);
                            in.add((Integer.parseInt(t[1]) - 1)+"");
                        }
                    }
                    else{
                        // input = "";
                        if (isNumeric(t[1])) {
                            input += (Integer.parseInt(t[1]));
                            in.add((Integer.parseInt(t[1]) + 1)+"");
                        }
                    }

                } else if (line.contains("==")) {
                    String t[] = line.split("==");
                    t[1] = preprocess(t[1]);
                    if(!elseVar) {
                        if (isNumeric(t[1])) {
                            input += (Integer.parseInt(t[1]));
                            in.add((Integer.parseInt(t[1]))+"");
                        }
                    }
                    else{
                        // input = "";
                        if (isNumeric(t[1])) {
                            input += (Integer.parseInt(t[1])+1);
                            in.add((Integer.parseInt(t[1]) + 1)+"");
                        }
                    }

                } else if (line.contains("!=")) {
                    String t[] = line.split("!=");
                    t[1] = preprocess(t[1]);
                    if(!elseVar) {
                        if (isNumeric(t[1])) {
                            input += (Integer.parseInt(t[1])-1);
                            in.add((Integer.parseInt(t[1]) - 1)+"");
                        }
                    }
                    else{
                        //  input = "";
                        if (isNumeric(t[1])) {
                            input += (Integer.parseInt(t[1]));
                            in.add((Integer.parseInt(t[1]))+"");
                        }
                    }

                }


            }
            else if (line.contains("printf")) {
                String t = line;
                res = "";
                //System.out.println("");
                for (int j = 0; j < t.length(); j++) {
                    if (t.charAt(j) == '"') {
                        j++;
                        while (t.charAt(j) != '"')
                            res += t.charAt(j++);

                    }
                }
                out.add(res);

            }

        }
        System.out.println("Input : ");
        for(int i=0;i<in.size();i++){
            System.out.print(in.get(i));
        }
        System.out.println();

        System.out.println("Output : ");

        for(int i=0;i<out.size();i++){
            System.out.print(out.get(i));
        }
        System.out.println();
        out.clear();
        in.clear();
        // System.out.println("Input "+input+"\nOutput "+res);
    }}
