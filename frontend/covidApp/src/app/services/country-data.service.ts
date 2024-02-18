import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CountryDataService {
  // public countryId: string = '';
  
  constructor(private http: HttpClient) {
    
  }

  getCountryData(countryId: string){
    return this.http.get("https://api.apify.com/v2/key-value-stores/" + countryId + "/records/LATEST?disableRedirect=true")
  }
}
