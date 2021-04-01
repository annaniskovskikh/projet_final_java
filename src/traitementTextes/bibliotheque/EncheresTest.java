package traitementTextes.bibliotheque;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EncheresTest {
	
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
	void testStartEnchereLivre() {
		//GIVEN
		Auteur tolstoy = new Auteur("Tolstoy");
	    LivreEnchere guerreEtPaix = new LivreEnchere(tolstoy, "Guerre et Paix", 15);
	    
		ComissairePriseur comissairePriseur = new ComissairePriseur(bibliothecaire);
		bibliothecaire.ajouterLivre(guerreEtPaix);
			
		Acheteur acheteur1 = new Acheteur("Pierredon", "Anaëlle", tolstoy);
		Acheteur acheteur2 = new Acheteur("Niskovskikh", "Anna", tolstoy);
	    comissairePriseur.addEncherisseur(acheteur1);
	    comissairePriseur.addEncherisseur(acheteur2);
	    
	    ArrayList<EncherisseurThread> acheteursThreadList = new ArrayList<EncherisseurThread>();
	    acheteursThreadList.add(new EncherisseurThread(acheteur1, guerreEtPaix));
	    acheteursThreadList.add(new EncherisseurThread(acheteur2, guerreEtPaix));
	    
	    //WHEN
	    comissairePriseur.StartEnchereLivre(guerreEtPaix);
	    
	    //THEN
	    
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
	    
	    ComissairePriseur comissairePriseur = new ComissairePriseur(bibliothecaire);
	    comissairePriseur.addEncherisseur(new Acheteur("Pierredon", "Anaëlle", hugo));
	    comissairePriseur.addEncherisseur(new Acheteur("Niskovskikh", "Anna", hugo));
	  
	    //WHEN
	    comissairePriseur.StartEnchere();
	    
	    //THEN
	    assertNotNull(miserables.getAuteur());
	    assertNotNull(guerreEtPaix.getAuteur());
	    assertNotNull(bibliothecaire.getCatalogue());
	    assertNotNull(comissairePriseur);
	    
	    
	}

}
