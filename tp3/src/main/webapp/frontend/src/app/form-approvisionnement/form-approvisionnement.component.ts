import {Component, OnInit, Input} from '@angular/core';

import {FormGroup, FormControl, Validators} from "@angular/forms";
import {Marchandisesmodele} from "../marchandisesmodele";
import {EntrepotModele} from "../entrepotmodele";
import {ApprovisionnementserviceService} from "../approvisionnementservice.service";
import { ApprovisionnementComponent } from "../approvisionnement/approvisionnement.component";

@Component({
  selector: 'app-form-approvisionnement',
  templateUrl: './form-approvisionnement.component.html',
  styleUrls: ['./form-approvisionnement.component.css'],
})
export class FormApprovisionnementComponent implements OnInit {
  myformapprovisionnement: FormGroup;
  title="ajouter un approvisionnement"

  @Input('marchandiseListShow') marchandiseList: Marchandisesmodele[];
  @Input('EntrepotListShow') EntrepotList: EntrepotModele[];
  constructor(private ApprovisionnementserviceService: ApprovisionnementserviceService, private ApprovisionnementComponent:ApprovisionnementComponent) { }

  onSubmit() {
    console.log("f",this.myformapprovisionnement);/*.fournisseur);
    console.log("p",this.myformapprovisionnement.prevu);
    console.log("m",this.myformapprovisionnement.magasin);
    console.log("q",this.myformapprovisionnement.quantite);*/

    if (this.myformapprovisionnement.valid) {

      let datatoAdd =
      {
        "fournisseur":this.myformapprovisionnement.value.fournisseur,
        "prevu": this.myformapprovisionnement.value.prevu,
        "magasin": this.myformapprovisionnement.value.magasin,
        "marchandise": {"reference": this.myformapprovisionnement.value.allMarchandises},
        "entrepot": {"nom": this.myformapprovisionnement.value.allEntrepots},
        "quantite": this.myformapprovisionnement.value.quantite
      }
      console.log(this.myformapprovisionnement.value.prevu);

      this.ApprovisionnementserviceService.addApprovisionnement(datatoAdd).then(
        (res) => {
          Materialize.toast('L\' approvisionnement a été bien ajouté ', 2000, 'green rounded top right');
          this.ApprovisionnementComponent.getApprovisionnent();

        },
        (erreur) => {
          Materialize.toast(erreur.json().message.toString(), 2000,'red rounded top right' );
        }
      );

      this.myformapprovisionnement.reset();
    }
  }

  ngOnInit() {

    this.myformapprovisionnement = new FormGroup({
      fournisseur: new  FormControl("", Validators.required),
      prevu: new FormControl("", Validators.required),
      magasin: new FormControl("", Validators.required),
      quantite: new FormControl("", Validators.required),
      allMarchandises: new FormControl("", Validators.required),
      allEntrepots: new FormControl("", Validators.required),

    });


  }


}
