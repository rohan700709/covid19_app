import { DecimalPipe } from '@angular/common'
import { Component, OnInit, PipeTransform } from '@angular/core'
import { FormControl } from '@angular/forms'
import { Observable } from 'rxjs'
import { CountryService } from '../services/country.service'
import { map, startWith } from 'rxjs/operators'
import { WatchlistService } from '../services/watchlist.service'
import Swal from 'sweetalert2'
import { LoginService } from '../services/login.service';

interface Country {
    country: string
    infected: string
    recovered: string
    deceased: string
}

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css'],
    providers: [DecimalPipe],
})
export class HomeComponent implements OnInit {
    public loggedIn = true;
    public countries: Country[] = []
    // nation$: Observable<Country[]>;
    public allCountries: Country[] = []
    filter = new FormControl('')
    countryInfo = {
        country: '',
        id: '',
        infected: '',
        deceased: '',
        recovered: '',
    }

    searchTerm = ''

    ngOnInit(): void {
        this.loggedIn = this.loginService.isLoggedIn();
        this.countryService.getCountry().subscribe((country: any) => {
            this.countries = country
            this.allCountries = this.countries
            console.log(country)
            console.log(this.allCountries)
        })
    }
    search(value: string): void {
        this.countries = this.allCountries.filter(
            (val) =>
                val.country.toLowerCase().includes(value) ||
                val.country.toUpperCase().includes(value)
        )
    }

    userData = localStorage.getItem('userData')
    userEmail = JSON.parse(this.userData || '{}').email

    onAdd(
        country: string,
        deceased: string,
        infected: string,
        recovered: string,
        id: string
    ) {
        this.countryInfo.country = country
        this.countryInfo.deceased = deceased
        this.countryInfo.infected = infected
        this.countryInfo.recovered = recovered
        this.countryInfo.id = id
        this.watchlistService
            .addCountry(this.userEmail, this.countryInfo)
            .subscribe((response) => {
                console.log(response)
            })
            Swal.fire(this.countryInfo.country, " Added Successfully",'success');
    }

    constructor(
        private loginService: LoginService,
        private countryService: CountryService,
        pipe: DecimalPipe,
        private watchlistService: WatchlistService
    ) {}

    loginClicked(){
        this.loggedIn = this.loginService.isLoggedIn();
      }

}
