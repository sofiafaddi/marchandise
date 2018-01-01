import {Component, OnInit, Input} from '@angular/core';
import {FormGroup, FormControl, Validators} from "@angular/forms";
import {MarchandisesService} from "../marchandises.service"
import {MarchandisesComponent} from "../marchandises/marchandises.component";
import {Marchandisesmodele} from "../marchandisesmodele";

@Component({
  selector: 'app-form-marchandise',
  templateUrl: './form-marchandise.component.html',
  styleUrls: ['./form-marchandise.component.css']
})
export class FormMarchandiseComponent implements OnInit {
  @Input()  title : string;
  @Input("formModificationChild") formModificationChild:Marchandisesmodele;
   myform: FormGroup;

  constructor(private MarchandisesService: MarchandisesService, private MarchandisesComponent: MarchandisesComponent) { }

  onSubmit() {
    if (this.myform.valid) {
      if(this.title =="Modifier") {
        this.MarchandisesService.updateMarchandise(this.myform.value,this.formModificationChild.reference ).
        then( (res) => {
          Materialize.toast('La marchandise a été bien mis à jour ', 2000, 'green rounded top right');
          this.MarchandisesComponent.getMarchandises();
          this.MarchandisesComponent.formModificationParent = this.formModificationChild;

        },
        (err) => {
          Materialize.toast('Une erreur s\'est produite !! Réesayer  ', 2000, 'red rounded top right');
        }
      );

}     else {

        this.MarchandisesService.addMarchandise(this.myform.value).then(
          (res) => {
            Materialize.toast('La marchandise a été bien ajouté ', 2000, 'green rounded top right');
            this.MarchandisesComponent.getMarchandises();

          },
          (err) => {
            Materialize.toast('Une erreur s\'est produite !! Réesayer  ', 2000, 'red rounded top right');
          }
        );
        this.myform.reset();
      }


    }

  }


  ngOnInit() {
    this.myform = new FormGroup({

      nom: new  FormControl("", Validators.required),
      volumeUnitaire: new FormControl("", Validators.required),
      description: new FormControl("", Validators.required)
    });


  }


  }
