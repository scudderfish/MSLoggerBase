package uk.org.smithfamily.msdisp.parser;

public class Repository
{
	  // Controller versioning.
	   double lofEGO, hifEGO, rdfEGO;

	   double loaTPD, hiaTPD;
	   double loaMAD, hiaMAD;

	   double lorEGO, hirEGO;
	   double lorCT,  hirCT; 
	   double lorBAT, hirBAT; 
	   double lorTR,  hirTR; 
	   double lorGE,  hirGE; 
	   double lorMAP, hirMAP; 
	   double lorMAT, hirMAT; 
	   double lorRPM, hirRPM; 
	   double lorPW,  hirPW; 
	   double lorDC,  hirDC; 
	   double lorEGC, hirEGC; 
	   double lorBC,  hirBC; 
	   double lorWC,  hirWC; 
	   double lorADC, hirADC; 
	   double lorVE,  hirVE; 
	   double lorACC, hirACC;

	   double lotEGO, hitEGO, rdtEGO; // The LED bar.

	   double lotVEB, hitVEB;

	   int      vatSD;  // spotDepth
	   int      vatCD;  // cursorDepth
	   int      gaugeColumns;

	void aeTpsDot(Tokenizer t) { loaTPD = t.v(1); hiaTPD = t.v(2); }
	   void aeMapDot(Tokenizer t) { loaMAD = t.v(1); hiaMAD = t.v(2); }

	   void runEGO(Tokenizer t) { lorEGO = t.v(1); hirEGO = t.v(2); }
	   void runCT (Tokenizer t) { lorCT  = t.v(1); hirCT  = t.v(2); }
	   void runBAT(Tokenizer t) { lorBAT = t.v(1); hirBAT = t.v(2); }
	   void runTR (Tokenizer t) { lorTR  = t.v(1); hirTR  = t.v(2); }
	   void runGE (Tokenizer t) { lorGE  = t.v(1); hirGE  = t.v(2); }
	   void runMAP(Tokenizer t) { lorMAP = t.v(1); hirMAP = t.v(2); }
	   void runMAT(Tokenizer t) { lorMAT = t.v(1); hirMAT = t.v(2); }
	   void runRPM(Tokenizer t) { lorRPM = t.v(1); hirRPM = t.v(2); }
	   void runPW (Tokenizer t) { lorPW  = t.v(1); hirPW  = t.v(2); }
	   void runDC (Tokenizer t) { lorDC  = t.v(1); hirDC  = t.v(2); }
	   void runEGC(Tokenizer t) { lorEGC = t.v(1); hirEGC = t.v(2); }
	   void runBC (Tokenizer t) { lorBC  = t.v(1); hirBC  = t.v(2); }
	   void runWC (Tokenizer t) { lorWC  = t.v(1); hirWC  = t.v(2); }
	   void runADC(Tokenizer t) { lorADC = t.v(1); hirADC = t.v(2); }
	   void runVE (Tokenizer t) { lorVE  = t.v(1); hirVE  = t.v(2); }
	   void runACC(Tokenizer t) { lorACC = t.v(1); hirACC = t.v(2); }
	   
	   public void readInit()
	   {
		   
	   }

}
