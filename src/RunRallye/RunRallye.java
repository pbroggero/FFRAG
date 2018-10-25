package RunRallye;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

import FFRAG.Coureur;
import FFRAG.Edition;
import FFRAG.Etape;
import FFRAG.FFRAG;
import FFRAG.Participant;
import FFRAG.Rallye;
import FFRAG.Voiture;
import vue.Bienvenue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
public class RunRallye {
	
	

    public static void main(String[] args) throws IOException,FileNotFoundException, ParseException {
    	
    	FFRAG ffrag = new FFRAG();
    	
        String csvPath = "data/";
    	String csv = "Coureurs.csv";
    	String csvVal = "ValThorens.csv";
    	String csvPuiss = "Puissances.csv";
    	String csvSuper = "SuperBesse.csv";
        SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        int numins= 0;
        int ned = 1;
    	
        
        // r�cup�rer les coureurs 
	     try {
	    	 int i=0;//on ne traite pas l'ent�te des tableaux
		     br = new BufferedReader(new FileReader(csvPath+csv));
		     while ((line = br.readLine()) != null) {
		    	 if(i!=0) {
		    		 String[] tab = line.split(cvsSplitBy);
			         //Coureur 
			         String name = tab[0];
			         String lastName = "";
			         String firstName= "";
			         if(name.split(" ").length>1){
			        	 lastName = name.substring(name.lastIndexOf(" ")+1);//dernier caract�re avec l'espace
			              firstName = name.substring(0, name.lastIndexOf(" "));
			          }else{
			               firstName = name;
			          }
		              Date d = dateformat.parse(tab[1]);
		              ffrag.insertCoureur(lastName, firstName, d, tab[2], tab[3]);

			      }
		    	 i++;
		     }
		     
	     	} finally {
				if (br != null) {
					try {
						br.close();
						} catch (IOException e) {
							e.printStackTrace();
					    }
					 }
				}
				
	    
	        //creation des rallyes
	        ffrag.creationRallye("ValThorens", "ValThorens", "France");
	        ffrag.creationRallye("SuperBesse", "Besse", "France");
	        
	        Date datedebV = dateformat.parse("08/12/2017");	 
	        Date datefinV = dateformat.parse("01/01/2018");	        
	        Date datedebS = dateformat.parse("27/01/2018");	 
	        Date datefinS = dateformat.parse("01/02/2018");	 
	        
	        //cr�ation edition de Val Thorens
	        for(Rallye r: ffrag.getListRallye()) {
	        	if(r.getNomRallye() == "ValThorens") {
	        		r.organiser(ned, datedebV, datefinV, "2017 / 2018");
	        		for(Edition eV : r.getListeEdition()) {
	        			if(eV.getNoEdition()==ned) {
		        			eV.organiserEtape(1, 347);
		        			eV.organiserEtape(2, 305);
		        			eV.organiserEtape(3, 552);
		        			eV.organiserEtape(4, 180);
		        			eV.organiserEtape(5, 210);
		        			eV.organiserEtape(6, 380);
		        			eV.organiserEtape(7, 645);
		        			eV.organiserEtape(8, 325);
		        			eV.organiserEtape(9, 305);
		        			eV.organiserEtape(10, 179);
		        			
	        			}
	        		}
	        	}
	        }
		   
	        //r�cup�rer v�hicules 
	        
	        
	        try {
	        	int j=0;//on ne traite pas l'ent�te des tableaux
		        br = new BufferedReader(new FileReader(csvPath+csvPuiss));
		        while ((line = br.readLine()) != null) {
		        	if(j!=0) {
		        		String[] tabPuiss = line.split(cvsSplitBy);
				        float pui = Float.parseFloat(tabPuiss[1]); 
			            ffrag.insertVoiture(tabPuiss[0], pui);
			        }
		            j++;

 		      }
		        
	        } finally {
				if (br != null) {
					try {
						br.close();
						} catch (IOException e) {
							e.printStackTrace();
					    }
					 }
				}
	        
	        
	        
	        
	        

	        //Cr�er participants pour ValThorens
	      
	        for(Rallye r: ffrag.getListRallye()) {
	        	if(r.getNomRallye() == "ValThorens") {
	        		for(Edition eV : r.getListeEdition()) {
	        			if(eV.getNoEdition()==ned) {
	        				
	        				 try {
	        			        	int k=0;//on ne traite pas l'ent�te des tableaux
	        				        br = new BufferedReader(new FileReader(csvPath+csvVal));
	        				        while ((line = br.readLine()) != null) {

	        				            	if(k!=0) {
	        					                String[] tab = line.split(cvsSplitBy);
	        					              
	        					                //Coureur 
	        					                String name = tab[0];
	        					                String lastName = "";
	        					                String firstName= "";
	        					                
	        					                if(name.split(" ").length>1){
	        					                	lastName = name.substring(name.lastIndexOf(" ")+1);//dernier caract�re avec l'espace
	        					                	firstName = name.substring(0, name.lastIndexOf(" "));
	        					                	    					}
	        					                else{
	        					                	  firstName = name;
	        					                	    }
	        					                for(Coureur c: ffrag.getListCoureur()) {

	    		        			        		if(c.getNomCoureur().equals(lastName) &&c.getPrenomCoureur().equals(firstName)) {
	    		        			        			int nIns = Integer.parseInt(tab[2]);
	    			        					        Date dateIns = dateformat.parse("03/03/2017");
	    			        					        for(Voiture v: ffrag.getListVoiture()){
	    			        					        	
	    			        					        	if(v.getModele().equals(tab[1])) {
	    			        					        		eV.organiserPart(new Participant(nIns,dateIns,c, v));
		 	    			        			                numins++;

	    			        					        	}
	    			        					        }
	    			        					    }
	    			        			               

	    			        			      }
	    			        			  }
	        				              
	        				            k++;
    
	        					                
	        					                
	        				        }

	        				 	}
				 
							finally {
								if (br != null) {
									try {
										br.close();
										} catch (IOException e) {
											e.printStackTrace();
									    }
									 }
								}
			        				
	        			}
	        		}
	        	}
	        }
	        
	        
	        //enregistrer temps des coureurs edition Valthorens
	        for(Rallye r: ffrag.getListRallye()) {
	        	if(r.getNomRallye() == "ValThorens") {
	        		for(Edition eV : r.getListeEdition()) {
	        			if(eV.getNoEdition()==ned) {
	        				for(Etape et: eV.getListEtape()){
	        					
	        					for(Participant p : eV.getListPart()){
	        						
	        						try {

		        			        	int m=0;//on ne traite pas l'ent�te des tableaux
		        				        br = new BufferedReader(new FileReader(csvPath+csvVal));
		        				        while ((line = br.readLine()) != null) {

		        				            	if(m!=0) {
		        					                String[] tab = line.split(cvsSplitBy);
    		        			        			int nIns = Integer.parseInt(tab[2]);
    		        			        			
    		        			        			
		        					                if(p.getNoInscription()==nIns){
		        					                	
		        					                	
		        					                	int h = Integer.parseInt(tab[et.getCodeEtape()+ 2].substring(0,2)); //tableau fixe
		        					                	int mm = Integer.parseInt(tab[et.getCodeEtape()+ 2].substring(3,5));
		        					                	int sec = Integer.parseInt(tab[et.getCodeEtape()+ 2].substring(6,8));
		        					                	System.out.println(h + " " + mm +" " + sec);
		        					              

		        					                	et.enregistreTemp(p, h, mm, sec,00);
		        					                

		        					                }
		        				            	}
		        				               	
		        				            	
		        				            	m++;
	    
		        					                
		        					                
		        				        }

	        						}
								 
									finally {
										if (br != null) {
											try {
												br.close();
												} catch (IOException e) {
													e.printStackTrace();
											    }
											 }
										}

	        					}
	        				}
		        					        			
	        			}
	        		}
	        	}
	        }
	        
	        
	        //cr�er edition pour SuperBesse
	        
	        for(Rallye r: ffrag.getListRallye()) {
	        	if(r.getNomRallye() == "SuperBesse") {
	        		r.organiser(ned, datedebV, datefinV, "2018/2019");
	        		for(Edition eV : r.getListeEdition()) {
	        			if(eV.getNoEdition()==ned) {
		        			eV.organiserEtape(1, 360);
		        			eV.organiserEtape(2, 325);
		        			eV.organiserEtape(3, 658);
		        			eV.organiserEtape(4, 157);
		        			eV.organiserEtape(5, 285);
		        			eV.organiserEtape(6, 423);
		        			eV.organiserEtape(7, 592);
		        			eV.organiserEtape(8, 227);
		        			eV.organiserEtape(9, 219);
		        			eV.organiserEtape(10, 280);
		        			eV.organiserEtape(11, 267);
		        			eV.organiserEtape(12, 302);

		        			
	        			}
	        		}
	        	}
	        }
	        
	        
	        
	        
	        //cr�er participants pour SuperBesse

	        for(Rallye r: ffrag.getListRallye()) {
	        	if(r.getNomRallye() == "SuperBesse") {
	        		for(Edition eV : r.getListeEdition()) {
	        			if(eV.getNoEdition()==ned) {
	        				
	        				 try {
	        			        	int l=0;//on ne traite pas l'ent�te des tableaux
	        				        br = new BufferedReader(new FileReader(csvPath+csvSuper));
	        				        while ((line = br.readLine()) != null) {

	        				            	if(l!=0) {
	        					                String[] tab = line.split(cvsSplitBy);
	        					              
	        					                //Coureur 
	        					                String name = tab[0];
	        					                String lastName = "";
	        					                String firstName= "";
	        					                
	        					                if(name.split(" ").length>1){
	        					                	lastName = name.substring(name.lastIndexOf(" ")+1);//dernier caract�re avec l'espace
	        					                	firstName = name.substring(0, name.lastIndexOf(" "));
	        					                	    					}
	        					                else{
	        					                	  firstName = name;
	        					                	    }
	        					                for(Coureur c: ffrag.getListCoureur()) {

	    		        			        		if(c.getNomCoureur().equals(lastName) &&c.getPrenomCoureur().equals(firstName)) {

	    		        			        			int nIns = Integer.parseInt(tab[2]);
	    			        					        Date dateIns = dateformat.parse("31/12/2017");
	    			        					        for(Voiture v: ffrag.getListVoiture()){
	    			        					        	
	    			        					        	if(v.getModele().equals(tab[1])) {
	    			        					        		eV.organiserPart(new Participant(nIns,dateIns,c, v));
		 	    			        			                numins++;

	    			        					        	}
	    			        					        }
	    			        					    }
	    			        			      }
	    			        			  }

	        				            	l++;       
	        					                
	        				        }

	        				 	}
	        					finally {
	        						if (br != null) {
	        							try {
	        								br.close();
	        								} catch (IOException e) {
	        									e.printStackTrace();
	        							    }
	        							 }
	        						}
	        			}
	        		}
	        	}
	        }

	        //enregistrer temps des coureurs edition SuperBesse
	        for(Rallye r: ffrag.getListRallye()) {
	        	if(r.getNomRallye() == "SuperBesse") {
	        		for(Edition eV : r.getListeEdition()) {
	        			if(eV.getNoEdition()==ned) {
	        				for(Etape et: eV.getListEtape()){
	        					
	        					for(Participant p : eV.getListPart()){
	        						
	        						try {

		        			        	int m=0;//on ne traite pas l'ent�te des tableaux
		        				        br = new BufferedReader(new FileReader(csvPath+csvSuper));
		        				        while ((line = br.readLine()) != null) {

		        				            	if(m!=0) {
		        					                String[] tab = line.split(cvsSplitBy);
    		        			        			int nIns = Integer.parseInt(tab[2]);
    		        			        			
    		        			        			
		        					                if(p.getNoInscription()==nIns){
		        					                	
		        					                	
		        					                	int h = Integer.parseInt(tab[et.getCodeEtape()+ 2].substring(0,2)); //tableau fixe
		        					                	int mm = Integer.parseInt(tab[et.getCodeEtape()+ 2].substring(3,5));
		        					                	int sec = Integer.parseInt(tab[et.getCodeEtape()+ 2].substring(6,8));
		        					                	System.out.println(h + " " + mm +" " + sec);
		        					              

		        					                	et.enregistreTemp(p, h, mm, sec,00);

		    			        			      }
		    			        			  }
		        				               	
		        				            m++;		                
		        					                
		        				       }

	        						}
					 
									finally {
										if (br != null) {
											try {
												br.close();
												} catch (IOException e) {
													e.printStackTrace();
											    }
											 }
										}
	        							
	        					}
	        				}
		        					        			
	        			}
	        		}
	        	}
	        }
	        System.out.println(ffrag.getListCoureur().get(0).getNomCoureur());
	        EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Bienvenue frame = new Bienvenue(ffrag);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
    }
}
	        				
	        				
	        		
	        				
	        				
	        				
	        				
	        				
	        				
	        				
	        				
	        			
	        				        	

