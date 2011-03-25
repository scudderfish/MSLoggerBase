package uk.org.smithfamily.msdisp.parser;

import uk.org.smithfamily.msdisp.parser.functions.Function;
import uk.org.smithfamily.msdisp.parser.functions.Functions;

public class Lexer
{
    static final String DecimalNumeric = "0123456789";
    static final String BinaryNumeric  = "01";
    static final String HexNumeric     = "0123456789abcdefABCDEF";
    static final String AlphaNumeric   = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_";
    static final String Alpha          = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_";

    static final char   AndChar        = '&';
    static final char   OrChar         = '|';
    static final char   PlusChar       = '+';
    static final char   QuoteChar      = '"';
    static final char   MinusChar      = '-';
    static final char   MultChar       = '*';
    static final char   ModChar        = '%';
    static final char   DivChar        = '/';
    static final char   LParenChar     = '(';
    static final char   RParenChar     = ')';
    static final char   CommaChar      = ',';
    static final char   EqualChar      = '=';
    static final char   LtChar         = '<';
    static final char   GtChar         = '>';
    static final char   NotChar        = '!';
    static final char   QChar          = '?';
    static final char   ColonChar      = ':';
    static final char   CaretChar      = '^';
    static final char   TildeChar      = '~';
    static final char   TermChar       = '\0';

    enum Token
    {
        StrTok, IntTok, DblTok, VariableTok, ConstantTok, FunctionTok, BAndTok, BOrTok, BXorTok, BNotTok, LAndTok, LOrTok, LNotTok, CondTok, ColonTok, PlusTok, MinusTok, MultTok, ModTok, DivTok, LParenTok, RParenTok, CommaTok, EqualTok, LShiftTok, RShiftTok, EqTok, NeTok, LtTok, LeTok, GtTok, GeTok, TermTok, ErrorTok
    };

    private ExprError e;
    private String    inputLine  = "";
    //private int       nextChar;

    private int nextCharPos = 1;
    public Token      CurrentTok;
    Function          CurrentFnc;
    public int        fnArgCount;
    public int        CurrentVar;
    public Symbol     CurrentSym;
    public String     CurrentStr = "";
    public int        CurrentInt;
    public double     CurrentDbl;

    char currentChar()
    {
        return inputLine.charAt(nextCharPos-1);
    }
    char nextChar() throws ExprError
    {
        if (nextCharPos > inputLine.length()-1)
        {
            e.Error("Attempt to move beyond length of input: input='"+inputLine+"'");
        }
        return inputLine.charAt(nextCharPos++);
    }
    String GetSequence(char firstChar, String validChars)
    {
        String result = "";
        char inputChar = firstChar;
        int inputLoc = 0;
        int itsThere = validChars.indexOf(inputChar);
        
        do
        {
            result += inputChar;
            inputLoc++;
            if(nextChar < inputLine.length())
            {
                inputChar = inputLine.charAt(nextChar++);
                itsThere = validChars.indexOf(inputChar);   
            }
        }
        while (nextChar < inputLine.length() && itsThere >= 0);
        return result;
    }

    String GetUntil(char untilChar)
    {
        String destStr = "";

        while (inputLine.charAt(nextChar) != untilChar)
        {
            destStr += inputLine.charAt(nextChar++);
        }
        inputLine.charAt(nextChar++); // Past the closing one

        return destStr;
    }

    void GetNumber(char firstChar) throws ExprError
    {
        char inputChar;
        String numStr;
        String newStr;

        int base = 10;
        String Numeric = Lexer.DecimalNumeric;

        if (firstChar == '0' && inputLine.length() > nextChar)
        { // Allow for binary numbers with a variant on C's hex: 0b10101
            switch (inputLine.charAt(nextChar))
            {
            case 'b':
                base = 2;
                inputLine.charAt(nextChar++);
                Numeric = BinaryNumeric;
                break;
            case 'x':
                base = 16;
                inputLine.charAt(nextChar++);
                Numeric = HexNumeric;
                break;
            }
        }

        numStr = GetSequence(firstChar, Numeric);

        inputChar = inputLine.charAt(nextChar);
        if (".eE".indexOf(inputChar) < 0)
        {
            // DiagPrint("   Integer value: >%s<\n", numStr);
            CurrentTok = Token.IntTok;
            CurrentInt = Integer.valueOf(numStr, base);
            // if (*end)
            // e.Error("Numeric conversion error, value may have overflowed");
        } else
        {
            if (base != 10)
                e.Error("Floating point values may only be base 10");

            if (inputChar == '.')
            {
                numStr += ".";
                inputLine.charAt(nextChar++);
                ;
                inputChar = inputLine.charAt(nextChar++);
                ;

                newStr = GetSequence(inputChar, DecimalNumeric);
                if (newStr != null)
                    numStr += newStr;
                else
                {
                    e.backup();
                    nextChar--;
                }
            }
            if (numStr.length() == 1 && numStr.charAt(0) == '.')
                e.Error("Expected numeric digits after decimal");

            inputChar = inputLine.charAt(nextChar++);
            ;
            if ("Ee".indexOf(inputChar) >= 0)
            {
                numStr += 'e';
                inputChar = inputLine.charAt(nextChar++);
                ;

                if (inputChar == '+' || inputChar == '-')
                {
                    if (inputChar == '-')
                        numStr += "-";
                    inputChar = inputLine.charAt(nextChar++);
                    ;
                }

                newStr = GetSequence(inputChar, DecimalNumeric);
                numStr += newStr;
                if (newStr.length() == 0)
                    e.Error("Expected number for exponent");
                inputChar = inputLine.charAt(nextChar++);
                ;
            }

            e.backup();
            nextChar--;

            // DiagPrint("   Real value: >%s<\n", numStr);
            CurrentTok = Token.DblTok;
            CurrentDbl = Double.valueOf(numStr);
            // if (*end)
            // e.Error("Numeric conversion error, value may have overflowed");
        }
    }

    public Lexer(ExprError ec)
    {
        e = ec;
    }

    void Init(String Line)
    {
        inputLine += Line;
        nextChar = 0;
    }

    void GetToken() throws ExprError
    {
        while (Character.isWhitespace((inputLine.charAt(nextChar))))
            nextChar++;

        char InputChar = inputLine.charAt(nextChar++);
        ;
        e.reset(InputChar);

        if (Alpha.indexOf(InputChar) >= 0)
        {
            CurrentStr = GetSequence(InputChar, AlphaNumeric);
            // Search the constants table. These first two have unique entries.
            // Search the function table.
            CurrentFnc = Functions.id(CurrentStr);
            if (CurrentFnc != null)
            {
                CurrentTok = Token.FunctionTok;
                fnArgCount = CurrentFnc.argCnt();
            } else
            {
                // Search the variables table in controller symbol table; if not
                // there, error.
                final MsDatabase mdb = MsDatabase.getInstance();
                CurrentVar = mdb.cDesc.varIndex(CurrentStr);
                if (CurrentVar != -1)
                    CurrentTok = Token.VariableTok;
                else
                {
                    CurrentSym = mdb.cDesc.lookup(CurrentStr);
                    if (CurrentSym == null)
                        e.Error("Unknown identifier");
                    CurrentTok = Token.ConstantTok;
                }
            }
        } else if (DecimalNumeric.indexOf(InputChar) >= 0)
        {
            GetNumber(InputChar);
        } else
        {
            switch (InputChar)
            {
            case QuoteChar:
                CurrentStr = GetUntil(QuoteChar);
                CurrentTok = Token.StrTok;
                break;
            case PlusChar:
                CurrentTok = Token.PlusTok;
                break;
            case MinusChar:
                CurrentTok = Token.MinusTok;
                break;
            case MultChar:
                CurrentTok = Token.MultTok;
                break;
            case ModChar:
                CurrentTok = Token.ModTok;
                break;
            case DivChar:
                CurrentTok = Token.DivTok;
                break;
            case LParenChar:
                CurrentTok = Token.LParenTok;
                break;
            case RParenChar:
                CurrentTok = Token.RParenTok;
                break;
            case CommaChar:
                CurrentTok = Token.CommaTok;
                break;
            case TermChar:
                CurrentTok = Token.TermTok;
                break;
            case CaretChar:
                CurrentTok = Token.BXorTok;
                break;
            case TildeChar:
                CurrentTok = Token.BNotTok;
                break;
            case QChar:
                CurrentTok = Token.CondTok;
                break;
            case ColonChar:
                CurrentTok = Token.ColonTok;
                break;
            case AndChar:
                switch (inputLine.charAt(nextChar))
                {
                case AndChar:
                    CurrentTok = Token.LAndTok;
                    inputLine.charAt(nextChar++);
                    ;
                    break;
                default:
                    CurrentTok = Token.BAndTok;
                    break;
                }
                break;
            case OrChar:
                switch (inputLine.charAt(nextChar))
                {
                case OrChar:
                    CurrentTok = Token.LOrTok;
                    inputLine.charAt(nextChar++);
                    ;
                    break;
                default:
                    CurrentTok = Token.BOrTok;
                    break;
                }
                break;
            case NotChar:
                switch (inputLine.charAt(nextChar))
                {
                case EqualChar:
                    CurrentTok = Token.NeTok;
                    inputLine.charAt(nextChar++);
                    ;
                    break;
                default:
                    CurrentTok = Token.LNotTok;
                    break;
                }
                break;
            case EqualChar:
                switch (inputLine.charAt(nextChar))
                {
                case EqualChar:
                    CurrentTok = Token.EqTok;
                    inputLine.charAt(nextChar++);
                    ;
                    break;
                default:
                    CurrentTok = Token.EqualTok;
                    break;
                }
                break;
            case LtChar:
                switch (inputLine.charAt(nextChar))
                {
                case LtChar:
                    CurrentTok = Token.LShiftTok;
                    inputLine.charAt(nextChar++);
                    ;
                    break;
                case EqualChar:
                    CurrentTok = Token.LeTok;
                    inputLine.charAt(nextChar++);
                    ;
                    break;
                default:
                    CurrentTok = Token.LtTok;
                    break;
                }
                break;
            case GtChar:
                switch (inputLine.charAt(nextChar))
                {
                case GtChar:
                    CurrentTok = Token.RShiftTok;
                    inputLine.charAt(nextChar++);
                    ;
                    break;
                case EqualChar:
                    CurrentTok = Token.GeTok;
                    inputLine.charAt(nextChar++);
                    ;
                    break;
                default:
                    CurrentTok = Token.GtTok;
                    break;
                }
                break;
            default:
                e.Error("Invalid input token");
                break;
            }
        }
    }

}
