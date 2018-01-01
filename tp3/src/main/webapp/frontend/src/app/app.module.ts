import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MaterializeModule } from 'ng2-materialize';
import { FormsModule, ReactiveFormsModule }   from '@angular/forms';
import { HttpModule} from '@angular/http';
import { RouterModule, Routes } from '@angular/router';


import { AppComponent } from './app.component';
import { MenuComponent } from './menu/menu.component';
import { EntrepotComponent } from './entrepot/entrepot.component';
import { FormsEntrepotComponent } from './forms-entrepot/forms-entrepot.component';
import { MarchandisesComponent } from './marchandises/marchandises.component';
import {EntrepotService } from  './entrepot.service';
import {MarchandisesService } from './marchandises.service';
import { FormMarchandiseComponent } from './form-marchandise/form-marchandise.component';
import { FormApprovisionnementComponent } from './form-approvisionnement/form-approvisionnement.component';
import { ApprovisionnementComponent } from './approvisionnement/approvisionnement.component';
import { LivraisonComponent } from './livraison/livraison.component';
import { FormLivraisonComponent } from './form-livraison/form-livraison.component';
import { FormReceptionnerComponent } from './form-receptionner/form-receptionner.component'
const appRoutes: Routes = [
  { path: 'marchandises', component: MarchandisesComponent },
  { path: 'entrepot', component: EntrepotComponent },
  { path: '', component: MarchandisesComponent },
  { path: 'livraison', component: LivraisonComponent },
  { path: 'approvisionnement', component: ApprovisionnementComponent }


];



@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    EntrepotComponent,
    FormsEntrepotComponent,
    MarchandisesComponent,
    FormMarchandiseComponent,
    FormApprovisionnementComponent,
    ApprovisionnementComponent,
    LivraisonComponent,
    FormLivraisonComponent,
    FormReceptionnerComponent
  ],
  imports: [
    BrowserModule,
    MaterializeModule,
    FormsModule,
    HttpModule,
    ReactiveFormsModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [EntrepotService, MarchandisesService],
  bootstrap: [AppComponent]
})
export class AppModule { }
