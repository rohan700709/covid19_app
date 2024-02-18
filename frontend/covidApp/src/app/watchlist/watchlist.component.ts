import { Component, OnInit } from '@angular/core'
import { WatchlistService } from '../services/watchlist.service'
import Swal from 'sweetalert2'

@Component({
    selector: 'app-watchlist',
    templateUrl: './watchlist.component.html',
    styleUrls: ['./watchlist.component.css'],
})
export class WatchlistComponent implements OnInit {
    userData = localStorage.getItem('userData')
    userEmail = JSON.parse(this.userData || '{}').email
    watchlistCountry: Array<any> = []
    constructor(private watchlistService: WatchlistService) {}

    ngOnInit(): void {
        this.watchlistService
            .getCountry(this.userEmail)
            .subscribe((data: any) => {
                this.watchlistCountry = data
                //console.log(this.watchlistCountry[0].player);
            })
    }

    onDelete(name: string) {
        this.watchlistService
            .deleteCountry(this.userEmail, name)
            .subscribe((response) => {
                console.log(response)
                this.ngOnInit()
            })
            Swal.fire(name, " Deleted Successfully",'warning');
    }
}
