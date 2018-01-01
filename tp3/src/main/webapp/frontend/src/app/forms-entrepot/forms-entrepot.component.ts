import { Component, OnInit } from '@angular/core';
import {FormGroup, FormControl, Validators} from "@angular/forms";
import {EntrepotService} from "../entrepot.service"
import {EntrepotComponent} from "../entrepot/entrepot.component";

@Component({
  selector: 'app-forms-entrepot',
  templateUrl: './forms-entrepot.component.html',
  styleUrls: ['./forms-entrepot.component.css'],
  providers: [EntrepotService]
})
export class FormsEntrepotComponent implements OnInit {
  title = "Ajouter un nouveau entrepôt";
  myform: FormGroup;

  constructor(private entrepotservice: EntrepotService,  private EntrepotComponent: EntrepotComponent) { }


  onSubmit() {
    if (this.myform.valid) {
      this.entrepotservice.addEntrepot(this.myform.value).then(
        (res) => {
          Materialize.toast('L\' entrepot a été bien ajouté ', 2000, 'green rounded top right');
          this.EntrepotComponent.getEntrepot();

        },
        (err) => {
          Materialize.toast('Une erreur s\'est produite !! Réesayer  ', 2000, 'red rounded top right');
        }
      );

      this.myform.reset();
      //this.myform.reset();



    }

  }




  ngOnInit() {
    this.myform = new FormGroup({
      nom: new  FormControl('', Validators.required),
      capacite: new FormControl('', Validators.required)
    });
  }

}
