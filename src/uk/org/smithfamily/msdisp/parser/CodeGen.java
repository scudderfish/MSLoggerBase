package uk.org.smithfamily.msdisp.parser;

import java.util.ArrayList;
import java.util.List;

import uk.org.smithfamily.msdisp.parser.MemoryItem.op;
import uk.org.smithfamily.msdisp.parser.functions.Function;

class MemoryItem
{
    public enum op
    {
        HALTop, DBGSop, LSHFop, RSHFop, PLUSop, MINUSop, MULTop, DIVop, MODop, NEGop, PUSHAop, PUSHIop, PUSHDop, PUSHSop, PUSHYop, POPAop, FUNCop, LTop, LEop, GTop, GEop, EQop, NEop, BANDop, BXORop, BORop, BNOTop, LNOTop, LANDop, LORop, BNEop, BRAop
    };

    public enum Type
    {
        OperatorType, IntegerType, DoubleType, StringType, SymbolType, FunctionType
    };

    private Symbol symbol;
    private Type type;
    private int  intVal;
    private double doubleVal;
    private String stringVal;
    private op   opVal;
    private Function function;

    public MemoryItem(int i)
    {
        this.type = Type.IntegerType;
        this.intVal = i;
    }

    public MemoryItem(op o)
    {
        this.type = Type.OperatorType;
        this.opVal = o;
    }
    public MemoryItem(double theOperand)
    {
        this.type=Type.DoubleType;
        this.doubleVal = theOperand;
    }

    public MemoryItem(String s)
    {
        this.type = Type.StringType;
        stringVal = s;
    }

    public MemoryItem(Symbol s)
    {
        this.type = Type.SymbolType;
        symbol = s;
    }
    public MemoryItem(Function f)
    {
        this.type = Type.FunctionType;
        function = f;
    }

    public op getOpVal()
    {
        return opVal;
    }

    public String getStringVal()
    {
       
        return stringVal;
    }

    public int getIntVal()
    {
       return intVal;
    }

    public double getDoubleVal()
    {
        return doubleVal;
    }

    public Symbol getSymValue()
    {
        return symbol;
    }

    @Override
    public String toString()
    {
        return "MemoryItem [type=" + type + ", intVal=" + intVal + ", doubleVal=" + doubleVal + ", stringVal=" + stringVal
                + ", opVal=" + opVal + ", function=" + function + "]";
    }
}

interface RelOp
{
    public boolean evaluate(double x,double y);
}


public class CodeGen
{
    
    ExprError        e = new ExprError();
    List<MemoryItem> Memory = new ArrayList<MemoryItem>();
    int              sizeMem;
    int              nextMemLoc;
    int              checkPointLoc;

    void alloc(int nWords)
    {
    }

    void copy(CodeGen from)
    {
        checkPointLoc = from.checkPointLoc;
        sizeMem = from.nextMemLoc; // Minimize wasted space.
        nextMemLoc = from.nextMemLoc;
        Memory = new ArrayList<MemoryItem>();
        Memory.addAll(from.Memory);
    }

    public CodeGen()
    {
        Gen1Op(op.HALTop);
        checkPointLoc = nextMemLoc;
    }

    CodeGen(CodeGen from)
    {
        copy(from);
    }

    // --------------------------------------------------------------------------
    // -- These two members allow you to append a new block of code, and if --
    // -- errors are found during code generation, to back off any new code --
    // -- that made its way into memory. --
    void checkPoint()
    {
        checkPointLoc = nextMemLoc;
    }

    void recover()
    {
        if (checkPointLoc > 0)
            nextMemLoc = checkPointLoc - 1;
        Gen1Op(op.HALTop);
    }

    void append()
    {
        if (nextMemLoc > 0)
        {
            if (Memory.get(nextMemLoc - 1).getOpVal() == op.HALTop)
                nextMemLoc--;
        }
    }

    public void Gen1Op(op theOperator)
    {
        alloc(1);
        Memory.add(nextMemLoc, new MemoryItem(theOperator));
        nextMemLoc++;
    }

    void Gen2Op(op theOperator, int theOperand)
    {
        alloc(2);
        Memory.add(nextMemLoc,new MemoryItem(theOperator));
        nextMemLoc++;
        Memory.add(nextMemLoc,new MemoryItem(theOperand));
        nextMemLoc++;
    }
    void Gen2Op(op theOperator, Symbol theOperand)
    {
        alloc(2);
        Memory.add(nextMemLoc,new MemoryItem(theOperator));
        nextMemLoc++;
        Memory.add(nextMemLoc,new MemoryItem(theOperand));
        nextMemLoc++;
    }

    void Gen2Op(op theOperator, double theOperand)
    {
        alloc(2);
        Memory.add(nextMemLoc,new MemoryItem(theOperator));
        nextMemLoc++;
        Memory.add(nextMemLoc,new MemoryItem(theOperand));
        nextMemLoc++;

    }
    void Gen2Op(op theOperator, Function theOperand)
    {
        alloc(2);
        Memory.add(nextMemLoc,new MemoryItem(theOperator));
        nextMemLoc++;
        Memory.add(nextMemLoc,new MemoryItem(theOperand));
        nextMemLoc++;

    }

    // void Gen2Op(op theOperator, void *theOperand);

    void  GenStr(op theOperator, String s) {
       Gen1Op(theOperator);
       alloc(1);
       Memory.add(nextMemLoc++,new MemoryItem(s));
    }

    int GenCond()
    {
        Gen1Op(op.BNEop);
        alloc(1);
        return nextMemLoc++;
    }

    int GenBranch()
    {
        Gen1Op(op.BRAop);
        alloc(1);
        return nextMemLoc++;
    }

    void Patch(int addr)
    {
        Memory.add(addr,new MemoryItem(nextMemLoc));
    }

    static int zeroDivs = 1; // Should be zero, fix it. ???
    public void Evaluate(List<Double> Variables) throws ExprError
    {
        RelOp  relOp;
        ParserStack  stack = new ParserStack(e);
        int    IP    = 0;
        boolean   Done  = false;
        double d1, d2, d3;
        int    i1, i2;
        String s1;

        e.setTitle("MegaTune Expression Evaluator");

        //DiagPrint(" IP  SP\n");

        do {
          // DiagPrint("%3d %3d  ", IP, Stack.SP);
           switch (Memory.get(IP).getOpVal()) {
              case DBGSop:
                 IP++;
            //     DiagPrint("DBGS  {%s}\n", reinterpret_cast<char *>(Memory[IP]));
              e.reset(Memory.get(IP).getStringVal());
                 IP++;
                 break;

              case POPAop:
              {
                 //DiagPrint("POP   @%03d", Memory[IP+1]);
                 IP++;
                 double v;
                 v=stack.popD();
                 Variables.add(Memory.get(IP).getIntVal(), v);
                 //DiagPrint("  (r=%f)\n", v);
                 //if (_isnan(Variables[Memory[IP]])) e.Error("Operation produced not-a-number");
                 IP++;
              }
              break;

              case LNOTop:
                 DiagPrint("LNOT\n");
                 IP++;
                 i1=stack.popI ();
                 stack.push(1-i1);
                 break;

              case BNOTop:
                 DiagPrint("BNOT\n");
                 IP++;
                 i1=stack.popI ();
                 stack.push(~i1);
                 break;

              case NEGop:
                 DiagPrint("NEG\n");
                 IP++;
                 d1=stack.popD();
                 stack.push(-d1);
                 break;

              case LSHFop:
                 DiagPrint("LSHF\n");
                 IP++;
                 i2=stack.popI();
                 i1=stack.popI();
                 stack.push(i1 << i2);
                 break;

              case RSHFop:
                 DiagPrint("RSHF\n");
                 IP++;
                 i2=stack.popI();
                 i1=stack.popI();
                 stack.push(i1 >> i2);
                 break;

              case PLUSop:
                 DiagPrint("PLUS\n");
                 IP++;
                 d2=stack.popD();
                 d1=stack.popD();
                 stack.push(d1+d2);
                 break;

              case MINUSop:
                 DiagPrint("MINUS\n");
                 IP++;
                 d2=stack.popD();
                 d1=stack.popD();
                 stack.push(d1-d2);
                 break;

              case MULTop:
                 DiagPrint("MULT\n");
                 IP++;
                 d2=stack.popD();
                 d1=stack.popD();
                 stack.push(d1*d2);
                 break;

              case DIVop:
                 DiagPrint("DIV\n");
                 IP++;
                 d2=stack.popD();
                 d1=stack.popD();
                 if (Math.abs(d2) > 1e-10)
                   d3 = d1/d2;
                 else {
                    zeroDivs++;
                    if (zeroDivs == 1) e.Error("Divide by zero.");
                    d3 = 1e6;
                 }
                 stack.push(d3);
                 break;

              case MODop:
                 DiagPrint("MOD\n");
                 IP++;
                 i2=stack.popI();
                 i1=stack.popI();
                 stack.push(i1%i2);
                 break;

              case LTop:
                  IP++;
                  d2=stack.popD();
                  d1=stack.popD();
                  stack.push((d1 < d2) ? 1 : 0);
                  break;
                  
              case LEop:
                  IP++;
                  d2=stack.popD();
                  d1=stack.popD();
                  stack.push((d1 <= d2) ? 1 : 0);
                  break;
              
              case GTop: 
                  IP++;
                  d2=stack.popD();
                  d1=stack.popD();
                  stack.push((d1 > d2) ? 1 : 0);
                  break;
              
              case GEop: 
                  IP++;
                  d2=stack.popD();
                  d1=stack.popD();
                  stack.push((d1 >= d2) ? 1 : 0);
                  break;
              
              case EQop:
                  IP++;
                  d2=stack.popD();
                  d1=stack.popD();
                  stack.push((d1 == d2) ? 1 : 0);
                  break;
              
              case NEop: 
                  IP++;
                  d2=stack.popD();
                  d1=stack.popD();
                  stack.push((d1 != d2) ? 1 : 0);
                  break;
              
              case BANDop:
                 DiagPrint("BAND\n");
                 IP++;
                 i2=stack.popI();
                 i1=stack.popI();
                 stack.push(i1 & i2);
                 break;

              case BXORop:
                 DiagPrint("BXOR\n");
                 IP++;
                 i2=stack.popI();
                 i1=stack.popI();
                 stack.push(i1 ^ i2);
                 break;

              case BORop:
                 DiagPrint("BOR\n");
                 IP++;
                 i2=stack.popI();
                 i1=stack.popI();
                 stack.push(i1 | i2);
                 break;

              case LANDop:
                 DiagPrint("LAND\n");
                 IP++;
                 i2=stack.popI();
                 i1=stack.popI();
                 stack.push((i1!=0) && (i2!=0) ? 1 : 0);
                 break;

              case LORop:
                 DiagPrint("LOR\n");
                 IP++;
                 i2=stack.popI();
                 i1=stack.popI();
                 stack.push((i1!=0) || (i2!=0) ? 1 : 0);
                 break;

              case HALTop:
                 DiagPrint("HALT\n");
                 Done = true;
                 break;

              case PUSHAop:
                 //DiagPrint("PUSH  @%03d", Memory[IP+1]);
                 IP++;
                 stack.push(Variables.get(Memory.get(IP).getIntVal()));
                 //DiagPrint("  (r=%f)\n", Stack.topD());
                 IP++;
                 break;

              case PUSHIop:
                 //DiagPrint("PUSHI %1d\n", Memory[IP+1]);
                 IP++;
                 stack.push(Memory.get(IP).getIntVal());
                 IP++;
                 break;

              case PUSHDop:
                 IP++;
                 stack.push(Memory.get(IP).getDoubleVal());
                 //DiagPrint("PUSHD %f\n", Stack.topD());
                 break;

              case PUSHSop:
                 IP++;
                 stack.push(Memory.get(IP).getStringVal());
                 //DiagPrint("PUSHS >%s<\n", Stack.topS());
                 break;

              case PUSHYop: // Symbol reference
                 IP++;
                 stack.push(Memory.get(IP).getSymValue().valueUser(0));
                 //DiagPrint("PUSHY %f (%s)\n", Stack.topD(), reinterpret_cast<symbol *>(Memory[IP])->name());
                 IP++;
                 break;

              case BNEop:
                 DiagPrint("BNE\n");
                 IP += 2;
                 if (stack.topIs(StackEntry.entType.strV)) 
                 {
                    s1=stack.popS();
                    if (s1==null || s1.trim().equals("")) IP =  Memory.get(IP-1).getIntVal();
                 }
                 else {
                 d1=stack.popD();
                    if (d1 == 0.0) IP =Memory.get(IP-1).getIntVal();
                 }
                 break;

              case BRAop:
                 DiagPrint("BRA\n");
                 IP = Memory.get(IP+1).getIntVal();;
                 break;

              case FUNCop:
              {
                 //DiagPrint("FUNC  %3d  (%s)\n", Memory[IP+1], func->fromId(Memory[IP+1]).name);
                 IP++;
//                    func.fromId(Memory.get(IP).getIntVal()).eval(stack);
                 IP++;
              }
              break;

              default:
                 e.reset("Eek!"); // ???
                 e.Error("Internal error: invalid op code");
                 break;
           }
        } while (!Done);

        if (stack.getSize() != 0) {
           e.reset('?');
           e.Error("Internal error: stack screwed up");
        }

    }

    private void DiagPrint(String string)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String toString()
    {
        return "CodeGen [e=" + e + ", Memory=" + Memory + ", sizeMem=" + sizeMem + ", nextMemLoc=" + nextMemLoc
                + ", checkPointLoc=" + checkPointLoc + "]";
    }
}
