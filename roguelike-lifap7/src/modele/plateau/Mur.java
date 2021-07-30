package modele.plateau;

public class Mur extends EntiteStatique {
    public Mur(Jeu _jeu) { super(_jeu); }

    @Override
    public boolean traversable() {
        return false;
    }

	@Override
	public boolean jumpable() {
		// TODO Auto-generated method stub
		return false;
	}

}
