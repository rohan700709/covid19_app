import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class WatchlistService {

  url = "/api/v1/watchlist"
  constructor(private http: HttpClient) { }

  getCountry(email: string){
    return this.http.get(`${this.url}/${email}/country`);
  }

  addCountry(email: string, countryInfo: any){
    return this.http.post(`${this.url}/${email}/country`, countryInfo);
  }

  deleteCountry(email: string, name: string){
    //console.log(`${this.url}/${email}/${name}`);
    
    return this.http.delete(`${this.url}/${email}/${name}`);
  }
}
