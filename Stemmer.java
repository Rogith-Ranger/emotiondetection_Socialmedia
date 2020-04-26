package stemmer;
import java.util.*;  
import java.lang.*;

/**
  * Stemmer, implementing the Porter Stemming Algorithm
  *
  * The Stemmer class transforms a word into its root form.  The input
  * word can be provided a character at time (by calling add()), or at once
  * by calling one of the various stem(something) methods.
  */

public class Stemmer
{ 
    private static Object Collectors;
   private char[] b;
   private int i,     /* offset into b */
               i_end, /* offset to end of stemmed word */
               j, k;
   private static final int INC = 50;
                     /* unit of size whereby b is increased */
   public Stemmer()
   {  b = new char[INC];
      i = 0;
      i_end = 0;
   }

   /**
    * Add a character to the word being stemmed.  When you are finished
    * adding characters, you can call stem(void) to stem the word.
     * @param ch
    */

   public void add(char ch)
   {  if (i == b.length)
      {  char[] new_b = new char[i+INC];
       System.arraycopy(b, 0, new_b, 0, i);
         b = new_b;
      }
      b[i++] = ch;
   }


   /** Adds wLen characters to the word being stemmed contained in a portion
    * of a char[] array. This is like repeated calls of add(char ch), but
    * faster.
     * @param w
     * @param wLen
    */

   public void add(char[] w, int wLen)
   {  if (i+wLen >= b.length)
      {  char[] new_b = new char[i+wLen+INC];
       System.arraycopy(b, 0, new_b, 0, i);
         b = new_b;
      }
      for (int c = 0; c < wLen; c++) b[i++] = w[c];
   }

   /**
    * After a word has been stemmed, it can be retrieved by toString(),
    * or a reference to the internal buffer can be retrieved by getResultBuffer
    * and getResultLength (which is generally more efficient.)
     * @return 
    */
   @Override
   public String toString() { return new String(b,0,i_end); }

   /**
    * Returns the length of the word resulting from the stemming process.
     * @return 
    */
   public int getResultLength() { return i_end; }

   /**
    * Returns a reference to a character buffer containing the results of
    * the stemming process.  You also need to consult getResultLength()
    * to determine the length of the result.
     * @return 
    */
   public char[] getResultBuffer() { return b; }

   /* cons(i) is true <=> b[i] is a consonant. */

   private boolean cons(int i)
   {  switch (b[i])
      {  case 'a': case 'e': case 'i': case 'o': case 'u': return false;
         case 'y': return (i==0) ? true : !cons(i-1);
         default: return true;
      }
   }

   /* m() measures the number of consonant sequences between 0 and j. if c is
      a consonant sequence and v a vowel sequence, and <..> indicates arbitrary
      presence,
         <c><v>       gives 0
         <c>vc<v>     gives 1
         <c>vcvc<v>   gives 2
         <c>vcvcvc<v> gives 3
         ....
   */

   private int m()
   {  int n = 0;
      int p = 0;
      while(true)
      {  if (p > j) return n;
         if (! cons(p)) break; p++;
      }
      p++;
      while(true)
      {  while(true)
         {  if (p > j) return n;
               if (cons(p)) break;
               p++;
         }
         p++;
         n++;
         while(true)
         {  if (p > j) return n;
            if (! cons(p)) break;
            p++;
         }
         p++;
       }
   }

   /* vowelinstem() is true <=> 0,...j contains a vowel */

   private boolean vowelinstem()
   {  int q; for (q = 0; q <= j; q++) if (! cons(q)) return true;
      return false;
   }

   /* doublec(j) is true <=> j,(j-1) contain a double consonant. */

   private boolean doublec(int j)
   {  if (j < 1) return false;
      if (b[j] != b[j-1]) return false;
      return cons(j);
   }

   /* cvc(i) is true <=> i-2,i-1,i has the form consonant - vowel - consonant
      and also if the second c is not w,x or y. this is used when trying to
      restore an e at the end of a short word. e.g.
         cav(e), lov(e), hop(e), crim(e), but
         snow, box, tray.
   */

   private boolean cvc(int i)
   {  if (i < 2 || !cons(i) || cons(i-1) || !cons(i-2)) return false;
      {  int ch = b[i];
         if (ch == 'w' || ch == 'x' || ch == 'y') return false;
      }
      return true;
   }

   private boolean ends(String s)
   {  int l = s.length();
      int o = k-l+1;
      if (o < 0) return false;
      for (int w = 0; w < l; w++) if (b[o+w] != s.charAt(w)) return false;
      j = k-l;
      return true;
   }

   /* setto(s) sets (j+1),...k to the characters in the string s, readjusting
      k. */

   private void setto(String s)
   {  int l = s.length();
      int o = j+1;
      for (int i = 0; i < l; i++) b[o+i] = s.charAt(i);
      k = j+l;
   }

   /* r(s) is used further down. */

   private void r(String s) { if (m() > 0) setto(s); }

   /* step1() gets rid of plurals and -ed or -ing. e.g.
          caresses  ->  caress
          ponies    ->  poni
          ties      ->  ti
          caress    ->  caress
          cats      ->  cat
          feed      ->  feed
          agreed    ->  agree
          disabled  ->  disable
          matting   ->  mat
          mating    ->  mate
          meeting   ->  meet
          milling   ->  mill
          messing   ->  mess
          meetings  ->  meet
   */

   private void step1()
   {  if (b[k] == 's')
      {  if (ends("sses")) k -= 2; else
         if (ends("ies")) setto("i"); else
         if (b[k-1] != 's') k--;
      }
      if (ends("eed")) { if (m() > 0) k--; } else
      if ((ends("ed") || ends("ing")) && vowelinstem())
      {  k = j;
         if (ends("at")) setto("ate"); else
         if (ends("bl")) setto("ble"); else
         if (ends("iz")) setto("ize"); else
         if (doublec(k))
         {  k--;
            {  int ch = b[k];
               if (ch == 'l' || ch == 's' || ch == 'z') k++;
            }
         }
         else if (m() == 1 && cvc(k)) setto("e");
     }
   }

   /* step2() turns terminal y to i when there is another vowel in the stem. */

   //private void step2() { if (ends("y") && vowelinstem()) b[k] = 'i'; }

   /* step3() maps double suffices to single ones. so -ization ( = -ize plus
      -ation) maps to -ize etc. note that the string before the suffix must give
      m() > 0. */

   private void step3() { if (k == 0) return; /* For Bug 1 */ switch (b[k-1])
   {
       case 'a': if (ends("ational")) { r("ate"); break; }
                 if (ends("tional")) { r("tion"); break; }
                 break;
       case 'c': if (ends("enci")) { r("ence"); break; }
                 if (ends("anci")) { r("ance"); break; }
                 break;
       case 'e': if (ends("izer")) { r("ize"); break; }
                 break;
       case 'l': if (ends("bli")) { r("ble"); break; }
                 if (ends("alli")) { r("al"); break; }
                 if (ends("entli")) { r("ent"); break; }
                 if (ends("eli")) { r("e"); break; }
                 if (ends("ousli")) { r("ous"); break; }
                 break;
       case 'o': if (ends("ization")) { r("ize"); break; }
                 if (ends("ation")) { r("ate"); break; }
                 if (ends("ator")) { r("ate"); break; }
                 break;
       case 's': if (ends("alism")) { r("al"); break; }
                 if (ends("iveness")) { r("ive"); break; }
                 if (ends("fulness")) { r("ful"); break; }
                 if (ends("ousness")) { r("ous"); break; }
                 break;
       case 't': if (ends("aliti")) { r("al"); break; }
                 if (ends("iviti")) { r("ive"); break; }
                 if (ends("biliti")) { r("ble"); break; }
                 break;
       case 'g': if (ends("logi")) { r("log"); break; }
   } }

   /* step4() deals with -ic-, -full, -ness etc. similar strategy to step3. */

   private void step4() { switch (b[k])
   {
       case 'e': if (ends("icate")) { r("ic"); break; }
                 if (ends("ative")) { r(""); break; }
                 if (ends("alize")) { r("al"); break; }
                 break;
       case 'i': if (ends("iciti")) { r("ic"); break; }
                 break;
       case 'l': if (ends("ical")) { r("ic"); break; }
                 if (ends("ful")) { r(""); break; }
                 break;
       case 's': if (ends("ness")) { r(""); break; }
                 break;
   } }

   /* step5() takes off -ant, -ence etc., in context <c>vcvc<v>. */

   private void step5()
   {   if (k == 0) return; /* for Bug 1 */ switch (b[k-1])
       {  case 'a': if (ends("al")) break; return;
          case 'c': if (ends("ance")) break;
                    if (ends("ence")) break; return;
          case 'e': if (ends("er")) break; return;
          case 'i': if (ends("ic")) break; return;
          case 'l': if (ends("able")) break;
                    if (ends("ible")) break; return;
          case 'n': if (ends("ant")) break;
                    if (ends("ement")) break;
                    if (ends("ment")) break;
                    /* element etc. not stripped before the m */
                    if (ends("ent")) break; return;
          case 'o': if (ends("ion") && j >= 0 && (b[j] == 's' || b[j] == 't')) break;
                                    /* j >= 0 fixes Bug 2 */
                    if (ends("ou")) break; return;
                    /* takes care of -ous */
          case 's': if (ends("ism")) break; return;
          case 't': if (ends("ate")) break;
                    if (ends("iti")) break; return;
          case 'u': if (ends("ous")) break; return;
          case 'v': if (ends("ive")) break; return;
          case 'z': if (ends("ize")) break; return;
          default: return;
       }
       if (m() > 1) k = j;
   }

   /* step6() removes a final -e if m() > 1. */

   private void step6()
   {  j = k;
      if (b[k] == 'e')
      {  int a = m();
         if (a > 1 || a == 1 && !cvc(k-1)) k--;
      }
      if (b[k] == 'l' && doublec(k) && m() > 1) k--;
   }

   /** Stem the word placed into the Stemmer buffer through calls to add().
    * Returns true if the stemming process resulted in a word different
    * from the input.  You can retrieve the result with
    * getResultLength()/getResultBuffer() or toString().
    */
   public void stem()
   {  k = i - 1;
      if (k > 1) { step1(); step3(); step4(); step5(); step6(); }
      i_end = k+1; i = 0;
   }

   public String stem(String s) {
	   for(char c : s.toCharArray()) {
		   add(c);
	   }
	   stem();
	   return toString();
   }
   
    public static void main(String[] args) 
    {
        Stemmer stemmer = new Stemmer();
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter the paragraph :");
        String s=sc.nextLine();
        
       List<String> allWords = new ArrayList<>(Arrays.asList(s.toLowerCase().split(" ")));
       List<String> stopWords = Arrays.asList("the","to","be","a","able","about","across","after","all","almost","also","am","among","an","and","any","are","as","at","actual","actually","although","be","because","been","but","by","being","can","cannot","could","dear","did","do","does","down","during","either","else","ever","every","each","elsewhere","even","everybody","everyone","everything","everywhere","except","far","for","from","few","fifth","first","five","followed","following","follows","furthermore","further","get","got","go","give","gets","getting","given","gives","goes","going","good","had","has","have","he","her","hers","him","his","how","however","happens","hardly","having","hereby","hence","hereafter","hereupon","happening","he's","her's","i","if","in","into","is","it","its","ie","indeed","instead","inward","itself","ive","if","just","kind","keep","keeps","kept","know","knows","known","keeping","kidding","least","let","like","likely","last","later","least","less","liked","look","looking","looks","little","lets","may","me","might","most","must","my","mainly","many","maybe","mean","meanwhile","merely","more","moreover","mostly","much","myself","main","neither","no","nor","not","name","namely","near","nearly","need","needs","never","nevertheless","nobody","non","none","normally","nothing","nowhere","now","normal","of","off","often","on","only","or","other","our","own","obviously","oh","ok","okay","one","once","ones","onto","others","otherwise","ought","ourself","ourselves","out","outside","over","overall","put","particular","particularly","per","perhaps","please","placed","possible","presumably","probably","provides","putting","provide","quite","que","rather","really","reasonably","regarding","regardless","regards","relatively","respectively","right","said","say","says","she","should","since","so","some","such","soon","same","saw","saying","see","seeing","seem","seemed","seeming","seems","seen","self","selves","sensible","serious","shall","somebody","someone","somehow","something","sometime","sometimes","somewhat","somewhere","sure","soon","than","that","the","their","them","then","there","these","they","this","tis","to","too","twas","take","taken","tell","tends","thank","thanks","thanx","thats","theirs","themselves","thence","thereafter","thereby","therefore","therein","thereupon","there's","through","throughly","though","thru","thus","throughout","to","together","toward","towards","tried","tries","truly","try","trying","there's","thing","us","under","unfortunately","unless","unlikely","until","unto","up","upon","use","used","useful","uses","using","usually","very","value","various","via","wants","was","we","were","what","when","where","which","while","who","whom","why","will","with","would","without","way","went","well","whatever","whence","whenever","whereafter","whereas","whereby","wherein","whereupon","wherever","whether","whose","willing","within","without","x","you","your","yet","yes","yours","yourself","yourselves","z");
       allWords.removeAll(stopWords);
       System.out.println("After removing stop words:  " + allWords);
       System.out.println();
 
       System.out.println("Chaning to root word: ");
       for (String word : allWords) 
       {
           System.out.print(stemmer.stem(word)+" ");
       }   
       System.out.println();

       
       
    }
}
