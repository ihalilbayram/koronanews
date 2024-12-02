package ibo.utils.searchEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ibo.config.ApplicationData;

public class CitySearchEngine {
	
	ApplicationData	appData;	
	private Locale 	locale;
	private boolean isCaseSensitive;
	
	private HashMap<String,String> cityMap;
	
	public CitySearchEngine(ApplicationData	appData){
		
		this.locale 		= appData.newsData.getLocale();
		this.isCaseSensitive= appData.newsData.isCityNameCaseSensitive();
		
		setCityMap();
	}
	
	
	public String search(String searchText) {
		
		if(!isCaseSensitive) {
			
			searchText = searchText.toLowerCase(locale);
		}
		
		return cityMap.get(searchText);
	}


	
	
	private void setCityMap() {
		
		this.cityMap = new HashMap<String,String>();
		
		List<String> citiesList = List.of("Adana","Adıyaman","Afyonkarahisar","Ağrı","Aksaray","Amasya","Ankara","Antalya","Ardahan", 
											"Artvin","Aydın","Balıkesir","Bartın","Batman","Bayburt","Bilecik","Bingöl","Bitlis","Bolu","Burdur",
											"Bursa","Çanakkale","Çankırı","Çorum","Denizli","Diyarbakır","Düzce","Edirne","Elazığ","Erzincan",
											"Erzurum",	"Eskişehir","Gaziantep","Giresun","Gümüşhane","Hakkâri","Hatay","Iğdır","Isparta","İstanbul","İzmir","Kahramanmaraş",
											"Karabük","Karaman","Kars","Kastamonu","Kayseri","Kırıkkale","Kırklareli","Kırşehir","Kilis","Kocaeli","Konya","Kütahya",
											"Malatya","Manisa","Mardin","Mersin","Muğla","Muş","Nevşehir","Niğde","Ordu","Osmaniye","Rize","Sakarya","Samsun","Siirt",
											"Sinop","Sivas","Şanlıurfa","Şırnak","Tekirdağ","Tokat","Trabzon","Tunceli","Uşak","Van","Yalova","Yozgat","Zonguldak");

		
		citiesList.forEach(key -> {
			
			if(this.isCaseSensitive) {
				this.cityMap.put(key, key);
			}else {				
				this.cityMap.put(key.toLowerCase(locale), key);   // map key converted to lowercase because while searching searchword also will become lowercase
			}
		});
	}
	
	
	
	public Locale getLocale() {
		return locale;
	}


	public HashMap<String, String> getCityMap() {
		return cityMap;
	}


	public boolean isCaseSensitive() {
		return isCaseSensitive;
	}

}
