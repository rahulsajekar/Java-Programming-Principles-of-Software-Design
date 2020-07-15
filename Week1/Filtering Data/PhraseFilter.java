
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter{

    private String Where;
    private String Phrase;
    public PhraseFilter(String where, String phrase)
    {
        Where = where;
        Phrase = phrase;
    }
    
    public boolean satisfies(QuakeEntry qe)
    {
        String title = qe.getInfo();
        int index = title.indexOf(Phrase);
        if(Where.equals("start"))
        {
            return index == 0;
        }
        else if(Where.equals("end"))
        {
            return index == (title.length()-Phrase.length());
        }
        else if(Where.equals("any"))
        {
            return index != -1;
        }
        else
        {
            return false;
        }
    }
    
    public String getName()
    {
        return "Phrase";
    }
}
