<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Participant {

    private int noInscription;
    private Date dateInscription;
    private float tempsFinal;
    private Coureur coureur;
    private Vehicule vehicule;
    
    public Participant(int numInscr, Date dateEnreg, Coureur c, Vehicule v, Edition edition) {
        this.noInscription = numInscr;
        this.dateInscription = dateEnreg;
        this.coureur = c;
        this.vehicule = v;
    }
    
    /**
     * 
     * @param e = etape pour laquelle on v�rifie que le participant a bien pris le d�part
     * @return true ou false en fonction de s'il a particip� ou non
     */
    public boolean prendreDepart(Etape e) {
    	boolean prendreDepart=true;
    	if(e.getTabparticipants().get(this)==null) { //v�rifier si le participant a un temps enregistr�(non null) en fonction de sa cl�
    		prendreDepart=false;
    	}
    	return prendreDepart;
    }
    

}
=======
>>>>>>> 4c9514f7897aa84e16c5fd5ccc8eaba3d6b2e18b
