import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { EditProfileComponent } from './edit-profile/edit-profile.component';
import { HomeComponent } from './home/home.component';
import { WatchlistComponent } from './watchlist/watchlist.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { CountryComponent } from './country/country.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AuthGuard } from './services/auth.guard';
import { LoginService } from './services/login.service';
import { AuthInterceptor } from './services/auth.interceptor';
import { SearchFilterPipe } from './services/search-filter.pipe';


const routes: Routes=[
  {
    path:'',
    redirectTo: '/home',
    pathMatch: 'full',
  },
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path:'user-login',
    component: LoginComponent
  },
  {
    path:'watchlist',
    component: WatchlistComponent,
    canActivate: [AuthGuard]
  },
  {
    path:'user-register',
    component: RegisterComponent
  },
  {path:'user-edit-profile',
    component: EditProfileComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'country/:countryName/:countryId',
    component: CountryComponent
  },
]
@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginComponent,
    RegisterComponent,
    EditProfileComponent,
    HomeComponent,
    WatchlistComponent,
    SearchFilterPipe,
    CountryComponent,
  ],
  imports: [
    BrowserModule, RouterModule.forRoot(routes), HttpClientModule, FormsModule,
    ReactiveFormsModule,NgbModule
  ],
  providers: [LoginService, AuthGuard ],
  bootstrap: [AppComponent]
})
export class AppModule { }
