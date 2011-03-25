package uk.org.smithfamily.msdisp.parser;

import uk.org.smithfamily.msdisp.parser.functions.Function;
import uk.org.smithfamily.msdisp.parser.functions.Functions;

public class Lexer
{
    private static final String INVALID_INPUT_TOKEN = "Invalid input token";
    static final String         DecimalNumeric      = "0123456789";
    static final String         BinaryNumeric       = "01";
    static final String         HexNumeric          = "0123456789abcdefABCDEF";
    static final String         AlphaNumeric        = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_";
    static final String         Alpha               = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_";

    static final char           AndChar             = '&';
    static final char           OrChar              = '|';
    static final char           PlusChar            = '+';
    static final char           QuoteChar           = '"';
    static final char           MinusChar           = '-';
    static final char           MultChar            = '*';
    static final char           ModChar             = '%';
    static final char           DivChar             = '/';
    static final char           LParenChar          = '(';
    static final char           RParenChar          = ')';
    static final char           CommaChar           = ',';
    static final char           EqualChar           = '=';
    static final char           LtChar              = '<';
    static final char           GtChar              = '>';
    static final char           NotChar             = '!';
    static final char           QChar               = '?';
    static final char           ColonChar           = ':';
    static final char           CaretChar           = '^';
    static final char           TildeChar           = '~';
    static final char           TermChar            = '\0';

    enum Token
    {
        StrTok, IntTok, DblTok, VariableTok, ConstantTok, FunctionTok, BAndTok, BOrTok, BXorTok, BNotTok, LAndTok, LOrTok, LNotTok, CondTok, ColonTok, PlusTok, MinusTok, MultTok, ModTok, DivTok, LParenTok, RParenTok, CommaTok, EqualTok, LShiftTok, RShiftTok, EqTok, NeTok, LtTok, LeTok, GtTok, GeTok, TermTok, ErrorTok
    };

    private ExprError e;
    private String    inputLine      = "";
    // private int nextChar;

    private int       currentCharPos = 0;
    public Token      CurrentTok;
    Function          CurrentFnc;
    public int        fnArgCount;
    public int        CurrentVar;
    public Symbol     CurrentSym;
    public String     CurrentStr     = "";
    public int        CurrentInt;
    public double     CurrentDbl;

    String GetSequence(char firstChar, String validChars) throws ExprError
    {
        String result = "";
        char inputChar = firstChar;
        int inputLoc = 0;
        int itsThere = validChars.indexOf(inputChar);

        while(currentCharPos < inputLine.length() && itsThere >= 0)
        {
            result += inputChar;
            inputLoc++;
            currentCharPos++;
            if (currentCharPos < inputLine.length())
            {
                inputChar = inputLine.charAt(currentCharPos);
                itsThere = validChars.indexOf(inputChar);
            }
            else
            {
                break;
            }
        }
        
        return result;
    }

    String GetUntil(char untilChar) throws ExprError
    {
        String destStr = "";
        testPosition();
        while (currentCharPos < inputLine.length() && inputLine.charAt(currentCharPos) != untilChar)
        {
            destStr += inputLine.charAt(currentCharPos++);
        }
        currentCharPos++;

        return destStr;
    }

    void GetNumber(char firstChar) throws ExprError
    {
        char inputChar;
        String numStr;
        String newStr;

        int base = 10;
        String Numeric = Lexer.DecimalNumeric;

        if (firstChar == '0' && inputLine.length() > currentCharPos + 1)
        { // Allow for binary numbers with a variant on C's hex: 0b10101
            switch (inputLine.charAt(currentCharPos + 1))
            {
            case 'b':
                base = 2;
                currentCharPos++;
                Numeric = BinaryNumeric;
                break;
            case 'x':
                base = 16;
                currentCharPos++;
                Numeric = HexNumeric;
                break;
            }
        }

        numStr = GetSequence(firstChar, Numeric);

        if (currentCharPos < inputLine.length())// There's more to process
        {
            inputChar = inputLine.charAt(currentCharPos);
            if (".eE".indexOf(inputChar) < 0)
            {
                // DiagPrint("   Integer value: >%s<\n", numStr);
                CurrentTok = Token.IntTok;
                CurrentInt = Integer.valueOf(numStr, base);
                return;
                // if (*end)
                // e.Error("Numeric conversion error, value may have overflowed");
            }
            else
            {
                if (base != 10)
                    e.Error("Floating point values may only be base 10");

                if (inputChar == '.')
                {
                    numStr += ".";
                    moveForward();
                    inputChar = inputLine.charAt(currentCharPos++);

                    newStr = GetSequence(inputChar, DecimalNumeric);
                    if (newStr != null)
                        numStr += newStr;
                    else
                    {
                        e.backup();
                        currentCharPos--;
                    }
                }
                if (numStr.length() == 1 && numStr.charAt(0) == '.')
                    e.Error("Expected numeric digits after decimal");

                
                if(currentCharPos < inputLine.length())
                {
                    testPosition();
                    inputChar = inputLine.charAt(currentCharPos++);
                    
                    if ("Ee".indexOf(inputChar) >= 0)
                    {
                        numStr += 'e';
                        testPosition();
                        inputChar = inputLine.charAt(currentCharPos++);
    
                        if (inputChar == '+' || inputChar == '-')
                        {
                            if (inputChar == '-')
                                numStr += "-";
                            testPosition();
                            inputChar = inputLine.charAt(currentCharPos++);
                        }
    
                        newStr = GetSequence(inputChar, DecimalNumeric);
                        numStr += newStr;
                        if (newStr.length() == 0)
                            e.Error("Expected number for exponent");
                        testPosition();
                        inputChar = inputLine.charAt(currentCharPos++);
    
                    }
                }
            }
         
            e.backup();
            currentCharPos--;

            // DiagPrint("   Real value: >%s<\n", numStr);
            CurrentTok = Token.DblTok;
            CurrentDbl = Double.valueOf(numStr);
            // if (*end)
            // e.Error("Numeric conversion error, value may have overflowed");
        }
        else
        {
            CurrentTok = Token.IntTok;
            CurrentInt = Integer.valueOf(numStr, base);
        }
    }

    private void testPosition() throws ExprError
    {
        if (currentCharPos >= inputLine.length())
        {
            e.Error(INVALID_INPUT_TOKEN);
        }
    }

    public Lexer(ExprError ec)
    {
        e = ec;
    }

    void Init(String Line)
    {
        inputLine += Line;
        currentCharPos = 0;
    }

    void GetToken() throws ExprError
    {
        if (currentCharPos >= inputLine.length())
        {
            CurrentTok = Token.TermTok;
            return;
        }
        while (currentCharPos < inputLine.length() && Character.isWhitespace(inputLine.charAt(currentCharPos)))
            currentCharPos++;

        char InputChar = inputLine.charAt(currentCharPos);
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
            }
            else
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
        }
        else if (DecimalNumeric.indexOf(InputChar) >= 0)
        {
            GetNumber(InputChar);
        }
        else
        {
            switch (InputChar)
            {
            case QuoteChar:
                CurrentStr = GetUntil(QuoteChar);
                CurrentTok = Token.StrTok;
                moveForward();
                break;
            case PlusChar:
                CurrentTok = Token.PlusTok;
                moveForward();
                break;
            case MinusChar:
                CurrentTok = Token.MinusTok;
                moveForward();
                break;
            case MultChar:
                CurrentTok = Token.MultTok;
                moveForward();
                break;
            case ModChar:
                CurrentTok = Token.ModTok;
                moveForward();
                break;
            case DivChar:
                CurrentTok = Token.DivTok;
                moveForward();
                break;
            case LParenChar:
                CurrentTok = Token.LParenTok;
                moveForward();
                break;
            case RParenChar:
                CurrentTok = Token.RParenTok;
                moveForwardNoTest();
                break;
            case CommaChar:
                CurrentTok = Token.CommaTok;
                moveForward();
                break;
            case TermChar:
                CurrentTok = Token.TermTok;
                moveForward();
                break;
            case CaretChar:
                CurrentTok = Token.BXorTok;
                moveForward();
                break;
            case TildeChar:
                CurrentTok = Token.BNotTok;
                moveForward();
                break;
            case QChar:
                CurrentTok = Token.CondTok;
                moveForward();
                break;
            case ColonChar:
                CurrentTok = Token.ColonTok;
                moveForward();
                break;
            case AndChar:
                moveForward();
                switch (inputLine.charAt(currentCharPos))
                {
                case AndChar:
                    CurrentTok = Token.LAndTok;
                    currentCharPos++;
                    break;
                default:
                    CurrentTok = Token.BAndTok;
                    break;
                }
                break;
            case OrChar:
                moveForward();
                switch (inputLine.charAt(currentCharPos))
                {
                case OrChar:
                    CurrentTok = Token.LOrTok;
                    currentCharPos++;
                    break;
                default:
                    CurrentTok = Token.BOrTok;
                    break;
                }
                break;
            case NotChar:
                moveForward();
                switch (inputLine.charAt(currentCharPos))
                {
                case EqualChar:
                    CurrentTok = Token.NeTok;
                    currentCharPos++;
                    break;
                default:
                    CurrentTok = Token.LNotTok;
                    break;
                }
                break;
            case EqualChar:
                moveForward();
                switch (inputLine.charAt(currentCharPos))
                {
                case EqualChar:
                    CurrentTok = Token.EqTok;
                    currentCharPos++;
                    break;
                default:
                    CurrentTok = Token.EqualTok;
                    break;
                }
                break;
            case LtChar:
                moveForward();
                switch (inputLine.charAt(currentCharPos))
                {
                case LtChar:
                    currentCharPos++;
                    break;
                case EqualChar:
                    CurrentTok = Token.LeTok;
                    currentCharPos++;
                    break;
                default:
                    CurrentTok = Token.LtTok;
                    break;
                }
                break;
            case GtChar:
                moveForward();
                switch (inputLine.charAt(currentCharPos))
                {
                case GtChar:
                    CurrentTok = Token.RShiftTok;
                    currentCharPos++;
                    break;
                case EqualChar:
                    CurrentTok = Token.GeTok;
                    currentCharPos++;
                    break;
                default:
                    CurrentTok = Token.GtTok;
                    break;
                }
                break;
            default:
                e.Error(INVALID_INPUT_TOKEN);
                break;
            }
        }
    }

    private void moveForwardNoTest()
    {
        
        currentCharPos++;
        
    }

    private void moveForward() throws ExprError
    {
        currentCharPos++;
        testPosition();
    }

}
