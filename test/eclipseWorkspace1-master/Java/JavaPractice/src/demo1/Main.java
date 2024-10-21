package demo1;
import java.util.ArrayList;

	public class Main {
	    public static void main(String[] args) {
	        int populationLimit = 31028700;
	        int r = 6371000;
	        ArrayList<Country> allCountries = new ArrayList<>();
	        ArrayList<Country> countries = new ArrayList<>();
	        for (Country country : allCountries) {
	            if (country.getPopulation() >= populationLimit && country.getNumberOfCurencies() > 1) {
	                countries.add(country);
	                if (countries.size() == 20) {
	                    break;
	                }
	            }
	        }
	        double total = 0;
	        for (int i = 0; i < countries.size(); i++) {
	            for (int j = i + 1; j < countries.size(); j++) {
	                double fi1 = countries.get(i).getLatitude() * Math.PI / 180; 
	                double fi2 = countries.get(j).getLatitude() * Math.PI / 180;
	                double dFi = (countries.get(j).getLatitude() - countries.get(i).getLatitude()) * Math.PI / 180;
	                double dLam = (countries.get(j).getLongitude() - countries.get(i).getLongitude()) * Math.PI / 180;
	                double a = Math.sin(dFi / 2) * Math.sin(dFi / 2) +
	                        Math.cos(fi1) * Math.cos(fi2) * Math.sin(dLam / 2) * Math.sin(dLam / 2);
	                double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	                total += r * c;
	            }
	        }
	        System.out.printf("Total distance: %.2f\n", total / 1000);
	    }
	}

