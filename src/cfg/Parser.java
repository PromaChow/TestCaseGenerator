package cfg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    ArrayList<String> lines = new ArrayList<>();
    ArrayList<Variable> vars = new ArrayList<>();
	public Parser(String path) throws IOException {

		FileReader fileReader = 
                new FileReader(path);
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            String line;
            int i=0;
			while((line = bufferedReader.readLine()) != null) {


                if(!line.contains("for")){
                    int k = i;
                    if(line.contains(";")) {
                        String pr[] = line.split(";");
                        for (int j = 0; j < pr.length; j++) {

                            lines.add(pr[j] + ";");
                            //System.out.println((k++) + ":" + pr[j] + ";");
                        }
                    }
                    else
                    lines.add(line);
                }
                else
                lines.add(line);
                i++;
            }

            for( i=0;i<lines.size();i++){
                System.out.println("lines"+ i+" : "+ lines.get(i));
            }
            variableParser();
        System.out.println("vars");

            for( i=0;i<vars.size();i++) {
                Variable v = vars.get(i);
                if (v instanceof ArrayVar) {
                    ArrayVar a = (ArrayVar)v;
                    System.out.println(vars.get(i).type + " " + vars.get(i).name + " ");
                    for(int j=0;j<a.value.size();j++){
                        System.out.println(a.value.get(j));
                    }
                }
            }
            // Always close files.
//            bufferedReader.close();
//
//            Graph2 graph = new Graph2(lines);
//            System.out.println();
//            System.out.println("The graph:");
//            graph.buildGraph();
	}

    public String checkCharAssignment(String line){
        String res="";
        if(line.contains("=")){
            String temp[] = line.split("=");
            temp[1] =  temp[1].trim();
            System.out.println(temp[1]);
            for( int j=0;j<temp[1].length();j++) {
                if(temp[1].charAt(j) == ';')
                    break;
                else
                    res+=temp[1].charAt(j);
            }
            System.out.println("res"+res);
            return res.substring(1,res.length()-1);

        }
        return null;
    }

    public String checkAssignment(String line){
        String res="";
        if(line.contains("=")){
            String temp[] = line.split("=");
            temp[1] =  temp[1].trim();
            System.out.println(temp[1]);
            for( int j=0;j<temp[1].length();j++) {
                if(temp[1].charAt(j) == ';')
                    break;
                else
                    res+=temp[1].charAt(j);
            }
            System.out.println("res"+res);
            return res;

        }
        return null;
    }

    public void addVariables(int i){
        Pattern p = Pattern.compile("(int|char|float|double)(.*)");

        Matcher m = p.matcher(lines.get(i));
        if (m.find()) {
            System.out.println("found");

            System.out.println(lines.get(i));

            if(lines.get(i).contains("int")){
                String temp = lines.get(i);
                int ind = lines.get(i).indexOf("int");
                System.out.println(ind);
                int j = ind+3;
                for(;j<temp.length();j++){
                    if(temp.charAt(j)!= ' '){
                        break;

                    }
                    // j++;
                }

                String name = "";

                for(int k=j;k<temp.length();k++){
                    if(temp.charAt(k) == ' ' || temp.charAt(k) == ';'){
                        break;
                    }
                    else name+=temp.charAt(k);
                }
                //System.out.println("s"+name);


                String t = checkAssignment(lines.get(i));
                if(t!=null){
                    vars.add(new NonArrayVar(name, true, "int", t));
                }
                else{
                    vars.add(new NonArrayVar(name, true, "int"));
                }
            }

            if(lines.get(i).contains("char")){
                String temp = lines.get(i);
                int ind = lines.get(i).indexOf("char");
                System.out.println(ind);
                int j = ind+4;
                for(;j<temp.length();j++){
                    if(temp.charAt(j)!= ' '){
                        break;

                    }
                    // j++;
                }

                String name = "";

                for(int k=j;k<temp.length();k++){
                    if(temp.charAt(k) == ' ' || temp.charAt(k) == ';'){
                        break;
                    }
                    else name+=temp.charAt(k);
                }
                //   System.out.println(name);

                String t = checkCharAssignment(lines.get(i));
                if(t!=null){
                    vars.add(new NonArrayVar(name, true, "char", t));
                }
                else{
                    vars.add(new NonArrayVar(name, true, "char"));
                }
            }


            if(lines.get(i).contains("float")){
                String temp = lines.get(i);
                int ind = lines.get(i).indexOf("float");
                System.out.println(ind);
                int j = ind+5;
                for(;j<temp.length();j++){
                    if(temp.charAt(j)!= ' '){
                        break;

                    }
                    // j++;
                }

                String name = "";

                for(int k=j;k<temp.length();k++){
                    if(temp.charAt(k) == ' ' || temp.charAt(k) == ';'){
                        break;
                    }
                    else name+=temp.charAt(k);
                }
                //   System.out.println(name);

                String t = checkAssignment(lines.get(i));
                if(t!=null){
                    vars.add(new NonArrayVar(name, true, "float", t));
                }
                else{
                    vars.add(new NonArrayVar(name, true, "float"));
                }
            }

            if(lines.get(i).contains("double")){
                String temp = lines.get(i);
                int ind = lines.get(i).indexOf("double");
                System.out.println(ind);
                int j = ind+6;
                for(;j<temp.length();j++){
                    if(temp.charAt(j)!= ' '){
                        break;

                    }
                    // j++;
                }

                String name = "";

                for(int k=j;k<temp.length();k++){
                    if(temp.charAt(k) == ' ' || temp.charAt(k) == ';'){
                        break;
                    }
                    else name+=temp.charAt(k);
                }
                //   System.out.println(name);

                String t = checkAssignment(lines.get(i));
                if(t!=null){
                    vars.add(new NonArrayVar(name, true, "double", t));
                }
                else{
                    vars.add(new NonArrayVar(name, true, "double"));
                }
            }



        }

    }


    public void addArray(int i) {
        Pattern p = Pattern.compile("(int|char|float|double)(.*)(\\[.*\\])");
        Matcher m = p.matcher(lines.get(i));
        if (m.find()) {
            System.out.println("found");

            System.out.println(lines.get(i));

            if(lines.get(i).contains("int")){
                String temp = lines.get(i);
                int ind = lines.get(i).indexOf("int");
                System.out.println(ind);
                int j = ind+3;
                for(;j<temp.length();j++){
                    if(temp.charAt(j)!= ' '){
                        break;

                    }
                    // j++;
                }

                String name = "";

                for(int k=j;k<temp.length();k++){
                    if(temp.charAt(k) == ' ' || temp.charAt(k) == '['){
                        break;
                    }
                    else name+=temp.charAt(k);
                }
                //System.out.println("s"+name);


                ArrayList t = checkArrayAssignment(lines.get(i));
                if(t!=null){
                    vars.add(new ArrayVar(name, true, "int", t));
                }
                else{
                    vars.add(new ArrayVar(name, true, "int"));
                }
            }

            if(lines.get(i).contains("char")){
                String temp = lines.get(i);
                int ind = lines.get(i).indexOf("char");
                System.out.println(ind);
                int j = ind+4;
                for(;j<temp.length();j++){
                    if(temp.charAt(j)!= ' '){
                        break;

                    }
                    // j++;
                }

                String name = "";

                for(int k=j;k<temp.length();k++){
                    if(temp.charAt(k) == ' ' || temp.charAt(k) == '['){
                        break;
                    }
                    else name+=temp.charAt(k);
                }
                //   System.out.println(name);

                ArrayList t = checkCharArrayAssignment(lines.get(i));
               // System.out.println("hello"+t.get(0));
                vars.add(new ArrayVar(name, true, "char", t));
            }


            if(lines.get(i).contains("float")){
                String temp = lines.get(i);
                int ind = lines.get(i).indexOf("float");
                System.out.println(ind);
                int j = ind+5;
                for(;j<temp.length();j++){
                    if(temp.charAt(j)!= ' '){
                        break;

                    }
                    // j++;
                }

                String name = "";

                for(int k=j;k<temp.length();k++){
                    if(temp.charAt(k) == ' ' || temp.charAt(k) == '['){
                        break;
                    }
                    else name+=temp.charAt(k);
                }
                //   System.out.println(name);

                ArrayList t = checkArrayAssignment(lines.get(i));
                if(t!=null){
                    vars.add(new ArrayVar(name, true, "float", t));
                }
                else{
                    vars.add(new ArrayVar(name, true, "float"));
                }
            }

            if(lines.get(i).contains("double")){
                String temp = lines.get(i);
                int ind = lines.get(i).indexOf("double");
                System.out.println(ind);
                int j = ind+6;
                for(;j<temp.length();j++){
                    if(temp.charAt(j)!= ' '){
                        break;

                    }
                    // j++;
                }

                String name = "";

                for(int k=j;k<temp.length();k++){
                    if(temp.charAt(k) == ' ' || temp.charAt(k) == '['){
                        break;
                    }
                    else name+=temp.charAt(k);
                }
                //   System.out.println(name);

                ArrayList t = checkArrayAssignment(lines.get(i));

                for( i=0;i<t.size();i++){
                    System.out.println(t.get(i));
                }
                if(t!=null){
                    vars.add(new ArrayVar(name, true, "double", t));
                }
                else{
                    vars.add(new ArrayVar(name, true, "double"));
                }
            }



        }

    }


    public ArrayList checkCharArrayAssignment( String line){
        ArrayList<String> res = new ArrayList<>();
        if(line.contains("=")){
            String temp[] = line.split("=");
            temp[1] =  temp[1].trim();

            res.add(temp[1].substring(1,temp[1].length()-1));
//            System.out.println("asd");
//            System.out.println(res.get(0));

        }
        return res;
    }

    public ArrayList checkArrayAssignment( String line){
        ArrayList<String> res = new ArrayList<>();
        if(line.contains("=")){
            String temp[] = line.split("=");
            temp[1] =  temp[1].trim();

            String temp_1 = "";
            System.out.println(temp[1]);
            for( int j=0;j<temp[1].length();j++) {
                if(temp[1].charAt(j) != '{' && temp[1].charAt(j) != '}' && temp[1].charAt(j) != ';') temp_1+=temp[1].charAt(j);
            }
            String t[] = temp_1.split(",");
            for(int i = 0; i<t.length;i++) {
                res.add(t[i].trim());
            }

        }
        return res;
    }

    public void addVariablesDeclaration(int i) {
        Pattern p = Pattern.compile("(.*)(\s)*(=)");
        Matcher m = p.matcher(lines.get(i));
        if (m.find()) {
            System.out.println("found");

            System.out.println(lines.get(i));

        }
    }


    public void variableParser(){
        System.out.println("helloo");


        for(int i=0;i<lines.size();i++){

           // addVariablesDeclaration(i);
          //  addArray(i);

            Pattern p = Pattern.compile("(int|char|float|double)(.*)(\\[.*\\])");
            Matcher m = p.matcher(lines.get(i));
            Pattern p1 = Pattern.compile("(int|char|float|double)(.*)");
            Matcher m1 = p1.matcher(lines.get(i));

            if (m.find()) {
               // System.out.println("hi");
                addArray(i);
            }
            else if(m1.find()){
                addVariables(i);
            }

//            if(Pattern.matches("(?:\\w+\\s+)([a-zA-Z_][a-zA-Z0-9_]*)", lines.get(i))){
//                System.out.println(lines.get(i));
//            }



//            if(lines.get(i).contains("=")){
//                System.out.println(lines.get(i));
//            }
        }
//        boolean b = Pattern.matches(", "as");

    }
}