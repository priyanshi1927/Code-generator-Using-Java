import java.util.*;
 class Main {
    public static String get_ptype(int code) 
    {
        String ptype = "";
        switch (code)
        {
            case 1:
                ptype = "int";
                break;
            case 2:
                ptype = "double";
                break;     
            case 3:
                ptype = "boolean";
                break;                     
        }
        return ptype;
    }
    
    public static String get_pvalue(int code) 
    {
        String pvalue = "";
        switch (code)
        {
            case 1:
            pvalue = "0";
                break;
            case 2:
            pvalue = "0.0";
                break;
            case 3:
            pvalue = "True";    
        }
        return pvalue;
    }

    public static String get_pstring(int n, int param_type[], String param_name[])
    {
        String code = "";
        for ( int i = 0 ; i < n ; i++)
        {
            if ( i > 0)
                code = code + ", "; 
            String param2_type_name = get_pvalue(param_type[i]);
            code = code + param2_type_name + " " + param_name[i];    
        }
        return code;
    }
    
    public static void code_print(String myfun, int rettype, int n, int param_type[], String param_name[])
    {
        String code = get_funsign(myfun,  rettype,  n,  param_type,  param_name);
        String ptype = get_ptype(rettype);
        code = code + "{\n";
        code = code + "\t" + ptype + " result = " + get_pvalue(rettype) + ";\n";
        code = code + "\treturn result;\n";
        code = code + "}\n";
        System.out.println(code);
    }

    public static String get_funargs(int n, int param2_type[], String param2_name[])
    {
        String code="";
        for ( int i = 0 ; i < n ; i++)
        {
            if ( i > 0)
                code = code + ", "; 
            String param2_type_name = get_ptype(param2_type[i]);
            code = code + param2_type_name + " " + param2_name[i];    
        }

        return code;
    }

    public static String get_funsign(String myfun, int rettype, int n, int param2_type[], String param2_name[])
    {
        String code = "";
        code = code + "public static " + get_ptype(rettype) +  " " + myfun + "(";
        code = code + get_funargs(n,  param2_type,  param2_name) + ")\n";
        return code;
    }
    
	 public static void code_print2(String myfun, int rettype, int n, int param2_type[], String param2_name[])    
    {
        String code = "";
        code = code + "public static void test_" + myfun + "(";
        code = code + get_funargs(n,  param2_type,  param2_name);
        code = code + ", " + get_ptype(rettype) + " expected";
        code = code + ")\n";
        code = code + "{\n";
        // int actual = myfun( num1, num2);
        code = code + "\t" + get_ptype(rettype) + " actual = " + myfun + "(";
        for ( int i = 0 ; i < n ; i++)
        {
            if ( i > 0)
                code = code + ", "; 
                String param2_type_name = get_ptype(param2_type[i]);
            code = code + "" + param2_name[i];    
        }
        code = code + ");\n\tif (actual == expected) \n\t{\n";
        code = code + "\t\tSystem.out.println(\"Pass: \"";
        for ( int i = 0 ; i < n ; i++)
        {
            String pname = param2_name[i];
            code = code + " + " + " \"" + pname + "=>\" + " +  pname ;
        }

        // 	System.out.println("Pass: num1=>"+num1+" num2=>" + num2 + " expected=> " + expected + " actual: " + actual);
        code = code + "+ \"expected=>\" + expected + \" actual=> \" + actual);\n";
        code = code + "\t}\n";
        code = code + "\telse\n\t{\n";
        code = code + "\t\tSystem.out.println(\"Fail: \"";
        for ( int i = 0 ; i < n ; i++)
        {
            String pname = param2_name[i];
            code = code + " + " + " \"" + pname + "=>\" + " +  pname ;
        }
        code = code + "+ \"expected=>\" + expected + \" actual=> \" + actual);\n";
        code = code + "\t}\n";
        code = code + "}\n";

        code = code + "public static void suite_test_" +myfun+ "()\n{\n";
        String fcall_value = "";
        for ( int i = 0 ; i < n ; i++)
        {
            String defval = get_pvalue(param2_type[i]);
            if ( i > 0)
                fcall_value += ", ";
            
            fcall_value += defval;
        }

        code = code + "\ttest_" +myfun+ "(" + fcall_value + ", " + get_pvalue(rettype) + ")" ;
        // test_myfun(true, true, true, true , true);
        code = code + "\n}";

        System.out.println(code);
    }
	
    public static void main(String[] args)
    {
        int param_type[] = {1,2};
        String param_name[] = {"num1", "num2", "num3" , "flag1"};

        code_print("myfunc", 1,2,  param_type,  param_name);
		code_print2("myfunc", 1,2,  param_type,  param_name);
       
    }
}