import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CountryDataService } from '../services/country-data.service';

@Component({
  selector: 'app-country',
  templateUrl: './country.component.html',
  styleUrls: ['./country.component.css']
})
export class CountryComponent implements OnInit {
  public countryName: string = '';
  public countryId: string = '';
  public countryData: any;
  

  constructor(private route: ActivatedRoute, private countryDataService: CountryDataService) {}

  ngOnInit(): void {
    this.countryName = this.route.snapshot.params['countryName'];
    this.countryId = this.route.snapshot.params['countryId'];

    this.countryDataService.getCountryData(this.countryId).subscribe((countryD: object) => {
      this.countryData = countryD;
    })
  }
}