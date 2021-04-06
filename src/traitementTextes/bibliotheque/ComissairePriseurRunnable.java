package traitementTextes.bibliotheque;

import java.util.ArrayList;

public class ComissairePriseurRunnable implements Runnable {

	private ComissairePriseur comissairePriseur;
	private Bibliothecaire bibliothecaire;
	private ArrayList<Acheteur> winnerList;
	
	public ComissairePriseurRunnable(ComissairePriseur comissairePriseur, Bibliothecaire bibliothecaire) {
		this.comissairePriseur = comissairePriseur;
		this.bibliothecaire = bibliothecaire;
	}

	public void run() {
		try {
			winnerList = comissairePriseur.startEnchere(bibliothecaire);
		} catch (EnchereFailure | LivreBloquePourEncheres | LivreNonTrouveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	ArrayList<Acheteur> getWinnerList() {
		return winnerList;
	}
}
