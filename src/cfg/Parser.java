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
    String error = "";
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
                           // System.out.println((k++) + ":" + pr[j] + ";");
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
           // variableParser();
      //  System.out.println("vars");


            // Always close files.
           bufferedReader.close();
//
            Graph2 graph = new Graph2(lines);
            System.out.println();

            graph.buildGraph();
	}

    public String checkCharAssignment(String line){
        String res="";
        if(line.contains("=")){
            String temp[] = line.split("=");
            temp[1] =  temp[1].trim();
         //   System.out.println(temp[1]);
            for( int j=0;j<temp[1].length();j++) {
                if(temp[1].charAt(j) == ';')
                    break;
                else
                    res+=temp[1].charAt(j);
            }
       //     System.out.println("res"+res);
            return res.substring(1,res.length()-1);

        }
        return null;
    }

    public String checkAssignment(String line){
        String res="";
        if(line.contains("=")){
            String temp[] = line.split("=");
            temp[1] =  temp[1].trim();
          //  System.out.println(temp[1]);
            for( int j=0;j<temp[1].length();j++) {
                if(temp[1].charAt(j) == ';')
                    break;
                else
                    res+=temp[1].charAt(j);
            }
          //  System.out.println("res"+res);
            return res;

        }
        return null;
    }

    public void addVariables(int i){
        Pattern p = Pattern.compile("(int|char|float|double)(.*)");

        Matcher m = p.matcher(lines.get(i));
        if (m.find()) {
            if(!lines.contains("printf"))
           // System.out.println("jell"+lines.get(i));
          //  System.out.println("found");

       //     System.out.println(lines.get(i));

            if(lines.get(i).contains("int")){
                String temp = lines.get(i);
                int ind = lines.get(i).indexOf("int");
             //   System.out.println(ind);
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
            //    System.out.println(ind);
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
             //   System.out.println(ind);
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
              //  System.out.println(ind);
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
       //     System.out.println("found");

        //    System.out.println(lines.get(i));

            if(lines.get(i).contains("int")){
                String temp = lines.get(i);
                int ind = lines.get(i).indexOf("int");
              //  System.out.println(ind);
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
               // System.out.println(ind);
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
             //   System.out.println(ind);
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
           //     System.out.println(ind);
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

//                for( i=0;i<t.size();i++){
//                    System.out.println(t.get(i));
//                }
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
         //   System.out.println(temp[1]);
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


    public ArrayVar getArrayVariableValue(String name){
        ArrayVar v = null;
        for(int i=0;i<vars.size();i++){
            if(name.equals(vars.get(i).name)){

                if(vars.get(i) instanceof ArrayVar ){
                   v =  ((ArrayVar) vars.get(i));
                }
            }
        }
        return v;
    }

    public NonArrayVar  getVariableValue(String name){
        NonArrayVar v = null;
        for(int i=0;i<vars.size();i++){
            if(name.equals(vars.get(i).name)){

                if(vars.get(i) instanceof NonArrayVar ){
                    v =  ((NonArrayVar) vars.get(i));
                }
            }
        }
        return v;
    }

    public boolean isEnclosedByQuotation(String str){
        str = str.trim();
        if(str.charAt(0)== '"' && str.charAt(str.length()-1) == '"') return true;
        else return false;
    }

    public boolean isArray(String line){
       if(line.contains("[")){
           return true;
       }
       else return false;
    }

    public String getArrayName( String line){
        line = line.trim();
        String res = "";
        for (int i=0;i<line.length();i++){
            if(line.charAt(i)=='[') break;
            else res+=line.charAt(i);
        }
        return res;
    }

    public int getArrayIndex(String line){
        line = line.trim();
        String res = "";
        for (int i=0;i<line.length();i++){
            if(line.charAt(i)=='[') {
                i++;
                while(line.charAt(i)!=']'){

                    res+=line.charAt(i);
                    i++;
                }
            }

        }
        if(isNumeric((res)))
        return Integer.parseInt(res);
        else return Integer.parseInt(getValue(res));
    }

    public String getValueOfArray(String line){
        String res = "";
        ArrayVar a = getArrayVariableValue(getArrayName(line));
        if(a!=null) {
            return a.getValue().get(getArrayIndex(line));
           // System.out.println(a.getValue().get(getArrayIndex(line)));
        }
        return null;

    }

    public void setValueOfArray(String line, String value){
        String res = "";
        ArrayVar a = getArrayVariableValue(getArrayName(line));
        if(a!=null) {
            a.getValue().set(getArrayIndex(line), value);
           // System.out.println("found array"+value);
        }

       // System.out.println("array"+a.getValue().get(getArrayIndex(line)));



    }


    public boolean isAssignment(String line){
        if(!(line.contains("int")|| line.contains("float") || line.contains("double") || line.contains("char"))) {
            return true;
        }
        return false;
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
    public String getValue(String t){
        for(int i=0;i<vars.size();i++){
            if(vars.get(i).name.equals(t))
                return ((NonArrayVar)vars.get(i)).value;
        }
        return null;
    }

    public void setVariableValue(String t, String value){

        for(int i=0;i<vars.size();i++){
          //  System.out.println(t + " "+ vars.get(i).name);
            if(vars.get(i).name.equals(t)) {
                //System.out.println("wn");
                ((NonArrayVar) vars.get(i)).setValue(value);
               // System.out.println("val " + ((NonArrayVar) vars.get(i)).value);
                break;
            }

        }
    }

    public void addVariablesAssignment(int i) {
       if(lines.get(i).contains("=")){
           String line = lines.get(i);
           String t[] = line.split("=");

           t[1] = t[1].substring(0,t[1].length()-1);
           t[1]=t[1].trim();
           t[0] = t[0].trim();

           if(isAssignment(t[0])) {
              // System.out.println("lines"+lines.get(i));
               //System.out.println("\n\n"+t[1]);
               if (isArray(t[1])) {
                  // System.out.println("array\n\n");
                   //System.out.println(getValueOfArray(t[1]));
                   if(isArray(t[0])){
                       setValueOfArray(t[0], getValueOfArray(t[1]));
                   }
                   else{
                       setVariableValue(t[0], getValueOfArray(t[1]));
                   }

               }
               else if(isNumeric(t[1])){

                   if(isArray(t[0])){

                       setValueOfArray(t[0], t[1]);
                   }
                   else{
                       setVariableValue(t[0], t[1]);
                   }
               }
               else{
                   if(isArray(t[0])){

                       setValueOfArray(t[0], getValue(t[1]));
                   }
                   else{
                       setVariableValue(t[0],getValue(t[1]));
                   }
               }

           }
       }
    }


    public void variableParser(){
     //   System.out.println("helloo");


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
            //addVariablesAssignment(i);
//            if(Pattern.matches("(?:\\w+\\s+)([a-zA-Z_][a-zA-Z0-9_]*)", lines.get(i))){
//                System.out.println(lines.get(i));
//            }



//            if(lines.get(i).contains("=")){
//                System.out.println(lines.get(i));
//            }
        }

        for( int i=0;i<vars.size();i++) {
            Variable v = vars.get(i);
            if (v instanceof ArrayVar) {
                ArrayVar a = (ArrayVar)v;
                System.out.println(vars.get(i).type + " " + vars.get(i).name + " ");
                for(int j=0;j<a.value.size();j++){
                    System.out.println(a.value.get(j));
                }
            }
        }

        for(int i=0;i<lines.size();i++) {

            // addVariablesDeclaration(i);
            //  addArray(i);




            addVariablesAssignment(i);


//            if(Pattern.matches("(?:\\w+\\s+)([a-zA-Z_][a-zA-Z0-9_]*)", lines.get(i))){
//                System.out.println(lines.get(i));
//            }
        }


//        boolean b = Pattern.matches(", "as");

    }
}