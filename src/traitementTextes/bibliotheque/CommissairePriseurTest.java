package traitementTextes.bibliotheque;

/**
* Classe qui contient les tests de la classe ComissairePriseur
* @author Anna Niskovskikh et Anaëlle Pierredon 
* @version 1.2
*/

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommissairePriseurTest {
	
	private Bibliothecaire bibliothecaire;

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	void displayTestHeader(String test)
	{
		System.out.println( "---------------------------------- " + test + "---------------------------------- ");
	}
	
	@Test
	void testAddEncherisseur() {
		displayTestHeader("Start testAddEncherisseur");
		
		//GIVEN
		ArrayList<Acheteur> encherisseurList = new ArrayList<Acheteur>();
		
		Auteur tolstoy = new Auteur("Tolstoy");
		Acheteur acheteur1 = new Acheteur("Pierredon", "Anaëlle", tolstoy);
		
		//WHEN
		encherisseurList.add(acheteur1);
		
		//THEN
		assertNotNull(encherisseurList);
		assertTrue(encherisseurList.contains(acheteur1));
		
		displayTestHeader("Stop testAddEncherisseur");
	}
	
	@Test
	void testStartEnchere() {
		displayTestHeader("Start testStartEnchere");
		
		//GIVEN
		Auteur tolstoy = new Auteur("Tolstoy");
		Livre guerreEtPaix = new Livre(tolstoy, "Guerre et Paix", 75);
		LivreEnchere guerreEtPaixAuxEcheres = new LivreEnchere(guerreEtPaix, 15);
	    
		ComissairePriseur comissairePriseur = new ComissairePriseur();
			
		Acheteur acheteur1 = new Acheteur("Pierredon", "Anaëlle", tolstoy);
		Acheteur acheteur2 = new Acheteur("Niskovskikh", "Anna", tolstoy);
	    comissairePriseur.addEncherisseur(acheteur1);
	    comissairePriseur.addEncherisseur(acheteur2);
	    
	    Acheteur winner;
	    //WHEN
	    try {
	    	winner = comissairePriseur.startEnchere(guerreEtPaixAuxEcheres);
	    }
	    catch(EnchereFailure e)
	    {
	    	System.out.println( "Exception catché. Le test a échoué");
	    	return;
	    }
	    
	    //THEN
	    assertNotNull(winner);
	    assertTrue(winner.getPrenom().equals(acheteur1.getPrenom()) || winner.getPrenom().equals(acheteur2.getPrenom()));
	    
	    displayTestHeader("Stop testStartEnchere");
	}
	
	@Test
	void testcreerThreads() {
		displayTestHeader("Start testcreerThreads");
		
		//GIVEN
		Auteur tolstoy = new Auteur("Tolstoy");
		Livre guerreEtPaix = new Livre(tolstoy, "Guerre et Paix", 75);
		LivreEnchere guerreEtPaixAuxEcheres = new LivreEnchere(guerreEtPaix, 15);
	    
	    ArrayList<Acheteur> encherisseurList = new ArrayList<Acheteur>();
	    ArrayList<EncherisseurThread> encherisseurThreadList = new ArrayList<EncherisseurThread>();
	    ComissairePriseur comissairePriseur = new ComissairePriseur();
		
		Acheteur acheteur1 = new Acheteur("Pierredon", "Anaëlle", tolstoy);
		Acheteur acheteur2 = new Acheteur("Niskovskikh", "Anna", tolstoy);
		comissairePriseur.addEncherisseur(acheteur1);
	    comissairePriseur.addEncherisseur(acheteur2);
	    
		//WHEN
		encherisseurThreadList = comissairePriseur.creerThreads(encherisseurList, guerreEtPaixAuxEcheres);
	    
		//THEN
		assertNotNull(encherisseurThreadList);
		for(var encherisseurThread : encherisseurThreadList)
			assertTrue(encherisseurThread.getName().equals(acheteur1.getPrenom()) || encherisseurThread.getName().equals(acheteur2.getPrenom()));
		
		displayTestHeader("Stop testcreerThreads");
	}
	

	@Test
	void testGlobal() {
		displayTestHeader("Start testGlobal");
	
		//GIVEN
	    Auteur hugo = new Auteur("Hugo");
	    Livre miserables = new Livre(hugo, "Les misérables", 50);
	    Auteur tolstoy = new Auteur("Tolstoy");
	    Livre guerreEtPaix = new Livre(tolstoy, "Guerre et Paix", 75);
		
		HashMap<Auteur, ArrayList<Livre>> catalogue = new HashMap<>();		
		bibliothecaire = new Bibliothecaire(catalogue);	    
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
	    catch(Exception e) {
	    	System.out.println( "Exception catché. Le test a échoué. " + e.getMessage());
	    	return;
	    }
	    
	    //THEN
	    for(var winner : winnerList)
	    	assertTrue(winner.getPrenom().equals(acheteur1.getPrenom()) || winner.getPrenom().equals(acheteur2.getPrenom()));
	    
	    displayTestHeader("Stop testGlobal");
	}
	
	@Test
	void testGlobalQuatreAcheteurs() {
		displayTestHeader("Start testGlobalQuatreAcheteurs");
		
		//GIVEN
	    Auteur hugo = new Auteur("Hugo");
	    Livre miserables = new Livre(hugo, "Les misérables", 50);
	    Auteur tolstoy = new Auteur("Tolstoy");
	    Livre guerreEtPaix = new Livre(tolstoy, "Guerre et Paix", 75);

		HashMap<Auteur, ArrayList<Livre>> catalogue = new HashMap<>();		
		bibliothecaire = new Bibliothecaire(catalogue);
	    bibliothecaire.ajouterLivre(miserables);
	    bibliothecaire.ajouterLivre(guerreEtPaix);
	    
	    ComissairePriseur comissairePriseur = new ComissairePriseur();
	    var acheteur1 = new Acheteur("Pierredon", "Anaëlle", hugo);
	    var acheteur2 = new Acheteur("Niskovskikh", "Anna", hugo);
	    var acheteur3 = new Acheteur("Rousseau", "Jean-Jacques", hugo);
	    var acheteur4 = new Acheteur("Kant", "Emmanuel", hugo);
	    comissairePriseur.addEncherisseur(acheteur1);
	    comissairePriseur.addEncherisseur(acheteur2);
	    comissairePriseur.addEncherisseur(acheteur3);
	    comissairePriseur.addEncherisseur(acheteur4);
	  
	    ArrayList<Acheteur> winnerList;
	    //WHEN
	    try {
	    	winnerList = comissairePriseur.startEnchere(bibliothecaire);
	    }
	    catch(Exception e) {
	    	System.out.println( "Exception catché. Le test a échoué. " + e.getMessage());
	    	return;
	    }
	    
	    //THEN
	    assertEquals(1, winnerList.size());
	    for(var winner : winnerList)
	    	assertTrue(winner.getPrenom().equals(acheteur1.getPrenom()) || 
	    			   winner.getPrenom().equals(acheteur2.getPrenom()) ||     
	    			   winner.getPrenom().equals(acheteur3.getPrenom()) ||
	    			   winner.getPrenom().equals(acheteur4.getPrenom())
	    			   );
	    
	    displayTestHeader("Stop testGlobalQuatreAcheteurs");
	}
	
	@Test
	void testBibliothecaireLivreBloqueParEnchere() {
		displayTestHeader("Start testLivreBloqueParEnchere");
		
		//GIVEN
	    Auteur hugo = new Auteur("Hugo");
	    Livre miserables = new Livre(hugo, "Les misérables", 50);
	    		
		HashMap<Auteur, ArrayList<Livre>> catalogue = new HashMap<>();		
		bibliothecaire = new Bibliothecaire(catalogue);	    
	    bibliothecaire.ajouterLivre(miserables);
	    try {
			bibliothecaire.setEnchereEnCoursSurLivre(miserables, true);
		} catch (LivreNonTrouveException e1) {
		}
 
	    // THEN
	    boolean estLivreBloqueParEnchere = false;
	    try {
	    	Thread.sleep(1000);
	    	bibliothecaire.enleverLivre(miserables);
	    } 
	    catch (LivreBloquePourEncheres e) {
	    	System.out.println( "Livre Bloqué");
			estLivreBloqueParEnchere = true;
		} catch (LivreNonTrouveException e) {
	    	System.out.println( "Exception catché. Le test a échoué");
	    	return;
		} catch (InterruptedException e) {
	    	System.out.println( "Exception catché. Le test a échoué");
	    	return;
		}
	
	    Assert.assertTrue(estLivreBloqueParEnchere);
	}
	
	@Test
	void testCommissairePriseurLivreBloqueParEnchere() {
		displayTestHeader("Start testLivreBloqueParEnchere");
		
		//GIVEN
	    Auteur hugo = new Auteur("Hugo");
	    Livre miserables = new Livre(hugo, "Les misérables", 50);
	    		
		HashMap<Auteur, ArrayList<Livre>> catalogue = new HashMap<>();		
		bibliothecaire = new Bibliothecaire(catalogue);	    
	    bibliothecaire.ajouterLivre(miserables);
	    
	    ComissairePriseur comissairePriseur = new ComissairePriseur();
	    var acheteur1 = new Acheteur("Pierredon", "Anaëlle", hugo);
	    var acheteur2 = new Acheteur("Niskovskikh", "Anna", hugo);
	    comissairePriseur.addEncherisseur(acheteur1);
	    comissairePriseur.addEncherisseur(acheteur2);
	    
	    //WHEN
	    var comissairePriserRunnable = new ComissairePriseurRunnable(comissairePriseur, bibliothecaire);
	    var comissairePriserThread = new Thread(comissairePriserRunnable);
	    comissairePriserThread.start();
  
	    // THEN
	    boolean estLivreBloqueParEnchere = false;
	    try {
	    	Thread.sleep(1000);
	    	bibliothecaire.enleverLivre(miserables);
	    } 
	    catch (LivreBloquePourEncheres e) {
	    	System.out.println( "Livre Bloqué");
			estLivreBloqueParEnchere = true;
		} catch (LivreNonTrouveException e) {
	    	System.out.println( "Exception catché. Le test a échoué");
	    	return;
		} catch (InterruptedException e) {
	    	System.out.println( "Exception catché. Le test a échoué");
	    	return;
		}
	
	    Assert.assertTrue(estLivreBloqueParEnchere);
	    try {
			comissairePriserThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    var winnerList = comissairePriserRunnable.getWinnerList();
	    for(var winner : winnerList)
	    	assertTrue(winner.getPrenom().equals(acheteur1.getPrenom()) || 
	    			   winner.getPrenom().equals(acheteur2.getPrenom()) 
	    			   );
	    
		displayTestHeader("Stop testLivreBloqueParEnchere");
	}
	
	@Test
	void testBibliothecaireLivreNonmisAuxEncheresCarEmprunte() throws Exception {
		//GIVEN
	    Auteur hugo = new Auteur("Hugo");
	    Livre miserables = new Livre(hugo, "Les misérables", 50);
	    Auteur tolstoy = new Auteur("Tolstoy");
	    Livre guerreEtPaix = new Livre(tolstoy, "Guerre et Paix", 75);

		HashMap<Auteur, ArrayList<Livre>> catalogue = new HashMap<>();		
		bibliothecaire = new Bibliothecaire(catalogue);
	    bibliothecaire.ajouterLivre(miserables);
	    bibliothecaire.ajouterLivre(guerreEtPaix);
	    
	    Etudiant etudiant = new Etudiant("Niskovskikh", "Anna");
	    bibliothecaire.preterLivre(guerreEtPaix, etudiant);
	    
	    //WHEN
	    var livrePourEnchere = bibliothecaire.getLivreAVendreAuxEncheres();
	    
	    //THEN
	    assertEquals(1, livrePourEnchere.size());
	    assertEquals(miserables, livrePourEnchere.get(0).getLivre());
		
	}
}
