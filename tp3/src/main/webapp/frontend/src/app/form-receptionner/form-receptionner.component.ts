import {Component, OnInit, Input} from '@angular/core';
import {Approvisionnement} from "../approvisionnement";
import {FormGroup, FormControl, Validators} from "@angular/forms";
import {ApprovisionnementserviceService} from "../approvisionnementservice.service";

@Component({
  selector: 'app-form-receptionner',
  templateUrl: './form-receptionner.component.html',
  styleUrls: ['./form-receptionner.component.css'],
  providers: [ApprovisionnementserviceService]
})
export class FormReceptionnerComponent implements OnInit {
  title = "Receptionner";
  @Input("ApprovisionnementList") ApprovisionnementList:Approvisionnement;
  myformreceptionner: FormGroup;

  constructor(ApprovisionnementserviceService :ApprovisionnementserviceService) { }


  onSubmit() {
    if (this.myformreceptionner.valid) {
      console.log(this.ApprovisionnementList);
      this.ApprovisionnementserviceService.postReception(this.myform.value, this.ApprovisionnementList).then((res) => {
          Materialize.toast('La marchandise a été bien mis à jour ', 2000, 'green rounded top right');
          this.ApprovisionnementserviceService.getApprovisionnement();
          //this.MarchandisesComponent.formModificationParent = this.formModificationChild;

        },
        (err) => {
          Materialize.toast('Une erreur s\'est produite !! Réesayer  ', 2000, 'red rounded top right');
        }
     );
    }
  }

        ngOnInit() {
    this.myformreceptionner = new FormGroup({

      dateEffectue: new  FormControl("", Validators.required)
    });
  }

}
