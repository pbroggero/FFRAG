package FFRAG;
import java.util.*;

public class Edition {
	private int noEdition;
	private Date dateDebER;
	private Date dateFinER;
	private String saison;
	private ArrayList<Etape> listEtape;
	private ArrayList<Participant> listPart;
	private HashMap<Participant, Integer> listTempsGeneral;
	private ArrayList<HashMap.Entry<Participant, Integer>> classementGeneral = new ArrayList<HashMap.Entry<Participant, Integer>>();
	
	public Edition(int noE, Date deb, Date fin, String saison) {
		this.noEdition=noE;
		this.dateDebER=deb;
		this.dateFinER=fin;
		this.saison = saison;
		this.listEtape = new ArrayList<Etape>();
		this.listPart = new ArrayList<Participant>();
		this.listTempsGeneral = new HashMap<Participant, Integer>();
	}

	public String getSaison() {
		return saison;
	}



	public ArrayList<HashMap.Entry<Participant, Integer>> getClassementGeneral() {
		return classementGeneral;
	}


	public void organiserEtape(int code, int distance) {
		Etape etape = new Etape(code, distance);
		listEtape.add(etape);
	}
	
	public void organiserPart(Participant part) {
		listPart.add(part);
<<<<<<< HEAD
=======
		part.setNoInscription((int) (listPart.size()));

>>>>>>> 94384c946f5d512040722a07b0c71e7e1591aba2
		for(Etape e : listEtape) {
			e.organiser(this);
		}
	}
	
	public ArrayList<Etape> getListEtape() {
		return listEtape;
	}
	
	public ArrayList<Participant> getListPart() {
		return listPart;
	}
	
	public void validerClassement(int etape) {
		for(Participant p : listPart) {
			if (listEtape.get(etape-1).validerClassement(p)) {
				listTempsGeneral.put(p, null);
			}
		}
		
	}
	
	public void calculerClassement(int etape) {
		this.validerClassement(etape);
		for(Participant p : listTempsGeneral.keySet()) {
			int temps = 0;
			for (int i = 0; i < etape; i++ ) {
				temps += listEtape.get(i).getTabParticipants().get(p);
			}
			listTempsGeneral.put(p, temps);
		}
		//System.out.println(this.listTempsGeneral.size());
		//mettre dans l'ordre
		Set<HashMap.Entry<Participant, Integer>> entryset = listTempsGeneral.entrySet();
		classementGeneral = new ArrayList<HashMap.Entry<Participant, Integer>>(entryset);
		Collections.sort(classementGeneral, new Comparator<HashMap.Entry<Participant, Integer>>(){
			@Override
			public int compare(HashMap.Entry<Participant, Integer> c1, HashMap.Entry<Participant, Integer> c2) {
				return c1.getValue().compareTo(c2.getValue());
			}
		});
		if(etape == this.listEtape.size()) {
			this.setTempFinal();
		}
		/*int i =0;
		for(Participant p: listTempsGeneral.keySet()) {
			i++;
			System.out.println(listTempsGeneral.get(p));
			System.out.println(i);
		}
		for(HashMap.Entry<Participant, Integer> classement : classementGeneral) {
			System.out.println(classement.getValue());
			System.out.println(classement.getKey().getTempsFinal());
			System.out.println(classement.getKey().getPosition());
		}*/
	}
	
	public void setTempFinal() {
		for(int i = 0; i < classementGeneral.size(); i++) {
			int temps = classementGeneral.get(i).getValue();
			int position = i + 1;
			classementGeneral.get(i).getKey().setTempsFinal(temps);
			classementGeneral.get(i).getKey().setPosition(position);
		}
	}
	
	public Participant getChampion() {
		Participant champion = null;
		if (classementGeneral.size()>0){
			champion = classementGeneral.get(0).getKey();
		}
		return champion;
	}

	public int getNoEdition() {
		return noEdition;
	}

	public Date getDateDebER() {
		return dateDebER;
	}

	public Date getDateFinER() {
		return dateFinER;
	}

}