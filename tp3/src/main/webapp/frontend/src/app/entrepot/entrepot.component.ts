import { Component, OnInit } from '@angular/core';
import {EntrepotService} from "../entrepot.service";
import { EntrepotModele } from '../entrepotmodele'

@Component({
  selector: 'app-entrepot',
  templateUrl: './entrepot.component.html',
  styleUrls: ['./entrepot.component.css'],
  providers: [EntrepotService]
})
export class EntrepotComponent implements OnInit {
  title = "Liste des Entrepôts";
  entrepotList: EntrepotModele[];

  constructor(private entrepotservice: EntrepotService) { }
  getEntrepot(): void {
    this.entrepotservice.getEntrepots()
      .subscribe(
        (entrepotList) => {
          this.entrepotList = entrepotList;
        });

  }

  ngOnInit() {
    this.getEntrepot();

    $(document).ready(function() {
      //the "href" attribute of the modal trigger must specify the modal ID that wants to be triggered
      $('.modal').modal();

    });





  }

}
