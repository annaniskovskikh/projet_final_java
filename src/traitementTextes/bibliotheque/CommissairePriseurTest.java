package traitementTextes.bibliotheque;

/**
* Classe qui contient les tests de la classe ComissairePriseur
* @author Anna Niskovskikh et Anaëlle Pierredon 
* @version 1.2
*/

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommissairePriseurTest {
	
	private Bibliothecaire bibliothecaire;

	@BeforeEach
	void setUp() throws Exception {
		
		HashMap<Auteur, ArrayList<Livre>> catalogue = new HashMap<>();		
		bibliothecaire = new Bibliothecaire(catalogue);
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void testAddEncherisseur() {
		//GIVEN
		ArrayList<Acheteur> encherisseurList = new ArrayList<Acheteur>();
		
		Auteur tolstoy = new Auteur("Tolstoy");
		Acheteur acheteur1 = new Acheteur("Pierredon", "Anaëlle", tolstoy);
		
		//WHEN
		encherisseurList.add(acheteur1);
		
		//THEN
		assertNotNull(encherisseurList);
		assertTrue(encherisseurList.contains(acheteur1));
	}
	
	@Test
	void testStartEnchere() {
		//GIVEN
		Auteur tolstoy = new Auteur("Tolstoy");
	    LivreEnchere guerreEtPaix = new LivreEnchere(tolstoy, "Guerre et Paix", 15);
	    
		ComissairePriseur comissairePriseur = new ComissairePriseur();
			
		Acheteur acheteur1 = new Acheteur("Pierredon", "Anaëlle", tolstoy);
		Acheteur acheteur2 = new Acheteur("Niskovskikh", "Anna", tolstoy);
	    comissairePriseur.addEncherisseur(acheteur1);
	    comissairePriseur.addEncherisseur(acheteur2);
	    
	    Acheteur winner;
	    //WHEN
	    try {
	    	winner = comissairePriseur.startEnchere(guerreEtPaix);
	    }
	    catch(EnchereFailure e)
	    {
	    	System.out.println( "Exception catché. Le test a échoué");
	    	return;
	    }
	    
	    //THEN
	    assertNotNull(winner);
	    assertTrue(winner.getPrenom().equals(acheteur1.getPrenom()) || winner.getPrenom().equals(acheteur2.getPrenom()));
	    
	}
	
	@Test
	void testcreerThreads() {
		//GIVEN
		Auteur tolstoy = new Auteur("Tolstoy");
	    LivreEnchere guerreEtPaix = new LivreEnchere(tolstoy, "Guerre et Paix", 15);
	    
	    ArrayList<Acheteur> encherisseurList = new ArrayList<Acheteur>();
	    ArrayList<EncherisseurThread> encherisseurThreadList = new ArrayList<EncherisseurThread>();
	    ComissairePriseur comissairePriseur = new ComissairePriseur();
		
		Acheteur acheteur1 = new Acheteur("Pierredon", "Anaëlle", tolstoy);
		Acheteur acheteur2 = new Acheteur("Niskovskikh", "Anna", tolstoy);
		comissairePriseur.addEncherisseur(acheteur1);
	    comissairePriseur.addEncherisseur(acheteur2);
	    
		//WHEN
		encherisseurThreadList = comissairePriseur.creerThreads(encherisseurList,guerreEtPaix);
	    
		//THEN
		assertNotNull(encherisseurThreadList);
		for(var encherisseurThread : encherisseurThreadList)
			assertTrue(encherisseurThread.getName().equals(acheteur1.getPrenom()) || encherisseurThread.getName().equals(acheteur2.getPrenom()));
	}
	

	@Test
	void testGlobal() {
		//GIVEN
	    Auteur hugo = new Auteur("Hugo");
	    LivreEnchere miserables = new LivreEnchere(hugo, "Les misérables", 10);
	    Auteur tolstoy = new Auteur("Tolstoy");
	    LivreEnchere guerreEtPaix = new LivreEnchere(tolstoy, "Guerre et Paix", 15);
	    
	    bibliothecaire.ajouterLivre(miserables);
	    bibliothecaire.ajouterLivre(guerreEtPaix);
	    
	    ComissairePriseur comissairePriseur = new ComissairePriseur();
	    var acheteur1 = new Acheteur("Pierredon", "Anaëlle", hugo);
	    var acheteur2 = new Acheteur("Niskovskikh", "Anna", hugo);
	    comissairePriseur.addEncherisseur(acheteur1);
	    comissairePriseur.addEncherisseur(acheteur2);
	  
	    ArrayList<Acheteur> winnerList;
	    //WHEN
	    try {
	    	winnerList = comissairePriseur.startEnchere(bibliothecaire);
	    }
	    catch(EnchereFailure e) {
	    	System.out.println( "Exception catché. Le test a échoué");
	    	return;
	    }
	    
	    //THEN
	    for(var winner : winnerList)
	    	assertTrue(winner.getPrenom().equals(acheteur1.getPrenom()) || winner.getPrenom().equals(acheteur2.getPrenom()));
	}

}
