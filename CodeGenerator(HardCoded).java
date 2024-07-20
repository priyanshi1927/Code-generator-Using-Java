import java.util.*;
class CodeGenerator
{
    String code = "";
    String func_name = "";
    String ret_type = "";
    String params[] = {"int" , "p1", "double" , "p2"};
    String ret_value = "";

    public CodeGenerator(String func, String rtype, String params[])
    {
        this.func_name = func;
        this.ret_type = rtype;
        this.params = params;
        this.ret_value = get_pvalue(rtype);
    }

    public CodeGenerator()
    {
       
        this.func_name = "arihantsProc";
        this.ret_type = "int";
        this.ret_value= get_pvalue("int");
    }

    // public static String get_ptype(int code)
    // {
    // String ptype = "";
    // switch (code)
    // {
    // case 1:
    // ptype = "int";
    // break;
    // case 2:
    // ptype = "double";
    // break;    
    // case 3:
    // ptype = "boolean";
    // break;                    
    // }
    // return ptype;
    // }
    public static String get_pvalue(String rtype)
    {
        String pvalue= "";
        if(rtype == "int")
            pvalue = "0";
        else if(rtype == "double" || rtype == "float")
            pvalue ="0.0";
        else if(rtype == "boolean")
            pvalue = "true";
        return pvalue;
    }

    // public static String get_pvalue(int code)
    // {
    // String pvalue = "";
    // switch (code)
    // {
    // case 1:
    // pvalue = "0";
    // break;
    // case 2:
    // pvalue = "0.0";
    // break;
    // case 3:
    // pvalue = "True";    
    // }
    // return pvalue;
    // }

   // public StringBuilder append(String str) {...}

    private void setFunctionHeader()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(this.code)
            .append("\npublic static ")
            .append(this.ret_type)
            .append(" ")
            .append(this.func_name)
            .append("(");

        //this.code += "\npublic static " + this.ret_type + " " + this.func_name + "(";
        this.code = sb.toString();
        for( int i = 0 ; i < this.params.length ; i+=2)
        {
            if ( i > 0)
                this.code+=(", ");
            this.code+= this.params[i] + (" ") + this.params[i+1];
        }
        this.code += ")\n";

    }
    private void setFunctionBody()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(this.code);
            sb.append("{\n\t");
            sb.append(this.ret_type);
            sb.append(" result = ");
            sb.append(this.ret_value);
            sb.append(" ; \n");
            sb.append("\treturn result;\n}");
       this.code = sb.toString();
    }
    private void setTestFunctionHeader()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(this.code);
            sb.append("\npublic static void test_");
            sb.append(this.func_name);
            sb.append("(");
        for( int i = 0 ; i < this.params.length ; i+=2)
        {
            if ( i > 0)
                sb.append(", ");
            sb.append(this.params[i]);
            sb.append(" ");
            sb.append(this.params[i+1]);
            // this.code+=(", ");
            // this.code+= this.params[i] + (" ") + this.params[i+1];
        }
            sb.append(", ");
            sb.append(this.ret_type);
            sb.append(" expected");
            sb.append(")\n");
            this.code = sb.toString();    
    }
    private void setTestFunctionBody()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(this.code);
        sb.append("{ \n\t");
        sb.append(ret_type);
        sb.append(" actual = ");
        sb.append(this.func_name);
        sb.append("( ");
        for( int i = 0 ; i < this.params.length ; i+=2)
        {
            if ( i > 0)
                sb.append(", ");
            sb.append(this.params[i+1]);
        }
        sb.append(" );\n\tif( actual == expected)\n System.out.println(\"pass: ");
        sb.append(getP_Message());
        sb.append("\n\t else \n");
        sb.append(" System.out.println(\"fail: ");
        sb.append(getP_Message());
        sb.append("}");
        this.code = sb.toString();
    }
    private void setSuiteFunctionHeader()
    {
     StringBuilder sb = new StringBuilder();
        sb.append(this.code);
         sb.append("\npublic static void suite_test_")
            .append(this.func_name)
            .append("( )");
        // this.code += "\npublic static " + this.ret_type + " " + this.func_name + "(";
        this.code = sb.toString();
       
    }
    private void setSuiteFunctionBody()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(this.code);
        sb.append("\n{\n\t");
        sb .append(this.func_name);
        sb.append("(");
        for(int i=0; i<this.params.length-1; i=i+2)
        {
            sb.append(get_pvalue(this.params[i]));
            if(i<this.params.length-2)
            sb.append(",");
        }
        sb.append(");");
        sb.append("\n}\n");
         this.code = sb.toString();
    }
    public String getP_Message()
    {
        StringBuilder sb = new StringBuilder();
        for( int i = 0 ; i < this.params.length ; i+=2)
        {
            sb.append(this.params[i+1]);
            sb.append(" =>\" + ");
            sb.append(this.params[i+1]);
            sb.append(" + \"");
        }
        sb.append(" expected=> \" + expected + \" actual: \" + actual);\n");
        this.code = sb.toString();
        return this.code;
    }
   
    public String getCode()
    {
        setFunctionHeader();
        setFunctionBody();
        setTestFunctionHeader();
        setTestFunctionBody();
        setSuiteFunctionHeader();
        setSuiteFunctionBody();

        return this.code;
    }
}
 class Main {
    public static void main(String[] args)
    {
        String []parameters = {"double","a","int","num","boolean","res"};
        CodeGenerator cgen = new CodeGenerator("arihant","boolean",parameters);
        //CodeGenerator cgen = new CodeGenerator();
        System.out.println(cgen.getCode());
    }
}