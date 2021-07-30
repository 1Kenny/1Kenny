package modele.plateau;

public class Dalle extends EntiteStatique {

    private static boolean utilisable;

    public boolean isUtilisable() {
        return utilisable;
    }

    public Dalle(Jeu _jeu)
    {
        super(_jeu);
        utilisable = true;
    }

    @Override
    public boolean traversable()
    {
        if(utilisable)						//On verifie si elle est utilisable
        {
            utilisable = false;				//Si le heros vas dessus elle devient inutilisable
            return true;
        }
        else return false;
    }
    public static void passentrue() {		//Eteins les flammes sur la dalle
    	utilisable=true;
    }

	@Override
	public boolean jumpable() {
		// TODO Auto-generated method stub
		return true;
	}

}


