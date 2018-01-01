import {Component, OnInit, Input} from '@angular/core';
import {Livraison} from "../livraison";
import {Validators, FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-form-livraison',
  templateUrl: './form-livraison.component.html',
  styleUrls: ['./form-livraison.component.css']
})
export class FormLivraisonComponent implements OnInit {
  @Input('LivraisonListShow') LivraisonList: Livraison[];
  myformlivraison: FormGroup;
  title = "Receptionner une livraison"

  constructor () {
  }

  ngOnInit () {
    this.myformlivraison = new FormGroup({
      id: new FormControl("", Validators.required),
      dateEffectuee: new FormControl("", Validators.required)
    });

  }
}
